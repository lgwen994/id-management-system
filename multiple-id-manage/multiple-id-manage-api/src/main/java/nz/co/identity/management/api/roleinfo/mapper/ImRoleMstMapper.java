
package nz.co.identity.management.api.roleinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.roleinfo.entity.ImRoleMst;

/**
 * <code>StvIdmfRoleMstMapper</code>is mapper class for getting role related
 * information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImRoleMstMapper {

    /**
     * select the RoleMst record by primary key
     *
     * @param roleId
     *        role id
     * @return RoleMst entity
     * @since Staveware Core Ver.5.3
     */
    ImRoleMst selectByPrimaryKey(String roleId);

    /**
     * select the RoleMst/RoleNameMst record by primary key and locale
     *
     * @param roleId
     *        role id
     * @param locale
     *        roleName locale
     * @return RoleMst entity
     * @since Staveware Core Ver.5.3
     */
    ImRoleMst selectByRoleIdByLocale(@Param("roleId") String roleId,
            @Param("locale") String locale);

    /**
     * select the RoleMst record by RoleMst code
     *
     * @param roleCode
     *        role code
     * @return RoleMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleMst> selectByRoleCode(String roleCode);

    /**
     * select the max version record by RoleMst code
     *
     * @param roleCode
     *        role code
     * @return RoleMst entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String roleCode);

    /**
     * select the RoleMst record by roleCode and locale
     *
     * @param roleCode
     *        role code
     * @param locale
     *        roleName locale
     * @return RoleMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleMst> selectByRoleCodeByLocale(
            @Param("roleCode") String roleCode, @Param("locale") String locale);

    /**
     * select the RoleMst record by RoleMst properties
     *
     * @param map
     *        RoleMst entity
     * @return RoleMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleMst> selectByRoleMst(Map<String, Object> map);

    /**
     * select the roleMst record Count by roleMst properties
     *
     * @param map
     *        roleMst entity
     * @return roleMst record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByRoleMst(Map<String, Object> map);

    /**
     * select the RoleMst record by RoleMst properties and locale
     *
     * @param map
     *        the Map of RoleMst entity and Locale
     * @return RoleMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleMst> selectByRoleMstByLocale(Map<String, Object> map);

    /**
     * select the roleMstByLocale record Count by roleMstByLocale properties
     *
     * @param map
     *        roleMstByLocale entity
     * @return roleMstByLocale record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByRoleMstByLocale(Map<String, Object> map);

    /**
     * insert a record
     *
     * @param record
     *        RoleMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImRoleMst record);

    /**
     * update selective the RoleMst record by primary key
     *
     * @param map
     *        RoleMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the RoleMst record by primary key
     *
     * @param map
     *        RoleMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * delete the RoleMst record by primary key and verson
     *
     * @param roleId
     *        role id
     * @param versionNo
     *        version No
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByRoleIdByVersion(@Param("roleId") String roleId,
            @Param("versionNo") int versionNo);
}