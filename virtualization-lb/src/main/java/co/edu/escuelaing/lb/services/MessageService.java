package co.edu.escuelaing.lb.services;

import co.edu.escuelaing.lb.configurations.RetrofitConfiguration;
import co.edu.escuelaing.lb.models.Message;
import co.edu.escuelaing.lb.repositories.MessageRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MessageService {

    private final List<MessageRepository> messageRepositories;
    private final AtomicLong requests = new AtomicLong(0);

    public MessageService() {
        this.messageRepositories = RetrofitConfiguration.getMessageRepositoryList();
    }

    public List<Message> getMessages() throws IOException {

        return this.getClient().getMessages().execute().body();
    }

    public Message createMessage(Message message) throws IOException {

        return this.getClient().createMessage(message).execute().body();
    }

    private MessageRepository getClient(){

        long currentRequest = requests.getAndIncrement();
        int currentRoundRobinClient = (int) (currentRequest % this.messageRepositories.size());

        return this.messageRepositories.get(currentRoundRobinClient);
    }
}
