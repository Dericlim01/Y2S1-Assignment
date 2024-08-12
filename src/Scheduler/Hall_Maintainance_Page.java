package src.Scheduler;
import javax.swing.*;

public class Hall_Maintainance_Page extends JFrame {
    public static String name;
    public static void main(String[] args) {
    //     EventQueue.invokeLater(new Runnable(){
    //         @Override
    //         public void run() {
    //             try{
    //             new Scheduler_Hall_Schedule(name).setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //     }
    // });
    SwingUtilities.invokeLater(() -> new Hall_Maintainance_Page(name).setVisible(true));
    }
    
    public Hall_Maintainance_Page(String n){
        setTitle("Hall Schedule Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
    }

    //Button
    
    
    
    
}
