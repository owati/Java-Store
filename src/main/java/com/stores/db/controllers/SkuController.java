package com.stores.db.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stores.db.models.Sku;
import com.stores.db.models.constants.SkuCategory;

public class SkuController implements IController<Sku> {
    private final Connection connection;
    private final String TABLENAME = "public.\"Sku\"";

    // field meta
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String STOCKQUANTITY = "stockQuantity";
    public static final String CATEGORY = "category";
    public static final String IMAGE = "image";


    public SkuController(Connection conn) {
        connection = conn;
    }

    @Override
    public Optional<List<Sku>> getAll() {
        try {
            PreparedStatement smt = connection.prepareStatement("SELECT * FROM " + TABLENAME);
            ResultSet result = smt.executeQuery();

            List<Sku> querySkus = new ArrayList<>();
            while (result.next())
                querySkus.add(new Sku(result.getInt(ID),
                        result.getString(NAME),
                        result.getInt(PRICE),
                        result.getInt(STOCKQUANTITY),
                        SkuCategory.fromString(result.getString(CATEGORY)),
                        result.getString(IMAGE)));

            return Optional.of(querySkus);

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Sku>> getSome(String field, String value) {
        try {
            PreparedStatement smt = connection.prepareStatement("SELECT * FROM " + TABLENAME + " WHERE " + field + " = ?");
            smt.setString(1, value);

            ResultSet result = smt.executeQuery();
            List<Sku> querySkus = new ArrayList<>();
            while (result.next())
                querySkus.add(new Sku(result.getInt(ID),
                        result.getString(NAME),
                        result.getInt(PRICE),
                        result.getInt(STOCKQUANTITY),
                        SkuCategory.fromString(result.getString(CATEGORY)),
                        result.getString(IMAGE)));

            return Optional.of(querySkus);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Sku> getOne(int id) throws SQLException {

        PreparedStatement smt = connection.prepareStatement("SELECT * FROM " + TABLENAME + " WHERE id = ?");
        smt.setInt(1, id);

        ResultSet result = smt.executeQuery();
        result.next();

        return Optional.of(new Sku(result.getInt(ID),
                result.getString(NAME),
                result.getInt(PRICE),
                result.getInt(STOCKQUANTITY),
                SkuCategory.fromString(result.getString(CATEGORY)),
                result.getString(IMAGE)));

    }

    @Override
    public Optional<Sku> getOne(String field, String value) {
        try {
            PreparedStatement smt = connection
                    .prepareStatement("SELECT * FROM " + TABLENAME + " WHERE " + field + " = ?");
            smt.setString(1, value);

            ResultSet result = smt.executeQuery();
            result.next();

            return Optional.of(new Sku(result.getInt(ID),
                    result.getString(NAME),
                    result.getInt(PRICE),
                    result.getInt("\"" +STOCKQUANTITY + "\""),
                    SkuCategory.fromString(result.getString(CATEGORY)),
                    result.getString(IMAGE)));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public int addNew(Sku data) throws SQLException {
        PreparedStatement smt = connection.prepareStatement("INSERT INTO " + TABLENAME + " (" +
                NAME + "," + PRICE + ", \"" + STOCKQUANTITY + "\"," + CATEGORY +  "," + IMAGE +") " +
                "VALUES (?, ?, ?, ?, ?);");
        smt.setString(1, data.getName());
        smt.setInt(2, data.getPrice());
        smt.setInt(3, data.getStockQuantity());
        smt.setString(4, data.getCategory().getValue());
        smt.setString(5, data.getImage());

        return smt.executeUpdate();
    }

    @Override
    public int update(int id, String field, String value) {
        try {
            PreparedStatement smt = connection.prepareStatement("UPDATE ? " +
                    "SET ? = ? " +
                    "WHERE id = ?");
            smt.setString(1, TABLENAME);
            smt.setString(2, field);
            smt.setString(3, value);
            smt.setInt(4, id);

            return smt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(int id, String field, int value) throws SQLException{
        PreparedStatement smt = connection.prepareStatement("UPDATE " + TABLENAME + " " +
                "SET " + field + " = ? " +
                "WHERE id = ?");
        smt.setInt(1, value);
        smt.setInt(2, id);

        return smt.executeUpdate();

    }
}
