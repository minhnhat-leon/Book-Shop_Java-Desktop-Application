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
import nhatbpm.db.MyConnection;
import nhatbpm.dtos.UserDetailsDTO;

/**
 *
 * @author NhatBPM;
 */
public class UserDetailsDAO {
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
    
    public UserDetailsDAO() {
    }
    
    public String checkLogin(String userId, String password) throws ClassNotFoundException, SQLException{
         try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select Role From UserDetails Where UserID = ? AND Password = ? AND Status = 'true'";
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, userId);
                pStmt.setString(2, password);
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    boolean role = rs.getBoolean("Role");
                    if (role) {
                        return "true";
                    }
                    return "false";
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    
    public UserDetailsDTO getUserDetails(String userID) 
            throws SQLException, ClassNotFoundException {
        UserDetailsDTO user = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select * From UserDetails Where UserID = ? AND Status = 'True'";
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, userID);
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    String userId = rs.getString("UserID");
                    String password = rs.getString("Password");
                    String name = rs.getString("Name");
                    String phone = rs.getString("Phone");
                    String address = rs.getString("Address");
                    boolean role = rs.getBoolean("Role");
                    user = new UserDetailsDTO(userId, password, name, phone, address, role);
                }
            }
        } finally {
            closeConnection();
        }
        return user;
    }
    
    public boolean updateUser(String userID, String name, String phone, String address) throws SQLException, ClassNotFoundException{
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Update UserDetails Set Name = ?, Phone = ?,  Address = ? Where UserID = ?";
                pStmt = con.prepareStatement(sql);
                pStmt.setString(4, userID);
                pStmt.setString(1, name);
                pStmt.setString(2, phone);
                pStmt.setString(3, address);
                pStmt.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
