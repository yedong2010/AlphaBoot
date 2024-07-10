package com.agileboot.domain.system.menu.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateMenuCommand extends AddMenuCommand {

    @NotNull
    private Long menuId;

}
