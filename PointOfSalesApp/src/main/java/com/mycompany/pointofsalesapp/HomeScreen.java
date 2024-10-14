package com.mycompany.pointofsalesapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JButton profileButton;
    private JButton logoutButton;
    private JButton manageUsersButton;

    public HomeScreen() {
        setTitle("Home Screen");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        profileButton = new JButton("Profile");
        logoutButton = new JButton("Logout");
        manageUsersButton = new JButton("Manage Users");

        panel.add(profileButton);
        panel.add(manageUsersButton);
        panel.add(logoutButton);

        add(panel);

        // Aksi ketika tombol profile ditekan
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileForm(); // Navigasi ke form profil
            }
        });

        // Aksi ketika tombol manage user ditekan
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataUserForm(); // Navigasi ke form DataUserForm
            }
        });

        // Aksi logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm(); // Kembali ke form login
                dispose(); // Tutup form ini
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeScreen();
    }
}
