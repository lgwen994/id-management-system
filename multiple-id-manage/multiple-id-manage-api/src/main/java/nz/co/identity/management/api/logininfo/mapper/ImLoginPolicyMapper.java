
package nz.co.identity.management.api.logininfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.logininfo.entity.ImLoginPolicy;

/**
 * <code>StvIdmfLoginPolicyMapper</code>is mapper class for getting loginpolicy
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImLoginPolicyMapper {

    /**
     * insert a record
     *
     * @param record
     *        LoginPolicy entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImLoginPolicy record);

    /**
     * select the LoginPolicy record by primary key
     *
     * @param loginPolicyId
     *        LoginPolicy id
     * @return Action entity
     * @since Staveware Core Ver.5.3
     */
    ImLoginPolicy selectByPrimaryKey(String loginPolicyId);

    /**
     * select the LoginPolicy record by LoginPolicy code
     *
     * @param loginPolicyCode
     *        LoginPolicy code
     * @return LoginPolicy entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImLoginPolicy> selectByLoginPolicyCode(String loginPolicyCode);

    /**
     * select the max(version) record by loginPolicyCode
     *
     * @param loginPolicyCode
     *        LoginPolicy loginPolicyCode
     * @return Max Version
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String loginPolicyCode);

    /**
     * update selective the LoginPolicy record by primary key
     *
     * @param map
     *        LoginPolicy entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the LoginPolicy record by primary key
     *
     * @param map
     *        LoginPolicy entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * select the LoginPolicy record by LoginPolicy properties
     *
     * @param map
     *        LoginPolicy entity
     * @return LoginPolicy entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImLoginPolicy> selectLoginPolicy(Map<String, Object> map);
    
    /**
     * select the loginPolicy record Count by loginPolicy properties
     *
     * @param map
     *        loginPolicy entity
     * @return loginPolicy record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByLoginPolicy(Map<String, Object> map);

    /**
     * delete the LoginPolicy record by primary key
     *
     * @param loginPolicyId
     *        loginPolicy id
     * @param versionNo
     *        version no
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByLoginPolicyIdByVersion(
            @Param("loginPolicyId") String loginPolicyId,
            @Param("versionNo") int versionNo);
}