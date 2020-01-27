/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver;

/**
 *
 * @author bintu
 */
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;


public class ServerFrame extends javax.swing.JFrame {

   private ObjectInputStream input;
   private ObjectOutputStream output;
   private ServerSocket server;
   private Socket connection;
     String name;
     String name1=" ";
           ArrayList<String> list = new ArrayList<>();
           ArrayList<String>friends= new ArrayList<>();
           ArrayList<String>friends1= new ArrayList<>();

      DefaultListModel listModel,listModel2;
       int sm=0;
     
    public ServerFrame(){
          initComponents();
         listModel = new DefaultListModel();
         listModel2 = new DefaultListModel();
         chatwindow.setEditable(false);
         usertext.addActionListener(
     new ActionListener(){
     public void actionPerformed(ActionEvent e){
     sendMessage(e.getActionCommand());
                   usertext.setText(" ");
                }
            }
        );
    }
    
    public void startRunning(){
   
    try{
         server = new ServerSocket(6789,50);
    while(true){
        try{
            waitforConnection();
            setupStream();
            whileChatting();
    }catch(IOException | ClassNotFoundException eof){
       showMessage("\nServer connection done\n"); 
    }finally{
            closeConnections();
    }
    }
    }catch(IOException io){
    }
}
void WriteFile(ArrayList<String> list) throws IOException {
   
       try (FileWriter fw = new FileWriter("C:/Users/bintu/Documents/SUP/TestServer/src/mainclass/"+name1+".txt")) {
           for (int i = 0; i < list.size(); i++) {
               fw.write(list.get(i));
               
           }
       }
}
void readFile()
{
    try{

       
          FileReader inputFile = new FileReader("C:/Users/bintu/Documents/SUP/TestServer/src/mainclass/"+name1+".txt");

         
        try (BufferedReader bufferReader = new BufferedReader(inputFile)) {
            String line;
            
            
            while ((line = bufferReader.readLine()) != null)   {
                
                showMessage(line+"\n");
                list.add(line+"\n");
            }
        }
       }catch(Exception e){
          System.out.println("Error while reading file line by line:" + e.getMessage());                      
       }
}


private void waitforConnection() throws IOException{
       // throw new IOException();
        showMessage("\nWaiting for Connection...\n");
       
        connection = server.accept();
     
        
        InetAddress a = connection.getInetAddress();
        System.out.println(connection.getInetAddress());
        String s = a.getHostName();
      
        
       
    }

private void setupStream()throws IOException, ClassNotFoundException{
    output = new ObjectOutputStream(connection.getOutputStream());
    output.flush();
    input = new ObjectInputStream(connection.getInputStream());
    
    name1= (String) input.readObject();
    
    listModel.addElement(name1);
    onlinelist.setModel(listModel);
    chatwindow.setText(null);
    showMessage("Now Connected to "+name1);
    showMessage("\n Streams are now setup\n");
}

void sendFile()
{
    String FILE_TO_SEND = "C:/temp/pizza.jpg";
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    int FILE_SIZE = 6022386;
        showMessage(FILE_TO_SEND);
        try {
        
          File myFile = new File (FILE_TO_SEND);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = connection.getOutputStream();
          System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          showMessage("\nFile Has Been Sent\n");
        }
       catch (IOException ex) {
          System.out.println(ex.getMessage()+": An Inbound Connection Was Not Resolved");
          
        }
      }
 

private void whileChatting()throws IOException{
   String message="";
   
   readFile();
   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   Calendar cal = Calendar.getInstance();
   list.add("\t                             "+dateFormat.format(cal.getTime())+"\n");
   showMessage("\n                         "+dateFormat.format(cal.getTime())+"\n");
    abletoType(true);
    do{
        try{
            message = (String)input.readObject();
            showMessage("\n"+message);
             list.add(message+"\n");
             WriteFile(list);
        }catch(ClassNotFoundException cnf){
            showMessage("Error with input message");
        }
    }while(!message.equals("Client : BYE"));
}


private void closeConnections(){
    
    sendMessage("\nClosing out connection\n");
    abletoType(false);
    try{
        
        input.close();
        output.close();
        connection.close();
    }catch(IOException ex){
        System.out.println(ex);
    }
}

private void sendMessage(String message){
    try{
        output.writeObject(name+" : "+message);
        output.flush();
         list.add(name+": "+message+"\n");
         WriteFile(list);
        showMessage("\n"+name+" : "+message);
        
    }catch(IOException exc){
        chatwindow.append("USER HAS GONE OFFLINE\n");
        
           listModel.removeElement(name1);
           onlinelist.setModel(listModel);
    }
}
private void sendMessage2(String message){
    try{
        output.writeObject(message);
        output.flush();
        showMessage("\n"+name+" : "+message);
        
    }catch(IOException exc){
        chatwindow.append("Error! Message not sent");
    }
}

private void showMessage (final String s){
    SwingUtilities.invokeLater(
                new Runnable(){
                public void run(){
                    chatwindow.append(s);
                }
                }     
    );
}

private void abletoType(final boolean os){
    SwingUtilities.invokeLater(
                new Runnable(){
                public void run(){
                    usertext.setEditable(os);
                }
                }     
    );
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        image = new javax.swing.JButton();
        usertext = new javax.swing.JTextField();
        File = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatwindow = new javax.swing.JTextArea();
        send = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        onlinelist = new javax.swing.JList<>();
        angry = new javax.swing.JButton();
        cool = new javax.swing.JButton();
        sadcry = new javax.swing.JButton();
        sad = new javax.swing.JButton();
        cry = new javax.swing.JButton();
        laugh = new javax.swing.JButton();
        pi = new javax.swing.JButton();
        straight = new javax.swing.JButton();
        smile = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usertext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usertextActionPerformed(evt);
            }
        });

        File.setText("FILE");
        File.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileActionPerformed(evt);
            }
        });

        jLabel1.setText("ONLINE");

        chatwindow.setColumns(20);
        chatwindow.setRows(5);
        jScrollPane4.setViewportView(chatwindow);

        send.setText("SEND");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        onlinelist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onlinelistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(onlinelist);

        angry.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        angry.setText(":@");
        angry.setMargin(new java.awt.Insets(3, 3, 3, 3));
        angry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angryActionPerformed(evt);
            }
        });

        cool.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cool.setText("B|");
        cool.setMargin(new java.awt.Insets(3, 3, 3, 3));
        cool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coolActionPerformed(evt);
            }
        });

        sadcry.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sadcry.setText(":'(");
        sadcry.setMargin(new java.awt.Insets(3, 3, 3, 3));
        sadcry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sadcryActionPerformed(evt);
            }
        });

        sad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sad.setText(":(");
        sad.setMargin(new java.awt.Insets(3, 3, 3, 3));
        sad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sadActionPerformed(evt);
            }
        });

        cry.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cry.setText(":')");
        cry.setMargin(new java.awt.Insets(3, 3, 3, 3));
        cry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cryActionPerformed(evt);
            }
        });

        laugh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        laugh.setText(":D");
        laugh.setMargin(new java.awt.Insets(3, 3, 3, 3));
        laugh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laughActionPerformed(evt);
            }
        });

        pi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pi.setText(":p");
        pi.setMargin(new java.awt.Insets(3, 3, 3, 3));
        pi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piActionPerformed(evt);
            }
        });

        straight.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        straight.setText(":|");
        straight.setMargin(new java.awt.Insets(3, 3, 3, 3));
        straight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                straightActionPerformed(evt);
            }
        });

        smile.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        smile.setText(":)");
        smile.setMargin(new java.awt.Insets(3, 3, 3, 3));
        smile.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                smileMouseWheelMoved(evt);
            }
        });
        smile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(usertext, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(send)
                        .addGap(18, 18, 18)
                        .addComponent(File))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(angry, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cry, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sadcry, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cool, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(laugh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(straight, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(smile, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sad)
                            .addComponent(angry)
                            .addComponent(cry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cool)
                                    .addComponent(sadcry))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(smile))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(laugh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pi)
                                    .addComponent(straight)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(File, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usertext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
      //message);
      
      try{
        output.writeObject(name+": "+usertext.getText());
        output.flush();
         list.add(name+": "+usertext.getText()+"\n");
         WriteFile(list);
        showMessage("\n"+name+" : "+usertext.getText());
        usertext.setText(" ");
        
    }catch(IOException exc){
        chatwindow.append("Error! Message not sent");
    }
    }//GEN-LAST:event_sendActionPerformed

    private void FileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileActionPerformed
        // TODO add your handling code here:
        sendMessage2("You're sending a File\n");
        sendFile();
    }//GEN-LAST:event_FileActionPerformed

    private void imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imageActionPerformed

    private void laughActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laughActionPerformed
        sendMessage(":D");
    }//GEN-LAST:event_laughActionPerformed

    private void piActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piActionPerformed
        sendMessage(":P");
    }//GEN-LAST:event_piActionPerformed

    private void straightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_straightActionPerformed
        // TODO add your handling code here:\
        sendMessage(":|");
    }//GEN-LAST:event_straightActionPerformed

    private void usertextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usertextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usertextActionPerformed

    private void sadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sadActionPerformed
        sendMessage(":(");
    }//GEN-LAST:event_sadActionPerformed

    private void angryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angryActionPerformed
         sendMessage(":@");
    }//GEN-LAST:event_angryActionPerformed

    private void cryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cryActionPerformed
        // TODO add your handling code here:
        sendMessage(":')");
    }//GEN-LAST:event_cryActionPerformed

    private void sadcryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sadcryActionPerformed
        // TODO add your handling code here:
        sendMessage(":'(");
    }//GEN-LAST:event_sadcryActionPerformed

    private void coolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coolActionPerformed
        // TODO add your handling code here:
        sendMessage("B|");
    }//GEN-LAST:event_coolActionPerformed

    private void smileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smileActionPerformed
        // TODO add your handling code here:
        sendMessage(":)");
    }//GEN-LAST:event_smileActionPerformed

    private void smileMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_smileMouseWheelMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_smileMouseWheelMoved

    private void onlinelistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlinelistMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_onlinelistMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      

        /* Create and display the form */
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton File;
    private javax.swing.JButton angry;
    private javax.swing.JTextArea chatwindow;
    private javax.swing.JButton cool;
    private javax.swing.JButton cry;
    private javax.swing.JButton image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton laugh;
    private javax.swing.JList<String> onlinelist;
    private javax.swing.JButton pi;
    private javax.swing.JButton sad;
    private javax.swing.JButton sadcry;
    private javax.swing.JButton send;
    private javax.swing.JButton smile;
    private javax.swing.JButton straight;
    private javax.swing.JTextField usertext;
    // End of variables declaration//GEN-END:variables
}
