package co.edu.escuelaing.lb.routes;

import co.edu.escuelaing.lb.controllers.MessageController;
import co.edu.escuelaing.lb.services.MessageService;

import static spark.Spark.*;

public class ApplicationRoutes {

    private final MessageController messageController;

    public ApplicationRoutes() {
        MessageService messageService = new MessageService();

        this.messageController = new MessageController(messageService);
    }

    public void makeRoutes(){
        staticFileLocation("/ui");
        this.allowCors();
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
