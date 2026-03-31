# Use Oracle's official JDK 25 image
FROM container-registry.oracle.com/java/openjdk:25-oraclelinux9

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR from your Windows host (I:\projects\...) into the container
# Ensure this matches your gradle build output exactly
COPY build/libs/BasicContainer-0.1.1.jar app.jar

# Expose the Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]