
package nz.co.identity.management.api.roleinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.roleinfo.dao.ImPositionRoleDAO;
import nz.co.identity.management.api.roleinfo.data.ImRoleAndPosition;
import nz.co.identity.management.api.roleinfo.entity.ImPositionRole;
import nz.co.identity.management.api.roleinfo.service.ImPositionRoleService;

/**
 *
 * the implementation class of positionRole's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImPositionRoleServiceImpl
        extends BaseService<ImPositionRoleDAO>
        implements ImPositionRoleService {

    /**
     * the list of StvIdmfPositionRoleDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImPositionRoleDAO> stvIdmfPositionRoleDAOList;

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
    public ImPositionRole registerPositionRole(
            ImPositionRole positionRole) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .registerPositionRole(positionRole);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole updatePositionRole(
            ImPositionRole positionRole) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .updatePositionRole(positionRole);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePositionRole(ImPositionRole positionRole) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .deletePositionRole(positionRole);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole getPositionRole(String positionRoleId) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .getPositionRole(positionRoleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPositionRole getPositionRoleByPositionRoleCode(
            String positionRoleCode) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .getPositionRoleByPositionRoleCode(positionRoleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPositionRole> searchPositionRole(
            ImPositionRole positionRole, Integer pageNum, Integer pageSize,
            List<String> sort) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .searchPositionRole(positionRole, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImRoleAndPosition> searchRole(ImPosition position,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .searchRole(position, pageNum, pageSize, sort);
    }

    @Override
    public boolean deleteForcePositionRole(ImPositionRole positionRole) {
        return getDAO(historyRoleinfo, stvIdmfPositionRoleDAOList)
                .deleteForcePositionRole(positionRole);
    }

}
