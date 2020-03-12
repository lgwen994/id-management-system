
package nz.co.identity.management.api.roleinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.roleinfo.dao.ImRoleMstDAO;
import nz.co.identity.management.api.roleinfo.entity.ImRoleMst;
import nz.co.identity.management.api.roleinfo.service.ImRoleMstService;

/**
 *
 * the implementation class of role's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImRoleMstServiceImpl extends BaseService<ImRoleMstDAO>
        implements ImRoleMstService {

    /**
     * the list of StvIdmfRoleMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImRoleMstDAO> stvIdmfRoleMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.roleinfo:off}")
    private String historyRoleinfo;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ImRoleMst registerRole(ImRoleMst roleMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList)
                .registerRole(roleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleMst updateRole(ImRoleMst roleMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList)
                .updateRole(roleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRole(ImRoleMst roleMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList)
                .deleteRole(roleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceRole(ImRoleMst roleMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList)
                .deleteForceRole(roleMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleMst getRole(String roleId) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList).getRole(roleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleMst getRoleByRoleCode(String roleCode) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList)
                .getRoleByRoleCode(roleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImRoleMst> searchRole(ImRoleMst roleMst,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return getDAO(historyRoleinfo, stvIdmfRoleMstDAOList)
                .searchRole(roleMst, pageNum, pageSize, sort);
    }

}