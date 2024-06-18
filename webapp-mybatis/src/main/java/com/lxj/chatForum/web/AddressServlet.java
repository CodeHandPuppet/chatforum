package com.lxj.chatForum.web;

import com.lxj.chatForum.service.AddressService;
import com.lxj.chatForum.service.impl.AddressServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//@WebServlet({"/address/getAllAddress","/address/add","/address/edit"})
public class AddressServlet extends HttpServlet {


    AddressService addressService=new AddressServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if(servletPath.equals("/address/getAllAddress")){
            doGetAllAddress(request, response);
        }
        else if(servletPath.equals("/address/add")){
            doAddAddress(request, response);
        }
        else if(servletPath.equals("/address/edit")){
            doEditAddress(request, response);
        }

    }

    private void doAddAddress(HttpServletRequest request, HttpServletResponse response) {

        String json = ReadJsonUtils.readJson(request);
        String returnData=addressService.toAddAddress(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doEditAddress(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=addressService.toEditAddress(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doGetAllAddress(HttpServletRequest request, HttpServletResponse response) {

        String returnData=addressService.toGetAllAddresses();

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
