# Roman Numeral Converter

[![CI](https://github.com/lMysticl/RomanConverter/actions/workflows/ci.yml/badge.svg)](https://github.com/lMysticl/RomanConverter/actions/workflows/ci.yml)

A small Spring Boot web application for converting integers to canonical Roman numerals and back.

The supported range is `1` through `3999`. The repository contains a browser interface, two REST endpoints, and automated tests. It is a portfolio project; no hosted deployment or production infrastructure is included.

## Requirements

- JDK 21
- No separate Maven installation: the included Maven Wrapper downloads Maven 3.9.9 when needed

The application has no database, credentials, or required environment variables.

## Run Locally

Clone the repository:

```shell
git clone https://github.com/lMysticl/RomanConverter.git
cd RomanConverter
```

On macOS or Linux:

```shell
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080). Stop the application with `Ctrl+C`.

## REST API

Both endpoints use `POST` and return the converted value directly in the response body.

| Endpoint | Example | Successful response |
| --- | --- | --- |
| `/api/toRoman` | `POST /api/toRoman?number=1994` | `MCMXCIV` |
| `/api/fromRoman` | `POST /api/fromRoman?roman=MCMXCIV` | `1994` |

Example requests:

```shell
curl -X POST "http://localhost:8080/api/toRoman?number=1994"
curl -X POST "http://localhost:8080/api/fromRoman?roman=MCMXCIV"
```

`toRoman` accepts integers from `1` through `3999`. `fromRoman` is case-insensitive and accepts only canonical Roman numerals in the same range. Out-of-range values and non-canonical numerals return HTTP `400` with a short error message; Spring also rejects missing or malformed request parameters with HTTP `400`.

## Build and Test

Run the test suite:

```shell
./mvnw test
```

On Windows, replace `./mvnw` with `.\mvnw.cmd`.

Run all verification steps and create the executable JAR:

```shell
./mvnw verify
java -jar target/roman-converter-0.0.1-SNAPSHOT.jar
```

GitHub Actions runs `verify` with Java 21 for pushes and pull requests.

## Design

The browser interface is served from `src/main/resources/static`. It sends conversion requests to `RomanController`, which delegates validation and conversion to `RomanService`.

`RomanService` precomputes the canonical representation of every supported number during application startup. The same mappings are used for both conversion directions, so non-canonical forms such as `IIII` are rejected.

```text
Browser UI
    |
    v
RomanController (/api)
    |
    v
RomanService (1-3999 mappings)
```

## Project Structure

```text
src/
├── main/
│   ├── java/com/roman/converter/
│   │   ├── RomanConverterApplication.java
│   │   ├── controller/RomanController.java
│   │   └── service/RomanService.java
│   └── resources/
│       ├── application.properties
│       └── static/
└── test/java/com/roman/converter/
```

## Contributing

Before opening a pull request:

1. Create a focused branch.
2. Keep the existing API paths and response behavior stable unless the change explicitly requires a contract update.
3. Run `./mvnw verify` (or `.\mvnw.cmd verify` on Windows).
4. Describe the behavior changed and the verification performed.

## License

Copyright 2024 Mystic. All rights reserved.

This repository is **not** licensed under MIT or another open-source license. The existing [LICENSE](LICENSE) reserves all rights; obtain explicit permission from the copyright holder before using the code.
