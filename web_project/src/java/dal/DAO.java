/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.oracle.wls.shaded.org.apache.bcel.generic.DADD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.AccountDetail;
import model.Cart;
import model.Category;
import model.Color;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.Specifications;

/**
 *
 * @author PV
 */
public class DAO extends DBContext {
    
    public List<Product> getAllProduct() {
        String sql = "select * from Products";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }
    
    public List<Account> getAllAccount() {
        String sql = "select * from Users where is_admin=0";
        List<Account> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt("user_id"), rs.getString("username")));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //lay tong tien theo Category
    public Double getAmountByCate(String cate) {
        String sql = "SELECT SUM(OD.quantity * OD.price_per_unit) AS total_amount\n"
                + "FROM OrderDetails OD\n"
                + "INNER JOIN Products P ON OD.product_id = P.product_id\n"
                + "INNER JOIN Category C ON P.c_id = C.category_id\n"
                + "WHERE C.cname = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cate);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return null;
        
    }
    //lay tong tien theo thang

    public Double getAmountByMonth(int month) {
        String sql = "SELECT\n"
                + "    SUM(total_amount) AS TotalAmount\n"
                + "FROM Orders\n"
                + "WHERE MONTH(order_date) = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return null;
        
    }

    //tong doanh thu
    public Double getTotalAmount() {
        String sql = "SELECT SUM(total_amount) FROM Orders";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return null;
        
    }

    // hàm thêm moi 1 san pham
    public void addProduct(Product product) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        String sql = "INSERT INTO Products (name, description, price,Quantity, url_img, c_id, release_date) VALUES (?, ?, ?,?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getPrice());
            st.setDouble(4, product.getQuantity());
            st.setString(5, product.getUrl_img());
            st.setInt(6, product.getCategoryID());
            st.setString(7, date);
            
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     //  sua chi tiet san pham
    public void editProductDetail(int productID, Specifications updatedDetail) {
        String sql = "UPDATE ProductSpecifications SET screen_size = ?,cpu = ?, ram = ?,storage_capacity=? WHERE product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, updatedDetail.getScreen());
            st.setString(2, updatedDetail.getCpu());
            st.setString(3, updatedDetail.getRam());
            st.setString(4, updatedDetail.getStorage());
            st.setInt(5, productID);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  sua san pham
    public void editProduct(int productID, Product updatedProduct) {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?,Quantity=?,url_img=? ,c_id = ? WHERE product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, updatedProduct.getName());
            st.setString(2, updatedProduct.getDescription());
            st.setDouble(3, updatedProduct.getPrice());
            st.setInt(4, updatedProduct.getQuantity());
            st.setString(5, updatedProduct.getUrl_img());
            st.setInt(6, updatedProduct.getCategoryID());
            st.setInt(7, productID);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hàm xóa san pham
    public void deleteProduct(int productID) {
        try {
            // Cập nhật các mặt hàng liên quan trong bảng OrderItems, đặt ProductID thành null
            String updateOrderItemsSql = "UPDATE OrderDetails SET product_id = NULL WHERE product_id = ?";
            PreparedStatement updateOrderItemsPs = connection.prepareStatement(updateOrderItemsSql);
            updateOrderItemsPs.setInt(1, productID);
            updateOrderItemsPs.executeUpdate();
            
            String sql = "DELETE FROM ProductColor WHERE product_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.executeUpdate();
            
            String sql1 = "DELETE FROM ProductSpecifications WHERE product_id = ?";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, productID);
            ps1.executeUpdate();
            

            // Xóa sản phẩm từ bảng Products
            String deleteProductSql = "DELETE FROM Products WHERE product_id = ?";
            PreparedStatement deleteProductPs = connection.prepareStatement(deleteProductSql);
            deleteProductPs.setInt(1, productID);
            deleteProductPs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     // hàm xóa san pham
    public void deleteAccount(int userid) {
        try {
            // Cập nhật các mặt hàng liên quan trong bảng Order, đặt uid thành null
            String updateOrderSql = "UPDATE Orders SET user_id = NULL WHERE user_id = ?";
            PreparedStatement updateOrder = connection.prepareStatement(updateOrderSql);
            updateOrder.setInt(1, userid);
            updateOrder.executeUpdate();
            
            String sql = "DELETE FROM UserDetails WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userid);
            ps.executeUpdate();

            // Xóa account
            String deleteAccountSql = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement deleteAccount = connection.prepareStatement(deleteAccountSql);
            deleteAccount.setInt(1, userid);
            deleteAccount.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lay san pham moi nhat
    public List<Product> getNewProduct() {
        String sql = "select top 8 * from Products order by release_date desc";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //lay san pham best seller
    public List<Product> getBestProduct() {
        String sql = "select top 8 * from Products order by Quantity";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //lay san pham hot theo cid
    public List<Product> getBestProductByCid1(String cid) {
        String sql = "select * from Products where c_id=? order by Quantity OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //lay san pham hot theo cid
    public List<Product> getBestProductByCid2(String cid) {
        String sql = "select * from Products where c_id=? order by Quantity OFFSET 3 ROWS FETCH NEXT 3 ROWS ONLY";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    // lay color cua san pham 
    public List<Color> getColor(String name) {
        String sql = "SELECT C.color_id, C.name\n"
                + "FROM Color C\n"
                + "INNER JOIN ProductColor PC ON C.color_id = PC.color_id\n"
                + "INNER JOIN Products P ON PC.product_id = P.product_id\n"
                + "WHERE P.name = ?";
        List<Color> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name); // Sử dụng setString để truyền tham số name
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Color(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            
        }
        return list;
    }

    //lay san pham moi nhat
    public List<Product> getNewProductByCid(String cid) {
        String sql = "select top 5 * from Products where c_id=? order by release_date desc";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    // lay san pham tuong tu
    public List<Product> getRelatedProduct(Product product) {        
        String sql = "select top 5 * from Products where c_id=? order by price desc";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product.getCategoryID());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //lay tat ca Category
    public List<Category> getAllCategory() {
        String sql = "select * from Category";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //đưa về sản phẩm theo category
    public List<Product> getProductByCID(String cid) {
        String sql = "select * from Products where c_id=?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //search theo gia
    public List<Product> getProductByPrice(double from, double to) {
        String sql = "select * from Products where price between ? and ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, from);
            st.setDouble(2, to);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
        
    }

    //search theo check
    public List<Product> searchByCheck(int[] cid) {
        String sql = "select * from Products where 1=1";
        if (cid != null && cid[0] != 0) {
            sql += " and c_id in(";
            for (int i = 0; i < cid.length; i++) {
                sql += cid[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    //vao detail san phảm
    public Product getProductByID(String id) {
        String sql = "select * from Products where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(7));
            }
        } catch (SQLException e) {
        }
        return null;
        
    }
    
    //lay san pham theo ten
    public Product getProductByName(String name) {
        String sql = "select * from Products where name=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(7));
            }
        } catch (SQLException e) {
        }
        return null;
        
    }
    //them chi tiet san pham cho san pham moi
    public void addProductDetail(Product product,String scr,String cpu,String ram,String sto) {
        String sql = "INSERT INTO ProductSpecifications (product_id, screen_size, cpu, ram, storage_capacity)"
                + " VALUES(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product.getId());
            st.setString(2,scr);
            st.setString(3,cpu);
            st.setString(4,ram);
            st.setString(5,sto); 
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lay thong so chi tiet san pham
    public Specifications getProductDetail(String id) {
        String sql = "select * from ProductSpecifications where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Specifications(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
        }
        return null;
        
    }

    //Search theo ten
    public List<Product> searchByName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
            
        } catch (SQLException e) {
        }
        
        return list;
    }

    //lay user
    public Account login(String user, String pass) {
        String sql = "select * from Users where username=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt(1), user, pass, rs.getBoolean(4));
            }
            
        } catch (Exception e) {
        }
        return null;
    }

    //check tai khoan
    public Account checkAccount(String user) {
        String sql = "select * from Users where username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), user, rs.getString(3), rs.getBoolean(4));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void signup(String user, String pass) {
        String sql = "insert into Users values(?,?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setBoolean(3, false);
            st.executeUpdate();
            String sql1="select * from Users where username=?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, user);
            ResultSet rs=st1.executeQuery();
            if(rs.next()){
            int uid=rs.getInt(1);
            String sql2="INSERT INTO UserDetails VALUES (?, NULL, NULL, NULL, NULL, NULL, NULL)";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, uid);
            st2.executeUpdate();
            }
            
        } catch (Exception e) {
        }
    }

    //phan trang
    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    //them order
    public void addOrder(Account a, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into Orders values(?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getUser_id());
            st.setString(2, date);
            st.setDouble(3, cart.getTotalMoney());
            st.executeUpdate();
            //lay id cua Order vua add
            String sql1 = "select top 1 order_id from Orders order by order_id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into OrderDetails values(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();
                }
            }
            // cap nhat so luong
            String sql3 = "update Products set Quantity=Quantity-? where product_id=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }
            
        } catch (Exception e) {
        }
        
    }


    // lay ra tat ca nhung don hang
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String xSql = "SELECT o.*, u.username\n"
                + "FROM Orders o\n"
                + "JOIN Users u ON o.user_id = u.user_id;  ";
        
        try {
            Order order;
            PreparedStatement st = connection.prepareStatement(xSql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int odid = rs.getInt("order_id");
                int uid = rs.getInt("user_id");
                String uname = rs.getString("username");
                
                String orderDate = rs.getString("order_date");
                double totalAmount = rs.getDouble("total_amount");
                order = new Order(odid, orderDate, uname, uid, totalAmount);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return orderList;
    }
    
    public List<Order> getMyOrderByUID(int userId) {
        List<Order> orderList = new ArrayList<>();
        String xSql = "SELECT o.*, u.username\n"
                + "FROM Orders o\n"
                + "JOIN Users u ON o.user_id = u.user_id\n"
                + "WHERE o.user_id = ?";
        
        try {
            Order order;
            PreparedStatement st = connection.prepareStatement(xSql);
            st.setInt(1, userId); // Đặt giá trị tham số user_id
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int odid = rs.getInt("order_id");
                int uid = rs.getInt("user_id");
                String uname = rs.getString("username");
                
                String orderDate = rs.getString("order_date");
                double totalAmount = rs.getDouble("total_amount");
                order = new Order(odid, orderDate, uname, uid, totalAmount);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return orderList;
    }
    
    public static void main(String[] args) {
        DAO d = new DAO();
        System.out.println(d.getOdDetailByOdId(5));
    }

    // lay ra thong tin cua don hàng
    public List<OrderDetail> getOdDetailByOdId(int odId) {
        List<OrderDetail> List = new ArrayList<>();
        String xSql = "select odd.*, p.Name from OrderDetails odd join Products p\n"
                + "                              on odd.product_id = p.product_id where odd.order_id = ?";
        try {
            OrderDetail detail;
            PreparedStatement st = connection.prepareStatement(xSql);
            st.setInt(1, odId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String name = rs.getString("Name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price_per_unit");
                detail = new OrderDetail(price, name, quantity);
                List.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return List;
    }

    //lay profile
    public AccountDetail getProfile(Account a) {
        String sql = "select * from UserDetails where user_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getUser_id());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new AccountDetail(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //lay profile 2
    public AccountDetail getProfile2(String id) {
        String sql = "select * from UserDetails where user_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new AccountDetail(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    // doi mk
    public void changepass(Account a, String newpass) {
        String sql = "UPDATE Users SET password = ? WHERE user_id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpass);
            st.setInt(2, a.getUser_id());
            st.executeUpdate();
            
        } catch (Exception e) {
        }
    }
    
    public void changeprofile(Account a, String first_name, String last_name, String email, String phone, String address) {
        String sql = "UPDATE UserDetails SET first_name = ?,last_name =?,email=?,phone=?,address=? WHERE user_id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, first_name);
            st.setString(2, last_name);
            st.setString(3, email);
            st.setString(4, phone);
            st.setString(5, address);
            st.setInt(6, a.getUser_id());
            st.executeUpdate();
            
        } catch (Exception e) {
        }
    }
    
}
