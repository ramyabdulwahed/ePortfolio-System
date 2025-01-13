package rabdulwa_a1.ePortfolio;


/**
 * Stock class is for investment in a stock.
 * It Contains details about the stock symbol, name, price, quantity, and book value.
 * It also contains methods to get and set the values of the different attributes of a stock investment.
 */

public class Stock extends Investment{


    public Stock(String symbol, String name, double price, int quantity){

        super(symbol, name, price, quantity);
        this.bookValue = (price * quantity) + 9.99;
    }
    /**
     * Method to get the symbol of the stock
     * @return the symbol of the stock which is unique
     */
    /**
     * Constructor used when we have the bookvalue from the file
     * @param symbol
     * @param name
     * @param price
     * @param quantity
     * @param bookValue
     */
     public Stock(String symbol, String name, double price, int quantity, double bookValue){
         super(symbol, name, price, quantity);
         this.bookValue = bookValue;
     }
     // Copy constructor
     public Stock(Stock other) {
        super(other); // Call the parent class copy constructor
        this.quantity = other.quantity;
    }

    /**
     * Method to calculate the gain of a stock investment
     * @param shares the investment to calculate the gain for
     * @return the gain of the stock investment
     */
     public Double getGain(Investment shares){
        return (shares.getQuantity() * shares.getPrice() -9.99) - shares.getBookValue();
    }
}