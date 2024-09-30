package src.shared;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

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
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(248,248,248));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        // Logo Pic
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(logo);

        // Design Pic 1
        JLabel des1 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design1.png"));
            Image image = get_image.getScaledInstance(300, 280, Image.SCALE_SMOOTH);
            des1.setIcon(new ImageIcon(image));
            des1.setBounds(780, 0, 250, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des1);

        // Design3 Pic
        JLabel des3 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design3.png"));
            Image image = get_image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
            des3.setIcon(new ImageIcon(image));
            des3.setBounds(-50, -50, 400, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des3);

        // Register Label
        JLabel reg_lbl = new JLabel("Register");
        reg_lbl.setFont(new Font("Engravers MT", Font.PLAIN|Font.PLAIN, 20));
        reg_lbl.setBounds(430, 120, 200, 45);
        contentPane.add(reg_lbl);

        // Username Label
        JLabel name_lbl = new JLabel("Username : ");
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        name_lbl.setBounds(240, 200, 150, 20);
        contentPane.add(name_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        name_txt_f.setBounds(420, 200, 300, 25);
        contentPane.add(name_txt_f);

        // Name status button
        JLabel name_stat_lbl = new JLabel();
        name_stat_lbl.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        name_stat_lbl.setForeground(new Color(255, 0, 0));
        name_stat_lbl.setBounds(420, 230, 150, 20);
        contentPane.add(name_stat_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        pass_lbl.setBounds(240, 250, 150, 20);
        contentPane.add(pass_lbl);

        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_txt_f.setBounds(420, 250, 300, 30);
        pass_txt_f.setEchoChar('*');
        contentPane.add(pass_txt_f);

        // Re-enter Password Label
        JLabel reen_pass_lbl = new JLabel("Re-enter Password : ");
        reen_pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        reen_pass_lbl.setBounds(240, 300, 200, 20);
        contentPane.add(reen_pass_lbl);

        // Re-enter Password Text Field
        JPasswordField pass_txt_f_reen = new JPasswordField();
        pass_txt_f_reen.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_txt_f_reen.setBounds(420, 300, 300, 30);
        pass_txt_f_reen.setEchoChar('*');
        contentPane.add(pass_txt_f_reen);

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(730, 256, 20, 20);
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
        cont_num_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        cont_num_lbl.setBounds(240, 350, 200, 20);
        contentPane.add(cont_num_lbl);

        // Contact Number Text Field
        JTextField cont_num_txt_f = new JTextField();
        cont_num_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        cont_num_txt_f.setBounds(420, 350, 300, 30);
        contentPane.add(cont_num_txt_f);

        // Email Label
        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        email_lbl.setBounds(240, 400, 200, 20);
        contentPane.add(email_lbl);

        // Email Text Field
        JTextField email_txt_f = new JTextField();
        email_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        email_txt_f.setBounds(420, 400, 300, 30);
        contentPane.add(email_txt_f);

        // DOB Label
        JLabel dob_lbl = new JLabel("Date of Birth : ");
        dob_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        dob_lbl.setBounds(240, 450, 200, 20);
        contentPane.add(dob_lbl);

        // D.O.B Calendar
        UtilDateModel model = new UtilDateModel();
        // Properties create object to store values in it
        Properties prop = new Properties();
        prop.put("text.day", "Day");
        prop.put("text.month","Month");
        prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
        JDatePickerImpl dobDatePicker = new JDatePickerImpl(datePanel, new DateFormat());
        dobDatePicker.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        dobDatePicker.setBounds(420,450,300,30);
        contentPane.add(dobDatePicker);

        // Gender Label
        JLabel gender_lbl = new JLabel("Gender : ");
        gender_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        gender_lbl.setBounds(240, 500, 200, 20);
        contentPane.add(gender_lbl);

        // Gender Combo Box
        String[] gender_data = {"male","female"};
        JComboBox<String> gen_cmbbx = new JComboBox<>(gender_data);
        gen_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        gen_cmbbx.setBounds(420, 500, 300, 30);
        contentPane.add(gen_cmbbx);

        // Back Label
        JLabel back_lbl = new JLabel("<html><u>Already have an account? Click here to login!</u></html>");
        back_lbl.setFont(new Font("Serif", Font.PLAIN, 15));
        back_lbl.setForeground(new Color(128,128,128));
        back_lbl.setBounds(360, 630, 400, 30);
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login_Page().setVisible(true);
            }
        });
        contentPane.add(back_lbl);

        // Register Button
        JButton register_btn = new JButton("Sign Up");
        register_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        register_btn.setBackground(new Color(250,240,230));
        register_btn.setForeground(new Color(128,128,128));
        register_btn.setBounds(420, 570, 150, 30);
        register_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_txt_f.getText();
                String pass = String.valueOf(pass_txt_f.getPassword());
                String cont_num = cont_num_txt_f.getText();
                String email = email_txt_f.getText();
                Date dob = (Date) dobDatePicker.getModel().getValue();
                String gender = String.valueOf(gen_cmbbx.getSelectedItem());
                String role = "customer";
                Register register_user = new Register(role);

                // If username and password is empty
                if (name.isEmpty() || pass.isEmpty()) {
                    name_stat_lbl.setText("Username cannot be empty");
                    name_stat_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
                } else if (String.valueOf(pass_txt_f.getPassword()).equals(String.valueOf(pass_txt_f_reen.getPassword()))) {
                    // If username and password is not empty
                    // Username available
                    if (register_user.chk_user(name)) {
                        // User register successfuly
                        if (register_user.reg_user(name, pass) && register_user.reg_users(name, cont_num, email, dob, gender)) {
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
