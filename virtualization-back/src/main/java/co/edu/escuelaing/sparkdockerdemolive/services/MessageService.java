package co.edu.escuelaing.sparkdockerdemolive.services;

import co.edu.escuelaing.sparkdockerdemolive.models.Message;
import co.edu.escuelaing.sparkdockerdemolive.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(){

        List<Message> messages = this.messageRepository.findAll();

        messages.sort((o1, o2) ->
                o1.getCreatedAt()
                        .isBefore(o2.getCreatedAt()) ? 1 : -1);

        return messages;
    }

    public Message createMessage(Message message){
        message.setCreatedAt(LocalDateTime.now());
        return this.messageRepository.saveOne(message);
    }
}
