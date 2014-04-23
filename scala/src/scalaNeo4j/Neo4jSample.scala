package scalaNeo4j

/**
 * Created by user1 on 4/23/14.
 */

import org.neo4j.graphdb._
import org.neo4j.kernel._
import org.neo4j.index._
import org.neo4j.index.lucene._

object RelTypes extends Enumeration {
  type RelTypes = Value
  val USERS_REFERENCE, USER = Value

  implicit def conv(rt: RelTypes) = new RelationshipType() {def name = rt.toString}
}

import RelTypes._

object EmbeddedNeo4jWithIndexingS {
  private val DB_PATH = "neo4j-store"
  private val USERNAME_KEY = "username"
  private val graphDb: GraphDatabaseService = new EmbeddedGraphDatabase(DB_PATH)
  private val indexService: IndexService = new LuceneIndexService(graphDb)

  private def registerShutdownHook =
    Runtime.getRuntime.addShutdownHook(new Thread() {override def run = shutdown})

  private def shutdown = {
    indexService.shutdown
    graphDb.shutdown
  }

  private def idToUserName(id: Int) = {
    "user" + id + "@neo4j.org"
  }

  private def createAndIndexUser(username: String) = {
    val node = graphDb.createNode
    node.setProperty(USERNAME_KEY, username)
    indexService.index(node, USERNAME_KEY, username)
    node
  }

  private def doTx(f: GraphDatabaseService => Unit) = {
    val tx = graphDb.beginTx
    try
    {
      f(graphDb)
      tx.success
    }
    finally {
      tx.finish
    }
  }

  def main(args: Array[String]): Unit = {
    registerShutdownHook
    doTx {
      db =>
        val usersReferenceNode = db.createNode

        db.getReferenceNode.createRelationshipTo(usersReferenceNode, USERS_REFERENCE)

        for (id <- 0 to 100)
        {
          val userNode = createAndIndexUser(idToUserName(id))
          usersReferenceNode.createRelationshipTo(userNode, USER);
        }
        println("Users created")

        val idToFind = 45
        val foundUser = indexService.getSingleNode(USERNAME_KEY, idToUserName(idToFind))
        println("The username of user " + idToFind + " is " + foundUser.getProperty(USERNAME_KEY))
        for (relationship <- usersReferenceNode.getRelationships(RelTypes.USER, Direction.OUTGOING))
        {
          val user = relationship.getEndNode
          indexService.removeIndex(user, USERNAME_KEY, user.getProperty(USERNAME_KEY))
          user.delete
          relationship.delete
        }
        usersReferenceNode.getSingleRelationship(USERS_REFERENCE, Direction.INCOMING).delete
        usersReferenceNode.delete
    }
  }
}
