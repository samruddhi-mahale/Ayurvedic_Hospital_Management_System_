# Ayurveda Backend — Setup & Run

## 1. Prerequisites
- JDK 17+
- Maven (or use the IDE's bundled Maven)
- MySQL Server running locally
- IDE: IntelliJ IDEA / Eclipse / VS Code with Java extensions

## 2. Database
Run `ayurveda_clinic_schema.sql` in MySQL Workbench first. It creates the
`ayurveda_clinic` database, all 9 tables, and seeds the master tables
(treatment types, examination parameters, room types).

## 3. Configure connection
Open `src/main/resources/application.properties` and update:
```
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

## 4. Import into your IDE
- IntelliJ: File → Open → select the `ayurveda-backend` folder (pom.xml is
  detected automatically as a Maven project)
- Eclipse: File → Import → Maven → Existing Maven Projects → select the folder
- VS Code: just open the folder with the Java Extension Pack installed

## 5. Run
```
mvn spring-boot:run
```
or run `AyurvedaApplication.java` directly from your IDE.

The API starts on `http://localhost:8080`.

## 6. Test endpoints (Postman / curl)
- `GET  /api/patients` — list patients
- `POST /api/patients` — create a patient
- `GET  /api/doctors`, `/api/treatment-types`, `/api/examination-parameters`, `/api/room-types`
- `POST /api/visits` — create a case paper (patientId, doctorId, visitDate, examinations[])
- `POST /api/bills` — create a billing slip (patientId, visitId, roomTypeId, billDate, foodCharge, items[])
  — `totalAmount` is calculated server-side from the items + food charge, not sent by the client.

Example `POST /api/visits` body:
```json
{
  "patientId": 1,
  "doctorId": 1,
  "visitDate": "2026-09-05",
  "examinations": [
    { "parameterId": 1, "symptoms": "Irregular", "treatmentNotes": "Monitor" },
    { "parameterId": 7, "symptoms": "140/90", "treatmentNotes": "Reduce salt" }
  ]
}
```

Example `POST /api/bills` body:
```json
{
  "patientId": 1,
  "visitId": 1,
  "roomTypeId": 2,
  "billDate": "2026-09-05",
  "foodCharge": 300,
  "items": [
    { "treatmentTypeId": 1, "days": 7, "amount": 3500 },
    { "treatmentTypeId": 9, "days": 5, "amount": 2500 }
  ]
}
```

## 7. Next steps
- Add the frontend (React or plain HTML/JS) that calls these REST endpoints —
  never connect the frontend directly to MySQL.
- Add DTOs for response shaping if you don't want to expose JPA entities directly
  (fine for the assessment, but worth mentioning if asked).
- Once change requests arrive, add fields/endpoints incrementally — the schema
  is normalized so most additions will be new columns or new small tables.
