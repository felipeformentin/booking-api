**ALTEN BOOKING API PROJECT**

This project contains APIs responsible for booking rooms for a hotel.

**HOW TO RUN**

Run MongoDB:

$docker run -p 27017:27017 --name mongodb mongo


**APIS**

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

**VALIDATIONS**

- BookDayAfterValidator -> Check if checkIn date is 1 day after the current one.

- DaysInAdvanceValidator -> Validates if checkOut is not more than 30 days in advance. 

- IdExistsValidator -> When updating, check if entity really exists.

- IsAlreadyBookedValidator -> Compare already booked dates to see if current Booking Entity is allowed.

- IsCheckOutAfterCheckInValidator -> Validates if checkOut is after checkIn date.

- ReservationPeriodValidator -> Guarantees that the booking won't be longer than 3 days.