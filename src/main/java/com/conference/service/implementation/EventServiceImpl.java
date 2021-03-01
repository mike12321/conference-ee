package com.conference.service.implementation;

import com.conference.dao.DaoFactory;
import com.conference.dao.DataBaseSelector;
import com.conference.dao.EventDao;
import com.conference.dao.UserEventDao;
import com.conference.entity.Event;
import com.conference.entity.UserEvent;
import com.conference.exceptions.*;
import com.conference.service.EventService;
import org.apache.log4j.Logger;

import java.util.List;

public class EventServiceImpl implements EventService {

    private static final Logger log = Logger.getLogger(EventServiceImpl.class);
    private static final DataBaseSelector source = DataBaseSelector.MY_SQL;

    private static DaoFactory daoFactory;
    private static EventDao productDao;
    private static UserEventDao userEventDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            productDao = daoFactory.getProductDao();
        } catch (IncorrectPropertyException | DataBaseConnectionException | DataBaseNotSupportedException ex) {
            log.error(ex);
        }
    }

    /** User validation method to check user before storing in DB */

    public boolean validateProductData(Event event) {
        return !(event.getTitle() == null || event.getTitle().isEmpty()
                || event.getDateTime() == null);
    }

    public boolean validateUserEventData(UserEvent userEvent) {
        return !(userEvent.getEventId() <= 0 || userEvent.getUserId() <= 0);
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
    public List<Event> findAllProducts() throws ProductServiceException {
        List<Event> events;
        try {
            daoFactory.open();
            productDao = daoFactory.getProductDao();
            events = productDao.findAllProductsInDB();
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new ProductServiceException();
        }
        return events;
    }

//    @Override
//    public Set<String> createProductSet() throws ProductServiceException {
//        Set<String> productSet = new HashSet<>();
//        List<Event> products = new LinkedList<>();
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
    public List<Event> findProducts(Integer from, Integer offset) throws ProductServiceException {
        List<Event> events;
        try {
            daoFactory.open();
            productDao = daoFactory.getProductDao();
            events = productDao.findProductsInDB(from, offset);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new ProductServiceException();
        }
        return events;
    }

    @Override
    public Event findProductById(int id) throws ProductServiceException {
        Event event;
        try {
            daoFactory.open();
            productDao = daoFactory.getProductDao();
            event = productDao.findProductById(id);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new ProductServiceException();
        }
        return event;
    }

//    @Override
//    public Event findProductByCode(String code) throws ProductServiceException {
//        Event product = new Event();
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
    public synchronized boolean addProduct(Event event) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            productDao = daoFactory.getProductDao();
            result = validateProductData(event) && productDao.addProductToDB(event);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateProduct(Event event) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            productDao = daoFactory.getProductDao();
            result = validateProductData(event) && productDao.updateProductInDB(event);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }

//    public synchronized boolean updateProducts(List<Event> products) {
//        try {
//            daoFactory.beginTransaction();
//            productDao = daoFactory.getProductDao();
//            for (Event product : products)
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
//    public synchronized boolean deleteProduct(Event product) {
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

    @Override
    public boolean assignUserToEvent(UserEvent userEvent) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userEventDao = daoFactory.getUserEventDao();
            result = validateUserEventData(userEvent) && userEventDao.assignUserToEvent(userEvent);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }
}