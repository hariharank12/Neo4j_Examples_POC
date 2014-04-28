package neo4j.demo

import org.neo4j.rest.graphdb.RestGraphDatabase

/**
 * Created by user1 on 4/28/14.
 */
def db = new RestGraphDatabase("http://localhost:7474/db/data")
def node = db.getNodeById(0)
println node
db.shutdown()
