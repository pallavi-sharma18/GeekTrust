package com.geektrust.backend.commands;

/* Add Top Up Command will add the top up for the user */

import java.util.List;
import com.geektrust.backend.services.UserService;

public class AddTopUp implements ICommand{
    // Define service to be executed
    private final UserService userService;

    public AddTopUp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens){
        String devices = tokens.get(1);
        Integer months = Integer.parseInt(tokens.get(2));
        userService.createTopup(devices, months);       
    }
    
    
}
