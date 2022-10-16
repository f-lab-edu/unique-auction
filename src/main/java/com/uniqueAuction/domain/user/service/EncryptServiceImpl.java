package com.uniqueauction.domain.user.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.uniqueauction.exception.advice.CommonException;

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
			throw new CommonException(ENCRYPT_ERROR);
		}
		return result;
	}
}
