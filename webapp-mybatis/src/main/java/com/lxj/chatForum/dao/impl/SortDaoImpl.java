package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.SortDao;
import com.lxj.chatForum.pojo.SortPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortDaoImpl implements SortDao {
    @Override
    public List<SortPojo> getAllSort() {
        Connection conn=null;
        ResultSet rs=null;
        List<SortPojo> list=null;
        PreparedStatement ps=null;
        try {
            conn= DButils.getConnection();
            String sql="select * from sort";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                Boolean isLook = rs.getInt("isLook")==0 ? false:true;
                String sortName = rs.getString("sortName");
                String intor = rs.getString("intor");
                String create_time = rs.getString("create_time");
                String update_time=rs.getString("update_time");
                list.add(new SortPojo(id, sortName, intor, isLook, create_time, update_time, uid));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int insert(SortPojo sort) {
       int count=0;
       Connection conn=null;
       PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into sort (sortName,intor,uid,isLook) values(?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, sort.getSortName());
            ps.setString(2,sort.getIntor());
            ps.setInt(3,sort.getUid());
            ps.setBoolean(4,sort.isLook());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int update(SortPojo sort) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="update  sort set sortName=?,intor=?,uid=?,isLook=? where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, sort.getSortName());
            ps.setString(2,sort.getIntor());
            ps.setInt(3,sort.getUid());
            ps.setBoolean(4,sort.isLook());
            ps.setInt(5,sort.getId());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int deleteById(String id) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="delete from chatforum.sort where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            Integer p=ps.executeUpdate();
            if (p!=null){
                count=p;
            }
        } catch (SQLException e) {
            System.out.println("删除失败");
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }
}
