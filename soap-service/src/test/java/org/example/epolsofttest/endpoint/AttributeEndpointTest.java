package org.example.epolsofttest.endpoint;

import org.example.epolsofttest.*;
import org.example.epolsofttest.domain.Attribute;
import org.example.epolsofttest.service.AttributeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeEndpointTest {

    @Autowired
    private AttributeEndpoint attributeEndpoint;
    @MockBean
    private AttributeService attributeService;
    private Attribute attribute;
    private AttributeType attributeType;

    @Before
    public void setUp() {
        attribute = new Attribute(
                "samsung","399","black"
        );
        attributeType = new AttributeType();
        BeanUtils.copyProperties(attribute, attributeType);
    }
    
    @Test
    public void getAttributeByName() {

        GetAttributeByNameRequest getAttributeByNameRequest = new GetAttributeByNameRequest();
        getAttributeByNameRequest.setName(attribute.getName());

        Mockito.when(attributeService.getByName(attribute.getName()))
                .thenReturn(attribute);

        Assert.assertEquals(
                attribute.getName(),
                attributeEndpoint
                        .getAttributeByName(getAttributeByNameRequest)
                        .getAttributeType()
                        .getName());

        Mockito.verify(attributeService, Mockito.times(1))
                .getByName(isA(String.class));
    }

    @Test
    public void getAllAttributes() {
        GetAllAttributesRequest request = new GetAllAttributesRequest();

        Mockito.when(attributeService.findAll())
                .thenReturn(
                        Collections.singletonList(new Attribute()));

        Assert.assertEquals(1, attributeEndpoint.getAllAttributes(request).getFindAll().size());

        Mockito.verify(attributeService, Mockito.times(1))
                .findAll();
    }

    @Test
    public void updateAttribute() {

        GetUpdateAttributeRequest getUpdateAttributeRequest =
                new GetUpdateAttributeRequest();

        getUpdateAttributeRequest.setAttributeType(attributeType);

        Mockito.when(attributeService.update(attribute))
                .thenReturn(true);

        Boolean flag = attributeService.update(attribute);
        Assert.assertTrue(flag);

        Mockito.verify(attributeService, Mockito.times(1))
                .update(isA(Attribute.class));

    }

    @Test
    public void deleteAttribute() {

        GetDeleteAttributeRequest getDeleteAttributeRequest = new GetDeleteAttributeRequest();
        getDeleteAttributeRequest.setName(attribute.getName());

        Mockito.when(attributeService.deleteByName(
                getDeleteAttributeRequest.getName()
        ))
                .thenReturn(true);

        Boolean deletedAttribute = attributeService.deleteByName(getDeleteAttributeRequest.getName());
        Assert.assertTrue(deletedAttribute);

        Mockito.verify(attributeService, Mockito.times(1))
                .deleteByName(isA(String.class));

    }

    @Test
    public void saveAttribute() {
        GetAddAttributeRequest getAddAttributeRequest =
                new GetAddAttributeRequest();

        getAddAttributeRequest.setAttributeType(attributeType);

        Mockito.when(attributeService.save(attribute))
                .thenReturn(attribute);

        Attribute savedAttribute = attributeService.save(attribute);
        Assert.assertEquals(attributeType.getName(), savedAttribute.getName());

        Mockito.verify(attributeService, Mockito.times(1))
                .save(isA(Attribute.class));
    }
}