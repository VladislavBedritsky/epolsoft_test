package org.example.epolsofttest.dao;

import org.example.epolsofttest.domain.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeDao extends CrudRepository<Attribute, Long> {

    Attribute findByName(String name);
    void deleteByName(String name);
}
