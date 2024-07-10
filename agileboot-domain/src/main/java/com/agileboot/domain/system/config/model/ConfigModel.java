package com.agileboot.domain.system.config.model;

import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.collection.ListUtil;
import org.dromara.hutool.core.text.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.config.command.ConfigUpdateCommand;
import com.agileboot.domain.system.config.db.SysConfigEntity;
import com.agileboot.domain.system.config.db.SysConfigService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.hutool.json.JSONUtil;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigModel extends SysConfigEntity {

    private SysConfigService configService;

    private Set<String> configOptionSet;

    public ConfigModel(SysConfigService configService) {
        this.configService = configService;
    }

    public ConfigModel(SysConfigEntity entity, SysConfigService configService) {
        BeanUtil.copyProperties(entity, this);

        List<String> options =
            JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                String.class) : ListUtil.empty();

        this.configOptionSet = new HashSet<>(options);

        this.configService = configService;
    }

    public void loadUpdateCommand(ConfigUpdateCommand updateCommand) {
        this.setConfigValue(updateCommand.getConfigValue());
    }


    public void checkCanBeModify() {
        if (StrUtil.isBlank(getConfigValue())) {
            throw new ApiException(ErrorCode.Business.CONFIG_VALUE_IS_NOT_ALLOW_TO_EMPTY);
        }

        if (!configOptionSet.isEmpty() && !configOptionSet.contains(getConfigValue())) {
            throw new ApiException(ErrorCode.Business.CONFIG_VALUE_IS_NOT_IN_OPTIONS);
        }
    }

}
