package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.CommentLikeDao;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentLikeDaoImpl implements CommentLikeDao{
    @Override
    public List<Integer> getCommentIdByForumIdAndUid( String uid) {
        List<Integer> list=null;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps=null;

        try {
            conn= DButils.getConnection();
            String sql="select comment_id as commentId from comment_like where uid=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while(rs.next()){
                list.add(rs.getInt("commentId"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int deleteByUidAndCommentId(String uid, String commentId) {
        Connection conn=null;
        int count=0;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="delete from comment_like where uid=? and comment_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,commentId);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }
    @Override
    public int insertByUidAndCommentId(String uid, String commentId) {
        Connection conn=null;
        int count=0;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into chatforum.comment_like(uid,comment_id) values (?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.setString(2,commentId);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }
}
