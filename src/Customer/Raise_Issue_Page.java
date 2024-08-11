package src.Customer;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Raise_Issue_Page extends JFrame {
    public static String name;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Raise_Issue_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Raise_Issue_Page(String n) {
        setTitle("Raise Issue");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentpane = new JPanel();
        contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentpane);
        contentpane.setLayout(null);

        // Hall type label
        JLabel hall_type_lbl = new JLabel("Hall Type : ");
        hall_type_lbl.setBounds(350, 150, 100, 20);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        contentpane.add(hall_type_lbl);

        // Show Hall type label
        JLabel hall_type_show_lbl = new JLabel();
        hall_type_show_lbl.setBounds(450, 150, 100, 20);
        hall_type_show_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        contentpane.add(hall_type_show_lbl);

        // Hall type label
        JLabel hall_id_lbl = new JLabel("Hall Type : ");
        hall_id_lbl.setBounds(100, 150, 100, 20);
        hall_id_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        contentpane.add(hall_id_lbl);

        // Hall id
        ArrayList<String> hallsList = new Raise_Issue(n).search_hall();
        String[] halls = hallsList.toArray(new String[0]);
        JComboBox<String> hall_id_cmbbx = new JComboBox<String>(halls);
        hall_id_cmbbx.setBounds(200, 150, 100, 25);
        hall_id_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hall_id_cmbbx.setSelectedIndex(-1);
        hall_id_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hall_id = String.valueOf(hall_id_cmbbx.getSelectedItem());
                String hall_type = new Raise_Issue(n).search_id(hall_id);
                hall_type_show_lbl.setText(hall_type);
            }
        });
        contentpane.add(hall_id_cmbbx);

        // Issue Label
        JLabel issue_title_lbl = new JLabel("Issue : ");
        issue_title_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        issue_title_lbl.setBounds(100, 200, 100, 30);
        contentpane.add(issue_title_lbl);

        // Issue title text area
        JTextArea issue_title_txt = new JTextArea(10, 50);
        issue_title_txt.setEditable(true);
        issue_title_txt.setLineWrap(true);
        issue_title_txt.setBounds(100, 230, 400, 35);
        contentpane.add(issue_title_txt);

        // Issue Description label
        JLabel issue_desc_lbl = new JLabel("Issue Description : ");
        issue_desc_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        issue_desc_lbl.setBounds(100, 270, 200, 30);
        contentpane.add(issue_desc_lbl);

        // Issue Description text area
        JTextArea issue_desc_txt = new JTextArea(10, 50);
        issue_desc_txt.setEditable(true);
        issue_desc_txt.setLineWrap(true);
        issue_desc_txt.setBounds(100, 300, 400, 300);
        contentpane.add(issue_desc_txt);

        // Submit button
        JButton submit_issue_btn = new JButton("Submit Issue");
        submit_issue_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        submit_issue_btn.setBounds(100, 630, 200, 30);
        submit_issue_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new Raise_Issue(n).send_issue(issue_title_txt.getText(), issue_desc_txt.getText())) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Issue send successfully",
                        "Issue Status",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Issue send failed",
                        "Issue Status",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        contentpane.add(submit_issue_btn);

        // Back button
        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        back_btn.setBounds(400, 630, 100, 30);
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Customer_Page(n).setVisible(true);
            }
        });
        contentpane.add(back_btn);
    }
}
