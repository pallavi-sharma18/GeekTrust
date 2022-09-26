package com.geektrust.backend.RepositoryTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.Repository.TopUpRepository;
import com.geektrust.backend.entities.TopUp;

public class TopUpRepositoryTest {
    private TopUpRepository topUpRepository;

    @BeforeEach
    public void setup(){
        final Map<String,TopUp> TopUpMap = new HashMap<String,TopUp>(){
            {
                put("1",new TopUp("FOUR_DEVICE", 3, 10));
                put("2",new TopUp("TWO_DEVICE", 1, 115));
              
           
            }
        };
        topUpRepository = new TopUpRepository(TopUpMap);
    }

//     @Test
//     @DisplayName("TopUp saved in Repository successfully")
//     public void savePlans(){
//         // Arrange
//         final TopUp topup = new TopUp("TWO_DEVICE", 4, 1);
//         TopUp expectedTopup = new TopUp("3","TWO_DEVICE",4,1);

//         // Act

//         TopUp actualTopup = topUpRepository.save(topup);
//         // Assert 
//         Assertions.assertEquals(expectedTopup,actualTopup);

// }

@Test
@DisplayName("findAll method should return All User")
public void findAllUserTopUp(){
    //Arrange
    int expectedCount = 2;
    //Act
    List<TopUp> actualTopup = topUpRepository.findAllTopupPricing();

    //Assert
    Assertions.assertEquals(expectedCount,actualTopup.size());
}
    
}
