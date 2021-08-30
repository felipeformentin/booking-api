package com.felipe.booking.app.config.routing;

import com.felipe.booking.app.dto.response.ValidationErrorDTO;
import com.felipe.booking.app.handler.BookingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
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
    public RouterFunction<ServerResponse> bookingRoutes() {
        return route(POST("/room/{id}/booking"), bookingHandler::saveBooking)
        .andRoute(GET("/room/{id}/availability"), bookingHandler::getRoomAvailability)
        .filter(badRequestHandler());
    }

    private HandlerFilterFunction<ServerResponse, ServerResponse> badRequestHandler() {
        return (request, next) -> next.handle(request)
                .onErrorResume(OptimisticLockingFailureException.class,
                        e -> ServerResponse.badRequest().bodyValue(new ValidationErrorDTO("Someone was booking the room at the same time. Please try again later.")))
                .onErrorResume(ResponseStatusException.class,
                        e -> ServerResponse.badRequest().bodyValue(new ValidationErrorDTO(e.getReason())));
    }
}
