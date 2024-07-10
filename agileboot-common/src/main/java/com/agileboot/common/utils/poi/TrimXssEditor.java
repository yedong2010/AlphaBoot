package com.agileboot.common.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.dromara.hutool.http.html.HtmlUtil;
import org.dromara.hutool.poi.excel.cell.CellEditor;

/**
 * @author valarchie
 * 读取excel的时候，去除掉html相关的标签  避免xss注入
 */
public class TrimXssEditor implements CellEditor {

    @Override
    public Object edit(Cell cell, Object value) {
        if (value instanceof String) {
            return HtmlUtil.cleanHtmlTag(value.toString());
        }
        return value;
    }
}
