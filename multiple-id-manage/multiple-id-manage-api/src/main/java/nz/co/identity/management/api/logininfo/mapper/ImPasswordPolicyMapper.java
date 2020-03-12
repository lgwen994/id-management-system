
package nz.co.identity.management.api.logininfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.logininfo.entity.ImPasswordPolicy;

/**
 * <code>StvIdmfPasswordPolicyMapper</code>is mapper class for getting
 * passwordpolicy related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImPasswordPolicyMapper {

    /**
     * delete the PasswordPolicy record by primary key
     *
     * @param passwordPolicyId
     *        passwordPolicy id
     * @param versionNo
     *        version no
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPasswordPolicyIdByVersion(
            @Param("passwordPolicyId") String passwordPolicyId,
            @Param("versionNo") int versionNo);

    /**
     * insert a record
     *
     * @param record
     *        PasswordPolicy entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImPasswordPolicy record);

    /**
     * select the PasswordPolicy record by primary key
     *
     * @param passwordPolicyId
     *        PasswordPolicy id
     * @return PasswordPolicy entity
     * @since Staveware Core Ver.5.3
     */
    ImPasswordPolicy selectByPrimaryKey(String passwordPolicyId);

    /**
     * select the PasswordPolicy record by PasswordPolicy code
     *
     * @param passwordPolicyCode
     *        PasswordPolicy code
     * @return PasswordPolicy entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPasswordPolicy> selectByPasswordPolicyCode(
            String passwordPolicyCode);

    /**
     * update selective the PasswordPolicy record by primary key
     *
     * @param map
     *        PasswordPolicy entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the PasswordPolicy record by primary key
     *
     * @param map
     *        PasswordPolicy entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * select the PasswordPolicy record by RoleMst code
     *
     * @param passwordPolicyCode
     *        password code
     * @return PasswordPolicy entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String passwordPolicyCode);

    /**
     * select the PasswordPolicy record by PasswordPolicy properties
     *
     * @param map
     *        PasswordPolicy entity
     * @return PasswordPolicy entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImPasswordPolicy> selectPasswordPolicy(Map<String, Object> map);
    
    /**
     * select the passwordPolicy record Count by passwordPolicy properties
     *
     * @param map
     *        passwordPolicy entity
     * @return passwordPolicy record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByPasswordPolicy(Map<String, Object> map);
}