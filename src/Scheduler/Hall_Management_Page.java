package src.Scheduler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Hall_Management_Page extends JFrame {
  public static String name;
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Hall_Management_Page(name).setVisible(true));
    }

    public Hall_Management_Page(String n){
      setTitle("Hall Info Management");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(140, 100, 1000, 800);
      setResizable(false);
      JPanel panel = new JPanel();
      setContentPane(panel);
      panel.setLayout(null);

      // table
      //String[] column = {"Hall ID", "Hall Type", "Capacity", "Price Per Hour"};
      
      

      //button

      JButton addHallBtn = new JButton("Add New Hall");
      addHallBtn.setBounds(100, 200, 200, 45);
      addHallBtn.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
      }


      });
}}

