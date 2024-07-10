package com.agileboot.domain.system.role.command;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateRoleCommand extends AddRoleCommand {

    @NotNull
    @PositiveOrZero
    private Long roleId;

}
