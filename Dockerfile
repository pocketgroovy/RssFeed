FROM openjdk:22-slim
MAINTAINER pocketgroovy.com
COPY target/RssReaderDemo-0.0.1-SNAPSHOT.jar RssReaderDemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/RssReaderDemo-0.0.1-SNAPSHOT.jar"]