package intermediatecodegeneration.frontend;

import intermediatecodegeneration.IntermediateCodeGeneration;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MainFrame extends javax.swing.JFrame {
    
    private StringBuffer expression=new StringBuffer();
    
    public MainFrame() {
        initComponents();
        Expression.setFont(new Font("Trebuchet MS",Font.BOLD,24));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Expression = new javax.swing.JTextArea();
        CalculateBodmas = new javax.swing.JButton();
        GenerateQuadraples = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setText("Enter Expression:- ");

        Expression.setColumns(20);
        Expression.setRows(5);
        jScrollPane2.setViewportView(Expression);

        CalculateBodmas.setBackground(new java.awt.Color(255, 255, 255));
        CalculateBodmas.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        CalculateBodmas.setText("Calculate Bodmas");
        CalculateBodmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculateBodmasActionPerformed(evt);
            }
        });

        GenerateQuadraples.setBackground(new java.awt.Color(255, 255, 255));
        GenerateQuadraples.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        GenerateQuadraples.setText("Generate Quadraples");
        GenerateQuadraples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateQuadraplesActionPerformed(evt);
            }
        });

        Refresh.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Documents\\NetBeansProjects\\IntermediateCodeGeneration\\src\\intermediatecodegeneration\\resources\\refresh.JPG")); // NOI18N
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CalculateBodmas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GenerateQuadraples, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Refresh)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CalculateBodmas)
                    .addComponent(GenerateQuadraples))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GenerateQuadraplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateQuadraplesActionPerformed
        ArrayList<StringBuffer> Quadraples=IntermediateCodeGeneration.Quadraples;
        String[][] Data = new String[Quadraples.size()][4];
        String Columns[] ={"OP","ARG1","ARG2","RESULT"};
        
        for(int i=0;i<Quadraples.size();i++){
            String row = String.valueOf(Quadraples.get(i));
            String[] split = row.split(",");
            Data[i][0]=new String("        "+split[0]);
            Data[i][1]=new String("        "+split[1]);
            Data[i][2]=new String("        "+split[2]);
            Data[i][3]=new String("        "+split[3]);
        }
        
        JFrame f= new JFrame("Quadruples");
        JTable jt = new JTable(Data,Columns);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        jt.setDefaultRenderer(String.class, centerRenderer);
        jt.setFont(new Font("Trebuchet MS",Font.BOLD,24));
        jt.setRowHeight(jt.getRowHeight()+9);
        
        jt.setBounds(30, 40, 650, 400);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(650, 400);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_GenerateQuadraplesActionPerformed

    private void CalculateBodmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculateBodmasActionPerformed
        expression=new StringBuffer(Expression.getText());
        if(expression.indexOf("(")==-1 && expression.indexOf(")")==-1){
           StringBuffer sb = expression;
           expression=new StringBuffer("");
           expression=expression.append("(").append(sb).append(")");
       }
       StringBuffer results=new StringBuffer("Result is ");
       results=results.append(IntermediateCodeGeneration.generateIntermidiateCode(expression)); 
       Expression.setText(String.valueOf(results));
    }//GEN-LAST:event_CalculateBodmasActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        expression=new StringBuffer(" ");
        Expression.setText("");
    }//GEN-LAST:event_RefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CalculateBodmas;
    private javax.swing.JTextArea Expression;
    private javax.swing.JButton GenerateQuadraples;
    private javax.swing.JButton Refresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
