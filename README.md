Это учебный проект по расчёту туристической страховки для путешествий. 
Проект принимает json запросы на адрес http://localhost:8080/insurance/travel/api/v1/
в формате:
  {
  "personFirstName" : "Vasja",
  "personLastName" : "Pupkin",
  "agreementDateFrom" : "2027-05-22",
  "agreementDateTo" : "2027-05-29",
  "selectedRisks":["TRAVEL_MEDICAL","TRAVEL_LOSS_BAGGAGE"],
  "country":"jaPAN",
  "birthDate" : "2019-05-29",
  "medicalRiskLimitLevel" : "LEVEL_15000"
}

и на адрес http://localhost:8080/insurance/travel/api/v2/
в формате:
  {
  "agreementDateFrom" : "2025-05-25",
  "agreementDateTo" : "2025-05-29",
  "country" : "japan1",
  "selectedRisks":["TRAVEL_MEDICAL"],
  "persons" : [
        {
          "personFirstName" : "Vasja",
          "personLastName" : "Pupkin",
          "medicalRiskLimitLevel" : "1LEVEL_10000",
          "birthDate" : "2000-05-29"
        },
        {
          "personFirstName" : "Petja",
          "personLastName" : "Pupkin",
          "medicalRiskLimitLevel" : "LEVEL_20000",
          "birthDate" : "1950-02-29"
        }
  ]
}

а так же реализован WEB UI http://localhost:8080/insurance/travel/web/v1/

В проекте используются технологии:
VCS, Git, GitHub
Build Tool Gradle
Spring Framework, Spring Boot
Design patterns: IoC, Builder, DTO, Factory
REST, WEB MVC
SQL, MySQL, H2, JDBC, ORM, JPA, Hibernate, Liquibase
JUnit, Mockito
Lombok
Logging, AOP

Проект не закончен и продолжает дополняться.
