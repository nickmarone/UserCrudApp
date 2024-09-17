# define base docker image
FROM openjdk:22
LABEL maintainer="nicholas"
ADD target/UserCrudApp-1.0-SNAPSHOT.jar UserCrudApp.jar
ENTRYPOINT ["java", "-jar", "UserCrudApp.jar"]
