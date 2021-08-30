package com.felipe.booking.alten.app.config.routing

import com.felipe.booking.alten.app.handler.BookingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

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
        }
}