# Maven build container
# FROM maven:3.8.5-openjdk-11 AS maven_build
# FROM maven:3.6.3-jdk-11-slim AS maven_build

FROM maven:3.8.5-openjdk-17 AS maven_build

# FROM maven:openjdk-13:alpine-slim AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image
# FROM adoptopenjdk:11-jre-hotspot
# FROM adoptopenjdk/openjdk13
FROM eclipse-temurin:17

#maintainer 
MAINTAINER dstar555@yahoo.com
#expose port 8080
EXPOSE 8080

#default command
CMD java -jar /data/demo-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/demo-0.0.1-SNAPSHOT.jar /data/demo-0.0.1-SNAPSHOT.jar



# FROM openjdk:17-oracle

# COPY target/*.jar demo.jar

# EXPOSE 8080

# ENTRYPOINT ["java","-jar","demo.jar"]