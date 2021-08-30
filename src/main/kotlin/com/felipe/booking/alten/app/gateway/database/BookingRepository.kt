package com.felipe.booking.alten.app.gateway.database

import com.felipe.booking.alten.app.entity.BookingEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface BookingRepository: ReactiveCrudRepository<BookingEntity, String>