FROM amazoncorretto:17-alpine

# Update package list and install curl in one step
RUN apk update && apk add curl

#ARG JAR_FILE=target/*.jar
COPY target/userMS-0.0.1-SNAPSHOT.jar userMS-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} userMS-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","userMS-0.0.1-SNAPSHOT.jar"]