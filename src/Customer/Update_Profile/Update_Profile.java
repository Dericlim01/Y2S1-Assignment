package src.Customer.Update_Profile;
import src.Customer.Customer_Page;
import src.DateFormat;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class Update_Profile extends JFrame {
    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Update_Profile(name).setVisible(true);;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Update_Profile(String n) {
        setTitle("Update Profile");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set Panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,255));

        // search in user txt
        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(n);
        // search in customer txt
        UpdateProfile search_user = new UpdateProfile();
        String[] users = search_user.search_users(n);

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

        // Profile Pic
        JLabel profile = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
            get_image = ImageIO.read(new File("resources\\Image\\labixiaoxin(red).png"));
            Image image = get_image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            profile.setIcon(new ImageIcon(image));
            profile.setBounds(450, 50, 1000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(profile);

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
                Customer_Page cp = new Customer_Page(n);
                cp.setTitle("Customer");
                cp.setVisible(true);
            }
        });
        contentPane.add(back_lbl);

        // Gradient Design
        JLabel gradient = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\gradient.png"));
            Image image = get_image.getScaledInstance(1000, 280, Image.SCALE_SMOOTH);
            gradient.setIcon(new ImageIcon(image));
            gradient.setBounds(0, 0, 1000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(gradient);

        //Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        greet_lbl.setBounds(450,210,200,30);
        contentPane.add(greet_lbl);

        // Name showing Label
        JLabel name_show_lbl = new JLabel(user[0]);
        name_show_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        name_show_lbl.setBounds(470,240,200,30);
        contentPane.add(name_show_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password: ");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_lbl.setBounds(310,280,200,25);
        contentPane.add(pass_lbl);

        // Password text field
        JPasswordField pass_txt_f = new JPasswordField(user[1]);
        pass_txt_f.setBounds(460,280,200,25);
        pass_txt_f.setEchoChar('*');
        pass_txt_f.setEditable(false);
        contentPane.add(pass_txt_f);

        // Reenter Password Label
        JLabel reen_pass_lbl = new JLabel("Re-enter Password : ");
        reen_pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        reen_pass_lbl.setBounds(310,330,200,25);
        contentPane.add(reen_pass_lbl);

        // Re-enter Password text field
        JPasswordField reen_pass_txt_f = new JPasswordField(user[1]);
        reen_pass_txt_f.setBounds(460,330,200,25);
        reen_pass_txt_f.setEchoChar('*');
        reen_pass_txt_f.setEditable(false);
        contentPane.add(reen_pass_txt_f);

        // Contact Number Label
        JLabel cont_num_lbl = new JLabel("Contact Number : ");
        cont_num_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        cont_num_lbl.setBounds(310,380,200,25);
        contentPane.add(cont_num_lbl);

        // Contact number text field
        JTextField cont_num_txt_f = new JTextField(users[1]);
        cont_num_txt_f.setBounds(460,380,200,30);
        cont_num_txt_f.setEditable(false);
        contentPane.add(cont_num_txt_f);

        // Email Label
        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        email_lbl.setBounds(310,430,200,25);
        contentPane.add(email_lbl);

        // Email text field
        JTextField email_txt_f = new JTextField(users[2]);
        email_txt_f.setBounds(460,430,200,25);
        email_txt_f.setEditable(false);
        contentPane.add(email_txt_f);
        
        // DOB
        JLabel dob_lbl = new JLabel("Date of Birth : ");
        dob_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        dob_lbl.setBounds(310,480,200,30);
        contentPane.add(dob_lbl);

        // D.O.B Calendar
        UtilDateModel model = new UtilDateModel();
        //Properties create object to store values in it
        Properties prop = new Properties();
        prop.put("text.day", "Day");
        prop.put("text.month","Month");
        prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
        JDatePickerImpl dobDatePicker = new JDatePickerImpl(datePanel, new DateFormat());
        dobDatePicker.setBounds(460,480,200,25);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dob = dateFormat.parse(users[3]);
            model.setValue(dob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane.add(dobDatePicker);

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(670, 330, 20, 20);
        show_pass.setSelected(false);
        show_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show_pass.isSelected()) {
                    pass_txt_f.setEchoChar((char) 0);
                    reen_pass_txt_f.setEchoChar((char) 0);
                } else {
                    pass_txt_f.setEchoChar('*');
                    reen_pass_txt_f.setEchoChar('*');
                }
            }
        });
        contentPane.add(show_pass);
        
        // Gender Label
        JLabel gender_lbl = new JLabel("Gender : ");
        gender_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        gender_lbl.setBounds(310, 530, 200, 25);
        contentPane.add(gender_lbl);

        // Gender Combo Box
        String[] gender_data = {"male","female"};
        JComboBox<String> gen_cmbbx = new JComboBox<>(gender_data);
        gen_cmbbx.setBackground(new Color(250,240,230));
        gen_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        gen_cmbbx.setBounds(460, 530, 200, 25);
        if (users[4].equals("male")) {
            gen_cmbbx.setSelectedIndex(0);
        } else {
            gen_cmbbx.setSelectedIndex(1);
        }
        gen_cmbbx.setEnabled(false);
        contentPane.add(gen_cmbbx);

        // Update button
        JButton update_btn = new JButton("Update");
        update_btn.setBackground(new Color(250,240,230));
        update_btn.setForeground(new Color(128,128,128));
        update_btn.setBounds(420, 610, 150, 25);
        update_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If password and reenter password match
                if (String.valueOf(pass_txt_f.getPassword()).equals(String.valueOf(reen_pass_txt_f.getPassword()))) {
                    String name = name_show_lbl.getText();
                    String pass = String.valueOf(pass_txt_f.getPassword());
                    String cont_num = cont_num_txt_f.getText();
                    String email = email_txt_f.getText();
                    Date dob = (Date) dobDatePicker.getModel().getValue();
                    String gender = String.valueOf(gen_cmbbx.getSelectedItem());
                    UpdateProfile update_profile = new UpdateProfile();
                    
                    // Update Successfully
                    if (update_profile.update_user(name, pass) && update_profile.update_users(name, cont_num, email, dob, gender)) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Update Successfully",
                            "Plain",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Update Failed
                        int response = JOptionPane.showConfirmDialog(
                            null,
                            "Update Failed, update again?",
                            "Question",
                            JOptionPane.YES_NO_OPTION);
                        if (response == 1) {
                            // Go to update page
                            new Update_Profile(name).setVisible(true);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Password not match",
                        "Password error",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Edit button
        JButton edit_btn = new JButton("Edit");
        edit_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        edit_btn.setBackground(new Color(250,240,230));
        edit_btn.setForeground(new Color(128,128,128));
        edit_btn.setBounds(420, 610, 150, 25);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pass_txt_f.setEditable(true);
                reen_pass_txt_f.setEditable(true);
                cont_num_txt_f.setEditable(true);
                email_txt_f.setEditable(true);
                gen_cmbbx.setEnabled(true);
                contentPane.add(show_pass);
                contentPane.remove(edit_btn);
                contentPane.add(update_btn);
            }
        });
        contentPane.add(edit_btn);
    }
}
