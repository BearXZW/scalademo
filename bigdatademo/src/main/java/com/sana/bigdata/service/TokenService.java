package com.sana.bigdata.service;


import com.sana.bigdata.model.User;

public interface TokenService {

    String getToken(User user);
}
