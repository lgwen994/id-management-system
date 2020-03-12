
package nz.co.identity.management.api.logininfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nz.co.identity.management.api.logininfo.entity.ImPasswordHistory;

/**
 * <code>StvIdmfPasswordHistoryMapper</code>is mapper class for getting
 * passwordhistory related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImPasswordHistoryMapper {

    /**
     * select the PasswordHistory record by PasswordHistory properties
     *
     * @param record
     *        PasswordHistory entity
     * @return PasswordHistory entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImPasswordHistory> selectByPasswordHistory(
            ImPasswordHistory record);
}