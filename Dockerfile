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
RUN ./mvnw clean package

# ---- Stage 2: Create the final image ----
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only the built JAR
COPY --from=build /app/target/*.jar platform.jar
COPY --from=build /app/.mvn .mvn
COPY --from=build /app/mvnw mvnw
COPY --from=build /app/pom.xml pom.xml
COPY --from=build /root/.m2/settings.xml /root/.m2/settings.xml


RUN apt-get update && apt-get install -y \
    libglib2.0-0 \
    libnss3 \
    libnspr4 \
    libdbus-1-3 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libatspi2.0-0 \
    libx11-6 \
    libxcomposite1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    libxcb1 \
    libxkbcommon0 \
    libasound2t64 \
    libpango-1.0-0 \
    libcairo2 \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

EXPOSE 4003

ENTRYPOINT ["java", "-jar", "platform.jar"]
