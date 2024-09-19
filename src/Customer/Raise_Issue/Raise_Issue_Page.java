package src.Customer.Raise_Issue;

import src.Customer.Customer_Page;

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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentpane = new JPanel();
        contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentpane.setBackground(new Color(248,248,255));
        setContentPane(contentpane);
        contentpane.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentpane.add(logo_lbl);

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
        contentpane.add(logo);

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
        JLabel hall_id_lbl = new JLabel("Hall ID : ");
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
                if (new Raise_Issue(n).send_issue(issue_title_txt.getText(), 
                                                    issue_desc_txt.getText(), 
                                                    String.valueOf(hall_id_cmbbx.getSelectedItem()))) {
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
                new Customer_Page(n).setVisible(true);
            }
        });
        contentpane.add(back_lbl);

        // Design 4 Pic
        JLabel des4 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));
            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentpane.add(des4);
    }
}
