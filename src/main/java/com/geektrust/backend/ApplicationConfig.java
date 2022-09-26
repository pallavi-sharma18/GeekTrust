package com.geektrust.backend;

import com.geektrust.backend.Repository.PlansRepository;
import com.geektrust.backend.Repository.TopUpRepository;
import com.geektrust.backend.Repository.UserRepository;
import com.geektrust.backend.commands.AddSubscription;
import com.geektrust.backend.commands.AddTopUp;
import com.geektrust.backend.commands.LoadDataCommand;
import com.geektrust.backend.commands.PrintRenewalCommand;
import com.geektrust.backend.services.LoadDataService;
import com.geektrust.backend.services.UserService;

public class ApplicationConfig {

    // Repository
    private final PlansRepository plansRepository = new PlansRepository();
    private final TopUpRepository topUpRepository = new TopUpRepository();
    private final UserRepository  userRepository = new UserRepository();

    // Services

    private final LoadDataService loadDataService = new LoadDataService(plansRepository, topUpRepository);
    private final UserService userService = new UserService(userRepository, plansRepository, topUpRepository);

    // Commands

    private final LoadDataCommand loadDataCommand = new LoadDataCommand(loadDataService, userService);
    private final AddSubscription addSubscription = new AddSubscription(userService);
    private final AddTopUp addTopup = new AddTopUp(userService);
    private final PrintRenewalCommand printRenewalCommand = new PrintRenewalCommand(userService);

    // Command Invoker
    private final commandInvoker CommandInvoker = new commandInvoker();

    public commandInvoker getCommandInvoker(){
        CommandInvoker.register("START_SUBSCRIPTION",loadDataCommand);
        CommandInvoker.register("ADD_SUBSCRIPTION",addSubscription);
        CommandInvoker.register("ADD_TOPUP",addTopup);
        CommandInvoker.register("PRINT_RENEWAL_DETAILS",printRenewalCommand);
    
        return CommandInvoker;
    }







    
}
