package com.agileboot.common.core.page;

import jakarta.validation.constraints.Max;
import org.dromara.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractPageQuery<T> extends AbstractQuery<T> {

    /**
     * 最大分页页数
     */
    public static final int MAX_PAGE_NUM = 200;
    /**
     * 单页最大大小
     */
    public static final int MAX_PAGE_SIZE = 500;
    /**
     * 默认分页页数
     */
    public static final int DEFAULT_PAGE_NUM = 1;
    /**
     * 默认分页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    @Max(MAX_PAGE_NUM)
    protected Integer pageNum;
    @Max(MAX_PAGE_SIZE)
    protected Integer pageSize;

    public Page<T> toPage() {
        pageNum = ObjUtil.defaultIfNull(pageNum, DEFAULT_PAGE_NUM);
        pageSize = ObjUtil.defaultIfNull(pageSize, DEFAULT_PAGE_SIZE);
        return new Page<>(pageNum, pageSize);
    }

}
