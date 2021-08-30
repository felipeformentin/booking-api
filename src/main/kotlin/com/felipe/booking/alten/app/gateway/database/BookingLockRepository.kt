package com.felipe.booking.alten.app.gateway.database

import com.felipe.booking.alten.app.entity.BookingLockEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface BookingLockRepository: ReactiveCrudRepository<BookingLockEntity, String>