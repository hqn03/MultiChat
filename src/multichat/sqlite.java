/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package multichat;

import java.lang.reflect.Parameter;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class sqlite {
    private Connection connect(){
//        String url = "jdbc:sqlite:C:\\Users\\Nhat\\Documents\\NetBeansProjects\\MultiChat\\src\\multichat\\data.db";
        //String kết nối database
        String url = "jdbc:sqlite:.\\src\\multichat\\data.db";
        Connection conn = null;
        try {
            //Lấy connection từ chuỗi kết nối
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    //tạo 1 room mới
    public void insert(String address,int port,String users){
        String sql = "INSERT INTO Rooms(address,port,users) VALUES(?,?,?)";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, address);
            pstmt.setInt(2, port);
            pstmt.setString(3, users);
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
            System.out.print(e.getMessage()+"1");
        }
    }
    //lấy list phòng
    public List<RoomChat> selectAll(){
        try {
            List<RoomChat> list = new ArrayList<RoomChat>();
            String sql = "SELECT id, address, port, users FROM Rooms";
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                list.add(new RoomChat(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
            }
            stmt.close();
            rs.close();
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"2");
            return null;
        }
    }
    //cập nhật column user
    public void updateUsers(String address,int port,String users) throws SQLException{
        String sql = "UPDATE rooms SET users=? WHERE address=? and port=?";
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, users);
            pstmt.setString(2, address);
            pstmt.setInt(3, port);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
    }
    
    //get chuỗi users
    public String getUsers(String address, int port) throws SQLException{
        String sql = "SELECT id, address, port, users FROM Rooms WHERE address=? and port=?";
        Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, address);
        pstmt.setInt(2, port);
        ResultSet rs = pstmt.executeQuery();
        String result = rs.getString(4);
        pstmt.close();
        conn.close();
        return result;
//        rs.getString("users");
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        sqlite app = new sqlite();        
    }
    
}
