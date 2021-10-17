package co.edu.escuelaing.sparkdockerdemolive.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoFactory {

    private static final CodecRegistry codecs = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final MongoClient mongoClient = new MongoClient(new MongoClientURI(System.getenv("MONGO_URI")));

    public static <T> MongoCollection<T> getDatabase(String collectionName, Class<T> collectionClass) {

        return mongoClient.getDatabase(System.getenv("MONGO_DATABASE"))
                .withCodecRegistry(codecs)
                .getCollection(collectionName, collectionClass);
    }
}
