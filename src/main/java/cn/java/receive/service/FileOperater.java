package cn.java.receive.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class FileOperater {
    Logger logger = LoggerFactory.getLogger(FileOperater.class);

    @Value("${token.path}")
    private String tokenPath;
    private static String Token = "";

    @PostConstruct
    private void getfileinfo() {

        try {
            FileSystemResource resource = new FileSystemResource(tokenPath);
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
            String str = null;
            while ((str = br.readLine()) != null) {
                Token += str;
            }
            br.close();
        } catch (IOException e) {
            logger.warn("token 读操作失败！");
        }
    }

    private Boolean writefileinfo(String t) {

        FileSystemResource resource = new FileSystemResource(tokenPath);
        try {
            FileWriter fileWriter = (new FileWriter(resource.getFile()));
            fileWriter.write(t);
            fileWriter.close();

        } catch (IOException e) {
            logger.warn("token 写操作失败！");
            return false;
        }
        return true;
    }

    public String getTokenStr() {
        Lock readLock = new ReentrantReadWriteLock().readLock();
        readLock.lock();
        logger.info(Token);
        readLock.unlock();
        return Token;
    }

    public Boolean updateTokenStr(String t) {
        Lock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        if (writefileinfo(t)) {
            Token = t;
            try {
                Thread.sleep(10000);
            }catch (Exception e){

            }
            writeLock.unlock();

            return true;
        }
        logger.warn("token 更新失败");
        return false;
    }
}


