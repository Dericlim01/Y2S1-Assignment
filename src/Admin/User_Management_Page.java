package src.Admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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

public class User_Management_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private JScrollPane scrollPane;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new User_Management_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public User_Management_Page(String name) {
        setTitle("User Management Page");
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

        //User Management Label
        JLabel userman_lbl = new JLabel("User Management");
        userman_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        userman_lbl.setBounds(390,100,300,30);
        contentPane.add(userman_lbl);

        //Users Data Table Showing
        User_Management userman = new User_Management();
        String[] users_table = {"Username","Password","Role"};
        scrollPane = userman.view_users(users_table);
        scrollPane.setBounds(100,150,800,380);
        contentPane.add(scrollPane); 
        //showing the scrollbars, setup scrollpane clients
        scrollPane.setViewportView(userman.view_users(users_table));
        scrollPane.setPreferredSize(new Dimension(200,150));

        //Filter Button
        JButton filter_btn = new JButton("~Click Me To Filter by Role~");
        filter_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        filter_btn.setBounds(150,570,300,20);
        filter_btn.setBackground(new Color(250,240,230));
        filter_btn.setForeground(new Color(128,128,128));
        contentPane.add(filter_btn);

        //Edit Delete Page Button
        JButton edit_btn = new JButton("~Clicke Me To Edit & Delete~");
        edit_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        edit_btn.setBounds(550,570,300,20);
        edit_btn.setBackground(new Color(250,240,230));
        edit_btn.setForeground(new Color(128,128,128));
        contentPane.add(edit_btn);

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
                Home_back hb = new Home_back();
                String[] users = hb.home_back(name);

                if (users != null && users.length > 2){
                    String role = users[2];
                    if(role.equals("admin")){
                        Admin_Page ad = new Admin_Page(name);
                        ad.setTitle("Admin");
                        ad.setVisible(true);
                    }
                    else if(role.equals("superadmin")){
                        Suadmin_Page suad = new Suadmin_Page(name);
                        suad.setTitle("Super Admin");
                        suad.setVisible(true);
                    }
                    else{
                        System.out.println("Error Occured.");
                    }
                }              
               
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
