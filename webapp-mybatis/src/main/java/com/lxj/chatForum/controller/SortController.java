package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.SortPojo;
import com.lxj.chatForum.service.SortService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

//@WebServlet({"/sort/getAllSorts","/sort/add","/sort/edit","/sort/delete"})
@Controller
@ResponseBody
@RequestMapping("/sort")
public class SortController {
    
    @Resource
    SortService sortServiceImpl;
    
    @GetMapping("/getAllSorts")
    public ResponseResult getAllSorts() {
        List<SortPojo> all = sortServiceImpl.getAll();
        
        return ResponseResult.success(all);
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody SortPojo sortPojo) {
       sortServiceImpl.toAddSort(sortPojo);

       return ResponseResult.success("ok");
    }

    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody SortPojo sortPojo) {
        sortServiceImpl.toEditSort(sortPojo);
        return ResponseResult.success("ok");
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody HashMap<String,String> map) {
        sortServiceImpl.toDeleteSort(map.get("id"));
        return ResponseResult.success("ok");
    }

}