package com.felipe.booking.alten.domain.model

import java.time.LocalDateTime

data class Booking(
    val checkInDate: LocalDateTime,
    val checkOutDate: LocalDateTime,
)