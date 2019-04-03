package com.sample;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@WebServlet(
        name = "selectchocolateservlet",
        urlPatterns = "/SelectChocolate"
)
public class SelectChocolateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chocolateType = req.getParameter("Type");
        String numberOne = req.getParameter("numberOne");
        String numberTwo = req.getParameter("numberTwo");

        List<String> thisList = new ArrayList<>();
        thisList.add(numberOne);
        thisList.add(numberTwo);
    
        com.sample.ChocolateService chocolateService = new com.sample.ChocolateService();
        com.sample.model.ChocolateType l = com.sample.model.ChocolateType.valueOf(chocolateType);

        List chocolateBrands = chocolateService.getAvailableBrands(l);
  

        req.setAttribute("brands", chocolateBrands);
        req.setAttribute("count", trueOrFalse(thisList));
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }

    public String trueOrFalse(List<String> list){
        Random rand = new Random();
        return list.get(rand.nextInt(list.size())); 
    }
       
}