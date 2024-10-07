package com.mycompany.pointofsalesapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernLoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public ModernLoginForm() {
        // Atur judul dan ukuran form
        setTitle("Binance-Style Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan background gradien
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 0, 0); // Warna hitam
                Color color2 = new Color(36, 36, 36); // Warna abu-abu gelap
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);

        // Label judul
        JLabel titleLabel = new JLabel("Login to Binance");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 200, 30);
        panel.add(titleLabel);

        // Label dan TextField Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 80, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(140, 80, 200, 25);
        usernameField.setBackground(new Color(50, 50, 50));
        usernameField.setForeground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(usernameField);

        // Label dan PasswordField Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 120, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 120, 200, 25);
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(passwordField);

        // Tombol Login dengan warna emas
        loginButton = new JButton("Login");
        loginButton.setBounds(140, 170, 200, 35);
        loginButton.setBackground(new Color(255, 204, 0)); // Warna emas khas Binance
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        // Action listener untuk login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Proses login (hardcoded untuk sementara)
                if (username.equals("admin") && password.equals("password123")) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    // Buka halaman beranda di sini
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password salah.");
                }
            }
        });

        // Tambahkan panel ke frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ModernLoginForm loginForm = new ModernLoginForm();
                loginForm.setVisible(true);
            }
        });
    }
}
