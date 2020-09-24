package org.example.epolsofttest.service.impl;

import org.example.epolsofttest.dao.AttributeDao;
import org.example.epolsofttest.domain.Attribute;
import org.example.epolsofttest.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeDao attributeDao;

    @Override
    public List<Attribute> findAll() {
        return (List<Attribute>) attributeDao.findAll();
    }

    @Override
    public Attribute getByName(String name) {
        return attributeDao.findByName(name);
    }

    @Override
    public Attribute save(Attribute attribute) {
        try {
            return attributeDao.save(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean update(Attribute attribute) {
        try {
            attributeDao.save(attribute);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteByName(String name) {
        try {
            attributeDao.deleteByName(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
