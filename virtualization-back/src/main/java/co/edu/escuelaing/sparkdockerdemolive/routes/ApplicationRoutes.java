package co.edu.escuelaing.sparkdockerdemolive.routes;

import co.edu.escuelaing.sparkdockerdemolive.controllers.MessageController;
import co.edu.escuelaing.sparkdockerdemolive.repositories.MessageRepository;
import co.edu.escuelaing.sparkdockerdemolive.services.MessageService;

import static spark.Spark.*;

public class ApplicationRoutes {

    private final MessageController messageController;

    public ApplicationRoutes() { //TODO handle dependency injection with Google - juice
        MessageRepository messageRepository = new MessageRepository();
        MessageService messageService = new MessageService(messageRepository);

        this.messageController = new MessageController(messageService);
    }

    public void makeRoutes(){
        this.allowCors();
        get("hello", (req,res) -> "Hello Docker!");

        get("messages", this.messageController::getMessages);
        post("messages", this.messageController::createMessage);
    }

    private void allowCors(){
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }
}
