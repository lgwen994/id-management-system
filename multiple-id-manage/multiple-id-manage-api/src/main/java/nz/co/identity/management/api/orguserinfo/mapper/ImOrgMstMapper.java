package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;

/**
 * <code>StvIdmfOrgMstMapper</code>is mapper class for getting organizational
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImOrgMstMapper {
    /**
     * delete the organization record by primary key
     *
     * @param orgId
     *        organization id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(String orgId);

    /**
     * select the OrgMst record by OrgMst properties and locale
     *
     * @param map
     *        the Map of OrgMst entity and Locale
     * @return OrgMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgMst> selectByOrgMstByLocale(Map<String, Object> map);

    /**
     * select the orgMstByLocale record count by orgMstByLocale information
     *
     * @param map
     *        orgMst entity
     * @return orgMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByOrgMstByLocale(Map<String, Object> map);

    /**
     * select the OrgMst record by OrgMst properties
     *
     * @param map
     *        OrgMst entity
     * @return OrgMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgMst> selectByOrgMst(Map<String, Object> map);

    /**
     * select the orgMst record count by orgMst information
     *
     * @param map
     *        orgMst entity
     * @return orgMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByOrgMst(Map<String, Object> map);

    /**
     * select the max version by OrgMst code
     *
     * @param orgCode
     *        org code
     * @return OrgMst entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String orgCode);

    /**
     * delete the OrgMst record by orgId and locale
     *
     * @param orgId
     *        org id
     * @param versionNo
     *        version No
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByOrgIdByVersion(@Param("orgId") String orgId,
            @Param("versionNo") int versionNo);

    /**
     * select the OrgMst record by orgId and locale
     *
     * @param orgId
     *        org id
     * @param locale
     *        orgName locale
     * @return OrgMst entity
     * @since Staveware Core Ver.5.3
     */
    ImOrgMst selectByOrgIdByLocale(@Param("orgId") String orgId,
            @Param("locale") String locale);

    /**
     * select the OrgMst record by code and locale
     *
     * @param orgCode
     *        org id
     * @param locale
     *        orgName locale
     * @return OrgMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgMst> selectByOrgCodeByLocale(
            @Param("orgCode") String orgCode, @Param("locale") String locale);

    /**
     * insert a record
     *
     * @param record
     *        organization entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImOrgMst record);

    /**
     * select the organization record by primary key
     *
     * @param orgId
     *        organization id
     * @return organization entity
     * @since Staveware Core Ver.5.3
     */
    ImOrgMst selectByPrimaryKey(String orgId);

    /**
     * select the organization record by organization code
     *
     * @param orgCode
     *        organization code
     * @return organization entity
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgMst> selectByOrgCode(String orgCode);

    /**
     * select the organization record by companyId
     *
     * @param companyId
     *        company Id
     * @return organization entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImOrgMst> selectByCompanyId(String companyId);

    /**
     * update selective the organization record by primary key
     *
     * @param map
     *        organization entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the organization record by primary key
     *
     * @param map
     *        organization entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);
}