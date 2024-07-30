import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(n);

        // Name Label
        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBounds(30, 20, 50, 20);
        contentPane.add(name_lbl);

        // Name showing Label
        JLabel name_show_lbl = new JLabel(user[0]);
        name_show_lbl.setBounds(140, 20, 100, 20);
        contentPane.add(name_show_lbl);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setBounds(30, 50, 100, 20);
        contentPane.add(pass_lbl);

        // Password text field
        JPasswordField pass_txt_f = new JPasswordField(user[1]);
        pass_txt_f.setBounds(140, 50, 100, 20);
        pass_txt_f.setEchoChar('*');
        pass_txt_f.setEditable(false);
        contentPane.add(pass_txt_f);

        // Reenter Password Label
        JLabel reen_pass_lbl = new JLabel("Re-enter Password : ");
        reen_pass_lbl.setBounds(30, 80, 100, 20);
        contentPane.add(reen_pass_lbl);

        // Re-enter Password text field
        JPasswordField reen_pass_txt_f = new JPasswordField(user[1]);
        reen_pass_txt_f.setBounds(140, 80, 100, 20);
        reen_pass_txt_f.setEchoChar('*');
        reen_pass_txt_f.setEditable(false);
        contentPane.add(reen_pass_txt_f);

        // Contact Number Label
        JLabel cont_num_lbl = new JLabel("Contact Number : ");
        cont_num_lbl.setBounds(30, 110, 120, 20);
        contentPane.add(cont_num_lbl);

        // Contact number text field
        JTextField cont_num_txt_f = new JTextField(user[2]);
        cont_num_txt_f.setBounds(140, 110, 100, 20);
        cont_num_txt_f.setEditable(false);
        contentPane.add(cont_num_txt_f);

        // Email Label
        JLabel email_lbl = new JLabel("Email : ");
        email_lbl.setBounds(30, 140, 100, 20);
        contentPane.add(email_lbl);

        // Email text field
        JTextField email_txt_f = new JTextField(user[3]);
        email_txt_f.setBounds(140, 140, 100, 20);
        email_txt_f.setEditable(false);
        contentPane.add(email_txt_f);

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(560, 315, 20, 20);
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

        // Update button
        JButton update_btn = new JButton("Update");
        update_btn.setBounds(140, 170, 100, 20);
        update_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If password and reenter password match
                if (String.valueOf(pass_txt_f.getPassword()).equals(String.valueOf(reen_pass_txt_f.getPassword()))) {
                    String name = name_show_lbl.getText();
                    String pass = String.valueOf(pass_txt_f.getPassword());
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
        edit_btn.setBounds(140, 170, 100, 20);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pass_txt_f.setEditable(true);
                reen_pass_txt_f.setEditable(true);
                cont_num_txt_f.setEditable(true);
                email_txt_f.setEditable(true);
                contentPane.add(show_pass);
                contentPane.remove(edit_btn);
                contentPane.add(update_btn);
            }
        });
        contentPane.add(edit_btn);
    }
}
