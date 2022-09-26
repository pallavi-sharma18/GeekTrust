package com.geektrust.backend.commands;

/* Add Subscription Command will add the subscription for the user */

import java.util.List;
import com.geektrust.backend.services.UserService;

public class AddSubscription implements ICommand {

    // Define service to be executed
    private final UserService userService;

    public AddSubscription(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens){
        String category = tokens.get(1);
        String plan_name = tokens.get(2);
        userService.createPlans(category, plan_name);
        
    }
    
}
