package src.Admin;

import src.shared.Register;
import src.shared.DateFormat;

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
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Staff_Add_Page extends JFrame {
    private JPanel contentPane;
    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Staff_Add_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Staff_Add_Page(String name) {
        setTitle("Staff Add Page");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        // Set Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,248));

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
        
        // Staff Information Adding Label
        JLabel staffadd_lbl = new JLabel("Add Staff Information");
        staffadd_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        staffadd_lbl.setBounds(380,100,400,30);
        contentPane.add(staffadd_lbl);

        // Add Staff information and register staff
        // Staffname Label
        JLabel staffname_lbl = new JLabel("Staff Name:");
        staffname_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        staffname_lbl.setBounds(180,200,200,30);
        contentPane.add(staffname_lbl);

        // Password Label
        JLabel staffpass_lbl = new JLabel("Password:");
        staffpass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        staffpass_lbl.setBounds(180,270,200,30);
        contentPane.add(staffpass_lbl);
     
        // Phone Label
        JLabel staffphone_lbl = new JLabel("Phone NO. :");
        staffphone_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffphone_lbl.setBounds(180,340,200,30);
        contentPane.add(staffphone_lbl);

        // Role Label
        JLabel staffrole_lbl = new JLabel("Role:");
        staffrole_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffrole_lbl.setBounds(180,410,200,30);
        contentPane.add(staffrole_lbl);

        // Email Label
        JLabel staffmail_lbl = new JLabel("Email:");
        staffmail_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffmail_lbl.setBounds(500,200,200,30);
        contentPane.add(staffmail_lbl);

        // D.O.B Label
        JLabel staffdob_lbl = new JLabel("D.O.B:");
        staffdob_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffdob_lbl.setBounds(500,270,200,30);
        contentPane.add(staffdob_lbl);

        // Gender Label
        JLabel staffgen_lbl = new JLabel("Gender:");
        staffgen_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffgen_lbl.setBounds(500,340,200,30);
        contentPane.add(staffgen_lbl);

        // Staffname Text Field
        JTextField staffname_txt = new JTextField();
        staffname_txt.setBounds(300,200,170,30);
        contentPane.add(staffname_txt);

        // Staffpass Text Field
        JTextField staffpass_txt = new JTextField();
        staffpass_txt.setBounds(300,270,170,30);
        contentPane.add(staffpass_txt);

        // Phone Text Field
        JTextField staffphone_txt = new JTextField();
        staffphone_txt.setBounds(300,340,170,30);
        contentPane.add(staffphone_txt);

        // Role Combo Box
        String[] roletype = {"scheduler", "manager"};
        JComboBox<String> roleData = new JComboBox<>(roletype);
        roleData.setBounds(300,410,170,30);
        roleData.setBackground(new Color(250,240,230));
        contentPane.add(roleData);

        // Email Text Field
        JTextField staffmail_txt = new JTextField();
        staffmail_txt.setBounds(590,200,170,30);
        contentPane.add(staffmail_txt);

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
        dobDatePicker.setBounds(590,270,170,30);
        contentPane.add(dobDatePicker);

        // Gender Combo Box
        String[] gender = {"male","female"};
        JComboBox<String> genData = new JComboBox<>(gender);
        genData.setBounds(590,340,170,30);
        genData.setBackground(new Color(250,240,230));
        contentPane.add(genData);

        // Add New Staff Button
        JButton staffadd_btn = new JButton("Add New Staff");
        staffadd_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffadd_btn.setBounds(420,500,170,30);
        staffadd_btn.setBackground(new Color(250,240,230));
        staffadd_btn.setForeground(new Color(128,128,128));
        staffadd_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the data from textfield/combobox/datepicker
                String staffname = staffname_txt.getText();
                String staffpass = staffpass_txt.getText();
                String staffphone = staffphone_txt.getText();
                String staffmail = staffmail_txt.getText();
                // casting datatype to Date form
                Date staffdob = (Date) dobDatePicker.getModel().getValue();
                // casting datatype to String form
                String staffgen = (String) genData.getSelectedItem();
                String staffrole = (String) roleData.getSelectedItem(); 

                Staff_Management Staff_Management = new Staff_Management(name);
                Register register_user = new Register(String.valueOf(roleData.getSelectedItem()));

                // Adding the informations into staffs text file
                // Checking staff whether data is existing
                if (Staff_Management.check_staff(staffname)) {
                    //Staffname not used, start adding
                    if (Staff_Management.add_staff(staffname, staffpass, staffphone, staffmail, staffdob, staffgen, staffrole)) {
                        //Show Message Dialog
                        if (register_user.chk_user(staffname)) {
                            if (register_user.reg_user(staffname, staffpass)) {
                                int response = JOptionPane.showConfirmDialog(
                                    null,
                                    "Staff Added Successfully. Do you want to add again?",
                                    "Question",
                                    JOptionPane.YES_NO_OPTION);
                                if (response == 0) {
                                    // add again
                                    dispose();
                                    new Staff_Add_Page(name).setVisible(true);
                                }
                                else {
                                    // back to management view page
                                    dispose();
                                    new Staff_Management_Page(name).setVisible(true);
                                }
                            }
                        }                    
                    }
                    else {
                        // Failed to Add New Staff, info insert not completed
                        int response = JOptionPane.showConfirmDialog(
                            null,
                            "Add Staff Failed. Do you want to add again?",
                            "Question",
                            JOptionPane.YES_NO_OPTION);
                        if (response == 0) {
                            // add again
                            dispose();
                            new Staff_Add_Page(name).setVisible(true);
                        }
                        else {
                            // back to management view page
                            dispose();
                            new Staff_Management_Page(name).setVisible(true);
                        }
                    }
                }
                else {
                    // Staffname exists
                    int response = JOptionPane.showConfirmDialog(
                        null,
                        "Staff name exists. Do you want to add again?",
                        "Question",
                        JOptionPane.YES_NO_OPTION);
                    if (response == 0) {
                        // add again
                        dispose();
                        new Staff_Add_Page(name).setVisible(true);
                    }
                    else {
                        // back to management view page
                        dispose();
                        new Staff_Management_Page(name).setVisible(true);
                    }
                }
            }
        });
        contentPane.add(staffadd_btn);
    
        // Back Staff Management Label
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Staff_Management_Page(name).setVisible(true);
            }
        });
        contentPane.add(back_lbl);

        // Design 4 Background Pic
        JLabel des4 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));
            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des4);
    }
}
