package com.mycompany.pointofsalesapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        setTitle("Home - Modern Design");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setModernDesign(); // Apply modern design theme

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 44, 52));  // Dark modern background
        add(panel);

        placeComponents(panel); // Add components to panel

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;

        JLabel label = new JLabel("Welcome to Point of Sales App", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Roboto", Font.BOLD, 20));
        panel.add(label, constraints);

        constraints.gridy++;
        constraints.gridwidth = 1;

        JButton manageUsersBtn = createStyledButton("Manage Users");
        panel.add(manageUsersBtn, constraints);

        constraints.gridx = 1;
        JButton manageProductsBtn = createStyledButton("Manage Products");
        panel.add(manageProductsBtn, constraints);

        manageUsersBtn.addActionListener(e -> {
            new DataUserForm();
            dispose();
        });

        manageProductsBtn.addActionListener(e -> openManageProductsDialog());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(new Color(45, 136, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Roboto", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 111, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(45, 136, 255));
            }
        });

        return button;
    }

    private void openManageProductsDialog() {
        JDialog manageProductsDialog = new JDialog(this, "Manage Products", true);
        manageProductsDialog.setSize(600, 400);
        manageProductsDialog.setLocationRelativeTo(this);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("View Products", createViewPanel());
        tabbedPane.addTab("Add Product", createAddPanel());
        tabbedPane.addTab("Edit Product", createEditPanel());
        tabbedPane.addTab("Delete Product", createDeletePanel());
        tabbedPane.addTab("Search Product", createSearchPanel());

        manageProductsDialog.add(tabbedPane);
        manageProductsDialog.setVisible(true);
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("View Products Here"));
        // Add code to display products from the database
        return panel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        panel.add(new JLabel("Product Name:"));
        JTextField productNameField = new JTextField();
        panel.add(productNameField);

        panel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        panel.add(priceField);

        JButton addButton = new JButton("Add Product");
        panel.add(addButton);

        addButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String price = priceField.getText();
            // Add product to database
            JOptionPane.showMessageDialog(panel, "Product added!");
        });

        return panel;
    }

    private JPanel createEditPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        panel.add(new JLabel("Product ID:"));
        JTextField productIdField = new JTextField();
        panel.add(productIdField);

        panel.add(new JLabel("New Product Name:"));
        JTextField newNameField = new JTextField();
        panel.add(newNameField);

        panel.add(new JLabel("New Price:"));
        JTextField newPriceField = new JTextField();
        panel.add(newPriceField);

        JButton editButton = new JButton("Edit Product");
        panel.add(editButton);

        editButton.addActionListener(e -> {
            String productId = productIdField.getText();
            String newName = newNameField.getText();
            String newPrice = newPriceField.getText();
            // Edit product in database
            JOptionPane.showMessageDialog(panel, "Product edited!");
        });

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Product ID to Delete:"));
        JTextField productIdField = new JTextField();
        panel.add(productIdField);

        JButton deleteButton = new JButton("Delete Product");
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            String productId = productIdField.getText();
            // Delete product from database
            JOptionPane.showMessageDialog(panel, "Product deleted!");
        });

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Search by Product Name:"));
        JTextField searchField = new JTextField();
        panel.add(searchField);

        JButton searchButton = new JButton("Search");
        panel.add(searchButton);

        searchButton.addActionListener(e -> {
            String productName = searchField.getText();
            // Search product in database
            JOptionPane.showMessageDialog(panel, "Search results for: " + productName);
        });

        return panel;
    }

    private void setModernDesign() {
        UIManager.put("control", new Color(60, 63, 65));
        UIManager.put("text", new Color(230, 230, 230));
        UIManager.put("nimbusBase", new Color(50, 50, 50));
        UIManager.put("nimbusBlueGrey", new Color(60, 60, 60));
        UIManager.put("nimbusLightBackground", new Color(40, 44, 52));
    }

    public static void main(String[] args) {
        new HomeScreen();
    }
}
