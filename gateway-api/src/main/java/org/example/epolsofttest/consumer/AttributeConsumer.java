package org.example.epolsofttest.consumer;

import org.example.epolsofttest.*;
import org.example.epolsofttest.dto.AttributeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.ArrayList;
import java.util.List;

public class AttributeConsumer extends WebServiceGatewaySupport {

    @Value("${soap.attributes.uri}")
    private String ATTRIBUTES_URL;

    public AttributeDto getAttributeByNameResponse(String name) {
        AttributeDto attributeDto = new AttributeDto();

        GetAttributeByNameRequest request = new GetAttributeByNameRequest();
        request.setName(name);
        GetAttributeByNameResponse response = (GetAttributeByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ATTRIBUTES_URL, request);

        BeanUtils.copyProperties(response.getAttributeType(), attributeDto);

        return attributeDto;
    }

    public List<AttributeDto> getAllAttributes() {
        List<AttributeDto> attributeDtoList = new ArrayList<>();

        GetAllAttributesRequest request = new GetAllAttributesRequest();
        GetAllAttributesResponse getAllAttributesResponse = (GetAllAttributesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ATTRIBUTES_URL, request);

        List<AttributeType> attributeTypeList = getAllAttributesResponse.getFindAll();
        attributeTypeList
                .forEach(attributeType -> {
                    AttributeDto attributeDto = new AttributeDto();
                    BeanUtils.copyProperties(attributeType, attributeDto);
                    attributeDtoList.add(attributeDto);
                });

        return attributeDtoList;

    }


    public ServiceStatus deleteAttribute(String name) {
        GetDeleteAttributeRequest request = new GetDeleteAttributeRequest();
        request.setName(name);

        GetDeleteAttributeResponse getDeleteAttributeResponse =
                (GetDeleteAttributeResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(ATTRIBUTES_URL, request);
        return getDeleteAttributeResponse.getServiceStatus();
    }

    public ServiceStatus updateAttribute(AttributeDto attribute) {
        AttributeType attributeType = new AttributeType();
        BeanUtils.copyProperties(attribute, attributeType);

        GetUpdateAttributeRequest request = new GetUpdateAttributeRequest();
        request.setAttributeType(attributeType);

        GetUpdateAttributeResponse response = (GetUpdateAttributeResponse)
                getWebServiceTemplate()
                    .marshalSendAndReceive(ATTRIBUTES_URL, request);

        return response.getServiceStatus();
    }

    public ServiceStatus saveAttribute(AttributeDto attribute) {
        AttributeType attributeType = new AttributeType();
        BeanUtils.copyProperties(attribute, attributeType);

        GetAddAttributeRequest request = new GetAddAttributeRequest();
        request.setAttributeType(attributeType);

        GetAddAttributeResponse response =
                (GetAddAttributeResponse) getWebServiceTemplate()
                        .marshalSendAndReceive(ATTRIBUTES_URL, request);

        return response.getServiceStatus();
    }
}
