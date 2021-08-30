package com.felipe.booking.domain.usecase;

import com.felipe.booking.domain.gateway.BookingDataSourceGateway;
import com.felipe.booking.domain.model.Booking;
import com.felipe.booking.domain.model.RoomAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class GetRoomAvailabilityUseCase {

    @Autowired
    private BookingDataSourceGateway bookingRepositoryGateway;

    public Mono<RoomAvailability> execute() {
        return bookingRepositoryGateway
            .findAllBookings()
            .map ( bookings -> getBookedDays(bookings) )
            .map ( bookedDays -> getRoomAvailability(bookedDays) );
    }

    private Set<LocalDate> getBookedDays(List<Booking> bookings) {
        Set<LocalDate> bookedDays = new HashSet<>();
        bookings.forEach (
            booking -> {
                LocalDate checkIn = booking.getCheckInDate().toLocalDate();
                LocalDate checkOut = booking.getCheckOutDate().toLocalDate();
                Long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);
                if (daysBetween == 2L) {
                    bookedDays.add(checkIn.plusDays(1));
                }
                bookedDays.add(checkIn);
                bookedDays.add(checkOut);
            }
        );
        return bookedDays;
    }

    private RoomAvailability getRoomAvailability(Set<LocalDate> bookedDays) {
        List<LocalDate> daysAvailable = new ArrayList<>();
        List<LocalDate> daysBooked = new ArrayList<>();
        List<LocalDate> possibleDays = get30DaysFromNow();
        possibleDays.forEach ( possibleDay -> {
                if (bookedDays.contains(possibleDay)) {
                    daysBooked.add(possibleDay);
                } else {
                    daysAvailable.add(possibleDay);
                }
            }
        );
        return new RoomAvailability(daysAvailable, daysBooked);
    }

    private List<LocalDate> get30DaysFromNow() {
        List<LocalDate> possibleDays = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (int i = 1; i <= 30; i++) {
            possibleDays.add(currentDate.plusDays(i));
        }
        return possibleDays;
    }
}
