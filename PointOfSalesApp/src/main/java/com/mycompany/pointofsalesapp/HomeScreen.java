package com.mycompany.pointofsalesapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JButton profileButton;
    private JButton logoutButton;

    public HomeScreen() {
        setTitle("Home Screen");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat panel dan layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Tombol untuk profil
        profileButton = new JButton("Profil");
        profileButton.setBounds(50, 50, 120, 30);
        panel.add(profileButton);

        // Tombol untuk logout
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(200, 50, 120, 30);
        panel.add(logoutButton);

        // Action listener untuk tombol profil
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfile();
            }
        });

        // Action listener untuk tombol logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        add(panel);
    }

    private void showProfile() {
        // Membuat tampilan profil (panel atau dialog baru)
        String userProfile = getProfile(); // Panggil fungsi getProfile
        JOptionPane.showMessageDialog(this, userProfile, "Profil", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getProfile() {
        // Di sini, Anda bisa mendapatkan profil pengguna dari database
        // Contoh sederhana hardcoded, ganti dengan query database sesuai kebutuhan
        String username = "admin"; // Ambil dari session pengguna yang sudah login
        return "Username: " + username + "\nRole: Admin\nAkses: Semua fitur"; // Ubah sesuai data yang diambil
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Tutup HomeScreen
            new LoginForm().setVisible(true); // Tampilkan form login
        }
    }
}
