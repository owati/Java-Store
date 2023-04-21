package com.stores.views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import com.stores.db.DataBaseConnection;
import com.stores.db.controllers.SkuController;
import com.stores.db.models.Sku;
import com.stores.db.models.constants.SkuCategory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LandingPage extends HttpServlet {

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
        try {
            List<String> categories = new ArrayList<>();

            for (SkuCategory cat : SkuCategory.values()) {
                if (cat == SkuCategory.UNKNOWN)
                    continue;
                categories.add(cat.getValue());
            }
            String categoryQuery = request.getParameter("cat");
            List<Sku> products;
            if (categoryQuery == null)
                products = controller.getAll().get();
            else
                products = controller.getSome(SkuController.CATEGORY, categoryQuery).get();
            
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("templates/landing.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("templates/landing.jsp").forward(request, response);
            
        }

    }
}
