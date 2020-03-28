FROM adoptopenjdk/openjdk11:alpine-slim
VOLUME /tmp
ADD multiple-id-manage-ws-0.1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
