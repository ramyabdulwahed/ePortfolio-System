# ePortfolio Project

## Overview

This project is an extension of the previous assignments where we created classes for managing investments such as `Investment`, `Stock`, `MutualFund`, and `Portfolio`. The functionalities included buying/selling investments, updating prices, calculating total gain, and searching for investments. Additionally, we implemented file I/O operations and a HashMap index for efficient keyword searches.

In this assignment, we enhance the project by adding a GUI interface and exception handling to make the system more robust and user-friendly.



## Features

### Graphical User Interface (GUI)

1. **Initial Interface**: Displays a welcome message and instructions. The "commands" menu includes options: buy, sell, update, getGain, search, and quit.
2. **Buying an Investment**: Allows users to buy stocks or mutual funds by entering details such as symbol, name, quantity, and price.
3. **Selling an Investment**: Enables users to sell investments by providing necessary details.
4. **Updating Investments**: Users can update the price of investments and navigate through the list of investments.
5. **Getting Total Gain**: Displays the total gain and individual gains for each investment.
6. **Searching Investments**: Users can search for investments based on various criteria.

### Exception Handling

- Ensures that invalid inputs and violations of class invariants are handled.
- Provides feedback to users for incorrect inputs and allows them to retry.

### Additional Requirements

1. **Validity Checks**: Ensures that all input values are valid (e.g., non-empty symbols and names, positive quantities and prices).
2. **Privacy Leak Protection**: Uses copy constructors to prevent privacy leaks in mutable classes.
3. **Method Overriding**: Ensures proper method overriding for inheritance.
4. **Abstract Classes and Methods**: Utilizes abstract classes and methods to enforce proper inheritance and polymorphism.
5. **Polymorphism**: Effectively uses polymorphism to simplify code and handle different types of investments uniformly.

## Libraries Used

- **Java AWT and Swing**: For building the GUI.
- **Java Exception Handling**: For managing errors and invalid inputs.

## How to Run

