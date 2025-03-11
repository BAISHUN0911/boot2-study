package com.baishun.mystudy.demotest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * @description: JWT公钥、私钥生成并存储到数据库
 * @Author BAISHUN
 * @Date 2024/8/11 17:26
 */
@RestController
@Slf4j
@RequestMapping("/keyGenerator")
public class KeyController {

    @PostMapping("/generate")
    public String generate() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            log.info("Public Key: {}", Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            log.info("Private Key: {}", Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            return "success";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
