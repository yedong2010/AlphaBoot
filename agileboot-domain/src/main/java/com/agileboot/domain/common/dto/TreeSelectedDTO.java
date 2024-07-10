package com.agileboot.domain.common.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.tree.MapTree;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class TreeSelectedDTO {

    private List<Long> checkedKeys;
    private List<MapTree<Long>> menus;
    private List<MapTree<Long>> depts;

}
