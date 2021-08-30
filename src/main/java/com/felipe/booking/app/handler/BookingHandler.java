package com.felipe.booking.app.handler;

import com.felipe.booking.app.dto.BookingDTO;
import com.felipe.booking.app.dto.RoomAvailabilityDTO;
import com.felipe.booking.domain.usecase.CreateBookingUseCase;
import com.felipe.booking.domain.usecase.GetRoomAvailabilityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookingHandler {

    @Autowired
    private CreateBookingUseCase createBookingUseCase;

    @Autowired
    private GetRoomAvailabilityUseCase getRoomAvailabilityUseCase;

    public Mono<ServerResponse> createBooking(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(BookingDTO.class)
                .map(it -> it.toDomain())
                .flatMap(it -> createBookingUseCase.execute(it))
                .map(it -> BookingDTO.of(it))
                .flatMap(it -> ServerResponse.ok().bodyValue(it));
    }

    public Mono<ServerResponse> getRoomAvailability(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .flatMap( it -> getRoomAvailabilityUseCase.execute())
                .map( it -> RoomAvailabilityDTO.of(it))
                .flatMap(it -> ServerResponse.ok().bodyValue(it));
    }
}