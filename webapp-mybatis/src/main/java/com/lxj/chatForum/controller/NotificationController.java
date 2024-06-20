package com.lxj.chatForum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet({"/notification/getAdminNot","/notification/getFriendNot","/notification/delFriendNot"
// ,"/notification/addFriendNot","/notification/removeAdminNot","/notification/addAdminNot","/notification/editAdminNot"})
@Controller
@RequestMapping("/notification")
public class NotificationController {

    @PostMapping("/getAdminNot")
    public void getAdminNot() {
        // Implementation goes here
    }

    @PostMapping("/getFriendNot")
    public void getFriendNot() {
        // Implementation goes here
    }

    @PostMapping("/delFriendNot")
    public void delFriendNot() {
        // Implementation goes here
    }

    @PostMapping("/addFriendNot")
    public void addFriendNot() {
        // Implementation goes here
    }

    @PostMapping("/removeAdminNot")
    public void removeAdminNot() {
        // Implementation goes here
    }

    @PostMapping("/addAdminNot")
    public void addAdminNot() {
        // Implementation goes here
    }

    @PostMapping("/editAdminNot")
    public void editAdminNot() {
        // Implementation goes here
    }

}