# Starta med en officiell Java 17-bild (ändra till 21 om du använder det)
FROM eclipse-temurin:21-jdk

# Skapa en arbetskatalog
WORKDIR /app

# Kopiera din färdiga jar-fil (antag att du har kört mvn clean package först)
COPY target/*.jar app.jar

# Exponera porten som din app kör på
EXPOSE 8080

# Kör jar-filen
ENTRYPOINT ["java", "-jar", "app.jar"]
