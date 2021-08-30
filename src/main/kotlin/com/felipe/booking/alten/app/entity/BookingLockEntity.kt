package com.felipe.booking.alten.app.entity

import com.felipe.booking.alten.domain.model.BookingLock
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document

@Document("booking_lock")
data class BookingLockEntity(
    @Id val roomId: String,
    @Version val version: Long?
) {
    fun toDomain() =
        BookingLock(
            roomId = roomId,
            version = version!!
        )
}