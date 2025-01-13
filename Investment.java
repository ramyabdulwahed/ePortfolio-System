//Ramy Abdulwahed, 1299971, rabdulwa
package rabdulwa_a1.ePortfolio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The Investment class is the parent class for the Stock and MutualFund classes
 * It contains the attributes and methods that are common to both the Stock and MutualFund classes
 * The Investment class has the following attributes
 * symbol: the symbol of the stock which is unique
 * name: the name of the stock. Name cannot be empty
 * price: the price of the stock. Price cannot be negative
 * quantity: the quantity of the stock. Quantity cannot be negative
 * bookValue: the book value of the stock. Book value cannot be negative
 * The Investment class has the following methods
 * getSymbol: method to get the symbol of the stock
 * getName: method to get the name of the stock
 * getPrice: method to get the price of the stock
 * setPrice: method to set the price of the stock
 * getQuantity: method to get the quantity of the stock
 * setQuantity: method to set the quantity of the stock
 * getBookValue: method to get the book value of the stock
 * setBookValue: method to set the book value of the stock
 * equals: method to compare two stocks
 * toString: method to display the details of the stock
 * loadInvestmentsFromFile: method to load investments from a file
 * saveInvestmentsToFile: method to save investments to a file
 */
public abstract class Investment {

     /**
     * Attributes of the Stock class
     * symbol: the symbol of the stock which is unique
     * name: the name of the stock. Name cannot be empty
     * price: the price of the stock. Price cannot be negative
     * quantity: the quantity of the stock. Quantity cannot be negative
     * bookValue: the book value of the stock. Book value cannot be negative
     */

    /**packagae acess for the following attributes, so that they can be accessed by the subclasses of the Investment class
     */
     String symbol;
     String name;
     double price; 
     int quantity;
     double bookValue;

 

