//Ramy Abdulwahed, 1299971, rabdulwa
package rabdulwa_a1.ePortfolio;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//for gui interface
import java.io.*;

import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PortFolioGUI extends JFrame implements ActionListener { 

    private int currentInvestmentIndex = 0;
    private Portfolio myPortfolio;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    private JPanel buyPanel, sellPanel, updatePanel, getGainPanel, searchPanel;
    private JTextField symbolField, nameField, priceField, quantityField, searchField, priceRangeField, sellSymbolField, sellQuantityField, SellpriceField, updateSymbolField, updateNameField, updatePriceField, totalGainField,  individualGainsArea, searchNameField, searchHighPriceField, searchLowPriceField;
    private JButton buyButton, resetButton, quitButton, sellButton, updateButton, getGainButton, searchButton;
    private CardLayout investmentCardLayout;
    private JPanel cardLayoutContainer;
    private JTextArea indvidualMessageField, searchResultsField, buyMessField, sellMessField, updateMessField;
    private JComboBox<String> investmentChoice;

    /**
     * Constructor for the PortFolioGUI class. This constructor initializes the GUI interface for the ePortfolio program.
     * The GUI interface contains a menu bar with commands to buy, sell, update, get gain, search, and quit the program.
     * The GUI interface also contains panels for buying, selling, updating, getting gain, and searching investments.
     * @param myPortfolio the portfolio to be used in the GUI interface
     */
    public PortFolioGUI(Portfolio myPortfolio) {
        super("ePortfolio");
        setSize (WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myPortfolio = myPortfolio;
        //layout to switch between the panels and the amin panels to hold all the different panels
        investmentCardLayout = new CardLayout();
        cardLayoutContainer = new JPanel(investmentCardLayout);
        add(cardLayoutContainer);

                //command meu
                JMenuBar menuBar = new JMenuBar();
                JMenu menu = new JMenu("Commands");
                JMenuItem buyItem = new JMenuItem("Buy an Investment");
                buyItem.addActionListener(this);
                JMenuItem sellItem = new JMenuItem("Sell an Investment");
                sellItem.addActionListener(this);
                JMenuItem updateItem = new JMenuItem("Updating Investments");
                updateItem.addActionListener(this);
                JMenuItem totalGain = new JMenuItem("Getting total gain");
                totalGain.addActionListener(this);
                JMenuItem searchItem = new JMenuItem("Search Investments");
                searchItem.addActionListener(this);
                JMenuItem quitItem = new JMenuItem("Quit");
                quitItem.addActionListener(this);
                //add the menu items to the menu
                menu.add(buyItem);
                menu.add(sellItem);
                menu.add(updateItem);
                menu.add(totalGain);
                menu.add(searchItem);
                menu.add(quitItem);
        
        
                menuBar.add(menu); //adding menu to the menu bar
                //mainPanel.add(menuBar, BorderLayout.NORTH);
                setJMenuBar(menuBar); //adding the menu bar to the frame
        // Add panels to the card layout
        cardLayoutContainer.add(createWelcomePanel(), "Welcome");
        cardLayoutContainer.add(createBuyPanel(), "Buy an Investment");
        cardLayoutContainer.add(createSellPanel(), "Sell an Investment");
        cardLayoutContainer.add(createUpdatePanel(), "Updating Investments");
        cardLayoutContainer.add(createGetGainPanel(), "Getting total gain");
        cardLayoutContainer.add(createSearchPanel(), "Search Investments");

        setVisible(true);
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    /**
     * Handles the action performed by the user. This action method is called whenever the user clicks on a button or menu item.
     * It will switch the panel to the requested panel based on the command.
     * @param e the action event generated by the button or menu item
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //System.out.println(command);
        if (command.equals("Buy an Investment")) {
            investmentCardLayout.show(cardLayoutContainer, "Buy an Investment");
        }
        else if (command.equals("Sell an Investment")) {
            investmentCardLayout.show(cardLayoutContainer, "Sell an Investment");
        }
        else if (command.equals("Updating Investments")) {

            //this is because the update panel gets updated frequently so each time we need to create a new update panel
            cardLayoutContainer.remove(updatePanel); // Remove the old update panel
            updatePanel = createUpdatePanel(); // Create a new update panel
            cardLayoutContainer.add(updatePanel, "Updating Investments"); // Add the new update panel
            investmentCardLayout.show(cardLayoutContainer, "Updating Investments");
            updatePanel.revalidate();
            updatePanel.repaint();

        }
        else if (command.equals("Getting total gain")) {
            investmentCardLayout.show(cardLayoutContainer, "Getting total gain");
            indvidualMessageField.setText(""); //clear the message field for different inputs
                //get the total gain of the portfolio
            double totalGain = 0.0;
            double gain = 0.0;
            int i = 0;
            //System.out.println("Investments size: " + myPortfolio.getInvestments().size());
            for (Investment shares: myPortfolio.getInvestments()){
                gain = shares.getGain(shares);
                indvidualMessageField.append("Investment name = " + shares.getName() + '\n' + " Quantity = " + shares.getQuantity() + '\n' + " Price = " + shares.getPrice() + '\n' + " BookValue() = " + shares.getBookValue() + '\n'); ;
                indvidualMessageField.append("gain = " + gain + '\n' + '\n');
                totalGain = totalGain + gain; 
                i++;
            }
            indvidualMessageField.append("# of investments: " + i + '\n');
            totalGainField.setText(String.valueOf(totalGain)); //set the total gain field to the totalGain;
        }
        else if (command.equals("Search Investments")) {
            investmentCardLayout.show(cardLayoutContainer, "Search Investments");
        }
        else if (command.equals("Quit")) {
            System.exit(0);
        }
    }


    private class BuyListener implements ActionListener {
        /**
         * Handles the action performed by the user in the Buy panel. This action method is called whenever the user clicks on a button in the Buy panel.
         * It will add a new investment to the portfolio based on the information entered by the user in the Buy panel.
         * @param e the action event generated by the button
         */
        public void actionPerformed(ActionEvent e) {
            buyMessField.setText(""); //clear the message field for different inputs
            String symbol;
            boolean exists = false; // Flag to check if the symbol already exists in the list
            boolean symbolInStocks = false; // Flag to check if the symbol already exists in the stocks list
            boolean symbolInInvestments = false; // Flag to check if the symbol already exists in the mutual funds list
            boolean InvestmentIsStock = false; // Flag to check if the investment type is stock
            boolean InvestmentIsMutualFund = false; // Flag to check if the investment type is mutual fund
            boolean InvestmentIsValid = false;
            //our investment arraylist
            ArrayList<Investment> investments = myPortfolio.getInvestments();
            //our index arraylist

            String investmentType = investmentChoice.getSelectedItem().toString().trim();

            //validate the user input 
           /* while (investmentType.isEmpty() || 
            (!investmentType.equalsIgnoreCase("Stock") && !investmentType.equalsIgnoreCase("MutualFund") 
            && !investmentType.equalsIgnoreCase("S") && !investmentType.equalsIgnoreCase("M"))) {                
                System.out.println("Invalid investment type. Please enter Stock or MutualFund:");
                investmentType = userChoice.nextLine().trim();
            }
*/
            //check if the investment type is stock or mutual fund to help us validate whether the symbol already exists in the list
            if(investmentType.equalsIgnoreCase("Stock") || investmentType.equalsIgnoreCase("S")){
                //System.out.println("Stock");
                InvestmentIsStock = true;
            }
            else {
                //System.out.println("MutualFund");
                InvestmentIsMutualFund = true;
            }


            //check if the symbol already exists in any of the lists
                // Check if the entered symbol already exists in the investments list.
                // Prompt the user to re-enter if the symbol is already used for the wrong type.
                //reset the flags
                exists = false;
                symbolInInvestments = false;
                symbolInStocks = false;
                InvestmentIsValid = false;

                //System.out.println("Enter the symbol of the investment:");
                symbol = symbolField.getText().trim();
                //validate the user input - symbol cannot be empty
                /*while (symbol.isEmpty()){
                    System.out.println("Invalid symbol. Please enter a symbol:");
                    symbol = userChoice.nextLine().trim();
                }
                */
                // First check if the symbol exists in the mutual funds list
                for (Investment shares : investments) { //check if the symbol exists in mutual funds

                    if (shares.getSymbol().equalsIgnoreCase(symbol)) {
                        exists = true; //here we know that the symbol exists in the investments list
                        if (shares.getClass() == Stock.class && InvestmentIsStock) {
                            InvestmentIsValid = true;
                            buyMessField.setText("Symbol already exists in the Investments list as a Stock.");
                        }
                        else if (shares.getClass() == MutualFund.class && InvestmentIsMutualFund) {
                            InvestmentIsValid = true;
                            buyMessField.setText("Symbol already exists in the Investments list as a MutualFund.");
                        }
                        else{
                            buyMessField.setText("Symbol already exists in the Investments list as a different type of investment.");
                            return; 
                        }
//                        symbolInInvestments = true;
                    }
                }
            //if exists if false, then we can move on to the next step, else if exists is true and InvestmentIsValid is true we can move on to the next step
            //the above condition is for checking if the symbol exists, and if it exists in the wrong type of investment list, prompt the user to enter a new symbol

            // If symbol doesn't exist in either list, add new investment

            if (!exists){
                    String name = nameField.getText().trim();
                    Double price;
                    int quantity;

                    try{
                         price = Double.parseDouble(priceField.getText().trim());

                         quantity = Integer.parseInt(quantityField.getText().trim());

                         if (price <= 0 || quantity <= 0) {
                            throw new IllegalArgumentException("Price and quantity cannot be negative or zero.");
                         }

                         
                        if (InvestmentIsStock) {
                            Stock newStock = new Stock(symbol, name, price, quantity);
                            investments.add(newStock);
                            buyMessField.append("\nNew stock added to the portfolio.\n");

                        }
                        else {
                            MutualFund newMutualFund = new MutualFund(symbol, name, price, quantity);
                            investments.add(newMutualFund);
                            buyMessField.append("\nNew mutual fund added to the portfolio.\n");

                        }
                    }
        
                    catch (NumberFormatException error){
                        buyMessField.setText("Invalid input. Please enter a valid price and quantity.");
                        return;
                    }
                    catch (Exception error){
                        buyMessField.setText(error.getMessage());
                        return;
                    }
                    
                }
            

                // If symbol doesn't exist in mutual funds, check the stocks list
                else if (exists && InvestmentIsValid) { //if the symbol exists in the investments list
                    for (Investment shares : investments) {
                        if (shares.getSymbol().equalsIgnoreCase(symbol)) {
                            buyMessField.append("Stock already exists, updating the quantity and price.");
                            try{
                                int quantity = Integer.parseInt(quantityField.getText().trim());

                                Double price = Double.parseDouble (priceField.getText().trim()); 
 
                                if (quantity <= 0) {
                                    throw new IllegalArgumentException("Quantity cannot be negative or zero.");
                                }
                                if (price <= 0) {
                                    throw new IllegalArgumentException("Price cannot be negative or zero.");
                                }
                            
                                shares.setQuantity(shares.getQuantity() + quantity);
                                shares.setPrice(price);
                                if (InvestmentIsStock) {
                                    shares.setBookValue(shares.getBookValue() + (quantity * price + 9.99)); // Adding commission for stock
                                    buyMessField.append("\nStock updated.\n");

                                }
                                else {
                                    shares.setBookValue(shares.getBookValue() + (quantity * price)); // No commission for mutual funds
                                    buyMessField.append("\nMutual Fund updated.\n");
                                }
                                    //show new quantity and price
                                    buyMessField.append("New quantity: " + shares.getQuantity() + "\n");
                                    buyMessField.append("New price: " + shares.getPrice() + "\n");
                        }
                            catch (NumberFormatException error){
                                buyMessField.setText("Invalid input. Please enter a valid price and quantity.");
                        }
                        catch (Exception error){
                            buyMessField.setText(error.getMessage());
                            return;
                        }
                            
                        }
                    }
                }
            

                else { // if the user enters neither stock nor mutualfund

                    buyMessField.setText("Invalid investment type.");
                }
        
        } //actionPerformed brackets
    }
    
    /**
     * Creates the welcome panel for the GUI interface. This panel contains instructions for the user.
     * @return the welcome panel
     */
    private JPanel createWelcomePanel() {
        //JPanel welcomePanel = new JPanel(new GridLayout(1,1));
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to ePortfolio\n\n\n");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel welcomeMessage = new JLabel(
                        "Choose a command from the “Commands” menu to buy or sell \r\n" +
                        "an investment, update prices for all investments, get gain for the \r\n" +
                        "portfolio, search for relevant investments, or quit the program.  ");
        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel , BorderLayout.NORTH);
        welcomePanel.add(welcomeMessage, BorderLayout.CENTER);
        return welcomePanel;
    }

    /**
     * Creates the buy panel for the GUI interface. This panel contains fields for the user to
     * enter the type of investment, symbol, name, quantity, and price of the investment to buy.
     * The panel also contains a reset button and a buy button and a message field to display
     * messages to the user.
     * @return the buy panel
     */
    private JPanel createBuyPanel() {
        //buyPanel = new JPanel(new BorderLayout());
        buyPanel = new JPanel(new GridLayout(2,1));
        buyPanel.setBorder (BorderFactory.createTitledBorder("Buying an Investment"));
        JPanel LeftPanel = new JPanel(new GridLayout(5,2));
        LeftPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //type of investment label
        JLabel typeLabel = new JLabel("Type");
        String[] investmentTypes = {"Stock", "MutualFund"};
        investmentChoice = new JComboBox<>(investmentTypes);
        LeftPanel.add(typeLabel);
        LeftPanel.add(investmentChoice);


        JLabel symbolLabel = new JLabel("Symbol:");
        LeftPanel.add(symbolLabel);
        symbolField = new JTextField();
        LeftPanel.add(symbolField);
        
        //name label and field
        JLabel nameLabel = new JLabel("Name");
        LeftPanel.add(nameLabel);
        nameField = new JTextField();
        LeftPanel.add(nameField);

        //quantity label and field
        JLabel quantityLabel = new JLabel("Quantity");
        LeftPanel.add(quantityLabel);
        quantityField = new JTextField();
        LeftPanel.add(quantityField);

        //price label and field
        JLabel priceLabel = new JLabel("Price");
        LeftPanel.add(priceLabel);
        priceField = new JTextField();
        LeftPanel.add(priceField);

        //add the left panel to the buy panel
        //buyPanel.add(LeftPanel, BorderLayout.CENTER);
        //buyPanel.add(LeftPanel);
        //Right panel with reset and buy buttons
        //reset button
        JPanel RightPanel = new JPanel(new GridLayout(2,1, 10, 10));
        RightPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
/**
 * Clears input fields for symbol, name, quantity, price, and message field.
 * This method is invoked when reset button is clicked, ensuring all fields are reset 
 * to their default empty state, allowing the user to enter new data.
 * 
 * @param e the ActionEvent triggered by the reset button
 */
            public void actionPerformed(ActionEvent e){

                investmentChoice.setSelectedIndex(0); // Reset investment type to Stock by default
                symbolField.setText("");
                nameField.setText("");
                quantityField.setText("");
                priceField.setText("");
                buyMessField.setText("");
            };
        });
        RightPanel.add(resetButton);
        //buy button
        buyButton = new JButton("Buy");
        buyButton.addActionListener(new BuyListener()); //when button is clicked, code in BuyListener is executed
        RightPanel.add(buyButton);

        //add the right panel to the buy panel
        
        //buyPanel.add(RightPanel, BorderLayout.EAST);

        JPanel LeftandRightPanel = new JPanel(new GridLayout(1,2));
        LeftandRightPanel.add(LeftPanel);
        LeftandRightPanel.add(RightPanel);
        buyPanel.add(LeftandRightPanel);

        //messages panel
        JPanel buyMessagePanel = new JPanel(new GridLayout(1,1, 10, 10));
        buyMessagePanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buyMessagePanel.setBorder (BorderFactory.createTitledBorder("Messages"));
        //JLabel buyMessageLabel = new JLabel("Messages");
        buyMessField = new JTextArea();
        buyMessField.setEditable(false); //only for display to user
        JScrollPane buyMessageScroll = new JScrollPane(buyMessField); //scroll bar for the message field
        buyMessageScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Never show horizontal scroll bar
        buyMessageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
        //buyMessagePanel.add(buyMessageLabel);
        buyMessagePanel.add(buyMessageScroll);
        //buyPanel.add(buyMessagePanel, BorderLayout.SOUTH);
        buyPanel.add(buyMessagePanel);
        return buyPanel;
    }
    
    /**
     * Creates the sell panel for the GUI interface. This panel contains the symbol, quantity, and price fields to sell an investment
     * @return the sell panel
     */
    private JPanel createSellPanel() {
        //sellPanel = new JPanel(new BorderLayout());
        sellPanel = new JPanel(new GridLayout(2,1));
        sellPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sellPanel.setBorder (BorderFactory.createTitledBorder("Selling an Investment"));

        //symbol label and field
        JPanel sellLeftPanel = new JPanel(new GridLayout(3,2));
        sellLeftPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel sellSymbolLabel = new JLabel ("Symbol:");
        sellSymbolField = new JTextField();
        sellLeftPanel.add(sellSymbolLabel);
        sellLeftPanel.add(sellSymbolField);

        //quantity label and field
        JLabel sellQuantityLabel = new JLabel("Quantity");
        sellQuantityField = new JTextField();
        sellLeftPanel.add(sellQuantityLabel);
        sellLeftPanel.add(sellQuantityField);

        //price label and field
        JLabel priceLabel = new JLabel("Price");
        SellpriceField = new JTextField();
        sellLeftPanel.add(priceLabel);
        sellLeftPanel.add(SellpriceField);

        //add the left panel to the sell panel
        sellPanel.add(sellLeftPanel, BorderLayout.CENTER);

        //Right panel with reset and buy buttons
        //reset button
        JPanel sellRightPanel = new JPanel(new GridLayout(2,1, 10, 10));
        sellRightPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
/**
 * Clears input fields for symbol, quantity, price, and message field.
 * This method is invoked when reset button is clicked.
 * 
 * @param e the ActionEvent triggered by the reset button
 */
            public void actionPerformed(ActionEvent e){
                sellSymbolField.setText("");
                sellQuantityField.setText("");
                SellpriceField.setText("");
                sellMessField.setText("");
            };
        });
        sellRightPanel.add(resetButton);
        //sell button
        sellButton = new JButton("Sell");
        sellButton.addActionListener(new ActionListener(){
        /**
         * Handles the action performed by the user in the Sell panel. This method is called whenever the user clicks on a button in the Sell panel.
         * It will sell the investment based on the investment information entered by the user in the Sell panel ex. symbol, quantity, and price.
         * @param e the action event generated by the button
         */
            public void actionPerformed(ActionEvent e){
                sellMessField.setText(""); //clear the message field for different inputs
                boolean symbolExists = false;
                ArrayList <Investment> investments = myPortfolio.getInvestments();
                boolean isValidInput = true;
                String symbol = sellSymbolField.getText().trim();                
                try{
                    if (symbol.isEmpty()){
                        throw new IllegalArgumentException("Symbol field is empty. Please enter valid symbol.");
                    }
                    if (quantityField.getText().isEmpty() || SellpriceField.getText().isEmpty()){
                        throw new IllegalArgumentException("Quantity or price field is empty. Please enter valid quantity and price.");
                    }
                    //if a string is entered instead of a number, for the quantity or price, throw an exception


                    if (Double.parseDouble(sellQuantityField.getText()) <= 0 || Double.parseDouble(SellpriceField.getText()) <0){
                        throw new IllegalArgumentException("Quantity or price cannot be negative or zero.");
                    }
                    //check if the symbol is a number
                    //Double.parseDouble(symbol);
                    //throw new IllegalArgumentException("Invalid input. Expected a string but got a number.");
                }

                catch (NumberFormatException ex){
                    sellMessField.setText(ex.getMessage());
                    isValidInput = false;
                }
                catch (IllegalArgumentException ex){
                    sellMessField.setText(ex.getMessage());
                    isValidInput = false;

                }
                catch (Exception ex){
                    sellMessField.setText(ex.getMessage());
                    isValidInput = false;
                }

                //if input is invalid, return no need to proceed
                if (!isValidInput){
                    return;
                }
                //System.out.println("Symbol: " + symbol);
                //words = investmentType.split(" ");
                for (int i = 0; i <investments.size(); i++) {
                    symbolExists =false;
                    Investment investment = investments.get(i); 
                    if (investment.getSymbol().equalsIgnoreCase(symbol)){
                        symbolExists = true;
                    }
                
                
                
                    //if the investment is stock or mutual fund and matches the symbol entered by the user then proceed to sell
                    if (symbolExists){
                        //System.out.println("Symbol exists in the portfolio");
                        try{
                           int quantity = Integer.parseInt(sellQuantityField.getText());
                           Double price = Double.parseDouble(SellpriceField.getText());

                           if (quantity <= 0){
                                 throw new IllegalArgumentException("Invalid quantity. Quantity cannot be negative or zero.");
                           }
                            if (price <= 0){
                                    throw new IllegalArgumentException("Invalid price. Price cannot be negative or zero.");
                            }

                           double payment, gain;
                           // if the quantity is equal to the total quantity of the stock
                           if (quantity == investment.getQuantity()){
                            sellMessField.append("\nQuantity is equal to the total quantity of the stock\n");
                            investments.remove(i);
                            i--; //decrement the index
                               //calculate the payment and gain
                               payment = quantity * price - 9.99; 
                               gain = payment - investment.getBookValue();
                               sellMessField.append("Payment: $" + payment + "\n");
                               sellMessField.append("Gain: $" + gain + "\n");
                               sellMessField.append("investment sold all.\n");
                               //break;
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
                               sellMessField.append("Payment: $" + payment + "\n");
                               sellMessField.append("Gain: $" + (payment - bookValueSold) + "\n");
                               sellMessField.append( quantity + " share(s) sold.\n");
                               //break;
                           }
                           return; 
                           
                        }
                        catch (NumberFormatException ex){
                            sellMessField.setText(ex.getMessage());
                            return;
                        }
                        catch (IllegalArgumentException ex){
                            sellMessField.setText(ex.getMessage());
                            return;
                        }
                        catch (Exception ex){
                            sellMessField.setText(ex.getMessage());
                            return;
                        }
                            
                    } //symbol exists brackets
                
                    //else {
                    //    sellMessField.setText("Invalid symbol. Symbol doesn't exist in the portfolio");
                    //}

            
                }
                if (!symbolExists){
                    sellMessField.setText("Invalid symbol. Symbol doesn't exist in the portfolio");
                }
        } //actionPerformed brackets
        }); //outer brackets
        sellRightPanel.add(sellButton);

        JPanel sellLeftandRightPanel = new JPanel(new GridLayout(1,2));
        sellLeftandRightPanel.add(sellLeftPanel);
        sellLeftandRightPanel.add(sellRightPanel);
        //add the right panel to the sell panel
