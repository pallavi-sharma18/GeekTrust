package com.geektrust.backend.entities;

public class UserTopup extends BaseEntity{

     // Instance variables
     private final String devices;
     private final Integer months;
 
     // Chaining Constructors
     public UserTopup(String id,String devices,Integer months) {
        this(devices,months);
        this.id = id;
     }
 
     public UserTopup(String devices,Integer months) {
        this.devices = devices;
        this.months = months;
     }
 
     // Getter
     public String getDevices() {
         return devices;
     }
     public Integer getMonths() {
         return months;
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
         UserTopup other = (UserTopup) obj;
         if (id == null) {
             if (other.id != null)
                 return false;
         } else if (!id.equals(other.id))
             return false;
         return true;
     }
 
     // Override toString to display on console
    //  @Override
    //  public String toString() {
    //      return "User TopUp Plans [id=" + id  + ", Devices=" + devices + ", Months=" + months + "]";
    //  }

     @Override
     public String toString() {
         return id  + " " + devices + " " + months ;
     }
    
     
}
