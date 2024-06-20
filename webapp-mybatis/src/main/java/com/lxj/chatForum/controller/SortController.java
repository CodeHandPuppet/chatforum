package com.lxj.chatForum.controller;

import com.lxj.chatForum.service.SortService;
import com.lxj.chatForum.service.impl.SortServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet({"/sort/getAllSorts","/sort/add","/sort/edit","/sort/delete"})
@Controller
@RequestMapping("/sort")
public class SortController {

    @PostMapping("/getAllSorts")
    public void getAllSorts() {
        // Implementation goes here
    }

    @PostMapping("/add")
    public void add() {
        // Implementation goes here
    }

    @PostMapping("/edit")
    public void edit() {
        // Implementation goes here
    }

    @PostMapping("/delete")
    public void delete() {
        // Implementation goes here
    }

}