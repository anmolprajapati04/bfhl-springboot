# BFHL - Chitkara API Round (Java / Spring Boot)

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+

## 1. Personalize Your Details

Open `src/main/resources/application.properties` and fill in your details:

```properties
app.user.full-name=john_doe
app.user.dob=17091999
app.user.email=john@xyz.com
app.user.roll-number=ABCD123
```

The `dob` value should be in `ddmmyyyy` format. The API returns `user_id` as `<full_name>_<dob>` in lowercase.

## 2. Build and Run Locally

From the project root, where `pom.xml` is located:

```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080/bfhl
```

## 3. Run Tests

```bash
mvn test
```

All 13 tests should pass.

## 4. Manual API Tests

### Example A

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

Expected response:

```json
{
  "is_success": true,
  "user_id": "john_doe_17091999",
  "email": "john@xyz.com",
  "roll_number": "ABCD123",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### Example B

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]}'
```

Expected: `"sum": "103"`, `"concat_string": "ByA"`.

### Example C

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["A", "ABCD", "DOE"]}'
```

Expected: `"sum": "0"`, `"concat_string": "EoDdCbAa"`.

### Error Case: Missing Data Field

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{}'
```

Expected: `400 Bad Request`, `"is_success": false`.

## 5. Hosting on Render

1. Push this project to a GitHub repository.
2. Go to https://render.com and create a new Web Service.
3. Connect your GitHub repo.
4. Set the build command:

```bash
mvn clean package -DskipTests
```

5. Set the start command:

```bash
java -jar target/bfhl-0.0.1-SNAPSHOT.jar
```

6. Add environment variables if you do not want to keep personal details in `application.properties`:

```text
APP_USER_FULL_NAME=your_name
APP_USER_DOB=ddmmyyyy
APP_USER_EMAIL=your@email.com
APP_USER_ROLL_NUMBER=your_roll
```

Your endpoint will be:

```text
https://<your-service>.onrender.com/bfhl
```

## Project Structure

```text
bfhl/
|-- pom.xml
|-- README.md
|-- src/
|   |-- main/
|   |   |-- java/com/chitkara/bfhl/
|   |   |   |-- BfhlApplication.java
|   |   |   |-- controller/BfhlController.java
|   |   |   |-- dto/BfhlRequestDto.java
|   |   |   |-- dto/BfhlResponseDto.java
|   |   |   |-- dto/ErrorResponseDto.java
|   |   |   |-- exception/GlobalExceptionHandler.java
|   |   |   |-- service/BfhlService.java
|   |   |   |-- service/impl/BfhlServiceImpl.java
|   |   |-- resources/application.properties
|   |-- test/java/com/chitkara/bfhl/
|       |-- BfhlServiceImplTest.java
|       |-- BfhlControllerIntegrationTest.java
```
