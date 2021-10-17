FROM openjdk:8

RUN mkdir /code
COPY target/ /target

ENTRYPOINT [ "sh", "-c", "java -cp \"target/classes:target/dependency/*\" co.edu.escuelaing.sparkdockerdemolive.SparkWebServer" ]