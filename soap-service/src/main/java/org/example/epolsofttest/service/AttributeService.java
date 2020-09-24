package org.example.epolsofttest.service;

import org.example.epolsofttest.domain.Attribute;

import java.util.List;

public interface AttributeService {

    List<Attribute> findAll();
    Attribute getByName(String name);
    Attribute save(Attribute attribute);
    Boolean update(Attribute attribute);
    Boolean deleteByName(String name);
}
