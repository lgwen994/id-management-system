package nz.co.identity.management.api.logininfo.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import nz.co.identity.management.api.logininfo.dao.ImPasswordHistoryDAO;
import nz.co.identity.management.api.logininfo.entity.ImPasswordHistory;
import nz.co.identity.management.api.logininfo.mapper.ImPasswordHistoryMapper;

/**
 *
 * the implementation class of password history's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
public class ImPasswordHistoryDAOImpl
        implements ImPasswordHistoryDAO {

    /**
     * idmfPasswordHistoryMapper
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPasswordHistoryMapper idmfPasswordHistoryMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImPasswordHistory> getPasswordHistory(
            ImPasswordHistory passwordHistory) {
        return idmfPasswordHistoryMapper
                .selectByPasswordHistory(passwordHistory);
    }

}
