package rabdulwa_a1.ePortfolio;
/**
 * MutualFund class is for investment in a mutual fund.
 * It Contains details about the mutual fund symbol, name, price, quantity, and book value.
 * It also contains methods to get and set the values of the different attributes of a mutual fund investment.
 */
public class MutualFund extends Investment{
    /**
     * symbol: the symbol of the mutual fund which is unique
     * name: the name of the mutual fund
     * price: the price of the mutual fund
     * quantity: the quantity of the mutual fund
     * bookValue: the book value of the mutual fund
     */
    
    /**
     * Constructor for the MutualFund class
     * @param symbol the symbol of the mutual fund must be unique in the portfolio
     * @param name the name of the mutual fund
     * @param price the price of the mutual fund
     * @param quantity the quantity of the mutual fund
     */

    public MutualFund(String symbol, String name, double price, int quantity){
        super(symbol, name, price, quantity);
        this.bookValue = (price * quantity);
    }

    /**
     * Method to get the symbol of the mutual fund
     * @return the symbol of the mutual fund which is unique
     */

     public MutualFund(String symbol, String name, double price, int quantity, double bookValue){
        super(symbol, name, price, quantity);
        //this.bookValue = bookValue;
        this.bookValue = (price * quantity);
    }

    // Copy constructor
    public MutualFund(MutualFund other) {
        super(other); // Call the parent class copy constructor
        this.quantity = other.quantity;
        this.bookValue = other.bookValue;
    }

    /**
     * Method to calculate the gain of a mutual fund investment
     * @param shares the investment to calculate the gain for
     * @return the gain of the mutual fund investment
     */
    public Double getGain(Investment shares){
        return (shares.getQuantity() * shares.getPrice() -45.00) - shares.getBookValue();
    }
}