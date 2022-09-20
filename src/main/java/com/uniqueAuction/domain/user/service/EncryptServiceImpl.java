package com.uniqueAuction.domain.user.service;

import com.uniqueAuction.exception.advice.user.UserException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptServiceImpl implements EncryptService {
    @Override
    public String encrypt(String data) {
        String result;
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(data.getBytes());
            byte[] byteData = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UserException("암호화 중 에러가 발생하였습니다", e);
        }
        return result;
    }
}
