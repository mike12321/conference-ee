package com.conference.dao;

import com.conference.exceptions.DataBaseNotSupportedException;
import com.conference.exceptions.IncorrectPropertyException;
import com.conference.exceptions.DataBaseConnectionException;
import org.apache.log4j.Logger;

public abstract class DaoFactory {

    private static final Logger log = Logger.getLogger(DaoFactory.class);

    /** DAO Factory methods */
    /**
     * Creates User DAO
     * @return User DAO
     */
    public abstract IUserDao getUserDao();

    /**
     * Product User DAO
     * @return Product DAO
     */
    public abstract IProductDao getProductDao();

    public abstract TopicDao getTopicDao();

    /**
     * Closes connection to Data Source
     * @throws DataBaseConnectionException if unable to close connection
     */
    abstract void closeConnection() throws DataBaseConnectionException;

    /**
     * Opens connection to Data Source
     * @throws DataBaseConnectionException if unable to open connection
     */
    public abstract void open() throws DataBaseConnectionException;

    /**
     * Closes connection to Data Source
     */
    public abstract void close();

    /**
     * Opens DB data transaction
     * @throws DataBaseConnectionException if unable to open data transaction
     */
    public abstract void beginTransaction() throws DataBaseConnectionException;

    /**
     * Commits transaction results and closes transaction
     * @throws DataBaseConnectionException if unable to commit data transaction
     */
    public abstract void commitTransaction() throws DataBaseConnectionException;

    /**
     * Rollbacks transaction results and closes transaction
     * @throws DataBaseConnectionException if unable to rollback transaction
     */
    public abstract void rollbackTransaction() throws DataBaseConnectionException;

    public static DaoFactory getDaoFactory(DataBaseSelector dataBase) throws
            DataBaseNotSupportedException,
            IncorrectPropertyException,
            DataBaseConnectionException {
        switch (dataBase) {
            case MY_SQL:
                return new MySQLDaoFactory();
            case MS_SQL:
                log.error("Database " + dataBase + " not supported yet");
                throw new DataBaseNotSupportedException(dataBase);
            case ORACLE:
                log.error("Database " + dataBase + " not supported yet");
                throw new DataBaseNotSupportedException(dataBase);
            case POSTGRESS:
                log.error("Database " + dataBase + " not supported yet");
                throw new DataBaseNotSupportedException(dataBase);
            default:
                log.error("Database type not set");
                throw new DataBaseNotSupportedException("Database type not set");
        }
    }
}