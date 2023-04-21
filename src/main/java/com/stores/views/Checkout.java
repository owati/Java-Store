package com.stores.views;


import java.io.IOException;
import java.util.ArrayList;

import com.stores.db.DataBaseConnection;
import com.stores.db.controllers.OrdersController;
import com.stores.db.models.Cart;
import com.stores.db.models.CartItem;
import com.stores.db.models.OrderItem;
import com.stores.db.models.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Checkout extends HttpServlet {
    private OrdersController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            controller = new OrdersController(DataBaseConnection.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("templates/checkout.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null || cart.size() == 0) {
                throw new Exception("Cannot create order cart is empty");
            }
            ArrayList<OrderItem> items = new ArrayList<>();

            for (CartItem item : cart) 
                items.add(new OrderItem(cart.indexOf(item), item.getProduct().getName(), 
                    item.getTotalCost(), item.getQuantity()));
            
            Orders order = new Orders(0, request.getParameter("name"), cart.getTotalCost(),
            false, items );

            controller.addNew(order);
            cart.clear();

            session.setAttribute("cart", cart);
            request.setAttribute("message", "Your order has been created and will handled");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
        } finally {
            request.getRequestDispatcher("templates/checkedout.jsp").forward(request, response);
        }
    }


}
