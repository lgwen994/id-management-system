
package nz.co.identity.management.api.roleinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.roleinfo.dao.ImRoleNameMstDAO;
import nz.co.identity.management.api.roleinfo.entity.ImRoleNameMst;
import nz.co.identity.management.api.roleinfo.service.ImRoleNameMstService;

/**
 *
 * the implementation class of roleName's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImRoleNameMstServiceImpl
        extends BaseService<ImRoleNameMstDAO>
        implements ImRoleNameMstService {
    /**
     * the list of StvIdmfRoleNameMstDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImRoleNameMstDAO> stvIdmfRoleNameMstDAOList;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.roleinfo:off}")
    private String historyRoleinfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleNameMst registerRoleName(ImRoleNameMst roleNameMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .registerRoleName(roleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleNameMst updateRoleName(ImRoleNameMst roleNameMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .updateRoleName(roleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRoleName(ImRoleNameMst roleNameMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .deleteRoleName(roleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForceRoleName(ImRoleNameMst roleNameMst) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .deleteForceRoleName(roleNameMst);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImRoleNameMst> getRoleName(String roleId) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .getRoleName(roleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImRoleNameMst getRoleNameById(String roleNameId) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .getRoleNameById(roleNameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImRoleNameMst> getRoleNameByRoleCode(String roleCode) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .getRoleNameByRoleCode(roleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImRoleNameMst> searchRoleName(
            ImRoleNameMst roleNameMst, Integer pageNum, Integer pageSize,
            List<String> sort) {
        return getDAO(historyRoleinfo, stvIdmfRoleNameMstDAOList)
                .searchRoleName(roleNameMst, pageNum, pageSize, sort);
    }

}
