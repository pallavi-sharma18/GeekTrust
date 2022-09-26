package com.geektrust.backend.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Plans;

public class PlansRepository {
    private final Map<String,Plans> PlanMap;
    private Integer autoIncrement = 0;

    public PlansRepository(){
        this.PlanMap = new HashMap<String,Plans>();
    }

    public PlansRepository(Map<String,Plans> PlanMap){
        this.PlanMap = PlanMap;
        this.autoIncrement = PlanMap.size();
    }
    // method to save data in Plan Map
    public Plans save(Plans entity){
        if( entity.getId() == null ){
            autoIncrement++;
            Plans p = new Plans(Integer.toString(autoIncrement),entity.getCategory(),entity.getPlan_name(),entity.getPlan_month(),entity.getPlan_price());
            PlanMap.put(p.getId(),p);
            return p;
        }else{
            PlanMap.put(entity.getId(),entity);
            return entity;
        }
       
           
    }
   // method to fetch the Available Subscription Plans
    public List<Plans> findAllPlansPricing() {
        List<Plans> containsAllUserList = PlanMap
        .entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
        return containsAllUserList;
   
    }
    
}
