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

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/deleteproduct"})
public class DeleteProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (!Authorization.isAdmin(acc)) {
            Authorization.redirectToHome(session, response);
        } else {
            // xóa san pham
            int productID = Integer.parseInt(request.getParameter("ID"));
            DAO pd = new DAO();
            pd.deleteProduct(productID);

            // lay lai danh sách san pham
            
            List<Product> listP;
            listP = pd.getAllProduct();
            
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
            List<Product> list1 = pd.getListByPage(listP, start, end);

            
            request.setAttribute("listP", list1);
            request.setAttribute("page", page);
            request.setAttribute("num", num);

            request.getRequestDispatcher("list_product.jsp").forward(request, response);
        }

    }

}
