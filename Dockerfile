FROM openjdk:11
COPY target/VacationPayCalculator-0.0.1-SNAPSHOT.jar /app/VacationPayCalculator.jar
WORKDIR /app
CMD ["java", "-jar", "VacationPayCalculator.jar"]