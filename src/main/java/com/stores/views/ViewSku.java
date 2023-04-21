package com.stores.views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.stores.db.DataBaseConnection;
import com.stores.db.controllers.SkuController;
import com.stores.db.models.Sku;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ViewSku extends HttpServlet {
    private SkuController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            controller = new SkuController(DataBaseConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        List<Sku> products = controller.getAll().get();

        request.setAttribute("products", products);
        request.getRequestDispatcher("templates/sku.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            controller.update(id, "\"" + SkuController.STOCKQUANTITY + "\"", quantity);
            List<Sku> products = controller.getAll().get();

            request.setAttribute("products", products);
            request.getRequestDispatcher("templates/sku.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
