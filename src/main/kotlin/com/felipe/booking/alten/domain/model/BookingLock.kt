package com.felipe.booking.alten.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document

data class BookingLock(
    @Id val roomId: String,
    @Version val version: Long
)