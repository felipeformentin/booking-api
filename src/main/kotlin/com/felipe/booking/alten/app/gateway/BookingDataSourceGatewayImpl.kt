package com.felipe.booking.alten.app.gateway

import com.felipe.booking.alten.app.entity.BookingEntity
import com.felipe.booking.alten.app.gateway.database.BookingRepository
import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BookingDataSourceGatewayImpl(
    private val bookingRepository: BookingRepository
): BookingDataSourceGateway {

    override fun createBooking() =
        bookingRepository.save(
            BookingEntity(
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
            )
        )
}