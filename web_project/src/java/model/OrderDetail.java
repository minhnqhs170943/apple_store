package model;

/**
 *
 * @author havie
 */
public class OrderDetail {

    private int ID;
    private int orderID;
    private int productID;
    private double productPrice;
    private String productName;
    private int quantity;

    // Constructors
    public OrderDetail() {
    }

    public OrderDetail(double productPrice, String productName, int quantity) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.quantity = quantity;
    }

    
    
    

    public OrderDetail(int ID, int orderID, int productID, String productName, int quantity, double productPrice) {
        this.ID = ID;
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public OrderDetail(int orderID, int productID, String productName, int quantity, double productPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "ID=" + ID + ", orderID=" + orderID + ", productID=" + productID + ", productPrice=" + productPrice + ", productName=" + productName + ", quantity=" + quantity + '}';
    }

 

}
