package com.stores.views;

import com.stores.db.DataBaseConnection;
import com.stores.db.controllers.SkuController;
import com.stores.db.models.Sku;
// import com.stores.db.models.Sku;
import com.stores.db.models.constants.SkuCategory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AddSku extends HttpServlet {

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
        List<String> categories = new ArrayList<>();

        for (SkuCategory cat : SkuCategory.values()) {
            if (cat == SkuCategory.UNKNOWN)
                continue;
            categories.add(cat.getValue());
        }
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("templates/addSku.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Part file = request.getPart(SkuController.IMAGE);

            String fileName = file.getSubmittedFileName();
            String uploadPath = "../webapps/images/" + fileName;

            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream stream = file.getInputStream();

            byte[] data = new byte[stream.available()];
            stream.read(data);
            fos.write(data);
            fos.close(); 


            Sku newSku = new Sku(0,
                    request.getParameter(SkuController.NAME),
                    Integer.parseInt(request.getParameter(SkuController.PRICE)),
                    Integer.parseInt(request.getParameter(SkuController.STOCKQUANTITY)),
                    SkuCategory.fromString(request.getParameter(SkuController.CATEGORY)),
                    fileName);
    
            int resStatus = controller.addNew(newSku);
            request.setAttribute("result", "The new product was " + 
            (resStatus == 0 ? "not" : "") +
         " added successfully");
            request.getRequestDispatcher("templates/addedSku.jsp").forward(request, response);
            
        } catch (Exception e) {
            // TODO: handle exception
            request.setAttribute("result", e.toString());
            request.getRequestDispatcher("templates/addedSku.jsp").forward(request, response);
        }
    }

}
