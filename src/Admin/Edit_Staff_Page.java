package src.Admin;

import src.DateFormat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.*;

public class Edit_Staff_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private String line;
    private JTextField staffpass_txt;
    private JTextField staffphone_txt;
    private JTextField staffmail_txt;
    private JComboBox<String> genData;
    private JComboBox<String> roleData;
    private JDatePickerImpl dobDatePicker;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    new Edit_Staff_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Edit_Staff_Page(String name) {
        setTitle("Edit Staff Page");
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

        //Staff Information Edit Label
        JLabel staffadd_lbl = new JLabel("Edit Staff Information");
        staffadd_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        staffadd_lbl.setBounds(380,100,400,30);
        contentPane.add(staffadd_lbl);

        //Edit Staff Information
        //Staffname Label
        JLabel staffname_lbl = new JLabel("Staff Name:");
        staffname_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        staffname_lbl.setBounds(180,200,200,30);
        contentPane.add(staffname_lbl);

        //Password Label
        JLabel staffpass_lbl = new JLabel("Password:");
        staffpass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        staffpass_lbl.setBounds(180,270,200,30);
        contentPane.add(staffpass_lbl);
     
        //Phone Label
        JLabel staffphone_lbl = new JLabel("Phone NO. :");
        staffphone_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffphone_lbl.setBounds(180,340,200,30);
        contentPane.add(staffphone_lbl);

        //Role Label
        JLabel staffrole_lbl = new JLabel("Role:");
        staffrole_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffrole_lbl.setBounds(180,410,200,30);
        contentPane.add(staffrole_lbl);

        //Email Label
        JLabel staffmail_lbl = new JLabel("Email:");
        staffmail_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffmail_lbl.setBounds(500,200,200,30);
        contentPane.add(staffmail_lbl);

        //D.O.B Label
        JLabel staffdob_lbl = new JLabel("D.O.B:");
        staffdob_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffdob_lbl.setBounds(500,270,200,30);
        contentPane.add(staffdob_lbl);

        //Gender Label
        JLabel staffgen_lbl = new JLabel("Gender:");
        staffgen_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffgen_lbl.setBounds(500,340,200,30);
        contentPane.add(staffgen_lbl);

        //Staffpass Text Field
        staffpass_txt = new JTextField();
        staffpass_txt.setBounds(300,270,170,30);
        contentPane.add(staffpass_txt);

        //Phone Text Field
        staffphone_txt = new JTextField();
        staffphone_txt.setBounds(300,340,170,30);
        contentPane.add(staffphone_txt);

        //Role Combo Box
        String[] roletype = {"scheduler", "manager"};
        roleData = new JComboBox<>(roletype);
        roleData.setBounds(300,410,170,30);
        roleData.setBackground(new Color(250,240,230));
        contentPane.add(roleData);

        //Email Text Field
        staffmail_txt = new JTextField();
        staffmail_txt.setBounds(590,200,170,30);
        contentPane.add(staffmail_txt);

        //D.O.B Calendar
        UtilDateModel model = new UtilDateModel();
        //Properties create object to store values in it
        Properties prop = new Properties();
        prop.put("text.day", "Day");
        prop.put("text.month","Month");
        prop.put("text.year", "Year");
        //Import Date Panel and Picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
        dobDatePicker = new JDatePickerImpl(datePanel, new DateFormat());
        dobDatePicker.setBounds(590,270,170,30);
        contentPane.add(dobDatePicker);

        //Gender Combo Box
        String[] gender = {"male","female"};
        genData = new JComboBox<>(gender);
        genData.setBounds(590,340,170,30);
        genData.setBackground(new Color(250,240,230));
        contentPane.add(genData);

        //Staffname ComboBox
        String[] staff ={};
        JComboBox<String> staffname = new JComboBox<>(staff);
        staffname.setBounds(300,200,170,30);
        staffname.setBackground(new Color(250,240,230));
        contentPane.add(staffname);
        Staff_Management sm = new Staff_Management(name);
        sm.load_staff(staffname);
        staffname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectName = (String) staffname.getSelectedItem();
                if (selectName != null){
                    show_info(selectName);
                }
            }

        });

        //Delete Button
        JButton delete_btn = new JButton("Delete Information");
        delete_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        delete_btn.setBounds(530,530,180,30);
        delete_btn.setBackground(new Color(250,240,230));
        delete_btn.setForeground(new Color(128,128,128));
        contentPane.add(delete_btn);
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectName = (String) staffname.getSelectedItem();
                if(selectName != null){
                    Staff_Management sm = new Staff_Management(name);
                    if(sm.delete_staff(selectName)){
                        sm.delete_staffuser(selectName);
                        int response = JOptionPane.showConfirmDialog(null, "Staff Delete Successfully. Do you want to delete again?","Question",JOptionPane.YES_NO_OPTION);
                        if(response == 0){
                            //delete again
                            Edit_Staff_Page esp = new Edit_Staff_Page(name);
                            esp.setTitle("Edit Staff Page");
                            esp.setVisible(true);
                        }
                        else{
                            //back to previous page
                            dispose();
                            Staff_Management_Page sman = new Staff_Management_Page(name);
                            sman.setTitle("Staff Managment");
                            sman.setVisible(true);
                        }
                    }
                    else{
                        //Failed to Delete Staff
                        int response = JOptionPane.showConfirmDialog(null,"Delete Staff Failed. Do you want to delete again?","Question" ,JOptionPane.YES_NO_OPTION);
                        if(response == 0){
                            //stay at this page
                            Edit_Staff_Page esp = new Edit_Staff_Page(name);
                            esp.setTitle("Edit Staff Page");
                            esp.setVisible(true);
                        }
                        else{
                            //back to management view page
                            dispose();
                            Staff_Management_Page sman = new Staff_Management_Page(name);
                            sman.setTitle("Staff Management");
                            sman.setVisible(true);
                        }
                    }
                }
            }
        });

        //Update Button
        JButton update_btn = new JButton("Update Information");
        update_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        update_btn.setBounds(330,530,180,30);
        update_btn.setBackground(new Color(250,240,230));
        update_btn.setForeground(new Color(128,128,128));
        contentPane.add(update_btn);
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectName = (String) staffname.getSelectedItem();
                if(selectName != null){
                    String editpass = staffpass_txt.getText();
                    String editphone = staffphone_txt.getText();
                    String editmail = staffmail_txt.getText();
                    Date editdob = (Date) dobDatePicker.getModel().getValue();
                    String editgen = (String) genData.getSelectedItem();
                    String editrole = (String) roleData.getSelectedItem();
                    Staff_Management sm = new Staff_Management(name);
                    if(sm.edit_staff(selectName, editpass, editphone, editmail, editdob, editgen, editrole)) {
                        sm.edit_to_users(selectName, editpass, editphone, editmail, editrole);
                        int response = JOptionPane.showConfirmDialog(null, "Staff Edit Successfully. Do you want to edit again?","Question",JOptionPane.YES_NO_OPTION);
                        if(response == 0){
                            //edit again
                            Edit_Staff_Page esp = new Edit_Staff_Page(name);
                            esp.setTitle("Edit Staff Page");
                            esp.setVisible(true);
                        }
                        else{
                            //back to previous page
                            dispose();
                            Staff_Management_Page sman = new Staff_Management_Page(name);
                            sman.setTitle("Staff Managment");
                            sman.setVisible(true);
                        }
                    }
                    
                    else{
                        //Failed to Edit Staff Info, info insert not completed
                        int response = JOptionPane.showConfirmDialog(null,"Edit Staff Failed. Do you want to edit again?","Question" ,JOptionPane.YES_NO_OPTION);
                        if(response == 0){
                            //edit again
                            Edit_Staff_Page esp = new Edit_Staff_Page(name);
                            esp.setTitle("Edit Staff Page");
                            esp.setVisible(true);
                        }
                        else{
                            //back to management view page
                            dispose();
                            Staff_Management_Page sman = new Staff_Management_Page(name);
                            sman.setTitle("Staff Management");
                            sman.setVisible(true);
                        }
                    }
                }
            }
        });

        //Back Staff Management Label
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
                Staff_Management_Page sm = new Staff_Management_Page(name);
                sm.setTitle("Staff Management");
                sm.setVisible(true);
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

    //showing staff information
    private void show_info (String name){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))){
            while((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if(data[0].equals(name)){
                    staffpass_txt.setText(data[1]);
                    staffphone_txt.setText(data[2]);
                    staffmail_txt.setText(data[3]);
                    
                    Date dobDate = sdf.parse(data[4]);
                    UtilDateModel dateModel = (UtilDateModel) dobDatePicker.getModel();
                    dateModel.setValue(dobDate);
                    
                    genData.setSelectedItem(data[5]);
                    roleData.setSelectedItem(data[6]);
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
