import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
 


public class Staff_Add_Page extends JFrame{
    private JPanel contentPane;
    private static String name;
    public static JComboBox roleData;
    public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable(){
        @Override
        public void run(){
            try {
                Staff_Add_Page sad = new Staff_Add_Page(name);
                sad.setTitle("Staff Add");
                sad.setVisible(true);
                sad.getContentPane().setBackground(new Color(230,220,202));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    });
}

public Staff_Add_Page(String name){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(140,100,1000,800);
    setResizable(false);

    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5,5,5,5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

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
    JLabel staffphone_lbl = new JLabel("Phone NO.:");
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
    roleData = new JComboBox<>(roletype);
    roleData.setBounds(300,410,170,30);
    contentPane.add(roleData);

    //Email Text Field
    JTextField staffmail_txt = new JTextField();
    staffmail_txt.setBounds(590,200,170,30);
    contentPane.add(staffmail_txt);

    //D.O.B Calendar
    


    //Gender Text Field
    JTextField staffgen_txt = new JTextField();
    staffgen_txt.setBounds(590,340,170,30);
    contentPane.add(staffgen_txt);
    
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
