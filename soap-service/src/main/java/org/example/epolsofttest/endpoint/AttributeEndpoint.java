package org.example.epolsofttest.endpoint;

import org.example.epolsofttest.*;
import org.example.epolsofttest.domain.Attribute;
import org.example.epolsofttest.service.AttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class AttributeEndpoint {

    public static final String NAMESPACE_URI = "http://example.org/xsd/attribute";

    @Autowired
    private AttributeService attributeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAttributeByNameRequest")
    @ResponsePayload
    public GetAttributeByNameResponse getAttributeByName(@RequestPayload GetAttributeByNameRequest request) {
        GetAttributeByNameResponse response = new GetAttributeByNameResponse();
        Attribute attribute = attributeService.getByName(request.getName());
        AttributeType attributeType = new AttributeType();

        BeanUtils.copyProperties(attribute, attributeType);
        response.setAttributeType(attributeType);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAttributesRequest")
    @ResponsePayload
    public GetAllAttributesResponse getAllAttributes(@RequestPayload GetAllAttributesRequest request) {
        GetAllAttributesResponse response = new GetAllAttributesResponse();
        List<AttributeType> listWithAttributeType = new ArrayList<>();
        List<Attribute> listWithAttributes = attributeService.findAll();

        listWithAttributes
                .forEach(attribute -> {
                    AttributeType attributeType = new AttributeType();
                    BeanUtils.copyProperties(attribute, attributeType);
                    listWithAttributeType.add(attributeType);
                });

        response.getFindAll().addAll(listWithAttributeType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAddAttributeRequest")
    @ResponsePayload
    public GetAddAttributeResponse addAttribute(@RequestPayload GetAddAttributeRequest request) {
        GetAddAttributeResponse response = new GetAddAttributeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Attribute newAttribute = new Attribute();
        BeanUtils.copyProperties(request, newAttribute);

        Attribute savedAttribute = attributeService.save(newAttribute);

        if (savedAttribute == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {

            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUpdateAttributeRequest")
    @ResponsePayload
    public GetUpdateAttributeResponse updateAttribute(@RequestPayload GetUpdateAttributeRequest request) {
        GetUpdateAttributeResponse response = new GetUpdateAttributeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        // 1. Find if attribute available
        Attribute attributeFromDB = attributeService.getByName(
                request.getAttributeType().getName()
        );

        if(attributeFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Attribute = " + request.getAttributeType().getName() + " not found");
        }else {

            // 2. Get updated attribute information from the request
            attributeFromDB.setName(request.getAttributeType().getName());
            attributeFromDB.setValue(request.getAttributeType().getValue());
            attributeFromDB.setDescription(request.getAttributeType().getDescription());

            // 3. update the attribute in database
            boolean isAttributeSaved = attributeService.update(attributeFromDB);

            if(!isAttributeSaved) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Attribute=" +
                        request.getAttributeType().getName());
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }

        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDeleteAttributeRequest")
    @ResponsePayload
    public GetDeleteAttributeResponse deleteAttribute(@RequestPayload GetDeleteAttributeRequest request) {
        GetDeleteAttributeResponse response = new GetDeleteAttributeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean isAttributeDeleted = attributeService
                                            .deleteByName(request.getName());

        if (!isAttributeDeleted) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting Attribute with name =" + request.getName());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }



}
