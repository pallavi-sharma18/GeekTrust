package com.geektrust.backend.entities;

public class TopUp extends BaseEntity {
    // Instance variables
    private final String devices;
    private final Integer months;
    private final Integer price;

    // CHAINING Constructor
    public TopUp(String id,String devices, Integer months, Integer price){
       
        this(devices,months,price);
        this.id = id;
    }
   
    public TopUp(String devices, Integer months, Integer price){
        this.devices = devices;
        this.months = months;
        this.price = price;

    }

    // Getters

    public Integer getPrice() {
        return price;
    }
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
        TopUp other = (TopUp) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // To display in console
    // @Override
    // public String toString() {
    //     return "Available TopUp Plans [id=" + id +  ", Devices=" + devices  + ", Months=" + months + ", Price=" + price + "]";
    // }

    @Override
    public String toString() {
        return  id +  " " + devices  + " " + months + " " + price ;
    }
    
    
}
