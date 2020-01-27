
    package testclient;


    import java.io.*;
    import java.net.*;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;

    import java.io.IOException;
    import java.net.Socket;
    import java.util.ArrayList;


    public class Client extends JFrame {
       private JTextField usertext;
       private JTextArea chatwindow;
       ObjectInputStream input;
       ObjectOutputStream output;
       private String message = "";
       private String serverIP;
       private Socket connection;
       public String name;
       public int t=0;
        ArrayList<String> studentList = new ArrayList();
       public Client(String Host){

        super("Client ");

        serverIP = Host;
        usertext = new JTextField();
        usertext.setEditable(true);
        usertext.addActionListener(
         new ActionListener(){
         public void actionPerformed(ActionEvent e){
         sendClientMessage(e.getActionCommand());
          usertext.setText(" ");
                    }
                }

        );
        add(usertext,BorderLayout.NORTH);
        chatwindow = new JTextArea();
        add(new JScrollPane(chatwindow),BorderLayout.CENTER);
        setSize(400,200);
  
        chatwindow.setEditable(false);
       }
       public void startRunning (){
           try{
               connectToServer();
               setupStreams();
               whileChatting();
           }
       catch(EOFException ex){
           showMessage("Connection terminated by Clent\n");
       }catch(IOException io){
           System.out.println(io);
       }
       finally{
        CloseConnections();
    }
    }
           private void connectToServer() throws IOException{
          showMessage("Connecting..\n");
          connection = new Socket("localhost",6789);
          //System.out.println(InetAddress.getByName(serverIP));
          showMessage("Now Connected to "+"Oishee");
    }
        
           void setupStreams()throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now setup\n");
      
    }
           //Chatting with server
           private void whileChatting() throws IOException{
               ableToType(true);
               do{
                  try{
                      message = (String) input.readObject();

                      if(message.equals("You're sending a File\n"))
                      {

                      showMessage("You Have Received a File\n");
                      receiveFile();
                      }
                      else
                          showMessage("\n"+message);
                  }catch(ClassNotFoundException cnf){
                      showMessage("Error!Object type unknown");
                  } 
               }while(!message.equals("Server : ABORT"));
           }
           private void CloseConnections() {
         showMessage("\nClosing out streams and socket\n");
         ableToType(false);
         try{
             input.close();
             output.close();
             connection.close();
         }
         catch(IOException io){
             io.printStackTrace();
         }
    }

           private void sendClientMessage(String message){
        try{
            output.writeObject(name+": "+message);
            output.flush();
            showMessage("\n "+name+": "+message);

        }catch(IOException io){
            chatwindow.append("Error! Message not sent");
        }
    }


           private void showMessage (final String l){
        SwingUtilities.invokeLater(
                    new Runnable(){
                    public void run(){
                        chatwindow.append(l);
                    }
                    }     
        );
    }

           private void ableToType(final boolean os){
        SwingUtilities.invokeLater(
                    new Runnable(){
                    public void run(){
                        usertext.setEditable(os);
                      }
                    }     
        );
    }
           public void receiveFile()throws IOException{
        String FILE_TO_RECEIVED = "C:/temp/downloaded12384.jpg";
        int bytesRead;
        int current = 0;
        FileOutputStream fos=null;
        BufferedOutputStream bos=null;
        int FILE_SIZE = 6022386;
        try {

          // receive file
          byte [] mybytearray  = new byte [FILE_SIZE];
          InputStream is = connection.getInputStream();
          fos = new FileOutputStream(FILE_TO_RECEIVED);
          bos = new BufferedOutputStream(fos);
          bytesRead = is.read(mybytearray,0,mybytearray.length);
          current = bytesRead;

          bos.write(mybytearray, 0 , current);
          bos.flush();
          showMessage("\nFile Location: " + FILE_TO_RECEIVED
              + " downloaded (" + current + " bytes read)");
        }catch(IOException io){
            io.printStackTrace();
        }
         finally {
          if (fos != null) fos.close();
          if (bos != null) bos.close();
       
        }
       
      } 

    }
