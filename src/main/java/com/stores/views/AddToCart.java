package com.stores.views;

import java.io.IOException;
import java.sql.SQLException;

import com.stores.db.DataBaseConnection;
import com.stores.db.controllers.SkuController;
import com.stores.db.models.Cart;
import com.stores.db.models.CartItem;
import com.stores.db.models.Sku;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;


public class AddToCart extends HttpServlet {

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
        try{
            final int id =  Integer.parseInt(request.getParameter("id"));
            Sku product = controller.getOne(id).get();
    
            request.setAttribute("product", product);
            request.getRequestDispatcher("templates/addToCart.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("templates/addToCart.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null)
            cart = new Cart();

        try {
            cart.add(new CartItem(controller.getOne(Integer.parseInt(request.getParameter("id"))).get(), 
                        Integer.parseInt(request.getParameter("quantity"))));
            session.setAttribute("cart", cart);
            request.setAttribute("message", "The product has been added to your cart");
        } catch (Exception e) {
            request.setAttribute("message", e.toString());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("templates/addedToCart.jsp").forward(request, response);
        }

    }
    
}
