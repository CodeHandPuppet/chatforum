package com.lxj.chatForum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ForumLikeOrCollectService {

    void allTypeMethods(Map<String,String> jsonMap, String type);

    HashMap<String, List<Integer>> allArrTypeMethods(Map<String,String> jsonMap, String collectArr);
}
