package org.example.epolsofttest.controller;


import org.example.epolsofttest.ServiceStatus;
import org.example.epolsofttest.consumer.AttributeConsumer;
import org.example.epolsofttest.dto.AttributeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/attributes", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AttributeController {

    @Autowired
    private AttributeConsumer attributeConsumer;

    @GetMapping
    public List<AttributeDto> findAll() {
        return attributeConsumer.getAllAttributes();
    }

    @GetMapping("/{name}")
    public AttributeDto getAttributeByName(
            @PathVariable String name
    ) {

        return attributeConsumer.getAttributeByNameResponse(name);
    }

    @PostMapping("/save")
    public ServiceStatus saveAttribute(@RequestBody @Valid AttributeDto attributeDto) {
        return attributeConsumer.saveAttribute(attributeDto);
    }

    @PutMapping("/update")
    public ServiceStatus updateAttribute(@RequestBody @Valid AttributeDto attributeDto) {
        return attributeConsumer.updateAttribute(attributeDto);
    }

    @DeleteMapping("/delete/{name}")
    public ServiceStatus deleteAttribute(@PathVariable String name) {
        return attributeConsumer.deleteAttribute(name);
    }
}
