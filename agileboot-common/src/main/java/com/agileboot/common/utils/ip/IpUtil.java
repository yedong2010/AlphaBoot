package com.agileboot.common.utils.ip;

import jakarta.servlet.http.HttpServletRequest;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.lang.Validator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.net.NetUtil;

/**
 * ip校验器
 *
 * @author valarchie
 */
@Slf4j
public class IpUtil {

    public static final String INNER_IP_REGEX = "^(127\\.0\\.0\\.\\d{1,3})|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$";
    public static final Pattern INNER_IP_PATTERN = Pattern.compile(INNER_IP_REGEX);

    private IpUtil() {
    }

    public static boolean isInnerIp(String ip) {
        return INNER_IP_PATTERN.matcher(ip).matches() || isLocalHost(ip);
    }

    public static boolean isLocalHost(String ipAddress) {
        InetAddress ia = null;
        try {
            InetAddress ad = InetAddress.getByName(ipAddress);
            byte[] ip = ad.getAddress();
            ia = InetAddress.getByAddress(ip);
        } catch (UnknownHostException e) {
            log.error("解析Ip失败", e);
            e.printStackTrace();
        }
        if (ia == null) {
            return false;
        }
        return ia.isSiteLocalAddress() || ia.isLoopbackAddress();
    }


    public static boolean isValidIpv4(String inetAddress) {
        return Validator.isIpv4(inetAddress);
    }

    public static boolean isValidIpv6(String inetAddress) {
        return Validator.isIpv6(inetAddress);
    }

    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = (String[])ArrayUtil.addAll(new String[][]{headers, otherHeaderNames});
        }

        return getClientIPByHeader(request, headers);
    }

    public static String getClientIPByHeader(HttpServletRequest request, String... headerNames) {
        String[] var3 = headerNames;
        int var4 = headerNames.length;

        String ip;
        for(int var5 = 0; var5 < var4; ++var5) {
            String header = var3[var5];
            ip = request.getHeader(header);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return NetUtil.getMultistageReverseProxyIp(ip);
    }
}
