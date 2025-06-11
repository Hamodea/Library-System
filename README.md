# 📚 LibrarySystem

Ett bibliotekssystem byggt med Spring Boot, PostgreSQL och Docker. Systemet erbjuder funktionalitet för att hantera böcker, lån och användare via ett REST API.

---

## 🚀 Starta applikationen

### 🔧 1. Krav
- Java 21 (eller Java 17 om du justerar projektets kompilatorversion)
- Maven
- Docker + Docker Compose

---

## 💻 Alternativ 1: Starta i IntelliJ (lokalt utan Docker)

### 📄 Steg-för-steg

1. **Klona projektet**
   ```bash
   git clone <repo-url>
   cd LibrarySystem
   ```

2. **Skapa databasen manuellt** i pgAdmin eller kör följande SQL i din lokala PostgreSQL:
   ```sql
   CREATE DATABASE "library_System";
   ```

3. **Se till att `application.properties` pekar på dev-profilen:**
   ```properties
   spring.profiles.active=dev
   ```

4. **Kör applikationen från IntelliJ** genom att högerklicka på din huvudklass och välja `Run`.

> **Obs!** `.env`-filen behövs inte i detta läge om du konfigurerar `application-dev.properties` direkt.


---

## 🐳 Alternativ 2: Starta med Docker

### 📁 Struktur
```text
.env
/docker/init/LibraryBackup.sql
/docker-compose.yml
```

> ✅ `.env`-filen skickas med projektet, så du behöver inte skapa den själv.

### ▶️ Starta applikationen
```bash
# Första gången (eller vid ändringar i koden):
mvn clean package

docker-compose --env-file .env up --build
```

Om du får fel om databasen eller vill återställa:
```bash
docker-compose down -v
mvn clean package -DskipTests
docker-compose --env-file .env up --build
```

> 📌 Om du har en backup-fil i `docker/init/LibraryBackup.sql`, körs den automatiskt första gången containern startar.

---

## 🔄 Byt mellan profiler

I `application.properties`, ändra aktiv profil beroende på var du kör:
```properties
# För Docker
spring.profiles.active=docker

# För IntelliJ
spring.profiles.active=dev
```

---

## ✅ Exempel: Testa i Postman

```http
GET http://localhost:8080/books
GET http://localhost:8080/books?title=Java&available=true&page=0&size=5&sortBy=title

POST http://localhost:8080/loans
Body (JSON):
{
  "userId": 1,
  "bookId": 2
}

PUT http://localhost:8080/loans/1/return
```

---

## 🧪 Enhetstester
Kör tester med Maven:
```bash
mvn test
```
> Kontrollera att databasen är korrekt konfigurerad om testerna fallerar.

---

## 📄 Stäng ner och rensa Docker
```bash
docker-compose down -v
```

---


