package org.example.epolsofttest.config;

import org.example.epolsofttest.client.AttributeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BeansConfig {

    @Value("${jaxb.context.path}")
    private String JAXB_CONTEXT_PATH;
    @Value("${soap.server.uri}")
    private String SOAP_SERVER_URI;


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(JAXB_CONTEXT_PATH);
        return marshaller;
    }

    @Bean
    public AttributeClient countryClient(Jaxb2Marshaller marshaller) {
        AttributeClient client = new AttributeClient();
        client.setDefaultUri(SOAP_SERVER_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
