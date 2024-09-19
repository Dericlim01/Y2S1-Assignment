package src.Admin;

import src.shared.DateFormat;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

public class Admin_Profile_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    new Admin_Profile_Page(name).setVisible(true);;               
       
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Admin_Profile_Page(String name) {
        setTitle("Admin Profile Page");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        // Set Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,255));
        Admin_Management search = new Admin_Management(name);
        String[] user = search.search_user(name);

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

        // Profile Pic
        JLabel profile = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
            get_image = ImageIO.read(new File("resources\\Image\\meiya.png"));
            Image image = get_image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            profile.setIcon(new ImageIcon(image));
            profile.setBounds(450, 50, 1000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(profile);

        // Home Page Label
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
                new Admin_Page(name).setVisible(true);
            }
        });
        contentPane.add(back_lbl);

        // Gradient Design
        JLabel gradient = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\gradient.png"));
            Image image = get_image.getScaledInstance(1000, 280, Image.SCALE_SMOOTH);
            gradient.setIcon(new ImageIcon(image));
            gradient.setBounds(0, 0, 1000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(gradient);

        // Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        greet_lbl.setBounds(450,210,200,30);
        contentPane.add(greet_lbl);

        // Username Label
        JLabel user_lbl = new JLabel(user[0]);
        user_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        user_lbl.setBounds(470,240,200,30);
        contentPane.add(user_lbl);

        // Password label
        JLabel pass_lbl = new JLabel("Password:");
        pass_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pass_lbl.setBounds(380,290,200,30);
        contentPane.add(pass_lbl);

        // Password Show Label
        JLabel pshow_lbl = new JLabel(user[1]);
        pshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pshow_lbl.setBounds(480,290,200,30);
        contentPane.add(pshow_lbl);

        // Phone No. Label
        JLabel phone_lbl = new JLabel("Phone No.:");
        phone_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        phone_lbl.setBounds(380,340,200,30);
        contentPane.add(phone_lbl);

        // Phone Show Label
        JLabel pnshow_lbl = new JLabel(user[2]);
        pnshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        pnshow_lbl.setBounds(480,340,200,30);
        contentPane.add(pnshow_lbl);

        // Email Label
        JLabel mail_lbl = new JLabel("Email:");
        mail_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        mail_lbl.setBounds(380,390,200,30);
        contentPane.add(mail_lbl);

        // Email Show Label
        JLabel mshow_lbl = new JLabel(user[3]);
        mshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        mshow_lbl.setBounds(480,390,200,30);
        contentPane.add(mshow_lbl);

        // D.O.B. Label
        JLabel dob_lbl = new JLabel("D.O.B.:");
        dob_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        dob_lbl.setBounds(380,440,200,30);
        contentPane.add(dob_lbl);

        // D.O.B. Show Label
        JLabel dshow_lbl = new JLabel(user[4]);
        dshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        dshow_lbl.setBounds(480,440,200,30);
        contentPane.add(dshow_lbl);

        // Gender Label
        JLabel gen_lbl = new JLabel("Gender:");
        gen_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        gen_lbl.setBounds(380,490,200,30);
        contentPane.add(gen_lbl);

        // Role Label
        JLabel role_lbl = new JLabel("Role:");
        role_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        role_lbl.setBounds(380,540,200,30);
        contentPane.add(role_lbl);

        // Gender Show Label
        JLabel gshow_lbl = new JLabel(user[5]);
        gshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        gshow_lbl.setBounds(480,490,200,30);
        contentPane.add(gshow_lbl);

        // Password field
        JTextField pfield = new JTextField();
        pfield.setBounds(480,295,200,30);

        // Phone Number field
        JTextField pnfield = new JTextField();
        pnfield.setBounds(480,345,200,30);

        // Mail field
        JTextField mfield = new JTextField();
        mfield.setBounds(480,395,200,30);

        // D.O.B DateTimePicker
        UtilDateModel model = new UtilDateModel();
        // Properties create object to store values in it
        Properties prop = new Properties();
        prop.put("text.day", "Day");
        prop.put("text.month","Month");
        prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
        JDatePickerImpl dobDatePicker = new JDatePickerImpl(datePanel, new DateFormat());
        try {
            Date dob = dateFormat.parse(dshow_lbl.getText());
            model.setValue(dob); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        dobDatePicker.setBounds(480,445,200,30);

        // Gender Combo Box
        String[] gender = {"male","female"};
        JComboBox<String> genData = new JComboBox<>(gender);
        genData.setBounds(480,495,200,30);
        genData.setBackground(new Color(250,240,230));

        // Role Show Label
        JLabel rshow_lbl = new JLabel("admin");
        rshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        rshow_lbl.setBounds(480,540,200,30);
        contentPane.add(rshow_lbl);
   
        // Update Button
        JButton up_btn = new JButton("Update");
        up_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        up_btn.setBackground(new Color(250,240,230));
        up_btn.setForeground(new Color(128,128,128));
        up_btn.setBounds(430,600,150,30);
        up_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = user_lbl.getText();
                String pass = pfield.getText();
                String phone = pnfield.getText();
                String mail = mfield.getText();
                Date dob = (Date) dobDatePicker.getModel().getValue();
                String gen = (String) genData.getSelectedItem();
                String role = rshow_lbl.getText();
                Admin_Management adman = new Admin_Management(name);          
                if (adman.update_user(username, pass,phone,mail,dob,gen,role)) {
                    JOptionPane.showMessageDialog(null,"Update Successfully","Plain",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    //Update Failed
                    int response = JOptionPane.showConfirmDialog(null,"Update Failed, Do you want to Update Again?","Question",JOptionPane.YES_NO_CANCEL_OPTION);
                    if (response == 1) {
                        new Admin_Profile_Page(name).setVisible(true);
                    }
                }
            }
        });
        contentPane.add(up_btn);
        up_btn.setVisible(false);

        // Edit Button
        JButton edit_btn = new JButton("Edit Profile");
        edit_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        edit_btn.setBackground(new Color(250,240,230));
        edit_btn.setForeground(new Color(128,128,128));
        edit_btn.setBounds(430,600,150,30);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pfield.setText(pshow_lbl.getText());
                contentPane.add(pfield);
                pshow_lbl.setVisible(false);

                pnfield.setText(pnshow_lbl.getText());
                contentPane.add(pnfield);
                pnshow_lbl.setVisible(false);

                mfield.setText(mshow_lbl.getText());
                contentPane.add(mfield);
                mshow_lbl.setVisible(false);

                contentPane.add(dobDatePicker);
                dshow_lbl.setVisible(false);

                genData.setSelectedItem(gshow_lbl.getText());
                contentPane.add(genData);
                gshow_lbl.setVisible(false);

                up_btn.setVisible(true);
            }
        });
        contentPane.add(edit_btn);
    }
}
