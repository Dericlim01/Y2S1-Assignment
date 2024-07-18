import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register_Page extends JFrame {
    private JPanel contentPane;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register_Page reg = new Register_Page();
                    reg.setTitle("Register Page");
                    reg.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register_Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBounds(30, 20, 50, 20);
        contentPane.add(name_lbl);

        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(30, 50, 100, 20);
        contentPane.add(pass_lbl);

        JLabel cont_num_lbl = new JLabel("Contact Number : ");
        cont_num_lbl.setBounds(30, 80, 120, 20);
        contentPane.add(cont_num_lbl);

        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setBounds(30, 110, 100, 20);
        contentPane.add(email_lbl);

        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(140, 20, 100, 20);
        contentPane.add(name_txt_f);

        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(140, 50, 100, 20);
        contentPane.add(pass_txt_f);

        JTextField cont_num_txt_f = new JTextField();
        cont_num_txt_f.setBounds(140, 80, 100, 20);
        contentPane.add(cont_num_txt_f);

        JTextField email_txt_f = new JTextField();
        email_txt_f.setBounds(140, 110, 100, 20);
        contentPane.add(email_txt_f);

        JButton register_btn = new JButton("Register");
        register_btn.setBounds(140, 140, 100, 20);
        register_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_txt_f.getText();
                char[] pass = pass_txt_f.getPassword();
                String cont_num = cont_num_txt_f.getText();
                String email = email_txt_f.getText();
                String role = "customer";
                Register register_user = new Register(role);

                // User register successfuly
                if (register_user.reg_user(name, pass, cont_num, email)) {
                    // Show Message Dialog
                    JOptionPane.showMessageDialog(
                        null,
                        "User Registered Successfully",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
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
                        Register_Page reg = new Register_Page();
                        reg.setVisible(true);
                    } else {
                        // Stop registration, back to login
                        Login_Page login = new Login_Page();
                        login.setTitle("Login");
                        login.setVisible(true);
                    }
                }
            }
        });
        contentPane.add(register_btn);
    }
}
