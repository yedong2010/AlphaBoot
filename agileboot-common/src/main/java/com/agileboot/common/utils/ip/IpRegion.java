package com.agileboot.common.utils.ip;

import org.dromara.hutool.core.text.CharSequenceUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author valarchie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpRegion {
    private static final String UNKNOWN = "未知";
    private String country;
    private String region;
    private String province;
    private String city;
    private String isp;

    public IpRegion(String province, String city) {
        this.province = province;
        this.city = city;
    }

    public String briefLocation() {
       return String.format("%s %s",
           CharSequenceUtil.defaultIfEmpty(province, UNKNOWN),
           CharSequenceUtil.defaultIfEmpty(city, UNKNOWN)).trim();
    }

}
