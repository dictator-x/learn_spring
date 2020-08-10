package com.myspring.basic.config;

import com.myspring.util.RsaUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Configuration
@ConfigurationProperties("rsa.key")
public class RsaKeyProperty {
    private String pubKeyFile;
    private PublicKey publicKey;

    @PostConstruct
    public void createRsaKey() throws Exception {
        publicKey = RsaUtil.getPublicKey(pubKeyFile);
    }
}
