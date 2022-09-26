package com.geektrust.backend.RepositoryTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.Repository.UserRepository;
import com.geektrust.backend.entities.UserPlans;
import com.geektrust.backend.entities.UserTopup;

public class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){

        final Map<String,UserPlans> UserPlanMap = new HashMap<String,UserPlans>(){
            {
                put("1",new UserPlans("1", "VIDEO", "PREMIUM"));
                put("2",new UserPlans("2", "PODCAST", "PREMIUM"));
           
            }
        };

        final Map<String,UserTopup> UserTopUpMap = new HashMap<String,UserTopup>(){
            {
                put("1",new UserTopup("1", "FOUR_DEVICES", 4));
           
            }
        };

        List<String> start_date = Arrays.asList("01-01-2022");

        userRepository = new UserRepository(UserPlanMap, UserTopUpMap, start_date);

    }

    @Test
    @DisplayName("findAll method should return All User Plans")
    public void findAllUserPlans(){
    //Arrange
        int expectedCount = 2;
    //Act
        List<UserPlans> actualPlans = userRepository.findAllPlans();

    //Assert
        Assertions.assertEquals(expectedCount,actualPlans.size());
}  

@Test
@DisplayName("findAll method should return All User TopUp")
public void findAllUserTopUp(){
//Arrange
    int expectedCount = 1;
//Act
   List<UserTopup> actualTopUp = userRepository.findAllTopup();

//Assert
    Assertions.assertEquals(expectedCount,actualTopUp.size());
} 

@Test
@DisplayName("Validate Start Date")
public void findStartDate(){
//Arrange
    String expected_date = "01-01-2022";
//Act
   String start_date = userRepository.findStartDate();

//Assert
    Assertions.assertEquals(expected_date,start_date);
} 


    
}
