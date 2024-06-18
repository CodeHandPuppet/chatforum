package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.MessageListDao;
import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageListDaoImpl implements MessageListDao {
    @Override
    public HashMap<String, Object> getFriendMessageList(String uid) {
        HashMap<String, Object>  map=null;

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT * FROM user WHERE id in(SELECT DISTINCT fid\n" +
                    "FROM (\n" +
                    "SELECT receiver_id AS fid\n" +
                    "FROM UserMessage\n" +
                    "WHERE uid = ?\n" +
                    "UNION\n" +
                    "SELECT uid AS fid\n" +
                    "FROM UserMessage\n" +
                    "WHERE receiver_id = ?\n" +
                    " ) as temp);";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,uid);
            List<UserPojo> list=new ArrayList<>();
            rs=ps.executeQuery();
            map=new HashMap<>();
            while (rs.next()){
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
                String homeLink = rs.getString("homeLink");
                String address = rs.getString("address");
                UserPojo user=UserPojo.createUserInfo(id,username,nickname,avatar,signature,type,level, likes,collect,forum_number,homeLink,address);
                list.add(user);
            }
            map.put("type","friend");
            map.put("friends",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getChatList(String uid) {
        HashMap<String, Object>  map=null;

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {

            conn= DButils.getConnection();

            String sql="SELECT u.*, f.appellation\n" +
                    "FROM user u\n" +
                    "         JOIN (\n" +
                    "    SELECT friend_id AS id, appellation\n" +
                    "    FROM friendslist\n" +
                    "    WHERE uid = ?\n" +
                    ") f ON u.id = f.id;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            List<HashMap<String,Object>> list=new ArrayList<>();
            rs=ps.executeQuery();

            while (rs.next()){
               HashMap<String,Object> tempMap=new HashMap<>();
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
                String homeLink = "/userHome?id="+id;
                String address = rs.getString("address");
                String appellation =rs.getString("appellation");
                UserPojo user=UserPojo.createUserInfo(id,username,nickname,avatar,signature,type,level, likes,collect,forum_number,homeLink,address);
                tempMap.put("appellation",appellation);
                tempMap.put("friendInfo",user);
                list.add(tempMap);
            }
            map=new HashMap<>();
            map.put("friends",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }
        return map;
    }

    @Override
    public List<MessagePojo> getOneMessageArr(String _uid, String _sendId) {
        List<MessagePojo> list=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=DButils.getConnection();
            String sql="select * from usermessage where (uid=? and receiver_id=?) or  (uid=? and receiver_id=?) ORDER BY send_time";
            ps=conn.prepareStatement(sql);
            ps.setString(1,_uid);
            ps.setString(2,_sendId);
            ps.setString(3,_sendId);
            ps.setString(4,_uid);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                int uid=rs.getInt("uid");
                int receiver_id=rs.getInt("receiver_id");
                String content=rs.getString("content");
                String send_time=rs.getString("send_time");
                list.add(new MessagePojo(id, uid, receiver_id, content, send_time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;
    }

    @Override
    public int insertMessage(MessagePojo message) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into chatforum.usermessage (uid,receiver_id,content) values(?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, message.getUid());
            ps.setInt(2,message.getReceiver_id());
            ps.setString(3,message.getContent());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int insertFriend(FriendsListPojo friends) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            conn.setAutoCommit(false);
            String sql1="insert into chatforum.friendslist (uid,friend_id) values(?,?)";
            ps=conn.prepareStatement(sql1);
            ps.setInt(1, friends.getUid());
            ps.setInt(2,friends.getFriend_id());
            ps.executeUpdate();
            String sql2="insert into chatforum.friendslist (uid,friend_id) values(?,?)";
            ps=conn.prepareStatement(sql2);
            ps.setInt(1,friends.getFriend_id());
            ps.setInt(2, friends.getUid());
            count+=ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int updateFriend(FriendsListPojo friends) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="update chatforum.friendslist set appellation=? where uid=? and friend_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(2, friends.getUid());
            ps.setInt(3,friends.getFriend_id());
            ps.setString(1,friends.getAppellation());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }
}
