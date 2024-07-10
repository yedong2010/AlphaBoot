package com.agileboot.domain.system.config.dto;

import org.dromara.hutool.core.collection.ListUtil;
import org.dromara.hutool.core.convert.Convert;
import com.agileboot.common.enums.common.YesOrNoEnum;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.domain.system.config.db.SysConfigEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.dromara.hutool.json.JSONUtil;

/**
 * @author valarchie
 */
@Data
@Schema(name = "ConfigDTO", description = "配置信息")
public class ConfigDTO {

    public ConfigDTO(SysConfigEntity entity) {
        if (entity != null) {
            configId = entity.getConfigId() + "";
            configName = entity.getConfigName();
            configKey = entity.getConfigKey();
            configValue = entity.getConfigValue();
            configOptions =
                JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                    String.class) : ListUtil.empty();
            isAllowChange = Convert.toInt(entity.getIsAllowChange());
            isAllowChangeStr = BasicEnumUtil.getDescriptionByBool(YesOrNoEnum.class, entity.getIsAllowChange());
            remark = entity.getRemark();
            createTime = entity.getCreateTime();
        }
    }

    private String configId;
    private String configName;
    private String configKey;
    private String configValue;
    private List<String> configOptions;
    private Integer isAllowChange;
    private String isAllowChangeStr;
    private String remark;
    private Date createTime;

}
