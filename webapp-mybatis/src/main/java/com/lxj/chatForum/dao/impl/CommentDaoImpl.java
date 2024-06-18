package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.CommentDao;
import com.lxj.chatForum.pojo.CommentPojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public List<CommentPojo> getCommentsByForumId(String forumId) {
        List<CommentPojo> list=null;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            conn = DButils.getConnection();
            String sql="SELECT\n" +
                    "    c.id AS id,\n" +
                    "    c.uid AS uid,\n" +
                    "    c.parent_id AS parentId,\n" +
                    "    c.content AS content,\n" +
                    "    c.likes AS likes,\n" +
                    "    c.content_img AS contentImg,\n" +
                    "    c.create_time AS createTime,\n" +
                    "    u.address AS address,\n" +
                    "    u.nickname AS username,\n" +
                    "    u.avatar AS avatar,\n" +
                    "    u.level AS level,\n" +
                    "    u.homeLink AS homeLink\n" +
                    "FROM\n" +
                    "    comment c\n" +
                    "        JOIN\n" +
                    "    user u ON c.uid = u.id\n" +
                    "WHERE\n" +
                    "                c.forum_id = ? order by createTime desc";
            ps=conn.prepareStatement(sql);
            ps.setString(1,forumId);
            list=new LinkedList<>();
            rs=ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String parentId = rs.getObject("parentId") != null ? rs.getString("parentId") : "null";
                String content = rs.getString("content");
                int likes = rs.getInt("likes");
                String contentImg = rs.getString("contentImg");
                String createTime = rs.getString("createTime");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                int level = rs.getInt("level");
                String homeLink ="/userHome?id="+uid;
                list.add(CommentPojo.createVisComment(id,uid,parentId,content,likes,contentImg,createTime,address,new UserPojo(username,avatar,level,homeLink)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;

    }

    @Override
    public int insertReturnId(CommentPojo comment) {
        int id=-1;
        Connection conn=null;
        int count=0;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();

            String sql="";
            if(comment.getParentId()==null){
                sql="insert into comment(uid,forum_id,content) values (?,?,?)";
                ps=conn.prepareStatement(sql);
            }
            else{
                sql="insert into comment(uid,forum_id,content,parent_id) values (?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(4,comment.getParentId());

            }
            ps.setInt(1,comment.getUid());
            ps.setInt(2,comment.getForumId());
            ps.setString(3,comment.getContent());
            count=ps.executeUpdate();
            if(count==0){
                id=-1;
            }
            else{
                sql="SELECT MAX(id) as id FROM comment;";
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                if(rs.next()){
                    id=rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return id;
    }

    @Override
    public int deleteById(String id,String parentId) {
        Connection conn=null;
        int count=0;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="delete from comment where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            count=ps.executeUpdate();
            if(count>0 && parentId == null){
                sql="delete from comment where parent_id = ?";
                ps=conn.prepareStatement(sql);
                ps.setString(1,id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return count;
    }
}
