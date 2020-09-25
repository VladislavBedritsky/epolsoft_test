package org.example.epolsofttest.controller;


import org.example.epolsofttest.ServiceStatus;
import org.example.epolsofttest.consumer.AttributeConsumer;
import org.example.epolsofttest.dto.AttributeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller which handle requests with attribute data
 *
 * @version 1.01 25 Sep 2020
 * @author Uladzislau Biadrytski
 */
@RestController
@RequestMapping(value = "/attributes", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AttributeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeController.class);

    @Autowired
    private AttributeConsumer attributeConsumer;

    /**
     * Request method GET to find all attributes
     *
     * @return list of org.example.epolsofttest.dto.AttributeDto
     */
    @GetMapping
    public List<AttributeDto> findAll() {
        LOGGER.info("Request method GET '/attributes'");
        return attributeConsumer.getAllAttributes();
    }

    /**
     * Request method GET to find specific attribute by name.
     *
     * @param name attributes name
     * @return org.example.epolsofttest.dto.AttributeDto
     */
    @GetMapping("/{name}")
    public AttributeDto getAttributeByName(
            @PathVariable String name
    ) {
        LOGGER.info("Request method GET '/attributes/"+name+"'");
        return attributeConsumer.getAttributeByNameResponse(name);
    }

    /**
     * Request method POST to save specific attribute.
     *
     * @param attributeDto org.example.epolsofttest.dto.AttributeDto
     * @return org.example.epolsofttest.ServiceStatus
     */
    @PostMapping("/save")
    public ServiceStatus saveAttribute(@RequestBody @Valid AttributeDto attributeDto) {
        LOGGER.info("Request method POST '/attributes/save'");
        return attributeConsumer.saveAttribute(attributeDto);
    }


    /**
     * Request method PUT to update specific attribute.
     *
     * @param attributeDto org.example.epolsofttest.dto.AttributeDto
     * @return org.example.epolsofttest.ServiceStatus
     */
    @PutMapping("/update")
    public ServiceStatus updateAttribute(@RequestBody @Valid AttributeDto attributeDto) {
        LOGGER.info("Request method PUT '/attributes/update'");
        return attributeConsumer.updateAttribute(attributeDto);
    }

    /**
     * Request method DELETE to delete specific attribute by name.
     *
     * @param name  attributes name
     * @return org.example.epolsofttest.ServiceStatus
     */
    @DeleteMapping("/delete/{name}")
    public ServiceStatus deleteAttribute(@PathVariable String name) {
        LOGGER.info("Request method DELETE '/attributes/delete/"+name+"'");
        return attributeConsumer.deleteAttribute(name);
    }
}
