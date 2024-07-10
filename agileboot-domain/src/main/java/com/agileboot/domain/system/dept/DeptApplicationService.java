package com.agileboot.domain.system.dept;

import com.agileboot.domain.system.dept.command.AddDeptCommand;
import com.agileboot.domain.system.dept.command.UpdateDeptCommand;
import com.agileboot.domain.system.dept.dto.DeptDTO;
import com.agileboot.domain.system.dept.model.DeptModel;
import com.agileboot.domain.system.dept.model.DeptModelFactory;
import com.agileboot.domain.system.dept.query.DeptQuery;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.dept.db.SysDeptService;
import com.agileboot.domain.system.role.db.SysRoleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.tree.TreeNode;
import org.dromara.hutool.core.tree.TreeUtil;
import org.springframework.stereotype.Service;

/**
 * 部门服务
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class DeptApplicationService {

    private final SysDeptService deptService;

    private final SysRoleService roleService;

    private final DeptModelFactory deptModelFactory;


    public List<DeptDTO> getDeptList(DeptQuery query) {
        List<SysDeptEntity> list = deptService.list(query.toQueryWrapper());
        return list.stream().map(DeptDTO::new).collect(Collectors.toList());
    }

    public DeptDTO getDeptInfo(Long id) {
        SysDeptEntity byId = deptService.getById(id);
        return new DeptDTO(byId);
    }

    public List<MapTree<Long>> getDeptTree() {
        List<SysDeptEntity> list = deptService.list();

        return TreeUtil.build(list, 0L, (dept, tree) -> {
            tree.setId(dept.getDeptId());
            tree.setParentId(dept.getParentId());
            tree.putExtra("label", dept.getDeptName());
        });
    }


    public void addDept(AddDeptCommand addCommand) {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.loadAddCommand(addCommand);

        deptModel.checkDeptNameUnique();
        deptModel.generateAncestors();

        deptModel.insert();
    }

    public void updateDept(UpdateDeptCommand updateCommand) {
        DeptModel deptModel = deptModelFactory.loadById(updateCommand.getDeptId());
        deptModel.loadUpdateCommand(updateCommand);

        deptModel.checkDeptNameUnique();
        deptModel.checkParentIdConflict();
        deptModel.checkStatusAllowChange();
        deptModel.generateAncestors();

        deptModel.updateById();
    }

    public void removeDept(Long deptId) {
        DeptModel deptModel = deptModelFactory.loadById(deptId);

        deptModel.checkHasChildDept();
        deptModel.checkDeptAssignedToUsers();

        deptModel.deleteById();
    }



}
