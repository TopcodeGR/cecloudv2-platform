# ---- Stage 1: Build & Test ----
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy only necessary files to optimize layer caching
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Copy the Maven settings.xml for private repo authentication
COPY maven-settings.xml /root/.m2/settings.xml

# Build arguments for authentication
ARG REPOSILITE_USERNAME
ARG REPOSILITE_PASSWORD

# Set environment variables for Maven
ENV REPOSILITE_USERNAME=${REPOSILITE_USERNAME}
ENV REPOSILITE_PASSWORD=${REPOSILITE_PASSWORD}


COPY src src

# If tests pass, proceed with packaging
RUN chmod +x ./mvnw
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"
RUN ./mvnw exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
RUN ./mvnw clean package

# ---- Stage 2: Create the final image ----
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only the built JAR
COPY --from=build /app/target/*.jar platform.jar

EXPOSE 4003

ENTRYPOINT ["java", "-jar", "platform.jar"]
