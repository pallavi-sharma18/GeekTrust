package com.geektrust.backend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.Repository.PlansRepository;
import com.geektrust.backend.Repository.TopUpRepository;
import com.geektrust.backend.Repository.UserRepository;
import com.geektrust.backend.entities.Plans;
import com.geektrust.backend.entities.SubscriptionStartDate;
import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.UserPlans;
import com.geektrust.backend.entities.UserTopup;

public class UserService {

    private final UserRepository userRepository;
    private final PlansRepository plansRepository;
    private final TopUpRepository topupRepository;

    public UserService(UserRepository userRepository,PlansRepository plansRepository,TopUpRepository topupRepository) {
        this.userRepository = userRepository;
        this.plansRepository = plansRepository;
        this.topupRepository = topupRepository;

    }
   // Store the subscription plan chosen by user in the user data repository
    public void createPlans(String category,String plan_name){
        String subscription_start_date = userRepository.findStartDate();
        if(subscription_start_date == null){
            System.out.println("ADD_SUBSCRIPTION_FAILED INVALID_DATE");    
        }else{
        final UserPlans plan = new UserPlans(category, plan_name);
       // Throw an error if plan for the same category is already added
       List<UserPlans> activePlans = userRepository.findAllPlans();
        Boolean isPresent = activePlans.stream()
                   .anyMatch(c -> c.getCategory().equals(category));
        
        if(isPresent == true){
            System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");

        }else{
            userRepository.save(plan);
        }
    }
       
    }
    // Store the top up plan chosen by user in the user data repository

    public void createTopup(String devices,Integer months){

        String subscription_start_date = userRepository.findStartDate();
        if(subscription_start_date == null){
            System.out.println("ADD_TOPUP_FAILED INVALID_DATE");    
        }else{
        // if subscriptions not found then do not add top up
        List<UserPlans> allPlans = userRepository.findAllPlans();
        if(allPlans.isEmpty() == true){
            System.out.println("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND");
        }
        else{

        final UserTopup topup = new UserTopup(devices,months);
        // If Top Up plan is already present throw below error
        if(userRepository.findAllTopup().isEmpty() == false){
            System.out.println("ADD_TOPUP_FAILED DUPLICATE_TOPUP");

        }else{
            userRepository.save(topup);
        }
    }
}
}
    // Add the Subscription Start Date
    public void addStartdate(String start_date){
        final SubscriptionStartDate addStartDate = new SubscriptionStartDate(start_date);
        userRepository.save(addStartDate);

    }

    // Calculate and fetch the renewal details
    public List<ArrayList<String>> renewalDetails(){
        // get all the user active plans
        List<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        String subscription_start_date = userRepository.findStartDate();
        if(subscription_start_date == null){
            System.out.println("SUBSCRIPTIONS_NOT_FOUND");
        }
        else{ 
            List<UserPlans> allPlans = userRepository.findAllPlans();
            if(allPlans.isEmpty()){
                System.out.println("SUBSCRIPTIONS_NOT_FOUND");  
            }else{
              return  fetchRegisteredPlans(output,subscription_start_date,allPlans);
            }
        } 
        
        return output;
       
    }

        // **************** Fetch Registered Plans **********************

    public  List<ArrayList<String>> fetchRegisteredPlans(List<ArrayList<String>> output,  String subscription_start_date, List<UserPlans> allPlans){
       
        // get the pricing of all the plans
        List<Plans> planPricing = plansRepository.findAllPlansPricing();

        // Total Renewal Amount
        Integer renewal_amount = 0;

         // For loop for iterating over the List
         for (int i = 0; i < allPlans.size(); i++) {
             String category = allPlans.get(i).getCategory();
             String plan_name = allPlans.get(i).getPlan_name();

              // **********  get months *************
              List<Plans> plan = planPricing
                .stream()
                .filter(c -> c.getCategory().equals(category) && c.getPlan_name().equals(plan_name))
                .collect(Collectors.toList());

                String renewal_date_final = getRenewalDate(plan, subscription_start_date);

               // ************* get pricing *****************
                Integer price = plan.get(0).getPlan_price();
                renewal_amount = renewal_amount + price;

                // Add the reminder details in a list of lists.
               output.add(new ArrayList<>(List.of("RENEWAL_REMINDER",category,renewal_date_final)));

        }
              return fetchRegisteredTopUp(output, renewal_amount);
        }


        // ************ Get renewal Date ****************
        public String getRenewalDate( List<Plans> plan, String subscription_start_date){
            DateTimeFormatter Pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Long month = Long.valueOf(plan.get(0).getPlan_month());
            // ************. get the renewal date ****************
              LocalDate date = LocalDate.parse(subscription_start_date, Pattern);
              LocalDate date2 = date.plusMonths(month);
              LocalDate renewal_date = date2.minusDays(10);
              String renewal_date_final = Pattern.format(renewal_date).toString();

            return renewal_date_final;

        }



        // ***************** Fetch Registered TopUp ***********************
        public  List<ArrayList<String>> fetchRegisteredTopUp(List<ArrayList<String>> output, Integer renewal_amount){
            List<UserTopup> allTopup = userRepository.findAllTopup();        
            // get the pricing of all the plans
            List<TopUp> topupPricing = topupRepository.findAllTopupPricing();

               // *********** Add renewal amount ***************
        if(allTopup.size()>0){

        
            // TOP UP NAME   
            String devices = allTopup.get(0).getDevices();
            // NO OF MONTHS
            Integer months = allTopup.get(0).getMonths();
    
            List<TopUp> plan = topupPricing
            .stream()
            .filter(c -> c.getDevices().equals(devices))
            .collect(Collectors.toList());
    
            // Total Renewal amount
             Integer topup_amount = months * (plan.get(0).getPrice()); 
             renewal_amount = renewal_amount + topup_amount;
    
            }

              // Add the renewal amount in output list
         output.add(new ArrayList<>(List.of("RENEWAL_AMOUNT",Integer.toString(renewal_amount))));
         return output;  

        }
    
}

   

