package com.mycompany.pointofsalesapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login Form");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel utama dengan layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(60, 63, 65)); // Warna background abu-abu gelap

        // Panel header untuk judul
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(45, 48, 50));
        JLabel titleLabel = new JLabel("WELCOME");
        titleLabel.setForeground(Color.WHITE); // Warna teks putih
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Font lebih besar
        headerPanel.add(titleLabel);

        // Panel form login
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(new Color(60, 63, 65));

        // Label dan TextField untuk username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 40, 80, 25);
        usernameLabel.setForeground(Color.WHITE); // Warna teks putih
        formPanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 40, 160, 25);
        formPanel.add(usernameField);

        // Label dan PasswordField untuk password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 80, 80, 25);
        passwordLabel.setForeground(Color.WHITE);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 80, 160, 25);
        formPanel.add(passwordField);

        // Tombol login
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 120, 260, 30);
        loginButton.setBackground(new Color(70, 130, 180)); // Warna biru pada tombol
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false); // Hilangkan border fokus saat klik
        formPanel.add(loginButton);

        // Tambahkan panel header dan form ke panel utama
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Tambahkan panel utama ke frame
        add(mainPanel);

        // Action listener untuk button login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void login() throws SQLException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Connection connection = DatabaseConnection.getConnection(); // Pastikan koneksi ke DB benar
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            JOptionPane.showMessageDialog(this, "Login Berhasil!");
            HomeScreen homeScreen = new HomeScreen(); // Membuat objek HomeScreen
            homeScreen.setVisible(true); // Menampilkan HomeScreen
            dispose(); // Menutup form login
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah.");
        }

        statement.close();
        connection.close();
    }
}
