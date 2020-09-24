package org.example.epolsofttest.controller;


import org.example.epolsofttest.AttributeDTO;
import org.example.epolsofttest.GetAllAttributesResponse;
import org.example.epolsofttest.GetAttributeByNameResponse;
import org.example.epolsofttest.ServiceStatus;
import org.example.epolsofttest.client.AttributeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/attributes", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AttributeController {

    @Autowired
    private AttributeClient attributeClient;

    @GetMapping
    public List<AttributeDTO> findAll() {
        return attributeClient.getAllAttributes();
    }

    @GetMapping("/{name}")
    public AttributeDTO getAttributeByName(
            @PathVariable String name
    ) {

        return attributeClient.getAttributeByNameResponse(name);
    }

    @PostMapping("/save")
    public ServiceStatus saveAttribute(@RequestBody AttributeDTO attributeDTO) {
        return attributeClient.saveAttribute(attributeDTO);
    }

    @PutMapping("/update")
    public ServiceStatus updateAttribute(@RequestBody AttributeDTO attributeDTO) {
        return attributeClient.updateAttribute(attributeDTO);
    }

    @DeleteMapping("/delete/{name}")
    public ServiceStatus deleteAttribute(@PathVariable String name) {
        return attributeClient.deleteAttribute(name);
    }
}
