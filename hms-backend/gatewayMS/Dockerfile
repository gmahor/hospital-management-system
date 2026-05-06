FROM amazoncorretto:17-alpine

# Update package list and install curl in one step
RUN apk update && apk add curl


COPY target/gatewayMS-0.0.1-SNAPSHOT.jar gatewayMS-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","gatewayMS-0.0.1-SNAPSHOT.jar"]