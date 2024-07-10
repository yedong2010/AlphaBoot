package com.agileboot.domain.system.monitor.dto;

import com.agileboot.common.constant.Constants;
import lombok.Data;
import org.dromara.hutool.core.math.NumberUtil;

/**
 * 內存相关信息
 *
 * @author valarchie
 */
@Data
public class MemoryInfo {

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public double getTotal() {
        return NumberUtil.div(total, Constants.GB, 2).doubleValue();
    }

    public double getUsed() {
        return NumberUtil.div(used, Constants.GB, 2).doubleValue();
    }

    public double getFree() {
        return NumberUtil.div(free, Constants.GB, 2).doubleValue();
    }

    public double getUsage() {
        return NumberUtil.div(used * 100, total, 2).doubleValue();
    }
}
