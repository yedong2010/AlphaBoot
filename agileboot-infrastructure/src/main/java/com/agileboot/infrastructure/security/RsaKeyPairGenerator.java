package com.agileboot.infrastructure.security;

import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.asymmetric.RSA;

/**
 * Rsa key生成
 * @author valarchie
 */
public class RsaKeyPairGenerator {

  public static void main(String[] args) {
        RSA rsa = SecureUtil.rsa();

        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        String publicKeyBase64 = rsa.getPublicKeyBase64();

        System.out.println(privateKeyBase64);
        System.out.println(publicKeyBase64);
    }


}
