/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatbpm.dtos;

import java.util.Vector;

/**
 *
 * @author NhatBPM;
 */
public class BookDTO {
    private String BookID;
    private String title;
    private String description;
    private String author;
    private String image;
    private int price;
    private String categoryName;
    private int quantity;
    private String importDate;
      
    public BookDTO() {
    }

    public BookDTO(String BookID, String title, String description, String author, String image, int price, String categoryID, int quantity, String importDate) {
        this.BookID = BookID;
        this.title = title;
        this.description = description;
        this.author = author;
        this.image = image;
        this.price = price;
        this.categoryName = categoryID;
        this.quantity = quantity;
        this.importDate = importDate;
    }

    /**
     * @return the BookID
     */
    public String getBookID() {
        return BookID;
    }

    /**
     * @param BookID the BookID to set
     */
    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the categoryID
     */
    public String getCategoryID() {
        return categoryName;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(String categoryID) {
        this.categoryName = categoryID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the importDate
     */
    public String getImportDate() {
        return importDate;
    }

    /**
     * @param importDate the importDate to set
     */
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }
    
    public Vector toVector() {
        Vector vector = new Vector();
        vector.add(this.BookID);
        vector.add(this.title);
        vector.add(this.description);
        vector.add(this.price);
        vector.add(this.author);
        vector.add(this.categoryName);
        vector.add(this.image);
        return vector;
    }
    
}
