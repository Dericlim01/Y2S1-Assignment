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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Admin_Add_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Admin_Add_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Admin_Add_Page(String name) {
        setTitle("Admin Add Page");
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
        } catch(IOException e) {
            e.printStackTrace();
        }
        contentPane.add(logo);

        // Admin login info Adding Label
        JLabel adadd_lbl = new JLabel("Add Admin Information");
        adadd_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        adadd_lbl.setBounds(375,100,350,30);
        contentPane.add(adadd_lbl);

        // Add Admin information and register admin
        // Adminname Label
        JLabel adname_lbl = new JLabel("Admin Name:");
        adname_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adname_lbl.setBounds(180,200,200,30);
        contentPane.add(adname_lbl);

        // Password Label
        JLabel adpass_lbl = new JLabel("Password:");
        adpass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adpass_lbl.setBounds(180,270,200,30);
        contentPane.add(adpass_lbl);
     
        // Phone Label
        JLabel adphone_lbl = new JLabel("Phone NO. :");
        adphone_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        adphone_lbl.setBounds(180,340,200,30);
        contentPane.add(adphone_lbl);

        // Role Label
        JLabel adrole_lbl = new JLabel("Role:");
        adrole_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        adrole_lbl.setBounds(180,410,200,30);
        contentPane.add(adrole_lbl);

        // Email Label
        JLabel admail_lbl = new JLabel("Email:");
        admail_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        admail_lbl.setBounds(500,200,200,30);
        contentPane.add(admail_lbl);

        // D.O.B Label
        JLabel addob_lbl = new JLabel("D.O.B:");
        addob_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        addob_lbl.setBounds(500,270,200,30);
        contentPane.add(addob_lbl);

        // Gender Label
        JLabel adgen_lbl = new JLabel("Gender:");
        adgen_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        adgen_lbl.setBounds(500,340,200,30);
        contentPane.add(adgen_lbl);

        // Adminpass Text Field
        JTextField adpass_txt = new JTextField();
        adpass_txt.setBounds(300,270,170,30);
        contentPane.add(adpass_txt);

        // Phone Text Field
        JTextField adphone_txt = new JTextField();
        adphone_txt.setBounds(300,340,170,30);
        contentPane.add(adphone_txt);

        // Email Text Field
        JTextField admail_txt = new JTextField();
        admail_txt.setBounds(590,200,170,30);
        contentPane.add(admail_txt);

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
        genData.setSelectedIndex(-1);
        contentPane.add(genData);

        // Role Show Label
        JLabel adshow_lbl = new JLabel("admin");
        adshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adshow_lbl.setBounds(350,410,200,30);
        contentPane.add(adshow_lbl);

        // Admin Name Textfield
        JTextField adname_txt = new JTextField();
        adname_txt.setBounds(300,200,170,30);
        contentPane.add(adname_txt);

        // Add New Admin Button
        JButton adadd_btn = new JButton("Add Admin Information");
        adadd_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        adadd_btn.setBounds(420,500,200,30);
        adadd_btn.setBackground(new Color(250,240,230));
        adadd_btn.setForeground(new Color(128,128,128));
        adadd_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adname = adname_txt.getText();
                String adpass = adpass_txt.getText();
                String adphone = adphone_txt.getText();
                String admail = admail_txt.getText();
                Date addob = (Date) dobDatePicker.getModel().getValue();
                String adgen = (String) genData.getSelectedItem();
                String adrole = adshow_lbl.getText();

                Staff_Management sm = new Staff_Management(name);
                Register register = new Register(adrole);

                if (sm.check_staff(adname)) {
                    if (sm.add_staff(adname, adpass, adphone, admail, addob, adgen, adrole)) {
                        if (register.chk_user(adname)) {
                            if (register.reg_user(adname, adpass)) {
                                int response = JOptionPane.showConfirmDialog(
                                                    null,
                                                    "Staff Added Successfully. Do you want to add again?",
                                                    "Question" ,
                                                    JOptionPane.YES_NO_OPTION);
                                if (response == 0) {
                                    // add again
                                    dispose();
                                    new Admin_Add_Page(name).setVisible(true);
                                }
                                else {
                                    // back to management view page
                                    dispose();
                                    new Admin_Management_Page(name).setVisible(true);
                                }
                            }
                        }
                    }
                    else {
                        // Failed to Add New Admin, info insert not completed
                        int response = JOptionPane.showConfirmDialog(
                                            null,
                                            "Add Admin Failed. Do you want to add again?",
                                            "Question" ,
                                            JOptionPane.YES_NO_OPTION);
                        if (response == 0) {
                            // add again
                            dispose();
                            new Admin_Add_Page(name).setVisible(true);
                        }
                        else {
                            // back to management view page
                            dispose();
                            new Admin_Management_Page(name).setVisible(true);
                        }
                    }
                }
                else {
                    // Adminname exists
                    int response = JOptionPane.showConfirmDialog(
                                        null,
                                        "Admin name exists. Do you want to add again?",
                                        "Question" ,
                                        JOptionPane.YES_NO_OPTION);
                    if (response == 0) {
                        // add again
                        dispose();
                        new Admin_Add_Page(name).setVisible(true);
                    }
                    else {
                        // back to management view page
                        dispose();
                        new Admin_Management_Page(name).setVisible(true);
                    }
                }
            }           
        });
        contentPane.add(adadd_btn);

        // Back Page Pic
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
                new Admin_Management_Page(name).setVisible(true);          
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