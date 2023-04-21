package com.stores.views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.stores.db.DataBaseConnection;
import com.stores.db.controllers.OrdersController;
import com.stores.db.models.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ViewOrder extends HttpServlet {
    private OrdersController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            controller = new OrdersController(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        List<Orders> orders;
        try {
            orders = controller.getAll().get();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("templates/viewOrders.jsp").forward(request, response);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            int id = Integer.parseInt(request.getParameter("id"));

            controller.update(id,  OrdersController.DELIVERED, true);
            List<Orders> orders = controller.getAll().get();

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("templates/viewOrders.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
