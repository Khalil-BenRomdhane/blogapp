package com.example.Gatewayservice.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRoutes {
        @Bean
        public RouteLocator configureRoute(RouteLocatorBuilder builder) {
            return builder.routes()
                    .route("post-service", r->r.path("/api/posts/**").uri("lb://POST-SERVICE")) //dynamic routing
                    .route("comment-service",r->r.path("/api/comments/**").uri("lb://COMMENT-SERVICE"))
                    .route("vote-service",r->r.path("/api/posts/**").uri("lb://VOTE-SERVICE"))
                    .build();
        }
    }
