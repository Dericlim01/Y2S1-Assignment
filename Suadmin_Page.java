import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;


public class Suadmin_Page extends JFrame {
    private JPanel contentPane;
    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Suadmin_Page suad = new Suadmin_Page(name);
                    suad.setTitle("Super Admin");
                    suad.setVisible(true);
                    suad.getContentPane().setBackground(new Color(230,220,202));
       
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Suadmin_Page(String n) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        getContentPane().setBackground(new Color(212,207,192));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Home Page Label
        JLabel suadhome_lbl = new JLabel("Super Admin Home Page");
        suadhome_lbl.setFont(new Font("Broadway",Font.PLAIN,20));
        suadhome_lbl.setBounds(400,100,300,30);
        contentPane.add(suadhome_lbl);

        //Admin Management Button
        JButton adBtn = new JButton("Admin Management");
        adBtn.setBackground(new Color(255,239,222));
        adBtn.setForeground(new Color(88,57,39));
        adBtn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        adBtn.setBounds(400,200,200,30);
        contentPane.add(adBtn);

        //Staff Management Button
        JButton staBtn = new JButton("Staff Management");
        staBtn.setBackground(new Color(255,239,222));
        staBtn.setForeground(new Color(88,57,39));
        staBtn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staBtn.setBounds(400,240,200,30);
        staBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Staff_Management sm = new Staff_Management();
                sm.setTitle("Staff Managemnt");
                sm.setVisible(true);
            }
        });
        contentPane.add(staBtn);

        //User Management Button
        JButton userBtn = new JButton("User Management");
        userBtn.setBackground(new Color(255,239,222));
        userBtn.setForeground(new Color(88,57,39));
        userBtn.setFont(new Font("Comic San MS",Font.PLAIN,15));
        userBtn.setBounds(400,280,200,30);
        contentPane.add(userBtn);

        //Booking Management Button
        JButton bookBtn = new JButton("Booking Management");
        bookBtn.setBackground(new Color(255,239,222));
        bookBtn.setForeground(new Color(88,57,39));
        bookBtn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        bookBtn.setBounds(400,320,200,30);
        contentPane.add(bookBtn);

        //Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBackground(new Color(255,239,222));
        logout_btn.setForeground(new Color(88,57,39));
        logout_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        logout_btn.setBounds(850,30,100,30);
        logout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Login_Page log = new Login_Page();
                log.setTitle("Login");
                log.setVisible(true);
            }
        });
        contentPane.add(logout_btn);
        
    }
}
