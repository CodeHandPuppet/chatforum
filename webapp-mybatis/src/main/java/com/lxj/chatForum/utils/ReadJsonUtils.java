package com.lxj.chatForum.utils;

import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReadJsonUtils {
    public static String readJson(HttpServletRequest request)  {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

        } catch (IOException e) {
           e.printStackTrace();
        }
        String line=null;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
               e.printStackTrace();
            }
            requestBody.append(line);
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonBody = requestBody.toString();
        System.out.println(jsonBody);
        return  jsonBody;
    }
}
