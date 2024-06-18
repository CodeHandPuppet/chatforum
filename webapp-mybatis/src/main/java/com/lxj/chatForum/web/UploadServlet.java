package com.lxj.chatForum.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet({"/upload/image"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Part part=request.getPart("image");

        String realPath = request.getServletContext().getRealPath("");

//        InputStream inputStream = part.getInputStream();
//        OutputStream outputStream=new FileOutputStream(realPath+"x.jpg");
//        byte[] bt=new byte[1024 * 1024 * 5];
//        int len=0;
//        while((len=inputStream.read(bt))!=-1){
//            outputStream.write(bt,0,len);
//        }
        response.getWriter().print(realPath);
    }
}
