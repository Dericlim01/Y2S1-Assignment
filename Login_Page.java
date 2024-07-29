import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login_Page
 */
public class Login_Page extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Login_Page login = new Login_Page();
                    login.setTitle("Login");
                    login.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login_Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        //contentPane.setTitle();
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Name Label
        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBackground(new Color(255, 255, 0));
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        name_lbl.setBounds(250, 250, 100, 30);
        contentPane.add(name_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(250, 310, 100, 30);
        contentPane.add(pass_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(400, 250, 150, 30);
        contentPane.add(name_txt_f);

        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(400, 310, 150, 30);
        contentPane.add(pass_txt_f);

        // Register Button
        JButton reg_btn = new JButton("Register");
        reg_btn.setBounds(250, 370, 100, 30);
        reg_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                String role = "customer";
                Register_Page reg = new Register_Page(role);
                reg.setTitle("Register");
                reg.setVisible(true);
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
                    Customer_Page cus = new Customer_Page(name);
                    cus.setTitle("Customer");
                    cus.setVisible(true);
                }
                else if(role.equals("admin")){
                    Admin_Page ad = new Admin_Page(name);
                    ad.setTitle("Admin");
                    ad.setVisible(true);
                } 
                else if (role.equals("manager")) {
                    Manager_Home_Page man_HP = new Manager_Home_Page(name);
                    man_HP.setTitle("Manager");
                    man_HP.setVisible(true);
                    dispose();
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