package com.myspring.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

class RsaUtilTest {

    private String privateFilePath="./id_key_rsa";
    private String publicFilePath="./id_key_rsa.pub";

    @Test
    public void generateKey() throws IOException, NoSuchAlgorithmException {
        RsaUtil.generateKey(publicFilePath, privateFilePath, "salt", 2048);
    }

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtil.getPublicKey(publicFilePath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtil.getPrivateKey(privateFilePath));
    }
}