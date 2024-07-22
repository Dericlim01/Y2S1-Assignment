import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update_Profile extends JFrame {
    private JPanel contentPane;

    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Update_Profile update_p = new Update_Profile(name);
                    update_p.setTitle("Update Profile");
                    update_p.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Update_Profile(String n) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBounds(30, 20, 50, 20);
        contentPane.add(name_lbl);

        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(30, 50, 100, 20);
        contentPane.add(pass_lbl);

        JLabel cont_num_lbl = new JLabel("Contact Number : ");
        cont_num_lbl.setBounds(30, 80, 120, 20);
        contentPane.add(cont_num_lbl);

        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setBounds(30, 110, 100, 20);
        contentPane.add(email_lbl);

        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(140, 20, 100, 20);
        contentPane.add(name_txt_f);

        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(140, 50, 100, 20);
        contentPane.add(pass_txt_f);

        JTextField cont_num_txt_f = new JTextField();
        cont_num_txt_f.setBounds(140, 80, 100, 20);
        contentPane.add(cont_num_txt_f);

        JTextField email_txt_f = new JTextField();
        email_txt_f.setBounds(140, 110, 100, 20);
        contentPane.add(email_txt_f);

        JButton update_btn = new JButton("Update");
        update_btn.setBounds(140, 140, 100, 20);
        contentPane.add(update_btn);
        update_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == update_btn) {
                    String name = name_txt_f.getText();
                    char[] pass = pass_txt_f.getPassword();
                    String cont_num = cont_num_txt_f.getText();
                    String email = email_txt_f.getText();
                    UpdateProfile update_profile = new UpdateProfile();
                    // Update Successfully
                    if (update_profile.update_user(name, pass, cont_num, email)) {
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
                            Update_Profile updateprofile = new Update_Profile(name);
                            updateprofile.setTitle("Update Profile");
                            updateprofile.setVisible(true);
                        }
                    }
                }
            }
        });

        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(n);
        name_txt_f.setText(user[0]);
        pass_txt_f.setText(user[1]);
        cont_num_txt_f.setText(user[2]);
        email_txt_f.setText(user[3]);
    }
}
