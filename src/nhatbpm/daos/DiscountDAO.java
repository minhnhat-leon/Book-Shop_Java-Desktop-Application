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
import nhatbpm.dtos.DiscountDTO;

/**
 *
 * @author NhatBPM;
 */
public class DiscountDAO {
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
    
    public ArrayList<DiscountDTO> getDiscountList() 
            throws SQLException, ClassNotFoundException {
        DiscountDTO dto = null;
        String sql = "Select DiscountID, DiscountPercent, Description, DateStart "
                        + "From Discount ";
        try {
            con = MyConnection.getMyConnection();
            ArrayList<DiscountDTO> list = new ArrayList<>();
            if (con != null) {
                pStmt = con.prepareCall(sql);
                rs = pStmt.executeQuery();
                
                while (rs.next()) {
                    String code = rs.getString("DiscountID");
                    float percent = rs.getFloat("DiscountPercent");
                    String discription = rs.getString("Description");
                    String dateStart = rs.getString("DateStart");
                    dto = new DiscountDTO(code, percent, discription, dateStart);
                    list.add(dto);
                }
                return list;
                
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    
    public void create(String discountID, float percent, String description, String date) 
            throws SQLException, ClassNotFoundException{
        String sql = "Insert Into Discount "
                        + "Values (?,?,?,?)";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareCall(sql);
                pStmt.setString(1, discountID);
                pStmt.setFloat(2, percent);
                pStmt.setString(3, description);
                pStmt.setString(4, date);
                pStmt.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
    
    public void update(String discountId, float precent, String description, String dateStar) 
            throws SQLException, ClassNotFoundException {
        DiscountDTO dto = null;
        String sql = "Update Discount "
                        + "Set DiscountPercent = ?, Description = ?, DateStart = ? "
                        + "Where DiscountID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareCall(sql);
                pStmt.setString(3, dateStar);
                pStmt.setFloat(1, precent);
                pStmt.setString(2, description);
                pStmt.setString(4, discountId);
                pStmt.executeUpdate();
                
            }
        } finally {
            closeConnection();
        }
    }
    
    public DiscountDTO getDiscount (String discountID) 
            throws SQLException, ClassNotFoundException {
        DiscountDTO dto = null;
        String sql = "Select DiscountPercent, Description, DateStart "
                        + "From Discount "
                        + "Where DiscountID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareCall(sql);
                pStmt.setString(1, discountID);
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    float percent = rs.getFloat("DiscountPercent");
                    String discription = rs.getString("Description");
                    String dateStart = rs.getString("DateStart");
                    dto = new DiscountDTO(discountID, percent, discription, dateStart);
                }
                
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean contains(String discountID) 
            throws SQLException, ClassNotFoundException {
        if (getDiscount(discountID) == null) {
            return false;
        }
        return true;
    }
    
    public boolean delete(String discountID) 
            throws SQLException, ClassNotFoundException {
        DiscountDTO dto = null;
        String sql = "Delete "
                        + "From Discount "
                        + "Where DiscountID = ?";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareCall(sql);
                pStmt.setString(1, discountID);
                pStmt.executeUpdate();
                return true; 
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
