package org.example.epolsofttest.service.impl;

import org.example.epolsofttest.dao.AttributeDao;
import org.example.epolsofttest.domain.Attribute;
import org.example.epolsofttest.service.AttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * AttributeService implementation
 *
 * @version 1.01 25 Sep 2020
 * @author Uladzislau Biadrytski
 */
@Service
public class AttributeServiceImpl implements AttributeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeServiceImpl.class);

    @Autowired
    private AttributeDao attributeDao;

    @Override
    public List<Attribute> findAll() {
        LOGGER.info("Get all attributes");
        return (List<Attribute>) attributeDao.findAll();
    }

    @Override
    public Attribute getByName(String name) {
        try {
            LOGGER.info("Get attribute by name = "+name);
            return attributeDao.findByName(name);
        } catch (Exception e) {
            LOGGER.error("Error: "+e);
            return new Attribute();
        }
    }

    @Override
    public Attribute save(Attribute attribute) {
        try {
            LOGGER.info("Saving attribute with name = "+attribute.getName());
            return attributeDao.save(attribute);
        } catch (Exception e) {
            LOGGER.error("Error: "+e);
            return null;
        }
    }

    @Override
    public Boolean update(Attribute attribute) {
        try {
            LOGGER.info("Updating attribute with name = "+attribute.getName());
            attributeDao.save(attribute);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error: "+e);
            return false;
        }
    }

    @Override
    public Boolean deleteByName(String name) {
        try {
            LOGGER.info("Deleting attribute with name = "+name);
            attributeDao.deleteByName(name);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error: "+e);
            return false;
        }
    }
}
