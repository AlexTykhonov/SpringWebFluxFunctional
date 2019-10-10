package com.javasampleapproach.webflux.functional.router;

import com.javasampleapproach.webflux.functional.handler.CustomerHandler;
import com.javasampleapproach.webflux.functional.handler.JobHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class JobRoutConfig {
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(JobHandler jobHandler) {
        return route(GET("/api/job").and(accept(MediaType.APPLICATION_JSON)), jobHandler::getAll)
                .andRoute(GET("/api/job/{id}").and(accept(MediaType.APPLICATION_JSON)), jobHandler::getJob)
                .andRoute(POST("/api/job/post").and(accept(MediaType.APPLICATION_JSON)), jobHandler::postJob)
                .andRoute(PUT("/api/job/put/{id}").and(accept(MediaType.APPLICATION_JSON)), jobHandler::putJob)
                .andRoute(DELETE("/api/job/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), jobHandler::deleteJob);
    }
}
