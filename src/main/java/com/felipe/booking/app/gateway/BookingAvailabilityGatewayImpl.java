package com.felipe.booking.app.gateway;

import com.felipe.booking.app.entity.BookingEntity;
import com.felipe.booking.app.gateway.database.BookingRepository;
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
}
