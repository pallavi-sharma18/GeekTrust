package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
    
@DisplayName("AppTest")
public class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
// ******* Test 1 ********
    @Test
    @DisplayName("Integration Test #1")
    void runTest1(){
        //Arrange
        List<String> arguments= new ArrayList<>(List.of("sample_input/input.txt"));
        
		String expectedOutput = 
        "RENEWAL_REMINDER MUSIC 10-03-2022\n"+
        "RENEWAL_REMINDER VIDEO 10-05-2022\n"+
        "RENEWAL_REMINDER PODCAST 10-03-2022\n"+
        "RENEWAL_AMOUNT 750";

        // String expectedOutput = "INVALID_DATE";
       
        //Act
        App.run(arguments);

        //Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
 
	}

// ******** Test 2 **********

@Test
@DisplayName("Integration Test #2")
void runTest2(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/input1.txt"));
    
    String expectedOutput = "INVALID_DATE";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}

// **************** Test 3 ************
@Test
@DisplayName("Integration Test #3")
void runTest3(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/input2.txt"));
    
   
    String expectedOutput = "SUBSCRIPTIONS_NOT_FOUND";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}

// ************ Test 4 *************
@Test
@DisplayName("Integration Test #4")
void runTest4(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/input3.txt"));
    
   
    String expectedOutput = "ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}

// *************** Test 5
@Test
@DisplayName("Integration Test #5")
void runTest5(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/input4.txt"));
    
   
    String expectedOutput = "ADD_TOPUP_FAILED DUPLICATE_TOPUP";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}

// **************** Test 6 ****************

@Test
@DisplayName("Integration Test #6")
void runTest6(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/input5.txt"));
    
   
    String expectedOutput = "ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND\n"+
    "SUBSCRIPTIONS_NOT_FOUND";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}

// ****************** Test 7 *******************
@Test
@DisplayName("Integration Test #7")
void runTest7(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/test3.txt"));
    
   
    String expectedOutput = "RENEWAL_REMINDER MUSIC 15-10-2021\n"+
    "RENEWAL_REMINDER VIDEO 15-10-2021\n"+
    "RENEWAL_REMINDER PODCAST 15-08-2021\n"+
    "RENEWAL_AMOUNT 850";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}

// ******************** Test 8 **************
@Test
@DisplayName("Integration Test #8")
void runTest8(){
    //Arrange
    List<String> arguments= new ArrayList<>(List.of("sample_input/test4.txt"));
    
   
    String expectedOutput = "INVALID_DATE\n"+
    "ADD_SUBSCRIPTION_FAILED INVALID_DATE\n"+
    "ADD_SUBSCRIPTION_FAILED INVALID_DATE\n"+
    "ADD_TOPUP_FAILED INVALID_DATE\n"+
    "SUBSCRIPTIONS_NOT_FOUND";
   
    //Act
    App.run(arguments);

    //Assert
    Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());

}
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
