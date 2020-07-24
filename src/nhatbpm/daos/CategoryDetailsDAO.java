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
import nhatbpm.dtos.CategoryDetailsDTO;

/**
 *
 * @author NhatBPM;
 */
public class CategoryDetailsDAO {

    Connection con;
    PreparedStatement pStmt;
    ResultSet rs;
    
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
    
    public CategoryDetailsDAO() {
    }
    
    public CategoryDetailsDTO getCategoryDetails(String categoryName)
            throws SQLException, ClassNotFoundException {
        String sql = "Select * "
                + "From CategoryDetails "
                + "Where CategoryName = ?";
        CategoryDetailsDTO dto = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                pStmt.setString(1, categoryName);
                rs = pStmt.executeQuery();
                
                if (rs.next()) {                    
                    String description = rs.getString("Description");
                    
                    dto = new CategoryDetailsDTO(categoryName, description);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    private List<CategoryDetailsDTO> catList;
    public List<CategoryDetailsDTO> getcatList() {
        return catList;
    }
    public void loadCategoryList() 
            throws SQLException, ClassNotFoundException {
        String sql = "Select * "
                + "From CategoryDetails";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                pStmt = con.prepareStatement(sql);
                rs = pStmt.executeQuery();
                
                while (rs.next()) {                    
                    String categoryName = rs.getString("CategoryName");
                    String description = rs.getString("Description");
                    
                    CategoryDetailsDTO dto = new CategoryDetailsDTO(categoryName, description);
                    
                    if (catList == null) {
                    catList = new ArrayList<>();
                    }
                    
                    catList.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
}
