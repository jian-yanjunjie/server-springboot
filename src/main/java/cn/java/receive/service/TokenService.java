package cn.java.receive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    @Autowired
    private FileOperater fileOperater;

    public String getToken() {

        return fileOperater.getTokenStr();
    }

    public boolean wriToken(String t) {
        return fileOperater.updateTokenStr(t);
    }
}
