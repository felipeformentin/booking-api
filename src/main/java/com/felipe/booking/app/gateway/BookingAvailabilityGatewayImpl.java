package com.felipe.booking.app.gateway;

import com.felipe.booking.domain.gateway.BookingAvailabilityGateway;
import com.felipe.booking.domain.gateway.BookingDataSourceGateway;
import com.felipe.booking.domain.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BookingAvailabilityGatewayImpl implements BookingAvailabilityGateway {

    @Autowired
    private BookingDataSourceGateway bookingDataSourceGateway;

    @Override
    public Mono<Set<LocalDate>> getBookedDays() {
        return bookingDataSourceGateway
                .findAllBookings()
                .map (bookings -> getBookedDays(bookings) );
    }

    @Override
    public Mono<Set<LocalDate>> getBookedDaysMinusCurrentBooking(Booking booking) {
        return getBookedDays()
                .flatMap(bookedDays -> bookingDataSourceGateway.findBooking(booking.getId())
                        .map(entity -> removeCurrentBookingFromSet(booking, bookedDays)));
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

    private Set<LocalDate> removeCurrentBookingFromSet(Booking booking, Set<LocalDate> bookedDays) {
        bookedDays.remove(booking.getCheckInDate().toLocalDate());
        bookedDays.remove(booking.getCheckOutDate().toLocalDate());
        if (ChronoUnit.DAYS.between(booking.getCheckInDate().toLocalDate(),
                booking.getCheckOutDate().toLocalDate()) == 2) {
            bookedDays.remove(booking.getCheckInDate().plusDays(1).toLocalDate());
        }
        return bookedDays;
    }
}
