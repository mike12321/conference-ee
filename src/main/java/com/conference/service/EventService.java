package com.conference.service;

import com.conference.entity.Event;
import com.conference.entity.UserEvent;
import com.conference.exceptions.ProductServiceException;

import java.util.List;

public interface EventService {

    /**
     * Calculates total number of products records in DB
     * @return total number of products stored in DB
     */
    Integer calculateProductsNumber();

    /**
     * Finds all Products in DB
     * @return List of all products
     * @throws ProductServiceException if unable to retrieve information for certain reasons
     */
    List<Event> findAllProducts() throws ProductServiceException;

    /**
     * Creates a set of all product codes
     * @return Set of all products
     * @throws ProductServiceException if unable to retrieve information for certain reasons
     */
//    Set<String> createProductSet() throws ProductServiceException;

    /**
     * Finds first @param=offset products starts from @param=from row
     * @param from - first table line number
     * @param offset - number of records to find
     * @return List of products
     * @throws ProductServiceException if unable to retrieve information for certain reasons
     */
    List<Event> findProducts(Integer from, Integer offset) throws ProductServiceException;

    /**
     * Finds a product by product code
     * @param id - product id
     * @return
     * @throws ProductServiceException if unable to retrieve information for certain reasons
     */
    Event findProductById(int id) throws ProductServiceException;

    /**
     * Adds a new event in DB
     * @param event Event to add
     * @return true if operation success and false if fails
     */
    boolean addProduct(Event event);

    /**
     * Updates an existent event in DB
     * @param event - Event to update
     * @return true if operation success and false if fails
     */

    boolean updateProduct(Event event);

    /**
     * Deletes product from DB
     * @param product - Event to delete
     * @return true if operation success and false if fails
     */
//    boolean deleteProduct(Event product);

    /**
     * Deletes product from DB by code
     * @param id product id
     * @return true if operation success and false if fails
     */

    boolean deleteProduct(int id);

    boolean assignUserToEvent(UserEvent userEvent);
}
