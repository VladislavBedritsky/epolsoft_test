package org.example.epolsofttest.endpoint;

import org.example.epolsofttest.*;
import org.example.epolsofttest.domain.Attribute;
import org.example.epolsofttest.service.AttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;


/**
 * Service endpoint is handling the incoming SOAP requests
 * with attributes data.
 *
 * @version 1.01 25 Sep 2020
 * @author Uladzislau Biadrytski
 */
@Endpoint
public class AttributeEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeEndpoint.class);

    public static final String NAMESPACE_URI = "http://example.org/xsd/attribute";

    @Autowired
    private AttributeService attributeService;


    /**
     * Handle request to find attribute by name.
     *
     * @param request org.example.epolsofttest.GetAttributeByNameRequest
     * @return org.example.epolsofttest.GetAttributeByNameResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAttributeByNameRequest")
    @ResponsePayload
    public GetAttributeByNameResponse getAttributeByName(@RequestPayload GetAttributeByNameRequest request) {
        LOGGER.info("Handling request = 'getAttributeByNameRequest'");

        GetAttributeByNameResponse response = new GetAttributeByNameResponse();
        Attribute attributeFromDb = attributeService.getByName(request.getName());
        AttributeType attributeType = new AttributeType();

        if(attributeFromDb != null) {
            BeanUtils.copyProperties(attributeFromDb, attributeType);
            response.setAttributeType(attributeType);
        } else {
            response.setAttributeType(new AttributeType());
        }
        return response;

    }

    /**
     * Handle request to find all attribute.
     *
     * @param request org.example.epolsofttest.GetAllAttributesRequest
     * @return org.example.epolsofttest.GetAllAttributesResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAttributesRequest")
    @ResponsePayload
    public GetAllAttributesResponse getAllAttributes(@RequestPayload GetAllAttributesRequest request) {
        LOGGER.info("Handling request = 'getAllAttributesRequest'");

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

    /**
     * Handle request to save attribute.
     *
     * @param request org.example.epolsofttest.GetAddAttributeRequest
     * @return org.example.epolsofttest.GetAddAttributeResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAddAttributeRequest")
    @ResponsePayload
    public GetAddAttributeResponse addAttribute(@RequestPayload GetAddAttributeRequest request) {
        LOGGER.info("Handling request = 'getAddAttributeRequest'");

        GetAddAttributeResponse response = new GetAddAttributeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Attribute newAttribute = new Attribute(
                request.getAttributeType().getName(),
                request.getAttributeType().getValue(),
                request.getAttributeType().getDescription()
        );

        Attribute savedAttribute = attributeService.save(newAttribute);

        if (savedAttribute == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");

            LOGGER.warn("Attribute with name = "+request.getAttributeType().getName()+
                    " hasn't been saved");
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }


    /**
     * Handle request to update attribute.
     *
     * @param request org.example.epolsofttest.GetUpdateAttributeRequest
     * @return org.example.epolsofttest.GetUpdateAttributeResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUpdateAttributeRequest")
    @ResponsePayload
    public GetUpdateAttributeResponse updateAttribute(@RequestPayload GetUpdateAttributeRequest request) {
        LOGGER.info("Handling request = 'getUpdateAttributeRequest'");

        GetUpdateAttributeResponse response = new GetUpdateAttributeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        // 1. Find if attribute available
        Attribute attributeFromDB = attributeService.getByName(
                request.getAttributeType().getName()
        );

        if(attributeFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Attribute = " + request.getAttributeType().getName() + " not found");

            LOGGER.warn("Attribute with name = "+request.getAttributeType().getName()+
                    " hasn't been found");
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

                LOGGER.warn("Attribute with name = "+request.getAttributeType().getName()+
                        " hasn't been updated");
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }

        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    /**
     * Handle request to delete attribute.
     *
     * @param request org.example.epolsofttest.GetDeleteAttributeRequest
     * @return org.example.epolsofttest.GetDeleteAttributeResponse
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDeleteAttributeRequest")
    @ResponsePayload
    public GetDeleteAttributeResponse deleteAttribute(@RequestPayload GetDeleteAttributeRequest request) {
        LOGGER.info("Handling request = 'getDeleteAttributeRequest'");

        GetDeleteAttributeResponse response = new GetDeleteAttributeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        // 1. Find if attribute available
        Attribute attributeFromDB = attributeService.getByName(
                request.getName()
        );

        if(attributeFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Attribute = " + request.getName() + " not found");
            response.setServiceStatus(serviceStatus);

            LOGGER.warn("Attribute with name = "+request.getName()+
                    " hasn't been found");
            return response;
        }

        // 2. delete the attribute in database
        boolean isAttributeDeleted = attributeService
                                            .deleteByName(request.getName());

        if (!isAttributeDeleted) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting Attribute with name =" + request.getName());

            LOGGER.warn("Attribute with name = "+request.getName()+

                    " hasn't been deleted");
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }



}
