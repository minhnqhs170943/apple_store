/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import controller.common.Authorization;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Product;

/**
 *
 * @author PV
 */
@WebServlet(name="AdminServlet", urlPatterns={"/admin"})
public class AdminServlet extends HttpServlet {
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (!Authorization.isAdmin(acc)) {
            Authorization.redirectToHome(session, response);
        } else {
            DAO d=new DAO();
            List<Product> listp=d.getAllProduct();
            List<Account> listacc=d.getAllAccount();
            Double total=d.getTotalAmount();
            Double Iphone=d.getAmountByCate("Iphone");
            Double Macbook=d.getAmountByCate("Macbook");
            Double Ipad=d.getAmountByCate("Ipad");
            Double Apple_watch=d.getAmountByCate("Apple watch");
            Double may=d.getAmountByMonth(5);
            Double june=d.getAmountByMonth(6);
            Double july=d.getAmountByMonth(7);
            Double august=d.getAmountByMonth(8);
            Double september=d.getAmountByMonth(9);
            Double october=d.getAmountByMonth(10);
            
            request.setAttribute("may", may);
            request.setAttribute("june", june);
            request.setAttribute("july", july);
            request.setAttribute("august",august);
            request.setAttribute("september", september);
            request.setAttribute("october",october);
            
            request.setAttribute("iphone", Iphone);
            request.setAttribute("mac", Macbook);
            request.setAttribute("ipad", Ipad);
            request.setAttribute("applew",Apple_watch);
            
            request.setAttribute("product", listp.size());
            request.setAttribute("user", listacc.size()+1);
            request.setAttribute("total", total);
            
            
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
