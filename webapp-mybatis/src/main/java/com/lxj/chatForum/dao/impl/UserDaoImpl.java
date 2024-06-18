package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.UserDao;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public String getTokenByUsernameAndPassword(String username, String password) {
        Connection conn=null;
        String token=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=DButils.getConnection();
            String sql="SELECT token FROM user WHERE username = ? AND password = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){

                token=rs.getString("token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return token;
    }
    @Override
    public UserPojo getUserInfoByToken(String token) {
        Connection conn=null;
        UserPojo userPojo=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=DButils.getConnection();
            String sql="SELECT * FROM user WHERE token=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,token);
            rs=ps.executeQuery();
            if(rs.next()){
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
                userPojo=UserPojo.createUserInfo(id,username,nickname,avatar,signature,type,level, likes,collect,forum_number,homeLink,address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }
        return userPojo;
    }
    @Override
    public UserPojo getInfoById(String uid) {
        Connection conn=null;
        UserPojo userPojo=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=DButils.getConnection();
            String sql="SELECT * FROM user WHERE id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            rs=ps.executeQuery();
            if(rs.next()){
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
                userPojo=UserPojo.createUserInfo(id,username,nickname,avatar,signature,type,level, likes,collect,forum_number,homeLink,address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }
        return userPojo;
    }

    @Override
    public String getIdBytoken(String token) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String id="";
        try {
            conn=DButils.getConnection();
            String sql="SELECT id FROM user WHERE token=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,token);
            rs=ps.executeQuery();
            if(rs.next()){
                id=rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }
        return id;
    }

    @Override
    public List<UserPojo> getUsers() {
        List<UserPojo> list=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=DButils.getConnection();
            String sql="select  * from user";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while(rs.next()){
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
                String password=rs.getString("password");
                UserPojo user=UserPojo.createUserInfo(id,username,nickname,avatar,signature,type,level, likes,collect,forum_number,homeLink,address);
                user.setPassword(password);

                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int update(UserPojo user) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="update user SET username=?, nickname=?, password=?, signature=?, address=?, avatar=?,type=? where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getNickname());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getSignature());
            ps.setString(5,user.getAddress());
            ps.setString(6, user.getAvatar());
            ps.setString(7,user.getType());
            ps.setInt(8,user.getId());
            Integer p=ps.executeUpdate();
            if (p!=null){
                count=p;
            }
        } catch (SQLException e) {
            System.out.println("更新");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int insert(UserPojo user) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into user (username, nickname, password, signature, address, avatar,type) values (?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getNickname());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getSignature());
            ps.setString(5,user.getAddress());
            ps.setString(6, user.getAvatar());
            ps.setString(7,"user");
            Integer p=ps.executeUpdate();
            if (p!=null){
                count=p;
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }
}
