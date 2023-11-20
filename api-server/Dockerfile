# Use the official image as a parent image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /api-server

# Copy the JAR file from the Maven build output (in target directory) to the container
COPY target/api-server-1.0-SNAPSHOT.jar /api-server/api-server.jar

# Expose port 8090 for the application
EXPOSE 8090

# Run the app. This uses ENTRYPOINT ["java", "-jar", "/api-server/api-server.jar"] instead of CMD to make your container exit when the application process finishes.
ENTRYPOINT ["java", "-jar", "/api-server/api-server.jar"]