1. Compile the program using the following command:
     javac rabdulwa_a1/ePortfolio/*.java
2. navigate back twice (ex. cd ../../), then run the program using the following command:
    java rabdulwa_a1.ePortfolio.Portfolio
### once program is running
3. Use the GUI to interact with the system and perform various investment-related operations.

## Limitations
- The program only supports two types of investments: stocks and mutual funds and each type has specific rules commission fee and when it is applied
- The program does not support handling real-time market data, so price and quantity updates rely on user input


## Conclusion

This project demonstrates the integration of a GUI and robust exception handling into an investment management system, enhancing user experience and system reliability.


# Test Plan for ePortfolio Project

## Introduction

This test plan outlines the testing strategy for the ePortfolio project. The goal is to ensure that all functionalities work as expected and that the system is robust and user-friendly.

## Conclusion

This test plan ensures that all functionalities of the ePortfolio project are tested thoroughly, and the system is robust and user-friendly.



## Test Cases

### 1. Initial Interface
  
**Description**: Verify that the initial interface displays the welcome message and instructions correctly.  
**Steps**:
1. Run the program.
2. Observe the initial interface.
**Expected Result**: The welcome message and instructions are displayed correctly.

### 2. Buying an Investment

**Description**: Verify that the user can buy stocks or mutual funds by entering valid details.  
**Steps**:
1. Select the "buy" option from the menu.
2. Enter valid details (symbol, name, quantity, price).
3. Confirm the purchase.
**Expected Result**: The investment is added to the portfolio, and a confirmation message is displayed.

### 3. Selling an Investment

**Description**: Verify that the user can sell investments by providing valid details.  
**Steps**:
1. Select the "sell" option from the menu.
2. Enter valid details (symbol, quantity, price).
3. Confirm the sale.
**Expected Result**: The investment is sold, and a confirmation message is displayed.

### 4. Updating Investments

**Description**: Verify that the user can update the price of investments.  
**Steps**:
1. Select the "update" option from the menu.
2. Enter valid details (symbol, new price).
3. Confirm the update.
**Expected Result**: The investment price is updated, and a confirmation message is displayed.

### 5. Getting Total Gain

**Description**: Verify that the user can view the total gain and individual gains for each investment.  
**Steps**:
1. Select the "getGain" option from the menu.
**Expected Result**: The total gain and individual gains are displayed correctly.

### 6. Searching Investments

**Description**: Verify that the user can search for investments based on various criteria.  
**Steps**:
1. Select the "search" option from the menu.
2. Enter search criteria (e.g., symbol, name).
3. Confirm the search.
**Expected Result**: The search results are displayed correctly.

### 7. Exception Handling

**Description**: Verify that invalid inputs are handled.  
**Steps**:
1. Enter invalid details (e.g., empty symbol, negative quantity).
**Expected Result**: An error message is displayed, and the user is prompted to retry.

### 8. Validity Checks

**Description**: Verify that all input values are validated correctly.  
**Steps**:
1. Enter invalid values (e.g., empty symbol, negative price).
**Expected Result**: An error message is displayed, and the user is prompted to retry.

### 9. Privacy Leak Protection

**Description**: Verify that privacy leaks are prevented using copy constructors.  
**Steps**:
1. Attempt to access mutable class properties directly.
**Expected Result**: Direct access is not allowed, and copy constructors are used.

### 10. Method Overriding and Polymorphism

**Description**: Verify that method overriding and polymorphism are implemented correctly.  
**Steps**:
1. Check the implementation of abstract classes and methods.
2. Verify that polymorphism is used effectively.
**Expected Result**: Methods are overridden correctly, and polymorphism is used to handle different types of investments uniformly.

### 11. Real Value Test Plans

**Description**: Verify the system with real investment data to ensure accuracy and reliability.  
**Steps**:
1. Use the following real investment data:
    - Buy Stock: Symbol: AAPL, Name: Apple Inc., Quantity: 10, Price: 150.00
    - Buy Mutual Fund: Symbol: VFIAX, Name: Vanguard 500 Index Fund, Quantity: 5, Price: 350.00
    - Sell Stock: Symbol: AAPL, Quantity: 5, Price: 155.00
    - Update Stock Price: Symbol: AAPL, New Price: 160.00
    - Update Mutual Fund Price: Symbol: VFIAX, New Price: 360.00
2. Perform the following operations:
    - Buy the stock and mutual fund with the given details.
    - Sell part of the stock.
    - Update the prices of the stock and mutual fund.
    - Get the total gain.
    - Search for the investments using their symbols.
**Expected Result**: 
    - The stock and mutual fund are added to the portfolio with correct details.
    - The stock is partially sold, and the portfolio is updated.
    - The prices of the stock and mutual fund are updated correctly.
    - The total gain and individual gains are calculated and displayed accurately.
    - The search results display the correct investments based on the symbols.

### 12. Edge Case Test Plans

**Description**: Verify the system with edge case values to ensure robustness.  
**Steps**:
1. Use the following edge case data:
    - Buy Stock: Symbol: EDGE, Name: Edge Case Inc., Quantity: 1, Price: 0.01
    - Buy Mutual Fund: Symbol: EDGEF, Name: Edge Fund, Quantity: 1, Price: 0.01
    - Sell Stock: Symbol: EDGE, Quantity: 1, Price: 0.01
    - Update Stock Price: Symbol: EDGE, New Price: 0.02
    - Update Mutual Fund Price: Symbol: EDGEF, New Price: 0.02
2. Perform the following operations:
    - Buy the stock and mutual fund with the given details.
    - Sell the stock.
    - Update the prices of the stock and mutual fund.
    - Get the total gain.
    - Search for the investments using their symbols.
**Expected Result**: 
    - The stock and mutual fund are added to the portfolio with correct details.
    - The stock is sold, and the portfolio is updated.
    - The prices of the stock and mutual fund are updated correctly.
    - The total gain and individual gains are calculated and displayed accurately.
    - The search results display the correct investments based on the symbols.