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

@WebServlet(name = "EditProductServlet", urlPatterns = {"/editproduct"})
public class EditProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (!Authorization.isAdmin(acc)) {
            Authorization.redirectToHome(session, response);
        } else {
            
            String strId = request.getParameter("ID");
            DAO d = new DAO();
            Product p = d.getProductByID(strId);
            Specifications productS=d.getProductDetail(strId);
            List<Category> listC = d.getAllCategory();
            
            request.setAttribute("detail", p);
            request.setAttribute("listC", listC);
            request.setAttribute("detailSpe", productS);
            

            request.getRequestDispatcher("edit_product.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productID = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        // String image =  request.getParameter("image");
        String image = "./img/" + request.getParameter("image");
        Double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int cateID = Integer.parseInt(request.getParameter("category"));
        String des = request.getParameter("des");
        
        String screen=request.getParameter("screen");
        String cpu=request.getParameter("cpu");
        String ram=request.getParameter("ram");
        String storage=request.getParameter("storage");

        // cap nhat san pham
        DAO d = new DAO();

        Product product = d.getProductByName(name);
        if (product != null) {
            String ms = "existed";
            request.setAttribute("error", ms);
            List<Category> listC = d.getAllCategory();
            request.setAttribute("listC", listC);
            request.getRequestDispatcher("edit_product.jsp").forward(request, response);
        } else {
            Product productUpdate = new Product(name, des, price,quantity, image, cateID);
            d.editProduct(productID, productUpdate);
                    
            Specifications productDetail=new Specifications(screen, cpu, ram, storage);
            d.editProductDetail(productID, productDetail);

            // lay ra danh sach san pham
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
