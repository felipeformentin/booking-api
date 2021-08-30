package com.felipe.booking.app.entity;

import com.felipe.booking.domain.model.BookingLock;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("booking_lock")
public class BookingLockEntity {
    @Id
    private String roomId;
    @Version
    private Long version;

    public BookingLock toDomain() {
        return new BookingLock(roomId, version);
    }
}
