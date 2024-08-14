package src.Admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Admin_Management_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Admin_Management_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
    
    public Admin_Management_Page(String name){
        setTitle("Admin Management Page");
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

        //Admin Management Label
        JLabel suadhome_lbl = new JLabel("Admin Management");
        suadhome_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        suadhome_lbl.setBounds(390,100,300,30);
        contentPane.add(suadhome_lbl);

        //Admin Table Showing
        Admin_Management ad_man = new Admin_Management(name);
        String[] admin_table = {"Admin","Password","Role"};
        scrollPane = ad_man.view_admin(admin_table);
        scrollPane.setBounds(100,150,800,380);
        contentPane.add(scrollPane); 
        //showing the scrollbars, setup scrollpane clients
        scrollPane.setViewportView(ad_man.view_admin(admin_table));
        scrollPane.setPreferredSize(new Dimension(200,150));
        
        //Add Admin Button
        JButton addad_btn = new JButton("Add Admin");
        addad_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        addad_btn.setBackground(new Color(250,240,230));
        addad_btn.setForeground(new Color(128,128,128));
        addad_btn.setBounds(120,600,150,20);
        addad_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
            Admin_Add_Page addad = new Admin_Add_Page(name);
            addad.setTitle("Admin Add Page");
            addad.setVisible(true);
            }
        });
        contentPane.add(addad_btn);

        //Edit Admin Button
        JButton editad_btn = new JButton("Edit Admin");
        editad_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        editad_btn.setBackground(new Color(250,240,230));
        editad_btn.setForeground(new Color(128,128,128));
        editad_btn.setBounds(420,600,150,20);
        editad_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Edit_Admin_Page editad = new Edit_Admin_Page(name);
                editad.setTitle("Edit Admin Page");
                editad.setVisible(true);
            }
        });
        contentPane.add(editad_btn);

        //Raise Staff to Admin
        JButton raisest_btn = new JButton("Raise Staff");
        raisest_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        raisest_btn.setBackground(new Color(250,240,230));
        raisest_btn.setForeground(new Color(128,128,128));
        raisest_btn.setBounds(720,600,150,20);
        raisest_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        contentPane.add(raisest_btn);

        //Home Page Label
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
                Suadmin_Page ad = new Suadmin_Page(name);
                ad.setTitle("Super Admin");
                ad.setVisible(true);
            }
        });
        contentPane.add(back_lbl);
        
        //Design 4 Pic
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
