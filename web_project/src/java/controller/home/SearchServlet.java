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
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //search theo name
        List<Product> product = new ArrayList<>();
        String sortType = request.getParameter("sort");
        String search = request.getParameter("txt");//nhan value
        String key = request.getParameter("key");
        String price = request.getParameter("price");
        DAO d = new DAO();
        List<model.Category> listC = d.getAllCategory();

        //search theo thuoc tinh
        if (search != null) {
            product = d.searchByName(search);
        }

        if (key != null) {
            if (key.equals("0")) {
                product = d.getAllProduct();
            } else {
                product = d.getProductByCID(key);
            }
        }

        if (price != null) {
            double from = 0, to = 0;
            if (price.equals("0")) {
                from = 0;
                to = 20000;
                product = d.getProductByPrice(from, to);
            }
            if (price.equals("1")) {
                from = 0;
                to = 1000;
                product = d.getProductByPrice(from, to);

            }
            if (price.equals("2")) {
                from = 1000;
                to = 2000;
                product = d.getProductByPrice(from, to);

            }
            if (price.equals("3")) {
                from = 2000;
                to = 3000;
                product = d.getProductByPrice(from, to);

            }
            if (price.equals("4")) {
                from = 3000;
                to = 20000;
                product = d.getProductByPrice(from, to);

            }

        }
        int sort = -1;
        try {
             sort=Integer.parseInt(sortType);
        } catch (Exception e) {
        }
        if (sort != -1) {
            product = d.getAllProduct();
            if (sortType.equals("1")) {
                Collections.sort(product, Comparator.comparingDouble(Product::getPrice));
            } else if (sortType.equals("2")) {
                Collections.sort(product, Comparator.comparingDouble(Product::getPrice));
                Collections.reverse(product);
            } else if (sortType.equals("3")) {
                Collections.sort(product, Comparator.comparing(Product::getRelease_date));

            } else if (sortType.equals("4")) {
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
        request.setAttribute("value", search);
        request.setAttribute("key", key);
        request.setAttribute("price", price);

        request.setAttribute("listP", list1);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("store.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
