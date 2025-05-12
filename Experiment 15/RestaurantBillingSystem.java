import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class RestaurantBillingSystem extends JFrame implements ActionListener {

    // Declare UI components (labels, text fields, buttons, and text area)
    JLabel pizzaLabel, burgerLabel, drinkLabel;
    JTextField pizzaQty, burgerQty, drinkQty;
    JButton generateBillBtn, resetBtn, saveBillBtn, exitBtn;
    JTextArea billArea;

    public RestaurantBillingSystem() {
        // Basic setup for the JFrame
        setTitle("Restaurant Billing System");
        setSize(500, 650);  // Set window size
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Close the window when the user clicks "X"

        // Set the layout for the JFrame (BorderLayout for main window)
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255));  // Light background color

        // Create and configure the header label
        JLabel header = new JLabel("Restaurant Billing System", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));  // Set header font
        header.setForeground(Color.WHITE);  // Set text color to white
        header.setOpaque(true);  // Make label opaque so background color shows
        header.setBackground(new Color(70, 130, 180));  // Set background color for header
        add(header, BorderLayout.NORTH);  // Add header to the top of the frame

        // Create center panel with BorderLayout for inputs and bill display
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(245, 245, 245));  // Light gray background
        add(centerPanel, BorderLayout.CENTER);

        // Create a panel to hold item quantity input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));  // Grid for 3 rows and 2 columns
        inputPanel.setBackground(new Color(224, 255, 255));  // Light cyan background
        inputPanel.setBorder(BorderFactory.createTitledBorder("Order Quantity"));  // Title for the panel

        // Initialize the labels and text fields for the items
        pizzaLabel = new JLabel("Pizza (Rs.150)");
        pizzaQty = new JTextField("0");  // Default quantity is 0
        burgerLabel = new JLabel("Burger (Rs.80)");
        burgerQty = new JTextField("0");
        drinkLabel = new JLabel("Cold Drink (Rs.50)");
        drinkQty = new JTextField("0");

        // Add the labels and text fields to the input panel
        inputPanel.add(pizzaLabel);
        inputPanel.add(pizzaQty);
        inputPanel.add(burgerLabel);
        inputPanel.add(burgerQty);
        inputPanel.add(drinkLabel);
        inputPanel.add(drinkQty);

        // Add the input panel to the center panel
        centerPanel.add(inputPanel, BorderLayout.NORTH);

        // Create a text area to display the bill and set its properties
        billArea = new JTextArea();
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 13));  // Monospaced font for the bill
        billArea.setEditable(false);  // Make text area non-editable
        JScrollPane scrollPane = new JScrollPane(billArea);  // Make the bill area scrollable
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bill Receipt"));  // Title for the bill section

        // Add the scrollable bill area to the center panel
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Create buttons and set their properties
        generateBillBtn = new JButton("Generate Bill");
        generateBillBtn.setBackground(new Color(60, 179, 113));  // Green button color
        generateBillBtn.setForeground(Color.WHITE);  // White text
        generateBillBtn.addActionListener(this);  // Add action listener

        resetBtn = new JButton("Reset");
        resetBtn.setBackground(new Color(255, 140, 0));  // Orange button color
        resetBtn.setForeground(Color.WHITE);
        resetBtn.addActionListener(this);

        saveBillBtn = new JButton("Save Bill");
        saveBillBtn.setBackground(new Color(34, 139, 34));  // Dark green button color
        saveBillBtn.setForeground(Color.WHITE);
        saveBillBtn.addActionListener(this);

        exitBtn = new JButton("Exit");
        exitBtn.setBackground(new Color(178, 34, 34));  // Red button color
        exitBtn.setForeground(Color.WHITE);
        exitBtn.addActionListener(this);

        // Add buttons to the button panel
        buttonPanel.add(generateBillBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(saveBillBtn);
        buttonPanel.add(exitBtn);

        // Add button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);  // Make the frame visible
    }

    // Action listener method for button clicks
    public void actionPerformed(ActionEvent e) {
        // Generate Bill when the Generate Bill button is clicked
        if (e.getSource() == generateBillBtn) {
            // Prices for each item
            int pizzaPrice = 150;
            int burgerPrice = 80;
            int drinkPrice = 50;

            // Get quantities from the text fields
            int qtyPizza = Integer.parseInt(pizzaQty.getText());
            int qtyBurger = Integer.parseInt(burgerQty.getText());
            int qtyDrink = Integer.parseInt(drinkQty.getText());

            // Calculate the total for each item
            int totalPizza = qtyPizza * pizzaPrice;
            int totalBurger = qtyBurger * burgerPrice;
            int totalDrink = qtyDrink * drinkPrice;

            // Calculate total amount, tax (5%), and final amount
            int totalAmount = totalPizza + totalBurger + totalDrink;
            double tax = totalAmount * 0.05;
            double finalAmount = totalAmount + tax;

            // Get the current date and time for the bill
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            // Display the bill in the text area
            billArea.setText("       Restaurant Bill Receipt\n");
            billArea.append("Date & Time : " + dateFormatter.format(date) + "\n");
            billArea.append("----------------------------------------\n");
            billArea.append("Item            Qty      Price\n");
            billArea.append("----------------------------------------\n");
            billArea.append("Pizza           " + qtyPizza + "       Rs." + totalPizza + "\n");
            billArea.append("Burger          " + qtyBurger + "       Rs." + totalBurger + "\n");
            billArea.append("Cold Drink      " + qtyDrink + "       Rs." + totalDrink + "\n");
            billArea.append("----------------------------------------\n");
            billArea.append("Total Items : " + (qtyPizza + qtyBurger + qtyDrink) + "\n");
            billArea.append("Sub Total  : Rs." + totalAmount + "\n");
            billArea.append("GST (5%)   : Rs." + String.format("%.2f", tax) + "\n");
            billArea.append("----------------------------------------\n");
            billArea.append("Final Amount : Rs." + String.format("%.2f", finalAmount) + "\n");
            billArea.append("Thank you! Visit Again!\n");
        }

        // Reset all input fields and clear the bill area when Reset button is clicked
        if (e.getSource() == resetBtn) {
            pizzaQty.setText("0");
            burgerQty.setText("0");
            drinkQty.setText("0");
            billArea.setText("");  // Clear the bill area
        }

        // Save the bill as a text file when Save Bill button is clicked
        if (e.getSource() == saveBillBtn) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("BillReceipt.txt"));
                writer.write(billArea.getText());  // Write the bill to the file
                writer.close();  // Close the file
                JOptionPane.showMessageDialog(this, "Bill saved as BillReceipt.txt!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving bill!");
            }
        }

        // Close the application when Exit button is clicked
        if (e.getSource() == exitBtn) {
            dispose();  // Close the window
        }
    }

    public static void main(String[] args) {
        new RestaurantBillingSystem();  // Create and display the billing system
    }
}