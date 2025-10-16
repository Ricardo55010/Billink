package com.example.billink.Interceptors;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GraphQlRequestHeaderInterceptor implements WebGraphQlInterceptor {

    private static final Logger log = LoggerFactory.getLogger(GraphQlRequestHeaderInterceptor.class);

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        Map<String, Object> headers = getHeadersFromRequest(request);
        log.trace("Found {} headers that will be added to the GQL-context: {}", headers.size(), headers);
        addHeadersToGraphQLContext(request, headers);
        return chain.next(request);
    }

    private Map<String, Object> getHeadersFromRequest(WebGraphQlRequest request) {
        return request.getHeaders().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().get(0) // toma el primer valor de la lista
                ));
    }

    private void addHeadersToGraphQLContext(WebGraphQlRequest request, Map<String, Object> customHeaders) {
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(customHeaders).build()
        );
    }
}


