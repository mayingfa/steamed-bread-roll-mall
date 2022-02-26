package com.qiu.config;

import com.qiu.util.general.PropertiesUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Captain
 */
@Data
@Component
public class AlipayConfig {
    /**
     * 沙箱appId
     */
    public static final String APPID = "2016110200787593";

    /**
     * 请求网关  固定
     */
    public static final String URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 编码
     */
    public static final String CHARSET = "UTF-8";

    /**
     * 返回格式
     */
    public static final String FORMAT = "json";

    /**
     * RSA2
     */
    public static final String SIGNTYPE = "RSA2";

    /**
     * 异步通知地址
     */
    public static final String NOTIFY_URL = "http://" + PropertiesUtil.getDomain() + "/alipay/notify";

    /**
     * 同步地址
     */
    public static final String RETURN_URL = "http://" + PropertiesUtil.getDomain() + "/alipay" +
            "/success";

    /**
     * 应用私钥 pkcs8格式
     */
    public static final String RSA_PRIVATE_KEY =
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCby10zPY8o2Q9NlBRc0Z5eiHFA3qURLZ3uFGUMZ9ye38n2qSQ7iy6vyv06EXiOH719/1pMpPEFCVxaHrdSBMN7qES46jPZk1Nz1BxGJMelPRsMqlzA9Cqj0Pi41VW6tTRe9Qp7o5vOQbh0WVT1NKiVnWS4vLsVetm67yLNJ/Ml1M7U4u2LAqV2qqlREl2z7jrSrARl/kKgldlGx6JzNgt73TNM74Po23Gbe3959QJLHptC47eUxROcI8cd2QCludS0Ibo6RT6rAKEr5vBzAdzafiqdGeqY+muzdUwb6Ze3E0bcF2gxhAu77F//pL6EY8CkkBlAuPr0+rqdB3qhVqr7AgMBAAECggEAHtxb6+iH0TmeFGn+1pwXWPaCKK1x2UNGA4E6uwhf5YDDCv9aVAkwCFaXbsdDMDQNX0YT5xbNP4gpKRjoi3CyIG6mnlKWTsUmRM2BhEuI2KyQDSQ6F2bGg4jDnCl2P/PhGeZEW1V/aY4PMcz4TVJPCsQQzZ9lF84sDyGLrUP6oEWFvlNJnuGYtO/w6jtXeYF5bn3/dhm9adlkELMamId9P9rcRdJ1Empkgfdder/69wrOcW+2WBC7HgQzE/Un7/u60trIDFURI54MCtd6IrDJotZ6JfvzEgCGgMW+QVF8fO5I81CiVN79nc/NUfw7DGHYHBCzt2lahcqEpy9QLT2n8QKBgQDgzBMkAgSVghSlqvZwwVkXLcMRw1FncX9gw2fvO07okZ9pQD+05oERzDiWaPssrc334j0XRz0IDkWxkO+4GS4y7doCLyIM4wbIXictZplmzQdO4Y8dLaeF+uHSEm/r2rUr59r0Sbyc811H0q5hCJpOxAPoCp33mMD2J7kgckhGNwKBgQCxa1mWxgU1fbQR4g04dP1HnQiKHpvoKuQv+4/APZSYLkWYgBxoGFTLjO1WljZW/pOdu6JU5kIisLSw2+dGKQHk7ZHEmkelj6w8uaUAumVMsDA0ByUvqc2zfUEvrFMutkEKefF8WupdxPEoT3sEsZqU0nOqU7QJ4nTMlqwcpc+fXQKBgEwH+flVzWMb6gvWdR/R5WYXjkC1xRStqJ17ue2KK7gxeK9KkcGHrOPuJL2KqFmGN4G9vVfgOl49JYlXMh0cNyn2lKPkeSnOp36hibKrXvsMIYqmc5c019JS9onsvnoRMPG2q7GwNuEG1ufx9BTRqg818PRMkv0pZypaaRz2SkelAoGBAKpLSQXAclWCn6memWP8XvQzFXRFpw40xSwNQVCbixp7J5AWP63m1NVAIrs+mU/tMVFwPYNSNF7Jz9YGbTnZuJi9M3JLR8PxYLg01N0bDIqMfughh571VJ0Y983DkWA/zbQ5kgHz3gs9MuCnUYQS2TZJvn3Zs/Dc7ZFGelNREDV9AoGBALyWiMz2HynSTigX4aEhLAyu1irtSnhc+ocEOmMjCGYMUWpiGcYMwYRCuGrSsD4luHcMSPBodqopsdVtS+BtL7AhZAKeDHmMeTEWCMgQKm0JFCfrVpyFK+WpChX6jgD1SQOSHFQ0fpsGrU5dV9sAX4/xCJM1oYNVNbD1MSBosXBC";

    /**
     * 沙箱支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm8tdMz2PKNkPTZQUXNGeXohxQN6lES2d7hRlDGfcnt/J9qkkO4sur8r9OhF4jh+9ff9aTKTxBQlcWh63UgTDe6hEuOoz2ZNTc9QcRiTHpT0bDKpcwPQqo9D4uNVVurU0XvUKe6ObzkG4dFlU9TSolZ1kuLy7FXrZuu8izSfzJdTO1OLtiwKldqqpURJds+460qwEZf5CoJXZRseiczYLe90zTO+D6Ntxm3t/efUCSx6bQuO3lMUTnCPHHdkApbnUtCG6OkU+qwChK+bwcwHc2n4qnRnqmPprs3VMG+mXtxNG3BdoMYQLu+xf/6S+hGPApJAZQLj69Pq6nQd6oVaq+wIDAQAB";

    private AlipayConfig() {
    }
}