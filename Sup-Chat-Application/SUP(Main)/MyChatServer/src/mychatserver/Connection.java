/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author bintu
 */
public class Connection {
    
    public   String name;
    public   Socket clientSocket;
    public   int id;
    ObjectInputStream input;
    ObjectOutputStream output;
    
}
