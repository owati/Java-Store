package com.stores.db.controllers;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Abstract Controller class for all controllers
 */
public interface IController<T> {

    public  Optional<List<T>> getAll() throws SQLException;

    public  Optional<List<T>> getSome(String field, String value) throws SQLException;

    public  Optional<T> getOne(int id) throws SQLException;

    public  Optional<T> getOne(String field, String value) throws SQLException;

    public  int addNew (T data) throws SQLException;

    public  int update (int id, String field, String value) throws SQLException;

    public  int update (int id, String field, int value) throws SQLException;

}