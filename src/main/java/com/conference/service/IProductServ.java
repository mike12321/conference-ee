package com.conference.service;

import com.conference.domain.Product;
import com.conference.exceptions.ProductServiceException;

import java.util.List;
import java.util.Set;

public interface IProductServ {

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
    List<Product> findAllProducts() throws ProductServiceException;

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
    List<Product> findProducts(Integer from, Integer offset) throws ProductServiceException;

    /**
     * Finds a product by product code
     * @param id - product id
     * @return
     * @throws ProductServiceException if unable to retrieve information for certain reasons
     */
    Product findProductById(int id) throws ProductServiceException;

    /**
     * Adds a new product in DB
     * @param product Product to add
     * @return true if operation success and false if fails
     */
    boolean addProduct(Product product);

    /**
     * Updates an existent product in DB
     * @param product - Product to update
     * @return true if operation success and false if fails
     */

    boolean updateProduct(Product product);

    /**
     * Deletes product from DB
     * @param product - Product to delete
     * @return true if operation success and false if fails
     */
//    boolean deleteProduct(Product product);

    /**
     * Deletes product from DB by code
     * @param id product id
     * @return true if operation success and false if fails
     */

    boolean deleteProduct(int id);
}
