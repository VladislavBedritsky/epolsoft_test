package org.example.epolsofttest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.epolsofttest.ServiceStatus;
import org.example.epolsofttest.consumer.AttributeConsumer;
import org.example.epolsofttest.dto.AttributeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AttributeController.class)
public class AttributeControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AttributeConsumer attributeConsumer;

    private AttributeDto attributeDto;
    private ServiceStatus serviceStatus;

    @Before
    public void setUp() {
        attributeDto = new AttributeDto();
        attributeDto.setName("samsung_s");
        attributeDto.setDescription("s7");
        attributeDto.setValue("399");
        serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
    }

    @Test
    public void findAll() throws Exception {
        List<AttributeDto> attributeDtoList = new ArrayList<>(
                Collections.singletonList(
                        new AttributeDto()));

        given(attributeConsumer.getAllAttributes()).willReturn(attributeDtoList);

        mvc.perform(get("/attributes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attributeConsumer, times(1))
                .getAllAttributes();
    }

    @Test
    public void getAttributeByName() throws Exception {
        given(attributeConsumer.getAttributeByNameResponse(attributeDto.getName()))
                .willReturn(attributeDto);

        mvc.perform(get("/attributes/samsung")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attributeConsumer, times(1))
                .getAttributeByNameResponse(isA(String.class));

    }

    @Test
    public void saveAttribute() throws Exception {


        given(attributeConsumer.saveAttribute(attributeDto))
                .willReturn(serviceStatus);

        mvc.perform(post("/attributes/save")
                .content(asJsonString(attributeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attributeConsumer, times(1))
                .saveAttribute(isA(AttributeDto.class));

    }

    @Test
    public void updateAttribute() throws Exception {

        given(attributeConsumer.updateAttribute(attributeDto))
                .willReturn(serviceStatus);

        mvc.perform(put("/attributes/update")
                .content(asJsonString(attributeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attributeConsumer, times(1))
                .updateAttribute(isA(AttributeDto.class));

    }

    @Test
    public void deleteAttribute() throws Exception {
        given(attributeConsumer.deleteAttribute(attributeDto.getName()))
                .willReturn(serviceStatus);

        mvc.perform(delete("/attributes/delete/samsung")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attributeConsumer, times(1))
                .deleteAttribute(isA(String.class));
    }



    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}