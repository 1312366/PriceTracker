package com.hcmus.pricetracker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import dao.PriceTrackerDAO;

/**
 *
 * @author Minh Tran
 */
public class homepage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="HEADER">
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        //<editor-fold defaultstate="collapsed" desc="EVENT CHECK LOGIN">
        if (userPath.equals("/getPriceProduct")) {
            String url = request.getParameter("url");
            PriceTrackerDAO priceTrackerDAO = new PriceTrackerDAO();
            String strJson = new Gson().toJson(priceTrackerDAO.getPriceProduct(url));
            out.print(strJson);
        }
        if (userPath.equals("/getPriceHistory")) {
            PriceTrackerDAO priceTrackerDAO = new PriceTrackerDAO();
            String url = request.getParameter("url");
            String strJson = new Gson().toJson(priceTrackerDAO.getPriceHistory(url));
            out.print(strJson);
        }
        if (userPath.equals("/saveUserRequest")) {
            PriceTrackerDAO priceTrackerDAO = new PriceTrackerDAO();
            String url = request.getParameter("url");
            Integer sessionID = request.getParameter("sessionID").equals("") ? -1 :Integer.parseInt(request.getParameter("sessionID"));
            Integer result=priceTrackerDAO.saveUserRequest(url, sessionID);
            out.print(result);
        }
    }

}
