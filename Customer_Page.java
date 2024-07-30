import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer_Page extends JFrame {
    private static String name;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Customer_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Customer_Page(String n) {
        setTitle("Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Name Label
        JLabel name_lbl = new JLabel(n);
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        name_lbl.setBounds(100, 150, 100, 30);
        contentPane.add(name_lbl);

        // Welcome back
        JLabel welcome_lbl = new JLabel("Welcome Back !");
        welcome_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        welcome_lbl.setBounds(100, 200, 150, 30);
        contentPane.add(welcome_lbl);

        // Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBounds(100, 250, 100, 30);
        logout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_Page().setVisible(true);
            }
        });
        contentPane.add(logout_btn);

        // Customer title
        JLabel cus_page_title = new JLabel("Customer Home Page");
        cus_page_title.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        cus_page_title.setBounds(400, 40, 300, 40);
        contentPane.add(cus_page_title);

        // Update Profile button
        JButton update_p_btn = new JButton("Update Profile");
        update_p_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        update_p_btn.setBounds(450, 450, 200, 40);
        update_p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Update_Profile(n).setVisible(true);
            }
        });
        contentPane.add(update_p_btn);

        // Hall Booking Button
        JButton book_hall_btn = new JButton("Hall Booking");
        book_hall_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        book_hall_btn.setBounds(450, 500, 200, 40);
        update_p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to booking page
            }
        });
        contentPane.add(book_hall_btn);

        // Booking Information
        JButton book_info_btn = new JButton("Book Information");
        book_info_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        book_info_btn.setBounds(450, 550, 200, 40);
        book_info_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to booking information page
            }
        });
        contentPane.add(book_info_btn);

        // Issue Rasing
        JButton issue_btn = new JButton("Issue Raising");
        issue_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        issue_btn.setBounds(450, 600, 200, 40);
        issue_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Raise_Issue_Page(n).setVisible(true);
            }
        });
        contentPane.add(issue_btn);
    }
}
