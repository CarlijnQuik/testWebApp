package com.sample;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "selectchocolateservlet",
        urlPatterns = "/SelectChocolate"
)
public class SelectChocolateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chocolateType = req.getParameter("Type");

        com.sample.ChocolateService chocolateService = new com.sample.ChocolateService();
        com.sample.model.ChocolateType l = com.sample.model.ChocolateType.valueOf(chocolateType);

        List chocolateBrands = chocolateService.getAvailableBrands(l);

        req.setAttribute("brands", chocolateBrands);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }
}