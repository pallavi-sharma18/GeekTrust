package com.geektrust.backend.services;

import com.geektrust.backend.Repository.PlansRepository;
import com.geektrust.backend.Repository.TopUpRepository;
import com.geektrust.backend.entities.Plans;
import com.geektrust.backend.entities.TopUp;

public class LoadDataService {

    private final PlansRepository plansRepository;
    private final TopUpRepository topUpRepository;

    public LoadDataService(PlansRepository plansRepository, TopUpRepository topUpRepository) {
        this.plansRepository = plansRepository;
        this.topUpRepository = topUpRepository;
    }

    // Store Available Subscription Plan provided by DoreMi
    public Plans create(String category ,String plan_name, Integer plan_month,Integer plan_price) {
        final Plans plan = new Plans(category, plan_name,plan_month,plan_price);
          return plansRepository.save(plan);
       }

    // Store Available TopUp Plan provided by DoReMi  
       public void create(String devices,Integer months,Integer price) {
        final TopUp topup = new TopUp(devices, months,price);
          topUpRepository.save(topup);
       }
    
}
