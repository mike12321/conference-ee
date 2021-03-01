package com.conference.service.implementation;

import com.conference.dao.DaoFactory;
import com.conference.dao.DataBaseSelector;
import com.conference.dao.IProductDao;
import com.conference.domain.Product;
import com.conference.exceptions.*;
import com.conference.service.IProductServ;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductService implements IProductServ {

    private static final Logger log = Logger.getLogger(ProductService.class);
    private static final DataBaseSelector source = DataBaseSelector.MY_SQL;

    private static DaoFactory daoFactory;
    private static IProductDao productDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            productDao = daoFactory.getProductDao();
        } catch (IncorrectPropertyException | DataBaseConnectionException | DataBaseNotSupportedException ex) {
            log.error(ex);
        }
    }

    /** User validation method to check user before storing in DB */

    public boolean validateProductData(Product product) {
        return !(product.getTitle() == null || product.getTitle().isEmpty()
                || product.getDateTime() == null);
    }

    /** Data access and storing methods */

    @Override
    public Integer calculateProductsNumber() {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            productDao = daoFactory.getProductDao();
            result = productDao.calculateProductNumber();
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
        }
        return result;
    }

    @Override
    public List<Product> findAllProducts() throws ProductServiceException {
        List<Product> products;
        try {
            daoFactory.open();
            productDao = daoFactory.getProductDao();
            products = productDao.findAllProductsInDB();
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new ProductServiceException();
        }
        return products;
    }

//    @Override
//    public Set<String> createProductSet() throws ProductServiceException {
//        Set<String> productSet = new HashSet<>();
//        List<Product> products = new LinkedList<>();
//        try {
//            daoFactory.open();
//            productDao = daoFactory.getProductDao();
//            products = productDao.findAllProductsInDB();
//            daoFactory.close();
//            products.forEach((product) -> {productSet.add(product.getCode());});
//        } catch (DataBaseConnectionException | DataNotFoundException ex) {
//            log.error(ex);
//            throw new ProductServiceException();
//        }
//        return productSet;
//    }

    @Override
    public List<Product> findProducts(Integer from, Integer offset) throws ProductServiceException {
        List<Product> products;
        try {
            daoFactory.open();
            productDao = daoFactory.getProductDao();
            products = productDao.findProductsInDB(from, offset);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new ProductServiceException();
        }
        return products;
    }

    @Override
    public Product findProductById(int id) throws ProductServiceException {
        Product product;
        try {
            daoFactory.open();
            productDao = daoFactory.getProductDao();
            product = productDao.findProductById(id);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new ProductServiceException();
        }
        return product;
    }

//    @Override
//    public Product findProductByCode(String code) throws ProductServiceException {
//        Product product = new Product();
//        try {
//            daoFactory.open();
//            productDao = daoFactory.getProductDao();
//            product = productDao.findProductByCode(code);
//            daoFactory.close();
//        } catch (DataBaseConnectionException | DataNotFoundException ex) {
//            log.error(ex);
//            throw new ProductServiceException();
//        }
//        return product;
//    }

    @Override
    public synchronized boolean addProduct(Product product) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            productDao = daoFactory.getProductDao();
            result = validateProductData(product) && productDao.addProductToDB(product);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateProduct(Product product) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            productDao = daoFactory.getProductDao();
            result = validateProductData(product) && productDao.updateProductInDB(product);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }

//    public synchronized boolean updateProducts(List<Product> products) {
//        try {
//            daoFactory.beginTransaction();
//            productDao = daoFactory.getProductDao();
//            for (Product product : products)
//                if (!productDao.updateProductInDB(product)) {
//                    daoFactory.rollbackTransaction();
//                    return false;
//                }
//            daoFactory.commitTransaction();
//            return true;
//        } catch (DataBaseConnectionException ex) {
//            log.error(ex);
//            return false;
//        }
//    }

//    @Override
//    public synchronized boolean deleteProduct(Product product) {
//        boolean result;
//        try {
//            daoFactory.beginTransaction();
//            productDao = daoFactory.getProductDao();
//            result = productDao.deleteProductFromDB(product.getId());
//            daoFactory.commitTransaction();
//        } catch (DataBaseConnectionException ex) {
//            log.error(ex);
//            return false;
//        }
//        return result;
//    }

    @Override
    public synchronized boolean deleteProduct(int id) {
        boolean result;

        try {
            daoFactory.beginTransaction();
            productDao = daoFactory.getProductDao();
            result = productDao.deleteProductFromDB(id);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }

        return result;
    }
}