//Ramy Abdulwahed, 1299971, rabdulwa
package rabdulwa_a1.ePortfolio;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//for gui interface
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



/* try {
        loadInvestmentsFromFile(filename, investments);
    } catch (FileNotFoundException e) {
        System.out.println("Error: Unable to open file '" + filename + "' for reading.");
    } catch (Exception e) {
        System.out.println("Error: An unexpected error occurred while loading data from file '" + filename + "'.");
    }

    //i need to decrement positions in index
*/



/**
 * Portfolio class is for managing a portfolio of investments. In our case we limit it to stocks and mutual funds.
 * It contains methods to buy, sell, update, get the gain, and search for stocks and mutual funds in the portfolio.
 * This Portfolio class has no constructor as it is meant to be used as one portfolio of stocks and mutual funds.
 */


public class Portfolio{

    private ArrayList<Investment> investments;
    private HashMap<String, ArrayList<Integer>> index;

    /**to implement gui interface */
    public Portfolio(){
        investments = new ArrayList<Investment>();
        index = new HashMap<String, ArrayList<Integer>>();
    }
    /**
     * Main method to run the program and manage the portfolio of investments
     * it contains a menu with options to buy, sell, update, get the gain, and search for stocks and mutual funds in the portfolio.
     * it uses two arraylists to store the stocks and mutual funds independently
     * @param args Command-line arguments - not used.
     */




    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        PortFolioGUI gui = new PortFolioGUI(portfolio);

    //two arraylists to store the stocks and mutual funds independently
    ArrayList<Investment> investments = new ArrayList<Investment>();
    HashMap<String, ArrayList <Integer>> index = new HashMap<String, ArrayList <Integer>>();

    String fileName = "investments.txt";
    //ArrayList <Integer> tempIndices = new ArrayList <Integer>();

