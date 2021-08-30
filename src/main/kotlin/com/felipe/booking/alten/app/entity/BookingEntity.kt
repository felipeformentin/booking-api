package com.felipe.booking.alten.app.entity

import com.felipe.booking.alten.domain.model.Booking
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "booking")
data class BookingEntity(
    @Id
    val id: String?,
    val checkInDate: LocalDateTime,
    val checkOutDate: LocalDateTime,
    val creationDate: LocalDateTime,
    val updateDate: LocalDateTime,
) {

    companion object {
        fun of(booking: Booking) =
            BookingEntity(
                id = null,
                checkInDate = booking.checkInDate,
                checkOutDate = booking.checkOutDate,
                creationDate = LocalDateTime.now(),
                updateDate = LocalDateTime.now()
            )
    }

    fun toDomain() =
        Booking(
            checkInDate = checkInDate,
            checkOutDate = checkOutDate
        )
}