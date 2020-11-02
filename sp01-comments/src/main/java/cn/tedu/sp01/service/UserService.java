package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.User;

public interface UserService {
    User getUser(Integer userId);
    void addScore(Integer userId,Integer score);
}
