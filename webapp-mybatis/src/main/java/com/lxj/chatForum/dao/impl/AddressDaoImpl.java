package com.lxj.chatForum.dao.impl;

import com.lxj.chatForum.dao.AddressDao;
import com.lxj.chatForum.pojo.AddressPojo;
import com.lxj.chatForum.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    @Override
    public List<AddressPojo> getAddresses() {
        List<AddressPojo> list=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= DButils.getConnection();
            String sql="select * from address";
            ps= conn.prepareStatement(sql);
            rs=ps.executeQuery();
            list=new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String intor = rs.getString("intor");
                String addressImg = rs.getString("address_img");
                String content = rs.getString("content");
                String createTime = rs.getString("create_time");
                Boolean isLook=rs.getInt("isLook")==0 ? false :true;
                list.add(new AddressPojo(id, name, intor, addressImg, content, createTime,isLook));
            }
        } catch (SQLException e) {
            System.out.println("查找失败");
        }finally {
            DButils.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int insert(AddressPojo address) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="insert into address(name,intor,isLook) values(?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, address.getName());
            ps.setString(2,address.getIntor());
//            ps.setInt(3,sort.getUid());
            ps.setBoolean(3,address.isLook());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int update(AddressPojo address) {
        int count=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=DButils.getConnection();
            String sql="update address set name=?,intor=?,isLook=? where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, address.getName());
            ps.setString(2,address.getIntor());
//            ps.setInt(3,sort.getUid());
            ps.setInt(3,address.isLook() ? 1:0);
            ps.setInt(4,address.getId());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(conn,ps,null);
        }
        return count;
    }
}
