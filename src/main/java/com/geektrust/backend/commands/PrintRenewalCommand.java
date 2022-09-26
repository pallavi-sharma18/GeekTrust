package com.geektrust.backend.commands;

/* Fetch the renewal details */

import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.services.UserService;

public class PrintRenewalCommand implements ICommand{

     // Define Service to be executed
     private final UserService userService;

    public PrintRenewalCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens){
        List<ArrayList<String>> result = userService.renewalDetails();
        int j=0;

        // Print details on the console
        for (int i = 0; i < result.size(); i++) {
            for (j = 0; j < result.get(i).size()-1; j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.print(result.get(i).get(j));
            System.out.println();
        }
    
    }

    
}
