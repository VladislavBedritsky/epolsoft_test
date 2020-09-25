package org.example.epolsofttest.dao;

import org.example.epolsofttest.domain.Attribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AttributeDaoTest {

    @Autowired
    private AttributeDao attributeDao;
    private Attribute attributeFromDb;

    @Before
    public void setUp() {
        attributeFromDb = new Attribute(
                "Iphone_X","699","white"
        );
    }

    @Test
    public void findAllAttributes_Test() {
        List<Attribute> attributes = (List<Attribute>) attributeDao.findAll();
        Assert.assertNotEquals(0, attributes.size());
    }

    @Test
    public void findAttributeByName_Test() {
        Attribute iphone_x = attributeDao.findByName(attributeFromDb.getName());
        Assert.assertEquals(iphone_x.getName(), attributeFromDb.getName());
        Assert.assertEquals(iphone_x.getValue(), attributeFromDb.getValue());
        Assert.assertEquals(iphone_x.getValue(), attributeFromDb.getValue());
    }

    @Test
    public void saveAttribute_Test() {
        Attribute attribute = new Attribute(
                "samsung","399","black onyx"
        );
        attributeDao.save(attribute);

        Attribute attrFromDb = attributeDao.findByName(attribute.getName());
        Assert.assertEquals(attribute.getName(), attrFromDb.getName());
        Assert.assertEquals(attribute.getDescription(), attrFromDb.getDescription());
        Assert.assertEquals(attribute.getValue(), attrFromDb.getValue());
    }

    @Test
    public void updateAttribute_Test() {
        attributeFromDb.setDescription("red");
        attributeDao.save(attributeFromDb);

        Attribute attrFromDb = attributeDao.findByName(attributeFromDb.getName());
        Assert.assertEquals(attributeFromDb.getName(), attrFromDb.getName());
        Assert.assertEquals(attributeFromDb.getDescription(), attrFromDb.getDescription());
    }

    @Test
    public void deleteAttributeByName_Test() {
        List<Attribute> attributesBeforeDeletion = (List<Attribute>) attributeDao.findAll();
        attributeDao.deleteByName(attributeFromDb.getName());
        List<Attribute> attributesAfterDeletion = (List<Attribute>) attributeDao.findAll();
        Assert.assertNotEquals(attributesAfterDeletion.size(), attributesBeforeDeletion.size());
    }
}