    //check if file name is provided
    /*if (args.length > 0){
        fileName = args[0];
        Investment.loadInvestmentsFromFile(fileName, investments, index);

    }*/
    //else{
    //    System.out.println("No input file provided.");
    //    userChoice.close();
    ///    return;
    //}
    //load the investments from the file
    //Investment.loadInvestmentsFromFile(fileName, investments);

/* 
    do{
    
        System.out.println("Choose from the following options");
        System.out.println("1. Buy");
        System.out.println("2. Sell");
        System.out.println("3. Update");
        System.out.println("4. getGain");
        System.out.println("5. search");
        
        choice = userChoice.nextLine().trim().toLowerCase();

        switch(choice){

            case "b":
            case "buy":

                boolean exists = false; // Flag to check if the symbol already exists in the list
                boolean symbolInStocks = false; // Flag to check if the symbol already exists in the stocks list
                boolean symbolInInvestments = false; // Flag to check if the symbol already exists in the mutual funds list
                boolean InvestmentIsStock = false; // Flag to check if the investment type is stock
                boolean InvestmentIsMutualFund = false; // Flag to check if the investment type is mutual fund
                boolean InvestmentIsValid = false;

                System.out.println("Enter the type of investment (Stock or MutualFund):");
                investmentType = userChoice.nextLine().trim();

                //validate the user input 
                while (investmentType.isEmpty() || 
                (!investmentType.equalsIgnoreCase("Stock") && !investmentType.equalsIgnoreCase("MutualFund") 
                && !investmentType.equalsIgnoreCase("S") && !investmentType.equalsIgnoreCase("M"))) {                
                    System.out.println("Invalid investment type. Please enter Stock or MutualFund:");
                    investmentType = userChoice.nextLine().trim();
                }

                //check if the investment type is stock or mutual fund to help us validate whether the symbol already exists in the list
                if(investmentType.equalsIgnoreCase("Stock") || investmentType.equalsIgnoreCase("S")){

                    InvestmentIsStock = true;
                }
                else {
                    InvestmentIsMutualFund = true;
                }


                //check if the symbol already exists in any of the lists
                do{
                    // Check if the entered symbol already exists in the investments list.
                    // Prompt the user to re-enter if the symbol is already used for the wrong type.
                    //reset the flags
                    exists = false;
                    symbolInInvestments = false;
                    symbolInStocks = false;
                    InvestmentIsValid = false;

                    System.out.println("Enter the symbol of the investment:");
                    symbol = userChoice.nextLine().trim();
                    //validate the user input - symbol cannot be empty
                    while (symbol.isEmpty()){
                        System.out.println("Invalid symbol. Please enter a symbol:");
                        symbol = userChoice.nextLine().trim();
                    }
                    // First check if the symbol exists in the mutual funds list
                    for (Investment shares : investments) { //check if the symbol exists in mutual funds

                        if (shares.getSymbol().equalsIgnoreCase(symbol)) {
                            exists = true; //here we know that the symbol exists in the investments list
                            if (shares.getClass() == Stock.class && InvestmentIsStock) {
                                InvestmentIsValid = true;
                                System.out.println("Symbol already exists in the Investments list as a Stock.");
                            }
                            else if (shares.getClass() == MutualFund.class && InvestmentIsMutualFund) {
                                InvestmentIsValid = true;
                                System.out.println("Symbol already exists in the Investments list as a MutualFund.");
                            }
    //                        symbolInInvestments = true;
                            break;
                        }
                    }
                //if exists if false, then we can move on to the next step, else if exists is true and InvestmentIsValid is true we can move on to the next step
                } while (exists && !InvestmentIsValid); //this means that the symbol does not exist in the investments list or it exists in the wrong type of investment list
                //the above condition is for checking if the symbol exists, and if it exists in the wrong type of investment list, prompt the user to enter a new symbol

                // If symbol doesn't exist in either list, add new investment

                if (!exists){
                    System.out.println("Enter the name of the investment:");
                        name = userChoice.nextLine().trim();
                        //validate the user input - name cannot be empty
                        while (name.isEmpty()){ //name cannot be empty, prompt the user to enter a name
                            System.out.println("Invalid name. Please enter a name:");
                            name = userChoice.nextLine().trim();
                        }
                        //we need to find the current index of the investment in the list
                        int countInvestmentIndex = investments.size(); //-1 because the index starts at 0 here.
                        //store the keywords in the HashMap
                        String keywords [] = name.split(" ");
                        for (int i =0; i < keywords.length; i++){
                            keywords[i] = keywords[i].toLowerCase();
                            ArrayList<Integer> tempIndices = new ArrayList<Integer>(); // Create a new ArrayList for each keyword so that indexes dont get deleted
                            tempIndices.clear(); // clear tempIndices after each keyword so that each word only contains its own indexes
                            if (index.containsKey(keywords[i])){
                                tempIndices = index.get(keywords[i]);
                                tempIndices.add(countInvestmentIndex);
                            }
                            else{
                                tempIndices.add(countInvestmentIndex);
                            }
                            //now we add the pair of keyword and index to the hashmap
                            index.put(keywords[i], tempIndices);
                            countInvestmentIndex++;
                        }
            
                        System.out.println("Enter the price:");
                        price = userChoice.nextDouble();
                        userChoice.nextLine(); // consume newline
                        //validate the user input
                        while (price < 0){ //price cannot be negative, but can be 0 (prof told me to allow 0)
                            System.out.println("Invalid price. Please enter a positive price:");
                            price = userChoice.nextDouble();
                            userChoice.nextLine(); // consume newline
                        }
            
                        System.out.println("Enter the quantity:");
                        quantity = userChoice.nextInt();
                        userChoice.nextLine(); // consume newline
                        //validate the user input
                        while (quantity <= 0){
                            System.out.println("Invalid quantity. Please enter a positive quantity:");
                            quantity = userChoice.nextInt();
                            userChoice.nextLine(); // consume newline
                        }
                        if (InvestmentIsStock) {
                            Stock newStock = new Stock(symbol, name, price, quantity);
                            investments.add(newStock);
                            System.out.println("New stock added to the portfolio.");

                        }
                        else {
                            MutualFund newMutualFund = new MutualFund(symbol, name, price, quantity);
                            investments.add(newMutualFund);
                            System.out.println("New stock added to the portfolio.");

                        }
                        

                        
                    }
                

                    // If symbol doesn't exist in mutual funds, check the stocks list
                    else if (exists) { //if the symbol exists in the investments list
                        for (Investment shares : investments) {
                            if (shares.getSymbol().equalsIgnoreCase(symbol)) {
                                System.out.println("Stock already exists, updating the quantity and price.");
                                System.out.println("Enter the new quantity:");
                                quantity = userChoice.nextInt();
                                userChoice.nextLine(); // consume newline
                                //validate the user input
                                while (quantity <= 0){
                                    System.out.println("Invalid quantity. Please enter a positive quantity:");
                                    quantity = userChoice.nextInt();
                                    userChoice.nextLine(); // consume newline
                                }

                                System.out.println("Enter the new price:");
                                price = userChoice.nextDouble();
                                userChoice.nextLine(); // consume newline
                                //validate the user input
                                while (price < 0){
                                    System.out.println("Invalid price. Please enter a positive price:");
                                    price = userChoice.nextDouble();
                                    userChoice.nextLine(); // consume newline
                                }
                                shares.setQuantity(shares.getQuantity() + quantity);
                                shares.setPrice(price);
                                if (InvestmentIsStock) {
                                    System.out.println ("Went through");
                                    shares.setBookValue(shares.getBookValue() + (quantity * price + 9.99)); // Adding commission for stock
                                }
                                else {
                                    shares.setBookValue(shares.getBookValue() + (quantity * price)); // No commission for mutual funds
                                }
                                //stock.setBookValue(stock.getBookValue() + (quantity * price + 9.99)); // Adding commission for stock
                                break;
                            }
                        }
                    }
                

                    else { // if the user enters neither stock nor mutualfund

                        System.out.println("Invalid investment type.");
                    }
            
                    break;

            //sell
            
            case "sell":
            stockExists = false;
            mutualFundExists = false;
            boolean symbolExists = false;
                System.out.println("Enter the type of investment you want to sell (stock, mutualfund)");
                investmentType = userChoice.nextLine();
                //validate the user input
                //below condition is checking if the user entered a valid investment type, if not, prompt the user to enter a valid investment type
                while (investmentType.isEmpty() || 
                (!investmentType.equalsIgnoreCase("Stock") && !investmentType.equalsIgnoreCase("MutualFund") 
                && !investmentType.equalsIgnoreCase("S") && !investmentType.equalsIgnoreCase("M"))) {                
                    System.out.println("Invalid investment type. Please enter Stock or MutualFund:");
                    investmentType = userChoice.nextLine().trim();
                }
                System.out.println("Enter the symbol of the investment you want to sell");
                symbol = userChoice.nextLine();
                while (symbol.isEmpty()){
                    System.out.println("Invalid input. Please enter a symbol:");
                    symbol = userChoice.nextLine().trim();
                }
                //words = investmentType.split(" ");
                for (int i = 0; i <investments.size(); i++) {
                    symbolExists =false;
                    Investment investment = investments.get(i); 
                    if (investment.getSymbol().equalsIgnoreCase(symbol)){
                        symbolExists = true;
                        if ((investmentType.equalsIgnoreCase("mutualfund") || investmentType.equalsIgnoreCase("m")) && investment instanceof MutualFund){
                            mutualFundExists = true;
                        }
                        else if (investmentType.equalsIgnoreCase("stock") || investmentType.equalsIgnoreCase("s") && investment instanceof Stock){
                            stockExists = true;
                        }
                        else{
                            System.out.println("Invalid investment type");
                        }
                    }
                    //if the investment is stock or mutual fund and matches the symbol entered by the user then proceed to sell
                    if (stockExists || mutualFundExists){
                        System.out.println("Enter the quantity you want to sell");
                            quantity = userChoice.nextInt();
                            userChoice.nextLine();

                            //quantity cannot be negative or greater than the total quantity of the mutual fund
                            while (quantity < 0 || investment.getQuantity() < quantity){
                                System.out.println("Invalid quantity");
                                System.out.println("Enter the quantity you want to sell");
                                quantity = userChoice.nextInt();
                                userChoice.nextLine();
                            }

                            System.out.println("Enter the price you want to sell at");
                            price = userChoice.nextDouble();
                            userChoice.nextLine();
                            //price cannot be negative
                            while (price < 0){
                                System.out.println("Invalid price. Enter a positive price");
                                price = userChoice.nextDouble();
                                userChoice.nextLine();
                            }

                            double payment, gain;
                            // if the quantity is equal to the total quantity of the stock
                            if (quantity == investment.getQuantity()){
                                int currentIndex = i;
                                investments.remove(i);
                                i--; // decrement i to avoid skipping the next stock in the list

                                //update the HashMap for each keyword in the name of the investment
                                String keywords [] = investment.getName().split(" ");
                                //int currentIndex = investments.indexOf(investment);

                                for (int j = 0; j < keywords.length; j++){
                                    ArrayList<Integer> tempIndices = new ArrayList<Integer>(); // Create a new ArrayList for each keyword so that indexes dont get deleted
                                    tempIndices = index.get(keywords[j]); //this is the ArrayList of indices of the investments that contain the keyword
                                    if (tempIndices.size() == 1){
                                        index.remove(keywords[j]); //if the keyword only has one index, remove the keyword from the HashMap
                                    }
                                    else{
                                    tempIndices.remove(currentIndex); //remove the index of the investment that is being sold
                                    //we need to update the index of all positions that are greater than the index of the investment that is being sold
                                    for (int k = 0; k < tempIndices.size(); k++){
                                        //we need to update indcies that are greater than the index of the investment that is being sold
                                        //we need to update the index of all positions that are greater than the index of the investment that is being sold

                                        if (tempIndices.get(k) >= currentIndex){
                                            tempIndices.set(k, tempIndices.get(k) - 1); 
                                        }
                                    }
                                    index.put(keywords[j], tempIndices); //update the HashMap
                                    }
                                }
                                //for (String key : index.keySet()){
                                //        System.out.println("Key: " + key + " Value: " + index.get(key));
                                //}
                                
                                //calculate the payment and gain
                                payment = quantity * price - 9.99; 
                                gain = payment - investment.getBookValue();
                                System.out.println("Payment: $" + payment);
                                System.out.println("Gain: $" + gain);
                                break;
                            }
                            // if the quantity is less than the total quantity of the stock
                            else {
                                int originalQuantity = investment.getQuantity();
                                investment.setQuantity(investment.getQuantity() - quantity);
                                investment.setPrice(price);
                                double bookValueB4Selling = investment.getBookValue(); //we will use this variable to calculate the gain
                                investment.setBookValue(investment.getBookValue() * ((double)(originalQuantity - quantity)/originalQuantity));
                                double bookValueRemaining = investment.getBookValue(); //we will use this variable to calculate the gain
                                double bookValueSold = bookValueB4Selling - bookValueRemaining;
                                if (investment instanceof MutualFund){
                                    payment = quantity * price - 45.00;
                                }
                                else{
                                    payment = quantity * price - 9.99;
                                }
                                System.out.println("Payment: $" + payment);
                                System.out.println("Gain: $" + (payment - bookValueSold));
                                break;
                            }
                    }
                    else{
                        System.out.println("Invalid symbol. Symbol doesn't exist in the portfolio");
                    }

                }

                if (!symbolExists){
                    System.out.println("Invalid symbol. Symbol doesn't exist in the portfolio");
                }

                break;


            //update
            case "u":
            case"update":
                //iterate over the stocks ArrayList and update the price of each stock according to the user input
                for (Investment shares: investments){
                    System.out.println("Enter new price for the symbol " + shares.getSymbol() + ": ");
                    price = userChoice.nextDouble();
                    userChoice.nextLine(); // clear the buffer
                    while (price < 0){
                        System.out.println("Invalid price. Enter a positive price");
                        price = userChoice.nextDouble();
                        userChoice.nextLine(); // clear the buffer
                    }
                    shares.setPrice(price);
                }
                break;


            //getGain
            case "g":
            case "getgain":
                double totalGain = 0.0;
                double gain = 0.0;
                int i = 0;

                for (Investment shares: investments){
                    if (shares instanceof Stock){
                    gain = (shares.getQuantity() * shares.getPrice() -9.99) - shares.getBookValue();   
                    }
                    else{
                        gain = (shares.getQuantity() * shares.getPrice() -45.00) - shares.getBookValue();
                    }
                    System.out.println("shares.getQuantity() = " + shares.getQuantity() + " shares.getPrice() = " + shares.getPrice() + " shares.getBookValue() = " + shares.getBookValue());
                    System.out.println("gain = " + gain);
                    totalGain = totalGain + gain; 
                    i++;
                }
                System.out.println("# of investments: " + i);
                System.out.println("Portfolio total gain is : $" + totalGain);

                break;
            //search
            case "search":
                boolean symbolEmpty = false;
                boolean keywordEmpty = false;
                boolean priceRangeEmpty = false;
                double lowerBound = 0.0; //price cannot be negative
                double upperBound = 0.0;
                System.out.println("Enter the symbol");
                symbol = userChoice.nextLine().trim();
                System.out.println("Enter the keyword/s for the name");
                String search = userChoice.nextLine().trim();
                System.out.println("Enter the price range");
                String priceRange = userChoice.nextLine().trim();
                words = search.split(" ");
                ArrayList <Integer> result = new ArrayList <Integer>();
                //HashMap<String, ArrayList <Integer>> index = new HashMap<String, ArrayList <Integer>>();
                //check if the user has entered any of the fields to know what to search for
                if (symbol.isEmpty()){
                    symbolEmpty = true;
                }
                if (search.isEmpty()){
                    keywordEmpty = true;
                }
                if (priceRange.isEmpty()){
                    priceRangeEmpty = true;
                }

                //iterate over the ArrayList and print the stocks that match the user input
                for (Investment shares: investments){
                    //if the user has not entered any of the fields, print all the investments
                    if (symbolEmpty && keywordEmpty && priceRangeEmpty){
                        System.out.println(shares);
                    }
                    //if the user has entered the symbol only
                    else if (!symbolEmpty && keywordEmpty && priceRangeEmpty){
                        if (shares.getSymbol().equalsIgnoreCase(symbol)){
                            System.out.println(shares);
                        }
                    }
                    //if user has entered the symbol and the keyword
                    else if(!symbolEmpty && !keywordEmpty && priceRangeEmpty){
                        boolean wordNotInIndex = false;
                        // check if first keyword is in the index if not break;
                        if (!index.containsKey(words[0].toLowerCase())) {
                            wordNotInIndex = true;
                        }
                        if (!wordNotInIndex){
                             result= index.get(words[0].toLowerCase());
                             //System.out.println("result = " + result); testing purposes
                             for (int k = 1; k < words.length; k++) {
                                if (index.containsKey(words[k].toLowerCase())) {
                                    System.out.println("entered the loop");
                                    ArrayList<Integer> tempIndices = new ArrayList<Integer>(); // Create a new ArrayList for each keyword so that indexes dont get deleted
                                    tempIndices = index.get(words[k].toLowerCase());
                                    result.retainAll(tempIndices); // Keep only common indices

                                }
                                else {
                                    wordNotInIndex = true;
                                    break; //exist the loop if word is not in index
                                }
                            }
                        }
                        //print only if the investments match the symbol at this point
                        if (!wordNotInIndex){ 
                            for (int j = 0; j < result.size(); j++){                     
                            if (investments.get(result.get(j)).getSymbol().equalsIgnoreCase(symbol)){
                                System.out.println(investments.get(result.get(j)));
                            }
                        }

                    }
                }
                    //user has entered symbol and priceRange
                    else if (!symbolEmpty && keywordEmpty && !priceRangeEmpty){
                        if (priceRange.contains("-")) {
                            if (priceRange.startsWith("-")) {
                                // "-100" case
                                lowerBound = 0.0;
                                upperBound = Double.parseDouble(priceRange.substring(1).trim());
                            } else if (priceRange.endsWith("-")) {
                                // "100-" case
                                lowerBound = Double.parseDouble(priceRange.substring(0, priceRange.length() - 1).trim());
                                upperBound = Double.POSITIVE_INFINITY;
                                System.out.println("lowerBound = " + lowerBound);
                            } else {
                                // "100-200" case
                                String[] range = priceRange.split("-");
                                lowerBound = Double.parseDouble(range[0].trim());
                                upperBound = Double.parseDouble(range[1].trim());
                            }
                        } else {
                            // "100" case
                            lowerBound = Double.parseDouble(priceRange.trim());
                            upperBound = lowerBound;
                        }

                        if (shares.getSymbol().equalsIgnoreCase(symbol) && shares.getPrice() >= lowerBound && shares.getPrice() <= upperBound){
                            System.out.println(shares);
                        }

                    }
                    
                    //if the user has entered the keyword only
                    else if (symbolEmpty && !keywordEmpty && priceRangeEmpty){
                        boolean wordNotInIndex = false;
                        // check if first keyword is in the index if not break;
                        if (!index.containsKey(words[0].toLowerCase())) {
                            //tempIndices = index.get(words[0].toLowerCase());
                            wordNotInIndex = true;
                        }

                        if (!wordNotInIndex){
                             result= index.get(words[0].toLowerCase());
                             for (int k = 1; k < words.length; k++) {
                                if (index.containsKey(words[k].toLowerCase())) {
                                    ArrayList<Integer> tempIndices = new ArrayList<Integer>(); // Create a new ArrayList for each keyword so that indexes dont get deleted
                                    tempIndices = index.get(words[k].toLowerCase());
                                    result.retainAll(tempIndices); // Keep only common indices

                                }
                                else {
                                    wordNotInIndex = true;
                                    break; //exist the loop if word is not in index
                                }
                            }
                        }
                        if (!wordNotInIndex){
                            System.out.println("result = " + result);
                            System.out.println("result.size() = " + result.size());
                            for (int k = 0; k < result.size(); k++) {
                                System.out.println(investments.get(result.get(k)));
                            }
                        }

                    }//else if
                    //if the user has entered the keyword and price range
                    else if (symbolEmpty && !keywordEmpty && !priceRangeEmpty){
                        //keyword checking
                        boolean wordNotInIndex = false;
                        // check if first keyword is in the index if not break;
                        //System.out.println("words[0] = " + words[0].toLowerCase());
                        if (!index.containsKey(words[0].toLowerCase())) {
                            //tempIndices = index.get(words[0].toLowerCase());
                            wordNotInIndex = true;
                        }
                        if (!wordNotInIndex){
                             result= index.get(words[0].toLowerCase());
                             //System.out.println("result = " + result);
                             for (int k = 1; k < words.length; k++) {
                                if (index.containsKey(words[k].toLowerCase())) {
                                    ArrayList<Integer> tempIndices = new ArrayList<Integer>(); // Create a new ArrayList for each keyword so that indexes dont get deleted
                                    tempIndices = index.get(words[k].toLowerCase());
                                    result.retainAll(tempIndices); // Keep only common indices

                                }
                                else {
                                    wordNotInIndex = true;
                                    break; //exist the loop if word is not in index
                                }
                            }
                        }
                        
                        if (!wordNotInIndex){
                            
                        
                        //price range
                        if (priceRange.contains("-")) {
                            if (priceRange.startsWith("-")) {
                                // "-100" case
                                lowerBound = 0.0;
                                upperBound = Double.parseDouble(priceRange.substring(1).trim());
                            } else if (priceRange.endsWith("-")) {
                                // "100-" case
                                lowerBound = Double.parseDouble(priceRange.substring(0, priceRange.length() - 1).trim());
                                upperBound = Double.POSITIVE_INFINITY;
                                System.out.println("lowerBound = " + lowerBound);
                            } else {
                                // "100-200" case
                                String[] range = priceRange.split("-");
                                lowerBound = Double.parseDouble(range[0].trim());
                                upperBound = Double.parseDouble(range[1].trim());
                            }
                        } else {
                            // "100" case
                            lowerBound = Double.parseDouble(priceRange.trim());
                            upperBound = lowerBound;
                        }

                        //check if the price range matches and then print if true,
                        for (int k = 0; k < result.size(); k++) {
                            //System.out.println("result.get(k) = " + result.get(k));
                            //System.out.println("investments.get(result.get(k)).getPrice() = " + investments.get(result.get(k)).getPrice());
                            if (investments.get(result.get(k)).getPrice() >= lowerBound && investments.get(result.get(k)).getPrice() <= upperBound){
                                
                                System.out.println(investments.get(result.get(k)));                        
                            }
                        }    
                    } //if bracket
                    else{
                    System.out.println("No investments found"); }
                    }
                //if the user has entered the price range only

                else if (symbolEmpty && keywordEmpty && !priceRangeEmpty){
                    if (priceRange.contains("-")) {
                        if (priceRange.startsWith("-")) {
                            // "-100" case
                            lowerBound = 0.0;
                            upperBound = Double.parseDouble(priceRange.substring(1).trim());
                        } else if (priceRange.endsWith("-")) {
                            // "100-" case
                            lowerBound = Double.parseDouble(priceRange.substring(0, priceRange.length() - 1).trim());
                            upperBound = Double.POSITIVE_INFINITY;
                        } else {
                            // "100-200" case
                            String[] range = priceRange.split("-");
                            lowerBound = Double.parseDouble(range[0].trim());
                            upperBound = Double.parseDouble(range[1].trim());
                        }
                    } else {
                        // "100" case
                        lowerBound = Double.parseDouble(priceRange.trim());
                        upperBound = lowerBound;
                    }
                    for (Investment share: investments){
                        if (share.getPrice() >= lowerBound && share.getPrice() <= upperBound){
                                System.out.println(share);
                        }

                    }
                }
            

                //if user enters inavlid input such as more than one symbol ...
                else{
                    System.out.println("Invalid input");
                }
            } //end of for loop

                break;

            
            case "s":
                System.out.println("Couldn't tell if you wanted to search or sell. Please enter search or sell");
                break;
            //quit
            case "quit":
            case "q":
                System.out.println("quitting");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
}
    while (!choice.equalsIgnoreCase("quit") && !choice.equalsIgnoreCase("q"));
    Investment.saveInvestmentsToFile (fileName, investments);
    userChoice.close();
    System.exit(0); 
*/
}
        // Example method to add an investment
        public void addInvestment(Investment investment) {
            investments.add(investment);
            // Update the index as needed
        }
    
        // Example method to get investments
        public ArrayList<Investment> getInvestments() {
            return investments;
           // return new ArrayList<>(investments); // Return a copy of the list
        }
    
        // Example method to get the index
        public HashMap<String, ArrayList<Integer>> getIndex() {
            return new HashMap<>(index); // Return a shallow copy of the map
        }

}