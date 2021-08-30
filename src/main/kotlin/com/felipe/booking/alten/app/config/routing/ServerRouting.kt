package com.felipe.booking.alten.app.config.routing

import com.felipe.booking.alten.app.dto.ValidationErrorDTO
import com.felipe.booking.alten.app.handler.BookingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Configuration
class ServerRouting(
    private val bookingHandler: BookingHandler
) {

    @Bean
    fun bookingRoutes() =
        router {
            ("/room/{id}/booking" and accept(MediaType.APPLICATION_JSON)).nest {
                POST(bookingHandler::createBooking)
            }
            onError<ResponseStatusException> { exception, serverRequest -> handleResponseStatusException(exception, serverRequest) }
        }

    private fun handleResponseStatusException(exception: Throwable, serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.badRequest().bodyValue(
            ValidationErrorDTO(message = (exception as ResponseStatusException).reason?: "Unknow Error" ))
    }
}