# Use official Maven + Java 17 image
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory inside container
WORKDIR /app

# Install curl (needed for service readiness check)
RUN apt-get update && apt-get install -y curl

# Copy project files into container
COPY . .

# Pre-download dependencies (faster builds later)
RUN mvn dependency:go-offline

# Build project (skip tests at build stage)
RUN mvn clean install -DskipTests

# Default command:
# Wait for mock-api to be ready, then run tests
CMD sh -c "\
echo 'Waiting for mock-api...'; \
until curl -s http://mock-api:3000/users > /dev/null; do \
  echo 'mock-api not ready yet...'; \
  sleep 2; \
done; \
echo 'mock-api is up! Running tests...'; \
mvn test \
"