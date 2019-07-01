
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

**API Example**

1. Creation of the game should be possible with the command:

|Request | Response|
|--- |--- |
|1. `curl -i  --header "Content-Type: application/json" --request POST http://localhost:8088/games` |`HTTP code: 201 Response Body: { "id": "1", "uri": "http://localhost:8088/games/1" }`|
|2. `curl -i  --header "Content-Type: application/json" --request PUT http://localhost:8088/games/1/pits/1` |`HTTP code: 200 Response Body: {"id":1,"uri":"http://localhost:8088/games/1","status":{"1":"0","2":"7","3":"7","4":"7","5":"7","6":"7","7":"1","8":"6","9":"6","10":"6","11":"6","12":"6","13":"6","14":"0"}}`|


 