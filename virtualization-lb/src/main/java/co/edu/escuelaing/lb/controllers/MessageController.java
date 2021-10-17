package co.edu.escuelaing.lb.controllers;

import co.edu.escuelaing.lb.models.Message;
import co.edu.escuelaing.lb.services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class MessageController {
    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
        this.objectMapper = new ObjectMapper().registerModule(
                new JavaTimeModule()
        );
    }

    public Object getMessages(Request request, Response response) throws IOException {
        response.status(200);
        response.type("application/json");
        return this.objectMapper.writeValueAsString(
                this.messageService.getMessages()
        );
    }

    public Object createMessage(Request request, Response response) throws IOException {

        response.status(200);
        response.type("application/json");
        Message message = this.objectMapper.readValue(request.body(), Message.class);

        Message createdMessage = this.messageService.createMessage(message);

        return this.objectMapper.writeValueAsString(createdMessage);
    }
}
