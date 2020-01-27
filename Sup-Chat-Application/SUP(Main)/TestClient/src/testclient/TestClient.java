
package testclient;

import javax.swing.JFrame;

public class TestClient {
    
  

    
    public static void main(String[] args) {
      
       Client obj1 = new Client("127.0.0.1");
   
        
       loginFrame obj = new loginFrame(obj1);
       
       obj.setVisible(true);
       obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       obj1.startRunning();
     
   
       
    }
    
}
