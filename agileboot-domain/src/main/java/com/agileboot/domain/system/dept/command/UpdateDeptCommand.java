package com.agileboot.domain.system.dept.command;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDeptCommand extends AddDeptCommand {

    @NotNull
    @PositiveOrZero
    private Long deptId;

}
