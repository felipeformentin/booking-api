package com.felipe.booking.alten.domain.usecase

import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import com.felipe.booking.alten.domain.model.Booking
import com.felipe.booking.alten.domain.model.RoomAvailability
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Component
class GetRoomAvailabilityUseCase(
    private val bookingRepositoryGateway: BookingDataSourceGateway
) {

    fun execute(): Mono<RoomAvailability> =
        bookingRepositoryGateway
            .findAllBookings()
            .map { bookings -> getBookedDays(bookings) }
            .map { bookedDays -> getRoomAvailability(bookedDays)}

    private fun getBookedDays(bookings: List<Booking>): Set<LocalDate> {
        val bookedDays = mutableSetOf<LocalDate>()
        bookings.forEach { booking ->
            val checkIn = booking.checkInDate.toLocalDate()
            val checkOut = booking.checkOutDate.toLocalDate()

            val daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut)
            if (daysBetween == 2L) {
                bookedDays.add(checkIn.plusDays(1))
            }

            bookedDays.add(checkIn)
            bookedDays.add(checkOut)
        }
        return bookedDays
    }

    private fun getRoomAvailability(bookedDays: Set<LocalDate>): RoomAvailability {
        val daysAvailable = mutableListOf<LocalDate>()
        val daysBooked = mutableListOf<LocalDate>()
        val possibleDays = get30DaysFromNow()

        possibleDays.forEach { possibleDay ->
            if (bookedDays.contains(possibleDay)) {
                daysBooked.add(possibleDay)
            } else {
                daysAvailable.add(possibleDay)
            }
        }
        return RoomAvailability(daysAvailable, daysBooked)
    }

    private fun get30DaysFromNow(): List<LocalDate> {
        val possibleDays = mutableListOf<LocalDate>()
        val currentDate = LocalDate.now()

        for (i in 1..30) {
            possibleDays.add(currentDate.plusDays(i.toLong()))
        }

        return possibleDays
    }
}