package com.geektrust.backend.RepositoryTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.Repository.PlansRepository;
import com.geektrust.backend.entities.Plans;

public class PlanRepositoryTest {
    private PlansRepository plansRepository;

    @BeforeEach
    public void setup(){
        final Map<String,Plans> PlanMap = new HashMap<String,Plans>(){
            {
                put("1",new Plans("1","MUSIC", "PREMIUM",4, 1));
                put("2",new Plans("2","VIDEO", "PERSONAL",4, 1));
           
            }
        };
        plansRepository = new PlansRepository(PlanMap);
    }

//     @Test
//     @DisplayName("Plans saved in Repository successfully")
//     public void savePlans(){


//         // Arrange
//         final Plans plan1 = new Plans("MUSIC", "FREE", 1, 1);
//         Plans expectedPlan = new Plans("3","MUSIC", "FREE", 1, 1);

//         // Act

//         Plans actualPlan = plansRepository.save(plan1);
//         // Assert 
//         Assertions.assertEquals(expectedPlan,actualPlan);

// }

@Test
@DisplayName("findAll method should return All Plans")
public void findAllUserPlans(){
    //Arrange
    int expectedCount = 2;
    //Act
    List<Plans> actualPlans = plansRepository.findAllPlansPricing();

    //Assert
    Assertions.assertEquals(expectedCount,actualPlans.size());
}

}