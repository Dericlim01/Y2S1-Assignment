package src.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Edit_Admin_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private String line;
    private JComboBox<String> adname;
    private JTextField adpass_txt;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Edit_Admin_Page(name).setVisible(true);
                } catch (Exception e) {

                }
            }
        });
    }
    
    public Edit_Admin_Page(String name){
        setTitle("Edit Admin Page");
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

        //Admin login info Edit Label
        JLabel adadd_lbl = new JLabel("Edit Admin Login Information");
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

        //Role Label
        JLabel adrole_lbl = new JLabel("Role:");
        adrole_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adrole_lbl.setBounds(280,340,200,30);
        contentPane.add(adrole_lbl);

        //Admin Name Combo Box
        String[] admin ={};
        adname = new JComboBox<>(admin);
        adname.setBounds(390,200,300,30);
        adname.setBackground(new Color(250,240,230));
        contentPane.add(adname);
        Admin_Management adman = new Admin_Management(name);
        adman.load_admin(adname);
        adname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectName = (String) adname.getSelectedItem();
                if (selectName != null){
                    show_info(selectName);
                }
            }
        });
        
        //Role Show Label
        JLabel adshow_lbl = new JLabel("admin");
        adshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        adshow_lbl.setBounds(500,340,200,30);
        contentPane.add(adshow_lbl);

        //Password Textfield
        adpass_txt = new JTextField();
        adpass_txt.setBounds(390,270,300,30);
        contentPane.add(adpass_txt);

        //Edit Admin Information Button
        JButton editad_btn = new JButton("Edit Admin");
        editad_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        editad_btn.setBounds(360,450,150,30);
        editad_btn.setBackground(new Color(250,240,230));
        editad_btn.setForeground(new Color(128,128,128));
        editad_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminname = (String) adname.getSelectedItem();
                String adpass = adpass_txt.getText();
                String adrole = adshow_lbl.getText();
                if(adman.edit_admin(adminname, adpass,adrole)){
                    int response = JOptionPane.showConfirmDialog(null, "Admin Edit Successfully. Do you want to edit again?","Question",JOptionPane.YES_NO_OPTION);
                    if(response == 0){
                        //edit again
                        dispose();
                        Edit_Admin_Page eap = new Edit_Admin_Page(name);
                        eap.setTitle("Edit Admin Page");
                        eap.setVisible(true);
                    }
                    else{
                        //back to previous page
                        dispose();
                        Admin_Management_Page admanage = new Admin_Management_Page(name);
                        admanage.setTitle("Admin Management Page");
                        admanage.setVisible(true);
                    }
                }
                else{
                    //Failed to Edit Staff Info, info insert not completed
                    int response = JOptionPane.showConfirmDialog(null,"Edit Staff Failed. Do you want to edit again?","Question" ,JOptionPane.YES_NO_OPTION);
                    if(response == 0){
                        //edit again
                        dispose();
                        Edit_Admin_Page eap = new Edit_Admin_Page(name);
                        eap.setTitle("Edit Staff Page");
                        eap.setVisible(true);
                    }
                    else{
                        //back to management view page
                        dispose();
                        Admin_Management_Page admanage = new Admin_Management_Page(name);
                        admanage.setTitle("Admin Management Page");
                        admanage.setVisible(true);
                    }
                }


            }
        });
        contentPane.add(editad_btn);

        //Delete Admin Login Information
        JButton deletead_btn = new JButton("Delete Admin");
        deletead_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        deletead_btn.setBounds(530,450,150,30);
        deletead_btn.setBackground(new Color(250,240,230));
        deletead_btn.setForeground(new Color(128,128,128));
        deletead_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminname = (String) adname.getSelectedItem();
                if(adman.delete_admin(adminname)){
                    int response = JOptionPane.showConfirmDialog(null, "Admin Delete Successfully. Do you want to delete again?","Question",JOptionPane.YES_NO_OPTION);
                    if(response == 0){
                        //delete again
                        dispose();
                        Edit_Admin_Page eap = new Edit_Admin_Page(name);
                        eap.setTitle("Edit Admin Page");
                        eap.setVisible(true);
                    }
                    else{
                        //back to previous page
                        dispose();
                        Admin_Management_Page admanage = new Admin_Management_Page(name);
                        admanage.setTitle("Admin Managment Page");
                        admanage.setVisible(true);
                    }
                }
                else{
                    //Failed to Delete Admin
                    int response = JOptionPane.showConfirmDialog(null,"Delete Staff Failed. Do you want to delete again?","Question" ,JOptionPane.YES_NO_OPTION);
                    if(response == 0){
                        //stay at this page
                        dispose();
                        Edit_Staff_Page esp = new Edit_Staff_Page(name);
                        esp.setTitle("Edit Admin Page");
                        esp.setVisible(true);
                    }
                    else{
                        //back to management view page
                        dispose();
                        Admin_Management_Page admanage = new Admin_Management_Page(name);
                        admanage.setTitle("Admin Management Page");
                        admanage.setVisible(true);
                    }
                }
            }
        });
        contentPane.add(deletead_btn);

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

    //showing admin information
    private void show_info (String selectedName){
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if(data[2].equals("admin") && data[0].equals(selectedName)){
                    adname.setSelectedItem(data[0]);
                    adpass_txt.setText(data[1]);
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
