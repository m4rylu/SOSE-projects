# SOSE-Tracker-Application
Service-Oriented Software Engineering Microsevices Application

## 📦 Requirements:
- Docker Desktop (recommended at least version 4.43.1)
- Git (optional, for cloning the project)
- Java, Maven and Tomcat are not required locally, everything is managed by docker

## 🧱 Architecture:
The application is based on microservices and uses:
- SOAP and REST services implemented with Java (Spring Boot + Apache CXF)
- Spring Cloud Gateway as centralized access point
- Docker for containerization and orchestration

## 🏗 Project Structure:
```
📂 SOSE-Tracker-Application
┣ 📂 advice-coffee-service/ # REST-CXF Service Advice for Coffee&Sleep
┣ 📂 advice-mood-adapter/   # REST-Spring Adapter for Advice Mood
┣ 📂 advice-mood-service/   # SOAP-CXF Service Advice for Mood
┣ 📂 client/                # An example of client exacutable in Java
┣ 📂 coffee-service/        # REST-CXF Service Coffee Tracker
┣ 📂 data-analysis-service/ # REST-CXF Service for analyzing data
┣ 📂 gateway/               # Spring Cloud Gateway
┣ 📂 humor-adapter/         # REST-Spring Adapter for Humor Tracker
┣ 📂 humor-service/         # Documentazione
┣ 📂 rate-service/          # Documentazione
┣ 📂 sleep-adapter/         # REST-Spring Adapter for Sleep Tracker
┣ 📂 sleep-service/         # SOAP-Spring Service Sleep Tracker
┣ 📂 weather-service/       # REST-Spring Service Weather Adapter
┣ 📄 docker-compose.yml     # Define containers of services and gateway
┣ 📄 README.md              # Documentation 
```
## 🚀 Execute Application:
1. (Optional) Clone the repository or simply download and extract the archive.
2. From project root run the command: `docker-compose up --build`
3. Wait for the build to complete and for all containers to start
4. Once the application is running, services will be accessible at: http://localhost:8087

## 🌐 Available endpoints:
☕ **Coffee Tracker**
- `http://localhost:8087/api/coffee/print` -  Display all recorded coffee entries
- `http://localhost:8087/api/coffee/add/{value}` - Add today's coffee value (e.g. /add/5)
- `http://localhost:8087/api/coffee/lastValues`- Retrieve the last 7 recorded values (excluding today)

📊 **Other Trackers**
- `http://localhost:8087/api/rate/print`
- `http://localhost:8087/api/sleep/print`
- `http://localhost:8087/api/humor/print`
- `http://localhost:8087/api/weather/print`

💡 **Advice & Analytics**
- `http://localhost:8087/api/advice/mood/advice`
- `http://localhost:8087/api/advice/coffee/advice`
- `http://localhost:8087/api/data/analysis/sleepData`
- `http://localhost:8087/api/data/analysis/coffeeData`
- `http://localhost:8087/api/data/analysis/humorData`

## Documentation of Services:
- Swagger UI (REST): `http://localhost:8087/api/coffee/openapi` (Only REST Services)
- WSDL (SOAP) e WADL (REST): es. `http://localhost:8087/api/sleep/wadl`
- Javadoc available in the source code.

## 📌 Notes:
- The system currently supports a single user for simplicity.
- The project is extensible to include:
    - authentication
    - load balancing with Eureka
    - multiple users. 
- Eureka has not been implemented as it is not supported by services with CXF.
- The user interface is not included: calls are tested from the included client service or directly from the browser.

