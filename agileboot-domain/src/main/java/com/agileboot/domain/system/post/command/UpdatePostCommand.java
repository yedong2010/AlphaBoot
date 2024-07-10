package com.agileboot.domain.system.post.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
public class UpdatePostCommand extends AddPostCommand {

    @NotNull("岗位ID不能为空")
    @Positive
    @Setter
    @Getter
    public Long postId;

    public UpdatePostCommand(@NotNull("显示顺序不能为空") Integer postSort) {
        super(postSort);
    }

}
