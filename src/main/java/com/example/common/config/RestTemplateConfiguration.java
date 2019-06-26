package com.example.common.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfiguration {

    /**
     * Spring RestTemplate bean : 여러곳에서 공용으로 사용할 목적
     *
     * @return
     */
    @Bean(name = "commonRestTemplate")
    protected RestTemplate commonRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        HttpComponentsClientHttpRequestFactory crf = new HttpComponentsClientHttpRequestFactory();

        crf.setReadTimeout(7000); //읽기시간초과 타임아웃
        crf.setConnectTimeout(5000); //연결시간초과 타임아웃

        // @formatter:off
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(300) //커넥션풀적용(최대 오픈되는 커넥션 수)
                .setMaxConnPerRoute(20) //커넥션풀적용(IP:포트 1쌍에 대해 수행 할 연결 수제한)
                .evictIdleConnections(2000L, TimeUnit.MILLISECONDS) //서버에서 keepalive시간동안 미 사용한 커넥션을 죽이는 등의 케이스 방어로 idle커넥션을 주기적으로 지움
                .build();

        crf.setHttpClient(httpClient);
        // @formatter:on

        restTemplate.setRequestFactory(crf);

        return restTemplate;
    }
}
