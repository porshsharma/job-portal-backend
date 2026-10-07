# Job Portal Backend

A REST API backend for a job portal application, built with Spring Boot. Supports user registration/authentication, job postings, company profiles, and job applications, with role-based access control for Employers and Applicants.

## Tech Stack

- **Java 17**
- **Spring Boot 4** (Spring Web, Spring Data JPA, Spring Security)
- **H2 Database** (in-memory, for development)
- **JWT (JSON Web Tokens)** for authentication
- **BCrypt** for password hashing
- **Maven** for dependency management

## Features

- User registration and login with encrypted passwords
- JWT-based authentication
- Role-based access control:
  - **EMPLOYER** — can create, update, and delete job postings
  - **APPLICANT** — can apply to job postings
- Full CRUD for job postings
- Company profiles linked to their owning Employer
- Job applications linking Applicants to Jobs, with status tracking
- Centralized exception handling for clean, consistent error responses

## Entities

- **User** — name, email, password (encrypted), role (APPLICANT / EMPLOYER / ADMIN)
- **Job** — title, description, location, salary range, job type, status
- **Company** — name, description, website, linked to an owning User
- **Application** — links a User (applicant) to a Job, tracks status (APPLIED / SHORTLISTED / REJECTED / HIRED)

## API Endpoints

### Auth
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/users/register` | Public | Register a new user |
| POST | `/api/users/login` | Public | Log in, returns a JWT token |
| GET | `/api/users` | Authenticated | List all users |

### Jobs
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/jobs` | Authenticated | List all jobs |
| POST | `/api/jobs` | EMPLOYER only | Create a job |
| PUT | `/api/jobs/{id}` | EMPLOYER only | Update a job |
| DELETE | `/api/jobs/{id}` | EMPLOYER only | Delete a job |

### Companies
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/companies` | Authenticated | Create a company (owner auto-assigned) |
| GET | `/api/companies` | Authenticated | List all companies |
| GET | `/api/companies/{id}` | Authenticated | Get a company by id |

### Applications
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/applications` | APPLICANT only | Apply to a job |
| GET | `/api/applications` | Authenticated | List all applications |
| PUT | `/api/applications/{id}/status` | Authenticated | Update application status |

## Running Locally

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`. The H2 console is available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:jobportaldb`, username: `sa`, no password).

## Planned Next Steps

- Swap H2 for a persistent PostgreSQL database
- Add input validation and DTOs
- Add pagination and filtering to job listings
- Add unit tests (JUnit + Mockito)
- Dockerize and deploy
- Add Swagger/OpenAPI documentation

## Author

Built by Porsh Sharma as a learning project to strengthen backend development skills with Java and Spring Boot.