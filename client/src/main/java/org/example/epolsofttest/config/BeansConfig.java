package org.example.epolsofttest.config;

import org.example.epolsofttest.client.AttributeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BeansConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.example.epolsofttest");
        return marshaller;
    }

    @Bean
    public AttributeClient countryClient(Jaxb2Marshaller marshaller) {
        AttributeClient client = new AttributeClient();
        client.setDefaultUri("http://localhost:8088/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
