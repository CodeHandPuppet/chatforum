package com.lxj.chatForum.web;

import com.lxj.chatForum.service.SortService;
import com.lxj.chatForum.service.impl.SortServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/sort/getAllSorts","/sort/add","/sort/edit","/sort/delete"})
public class SortServlet extends HttpServlet {

    SortService sortService=new SortServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        if(servletPath.equals("/sort/getAllSorts")){
            doGetAll(request,response);
        }
        else if(servletPath.equals("/sort/add")){
            doAddSort(request,response);
        }
        else if(servletPath.equals("/sort/edit")){
            doEditSort(request,response);
        }
        else if(servletPath.equals("/sort/delete")){
            doDeleteSort(request,response);
        }
    }

    private void doDeleteSort(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=sortService.toDeleteSort(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doEditSort(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=sortService.toEditSort(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doAddSort(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=sortService.toAddSort(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @method get
     */
    private void doGetAll(HttpServletRequest request, HttpServletResponse response) {
        String returnData=sortService.getAll();
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
