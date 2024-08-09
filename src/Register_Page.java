package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register_Page extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Register_Page().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register_Page() {
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Register Label
        JLabel reg_lbl = new JLabel("Register");
        reg_lbl.setFont(new Font("Comic Sans MS", Font.BOLD|Font.ITALIC, 35));
        reg_lbl.setBounds(400, 100, 200, 45);
        contentPane.add(reg_lbl);

        // Username Label
        JLabel name_lbl = new JLabel("Username : ");
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        name_lbl.setBounds(280, 200, 150, 20);
        contentPane.add(name_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        name_txt_f.setBounds(500, 200, 150, 30);
        contentPane.add(name_txt_f);

        // Name status button
        JLabel name_stat_lbl = new JLabel();
        name_stat_lbl.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        name_stat_lbl.setForeground(new Color(255, 0, 0));
        name_stat_lbl.setBounds(500, 230, 150, 20);
        contentPane.add(name_stat_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        pass_lbl.setBounds(280, 250, 150, 20);
        contentPane.add(pass_lbl);

        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_txt_f.setBounds(500, 250, 150, 30);
        pass_txt_f.setEchoChar('*');
        contentPane.add(pass_txt_f);

        // Re-enter Password Label
        JLabel reen_pass_lbl = new JLabel("Re-enter Password : ");
        reen_pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        reen_pass_lbl.setBounds(280, 300, 200, 20);
        contentPane.add(reen_pass_lbl);

        // Re-enter Password Text Field
        JPasswordField pass_txt_f_reen = new JPasswordField();
        pass_txt_f_reen.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_txt_f_reen.setBounds(500, 300, 150, 30);
        pass_txt_f_reen.setEchoChar('*');
        contentPane.add(pass_txt_f_reen);

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(655, 305, 20, 20);
        show_pass.setSelected(false);
        show_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show_pass.isSelected()) {
                    pass_txt_f.setEchoChar((char) 0);
                    pass_txt_f_reen.setEchoChar((char) 0);
                } else {
                    pass_txt_f.setEchoChar('*');
                    pass_txt_f_reen.setEchoChar('*');
                }
            }
        });
        contentPane.add(show_pass);

        // Contact Number Label
        JLabel cont_num_lbl = new JLabel("Contact Number : ");
        cont_num_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        cont_num_lbl.setBounds(280, 350, 200, 20);
        contentPane.add(cont_num_lbl);

        // Contact Number Text Field
        JTextField cont_num_txt_f = new JTextField();
        cont_num_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        cont_num_txt_f.setBounds(500, 350, 150, 30);
        contentPane.add(cont_num_txt_f);

        // Email Label
        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        email_lbl.setBounds(280, 400, 200, 20);
        contentPane.add(email_lbl);

        // Email Text Field
        JTextField email_txt_f = new JTextField();
        email_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        email_txt_f.setBounds(500, 400, 150, 30);
        contentPane.add(email_txt_f);

        // Back button
        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        back_btn.setBounds(280, 450, 100, 30);
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_Page().setVisible(true);
            }
        });
        contentPane.add(back_btn);

        // Register Button
        JButton register_btn = new JButton("Sign Up");
        register_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        register_btn.setBounds(425, 450, 150, 30);
        register_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_txt_f.getText();
                String pass = String.valueOf(pass_txt_f.getPassword());
                String cont_num = cont_num_txt_f.getText();
                String email = email_txt_f.getText();
                String role = "customer";
                Register register_user = new Register(role);

                // If username and password is empty
                if (name.isEmpty() && pass.isEmpty()) {
                    name_stat_lbl.setText("Username cannot be empty");
                    name_stat_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
                } else if (String.valueOf(pass_txt_f.getPassword()).equals(String.valueOf(pass_txt_f_reen.getPassword()))) {
                    // If username and password is not empty
                    // Username available
                    if (register_user.chk_user(name)) {
                        // User register successfuly
                        if (register_user.reg_user(name, pass, cont_num, email)) {
                            // Show Message Dialog
                            JOptionPane.showMessageDialog(
                                null,
                                "User Registered Successfully",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                            // If done register, back login
                            dispose();
                            new Login_Page().setVisible(true);
                        } else {
                            // User register failed, show option dialog
                            int response = JOptionPane.showConfirmDialog(
                                null,
                                "Register Failed, Register again?",
                                "Question",
                                JOptionPane.YES_NO_OPTION);
                            // Register again
                            if (response == 1) {
                                // Go to register page
                                new Register_Page().setVisible(true);
                            } else {
                                // Stop registration, back to login
                                new Login_Page().setVisible(true);
                            }
                        }
                    } else {
                        // Username exists
                        name_stat_lbl.setText("Username already exists.");
                        name_stat_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Password not match",
                        "Password error",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        contentPane.add(register_btn);
    }
}
