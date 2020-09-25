package org.example.epolsofttest.service.impl;

import org.example.epolsofttest.dao.AttributeDao;
import org.example.epolsofttest.domain.Attribute;
import org.example.epolsofttest.service.AttributeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeServiceImplTest {

    @Autowired
    private AttributeService attributeService;
    @MockBean
    private AttributeDao attributeDao;
    private Attribute attribute;

    @Before
    public void setUp() {
        attribute = new Attribute(
                "samsung","399","black"
        );
    }

    @Test
    public void findAll() {
        Mockito.when(attributeDao.findAll())
                .thenReturn(
                        Collections.singletonList(new Attribute()));

        Assert.assertEquals(1, attributeService.findAll().size());

        Mockito.verify(attributeDao, Mockito.times(1))
                .findAll();
    }

    @Test
    public void getByName() {
        Mockito.when(attributeDao.findByName(attribute.getName()))
                .thenReturn(attribute);

        Assert.assertEquals(attribute.getName(), attributeService.getByName(attribute.getName()).getName());

        Mockito.verify(attributeDao, Mockito.times(1))
                .findByName(isA(String.class));
    }

    @Test
    public void save() {
        Mockito.when(attributeDao.save(attribute))
                .thenReturn(attribute);

        Attribute savedAttribute = attributeService.save(attribute);
        Assert.assertEquals(attribute.getName(), savedAttribute.getName());

        Mockito.verify(attributeDao, Mockito.times(1))
                .save(isA(Attribute.class));
    }

    @Test
    public void deleteByName() {
        attributeService.deleteByName(attribute.getName());
        Mockito.verify(attributeDao, Mockito.times(1))
                .deleteByName(isA(String.class));
    }

    @Test
    public void update() {
        Mockito.when(attributeDao.save(attribute))
                .thenReturn(attribute);

        Boolean flag = attributeService.update(attribute);
        Assert.assertTrue(flag);

        Mockito.verify(attributeDao, Mockito.times(1))
                .save(isA(Attribute.class));
    }
}