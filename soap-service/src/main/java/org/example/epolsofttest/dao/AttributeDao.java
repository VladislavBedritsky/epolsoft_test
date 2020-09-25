package org.example.epolsofttest.dao;

import org.example.epolsofttest.domain.Attribute;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Attribute Dao is a child of org.springframework.data.repository.CrudRepository.
 * Handle attribute data from/to db.
 *
 * @version 1.01 25 Sep 2020
 * @author Uladzislau Biadrytski
 */
@Repository
public interface AttributeDao extends CrudRepository<Attribute, Long> {

    /**
     * Find attribute by specific name
     *
     * @param name attributes specific name
     * @return org.example.epolsofttest.domain.Attribute
     */
    Attribute findByName(String name);

    /**
     * Delete attribute
     *
     * @param name attributes specific name
     */
    @Modifying
    @Transactional
    @Query("delete from Attribute a where a.name=:name")
    void deleteByName(@Param("name") String name);
}
