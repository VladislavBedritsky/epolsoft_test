package org.example.epolsofttest.service;

import org.example.epolsofttest.domain.Attribute;

import java.util.List;

/**
 * Attribute service is handling attribute data
 *
 * @version 1.01 25 Sep 2020
 * @author Uladzislau Biadrytski
 */
public interface AttributeService {


    /**
     * Find all attributes
     *
     * @return list of org.example.epolsofttest.domain.Attribute
     */
    List<Attribute> findAll();

    /**
     * Find attribute by specific name
     *
     * @param name attributes specific name
     * @return org.example.epolsofttest.domain.Attribute
     */
    Attribute getByName(String name);

    /**
     * Save attribute
     *
     * @param attribute org.example.epolsofttest.domain.Attribute
     * @return org.example.epolsofttest.domain.Attribute
     */
    Attribute save(Attribute attribute);

    /**
     * Update attribute
     *
     * @param attribute org.example.epolsofttest.domain.Attribute
     * @return true if attribute has been saved
     *        or false if not.
     */
    Boolean update(Attribute attribute);

    /**
     * Delete attribute
     *
     * @param name attributes specific name
     * @return true if attribute has been deleted
     *        or false if not.
     */
    Boolean deleteByName(String name);
}
