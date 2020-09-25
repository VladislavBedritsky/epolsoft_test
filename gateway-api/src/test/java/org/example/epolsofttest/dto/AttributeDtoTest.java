package org.example.epolsofttest.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttributeDtoTest {

    private static final String NAME = "samsung";
    private static final String VALUE = "399";
    private static final String DESCRIPTION = "black";
    private AttributeDto attributeDto;

    @Before
    public void setUp() {
        attributeDto = new AttributeDto();
    }

    @Test
    public void getName() {
        attributeDto.setName("samsung");

        Assert.assertNotNull(attributeDto);
        Assert.assertEquals(NAME, attributeDto.getName());
    }


    @Test
    public void getValue() {
        attributeDto.setValue("399");

        Assert.assertNotNull(attributeDto);
        Assert.assertEquals(VALUE, attributeDto.getValue());
    }

    @Test
    public void getDescription() {
        attributeDto.setDescription("black");

        Assert.assertNotNull(attributeDto);
        Assert.assertEquals(DESCRIPTION, attributeDto.getDescription());
    }

    @Test
    public void equalsAndHashcodeTest() {
        attributeDto.setName(NAME);
        attributeDto.setValue(VALUE);
        attributeDto.setDescription(DESCRIPTION);

        AttributeDto newAttr = new AttributeDto();
        newAttr.setName(NAME);
        newAttr.setValue(VALUE);
        newAttr.setDescription(DESCRIPTION);

        Assert.assertEquals(attributeDto, newAttr);
    }
}