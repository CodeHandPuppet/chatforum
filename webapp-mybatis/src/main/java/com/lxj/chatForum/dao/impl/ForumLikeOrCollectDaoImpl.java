package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.ForumLikeOrCollectDao;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumLikeOrCollectDaoImpl implements ForumLikeOrCollectDao {
    @Override
    public int deleteCollect(String uid, String forumId) {
        Connection conn=null;
        int count=0;
        PreparedStatement ps=null;
        try {
            conn= DButils.getConnection();
            String sql="delete from chatforum.forum_collect where user_id=? and forum_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,forumId);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int insertCollect(String uid, String forumId) {
        Connection conn=null;
        int count=0;
        PreparedStatement ps=null;
        try {
            conn= DButils.getConnection();
            String sql="insert into chatforum.forum_collect(user_id,forum_id) values (?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,forumId);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int deleteLike(String uid, String forumId) {
        Connection conn=null;
        int count=0;
        PreparedStatement ps=null;
        try {
            conn= DButils.getConnection();
            String sql="delete from chatforum.forum_like where user_id=? and forum_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,forumId);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int insertLike(String uid, String forumId) {
        Connection conn=null;
        int count=0;
        PreparedStatement ps=null;
        try {
            conn= DButils.getConnection();
            String sql="insert into chatforum.forum_like(user_id,forum_id) values (?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,forumId);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public List<Integer> getAllLikeForumId(String uid) {
        Connection conn=null;
        List<Integer> list=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="select forum_id from chatforum.forum_like where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while (rs.next()){
                list.add(Integer.parseInt(rs.getString("forum_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public List<Integer> getAllCollectForumId(String uid) {
        Connection conn=null;
        List<Integer> list=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="select forum_id from chatforum.forum_collect where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while (rs.next()){
                list.add(Integer.parseInt(rs.getString("forum_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }
}
