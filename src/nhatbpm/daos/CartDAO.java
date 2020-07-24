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
import nhatbpm.db.MyConnection;
import nhatbpm.dtos.CartDTO;

/**
 *
 * @author NhatBPM;
 */
public class CartDAO {
    Connection con;
    PreparedStatement pStmt;
    ResultSet rs;
    
    private void closeConnection() 
            throws SQLException, ClassNotFoundException{
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
    
    public boolean insertItem(String bookID, int amount, String date) 
            throws ClassNotFoundException, SQLException{
        String sql = "Insert into Cart "
                        + "Values(?,?,?)";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, bookID);
                pStmt.setInt(2, amount);
                pStmt.setString(3, date);
                int row = pStmt.executeUpdate();
                if (row >= 1) {
                    return true;
                }
            }//end when con = null
        } finally {
            closeConnection();
        }
        return false;
        
    }
    
    public boolean updateItem(String bookID, int amount) 
            throws ClassNotFoundException, SQLException{
        String sql = "Update Cart "
                        + "Set Quantity = ? "
                        + "Where BookID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setInt(1, amount);
                pStmt.setString(2, bookID);
                int row = pStmt.executeUpdate();
                if (row >= 1) {
                    return true;
                }
            }//end when con = null
        } finally {
            closeConnection();
        }
        return false;
        
    }
    
    public boolean clear() 
            throws ClassNotFoundException, SQLException{
        String sql = "Delete From Cart";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                int row = pStmt.executeUpdate();
                if (row >= 1) {
                    return true;
                }
            }//end when con = null
        } finally {
            closeConnection();
        }
        return false;
        
    }
    
    public boolean delete(String bookID) 
            throws ClassNotFoundException, SQLException{
        String sql = "Delete From Cart Where BookID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, bookID);
                int row = pStmt.executeUpdate();
                if (row >= 1) {
                    return true;
                }
            }//end when con = null
        } finally {
            closeConnection();
        }
        return false;
        
    }
    
    public CartDTO getItem(String bookID) 
            throws ClassNotFoundException, SQLException{
        String sql = "Select Quantity, OrderDate "
                        + "From Cart "
                        + "Where BookID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, bookID);
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    int amount = rs.getInt("Quantity");
                    String oderDate = rs.getString("OrderDate");
                    CartDTO dto = new CartDTO(bookID, amount, oderDate);
                    return dto;
                }
            }//end when con = null
        } finally {
            closeConnection();
        }
        return null;
        
    }
    
    public ArrayList<CartDTO> getListItems() 
            throws ClassNotFoundException, SQLException{
        String sql = "Select BookID, Quantity, OrderDate "
                        + "From Cart ";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                rs = pStmt.executeQuery();
                ArrayList<CartDTO> listItems = new ArrayList<>();
                while (rs.next()) {
                    String bookID = rs.getString("BookID");
                    int amount = rs.getInt("Quantity");
                    String oderDate = rs.getString("OrderDate");
                    CartDTO dto = new CartDTO(bookID, amount, oderDate);
                    listItems.add(dto);                     
                }
                return listItems;
            }//end when con = null
        } finally {
            closeConnection();
        }
        return null;
        
    }
    
    public boolean contains(String bookID) 
            throws ClassNotFoundException, SQLException{
        if (getItem(bookID) != null) {
            return true;
        }
        return false;
    }
    
    public int getCountItems() 
            throws ClassNotFoundException, SQLException{
        String sql = "Select *"
                        + "From Cart ";
        int countItems = 0;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                rs = pStmt.executeQuery();
                while (rs.next()) {;                     
                    countItems++;
                }
            }//end when con = null
        } finally {
            closeConnection();
        }
        return countItems;
        
    }
    
}
