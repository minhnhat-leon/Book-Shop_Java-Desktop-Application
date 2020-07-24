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
public class OrderDTO {
    private String userId;
    private String bookId;
    private int amount;
    private String date;
    
    public OrderDTO() {
    }

    public OrderDTO(String userId, String bookId, int amount, String date) {
        this.userId = userId;
        this.bookId = bookId;
        this.amount = amount;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
