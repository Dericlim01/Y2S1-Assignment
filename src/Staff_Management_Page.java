package src;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;


public class Staff_Management_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private JScrollPane scrollPane;
    private String valFilter;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    new Staff_Management_Page(name).setVisible(true);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Staff_Management_Page(String name){
        setTitle("Staff Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(230,220,202));

        //Staff Management Page Label
        JLabel staffman_lbl = new JLabel("Staff Management");
        staffman_lbl.setFont(new Font("Broadway",Font.PLAIN,20));
        staffman_lbl.setBounds(400,100,300,30);
        contentPane.add(staffman_lbl);      

        //Add Staff Button
        JButton add_btn = new JButton("Add Staff");
        add_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        add_btn.setBounds(700,630,150,20);
        add_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Staff_Add_Page sad = new Staff_Add_Page(name);
                sad.setTitle("Staff Add");
                sad.setVisible(true);
                sad.getContentPane().setBackground(new Color(230,220,202));
            }
        });
        contentPane.add(add_btn);

        //Staffs Table Showing
        Staff_Management staff_man = new Staff_Management(name);
        String[] staff_table = {"StaffName","Password","Phone","Mail","D.O.B","Gender","Role"};
        scrollPane = staff_man.view_staff(staff_table);
        scrollPane.setBounds(100,150,800,380);
        contentPane.add(scrollPane); 
        //showing the scrollbars
        scrollPane.setViewportView(staff_man.view_staff(staff_table));
        scrollPane.setPreferredSize(new Dimension(200,150));
       
        //Edit Page Button
        JButton edit_btn = new JButton("Edit Staff Info");
        edit_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        edit_btn.setBounds(520,630,150,20);
        try {
            edit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    Edit_Staff_Page es = new Edit_Staff_Page(name);
                    es.setTitle("Edit Staff Page");
                    es.setVisible(true);
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        contentPane.add(edit_btn);

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
        //Let this box hidden initially
        genOp.setVisible(false);
        contentPane.add(genOp);
  
        //Role option combo box
        String[] role = {"scheduler", "manager"};
        JComboBox<String> roleOp = new JComboBox<>(role);
        roleOp.setBounds(170,603,150,20);
        //Let this box hidden initially
        roleOp.setVisible(false);
        contentPane.add(roleOp);

        //Filter option combo box
        String[] option = {"Gender", "Role"};
        JComboBox<String> filOp = new JComboBox<>(option);
        filOp.setBounds(170,603,150,20);
        try {
            filOp.addActionListener(new ActionListener(){
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
            // TODO: handle exception
            e.printStackTrace();
        }
        contentPane.add(filOp);


        //Filter Button
        JButton filter_btn = new JButton("Filter");
        filter_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        filter_btn.setBounds(520,600,150,20);
        try {
            filter_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    //get the filter options from the combo box
                    String opGet = (String) filOp.getSelectedItem();
                    if (opGet.equals("Gender")){
                        valFilter = (String) genOp.getSelectedItem();
                    }
                    else if(opGet.equals("Role")){
                        valFilter = (String) roleOp.getSelectedItem();
                    }
                    staff_man.filter_staff(opGet, valFilter);

                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        contentPane.add(filter_btn);

        //Refresh Button
        JButton refresh_btn = new JButton("Refresh");
        refresh_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        refresh_btn.setBounds(700,600,150,20);
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
            // TODO: handle exception
            e.printStackTrace();
        }
        contentPane.add(refresh_btn);


        //Home Page Button
        JButton home_btn = new JButton();
        try{
            BufferedImage home_pic = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            home_pic = ImageIO.read(new File("C:\\Users\\ARELLAYIM\\Documents\\JAVA\\Assignment Pictures\\logout.png"));
            Image home_icon = home_pic.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            home_btn.setIcon(new ImageIcon(home_icon));
            home_btn.setBounds(920,30,40,40);

        }catch(IOException e){
            e.printStackTrace();
        }
        home_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Home_back hb = new Home_back();
                String[] users = hb.home_back(name);

                if (users != null && users.length > 4){
                    String role = users[4];
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
        contentPane.add(home_btn);
    }
}