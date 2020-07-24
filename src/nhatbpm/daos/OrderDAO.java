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
import nhatbpm.dtos.OrderDTO;

/**
 *
 * @author NhatBPM;
 */
public class OrderDAO {
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
    
    public boolean insertItem(String userID, String bookID, int amount, String date) 
            throws ClassNotFoundException, SQLException{
        String sql = "Insert Into OrderDetails "
                        + "Values(?,?,?,?)";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, userID);
                pStmt.setString(2, bookID);
                pStmt.setInt(3, amount);
                pStmt.setString(4, date);
                pStmt.executeUpdate();
                return true;
                
            }//end when con = null
        } finally {
            closeConnection();
        }
        return false;
    }    
    
    public ArrayList<CartDTO> getListItems(String userID) 
            throws ClassNotFoundException, SQLException{
        String sql = "Select UserID, BookID, Quantity, OrderDate "
                        + "From OrderDetails "
                        + "Where UserID = ? ";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, userID);
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
    
    public ArrayList<String> getDateList(String userID)
            throws ClassNotFoundException, SQLException{
        String sql = "Select OrderDate "
                        + "From OrderDetails "
                        + "Where UserID = ? " 
                        + "Group By OrderDate";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, userID);
                rs = pStmt.executeQuery();
                ArrayList<String> listItems = new ArrayList<>();
                while (rs.next()) {
                    String oderDate = rs.getString("OrderDate");
                    listItems.add(oderDate);                     
                    
                }
                return listItems;
            }//end when con = null
        } finally {
            closeConnection();
        }
        return null;
        
    }
}
