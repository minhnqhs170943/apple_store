/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.home;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Color;
import model.Product;
import model.Specifications;


@WebServlet(name="DetailServlet", urlPatterns={"/detail"})
public class DetailServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id=request.getParameter("pid");
        DAO d=new DAO();
        Product p=d.getProductByID(id);
        List<Product> listB=d.getRelatedProduct(p);
        List<Color> listc=d.getColor(p.getName());
        List<model.Category> listC=d.getAllCategory();
        Specifications s=d.getProductDetail(id);
        
        request.setAttribute("spe", s);
        request.setAttribute("listCo", listc);                
        request.setAttribute("listC", listC);
        request.setAttribute("detail", p);
        request.setAttribute("listB", listB);

        request.getRequestDispatcher("detail.jsp").forward(request, response);
        
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
