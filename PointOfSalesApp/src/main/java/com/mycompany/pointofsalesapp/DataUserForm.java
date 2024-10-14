package com.mycompany.pointofsalesapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataUserForm extends JFrame {
    private JTextField idField, namaField, emailField, noHPField;
    private JButton tambahBtn, editBtn, deleteBtn, resetBtn;

    public DataUserForm() {
        setTitle("Data User");
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        idField = new JTextField();
        namaField = new JTextField();
        emailField = new JTextField();
        noHPField = new JTextField();

        tambahBtn = new JButton("Tambah");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        resetBtn = new JButton("Reset");

        panel.add(new JLabel("ID User"));
        panel.add(idField);
        panel.add(new JLabel("Nama"));
        panel.add(namaField);
        panel.add(new JLabel("Email"));
        panel.add(emailField);
        panel.add(new JLabel("No HP"));
        panel.add(noHPField);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.add(tambahBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(resetBtn);

        add(buttonPanel, BorderLayout.SOUTH);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        tambahBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahUser();
            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tambah kode untuk edit
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tambah kode untuk delete
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                namaField.setText("");
                emailField.setText("");
                noHPField.setText("");
            }
        });
    }

    private void tambahUser() {
        Connection connection = DatabaseConnection.connect();
        try {
            String query = "INSERT INTO users (id, nama, email, no_hp) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idField.getText());
            preparedStatement.setString(2, namaField.getText());
            preparedStatement.setString(3, emailField.getText());
            preparedStatement.setString(4, noHPField.getText());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data User Ditambahkan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DataUserForm();
    }
}
