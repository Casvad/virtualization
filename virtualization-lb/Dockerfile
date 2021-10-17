FROM openjdk:8
ENV PORT 8088

RUN mkdir /code
COPY target/ /target

ENTRYPOINT [ "sh", "-c", "java -cp \"target/classes:target/dependency/*\" co.edu.escuelaing.lb.SparkWebServer" ]