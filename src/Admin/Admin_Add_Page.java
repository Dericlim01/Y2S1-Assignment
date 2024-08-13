package src.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.Register;

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

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,248));
        
        //Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        //Logo Pic
        JLabel logo = new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));

            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(logo);

        //Admin login info Adding Label
        JLabel adadd_lbl = new JLabel("Add Admin Login Information");
        adadd_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        adadd_lbl.setBounds(365,100,350,30);
        contentPane.add(adadd_lbl);

        //Admin Name Label
        JLabel adname_lbl = new JLabel("Admin Name:");
        adname_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adname_lbl.setBounds(280,200,200,30);
        contentPane.add(adname_lbl);

        //Password Label
        JLabel adpass_lbl = new JLabel("Password:");
        adpass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adpass_lbl.setBounds(280,270,200,30);
        contentPane.add(adpass_lbl);

        //Phone Label
        JLabel adphone_lbl = new JLabel("Phone:");
        adphone_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adphone_lbl.setBounds(280,340,200,30);
        contentPane.add(adphone_lbl);

        //Email Label
        JLabel admail_lbl = new JLabel("Email:");
        admail_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        admail_lbl.setBounds(280,410,200,30);
        contentPane.add(admail_lbl);

        //Role Label
        JLabel adrole_lbl = new JLabel("Role:");
        adrole_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adrole_lbl.setBounds(280,480,200,30);
        contentPane.add(adrole_lbl);
        
        //Role Show Label
        JLabel adshow_lbl = new JLabel("admin");
        adshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adshow_lbl.setBounds(500,480,200,30);
        contentPane.add(adshow_lbl);

        //Admin Name Textfield
        JTextField adname_txt = new JTextField();
        adname_txt.setBounds(390,200,300,30);
        contentPane.add(adname_txt);

        //Password Textfield
        JTextField adpass_txt = new JTextField();
        adpass_txt.setBounds(390,270,300,30);
        contentPane.add(adpass_txt);

        //Phone Textfield
        JTextField adphone_txt = new JTextField();
        adphone_txt.setBounds(390,340,300,30);
        contentPane.add(adphone_txt);

        //Email Textfield
        JTextField admail_txt = new JTextField();
        admail_txt.setBounds(390,410,300,30);
        contentPane.add(admail_txt);

        //Add New Admin Button
        JButton adadd_btn = new JButton("Add Admin Login Information");
        adadd_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        adadd_btn.setBounds(400,580,240,30);
        adadd_btn.setBackground(new Color(250,240,230));
        adadd_btn.setForeground(new Color(128,128,128));
        adadd_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adname = adname_txt.getText();
                String adpass = adpass_txt.getText();
                String adphone = adphone_txt.getText();
                String admail = admail_txt.getText();
                String adrole = adshow_lbl.getText();

                Register register = new Register(adrole);

                // Check if any text field is empty
                if (adname.isEmpty() || adpass.isEmpty() || adphone.isEmpty() || admail.isEmpty() || adrole.isEmpty()) {
                    // Show a message prompting the user to fill in all fields
                    JOptionPane.showMessageDialog(null, "Please complete all fields before adding the admin.");
                }
                else {
                    // Checking if admin data already exists
                    if (register.chk_user(adname)) {
                        // Admin name not used, start adding
                        if (register.reg_user(adname, adpass, adphone, admail)) {
                            int response = JOptionPane.showConfirmDialog(null, "Admin Added Successfully. Do you want to add again?", "Question", JOptionPane.YES_NO_OPTION);
                            if (response == 0) {
                                // Add again
                                Admin_Add_Page adadd = new Admin_Add_Page(name);
                                adadd.setTitle("Admin Add Page");
                                adadd.setVisible(true);
                            } 
                            else {
                                // Back to management view page
                                dispose();
                                Admin_Management_Page adman = new Admin_Management_Page(name);
                                adman.setTitle("Admin Management Page");
                                adman.setVisible(true);
                            }
                        }
                        else{
                            //Admin name exist
                            //Failed to add new admin, info insert not completed
                            int response = JOptionPane.showConfirmDialog(null,"Admin Name Exist. Do you want to add again?","Question" ,JOptionPane.YES_NO_OPTION);
                            if(response == 0){
                                //add again
                                Admin_Add_Page adadd = new Admin_Add_Page(name);
                                adadd.setTitle("Admin Add Page");
                                adadd.setVisible(true);
                            }
                            else{
                                //back to management view page
                                dispose();
                                Admin_Management_Page adman = new Admin_Management_Page(name);
                                adman.setTitle("Admin Management Page");
                                adman.setVisible(true);
                            }
                        }
                    }
                    else{
                        //back to management view page
                        dispose();
                        Admin_Management_Page adman = new Admin_Management_Page(name);
                        adman.setTitle("Admin Management Page");
                        adman.setVisible(true);
                    }
                }              
            }
            
        });
        contentPane.add(adadd_btn);

        //Back Page Pic
        JLabel back_lbl = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));

            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);

        } catch(IOException e){
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Admin_Management_Page adman = new Admin_Management_Page(name);
                adman.setTitle("Admin Management Page");
                adman.setVisible(true);          
            }

        });
        contentPane.add(back_lbl);

        //Design 4 Background Pic
        JLabel des4 = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));

            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(des4);

    }
    
    
}
