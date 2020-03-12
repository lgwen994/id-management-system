
package nz.co.identity.management.api.roleinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.roleinfo.entity.ImRoleNameMst;

/**
 * <code>StvIdmfRoleNameMstMapper</code>is mapper class for getting roleName
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImRoleNameMstMapper {

    /**
     * select the RoleNameMst record by primary key
     *
     * @param roleNameId
     *        roleName id
     * @return RoleNameMst entity
     * @since Staveware Core Ver.5.3
     */
    ImRoleNameMst selectByPrimaryKey(String roleNameId);

    /**
     * select the RoleNameMst record by roleId
     *
     * @param roleId
     *        role id
     * @return RoleNameMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleNameMst> selectByRoleId(String roleId);

    /**
     * select the RoleNameMst record by roleId and locale
     *
     * @param roleId
     *        role id
     * @param locale
     *        roleName locale
     * @return RoleNameMst entity
     * @since Staveware Core Ver.5.3
     */
    ImRoleNameMst selectByRoleIdByLocale(@Param("roleId") String roleId,
            @Param("locale") String locale);

    /**
     * select the RoleNameMst record by RoleMst code
     *
     * @param roleCode
     *        role code
     * @return RoleNameMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleNameMst> selectByRoleCode(String roleCode);

    /**
     * select the RoleNameMst record by RoleNameMst properties
     *
     * @param map
     *        RoleNameMst entity
     * @return RoleNameMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleNameMst> selectByRoleNameMst(Map<String, Object> map);
    
    /**
     * select the roleNameMst record Count by roleNameMst properties
     *
     * @param map
     *        roleNameMst entity
     * @return roleNameMst record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByRoleNameMst(Map<String, Object> map);

    /**
     * update selective the RoleNameMst record by primary key
     *
     * @param map
     *        RoleNameMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the RoleNameMst record by primary key
     *
     * @param map
     *        RoleNameMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * insert a record
     *
     * @param record
     *        RoleNameMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImRoleNameMst record);

    /**
     * delete the RoleNameMst record by roleId
     *
     * @param roleId
     *        role id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByRoleId(String roleId);

    /**
     * delete the RoleNameMst record by roleId and locale
     * 
     * @param roleId
     *        role Id
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByRoleIdByLocale(@Param("roleId") String roleId,
            @Param("locale") String locale);
}