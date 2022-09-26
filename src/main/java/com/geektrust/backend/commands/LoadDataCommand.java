package com.geektrust.backend.commands;

/* Load the subscription and Top Up details provided by DoReMi including pricing in the repository */
/* Validate the subscription start date */
/* Store the date in User Repository */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.entities.Plans;
import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.services.LoadDataService;
import com.geektrust.backend.services.UserService;

public class LoadDataCommand implements ICommand{

    // Define Service to be executed
    private final LoadDataService loadDataService;
    private final UserService userService;

    public LoadDataCommand(LoadDataService loadDataService, UserService userService) {
        this.loadDataService = loadDataService;
        this.userService = userService;
    }
   // load plans.csv and topup.csv data in data repository
    public void loadData() { 
    ArrayList<Plans> list = new ArrayList<>();

    list.add(new Plans("MUSIC","FREE",1,0));
    list.add(new Plans("MUSIC","PERSONAL",1,100));
    list.add(new Plans("MUSIC","PREMIUM",3,250));
    list.add(new Plans("VIDEO","FREE",1,0));
    list.add(new Plans("VIDEO","PERSONAL",1,200));
    list.add(new Plans("VIDEO","PREMIUM",3,500));
    list.add(new Plans("PODCAST","FREE",1,0));
    list.add(new Plans("PODCAST","PERSONAL",1,100));
    list.add(new Plans("PODCAST","PREMIUM",3,300));


    for (int i = 0; i < list.size(); i++) {

        Plans plan = list.get(i);

        String category = plan.getCategory();
        String plan_name = plan.getPlan_name();
        Integer plan_month = plan.getPlan_month();
        Integer plan_price = plan.getPlan_price();

        loadDataService.create(category, plan_name, plan_month, plan_price);
    }

     ArrayList<TopUp> list_topup = new ArrayList<>();

     list_topup.add(new TopUp("FOUR_DEVICE",1,50));
     list_topup.add(new TopUp("TEN_DEVICE",1,100));

     for (int i = 0; i < list_topup.size(); i++) {
  
        TopUp topUp = list_topup.get(i);

        String devices = topUp.getDevices();
        Integer months = topUp.getMonths();
        Integer price = topUp.getPrice();
             
        loadDataService.create(devices, months, price);
 
     }
    }

    // ************************ START SUBSCRIPTION ************************//
    @Override
    public void execute(List<String> tokens){
        // load the data first
        loadData();
        // Store the subscription start date
        String start_date = tokens.get(1);

        // Date Validation
        Boolean isValid = dateValidation(start_date);

        if(isValid == true){
            userService.addStartdate(start_date);
        }else{
            System.out.println("INVALID_DATE");
           // throw new InvalidDateException("INVALID_DATE");
        }
       
    }

    public boolean dateValidation(final String date){

        boolean valid = false;

        try {

          //  ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("d-M-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT)
                            
            );

            valid = true;

        } catch (DateTimeParseException e) {
           // e.printStackTrace();
            valid = false;
        }

        return valid;
    }
    
    
    
}
