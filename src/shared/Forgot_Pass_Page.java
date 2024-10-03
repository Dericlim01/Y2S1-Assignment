package src.shared;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

public class Forgot_Pass_Page extends JFrame {
    private static String name;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Forgot_Pass_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Forgot_Pass_Page(String n) {
        init_components();
        name = n;
        name_txt_f.setText(n);
    }

    private void init_components() {
        setTitle("Forgot Password Page");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(248, 248, 248));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        // Logo Pic
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(logo);

        // Home Page Label
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login_Page().setVisible(true);
            }
        });
        contentPane.add(back_lbl);

        // Design Pic 1
        JLabel des1 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design1.png"));
            Image image = get_image.getScaledInstance(300, 280, Image.SCALE_SMOOTH);
            des1.setIcon(new ImageIcon(image));
            des1.setBounds(780, 0, 250, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des1);

        // Design3 Pic
        JLabel des3 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design3.png"));
            Image image = get_image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
            des3.setIcon(new ImageIcon(image));
            des3.setBounds(-50, -50, 400, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des3);

        // Forgot Password Label
        fog_lbl = new JLabel("Forgot Password");
        fog_lbl.setFont(new Font("Engravers MT", Font.PLAIN|Font.PLAIN, 20));
        fog_lbl.setBounds(370, 120, 300, 45);
        contentPane.add(fog_lbl);

        // Username Label
        name_lbl = new JLabel("Username : ");
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        name_lbl.setBounds(240, 200, 150, 30);
        contentPane.add(name_lbl);

        // Name Text Field
        name_txt_f = new JTextField();
        name_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        name_txt_f.setBounds(420, 200, 300, 30);
        name_txt_f.setEditable(false);
        contentPane.add(name_txt_f);

        // Password Label
        pass_lbl = new JLabel("New Password : ");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_lbl.setBounds(240, 270, 150, 30);
        contentPane.add(pass_lbl);

        // Password Text Field
        password_txt_f = new JPasswordField();
        password_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        password_txt_f.setBounds(420, 270, 300, 30);
        password_txt_f.setEchoChar('*');
        contentPane.add(password_txt_f);

        // Confirm Password Label
        confirm_pass_lbl = new JLabel("Confirm Password : ");
        confirm_pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        confirm_pass_lbl.setBounds(240, 340, 150, 30);
        contentPane.add(confirm_pass_lbl);

        // Confirm Password Text Field
        confirm_pass_txt_f = new JPasswordField();
        confirm_pass_txt_f.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        confirm_pass_txt_f.setBounds(420, 340, 300, 30);
        confirm_pass_txt_f.setEchoChar('*');
        contentPane.add(confirm_pass_txt_f);

        // Show password check box
        show_pass = new JCheckBox();
        show_pass.setBounds(730, 345, 20, 20);
        show_pass.setSelected(false);
        show_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show_pass.isSelected()) {
                    password_txt_f.setEchoChar((char) 0);
                    confirm_pass_txt_f.setEchoChar((char) 0);
                } else {
                    password_txt_f.setEchoChar('*');
                    confirm_pass_txt_f.setEchoChar('*');
                }
            }
        });
        contentPane.add(show_pass);
        
        // Submit Button
        submit_btn = new JButton("Submit");
        submit_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        submit_btn.setBackground(new Color(250, 240, 230));
        submit_btn.setForeground(new Color(120, 120, 120));
        submit_btn.setBounds(445, 410, 100, 30);
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_btn_ActionPerformed(e);
            }
        });
        contentPane.add(submit_btn);

        password_txt_f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit();
                }
            }
        });

        confirm_pass_txt_f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit();
                }
            }
        });
    }

    public void submit_btn_ActionPerformed(ActionEvent e) {
        submit();
    }

    public void submit() {
        String pass = new String(password_txt_f.getPassword());
        String confirm_pass = new String(confirm_pass_txt_f.getPassword());

        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                                        "Password cannot be empty!",
                                        "Password Field Empty",
                                        JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (pass.equals(confirm_pass)) {
                if (new Forgot_Pass().write_file(name, pass)) {
                    JOptionPane.showMessageDialog(null,
                                                "Password changed successfully!",
                                                "Password changing status",
                                                JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new Login_Page().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                                                "Password change failed!",
                                                "Password changing status",
                                                JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                                            "Passwords do not match!",
                                            "Password mismatch",
                                            JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private JPanel contentPane;
    private JLabel fog_lbl;
    private JLabel name_lbl;
    private JTextField name_txt_f;
    private JLabel pass_lbl;
    private JPasswordField password_txt_f;
    private JLabel confirm_pass_lbl;
    private JPasswordField confirm_pass_txt_f;
    private JCheckBox show_pass;
    private JButton submit_btn;
}
