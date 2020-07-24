/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatbpm.dtos;

/**
 *
 * @author NhatBPM;
 */
public class CartDTO {
    private String bookID;
    private int quantity;
    private String oderDate;
    
    public CartDTO() {
    }

    public CartDTO(String bookID, int quantity, String oderDate) {
        this.bookID = bookID;
        this.quantity = quantity;
        this.oderDate = oderDate;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOderDate() {
        return oderDate;
    }

    public void setOderDate(String oderDate) {
        this.oderDate = oderDate;
    }
    
    
    
    
}