    /**
     * Constructor for the Stock class
     * @param symbol the symbol of the stock must be unique in the portfolio
     * @param name the name of the stock
     * @param price the price of the stock
     * @param quantity the quantity of the stock
     */
    public Investment(String symbol, String name, double price, int quantity) {

        if (symbol == null ||  symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or zero");
        }

        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Copy constructor
    public Investment(Investment other) {
        this.symbol = other.symbol;
        this.name = other.name;
        this.price = other.price;
    }
    public abstract Double getGain(Investment shares);


    public String getSymbol(){
        return symbol;
    }
    
    /**
     * Method to set the symbol of the investment
     * @param symbol the new symbol of the investment
     */
    public void setSymbol (String symbol){
        if(symbol == null || symbol.isEmpty()){
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        this.symbol =symbol;
    }
    /**
     * Method to set the name of the investment
     * @param name the new name of the investment
     */
    public void setName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        // //check if name is integer
        // try{
        //     Integer.parseInt(name);
        //     throw new IllegalArgumentException("Name cannot be a number");
        // }
        // catch (NumberFormatException e){

        // }
        this.name = name;
    }

    
    /**
     * Method to get the name of the mutual fund
     * @return the name of the mutual fund
     */
    public String getName(){
        return name;
    }
    /**
     * Method to get the price of the mutual fund
     * @return the price of the mutual fund
     */

    public double getPrice(){
        return price;
    }
    /**
     * Method to set the price of the mutual fund
     * @param price the price of the mutual fund
     * price cannot be negative
     */

    public void setPrice(double price){
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
    /**
     * Method to get the quantity of the mutual fund
     * @return the quantity of the mutual fund
     */

    public int getQuantity(){
        return quantity;
    }
    /**
     * Method to set the quantity of the mutual fund
     * @param quantity the quantity of the mutual fund
     quantity cannot be negative or zero
     */

    public void setQuantity(int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity cannot be negative or zero");
        }
        this.quantity = quantity;
    }
    /**
     * Method to get the book value of the mutual fund
     * @return the book value of the mutual fund
     */

    public double getBookValue(){
        return bookValue;
    }
    /**
     * Method to set the book value of the mutual fund
     * @param bookValue the book value of the mutual fund
     * book value cannot be negative
     */
    public void setBookValue(double bookValue){
        if(bookValue < 0){
            throw new IllegalArgumentException("Book value cannot be negative");
        }
        this.bookValue = bookValue;
    }

    /**
     * Method to compare two investments
     * @param investment to compare with
     * @return true if the investments are equal, 
     * that is if they have the same symbol, name, price, quantity, and book value 
     * false otherwise
     */
    /*public boolean equals(MutualFund mutualFund){
        if (this.symbol.equals(mutualFund.getSymbol()) && this.name.equals(mutualFund.getName()) && this.price == mutualFund.getPrice() && this.quantity == mutualFund.getQuantity() && this.bookValue == mutualFund.getBookValue()){
            return true;
        }
        return false;   
    }
    */
    public boolean equals(Object investment){
        if (this == investment){
            return true;
        }
        if (investment == null){
            return false;
        }
        if (this.getClass() != investment.getClass()){
            return false;
        }
        Investment other = (Investment) investment;
        return this.symbol.equals(other.symbol) && this.name.equals(other.name) && this.price == other.price && this.quantity == other.quantity && this.bookValue == other.bookValue;
        
    }
    /**
     * Method to display the details of the mutual fund
     * @return the details of the mutual fund
     * details include the symbol, name, price, quantity, and book value
     */
    public String toString( ){
        return "Symbol: " + this.symbol + "  Name: " + this.name + "  Price: " + this.price + "  Quantity: " + this.quantity + "  Book Value: " + this.bookValue;
    }

    

    /**
     * Method to load investments from a file with the following format
     *type = ""
    symbol = ""
    name = ""
    quantity = 
    price = ""
    bookValue = ""
     * 
     * 
     */
    public static void loadInvestmentsFromFile(String filename, ArrayList<Investment> investments, HashMap<String, ArrayList <Integer>> index){ 
        int countInvestmentIndex = 0;
        String type, symbol, name;
        int quantity;
        double price, bookValue;
        String line = "";
        //ArrayList <Integer> tempIndices = new ArrayList <Integer>();
        try{
            Scanner inputScan = new Scanner (new FileInputStream(filename));
            while (inputScan.hasNextLine()) {
                line = inputScan.nextLine();
                //System.out.println(line);
                String [] parts = line.split("=");
                type = parts[1].trim();

                line = inputScan.nextLine();
                parts = line.split("=");
                symbol = parts[1].trim();

                line = inputScan.nextLine();
                parts = line.split("=");
                name = parts[1].trim();
                //System.out.println("Name: " + name);
                //add name keywords to the hashmap
                String keywords [] = name.split(" ");

                //String keywords [] = name.split(" ");
                //System.out.println("Keywords: " + keywords);
                //System.out.println("Keywords: " + Arrays.toString(keywords));
                for (int i = 0; i < keywords.length; i++){
                    keywords[i] = keywords[i].toLowerCase();
                    ArrayList<Integer> tempIndices = new ArrayList<Integer>(); // Create a new ArrayList for each keyword so that indexes dont get deleted
                    //tempIndices.clear(); // clear tempIndices after each keyword so that each word only contains its own indexes
                    if (index.containsKey(keywords[i])){
                        tempIndices = index.get(keywords[i]); //get all the indices bound to the keyword
                        //System.out.println("Adding keyword: " + keywords[i] + " with index: " + countInvestmentIndex);
                        tempIndices.add(countInvestmentIndex); //add the index of the current investment to the list of indices
                    }
                    else { //if the keyword is not in the hashmap create a new key and value list with the current investment index
                        tempIndices.add(countInvestmentIndex);
                    }
                    //now we add the pair of keyword and index to the hashmap
                    //System.out.println("Adding keyword: " + keywords[i] + " with index: " + countInvestmentIndex);
                    index.put(keywords[i], tempIndices);
                }

                line = inputScan.nextLine();
                parts = line.split("=");
                //we need to convert the string to an integer
                quantity = Integer.parseInt(parts[1].trim());


                line = inputScan.nextLine();
                parts = line.split("=");
                price = Double.parseDouble(parts[1].trim());

                line = inputScan.nextLine();
                parts = line.split("=");
                //we need to convert the string to an integer
                bookValue = Double.parseDouble(parts[1].trim());
                line = inputScan.nextLine(); 


                if(type.equalsIgnoreCase("Stock")){
                    Stock stock = new Stock(symbol, name, price, quantity, bookValue);
                    investments.add(stock);
                } else if (type.equalsIgnoreCase("MutualFund")){
                    MutualFund mutualFund = new MutualFund(symbol, name, price, quantity, bookValue);
                    investments.add(mutualFund);
                }
                countInvestmentIndex++;

            }
            inputScan.close();
        } 

        catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file '" + filename + "' for reading.");
        } 
        catch (Exception e) {
            System.out.println("Error: unexpected error while loading data from file '" + filename + "'.");
        } 
        //lets iterate through the hashmap to see if the keywords are added correctly
        //for (String key : index.keySet()){
        //    System.out.println("Key: " + key + " Value: " + index.get(key));
        //}
        
        
    
}
    /**
     * Method to save investments to a file with the following format
     *type = ""
    symbol = ""
    name = ""
    quantity =
    price = ""
    bookValue = ""
     * 
     * 
     */ 

    public static void saveInvestmentsToFile(String filename, ArrayList<Investment> investments){
    
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(filename))){

            for (Investment investment : investments) {
                // Check if the investment is a stock or mutual fund
                if (investment instanceof Stock) {
                    Stock stock = (Stock) investment;
                    writer.println("type = Stock");
                    writer.println("symbol = " + stock.getSymbol());
                    writer.println("name = " + stock.getName());
                    writer.println("quantity = " + stock.getQuantity());
                    writer.println("price = " + stock.getPrice());
                    writer.println("bookValue = " + stock.getBookValue());
                    writer.println(); // Add an empty line after each stock

                }
                else if (investment instanceof MutualFund) {
                    MutualFund mutualFund = (MutualFund) investment;
                    writer.println("type = MutualFund");
                    writer.println("symbol = " + mutualFund.getSymbol());
                    writer.println("name = " + mutualFund.getName());
                    writer.println("quantity = " + mutualFund.getQuantity());
                    writer.println("price = " + mutualFund.getPrice());
                    writer.println("bookValue = " + mutualFund.getBookValue());
                    writer.println(); // Add an empty line after each mutual fund
                }
        
            }
        } 
        catch(FileNotFoundException e) {
            System.out.println("Error: Unable to open file '" + filename + "' for writing.");
        } 
        catch (Exception e) {
            System.out.println("Error: unexpected error while saving data to file '" + filename + "'.");
        }
    }
}
