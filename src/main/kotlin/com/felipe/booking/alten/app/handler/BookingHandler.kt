package com.felipe.booking.alten.app.handler

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
        createBookingUseCase.execute()
            .flatMap { ServerResponse.ok().bodyValue("Ok") }

}