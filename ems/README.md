# Employee Management System(ems)

A web-based Employee Management System that allows users to create, view, update, delete, search, and paginate employee records.

## 1. Features

- Add new employees
- View employee details
- Update existing employee records
- Delete employees
- Search employees by ID
- Server-side pagination
- Display employee details, including name, email, job type, designation, department, salary, and address
- Responsive HTML, CSS, and JavaScript frontend
- REST API integration with Spring Boot

## 2. Technology Stack

| Technology | Purpose |
|---|---|
| Java 27 | Backend development |
| Spring Boot 4 | REST API development |
| Spring Web | HTTP endpoints |
| Spring Data JPA | Database operations |
| HTML5 | Frontend structure |
| CSS3 | Frontend styling |
| JavaScript | Frontend interactions |
| Gradle 9 | Build and dependency management |
| IntelliJ IDEA | Development environment |

## 3. Prerequisites

Install the following software before running the application:

- IntelliJ IDEA
- JDK-27 version configured in the Spring Boot project
- Gradle 9.8.1
- H2 database(No need to Install anything)

Verify your Java installation:

```bash
java -version
```

Verify Gradle installation:

```bash
gradle -version
```

**Note:** Use the Java version specified in your project's `build.gradle.kts` file.

## 4. Project Structure:


```text
ems/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/bs/ems/
│       │       ├── controller/
│       │       ├── service/
│       │       ├── repository/
│       │       ├── entities/
│       │       ├── dto/
│       │       └── EmsApplication.java
│       └── resources/
│           └── application.yaml
│           └── static/  
│               └── index.html
│
├── build.gradle.kts
└── README.md
```

## 5. Clone/download the Project

```bash
git clone <repository-url>
cd ems
```

Then, open the project directory directly in IntelliJ IDEA.

1. Launch IntelliJ IDEA.
2. Select **File → Open**.
3. Select the project directory.
4. Allow IntelliJ IDEA to import the Gradle dependencies.
5. Configure the required JDK-27 under **File → Project Structure → Project SDK**.

## 6. Run the application

### Option A: Run directly from IntelliJ IDEA

1. Locate the main Spring Boot application class `EmsApplication.java`.
2. Open the class.
3. Click the green **Run** icon beside the `main()` method.
4. Wait for Spring Boot to start successfully.

The application will typically run at:
```text
http://localhost:8080
```

### Option B: Run using Gradle

On Linux:

```bash
./gradlew bootRun
```

On Windows:

```bat
gradlew.bat bootRun
```

### Open the below url in your browser: 

```text
http://localhost:8080/index.html
```

## 7. REST API Endpoints

The frontend expects the following API endpoints:

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create an employee |
| GET | `/api/employees/{id}` | Retrieve an employee by ID |
| GET | `/api/employees/paged?page=0&size=5` | Retrieve a paginated employee list |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |

The paginated endpoint should return a Spring Data `Page` response containing fields such as:

- `content` — employees on the current page
- `number` — zero-based current page number
- `totalPages` — total number of pages
- `totalElements` — total number of matching employees
