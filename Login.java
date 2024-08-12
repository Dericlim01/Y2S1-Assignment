import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.Arrays;


public class Login extends JFrame{// private JPanel contentPane;
    public static Login login = new Login();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try{
                    login.setTitle("Login");
                    login.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Login(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false); // ini apa?

        //contentPane.setTitle();
        // JPanel contentPane = new JPanel();
        // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);
        // contentPane.setLayout(null);
        JPanel login_frame = new JPanel();
        login_frame.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(login_frame);
        login_frame.setLayout(null);

        // Name Label
        JLabel name_lbl = new JLabel("Name: ");
        //name_lbl.setBackground(new Color(255, 255, 0));
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        name_lbl.setBounds(250, 250, 100, 30);
        login_frame.add(name_lbl);

        // Password Label

        JLabel pass_lbl = new JLabel("Password: ");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        pass_lbl.setBounds(250, 310, 100, 30);
        login_frame.add(pass_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(400, 250, 150, 30);
        login_frame.add(name_txt_f);
        
        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(400, 310, 150, 30);
        login_frame.add(pass_txt_f);

        // Register Button
        JButton reg_btn = new JButton("Register");
        reg_btn.setBounds(250, 370, 100, 30);
        reg_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register reg = new Register();
                reg.setTitle("Register");
                reg.setVisible(true);
            }
        });
        login_frame.add(reg_btn);

        // Login Button
        JButton login_btn = new JButton("Login");
        login_btn.setBounds(400, 370, 100, 30);
        login_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = name_txt_f.getText();
                char[] pass = pass_txt_f.getPassword();


                String role = login.run_login(name, pass);

                // Login Successful
                if(role.equals("Manager")){
                    Manager_Home_Page man_HP = new Manager_Home_Page(name);
                    man_HP.setTitle("Manager");
                    man_HP.setVisible(true);
                } else{
                    // login failes, show message box
                    JOptionPane.showMessageDialog(null, "Login Failed", "Login Status", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        login_frame.add(login_btn);

    }

    public String run_login(String n, char[] p) {
        String line;

        //check file
        CreateFile file = new CreateFile();
        if(file.user_file()){
            // Read file
            try (BufferedReader read = new BufferedReader(new FileReader("users.txt"))){
                while((line = read.readLine()) != null) {
                    // Create array and store data
                    String[] data = line.split(",");
                    String username = data[0];
                    String password = data[1];

                    // If username and password correct
                    if(username.equals(n) && Arrays.equals(password.toCharArray(), p)) {
                        return data[4];
                    }
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}