package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.NotificationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet({"/notification/getAdminNot","/notification/getFriendNot","/notification/delFriendNot"
// ,"/notification/addFriendNot","/notification/removeAdminNot","/notification/addAdminNot","/notification/editAdminNot"})
@Controller
@ResponseBody
@RequestMapping("/notification")
public class NotificationController {
    
    
    @Resource
    NotificationService notificationServiceImpl;
    
    
    @GetMapping("/getAdminNot")
    public ResponseResult getAdminNot() {
        List<NotificationPojo> getAllAdminNotifications = notificationServiceImpl.toGetAllAdminNotifications();
        return ResponseResult.success(getAllAdminNotifications);
    }

    @PostMapping("/getFriendNot")
    public ResponseResult getFriendNot(@RequestBody HashMap<String,String> map) {

        List<UserPojo> getFriendNotifications = notificationServiceImpl.toGetFriendNotifications(map.get("uid"));

        return ResponseResult.success(getFriendNotifications);

    }

    @PostMapping("/delFriendNot")
    public ResponseResult delFriendNot(@RequestBody NotificationPojo notification) {
       notificationServiceImpl.toDeleteFriendNotifications(notification);

       return ResponseResult.success("ok");
    }

    @PostMapping("/addFriendNot")
    public ResponseResult addFriendNot(@RequestBody NotificationPojo notification) {

        notificationServiceImpl.toAddFriendNotifications(notification);
        return ResponseResult.success("ok");
    }

    @PostMapping("/removeAdminNot")
    public ResponseResult removeAdminNot(@RequestBody Map<String,String> map) {
       notificationServiceImpl.toRemoveAdminNotifications(map.get("id"));
        return ResponseResult.success("ok");
    }

    @PostMapping("/addAdminNot")
    public ResponseResult addAdminNot(@RequestBody NotificationPojo notification) {
        notificationServiceImpl.toAddAdminNotifications(notification);
        return ResponseResult.success("ok");
    }

    @PostMapping("/editAdminNot")
    public ResponseResult editAdminNot(@RequestBody NotificationPojo notification) {
        notificationServiceImpl.toEditAdminNotifications(notification);
        return ResponseResult.success("ok");
    }

}