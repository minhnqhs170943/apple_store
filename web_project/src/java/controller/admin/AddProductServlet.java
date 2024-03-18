package controller.admin;

import controller.common.Authorization;
import dal.DAO;
import model.*;
import java.util.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
public class AddProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (!Authorization.isAdmin(acc)) {
            Authorization.redirectToHome(session, response);
        } else {
            // lay ra list category de chon category cho san pham moi
            DAO d = new DAO();
            List<Category> listC = d.getAllCategory();
            request.setAttribute("listC", listC);

            request.getRequestDispatcher("add_product.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // lay thong tin cua san pham moi tu trang jsp
        String name = request.getParameter("name");
        String image = "./img/" + request.getParameter("image");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String des = request.getParameter("des");
        int category = Integer.parseInt(request.getParameter("category"));
        String screen=request.getParameter("screen");
        String cpu=request.getParameter("cpu");
        String ram=request.getParameter("ram");
        String storage=request.getParameter("storage");
        
        DAO d = new DAO();
        Product product = d.getProductByName(name);
        if (product != null) {
            String ms = "existed";
            request.setAttribute("error", ms);
             List<Category> listC=d.getAllCategory();
              request.setAttribute("listC", listC);
            request.getRequestDispatcher("add_product.jsp").forward(request, response);
        } else {
            // them moi 1 san pham
            Product productNew=new Product(name, des, price, quantity, image, category);
            d.addProduct(productNew);
            Product pn=d.getProductByName(name);
            d.addProductDetail(pn, screen, cpu, ram,storage);
            
            List<Product> listP;
            listP = d.getAllProduct();
            
            int page, numperpage = 10;
            int size = listP.size();
            int num = (size % 10 == 0 ? (size / 10) : ((size / 10) + 1));
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<Product> list1 = d.getListByPage(listP, start, end);

            
            request.setAttribute("listP", list1);
            request.setAttribute("page", page);
            request.setAttribute("num", num);

            request.getRequestDispatcher("list_product.jsp").forward(request, response);
        }
    }
}
