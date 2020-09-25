package org.example.epolsofttest.consumer;

import org.example.epolsofttest.*;
import org.example.epolsofttest.dto.AttributeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.ArrayList;
import java.util.List;

/**
 * AttributeConsumer is a child of org.springframework.ws.client.core.support.WebServiceGatewaySupport.
 * This class is a web service client that does the actual SOAP exchange
 * particularly with attributes data.
 *
 * @version 1.01 25 Sep 2020
 * @author Uladzislau Biadrytski
 */
public class AttributeConsumer extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeConsumer.class);

    @Value("${soap.attributes.uri}")
    private String ATTRIBUTES_URL;

    /**
     * Get attribute by name from soap service.
     *
     * @param name attributes specific name
     * @return org.example.epolsofttest.dto.AttributeDto
     */
    public AttributeDto getAttributeByNameResponse(String name) {
        LOGGER.info("Send request 'GetAttributeByNameRequest' to soap service");

        AttributeDto attributeDto = new AttributeDto();

        GetAttributeByNameRequest request = new GetAttributeByNameRequest();
        request.setName(name);
        GetAttributeByNameResponse response = (GetAttributeByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ATTRIBUTES_URL, request);

        BeanUtils.copyProperties(response.getAttributeType(), attributeDto);

        return attributeDto;
    }

    /**
     * Get all attributes from soap service.
     *
     * @return list of org.example.epolsofttest.dto.AttributeDto
     */
    public List<AttributeDto> getAllAttributes() {
        LOGGER.info("Send request 'GetAllAttributesRequest' to soap service");

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

    /**
     * Send request to soap service to delete attribute and
     * get service status as response.
     *
     * @param name attributes specific name
     * @return org.example.epolsofttest.ServiceStatus
     */
    public ServiceStatus deleteAttribute(String name) {
        LOGGER.info("Send request 'GetDeleteAttributeRequest' to soap service");

        GetDeleteAttributeRequest request = new GetDeleteAttributeRequest();
        request.setName(name);

        GetDeleteAttributeResponse getDeleteAttributeResponse =
                (GetDeleteAttributeResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(ATTRIBUTES_URL, request);
        return getDeleteAttributeResponse.getServiceStatus();
    }

    /**
     * Send request to soap service to update attribute and
     * get service status as response.
     *
     * @param attribute org.example.epolsofttest.dto.AttributeDto
     * @return org.example.epolsofttest.ServiceStatus
     */
    public ServiceStatus updateAttribute(AttributeDto attribute) {
        LOGGER.info("Send request 'GetUpdateAttributeRequest' to soap service");

        AttributeType attributeType = new AttributeType();
        BeanUtils.copyProperties(attribute, attributeType);

        GetUpdateAttributeRequest request = new GetUpdateAttributeRequest();
        request.setAttributeType(attributeType);

        GetUpdateAttributeResponse response = (GetUpdateAttributeResponse)
                getWebServiceTemplate()
                    .marshalSendAndReceive(ATTRIBUTES_URL, request);

        return response.getServiceStatus();
    }

    /**
     * Send request to soap service to save attribute and
     * get service status as response.
     *
     * @param attribute org.example.epolsofttest.dto.AttributeDto
     * @return org.example.epolsofttest.ServiceStatus
     */
    public ServiceStatus saveAttribute(AttributeDto attribute) {
        LOGGER.info("Send request 'GetAddAttributeRequest' to soap service");

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
