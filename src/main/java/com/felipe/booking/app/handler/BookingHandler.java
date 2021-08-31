package com.felipe.booking.app.handler;

import com.felipe.booking.app.dto.request.BookingRequestDTO;
import com.felipe.booking.app.dto.response.BookingResponseDTO;
import com.felipe.booking.app.dto.response.RoomAvailabilityDTO;
import com.felipe.booking.domain.usecase.DeleteBookingUseCase;
import com.felipe.booking.domain.usecase.FindBookingUseCase;
import com.felipe.booking.domain.usecase.SaveBookingUseCase;
import com.felipe.booking.domain.usecase.GetRoomAvailabilityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class BookingHandler {

    @Autowired
    private SaveBookingUseCase saveBookingUseCase;

    @Autowired
    private DeleteBookingUseCase deleteBookingUseCase;

    @Autowired
    private FindBookingUseCase findBookingUseCase;

    @Autowired
    private GetRoomAvailabilityUseCase getRoomAvailabilityUseCase;

    public Mono<ServerResponse> createBooking(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(BookingRequestDTO.class)
                .map(it -> it.toDomain())
                .flatMap(it -> saveBookingUseCase.execute(it))
                .flatMap(it -> ServerResponse.created(URI.create(it.getId())).bodyValue(BookingResponseDTO.of(it)));
    }

    public Mono<ServerResponse> saveBooking(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(BookingRequestDTO.class)
                .map(it -> it.toDomain(serverRequest.pathVariable("bookingId")))
                .flatMap(it -> saveBookingUseCase.execute(it))
                .flatMap(it -> ServerResponse.status(HttpStatus.ACCEPTED).bodyValue(BookingResponseDTO.of(it)));
    }

    public Mono<ServerResponse> deleteBooking(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .flatMap(it -> deleteBookingUseCase.delete(serverRequest.pathVariable("bookingId")))
                .flatMap(it -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findBooking(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .flatMap(it -> findBookingUseCase.find(serverRequest.pathVariable("bookingId")))
                .flatMap(it -> ServerResponse.ok().bodyValue(BookingResponseDTO.of(it)));
    }

    public Mono<ServerResponse> getRoomAvailability(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .flatMap(it -> getRoomAvailabilityUseCase.execute())
                .flatMap(it -> ServerResponse.ok().bodyValue(RoomAvailabilityDTO.of(it)));
    }

}