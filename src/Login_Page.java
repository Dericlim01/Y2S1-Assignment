package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.Admin.Admin_Page;
import src.Admin.Suadmin_Page;
import src.Customer.Customer_Page;
import src.Manager.Manager_Home_Page;

import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login_Page
 */
public class Login_Page extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Login_Page().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login_Page() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Login Label
        JLabel login_lbl = new JLabel("Login");
        login_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        login_lbl.setBounds(400, 170, 150, 45);
        contentPane.add(login_lbl);

        // Name Label
        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBackground(new Color(255, 255, 0));
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        name_lbl.setBounds(250, 250, 100, 30);
        contentPane.add(name_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(400, 250, 150, 30);
        contentPane.add(name_txt_f);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(250, 310, 100, 30);
        contentPane.add(pass_lbl);

        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(400, 310, 150, 30);
        pass_txt_f.setEchoChar('*');
        contentPane.add(pass_txt_f);

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(560, 315, 20, 20);
        show_pass.setSelected(false);
        show_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show_pass.isSelected()) {
                    pass_txt_f.setEchoChar((char) 0);
                } else {
                    pass_txt_f.setEchoChar('*');
                }
            }
        });
        contentPane.add(show_pass);

        // Register Button
        JButton reg_btn = new JButton("Register");
        reg_btn.setBounds(250, 370, 100, 30);
        reg_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Register_Page().setVisible(true);
            }
        });
        contentPane.add(reg_btn);

        // Login Button
        JButton login_btn = new JButton("Login");
        login_btn.setBounds(400, 370, 100, 30);
        login_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_txt_f.getText();
                String pass = String.valueOf(pass_txt_f.getPassword());
                Login log_user = new Login();
                String role = log_user.login(name, pass);
                // Login Successfull
                if (role.equals("customer")) {
                    dispose();
                    new Customer_Page(name).setVisible(true);
                }
                else if(role.equals("superadmin")){
                    dispose();
                    new Suadmin_Page(name).setVisible(true);
                }
                else if(role.equals("admin")){
                    dispose();
                    new Admin_Page(name).setVisible(true);
                } 
                else if (role.equals("manager")) {
                    dispose();
                    new Manager_Home_Page(name).setVisible(true);
                }
                else {
                    // Login failed, show message box
                    JOptionPane.showMessageDialog(
                        null,
                        "Login Failed",
                        "Login Status",
                        JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        contentPane.add(login_btn);
    }
}