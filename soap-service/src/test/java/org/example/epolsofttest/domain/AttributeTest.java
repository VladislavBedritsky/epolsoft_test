package org.example.epolsofttest.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttributeTest {
    private static final String NAME = "samsung";
    private static final String VALUE = "399";
    private static final String DESCRIPTION = "black";
    private Attribute attribute;

    @Before
    public void setUp() {
        attribute = new Attribute();
    }

    @Test
    public void getName() {
        attribute.setName("samsung");

        Assert.assertNotNull(attribute);
        Assert.assertEquals(NAME, attribute.getName());
    }


    @Test
    public void getValue() {
        attribute.setValue("399");

        Assert.assertNotNull(attribute);
        Assert.assertEquals(VALUE, attribute.getValue());
    }

    @Test
    public void getDescription() {
        attribute.setDescription("black");

        Assert.assertNotNull(attribute);
        Assert.assertEquals(DESCRIPTION, attribute.getDescription());
    }

    @Test
    public void equalsAndHashcodeTest() {
        attribute.setName(NAME);
        attribute.setValue(VALUE);
        attribute.setDescription(DESCRIPTION);

        Attribute newAttr = new Attribute();
        newAttr.setName(NAME);
        newAttr.setValue(VALUE);
        newAttr.setDescription(DESCRIPTION);

        Assert.assertEquals(attribute, newAttr);
    }
}