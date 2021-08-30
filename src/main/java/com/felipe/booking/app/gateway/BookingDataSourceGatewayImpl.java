package com.felipe.booking.app.gateway;

import com.felipe.booking.app.entity.BookingEntity;
import com.felipe.booking.app.entity.BookingLockEntity;
import com.felipe.booking.app.gateway.database.BookingLockRepository;
import com.felipe.booking.app.gateway.database.BookingRepository;
import com.felipe.booking.domain.gateway.BookingDataSourceGateway;
import com.felipe.booking.domain.model.Booking;
import com.felipe.booking.domain.model.BookingLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BookingDataSourceGatewayImpl implements BookingDataSourceGateway {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingLockRepository bookingLockRepository;

    private static final String ROOM_ID = "FIXED_ROOM_ID";

    @Override
    public Mono<BookingLock> getLock() {
        return bookingLockRepository
                .findById(ROOM_ID)
                .switchIfEmpty( Mono.defer(() ->
                        bookingLockRepository.save(new BookingLockEntity(ROOM_ID, null))))
                .map ( lock -> lock.toDomain() );
    }

    @Override
    public Mono<List<Booking>> findAllBookings() {
        return bookingRepository
                .findAll()
                .map ( bookingEntity -> bookingEntity.toDomain() )
                .collectList();
    }

    @Override
    public Mono<Booking> createBooking(Booking booking, BookingLock lock) {
        return bookingRepository
                .save(BookingEntity.of(booking))
                .map ( bookingEntity ->  bookingEntity.toDomain() );
    }
}
