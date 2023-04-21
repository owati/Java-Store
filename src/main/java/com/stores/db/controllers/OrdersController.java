package com.stores.db.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.stores.db.models.OrderItem;
import com.stores.db.models.Orders;

public class OrdersController implements IController<Orders> {
    private final Connection connection;

    private final String TABLENAME = "public.\"order\"";

    public static final String ID = "id";
    public static final String TOTAL = "total";
    public static final String NAME = "name";
    public static final String ITEMS = "items";
    public static final String DELIVERED = "delivered";

    public OrdersController(Connection conn) {
        connection = conn;
    }

    @Override
    public Optional<List<Orders>> getAll() throws SQLException {
        PreparedStatement smt = connection
                .prepareStatement("SELECT public.\"order\".id, delivered, public.\"order\".total, name," +
                        "(item).product AS item_name, (item).quantity AS item_quantity, (item).total as item_total " +
                        "FROM public.\"order\", UNNEST(items) AS item  ORDER BY public.\"order\".id ASC;");

        ResultSet result = smt.executeQuery();

        List<Orders> orders = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt(ID);
            boolean delivered = result.getBoolean(DELIVERED);
            int total = result.getInt(TOTAL);
            String name = result.getString(NAME);
            String item_name = result.getString("item_name");
            int item_quantity = result.getInt("item_quantity");
            int item_total = result.getInt("item_total");

            Predicate<Orders> filterOrder = new HasIdPredicate(id);

            Orders order = orders.stream()
                    .filter(filterOrder)
                    .findFirst()
                    .orElse(null);
            System.out.println(String.format("The id => %d", id));
            if (order == null) {
                System.out.println(String.format("New Object => %d", id));
                ArrayList<OrderItem> items = new ArrayList<OrderItem>();
                items.add(new OrderItem(0, item_name, item_total, item_quantity));
                Orders newOrder = new Orders(id, name, total, delivered, items);
                orders.add(newOrder);
            } else {
                order.getItems().add(new OrderItem(0, item_name, item_total, item_quantity));
            }
        }

        return Optional.of(orders);
    }

    @Override
    public Optional<List<Orders>> getSome(String field, String value) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSome'");
    }

    @Override
    public Optional<Orders> getOne(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public Optional<Orders> getOne(String field, String value) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public int addNew(Orders data) throws SQLException {
        String queryString = "INSERT INTO " + TABLENAME + "(" +
                NAME + "," + TOTAL + "," + DELIVERED + "," + ITEMS + ") VALUES (? , ? , ?, ARRAY[";
        List<OrderItem> items = data.getItems();
        for (OrderItem item : items) {
            queryString += String.format("ROW(%d, '%s', %d, %d)::public.\"Item\"", item.getId(), item.getProduct(),
                    item.getQuantity(), item.getTotal()) + (item.getId() != (items.size() - 1) ? ", " : "");
        }
        queryString += "])";
        System.out.println(queryString);
        System.out.println(items.size());

        PreparedStatement smt = connection.prepareStatement(queryString);

        smt.setString(1, data.getName());
        smt.setInt(2, data.getTotal());
        smt.setBoolean(3, data.isDelivered());

        return smt.executeUpdate();
    }

    @Override
    public int update(int id, String field, String value) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int update(int id, String field, int value) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public int update(int id, String field, boolean value) throws SQLException {
        PreparedStatement smt = connection.prepareStatement("UPDATE " + TABLENAME + " " +
                "SET " + field + " = ? " +
                "WHERE id = ?");
        smt.setBoolean(1, value);
        smt.setInt(2, id);

        return smt.executeUpdate();
    }

    class HasIdPredicate implements Predicate<Orders> {
        private final int id;

        public HasIdPredicate(int id) {
            super();
            this.id = id;
        }

        @Override
        public boolean test(Orders order) {
            return order.getId() == id;
        }
    }

}
