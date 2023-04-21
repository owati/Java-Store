package com.stores.views;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ViewCart extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        request.getRequestDispatcher("templates/cart.jsp").forward(request, response);
    }
}
