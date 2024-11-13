package com.mycompany.pointofsalesapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageProductsForm extends JFrame {

    private JTable productsTable;
    private DefaultTableModel tableModel;
    private JTextField searchField, addNameField, editNameField, deleteIdField;
    private JTextField addPriceField, editPriceField;

    public ManageProductsForm() {
        setTitle("Manage Products");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("View Products", createViewPanel());
        tabbedPane.addTab("Add Product", createAddPanel());
        tabbedPane.addTab("Edit Product", createEditPanel());
        tabbedPane.addTab("Delete Product", createDeletePanel());
        tabbedPane.addTab("Search Product", createSearchPanel());
        add(tabbedPane);

        setVisible(true);
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Product ID", "Product Name", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Product Name:"));
        addNameField = new JTextField();
        panel.add(addNameField);

        panel.add(new JLabel("Price:"));
        addPriceField = new JTextField();
        panel.add(addPriceField);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        panel.add(addButton);

        return panel;
    }

    private JPanel createEditPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Product ID to Edit:"));
        deleteIdField = new JTextField();
        panel.add(deleteIdField);

        panel.add(new JLabel("New Product Name:"));
        editNameField = new JTextField();
        panel.add(editNameField);

        panel.add(new JLabel("New Price:"));
        editPriceField = new JTextField();
        panel.add(editPriceField);

        JButton editButton = new JButton("Edit Product");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct();
            }
        });
        panel.add(editButton);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Product ID to Delete:"));
        deleteIdField = new JTextField();
        panel.add(deleteIdField);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });
        panel.add(deleteButton);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        searchField = new JTextField(20);
        panel.add(new JLabel("Search Product by Name:"), BorderLayout.WEST);
        panel.add(searchField, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });
        panel.add(searchButton, BorderLayout.EAST);

        return panel;
    }

    private void viewProducts() {
        tableModel.setRowCount(0);
        try {
            DatabaseConnections dbConn = new DatabaseConnections();
            Connection conn = dbConn.getConnection();

            String sql = "SELECT * FROM products";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price")
                };
                tableModel.addRow(row);
            }

            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading product data.");
        }
    }

    private void addProduct() {
        String name = addNameField.getText();
        String price = addPriceField.getText();

        if (name.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            double priceValue = Double.parseDouble(price);

            DatabaseConnections dbConn = new DatabaseConnections();
            Connection conn = dbConn.getConnection();

            String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, priceValue);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Product Added: " + name);
                viewProducts();
            }

            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding product.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for price.");
        }
    }

    private void editProduct() {
        // Implement edit functionality here with similar validation as addProduct
    }

    private void deleteProduct() {
        // Implement delete functionality here
    }

    private void searchProduct() {
        // Implement search functionality here
    }

    public static void main(String[] args) {
        new ManageProductsForm();
    }
}
