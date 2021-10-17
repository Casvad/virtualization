package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.sparkdockerdemolive.routes.ApplicationRoutes;

import static spark.Spark.port;

public class SparkWebServer {

    public static void main(String... args){
        ApplicationRoutes applicationRoutes = new ApplicationRoutes();

        port(getPort());
        applicationRoutes.makeRoutes();
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
