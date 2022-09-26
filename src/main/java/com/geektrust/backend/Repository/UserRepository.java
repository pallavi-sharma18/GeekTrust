package com.geektrust.backend.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.SubscriptionStartDate;
import com.geektrust.backend.entities.UserPlans;
import com.geektrust.backend.entities.UserTopup;

public class UserRepository {

    private final Map<String,UserPlans> UserPlanMap;
    private final Map<String,UserTopup> UserTopupMap;
    private final List<String> subscription_start_date;
    //StringBuilder str = new StringBuilder();
    private Integer autoIncrement = 0;

    public UserRepository(){
        this.UserPlanMap = new HashMap<String,UserPlans>();
        this.UserTopupMap = new HashMap<String,UserTopup>();
        this.subscription_start_date = new ArrayList<>();
    }

    public UserRepository(Map<String,UserPlans> UserPlanMap,Map<String,UserTopup> UserTopupMap,List<String> subscription_start_date){
        this.UserPlanMap = UserPlanMap;
        this.UserTopupMap = UserTopupMap;
        this.subscription_start_date = subscription_start_date;
        this.autoIncrement = UserPlanMap.size();
    }
    // method to save the Subscription Plan Choosen by User
    public void save(UserPlans entity){
        if( entity.getId() == null ){
            autoIncrement++;
            UserPlans p = new UserPlans(Integer.toString(autoIncrement),entity.getCategory(),entity.getPlan_name());
            UserPlanMap.put(p.getId(),p);
        }
        else{
            UserPlanMap.put(entity.getId(),entity);
        }
           
    }
    // method to save the Top Up Plan choosen by User
    public void save(UserTopup entity){
        if( entity.getId() == null ){
            autoIncrement++;
            UserTopup p = new UserTopup(Integer.toString(autoIncrement),entity.getDevices(),entity.getMonths());
            UserTopupMap.put(p.getId(),p);
        }
        else{
            UserTopupMap.put(entity.getId(),entity);
        }
            
    }

    // method to save the Subscription Date
    public void save(SubscriptionStartDate add_start_date){
        subscription_start_date.add(add_start_date.getSubscription_start_date());
    }

    // get subscription plan details
    public List<UserTopup> findAllTopup() {
        List<UserTopup> containsAllUserList = UserTopupMap
        .entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
        return containsAllUserList;
   
    }

    // get top up plans details
    public List<UserPlans> findAllPlans() {
        List<UserPlans> containsAllUserList = UserPlanMap
        .entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
        return containsAllUserList;
   
    }

    // method to fetch the Subscription start date
    public String findStartDate(){
        if(subscription_start_date.size()>0){
            return subscription_start_date.get(0);
        }
        else{
            return null;
        }
        
       
    }





    
}
