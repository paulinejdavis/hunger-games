# hunger-games

## Overview

A SpringBoot API that returns restaurants for a given postcode using an external API. The response is transformed into a simplified clear format.

## Endpoint

GET /restaurants/{postcode}

Example:
http://localhost:8080

## Example Response

```
[
    {
    "name": "Restaurant Name"
    "cuisines": "Indian", "Halal",
    "rating": 4.5
}
]
```

## Tech Stack
- Java
- SpringBoot
- JUnit


## How to Run

```
cd hunger-games
./mvnw spring-boot:run

then open:
http://localhost:8080
```

## Testing
Run tests with:
```
./mvnw test
```

## Infrastructure Decisions
- Used a service layer to handle business logic
- Introduced a Data Transfer Object (DTO) to control exposed fields
- Integrated external API via a client class
- Added basic error handling to ensure stability

## AI Usage
- Used AI for styling, test refinement, error handling and sense checking 


