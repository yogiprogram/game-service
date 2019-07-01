
**Technology Use in Project**

- Gradle
- Java 8
- Spring Boot
- Swagger 2.0 CodeGen and UI
- Docker

**How to Modify Api**

API Yaml file : src/main/resources/swagger/game_service.yml

Use Swagger Editor : https://editor.swagger.io/

**Build Project**

First generate api code using swagger codege and gradle 
`
./gradlew copyGeneratedCode build
`

**Run Service**

`
./gradlew bootRun
`

or 

`
java -jar build/libs/game-service-0.0.1-SNAPSHOT.jar
`

**Docker build**

`
./gradlew buildDocker
`