/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatbpm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nhatbpm.db.MyConnection;
import nhatbpm.dtos.BookDTO;

/**
 *
 * @author NhatBPM;
 */
public class BookDetailsDAO{
    private Connection con;
    private PreparedStatement pStmt;
    private ResultSet rs;
    
    private void closeConnection() 
            throws SQLException, ClassNotFoundException {
        if (con != null) {
            con.close();
        }
        if (pStmt != null) {
            pStmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
    
    private List<BookDTO> bookList;
    public List<BookDTO> getBookList() {
        return bookList;
    }
    public void loadBookList() 
            throws SQLException, ClassNotFoundException {
        String sql = "Select * "
                + "From BookDetails "
                + "Where Status = 'True'";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                rs = pStmt.executeQuery();
                
                while (rs.next()) {                    
                    String bookID = rs.getString("BookId");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String author = rs.getString("Author");
                    String image = rs.getString("Image");
                    int price = rs.getInt("Price");
                    String categoryName = rs.getString("CategoryName");
                    int quantity = rs.getInt("Quantity");
                    String importDate = rs.getString("ImportDate");
                    BookDTO dto = new BookDTO(bookID, title, description, author, image, price, categoryName, quantity, importDate);
                    
                    if (bookList == null) {
                    bookList = new ArrayList<>();
                    }
                    
                    bookList.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public BookDTO getBook(String bookID) 
            throws SQLException, ClassNotFoundException{
        String sql= "Select * "
                + "From BookDetails "
                + "Where BookID = ?";
        BookDTO dto = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, bookID);
                rs = pStmt.executeQuery();
                
                if (rs.next()) {                    
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String author = rs.getString("Author");
                    String image = rs.getString("Image");
                    int price = rs.getInt("Price");
                    String categoryName = rs.getString("CategoryName");
                    int quantity = rs.getInt("Quantity");
                    String importDate = rs.getString("ImportDate");
                    dto = new BookDTO(bookID, title, description, author, image, price, categoryName, quantity, importDate);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public void insert(String bookID, String title, String description, String author, String image, int price, String categoryName, int quantity, String importDate) 
            throws SQLException, ClassNotFoundException {
        String sql = "Insert Into BookDetails "
                        + "Values (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, bookID);
                pStmt.setString(2, title);
                pStmt.setString(3, description);
                pStmt.setString(4, author);
                pStmt.setString(5, image);
                pStmt.setInt(6, price);
                pStmt.setString(7, categoryName);
                pStmt.setInt(8, quantity);
                pStmt.setString(9, importDate);
                pStmt.setBoolean(10, true);
                pStmt.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
    
    public void update(String bookID, String title, String description, String author, String image, int price, String categoryName, int quantity, String importDate, boolean status) 
            throws SQLException, ClassNotFoundException {
        String sql = "Update BookDetails "
                        + "Set Title = ?, Description = ?, Author = ?, Image = ?, Price = ?, CategoryName = ?, Quantity = ?, ImportDate = ?, Status = ? "
                        + "Where BookID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, title);
                pStmt.setString(2, description);
                pStmt.setString(3, author);
                pStmt.setString(4, image);
                pStmt.setInt(5, price);
                pStmt.setString(6, categoryName);
                pStmt.setInt(7, quantity);
                pStmt.setString(8, importDate);
                pStmt.setBoolean(9, status);
                pStmt.setString(10, bookID);
                pStmt.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
    public void updateQuantity(String bookID, int quantity) 
            throws SQLException, ClassNotFoundException {
        String sql = "Update BookDetails "
                        + "Set Quantity = ? "
                        + "Where BookID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setInt(1, quantity);
                pStmt.setString(2, bookID);
                pStmt.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
}
