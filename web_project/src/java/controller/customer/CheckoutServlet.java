/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

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
import model.AccountDetail;
import model.Cart;
import model.Email;
import model.Item;

/**
 *
 * @author PV
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (!Authorization.isCustomer(acc)) {
            Authorization.redirectToHome(session, response);
        } else {
            Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (Cart) o;
            } else {
                cart = new Cart();
            }
            DAO d = new DAO();
            Account a = (Account) session.getAttribute("acc");
            List<Item> list = cart.getItems();

            if (a == null) {
                response.sendRedirect("login.jsp");
            } else {
                d.addOrder(a, cart);
                
                Email e = new Email();
                AccountDetail ad = d.getProfile(a);

                e.sendEmail(ad.getEmail(), "Your order successfully", " <!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "\n"
                        + "<h3 style=\"color:pink;\">HongApple store thanks you for your order </h3>\n"
                        + "<h4>The amount you have to pay : $" + session.getAttribute("total") + "</h4>\n"
                        + "\n"
                        + "</body>\n"
                        + "</html>");
                session.removeAttribute("cart");
                session.setAttribute("total", 0);
                session.setAttribute("size", 0);
                response.sendRedirect("home");
            }
        }

    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
