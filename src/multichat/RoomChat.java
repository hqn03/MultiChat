/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multichat;

import javax.sound.sampled.Port;

/**
 *
 * @author Nhat
 */
public class RoomChat {
    private int id;
    private String address;
    private int port ;
    private String users;

    public RoomChat() {
    }

    public RoomChat(int id, String address, int port, String users) {
        this.id = id;
        this.address = address;
        this.port = port;
        this.users = users;
    }

    public String getUsers() {
        return users;
    }
    

    public int getPort() {
        return port;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
    
    
    
}
