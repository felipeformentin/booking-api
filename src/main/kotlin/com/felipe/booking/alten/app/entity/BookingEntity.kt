package com.felipe.booking.alten.app.entity

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
)