package com.felipe.booking.app.config.routing;

import com.felipe.booking.app.dto.ValidationErrorDTO;
import com.felipe.booking.app.handler.BookingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class ServerRouting {

    @Autowired
    private BookingHandler bookingHandler;

    @Bean
    public void bookingRoutes() {
        route(POST("/room/{id}/booking"), bookingHandler::createBooking)
        .andRoute(GET("/availability"), bookingHandler::getRoomAvailability)
        .filter(badRequestHandler());
    }

    private HandlerFilterFunction<ServerResponse, ServerResponse> badRequestHandler() {
        return (request, next) -> next.handle(request)
                .onErrorResume(ResponseStatusException.class,
                        e -> ServerResponse.badRequest().bodyValue(new ValidationErrorDTO(e.getMessage())));
    }
}
