<div align="center">

# ALTEN BOOKING API PROJECT

</div>

This project contains APIs responsible for booking rooms for a hotel.

### HOW TO RUN

Run with docker-compose:

`$docker-compose up`

Postman Collection available on root folder:
**"Alten Booking.postman_collection.json"**

### CORE TECHNOLOGIES

- Java 11
- Spring Webflux 
- MongoDB 
- VueJS
- NPM
- VCalendar

### APIS

- **GET /booking/** -> Returns All Booking Entities with Status 200
  or Status 200 with empty list when we have no entities.

- **GET /booking/{bookingId}** -> Returns Booking Entity with Status 200
or Status 404 when ID does not exist.

- **POST /booking** -> Creates a new Booking Entity if all validations
and optimistic lock happens with success with Status 201.

- **PUT /booking/{id}** -> Updates Booking Entity with Status 202
or Status 404 when ID does not exist.

- **DELETE /booking/{id}** -> Returns Status 204 if Entity was deleted
or does not exist.

- **GET /availability** -> Returns two lists, one containing the dates available,
and the other containing already booked dates.

### VALIDATIONS

- BookDayAfterValidator -> Check if checkIn date is 1 day after the current one.

- DaysInAdvanceValidator -> Validates if checkOut is not more than 30 days in advance. 

- IdExistsValidator -> When updating, check if entity really exists.

- IsAlreadyBookedValidator -> Compare already booked dates to see if current Booking Entity is allowed.

- IsCheckOutAfterCheckInValidator -> Validates if checkOut is after checkIn date.

- ReservationPeriodValidator -> Guarantees that the booking won't be longer than 3 days.

### FUTURE IMPROVEMENTS

- Circuit break could be implemented so client doesn't get stuck when DB is down

- In order to have more uptime we could add a cache layer. This would result in better performance and if by some reason our DB is down we could have cache as fallback and the other way around is also true.

- Implement PATCH Api so we can partially update our entity

- Validation on Request Fields

- Automatic Retry when receiving OptmisticLocking Exceptions

- Add unit tests

- Generate metrics with gatling

- Add logs

- Add swagger

### COMMENTS

- Why I didn???t use @Transaction ? -> Since I create a new Booking Entity, Transactional
wouldn't help me lock the dates, so I decided to implement Optimistic Lock