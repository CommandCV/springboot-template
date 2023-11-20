# Use the official image as a parent image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /api-client

# Copy the JAR file from the Maven build output (in target directory) to the container
COPY target/api-client-1.0-SNAPSHOT.jar /api-client/api-client.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the app. This uses ENTRYPOINT ["java", "-jar", "/api-client/api-client.jar"] instead of CMD to make your container exit when the application process finishes.
ENTRYPOINT ["java", "-jar", "/api-client/api-client.jar"]