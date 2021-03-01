package com.conference.dao;

import com.conference.entity.Event;
import com.conference.exceptions.DataNotFoundException;

import java.util.List;

/**
 * CRUD operations interface for Event entity
 */
public interface EventDao {

    /**
     * Calculates total product number available in DB
     * @return count of products in DB
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateProductNumber() throws DataNotFoundException;

    /**
     * Finds all products in DB
     * @return List of all products in DB
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Event> findAllProductsInDB() throws DataNotFoundException;

    /**
     * Finds products in DB from
     * @param first first row number
     * @param offset offset
     * @return List of all products in DB
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Event> findProductsInDB(Integer first, Integer offset) throws DataNotFoundException;

    /**
     * Finds product in DB by id number
     * @param id - product id number
     * @return product by id number
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Event findProductById(Integer id) throws DataNotFoundException;

    /**
     * Finds product in DB by product ordering code
     * @param code - product ordering code
     * @return product by product ordering code
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
//    Event findProductByCode(String code) throws DataNotFoundException;

    /**
     * Adds new event to database
     * @param event - event to add in DB
     * @return true if operation success and false if fails
     */
    boolean addProductToDB(Event event);

    /**
     * Updates existent event to database
     * @param event - event to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateProductInDB(Event event);

    /**
     * Deletes existent product from database by product id number
     * @param id - product id number
     * @return true if operation success and false if fails
     */
    boolean deleteProductFromDB(Integer id);
}