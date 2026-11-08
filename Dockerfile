# Use Oracle's official JDK 25 image
FROM container-registry.oracle.com/java/openjdk:25-oraclelinux9

# Set the working directory inside the container
WORKDIR /app

# Accept the application version from GitHub Actions 
# (@todo: set defaults externally or internal for generic snapshots)
ARG APP_VERSION

# Copy the JAR from your build host (<root>\projects\..\BasicContainer\) into the container
# Ensure this matches your gradle build output exactly
# @todo: Fix this manual inclusion. (Create Template)
COPY build/libs/BasicContainer-${APP_VERSION}.jar app.jar

# Expose the Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]