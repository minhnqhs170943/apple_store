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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Product;

/**
 *
 * @author PV
 */
@WebServlet(name="SortServlet", urlPatterns={"/sort"})
public class SortServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SortServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
        //search theo name
        List<Product> product = new ArrayList<>();
        String sortType = request.getParameter("sort");
        
        DAO d = new DAO();
        List<model.Category> listC = d.getAllCategory();
        product=d.getAllProduct();

        //search theo thuoc tinh
        
            
            if (sortType != null) {
            if (sortType.equals("1")) {
                Collections.sort(product, Comparator.comparingDouble(Product::getPrice));
            } else if (sortType.equals("2")) {
                Collections.sort(product, Comparator.comparingDouble(Product::getPrice));
                Collections.reverse(product);
            }else if (sortType.equals("3")) {
              Collections.sort(product, Comparator.comparing(Product::getRelease_date));
               
           }else if (sortType.equals("4")) {
              Collections.sort(product, Comparator.comparing(Product::getRelease_date));
               Collections.reverse(product);
           }
        }
        

        //phan  trang
        int page, numperpage = 9;
        int size = product.size();
        int num = (size % 9 == 0 ? (size / 9) : ((size / 9) + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Product> list1 = d.getListByPage(product, start, end);


        request.setAttribute("s", sortType);
        request.setAttribute("listP", list1);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("store.jsp").forward(request, response);

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
