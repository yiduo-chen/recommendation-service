# Recommendation Service (MVP)

A minimal Spring Boot REST service backed by Amazon DynamoDB.

This project is intentionally designed as a **cloud engineer portfolio project**: it is cost-aware, deployment-ready, and provides strong traceability via build and Git metadata.

---

## Features

- Spring Boot REST API (MVP)
- Amazon DynamoDB integration (PAY_PER_REQUEST)
- Composite primary key design (`pk`, `sk`)
- Request validation with clear error responses
- Spring Boot Actuator for health and info endpoints
- Build and Git metadata exposed via `/actuator/info`
- Docker-ready for containerized deployment

---

## Tech Stack

- Java 17
- Spring Boot
- Maven Wrapper
- Amazon DynamoDB
- Docker
- AWS CLI

---

## API Endpoints

### Create a Recommendation

`POST /api/recommendations`

**PowerShell example:**

```powershell
Invoke-RestMethod \
  -Method Post \
  -Uri "http://localhost:8085/api/recommendations" \
  -ContentType "application/json" \
  -Body '{"title":"Tycho - Awake","reason":"Clean and uplifting electronic music that helps you stay productive."}'
```

---

### Get a Random Recommendation

`GET /api/recommendations/random`

```powershell
Invoke-RestMethod -Uri "http://localhost:8085/api/recommendations/random"
```

---

### Observability

- `GET /actuator/health`
- `GET /actuator/info`

The `/actuator/info` endpoint exposes:
- Application version
- Build timestamp
- Git branch and commit SHA

This enables precise identification of which version is running in a container or cloud environment.

---

## Local Development Setup

### Prerequisites

- Java 17
- AWS CLI configured (`aws configure`)
- An AWS account with DynamoDB access

---

### Create DynamoDB Table

```powershell
aws dynamodb create-table \
  --table-name recommendation \
  --attribute-definitions AttributeName=pk,AttributeType=S AttributeName=sk,AttributeType=S \
  --key-schema AttributeName=pk,KeyType=HASH AttributeName=sk,KeyType=RANGE \
  --billing-mode PAY_PER_REQUEST \
  --region ap-northeast-1
```

---

### Run the Application Locally

```powershell
.\mvnw.cmd spring-boot:run
```

The service will start at:

```
http://localhost:8085
```

---

## Docker

### Build Docker Image

```powershell
docker build -t recommendation:local .
```

### Run Docker Container

```powershell
docker run --rm -p 8085:8085 recommendation:local
```

---

## Versioning Strategy

This project follows **Semantic Versioning**:

- `v0.x.x` — MVP and early iterations
- `v1.0.0` — First production-ready release

Each release is tagged in GitHub. Build and Git metadata ensure that every deployed container can be traced back to an exact commit.

---

## Roadmap

- GitHub Actions CI pipeline (build + test)
- Docker image publishing (ECR / GHCR)
- AWS deployment (ECS Fargate or App Runner)
- Infrastructure as Code (Terraform)
- Centralized logging and metrics

---

## License

MIT License

