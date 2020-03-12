package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;

/**
 * <code>StvIdmfOrgNameMstMapper</code>is mapper class for getting orgName
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImOrgNameMstMapper {

    /**
     * insert a record
     *
     * @param record
     *        stvIdmfOrgNameMst entity
     * @return flag result(0:false, 1:true)
     *
     * @since Staveware Core Ver.5.3
     */
    int insert(ImOrgNameMst record);

    /**
     * select the stvIdmfOrgNameMst record by primary key
     *
     * @param orgNameId
     *        stvIdmfOrgNameMst orgNameId
     * @return stvIdmfOrgNameMst entity
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst selectByPrimaryKey(String orgNameId);

    /**
     * update selective the stvIdmfOrgNameMst record by primary key
     *
     * @param mapOrgName
     *        stvIdmfOrgNameMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> mapOrgName);

    /**
     * update the stvIdmfOrgNameMst record by primary key
     *
     * @param map
     *        stvIdmfOrgNameMst entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * delete the OrgNameMst record by orgId and locale
     *
     * @param orgId
     *        org Id
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByOrgIdByLocale(@Param("orgId") String orgId,
            @Param("locale") String locale);

    /**
     * select the stvIdmfOrgNameMst record by orgId and locale
     *
     * @param orgId
     *        stvIdmfOrgNameMst orgId
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    ImOrgNameMst selectByOrgIdByLocale(@Param("orgId") String orgId,
            @Param("locale") String locale);

    /**
     * select the stvIdmfOrgNameMst record by orgId
     *
     * @param orgId
     *        stvIdmfOrgNameMst orgId
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgNameMst> selectByOrgId(String orgId);

    /**
     * select the OrgNameMst record by org code
     *
     * @param orgCode
     *        org code
     * @return OrgNameMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgNameMst> selectByOrgCode(String orgCode);

    /**
     * select the OrgNameMst record by OrgNameMst properties
     *
     * @param map
     *        OrgNameMst entity
     * @return OrgNameMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgNameMst> selectByOrgNameMst(Map<String, Object> map);

    /**
     * select the orgNameMst record count by orgNameMst information
     *
     * @param map
     *        orgNameMst entity
     * @return orgNameMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByOrgNameMst(Map<String, Object> map);

    /**
     * delete the stvIdmfOrgNameMst record by orgId
     *
     * @param orgId
     *        stvIdmfOrgNameMst orgId
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByOrgId(String orgId);
}