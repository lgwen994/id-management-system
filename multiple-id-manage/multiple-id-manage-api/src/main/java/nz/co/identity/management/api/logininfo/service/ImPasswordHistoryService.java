package nz.co.identity.management.api.logininfo.service;
import java.util.List;

import nz.co.identity.management.api.logininfo.entity.ImPasswordHistory;

/**
 * according to the passwordhistory form of CRUD operations, return a valid
 * data.<br>
 * passwordhistory's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImPasswordHistoryService {

    /**
     * get passwordhistory by passwordhistory id
     *
     * @param passwordHistory
     *        passwordhistory entity
     * @return result of passwordhistory
     * @since Staveware Core Ver.5.3
     */
    List<ImPasswordHistory> getPasswordHistory(
            ImPasswordHistory passwordHistory);
}