package neo4j.demo

import org.neo4j.kernel.*
import org.neo4j.graphdb.*


/**
 * Created by user1 on 4/28/14.
 */
db = new EmbeddedGraphDatabase("/tmp/target/neo4j-hello-db")
tx = db.beginTx()
tx.success()
tx.finish()
db.shutdown()
