package co.edu.escuelaing.sparkdockerdemolive.repositories;

import co.edu.escuelaing.sparkdockerdemolive.configuration.MongoFactory;
import co.edu.escuelaing.sparkdockerdemolive.models.Message;

public class MessageRepository extends  BaseMongoRepository<Message> {

    public MessageRepository() {
        super(MongoFactory.getDatabase("messages", Message.class));
    }
}
