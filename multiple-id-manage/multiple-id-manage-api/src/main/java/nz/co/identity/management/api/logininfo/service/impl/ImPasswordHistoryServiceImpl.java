package nz.co.identity.management.api.logininfo.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.logininfo.dao.ImPasswordHistoryDAO;
import nz.co.identity.management.api.logininfo.entity.ImPasswordHistory;
import nz.co.identity.management.api.logininfo.service.ImPasswordHistoryService;

/**
 *
 * the implementation class of passwordhistory's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImPasswordHistoryServiceImpl
        implements ImPasswordHistoryService {

    /**
     * stvIdmfPasswordHistoryDAO
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordHistoryDAO stvIdmfPasswordHistoryDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImPasswordHistory> getPasswordHistory(
            ImPasswordHistory passwordHistory) {
        return stvIdmfPasswordHistoryDAO.getPasswordHistory(passwordHistory);
    }

}
