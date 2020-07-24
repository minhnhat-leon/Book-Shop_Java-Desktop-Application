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
public class DiscountDTO {
    private String discountId;
    private float precent;
    private String description;
    private String dateStar;
    
    public DiscountDTO() {
    }

    public DiscountDTO(String discountId, float precent, String description, String dateStar) {
        this.discountId = discountId;
        this.precent = precent;
        this.description = description;
        this.dateStar = dateStar;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public float getPrecent() {
        return precent;
    }

    public void setPrecent(float precent) {
        this.precent = precent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStar() {
        return dateStar;
    }

    public void setDateStar(String dateStar) {
        this.dateStar = dateStar;
    }
    
    public Vector toVector() {
        Vector vector = new Vector();
        vector.add(this.discountId);
        vector.add(this.precent);
        vector.add(this.dateStar);
        return vector;
    }
    
    
    
}
