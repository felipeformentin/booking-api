package com.felipe.booking.app.gateway.database;

import com.felipe.booking.app.entity.BookingLockEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookingLockRepository extends ReactiveCrudRepository<BookingLockEntity, String> { }
