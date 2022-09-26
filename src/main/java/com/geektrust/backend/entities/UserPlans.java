package com.geektrust.backend.entities;

public class UserPlans extends BaseEntity {

    // Instance variables
    private final String category;
    private final String plan_name;

    // Chaining Constructors
    public UserPlans(String id, String category, String plan_name) {
       this(category,plan_name);
       this.id = id;

    }

    public UserPlans(String category, String plan_name) {
        this.category = category;
        this.plan_name = plan_name;
    }

    // Getter
  
    public String getCategory() {
        return category;
    }
    public String getPlan_name() {
        return plan_name;
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
        UserPlans other = (UserPlans) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // Override toString to display on console
    // @Override
    // public String toString() {
    //     return "User Subscription Plans [id=" + id  + ", Category=" + category + ", Plan Name=" + plan_name  + "]";
    // }

    @Override
    public String toString() {
        return id  + " " + category + " " + plan_name ;
    }

    
    
}
