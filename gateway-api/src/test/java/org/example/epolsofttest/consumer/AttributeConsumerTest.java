package org.example.epolsofttest.consumer;

import org.example.epolsofttest.dto.AttributeDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeConsumerTest {

    private static final String ATTRIBUTES_URL = "https://epol-soap.xfarm.xyz/ws/attributes";

    @Autowired
    private AttributeConsumer attributeConsumer;

    private AttributeDto attributeFromDb;

    @Before
    public void setUp()  {
        attributeFromDb = new AttributeDto();
        attributeFromDb.setName("Iphone_X");
        attributeFromDb.setValue("699");
        attributeFromDb.setDescription("white");
    }


    @Test
    public void getAttributeByNameResponse() {
        AttributeDto attributeByNameResponse =
                attributeConsumer.getAttributeByNameResponse(attributeFromDb.getName());
        Assert.assertEquals(attributeFromDb.getName(), attributeByNameResponse.getName());

    }

    @Test
    public void getAllAttributes() {
        List<AttributeDto> allAttributes = attributeConsumer.getAllAttributes();
        Assert.assertNotEquals(0, allAttributes.size());
    }

}