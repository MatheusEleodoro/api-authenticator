FROM openjdk:21-jdk-slim
ENV PROJECT_HOME /app/api-authenticator
ENV JAR_NAME api-authenticator-0.0.1-SNAPSHOT.war

# Instalação do Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

COPY . .

RUN mvn clean package -DskipTests

RUN mv $PROJECT_HOME/target/*.war $PROJECT_HOME/

ENTRYPOINT ["java", "-jar","/app/api-authenticator/api-authenticator-0.0.1-SNAPSHOT.war"]