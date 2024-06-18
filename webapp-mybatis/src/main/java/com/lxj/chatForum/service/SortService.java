package com.lxj.chatForum.service;

public interface SortService {
    String getAll();

    String toEditSort(String json);

    String toAddSort(String json);

    String toDeleteSort(String json);
}
