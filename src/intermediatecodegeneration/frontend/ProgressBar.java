package intermediatecodegeneration.frontend;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBar extends JFrame {
    private JProgressBar progressBar;
    int i=0,num=0;
    
    ProgressBar(){
        progressBar=new JProgressBar(0,2000);
        progressBar.setBounds(40, 40, 160, 30);
        progressBar.setVisible(true);
        progressBar.setStringPainted(true);
        add(progressBar);
        this.setSize(250,150);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("loading..");
        this.setFocusable(true);
    }
    
    public void iterate(){
        while(i<=2000){
            progressBar.setValue(i);
            i=i+25;
            try{
                 Thread.sleep(150);
             }
             catch(Exception e){
             } 
        }
        dispose();
    }
    
    public static void main(String[] args){
        ProgressBar pb = new ProgressBar();
        pb.iterate();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Quadruples Generation using BODMAS");
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
    }
}
