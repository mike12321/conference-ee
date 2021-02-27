package com.conference.dao.implementation;

import com.conference.dao.GenericAbstractDao;
import com.conference.dao.Mapper;
import com.conference.domain.Product;
import com.conference.dao.IProductDao;
import com.conference.exceptions.DataNotFoundException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ProductDaoImpl extends GenericAbstractDao<Product> implements IProductDao {

    private Connection connection;
    private static String SQL_select_base = "SELECT * FROM event ";
    private static String SQL_selectAll = "SELECT * FROM event;";
    private static String SQL_selectById = "SELECT * FROM event WHERE id=?;";
    private static String SQL_addNewProduct = "INSERT INTO event (title, date_time) " +
            "VALUES (?,?);";
    private static String SQL_updateProduct =       "UPDATE event SET title=?, date_time=? " +
                                                    "WHERE id=?;";
    private static String SQL_deleteProductById = "DELETE FROM event WHERE id=?;";

    /** Private methods for serving methods implementing DAO interface */

    private Mapper<Product, PreparedStatement> mapperToDB = (Product product, PreparedStatement preparedStatement) -> {
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setDate(2, Date.valueOf(product.getDateTime().toLocalDate()));
    };

    private Mapper<ResultSet, Product> mapperFromDB = (ResultSet resultSet, Product product) -> {
        product.setId(resultSet.getInt("id"));
        product.setTitle(resultSet.getString("title"));
        product.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
    };

    public ProductDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }

    @Override
    public Integer calculateProductNumber() throws DataNotFoundException {
        return calculateRowCounts(connection, "event");
    }

    @Override
    public List<Product> findAllProductsInDB() throws DataNotFoundException {
        List<Product> products = findAll(connection, Product.class, SQL_selectAll);
        return products;
    }

    @Override
    public List<Product> findProductsInDB(Integer first, Integer offset) throws DataNotFoundException {
        return findAllFromTo(connection, Product.class, first, offset, SQL_select_base);
    }

    @Override
    public Product findProductById(Integer id) throws DataNotFoundException {
        return findBy(connection, Product.class, SQL_selectById, id);
    }

    @Override
    public boolean addProductToDB(Product product) {
        return addToDB(connection, product, SQL_addNewProduct);
    }

    @Override
    public boolean updateProductInDB(Product product) {
        Integer id = product.getId();
        return updateInDB(connection, product, SQL_updateProduct, 3, id);
    }

    @Override
    public boolean deleteProductFromDB(Integer id) {
        return deleteFromDB(connection, SQL_deleteProductById, id);
    }
}