package ch.erni.community.ideasboard.backend;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public abstract class EmbeddedMongoDbTest {

    private static MongoClient mongo;
    private static MongodProcess mongod;

    public static void initializeDB() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        int port = 1337;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = null;
        try {
            mongodExecutable = starter.prepare(mongodConfig);
            mongod = mongodExecutable.start();

            mongo = new MongoClient("localhost", port);
            DB db = mongo.getDB("ideasboard_test");
            db.requestEnsureConnection();

        } finally {
            if (mongodExecutable != null)
                mongodExecutable.stop();
        }
    }

    public static void shutdownDB() throws InterruptedException {
        mongo.close();
        mongod.stop();
    }

}
