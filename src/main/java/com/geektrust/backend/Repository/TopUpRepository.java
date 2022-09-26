package com.geektrust.backend.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.TopUp;

public class TopUpRepository {

    private final Map<String,TopUp> TopUpMap;
    private Integer autoIncrement = 0;

    public TopUpRepository(){
        this.TopUpMap = new HashMap<String,TopUp>();
    }

    public TopUpRepository(Map<String,TopUp> TopUpMap){
        this.TopUpMap = TopUpMap;
        this.autoIncrement = TopUpMap.size();
    }
    // method to save data in Plan Map
    public TopUp save(TopUp entity){
        if( entity.getId() == null ){
            autoIncrement++;
            TopUp p = new TopUp(Integer.toString(autoIncrement),entity.getDevices(),entity.getMonths(),entity.getPrice());
            TopUpMap.put(p.getId(),p);
            return p;
        }else{
            TopUpMap.put(entity.getId(),entity);   
            return entity;
        }
            
    }
    // method to fetch all the available topup plans
    public List<TopUp> findAllTopupPricing() {
        List<TopUp> containsAllUserList = TopUpMap
        .entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
        return containsAllUserList;
   
    }
    
}
