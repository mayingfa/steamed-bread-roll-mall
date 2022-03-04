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
            "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDYjcBKrMlFIeYbEgq1zDH3Vxk4C5M3mE96xIAWu4OwPJOvauWkiu5tintpl0IrJJDCeCtHS19oPpwxwrbocFDfRuF7FJGiWCSwdPCvLihUScXVvwVBGktgNkrZBuJkXQNZRCN4aJSzHYjV+EG0Wnm0rX8rAz3JXO1Ejlv4O4Ql/n/FEJP/hoOFCXXfjReVP5Gn9Zt+r9mOlhJs2UT9LmqIffn3AKQudy73496F3gKiQzXkg7QYAF7c63zsKoCpd+ycJtAhCbblWSfZ+JtMBqLZ+qMZvi7cDCUxMv+0nQUMjn8Mj1Njy+0L1aI6hm1Y1sEkK1kSqrV3kMoU038eBDdHAgMBAAECggEBAIgEoR9GYDU4EZw3PNrrowbEgmadfp6Q4HswKXQd2506r1ijQBKV4SSUsuejxj/q+7HqYOSrBwYnLVxj/RcKVQGpN79jMAZUbtcW3IZr65DltYmILfiyV13bPDANDu4TLiNHlS/wIFZQ2r3q7PonSgKeZx3RL2HnKHDDKilE2MAzbp/RLeKDxHHwoGL5pso5O6AFW+Uf+LSlmrXF9TUanxH8yegYdOLuSnRsbeWQ2gTuyJ/kPwmLSJRS56Rg/GjxMhF3F/xOGkF5YSFnt+KnCofNu8bMu1rOtU0qQG6Ho5AveoPH33QC9Zq31WArAFGnsANi3gGK33RklTS+Me5DTmECgYEA7/w8Nu1Bnn1ZxhAr4ePW8i7ceZVaq7hSYXGYpiYrH4EcWN8bH/lXcRdRDGCHuip3UpIJhbIow082bYVr7BeJLlZC9nBR1BA7rA2hcgnBavdnORkQeLpR64/9YpN+uv3jAeg8iVh3DdiTA6E9Jigr0sVC7tKSH+DKfWYP5euvqQsCgYEA5wE5kue4gBjYGJDaeEMh/Pl6RjCz/tE5ntnD2SfDAxu0vbIDoqKCZEgQp5hllZ9ejD06R/v0gB4dw479dlVmJ5tVDW+0PPxyUd1ZtyMukt050B2hlVkQXtH+hzVqshcb3L7QZLl/v4wiNJOpcjJiVRCnlEnXjU9OKbLY80uFqDUCgYEAvfb3Hy99gtyMxJimc9/rlLd9dIzCQi3coB/Qiqyp8/06mCq7CPtRuUaQtHBw3++flm/dM8SMOdYpKlaz0CXtRA2qYfMVAv16jr4XbvLD9U0zm2H4xOgP0pyfIXnJlnFEUY0o5jttujsWXIg0pFCmJ+hYDD4Ix00kXFD/Aqwm13MCgYBGKFkUBMxEG0eGLngrjjgEXMCGEMSZ33FgjUKt8SWJDCEYe7ge7LwtOLXWMLKpfn14mh/ZPOxE/OCNRCA2kDk2TjbGneZfn0wZEODufgDnGck1K8qnUSVnpt9msRJ2hA4yPMGN1ci2pBi/XxlXVXYH38cFgme0wyE6CNdbDNTLAQKBgQC+ukY54XllKzqEg7/3Ifp2WsgPXHlvITgYoLqvWfUVZc858nZV7YtMGoAqvYAlMUzakLbGj79HQLEjpG3bKY+tQ1N311IKZUnaa3OdC2FMvwNivmksHdV6mZXXZSr3zb2bgXRPo8Xyaxp2FdVqGSRb4QgGfErjyuqh1ejZqXOeSw==";

    /**
     * 沙箱支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3oN26u6vw0TaiE8fkHpKerphbDlFtguH9TRQeYppBrZzYnxZhRv2s7Skk2hpnyUcFx7JOL8Oon6d2sTy25qFoUudYHk5xz7duKajbImZ4PeBprhGM+oRGLxgNyyftP3O6Et77PGJEIvdy6BIA4Z+DYihC0JjIyHad+XNF4jOWo9oEskplldcU23M1Qj0r4nCgWQm+jgOk+kigj5jxa4+qarMXFEIEkPvGNu3Io/uI2u/BFiGyj894VQ81IV119G1jiBWZ4REt72FlADYGhhcILXdKJTUw7i+bg+3kieOk9JQkoRi+9zaIubuRTkEdcnol0c8Ln/PjAfPTjQF0bKfcwIDAQAB";

    private AlipayConfig() {
    }
}