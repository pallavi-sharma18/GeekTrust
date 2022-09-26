package com.geektrust.backend.entities;

public class Plans extends BaseEntity{
    // class Plans extends i.e. INHERIT from parent class BaseEntity.
    // Instance variables
    // Achieved ENCAPSULATION and immutability by using private acces modifier
    private final String category;
    private final String plan_name;
    private final Integer plan_month;
    private final Integer plan_price;

   
    // Getters 
    // play an important role in retrieving the value of a variable outside the encapsulating class.
    public String getCategory() {
        return category;
    }
    public String getPlan_name() {
        return plan_name;
    }
    public Integer getPlan_month() {
        return plan_month;
    }
    public Integer getPlan_price() {
        return plan_price;
    }
  

    // Parameterized Constructor

    public Plans(String id, String category, String plan_name, Integer plan_month, Integer plan_price ){
        this.id = id;
        this.category = category;
        this.plan_name = plan_name;
        this.plan_month  = plan_month;
        this.plan_price = plan_price;

    }
    public Plans(String category, String plan_name, Integer plan_month, Integer plan_price ){
        this.category = category;
        this.plan_name = plan_name;
        this.plan_month  = plan_month;
        this.plan_price = plan_price;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plans other = (Plans) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // Use Override toString to print the output to console
    // @Override
    // public String toString() {
    //     return "Available Subscription Plans [id=" + id +  ", Category=" + category  + ", Plan Name=" + plan_name + ", Plan Month=" + plan_month +  ", Plan Price=" + plan_price + "]";
    // }

    @Override
    public String toString() {
        return id +  " " + category  + " " + plan_name + " " + plan_month +  " " + plan_price ;
    }
    
}
