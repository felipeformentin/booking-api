package com.felipe.booking.app.entity;

import com.felipe.booking.domain.model.BookingLock;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("booking_lock")
@NoArgsConstructor
@AllArgsConstructor
public class BookingLockEntity {
    @Id
    private String roomId;
    @Version
    private Long version;

    public static BookingLockEntity of(BookingLock lock) { return new BookingLockEntity(lock.getRoomId(), lock.getVersion()); }
    public BookingLock toDomain() {
        return new BookingLock(roomId, version);
    }
}
