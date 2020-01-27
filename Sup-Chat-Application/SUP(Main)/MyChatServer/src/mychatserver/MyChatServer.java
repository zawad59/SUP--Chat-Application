/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver;

import java.io.IOException;

/**
 *
 * @author bintu
 */
public class MyChatServer {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        ServerFrame obj2 = new ServerFrame();
        loginFrame obj1 = new loginFrame(obj2);
        obj1.setVisible(true);
        obj2.startRunning();
    }
    
}
