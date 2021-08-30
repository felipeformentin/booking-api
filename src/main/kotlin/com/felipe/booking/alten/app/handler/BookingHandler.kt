package com.felipe.booking.alten.app.handler

import com.felipe.booking.alten.app.dto.BookingDTO
import com.felipe.booking.alten.domain.usecase.CreateBookingUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BookingHandler(
    private val createBookingUseCase: CreateBookingUseCase
) {

    fun createBooking(serverRequest: ServerRequest): Mono<ServerResponse> =
        serverRequest
            .bodyToMono(BookingDTO::class.java)
            .map { it.toDomain() }
            .flatMap { createBookingUseCase.execute(it) }
            .map { BookingDTO.of(it) }
            .flatMap { ServerResponse.ok().bodyValue(it) }
}