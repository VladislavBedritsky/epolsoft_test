package org.example.epolsofttest.client;

import org.example.epolsofttest.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;

public class AttributeClient extends WebServiceGatewaySupport {

    private static final String ATTRIBUTES_URL = "http://localhost:8088/ws/attributes";

    public AttributeDTO getAttributeByNameResponse(String name) {
        GetAttributeByNameRequest request = new GetAttributeByNameRequest();
        request.setName(name);

        GetAttributeByNameResponse response = (GetAttributeByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ATTRIBUTES_URL, request);
        return response.getAttributeDTO();
    }

    public List<AttributeDTO> getAllAttributes() {
        GetAllAttributesRequest request = new GetAllAttributesRequest();
        GetAllAttributesResponse getAllAttributesResponse = (GetAllAttributesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ATTRIBUTES_URL, request);
        return getAllAttributesResponse.getFindAll();

    }


    public ServiceStatus deleteAttribute(String name) {
        GetDeleteAttributeRequest request = new GetDeleteAttributeRequest();
        request.setName(name);

        GetDeleteAttributeResponse getDeleteAttributeResponse =
                (GetDeleteAttributeResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(ATTRIBUTES_URL, request);
        return getDeleteAttributeResponse.getServiceStatus();
    }

    public ServiceStatus updateAttribute(AttributeDTO attribute) {
        GetUpdateAttributeRequest request = new GetUpdateAttributeRequest();
        request.setAttributeDTO(attribute);

        GetUpdateAttributeResponse response = (GetUpdateAttributeResponse)
                getWebServiceTemplate()
                    .marshalSendAndReceive(ATTRIBUTES_URL, request);

        return response.getServiceStatus();
    }

    public ServiceStatus saveAttribute(AttributeDTO attribute) {
        GetAddAttributeRequest request = new GetAddAttributeRequest();
        request.setAttributeDTO(attribute);

        GetAddAttributeResponse response =
                (GetAddAttributeResponse) getWebServiceTemplate()
                        .marshalSendAndReceive(ATTRIBUTES_URL, request);

        return response.getServiceStatus();
    }
}
