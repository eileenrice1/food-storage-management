FROM maven:latest

COPY foodStorage .

RUN mvn package

EXPOSE 8080

CMD [ "java", "-cp", "target/foodStorage-1.0-SNAPSHOT-jar-with-dependencies.jar", "edu.csu.cs370.foodStorage.RestfulServer" ]
