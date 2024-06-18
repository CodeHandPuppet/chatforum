package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.ForumDao;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.pojo.SortPojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ForumDaoImpl implements ForumDao {
    @Override
    public List<ForumPojo> getAllForum() {
        List<ForumPojo> list=null;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT f.id, f.uid, f.title, f.content, f.intor, f.sortId, f.img, f.likes, f.collect, f.create_time, s.sortName, u.username, u.avatar, u.level, u.homeLink\n" +
                    "FROM forum f\n" +
                    "JOIN sort s ON f.sortId = s.id\n" +
                    "JOIN user u ON f.uid = u.id order by  f.create_time desc;";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            list=new LinkedList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String intor = rs.getString("intor");
                Integer sortId = rs.getInt("sortId");
                String img = rs.getString("img");
                int likes = rs.getInt("likes");
                int collect = rs.getInt("collect");
                String create_time = rs.getString("create_time");
                String sortName = rs.getString("sortName");
                String username=rs.getString("username");
                String avatar=rs.getString("avatar");
                Integer level=rs.getInt("level");
                String homeLink="/userHome?id="+uid;

                list.add(ForumPojo.createVisForum(id,uid,title,content,intor,sortId,img,likes,collect,create_time,new SortPojo(sortId,sortName), new UserPojo(username, avatar, level, homeLink)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;
    }

    @Override
    public List<ForumPojo> getForumBySortId(String _sortId) {
        List<ForumPojo> list=null;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT f.id, f.uid, f.title, f.content, f.intor, f.sortId, f.img, f.likes, f.collect, f.create_time, s.sortName, u.username, u.avatar, u.level, u.homeLink\n" +
                    "FROM forum f\n" +
                    "JOIN sort s ON f.sortId = s.id\n" +
                    "JOIN user u ON f.uid = u.id\n" +
                    "WHERE f.sortId = ? order by  f.create_time desc;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,_sortId);
            rs=ps.executeQuery();
            list=new LinkedList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String intor = rs.getString("intor");
                Integer sortId = rs.getInt("sortId");
                String img = rs.getString("img");
                int likes = rs.getInt("likes");
                int collect = rs.getInt("collect");
                String create_time = rs.getString("create_time");
                String sortName = rs.getString("sortName");
                String username=rs.getString("username");
                String avatar=rs.getString("avatar");
                Integer level=rs.getInt("level");
                String homeLink="/userHome?id="+uid;
                list.add(ForumPojo.createVisForum(id,uid,title,content,intor,sortId,img,likes,collect,create_time,new SortPojo(sortId,sortName), new UserPojo(username, avatar, level, homeLink)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;
    }

    @Override
    public List<ForumPojo> getAllForumByUid(String _uid) {
        List<ForumPojo> list=null;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT f.id, f.uid, f.title, f.content, f.intor, f.sortId, f.img, f.likes, f.collect, f.create_time, s.sortName, u.username, u.avatar, u.level, u.homeLink\n" +
                    "FROM forum f\n" +
                    "JOIN sort s ON f.sortId = s.id\n" +
                    "JOIN user u ON f.uid = u.id\n" +
                    "WHERE f.uid = ? order by  f.create_time desc;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,_uid);
            rs=ps.executeQuery();
            list=new LinkedList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String intor = rs.getString("intor");
                Integer sortId = rs.getInt("sortId");
                String img = rs.getString("img");
                int likes = rs.getInt("likes");
                int collect = rs.getInt("collect");
                String create_time = rs.getString("create_time");
                int sortIdFromResultSet = rs.getInt("sortId");
                String sortName = rs.getString("sortName");
                String username=rs.getString("username");
                String avatar=rs.getString("avatar");
                Integer level=rs.getInt("level");
                String homeLink="/userHome?id="+uid;

                list.add(ForumPojo.createVisForum(id,uid,title,content,intor,sortId,img,likes,collect,create_time,new SortPojo(sortId,sortName), new UserPojo(username, avatar, level, homeLink)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;
    }

    @Override
    public List<ForumPojo> getLikeForumByUid(String _uid) {

        List<ForumPojo> list=null;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT f.id, f.uid, f.title, f.content, f.intor, f.sortId, f.img, f.likes, f.collect, f.create_time,\n" +
                    "       s.sortName, u.username, u.avatar, u.level, u.homeLink\n" +
                    "FROM forum f\n" +
                    "         JOIN sort s ON f.sortId = s.id\n" +
                    "         JOIN user u ON f.uid = u.id\n" +
                    "WHERE f.id IN (SELECT forum_id FROM forum_like WHERE user_id = ?) order by  f.create_time desc;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,_uid);
            rs=ps.executeQuery();
            list=new LinkedList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String intor = rs.getString("intor");
                Integer sortId = rs.getInt("sortId");
                String img = rs.getString("img");
                int likes = rs.getInt("likes");
                int collect = rs.getInt("collect");
                String create_time = rs.getString("create_time");

                String sortName = rs.getString("sortName");
                String username=rs.getString("username");
                String avatar=rs.getString("avatar");
                Integer level=rs.getInt("level");
                String homeLink="/userHome?id="+uid;

                list.add(ForumPojo.createVisForum(id,uid,title,content,intor,sortId,img,likes,collect,create_time,new SortPojo(sortId,sortName), new UserPojo(username, avatar, level, homeLink)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;
    }

    @Override
    public List<ForumPojo> getCollectForum(String _uid) {
        List<ForumPojo> list=null;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT f.id, f.uid, f.title, f.content, f.intor, f.sortId, f.img, f.likes, f.collect, f.create_time,\n" +
                    "       s.sortName, u.username, u.avatar, u.level, u.homeLink\n" +
                    "FROM forum f\n" +
                    "         JOIN sort s ON f.sortId = s.id\n" +
                    "         JOIN user u ON f.uid = u.id\n" +
                    "WHERE f.id IN (SELECT forum_id FROM forum_collect WHERE user_id = ?) order by  f.create_time desc;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,_uid);
            rs=ps.executeQuery();
            list=new LinkedList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String intor = rs.getString("intor");
                Integer sortId = rs.getInt("sortId");
                String img = rs.getString("img");
                int likes = rs.getInt("likes");
                int collect = rs.getInt("collect");
                String create_time = rs.getString("create_time");
                int sortIdFromResultSet = rs.getInt("sortId");
                String sortName = rs.getString("sortName");
                String username=rs.getString("username");
                String avatar=rs.getString("avatar");
                Integer level=rs.getInt("level");
                String homeLink="/userHome?id="+uid;

                list.add(ForumPojo.createVisForum(id,uid,title,content,intor,sortId,img,likes,collect,create_time,new SortPojo(sortId,sortName), new UserPojo(username, avatar, level, homeLink)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return list;
    }

    @Override
    public ForumPojo getForumById(String forumId) {
        ForumPojo forumPojo=null;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="SELECT f.id, f.uid, f.title, f.content, f.intor, f.sortId, f.img, f.likes, f.collect, f.create_time,\n" +
                    "       s.sortName, u.username, u.avatar, u.level, u.homeLink\n" +
                    "FROM forum f\n" +
                    "         JOIN sort s ON f.sortId = s.id\n" +
                    "         JOIN user u ON f.uid = u.id\n" +
                    "WHERE f.id = ? order by  f.create_time desc";
            ps=conn.prepareStatement(sql);
            ps.setString(1,forumId);
            rs=ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String intor = rs.getString("intor");
                Integer sortId = rs.getInt("sortId");
                String img = rs.getString("img");
                int likes = rs.getInt("likes");
                int collect = rs.getInt("collect");
                String create_time = rs.getString("create_time");
                String sortName = rs.getString("sortName");

                String username=rs.getString("username");
                String avatar=rs.getString("avatar");
                Integer level=rs.getInt("level");
                String homeLink="/userHome?id="+uid;

                forumPojo=ForumPojo.createVisForum(id,uid,title,content,intor,sortId,img,likes,collect,create_time,new SortPojo(sortId,sortName), new UserPojo(username, avatar, level, homeLink));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButils.close(conn,ps,rs);
        }

        return forumPojo;
    }

    @Override
    public int updateForum(ForumPojo forum) {
        int count =0;
        Connection conn=null;
        PreparedStatement  ps=null;
        try {
            conn=DButils.getConnection();
            String sql="update forum set uid=?,title=?,content=?,intor=?,img=?,sortId=? where id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,forum.getUid());
            ps.setString(2,forum.getTitle());
            ps.setString(3,forum.getContent());
            ps.setString(4,forum.getIntor());
            ps.setString(5,forum.getImg());
            ps.setInt(6,forum.getSortId());
            ps.setInt(7,forum.getId());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int addForum(ForumPojo forum) {
        int count =0;
        Connection conn=null;
        PreparedStatement  ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into forum(uid,title,content,intor,img,sortId) values(?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,forum.getUid());
            ps.setString(2,forum.getTitle());
            ps.setString(3,forum.getContent());
            ps.setString(4,forum.getIntor());
            ps.setString(5,forum.getImg());
            ps.setInt(6,forum.getSortId());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int deleteForumById(String id) {
        int count =0;
        Connection conn=null;
        PreparedStatement  ps=null;
        ResultSet rs=null;
        try {
            conn=DButils.getConnection();
            String sql="delete from forum where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return count;
    }

}
