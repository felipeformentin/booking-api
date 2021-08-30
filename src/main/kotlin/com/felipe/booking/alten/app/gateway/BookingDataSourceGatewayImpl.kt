package com.felipe.booking.alten.app.gateway

import com.felipe.booking.alten.app.entity.BookingEntity
import com.felipe.booking.alten.app.entity.BookingLockEntity
import com.felipe.booking.alten.app.gateway.database.BookingLockRepository
import com.felipe.booking.alten.app.gateway.database.BookingRepository
import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import com.felipe.booking.alten.domain.model.BookingLock
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import reactor.kotlin.core.publisher.switchIfEmpty

@Component
class BookingDataSourceGatewayImpl(
    private val bookingRepository: BookingRepository,
    private val bookingLockRepository: BookingLockRepository
): BookingDataSourceGateway {

    companion object {
        const val ROOM_ID = "FIXED_ROOM_ID"
    }

    override fun createBooking(lock: BookingLock) =
        bookingRepository.save(
            BookingEntity(
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
            )
        ).map { it.toDomain() }

    override fun getLock(): Mono<BookingLock> =
        bookingLockRepository.findById(ROOM_ID)
            .switchIfEmpty { bookingLockRepository.save(BookingLockEntity(roomId = ROOM_ID, null)) }
            .map { it.toDomain() }
}