//        sellPanel.add(sellRightPanel, BorderLayout.EAST);
        sellPanel.add(sellLeftandRightPanel);
        //sellPanel.add(RightPanel, BorderLayout.EAST);

        //messages panel
        JPanel sellMessagePanel = new JPanel(new GridLayout(1,1, 10, 10));
        sellMessagePanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sellMessagePanel.setBorder (BorderFactory.createTitledBorder("Messages"));
        //JLabel sellMessageLabel = new JLabel("Messages");
        sellMessField = new JTextArea();
        sellMessField.setEditable(false); //only for display to user
        JScrollPane sellMessageScroll = new JScrollPane(sellMessField); //scroll bar for the message field
        sellMessageScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        sellMessageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //sellMessagePanel.add(sellMessageLabel);
        sellMessagePanel.add(sellMessageScroll);
        //add the message panel to the sell panel south
        sellPanel.add(sellMessagePanel, BorderLayout.SOUTH);

        return sellPanel;
    }

    


/**
 * Creates the update panel for the GUI interface. This panel contains fields for the user to
 * enter the symbol, name, and price of an investment to update. It also includes buttons
 * to navigate to previous and next investments and save the updates. Additionally, a message
 * field is provided to display messages to the user.
 * @return the update panel
 */
    private JPanel createUpdatePanel() {
        // Initialize currentInvestmentIndex to 0
        currentInvestmentIndex = 0;
        //updatePanel = new JPanel(new BorderLayout());
        updatePanel = new JPanel(new GridLayout(2,1));
        updatePanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        updatePanel.setBorder (BorderFactory.createTitledBorder("Updating an Investment"));

        //left panel with symbol name and price
        JPanel updateLeftPanel = new JPanel (new GridLayout(3,2));
        //symbol label and field
        JLabel updateSymbolLabel = new JLabel("Symbol:");
        updateSymbolField = new JTextField();
        updateSymbolField.setEditable(false); //only for display, user cannot write
        updateSymbolField.setVisible(true); 
        updateLeftPanel.add(updateSymbolLabel);
        updateLeftPanel.add(updateSymbolField);

        //name label and field
        JLabel updateNameLabel = new JLabel("Name:");
        updateNameField = new JTextField();
        updateNameField.setEditable(false); //only for display, user cannot write
        updateNameField.setVisible(true);
        updateLeftPanel.add(updateNameLabel);
        updateLeftPanel.add(updateNameField);

        //price label and field
        JLabel updatePriceLabel = new JLabel("Price:");
        updatePriceField = new JTextField();
        updatePriceField.setEditable(true); //user can write
        updateLeftPanel.add(updatePriceLabel);
        updateLeftPanel.add(updatePriceField);

        //add the left panel to the update panel
        //updatePanel.add(updateLeftPanel, BorderLayout.CENTER);

        //Right panel with reset and update button
        JPanel updateRightPanel = new JPanel(new GridLayout(3,1, 10, 10));
        updateRightPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //prev button
        JButton nextButton = new JButton("Next");
        JButton previousButton = new JButton("Prev");
        //when the panel is first created

        // Enable previous and next buttons if there are investments

        if (!myPortfolio.getInvestments().isEmpty()) {
            //System.out.println("Entered. Investments size: " + myPortfolio.getInvestments().size());
            //currentInvestmentIndex = 0; // set the current index to the first investment
            String symbol = myPortfolio.getInvestments().get(currentInvestmentIndex).getSymbol();
            String name = myPortfolio.getInvestments().get(currentInvestmentIndex).getName();
            double price = myPortfolio.getInvestments().get(currentInvestmentIndex).getPrice();

            //System.out.println("Symbol: " + symbol + " Name: " + name + " Price: " + price);
        
            updateNameField.setText(name);
            updateSymbolField.setText(symbol);
            updatePriceField.setText(String.valueOf(price));
        
            // enable or disable the previous and next buttons
            if (myPortfolio.getInvestments().size() > 1) {
                nextButton.setEnabled(true);
            } else {
                nextButton.setEnabled(false);
            }
            previousButton.setEnabled(false); // disable the previous button since we are at the first investment
        }
        else{
            //System.out.println("Empty. Investments size: " + myPortfolio.getInvestments().size());
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
        }
        previousButton.addActionListener(new ActionListener() {
        /**
         * Action listener for the Previous button in the Update panel.
         * When the Previous button is clicked, the program will update the fields with the previous investment in the portfolio.
         * If the current index is the first investment, the Previous button will be disabled.
         * @param e the ActionEvent generated by the button
         */ 
            public void actionPerformed(ActionEvent e) {
                updateMessField.setText(""); //clear the message field for different inputs
                ArrayList <Investment> investments = myPortfolio.getInvestments();
                //update the fields with the previous
                if (currentInvestmentIndex > 0){
                    currentInvestmentIndex--; //decrement to previous investment
                    String symbol = investments.get(currentInvestmentIndex).getSymbol();
                    String name = investments.get(currentInvestmentIndex).getName();
                    Double price = investments.get(currentInvestmentIndex).getPrice();

                    //update the fields with the new current index
                    updateNameField.setText(name);
                    updateSymbolField.setText(symbol);
                    updatePriceField.setText(String.valueOf(price));

                }
                nextButton.setEnabled(true); // enable "Next" because we're moving back
            if (currentInvestmentIndex == 0) {
                previousButton.setEnabled(false); // disable Prev if at the first investment
            }
            
            }
        });
        updateRightPanel.add(previousButton);

        //next button
        
        nextButton.addActionListener(new ActionListener() {
        /**
         * Action listener for the Next button in the Update panel.
         * When the Next button is clicked, the program will update the fields with the next investment in the portfolio.
         * If the current index is the last investment, the Next button will be disabled.
         * @param e the ActionEvent generated by the button
         */
            public void actionPerformed(ActionEvent e) {
                updateMessField.setText(""); //clear the message field for different inputs
               // System.out.println(currentInvestmentIndex);
               // System.out.println(myPortfolio.getInvestments().size());
                ArrayList <Investment> investments = myPortfolio.getInvestments();
                //update the fields with the next
                if (currentInvestmentIndex < investments.size() - 1){
                    currentInvestmentIndex++; //increment to next investment
                    String symbol = investments.get(currentInvestmentIndex).getSymbol();
                    String name = investments.get(currentInvestmentIndex).getName();
                    Double price = investments.get(currentInvestmentIndex).getPrice();

                    System.out.println(investments.get(currentInvestmentIndex).toString());
                    //update the fields with the new current index
                    updateNameField.setText(name);
                    updateSymbolField.setText(symbol);
                    updatePriceField.setText(String.valueOf(price));    
                    previousButton.setEnabled(true); // Always enable previous button if more than one investment

                    //System.out.println("Current index: " + currentInvestmentIndex);
                    //System.out.println("symbol: " + symbol);
                    //System.out.println("name: " + name);
                    //System.out.println("price: " + price);



                }
                if (currentInvestmentIndex == investments.size() - 1){
                    nextButton.setEnabled(false); // Disable next button if at the last investment
                }

            }
            });
        updateRightPanel.add(nextButton);


        //save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                //if the array is empty, then we cannot update
                updateMessField.setText(""); //clear the message field for different inputs
                if (myPortfolio.getInvestments().isEmpty()){
                    updateMessField.setText("No investments to update.");
                }
                else if (updatePriceField.getText().isEmpty()){
                    updateMessField.setText("Please enter price to update.");
                }
                else{
                    ArrayList <Investment> investments = myPortfolio.getInvestments();
                try{
                    Double price = Double.parseDouble(updatePriceField.getText());
                    investments.get(currentInvestmentIndex).setPrice(price);
                }
                catch (NumberFormatException ex){
                    updateMessField.setText("Invalid input. Please enter a valid price.");
                    return;
                }
                catch (Exception ex){
                    updateMessField.setText(ex.getMessage());
                    return;
                }
                
                //update the message field
                updateMessField.setText("Update successful!");
                //test
                //System.out.println(investments.get(currentInvestmentIndex).getPrice());
            }
        }
        });
        updateRightPanel.add(saveButton);

        //add the right panel to the update panel
        //updatePanel.add(updateRightPanel, BorderLayout.EAST);
        JPanel updateLeftandRightPanel = new JPanel(new GridLayout(1,2));
        updateLeftandRightPanel.add(updateLeftPanel);
        updateLeftandRightPanel.add(updateRightPanel);
        updatePanel.add(updateLeftandRightPanel); //add the right and left panel to the update panel
        //add message panel
        JPanel updateMessagePanel = new JPanel(new GridLayout(1,1, 10, 10));
        updateMessagePanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
        updateMessagePanel.setBorder (BorderFactory.createTitledBorder("Messages"));
        //JLabel updateMessageLabel = new JLabel("Messages");
        updateMessField = new JTextArea();
        updateMessField.setEditable(false); //only for display to user
        JScrollPane updateMessageScroll = new JScrollPane(updateMessField); //scroll bar for the message field
        updateMessageScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        updateMessageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //updateMessagePanel.add(updateMessageLabel);
        updateMessagePanel.add(updateMessageScroll);
        //add the message panel to the update panel south
        updatePanel.add(updateMessagePanel);

        return updatePanel;
    }

    /**
     * Creates the get gain panel for the GUI interface. This panel contains a field to display the total gain of the portfolio.
     * @return the get gain panel
     */
    private JPanel createGetGainPanel() {
        // Create the main panel for "Getting total gain"
        JPanel getGainPanel = new JPanel(new BorderLayout());
        getGainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        getGainPanel.setBorder(BorderFactory.createTitledBorder("Getting total gain"));

        // Top Section: Total Gain
        JPanel topGainPanel = new JPanel(new BorderLayout());
        topGainPanel.setBorder(BorderFactory.createTitledBorder("Getting total gain")); // Titled border for clarity

    // Sub-panel for Total Gain Input
    JPanel totalGainPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // GridLayout for label and text field
    JLabel totalGainLabel = new JLabel("Total Gain:");
    totalGainField = new JTextField();
    totalGainField.setEditable(false); // Display-only field
    totalGainPanel.add(totalGainLabel);
    totalGainPanel.add(totalGainField);

    topGainPanel.add(totalGainPanel, BorderLayout.CENTER); // Add total gain panel to the top section

    // Bottom Section: Individual Gains
    JPanel bottomGainPanel = new JPanel(new BorderLayout());
    bottomGainPanel.setBorder(BorderFactory.createTitledBorder("Individual gains")); // Titled border for clarity

    // Scrollable Text Area for Individual Gains
    indvidualMessageField = new JTextArea();
    indvidualMessageField.setEditable(false); // Display-only text area
    JScrollPane scrollPane = new JScrollPane(indvidualMessageField);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Never show horizontal scroll bar
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
    bottomGainPanel.add(scrollPane, BorderLayout.CENTER); // Add scrollable area to bottom section

    // Add sections to the main panel
    getGainPanel.add(topGainPanel, BorderLayout.NORTH); // Top section (Total Gain)
    getGainPanel.add(bottomGainPanel, BorderLayout.CENTER); // Bottom section (Individual Gains)


    return getGainPanel;
    }

    /**
     * Creates the search panel for the GUI interface. This panel contains fields for the user to
     * enter the name, high price, and low price of an investment to search for. It also includes a search button
     * and a message field to display messages to the user.
     * @return the search panel
     */
    private JPanel createSearchPanel() {
         //search panel
         searchPanel = new JPanel(new GridLayout(2,1));
         //searchPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));
         searchPanel.setBorder(BorderFactory.createTitledBorder("Search Investments"));
 
         JPanel searchLeftPanel = new JPanel(new GridLayout(4,1));
         
         //symbol and textfield. 
         JPanel searchSymbolPanel = new JPanel(new GridLayout(1,2));
         JLabel searchLabel = new JLabel("Symbol");
         searchField = new JTextField();
         searchSymbolPanel.add(searchLabel);
         searchSymbolPanel.add(searchField);
         searchLeftPanel.add(searchSymbolPanel);
 
 
         //name and textfield
         JPanel searchNamePanel = new JPanel(new GridLayout(1,2));
         JLabel searchNameLabel = new JLabel("Name and keywords");
         searchNameField = new JTextField();
         searchNamePanel.add(searchNameLabel);
         searchNamePanel.add(searchNameField);
         searchLeftPanel.add(searchNamePanel);
 
 
         //low price and high price labels and symbols
         JPanel searchLowPricePanel = new JPanel(new GridLayout(1,2));
         JPanel searchHighPricePanel = new JPanel(new GridLayout(1,2)); 
         JLabel searchLowPriceLabel = new JLabel("Low Price");
         JLabel searchHighPriceLabel = new JLabel("High Price");
         searchLowPriceField = new JTextField();
         searchHighPriceField = new JTextField();
         searchLowPricePanel.add(searchLowPriceLabel);
         searchLowPricePanel.add(searchLowPriceField);
         searchHighPricePanel.add(searchHighPriceLabel);
         searchHighPricePanel.add(searchHighPriceField);
         searchLeftPanel.add(searchLowPricePanel);
         searchLeftPanel.add(searchHighPricePanel);
 
         
 
         //add to main panel
         searchPanel.add(searchLeftPanel, BorderLayout.CENTER);
 
         //Right panel with reset and search buttons
         JPanel searchRightPanel = new JPanel(new GridLayout(2,1));
         resetButton = new JButton("Reset");
         resetButton.addActionListener(new ActionListener() {
            
/**
 * Clears the input fields in the search panel when the reset button is pressed.
 * This method sets the text of the search fields symbol, name, low price, high price
 * to an empty string
 * @param e the ActionEvent generated by the button
 */
         public void actionPerformed(ActionEvent e) {

                searchField.setText("");
                searchNameField.setText("");
                searchLowPriceField.setText("");
                searchHighPriceField.setText("");
                searchResultsField.setText("");
            }
             
         });
         searchButton = new JButton("Search");
         searchButton.addActionListener(new ActionListener() {

    /**
     * This method is called when the search button is pressed. It gets the input from the
     * search panel and prints the investments that match the user input in the search results
     * text field. If the user enters nothing in any of the fields, all the investments are printed
     * in the search results text field. If the user enters a symbol only, the investments that have
     * that symbol are printed. If the user enters a symbol and a keyword, the investments that have
     * that symbol and have the keyword in their name are printed. If the user enters a symbol and a
     * price range, the investments that have that symbol and have a price within the price range are
     * printed. If the user enters a keyword only, the investments that have the keyword in their name
     * are printed. If the user enters a keyword and a price range, the investments that have the
     * keyword in their name and have a price within the price range are printed. If the user enters
     * a price range only, the investments that have a price within the price range are printed. If
     * the user enters invalid input, the search results text field will print "Invalid input"
     * @param e the ActionEvent generated by the button
     */
            public void actionPerformed(ActionEvent e) {
                searchResultsField.setText(""); //clear the search results field for different searches
                boolean symbolEmpty = false;
                boolean keywordEmpty = false;
                //boolean priceRangeEmpty = false;
                double lowerBound = 0.0; //price cannot be negative
                double upperBound = 0.0;
                String symbol = searchField.getText().trim();
                String search = searchNameField.getText().trim();
                String lowPrice = searchLowPriceField.getText().trim();
                String highPrice = searchHighPriceField.getText().trim();
                String[] words = search.split(" ");
                ArrayList <Integer> result = new ArrayList <Integer>();
                ArrayList <Investment> investments = myPortfolio.getInvestments();
                HashMap<String, ArrayList <Integer>> index = myPortfolio.getIndex();
                boolean priceRangeEmpty = true;
                //HashMap<String, ArrayList <Integer>> index = new HashMap<String, ArrayList <Integer>>();
                //check if the user has entered any of the fields to know what to search for
                if (symbol.isEmpty()){
                    symbolEmpty = true;
                }
                if (search.isEmpty()){
                    keywordEmpty = true;
                }
                if (!searchLowPriceField.getText().trim().isEmpty() || !searchHighPriceField.getText().trim().isEmpty()) {
                    priceRangeEmpty = false;
                }


                //test
                //System.out.println("symbolEmpty = " + symbolEmpty);
                //System.out.println("keywordEmpty = " + keywordEmpty);
                //System.out.println("priceRangeEmpty = " + priceRangeEmpty);

                //iterate over the ArrayList and print the stocks that match the user input
                for (Investment shares: investments){
                    //if the user has not entered any of the fields, print all the investments
                    if (symbolEmpty && keywordEmpty && priceRangeEmpty){
                        searchResultsField.append(shares.toString() + '\n');
                    }
                    //if the user has entered the symbol only
                    else if (!symbolEmpty && keywordEmpty && priceRangeEmpty){
                        if (shares.getSymbol().equalsIgnoreCase(symbol)){
                            searchResultsField.append(shares.toString() + '\n');
                        }
                    }
                    //if user has entered the symbol and the keyword
                    else if(!symbolEmpty && !keywordEmpty && priceRangeEmpty){
                        // check if first keyword is in the index if not break;
                        boolean found = true;
                         for (String word: words){
                            if (!(shares.getName().toLowerCase().contains(word.toLowerCase()))){
                                found = false;
                                break;
                            }

                        }
                        //print only if the investments match the symbol at this point
                        if (found && shares.getSymbol().equalsIgnoreCase(symbol)){
                            searchResultsField.append(shares.toString() + '\n');
                        } 

                    }
                
                    //user has entered symbol and priceRange
                    else if (!symbolEmpty && keywordEmpty && !priceRangeEmpty){ //if the user has entered both upper and lower bounds
                        try{
                            //check if the user has not entered negative numbers
                            //System.out.println("lowPrice = " + lowPrice);
                            //System.out.println("highPrice = " + highPrice);
                            if (Double.parseDouble(lowPrice.trim()) < 0.0 || Double.parseDouble(highPrice.trim()) < 0.0){
                                throw new IllegalArgumentException("Price cannot be negative");
                            }

                            if (!lowPrice.isEmpty() && !highPrice.isEmpty()) {
                                lowerBound = Double.parseDouble(lowPrice.trim());
                                upperBound = Double.parseDouble(highPrice.trim());
                            }
                            else if (!lowPrice.isEmpty() && highPrice.isEmpty()) { //if the user has entered only the lower bound
                                lowerBound = Double.parseDouble(lowPrice.trim());
                                upperBound = Double.POSITIVE_INFINITY;
                            }
                            else if (lowPrice.isEmpty() && !highPrice.isEmpty()) { //if the user has entered only the upper bound
                                lowerBound = 0.0;
                                upperBound = Double.parseDouble(highPrice.trim());
                            }
                            else { //if the user has not entered any bounds. NOT POSSIBLE
                                lowerBound = 0.0;
                                upperBound = Double.POSITIVE_INFINITY;
                            }
                        } catch (NumberFormatException ex) {
                            searchResultsField.append("Invalid input. Please enter a valid price range.\n");
                        return;
                        }
                        catch (IllegalArgumentException ex){
                            searchResultsField.append(ex.getMessage());
                            return;
                        }
                        catch (Exception ex){
                            searchResultsField.append(ex.getMessage());
                            return;
                        }
                        if (lowerBound > upperBound){
                            searchResultsField.append("Invalid input. Lower bound cannot be greater than upper bound.\n");
                            return;
                        }


                        if (shares.getSymbol().equalsIgnoreCase(symbol) && shares.getPrice() >= lowerBound && shares.getPrice() <= upperBound){
                            searchResultsField.append(shares.toString() + "\n");
                        }

                    }
                    
                    //if the user has entered the keyword only
                    else if (symbolEmpty && !keywordEmpty && priceRangeEmpty){
                        boolean found = true;
                         for (String word: words){
                            if (!(shares.getName().toLowerCase().contains(word.toLowerCase()))){
                                found = false;
                                break;
                            }

                        }
                        //print only if the investments match the symbol at this point
                        if (found){
                            searchResultsField.append(shares.toString() + '\n');
                        } 

                    }//else if
                    //if the user has entered the keyword and price range
                    else if (symbolEmpty && !keywordEmpty && !priceRangeEmpty){
                        //keyword checking
                        boolean found = true;
                         for (String word: words){
                            if (!(shares.getName().toLowerCase().contains(word.toLowerCase()))){
                                found = false;
                                break;
                            }

                        }
                        
                        if (found){
                            
                        
                        //price range
                        try{
                            //check if the user has not entered negative numbers
                            if (Double.parseDouble(lowPrice.trim()) < 0.0 || Double.parseDouble(highPrice.trim()) < 0.0){
                                throw new IllegalArgumentException("Price cannot be negative");
                            }

                            if (!lowPrice.isEmpty() && !highPrice.isEmpty()) {
                                lowerBound = Double.parseDouble(lowPrice.trim());
                                upperBound = Double.parseDouble(highPrice.trim());
                            }
                            else if (!lowPrice.isEmpty() && highPrice.isEmpty()) { //if the user has entered only the lower bound
                                lowerBound = Double.parseDouble(lowPrice.trim());
                                upperBound = Double.POSITIVE_INFINITY;
                            }
                            else if (lowPrice.isEmpty() && !highPrice.isEmpty()) { //if the user has entered only the upper bound
                                lowerBound = 0.0;
                                upperBound = Double.parseDouble(highPrice.trim());
                            }
                            else { //if the user has not entered any bounds. NOT POSSIBLE
                                lowerBound = 0.0;
                                upperBound = Double.POSITIVE_INFINITY;
                            }
                        } catch (NumberFormatException ex) {
                            searchResultsField.append("Invalid input. Please enter a valid price range.\n");
                            return;
                        }
                        catch (IllegalArgumentException ex){
                            searchResultsField.append(ex.getMessage());
                            return;
                        }
                        catch (Exception ex){
                            searchResultsField.append(ex.getMessage());
                            return;
                        }
                        if (lowerBound > upperBound){
                            searchResultsField.append("Invalid input. Lower bound cannot be greater than upper bound.\n");
                            return;
                        }

                        //check if the price range matches and then print if true,
                        if (shares.getPrice() >= lowerBound && shares.getPrice() <= upperBound){
                            searchResultsField.append(shares.toString() + "\n");
                        }   
                    } //if bracket
                    //else{
                    //    searchResultsField.append("No investments found"); 
                    //}

                }
                //if the user has entered the price range only

                else if (symbolEmpty && keywordEmpty && !priceRangeEmpty){
                    try{
                        //check if the user has not entered negative numbers
                        if (Double.parseDouble(lowPrice.trim()) < 0.0 || Double.parseDouble(highPrice.trim()) < 0.0){
                            throw new IllegalArgumentException("Price cannot be negative");
                        }

                        if (!lowPrice.isEmpty() && !highPrice.isEmpty()) {
                            lowerBound = Double.parseDouble(lowPrice.trim());
                            upperBound = Double.parseDouble(highPrice.trim());
                        }
                        else if (!lowPrice.isEmpty() && highPrice.isEmpty()) { //if the user has entered only the lower bound
                            lowerBound = Double.parseDouble(lowPrice.trim());
                            upperBound = Double.POSITIVE_INFINITY;
                        }
                        else if (lowPrice.isEmpty() && !highPrice.isEmpty()) { //if the user has entered only the upper bound
                            lowerBound = 0.0;
                            upperBound = Double.parseDouble(highPrice.trim());
                        }
                        else { //if the user has not entered any bounds. NOT POSSIBLE
                            lowerBound = 0.0;
                            upperBound = Double.POSITIVE_INFINITY;
                        }
                    } catch (NumberFormatException ex) {
                        searchResultsField.append("Invalid input. Please enter a valid price range.\n");
                    return;
                    }
                    catch (IllegalArgumentException ex){
                        searchResultsField.append(ex.getMessage());
                        return;
                    }
                    catch (Exception ex){
                        searchResultsField.append(ex.getMessage());
                        return;
                    }
                    if (lowerBound > upperBound){
                        searchResultsField.append("Invalid input. Lower bound cannot be greater than upper bound.\n");
                        return;
                    }
                    /*for (Investment share: investments){
                        if (share.getPrice() >= lowerBound && share.getPrice() <= upperBound){
                            searchResultsField.append(share.toString() + "\n");
                        }

                    }*/
                    if (shares.getPrice() >= lowerBound && shares.getPrice() <= upperBound){
                        searchResultsField.append(shares.toString() + "\n");
                    }
                }
            

                //if user enters inavlid input such as more than one symbol ...
                else{
                    searchResultsField.append("Invalid input");
                }
            } //end of for loop

            }
         });
         searchRightPanel.add(resetButton);
         searchRightPanel.add(searchButton);
 
         JPanel leftandRightPanel = new JPanel(new GridLayout(1,2));
         leftandRightPanel.add(searchLeftPanel);
         leftandRightPanel.add(searchRightPanel);
         //search results panel
         JPanel searchResultsPanel = new JPanel(new BorderLayout());
         searchResultsPanel.setBorder(BorderFactory.createTitledBorder("Search Results"));
         searchResultsField = new JTextArea();
         searchResultsField.setEditable(false);
         JScrollPane searchResultsScroll = new JScrollPane(searchResultsField);
         searchResultsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         searchResultsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         searchResultsPanel.add(searchResultsScroll, BorderLayout.CENTER);
 
         searchPanel.add(leftandRightPanel);
         searchPanel.add(searchResultsPanel);

        return searchPanel;
    }
}
