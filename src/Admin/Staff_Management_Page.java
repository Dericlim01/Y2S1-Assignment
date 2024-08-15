package src.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;

public class Staff_Management_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private String valFilter;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Staff_Management_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Staff_Management_Page(String name) {
        setTitle("Staff Management");
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

        //Staff Management Page Label
        JLabel staffman_lbl = new JLabel("Staff Management");
        staffman_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        staffman_lbl.setBounds(390,100,300,30);
        contentPane.add(staffman_lbl);      

        //Staffs Table Showing
        Staff_Management staff_man = new Staff_Management(name);
        String[] staff_table = {"StaffName","Password","Phone","Mail","D.O.B","Gender","Role"};
        scrollPane = staff_man.view_staff(staff_table);
        scrollPane.setBounds(100,150,800,380);
        contentPane.add(scrollPane); 
        //showing the scrollbars, setup scrollpane clients
        scrollPane.setViewportView(staff_man.view_staff(staff_table));
        scrollPane.setPreferredSize(new Dimension(200,150));

        //View, Filter and Delete Page Button
        //Filter Label
        JLabel filter_lbl = new JLabel("Filter by:");
        filter_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        filter_lbl.setBounds(100,600,150,20);
        contentPane.add(filter_lbl);

        //Gender option combo box
        String[] gen = {"male","female"};
        JComboBox<String> genOp = new JComboBox<>(gen); 
        genOp.setBounds(170,603,150,20);
        genOp.setBackground(new Color(250,240,230));
        //Let this box hidden initially
        genOp.setVisible(false);
        contentPane.add(genOp);
  
        //Role option combo box
        String[] role = {"admin","scheduler", "manager"};
        JComboBox<String> roleOp = new JComboBox<>(role);
        roleOp.setBounds(170,603,150,20);
        roleOp.setBackground(new Color(250,240,230));
        //Let this box hidden initially
        roleOp.setVisible(false);
        contentPane.add(roleOp);

        //Filter option combo box
        String[] option = {"Gender", "Role"};
        JComboBox<String> filOp = new JComboBox<>(option);
        filOp.setBackground(new Color(250,240,230));
        filOp.setBounds(170,603,150,20);
        try {
            filOp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if(filOp.getSelectedItem().equals("Gender")){
                        //Let the gender option combo box appear
                        genOp.setVisible(true);                
                    }
                    else if(filOp.getSelectedItem().equals("Role")){
                        //Let the role option combo box appear
                        roleOp.setVisible(true);                       
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane.add(filOp);


        //Filter Button
        JButton filter_btn = new JButton("Filter");
        filter_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        filter_btn.setBounds(520,600,150,20);
        filter_btn.setBackground(new Color(250,240,230));
        filter_btn.setForeground(new Color(128,128,128));
        try {
            filter_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    //get the filter options from the combo box
                    //casting selected Item to String
                    String opGet = (String) filOp.getSelectedItem();
                    if (opGet.equals("Gender")){
                        //get male or female option
                        valFilter = (String) genOp.getSelectedItem();
                    }
                    else if(opGet.equals("Role")){
                        //get scheduler or manager option
                        valFilter = (String) roleOp.getSelectedItem();
                    }
                    staff_man.filter_staff(opGet, valFilter);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane.add(filter_btn);

        //Refresh Button
        JButton refresh_btn = new JButton("Refresh");
        refresh_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        refresh_btn.setBounds(700,600,150,20);
        refresh_btn.setBackground(new Color(250,240,230));
        refresh_btn.setForeground(new Color(128,128,128));
        try {
            refresh_btn.addActionListener(new ActionListener() {
                @Override
                //refresh this page back to default
                public void actionPerformed(ActionEvent e){
                    //reset/refresh the filter selection box
                    filOp.setSelectedIndex(0);
                    //hidden these two combo box again
                    genOp.setVisible(false);
                    roleOp.setVisible(false);
                    //reset/refresh the table to default
                    scrollPane.setViewportView(staff_man.view_staff(staff_table));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane.add(refresh_btn);

        //Edit Page Button
        JButton edit_btn = new JButton("Edit Staff Info");
        edit_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        edit_btn.setBounds(520,630,150,20);
        edit_btn.setBackground(new Color(250,240,230));
        edit_btn.setForeground(new Color(128,128,128));
        try {
            edit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                dispose();
                Edit_Staff_Page es = new Edit_Staff_Page(name);
                es.setTitle("Edit Staff Page");
                es.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane.add(edit_btn);
 
        //Add Staff Button
        JButton add_btn = new JButton("Add Staff");
        add_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        add_btn.setBounds(700,630,150,20);
        add_btn.setBackground(new Color(250,240,230));
        add_btn.setForeground(new Color(128,128,128));
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Staff_Add_Page sad = new Staff_Add_Page(name);
                sad.setTitle("Staff Add");
                sad.setVisible(true);
            }
        });
        contentPane.add(add_btn);

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
                        dispose();
                        Admin_Page ad = new Admin_Page(name);
                        ad.setTitle("Admin Home Page");
                        ad.setVisible(true);
                    }
                    else if(role.equals("superadmin")){
                        dispose();
                        Suadmin_Page suad = new Suadmin_Page(name);
                        suad.setTitle("Super Admin Home Page");
                        suad.setVisible(true);
                    }
                    else{
                        System.out.println("Error Occured.");
                    }
                }              
               
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
