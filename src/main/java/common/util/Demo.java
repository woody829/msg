package common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import common.util.crypto.CgMessageUtils;
import common.util.crypto.P2PMessageUtils;

/**
 * Created by woody on 2017/3/5.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
    	Logger LOG = LoggerFactory.getLogger(Demo.class);


    	// P2P公司发送报文信息给华瑞存管系统
    	// 加密
    	// 1.存管公钥加密key
    	// 2.p2p私钥签名
    	// 3.key加密发送内容
    	String msg = P2PMessageUtils.getEncryptContent("abcdefg");
        LOG.debug(msg);

        // P2P公司接收华瑞存管系统报文信息并解密验签
    	// 解密
    	// 1.p2p私钥解密key
    	// 2.key解密发送内容
    	// 3.存管公钥验签
        String encryptContent = "P0008000|M/4hHJ1ZqXMBvQ1aQjqBAr5+7cHiKWQNOgHLGmkOmIfe6FFW2P/5F/ONz1uv6/EZS0H8VPb/m4nHrKW8ErHv71+pzVNL25s3e0Of/KhK+/k9WZj5AUSBYWKXEdo740xsT2JCR26+dOb3Yj5lvv+AMsskDApmW+ORwdO0P4mYEJUmopFrqiOy3zL1rSPF/mW3TmGN6PT9S9cbNQ1bbX/zrSyxvX1l08SP5HLa4MNVaSdOGdtvMGShGML6EuOLLKer+9MpA9s7ooPbJKyh3oqQIVvOHpUSra1a9bs/O442T4iXD7Vyd3Qlxw3qeTHfTh4Qh9hS4oC2MBTjrPN5Npap7g==|c2IbD0oZ6Ybi8nLK/UPK21lQSYpQOqoI7K+y/2TebLQoUuSgvZSdtZ1wpcafYuLBXdstww+yq478EWzZJsFU3pPbvTE62pdd3iFuJiDo5Ysc32mum7oogepC8u65zp4v0tvHiWvKieK9j0AtDvsFdFkTHjI3dhiml6dXtmnNFtU=|xzShnQJVkjYCCwiXE9nE8kF2fmvhKh/Us3sOg3xhpx2dd7Esz5day4By1qqdi8LV+ehKsDX06KRQC0oKqk2jR2E3SSUgbOprPerL3+/XcUCnlEmDZ/rpnUgH30g5oxqndjU/T4qKSiGhsfThzOEV3foDuMmnnxj/neLqWYJUu+vN2cfZbB1bD0hnJ/+C2r28P0/hQeNZMKVMhTQMqgEuK0DkDWaL7DELvxk4R1zGgFeXlj9DUL0+VONkIuPxiMp0uQTUEX6bIDlZKr4n0Pflb96MoZyI9ecTOB9JR36r1zcy7JOOGTVm21h419rebN1CTxvCZ0DXnt186OmaYPeirpZ44df/ivtf+oYS7qUsGcrjyl2ZDypuBjAIWD/SiRavOdkEmI18c/K7Jnv4r6QLlj6z0+6LGlRY+jEQOomrdWCLzoTRjBck1N+wCMe1Kg471rTzL+Tz4DhSP/cwxMGpZhlvlrWFXf6IUVcevj/0ZapoBbLVKGe8foj9vjQW5+QBzx3BkpAYNdJYP9Cxng2OZaMz5kLj6agWXg9tjjr9eFszDNEFznL/n5iZx7t8ty5AmGv+Kvsmv+IFmJ8OST5vKsdi2a9av1YeYgDDp5NMPpBK1sCpRO9g08I1+3/2Op+/5anxmCVXR7jPB2o73NHlfDnx4FDYA47sxwmlFBKUVKykqCwCB/LKXKkEnW1nQWTq21IV7Qv86h6D9//YPL7k05Y6Cq4cdE4R7u0nsEs+9gPPsKcRBCkQFFXe6bfFtBJhQOGOHVf6Ql6o2sEzPka1dUsMLo4kzw1fQdmGuhsdQZAfKrh8nz3rTe6PZRuOjWECPeb+yEoztshH4HGoMMtn6cngqBoOmeB4iHjjrL2ecKEZc8rAsjJsnJDOLapPNEi5rgXoewjfWz+LBatvfkzcgQ==";
//        String encryptContent = "P0008000|mGd9wKnP1ccs7a3YzWQzqUlYBF+0dMTjYfz93cC21ok+2BGo6djuW+1rgfKuoYMLqWptzJ61Cam42wDXiQP/ZpxBwFk3+Fkzn6RLz4IxyQuWHYnNSaUMsIj92Rn6iRYAobueD/8Zz8NvjCx5OZRPkXeJs5YGd9mky38NT3kmtftLgdFCldLtu9k9C2M+Wp4wkRCWjzF/opXKQ2h2DVN1XeCYRjlHLJEQW+7FtC+AsTVvNlw2TSgFrZP24v4p52WbMA2zXgSzRnIfcDy+3NZEzjZX40FsQqKL7t6kWVhJjGedR6iVbh66VLneH2uykB8mSeF4hvHP3oAop0uK7tNc1w==|q3Mofc8OIlybqh5HbhQfFenmCKSy9wjeZuQeidBrbXMbR2P938Oequ66WTo3lmDAClSPS7jJRB6RusSDU664QxPrndQiDHYt9QqGibBhWLbzd5T23CYk/FCDrq8FHBfhZqU6wwlZgUjuMFQ0bnkO9UsMtxJYswakomwo1QG9TLQ=|DUUCXYo3TKm8pICLCuOMiBWkqWokzxfq/lB76Da3dOQqqe8LeE9W69dIzDwxa/1KHKGHf4LHHMA84O+cm0rpDqMZD8De4kCchkHYSSTHZ2/72Jf7RRnplpgK4FgOb6WVQb2V/KWqw/ukhvXCsanz/ejlHnQgpDnXEt2PhC/BCK2oT9tTJ9UBvFzPF/5UVijMM3pyvKz8SfyXsjp/tqdFs6gvXhQ7LxjQ6ygOCFDYmssDn6A5qQCpGUmu8lT3w/h9CzjAW/o/qzzRsUSsh1b8rM/pITYMBzPP7D9R5mHYskGXTIluIjo+eLo5ABGgH6x259hAhNtd3U8S67ld/GsKtyULvW3IId/hTKKg0ZNoJt0lcS4/nnPaU8B6C9TRLCq2HRy7KUz1YE9YrZKm0pzh+oyiqGKQbDb7dOj4mpX4MkGo1VApnJFgSTnjvsNlAjriJdGVW5iUmdiROM3S6yYuthyZDis5AE08+r97LawBm+J5N2IkaHPz5tZ5gRQAhXDxky7pvf/61A3REviNCXLK+ap+K8EZx071kLosLWbxLNy0S9VG/184bjgdMfXyRnP+EWJp3fdqcQ+SOrxWQWGuLH9YATlf/gICbUNv6DMkAowVdDeYz+yJQjbDsShsu3q80mGUB5/+1X1B4JxygLLN5Q5DCJ+JILJlK5yeE1yb66nvktaX1VVkMYQ1nI5LWkPq0dG3j9quq8oj1JlMfTNag46hmN2TfdO3c4je29acoS9J6wlsxmSVcUhHix+A7MZm7qtmUa3Bgbx6fQWrlbxDoc/C5xoqOraBZfZLq9qMA1vu/ngl4nxC3IReAWrrEywPBNCpvsAazxFbROvburFu8+f1ZzNNCo6sJG9YYtBRlpO/CN4I9T+/dzn48HqAX2/cYorMaFrtxeVkh9C/upM69w==";
        String s = P2PMessageUtils.getDecryptContent(encryptContent);
        System.out.println("info:"+s);



        // 存管接收P2P报文信息并解密验签
        // 解密
        // 1.存管私钥解密key
        // 2.key解密发送内容
        // 3.P2P公钥验签
        String msg1="P0002000|gI/NDpfZnSB1uuT8Poxp7j+cGFL+AXa4ExewDyLzMuabMqa8cwv89kINVpVLRP7IfihWNEL3RwqMD72H1oC+6aFU7A915l/xRMJhv6vqdx1kd4NdIxJixiuQMEBensp0aBpLvVJtKKEDK89LcxrzWmmxnQPbCqRIYWLmJbq6BCY=|Y2h1QOSsF4uEUYncifNvm6/3JNBvQn6mAzdcTT+GIlXMBd/dQhEQ5IjQ7w1tM+ccDBYD/aYNfAUa1TmNhv6WFlt38MKTX9MVhPsfFNP3Ox2+WWkJaPPMUc5tHEc2f18F1jGN3XKr+ZRoJYXUUoN7X8JRbPtb9kI5XRD+TB/xPJg=|GFaZYccCgVZ45OYPXcoj6TnzeeFxwoQto6iiOddTWzS4OfQVEOlIGeRaa+ns57SfG0lhRxVe9z0hb32uYZ3ovzJdMC5JDB/AngOWBLlLNxEP6IcJFpS3Vd52qBqM4w11xeHm4b08yoDdTghSecGBiR4shluXU66a4QdKaYXN97MDdPCbEpNOY03xK+NqpogsXUHVKMMfbjoFdCZgEEDtbAem+J/vSrBcYZ3AOFXeiry4WX7cPXiGIehzx3Rvnh6nrZIDJV5nnxUp35v67UzblLOCOUafn0mZc8YYxYg1eIki7Km0+iIod+XXuuDui7L2ywmZHXATGTb+SqbzDrnnMSiwM0EF+eb2um6ZVz/AUZXIFC6kTeLGMothZ691R8kkfbWcBsVH5M/c3XjAVBcp9RHFDd4SeqNArwqpJ0wUxvbxPUVt2wQfPpJXC7fwPwaP8aVx1Xmwa5Hd0+b4G7ASsJZo+Gy/sySgJ3R6jP5Wtg76Fh7B3t+6aRUe3QRLWd7ZnbHGIrjlKSxJGvf+Y0Z+HyQZFmqHV2MIqppDpMKslPhTNm1hhiaXKY3/00oaGZCRGGZWDsdC75J5ppf4KxFvL+OEBNEcjJeMqQzF870DeWkcPwWJqQJYt/NKEUpQDJ4gSlG6HWvaaP4IFutgdtDHNoCCcGthsqE2xMIBI/9/2TBp1aD7e2hystgJuwxC2xeyHZyrtlbV/jz7op3QjRwJsQ==";
        CgMessageUtils.getDecryptContent(msg1);

    }
}
