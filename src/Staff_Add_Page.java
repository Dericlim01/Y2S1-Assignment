package src;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.util.Properties;
import org.jdatepicker.impl.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;


public class Staff_Add_Page extends JFrame{
    private JPanel contentPane;
    private static String name;

    public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable(){
        @Override
        public void run(){
            try {
                new Staff_Add_Page(name).setVisible(true);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    });
}

    public Staff_Add_Page(String name){
        setTitle("Staff Add Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(230,220,202));

        //Staff Information Adding Label
        JLabel staffadd_lbl = new JLabel("Add Staff Information");
        staffadd_lbl.setFont(new Font("Broadway",Font.PLAIN,20));
        staffadd_lbl.setBounds(380,100,300,30);
        contentPane.add(staffadd_lbl);


        //Add Staff information and register staff
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

        //Staffname Text Field
        JTextField staffname_txt = new JTextField();
        staffname_txt.setBounds(300,200,170,30);
        contentPane.add(staffname_txt);

        //Staffpass Text Field
        JTextField staffpass_txt = new JTextField();
        staffpass_txt.setBounds(300,270,170,30);
        contentPane.add(staffpass_txt);

        //Phone Text Field
        JTextField staffphone_txt = new JTextField();
        staffphone_txt.setBounds(300,340,170,30);
        contentPane.add(staffphone_txt);

        //Role Combo Box
        String[] roletype = {"scheduler", "manager"};
        JComboBox<String> roleData = new JComboBox<>(roletype);
        roleData.setBounds(300,410,170,30);
        contentPane.add(roleData);

        //Email Text Field
        JTextField staffmail_txt = new JTextField();
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
        JDatePickerImpl dobDatePicker = new JDatePickerImpl(datePanel, new DateFormat());
        dobDatePicker.setBounds(590,270,170,30);
        contentPane.add(dobDatePicker);

        //Gender Combo Box
        String[] gender = {"male","female"};
        JComboBox<String> genData = new JComboBox<>(gender);
        genData.setBounds(590,340,170,30);
        contentPane.add(genData);

        //Add New Staff Button
        JButton staffadd_btn = new JButton("Add New Staff");
        staffadd_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffadd_btn.setBounds(400,500,170,30);
        staffadd_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //get the data from textfield/combobox/datepicker
                String staffname = staffname_txt.getText();
                String staffpass = staffpass_txt.getText();
                String staffphone = staffphone_txt.getText();
                String staffmail = staffmail_txt.getText();
                //casting datatype to Date form
                Date staffdob = (Date) dobDatePicker.getModel().getValue();
                //casting datatype to String form
                String staffgen = (String) genData.getSelectedItem();
                String staffrole = (String) roleData.getSelectedItem(); 

                Staff_Management Staff_Management = new Staff_Management(name);
                Register register_user = new Register(String.valueOf(roleData.getSelectedItem()));

                //Adding the informations into staffs text file
                //Checking staff whether data is existing
                if(Staff_Management.check_staff(staffname)){
                    //Staffname not used, start adding
                    if(Staff_Management.add_staff(staffname, staffpass, staffphone, staffmail, staffdob, staffgen, staffrole)){
                        //Show Message Dialog
                        if(register_user.chk_user(name)){
                            if(register_user.reg_user(staffname, staffpass, staffphone, staffmail)){
                                int response = JOptionPane.showConfirmDialog(null,"Staff Added Successfully. Do you want to add again?","Question" ,JOptionPane.YES_NO_OPTION);
                                if(response == 0){
                                    //add again
                                    Staff_Add_Page sad = new Staff_Add_Page(name);
                                    sad.setTitle("Staff Add");
                                    sad.setVisible(true);
                                }
                                else{
                                    //back to management view page
                                    dispose();
                                    Staff_Management_Page sm = new Staff_Management_Page(name);
                                    sm.setTitle("Staff Management");
                                    sm.setVisible(true);
                                }
                            }
                        }                    
                    }
                    
                    else{
                        //Failed to Add New Staff, info insert not completed
                        int response = JOptionPane.showConfirmDialog(null,"Add Staff Failed. Do you want to add again?","Question" ,JOptionPane.YES_NO_OPTION);
                        if(response == 0){
                            //add again
                            Staff_Add_Page sad = new Staff_Add_Page(name);
                            sad.setTitle("Staff Add");
                            sad.setVisible(true);
                        }
                        else{
                            //back to management view page
                            dispose();
                            Staff_Management_Page sm = new Staff_Management_Page(name);
                            sm.setTitle("Staff Management");
                            sm.setVisible(true);
                        }
                    }
                }
                else{
                    //Staffname exists
                    int response = JOptionPane.showConfirmDialog(null,"Staff name exists. Do you want to add again?","Question" ,JOptionPane.YES_NO_OPTION);
                    if(response == 0){
                        //add again
                        Staff_Add_Page sad = new Staff_Add_Page(name);
                        sad.setTitle("Staff Add");
                        sad.setVisible(true);
                    }
                    else{
                        //back to management view page
                        dispose();
                        Staff_Management_Page sm = new Staff_Management_Page(name);
                        sm.setTitle("Staff Management");
                        sm.setVisible(true);
                    }
                }
            }
        });
        contentPane.add(staffadd_btn);
    
        //Back Staff Management Button
        JButton backstaff_btn = new JButton("Back");
        backstaff_btn.setBounds(870,30,70,40);
        backstaff_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Staff_Management_Page sm = new Staff_Management_Page(name);
                sm.setTitle("Staff Management");
                sm.setVisible(true);
            }
        });
        contentPane.add(backstaff_btn);

    }


}


