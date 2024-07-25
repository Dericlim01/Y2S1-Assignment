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
                    new Update_Profile(name).setVisible(true);;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Update_Profile(String n) {
        setTitle("Update Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(n);

        // Name Label
        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBounds(30, 20, 50, 20);
        contentPane.add(name_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(30, 50, 100, 20);
        contentPane.add(pass_lbl);

        // Contact Number Label
        JLabel cont_num_lbl = new JLabel("Contact Number : ");
        cont_num_lbl.setBounds(30, 80, 120, 20);
        contentPane.add(cont_num_lbl);

        // Email Label
        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setBounds(30, 110, 100, 20);
        contentPane.add(email_lbl);

        // Name showing Label
        JLabel name_show_lbl = new JLabel(user[0]);
        name_show_lbl.setBounds(140, 20, 100, 20);
        contentPane.add(name_show_lbl);

        // Password showing Label
        JLabel pass_show_lbl = new JLabel(user[1]);
        pass_show_lbl.setBounds(140, 50, 100, 20);
        contentPane.add(pass_show_lbl);

        // Contact number showing Label
        JLabel cont_num_show_lbl = new JLabel(user[2]);
        cont_num_show_lbl.setBounds(140, 80, 100, 20);
        contentPane.add(cont_num_show_lbl);

        // Email showing Label
        JLabel email_show_lbl = new JLabel(user[3]);
        email_show_lbl.setBounds(140, 110, 100, 20);
        contentPane.add(email_show_lbl);

        // Password text field
        JTextField pass_txt_f = new JTextField();
        pass_txt_f.setBounds(140, 50, 100, 20);

        // Contact number text field
        JTextField cont_num_txt_f = new JTextField();
        cont_num_txt_f.setBounds(140, 80, 100, 20);

        // Email text field
        JTextField email_txt_f = new JTextField();
        email_txt_f.setBounds(140, 110, 100, 20);

        // Update button
        JButton update_btn = new JButton("Update");
        update_btn.setBounds(140, 140, 100, 20);
        update_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_show_lbl.getText();
                String pass = pass_txt_f.getText();
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
        });

        // Edit button
        JButton edit_btn = new JButton("Edit");
        edit_btn.setBounds(140, 140, 100, 20);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pass_txt_f.setText(pass_show_lbl.getText());
                cont_num_txt_f.setText(cont_num_show_lbl.getText());
                email_txt_f.setText(email_show_lbl.getText());

                contentPane.add(pass_txt_f);
                contentPane.add(cont_num_txt_f);
                contentPane.add(email_txt_f);
                contentPane.remove(edit_btn);
                contentPane.add(update_btn);
            }
        });
        contentPane.add(edit_btn);
    }
}
