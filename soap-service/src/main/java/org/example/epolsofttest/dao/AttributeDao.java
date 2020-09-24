package org.example.epolsofttest.dao;

import org.example.epolsofttest.domain.Attribute;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AttributeDao extends CrudRepository<Attribute, Long> {

    Attribute findByName(String name);

    @Modifying
    @Transactional
    @Query("delete from Attribute a where a.name=:name")
    void deleteByName(@Param("name") String name);
}
