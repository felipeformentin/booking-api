package com.felipe.booking.domain.usecase;

import com.felipe.booking.domain.model.Booking;
import com.felipe.booking.domain.validations.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class ValidateBookingUseCase {

    @Autowired
    private Set<BookingValidator> validators;

    public Mono<Booking> validate(Booking booking) {
        return Mono.just(booking)
                .flatMap(book -> executeValidations(booking));
    }

    private Mono<Booking> executeValidations(Booking booking) {
     return Flux.fromIterable(validators)
                .flatMap ( validator -> validator.validate(booking) )
                .collectList();
    }
}
