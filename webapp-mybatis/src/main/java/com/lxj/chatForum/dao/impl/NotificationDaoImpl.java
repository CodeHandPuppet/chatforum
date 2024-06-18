package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.NotificationDao;
import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {
    @Override
    public List<NotificationPojo> getAdminNotifications() {
        List<NotificationPojo> list=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="select  * from chatforum.notification where type='Admin'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            list=new ArrayList<NotificationPojo>();
            while(rs!=null && rs.next()) {
                int id=rs.getInt("id");
                int sender_id=rs.getInt("sender_id");
                int receiver_id=rs.getInt("receiver_id");
                String type=rs.getString("type");
                String content=rs.getString("content");
                String create_time=rs.getString("create_time");
                String update_time=rs.getString("update_time");
                boolean isLook=rs.getInt("isLook") ==1 ?true:false;
                list.add(new NotificationPojo(id, type, content, sender_id, receiver_id, create_time, update_time,isLook));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public List<UserPojo> getFriendsUserInfo(String uid) {
        List<UserPojo> list=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT * FROM chatforum.user WHERE id IN (SELECT sender_id FROM chatforum.notification WHERE type = 'AddFriend' AND receiver_id = ?);";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            rs=ps.executeQuery();
            list=new ArrayList();
            while( rs.next()) {
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String nickname = rs.getString("nickname");
                String avatar = rs.getString("avatar");
                String signature = rs.getString("signature");
                String type = rs.getString("type");
                Integer level = rs.getInt("level");
                Integer likes = rs.getInt("likes");
                Integer collect = rs.getInt("collect");
                Integer forum_number = rs.getInt("forum_number");
                String homeLink ="/userHome?id="+id;
                String address = rs.getString("address");
                UserPojo userPojo=UserPojo.createUserInfo(id,username,nickname,avatar,signature,type,level, likes,collect,forum_number,homeLink,address);
                list.add(userPojo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int delete(String receiverId, String senderId) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="delete  from chatforum.notification where receiver_id=? and sender_id=? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1, receiverId);
            ps.setString(2, senderId);
            Integer temp=ps.executeUpdate();
            if(temp!=null){
                count=temp;
            }
        } catch (SQLException e) {
            System.out.println("notification 删除发送错误");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int addFriendNot(String receiverId, String senderId) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into chatforum.notification(receiver_id,sender_id,type)  values(?,?,'AddFriend')";
            ps=conn.prepareStatement(sql);
            ps.setString(1, receiverId);
            ps.setString(2, senderId);
            Integer temp=ps.executeUpdate();
            if(temp!=null){
                count=temp;
            }
        } catch (SQLException e) {
            System.out.println("notification 删除发送错误");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int deleteById(String id) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="delete  from chatforum.notification where id=? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1, id);
            Integer temp=ps.executeUpdate();
            if(temp!=null){
                count=temp;
            }
        } catch (SQLException e) {
            System.out.println("notification 删除发送错误");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int addAdminNot(NotificationPojo notification) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into chatforum.notification(sender_id,content,isLook,type)  values(?,?,?,'Admin')";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,notification.getSender_id() );
            ps.setString(2, notification.getContent());
            ps.setInt(3,notification.isLook() ? 1 : 0);
            Integer temp=ps.executeUpdate();
            if(temp!=null){
                count=temp;
            }
        } catch (SQLException e) {
            System.out.println("notification 删除发送错误");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int updateAdminNot(NotificationPojo notification) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="update chatforum.notification set content=? ,isLook=? where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, notification.getContent());
            ps.setInt(2,notification.isLook() ? 1 : 0);
            ps.setInt(3,notification.getId());
            Integer temp=ps.executeUpdate();
            if(temp!=null){
                count=temp;
            }
        } catch (SQLException e) {
            System.out.println("notification 删除发送错误");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

}
