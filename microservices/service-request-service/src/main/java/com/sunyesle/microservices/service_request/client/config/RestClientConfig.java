package com.sunyesle.microservices.service_request.client.config;

import com.sunyesle.microservices.service_request.client.code.CodeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    @Value("${service.common.url}")
    private String commonServiceUrl;

    @Bean
    public CodeClient codeClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = getHttpServiceProxyFactory(commonServiceUrl);
        return httpServiceProxyFactory.createClient(CodeClient.class);
    }

    private HttpServiceProxyFactory getHttpServiceProxyFactory(String baseUrl) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        return HttpServiceProxyFactory.builderFor(restClientAdapter).build();
    }
}
