import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Hall_Management_Page extends JFrame {
    public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> new Hall_Management_Page().setVisible(true));
      }

      public Hall_Management_Page(){
        setTitle("Hall Info Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        // table
        
        
      }






}

