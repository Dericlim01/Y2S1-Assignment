import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register_Page extends JFrame {
    private static String role;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Register_Page(role).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register_Page(String r) {
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Name Label
        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBounds(30, 20, 50, 20);
        contentPane.add(name_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(140, 20, 100, 20);
        contentPane.add(name_txt_f);

        // Name status button
        JLabel name_stat_lbl = new JLabel();
        name_stat_lbl.setBounds(140, 35, 150, 20);
        contentPane.add(name_stat_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(30, 50, 100, 20);
        contentPane.add(pass_lbl);

        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(140, 50, 100, 20);
        pass_txt_f.setEchoChar('*');
        contentPane.add(pass_txt_f);

        // Re-enter Password Label
        JLabel reen_pass_lbl = new JLabel("Re-enter Password : ");
        reen_pass_lbl.setBounds(30, 80, 100, 20);
        contentPane.add(reen_pass_lbl);

        // Re-enter Password Text Field
        JPasswordField pass_txt_f_reen = new JPasswordField();
        pass_txt_f_reen.setBounds(140, 80, 100, 20);
        pass_txt_f_reen.setEchoChar('*');
        contentPane.add(pass_txt_f_reen);

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(560, 315, 20, 20);
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
        cont_num_lbl.setBounds(30, 110, 120, 20);
        contentPane.add(cont_num_lbl);

        // Contact Number Text Field
        JTextField cont_num_txt_f = new JTextField();
        cont_num_txt_f.setBounds(140, 110, 100, 20);
        contentPane.add(cont_num_txt_f);

        // Email Label
        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setBounds(30, 140, 100, 20);
        contentPane.add(email_lbl);

        // Email Text Field
        JTextField email_txt_f = new JTextField();
        email_txt_f.setBounds(140, 140, 100, 20);
        contentPane.add(email_txt_f);

        // Register Button
        JButton register_btn = new JButton("Sign Up");
        register_btn.setBounds(140, 170, 100, 20);
        register_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_txt_f.getText();
                String pass = String.valueOf(pass_txt_f.getPassword());
                String cont_num = cont_num_txt_f.getText();
                String email = email_txt_f.getText();
                Register register_user = new Register(r);

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
                            // If superadmin register user, stay at register page
                            if (role == "superadmin") {
                                name_txt_f.setText("");
                                pass_txt_f.setText("");
                                cont_num_txt_f.setText("");
                                email_txt_f.setText("");
                            } else {
                                // If customer done register, back login
                                dispose();
                                new Login_Page().setVisible(true);
                            }
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
                                new Register_Page(role).setVisible(true);
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
