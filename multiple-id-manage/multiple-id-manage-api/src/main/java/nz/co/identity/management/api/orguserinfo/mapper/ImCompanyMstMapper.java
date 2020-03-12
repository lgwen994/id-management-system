package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImCompanyMst;

/**
 * <code>StvIdmfCompanyMstMapper</code>is mapper class for getting company
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImCompanyMstMapper {

    /**
     * select the company record by primary key
     *
     * @param companyId
     *        company id
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    ImCompanyMst selectByPrimaryKey(String companyId);

    /**
     * select the max version by company code
     *
     * @param companyCode
     *        company code
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String companyCode);

    /**
     * select the company record by companyId and locale
     *
     * @param companyId
     *        company id
     * @param locale
     *        locale
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    ImCompanyMst selectByCompanyIdByLocale(
            @Param("companyId") String companyId,
            @Param("locale") String locale);

    /**
     * select the company record by company code
     *
     * @param companyCode
     *        company code
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyMst> selectByCompanyCode(String companyCode);

    /**
     * select the company record by company code and locale
     *
     * @param companyCode
     *        company code
     * @param locale
     *        locale
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyMst> selectByCompanyCodeByLocale(
            @Param("companyCode") String companyCode,
            @Param("locale") String locale);

    /**
     * select the company record by company information
     *
     * @param map
     *        company information
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyMst> selectByCompanyMst(Map<String, Object> map);

    /**
     * select the companyMst record count by companyMst information
     *
     * @param map
     *        companyMst entity
     * @return companyMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByCompanyMst(Map<String, Object> map);

    /**
     * select the company record by company information and locale
     *
     * @param map
     *        company information
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyMst> selectByCompanyMstByLocale(Map<String, Object> map);

    /**
     * select the companyMstByLocale record count by companyMstByLocale information
     *
     * @param map
     *        companyMstByLocale entity
     * @return companyMstByLocale record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByCompanyMstByLocale(Map<String, Object> map);

    /**
     * insert a record
     *
     * @param record
     *        company entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImCompanyMst record);

    /**
     * update selective the company record by primary key
     *
     * @param map
     *        company entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the company record by primary key
     *
     * @param map
     *        company entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * delete the company record by primary key
     *
     * @param companyId
     *        company id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(String companyId);

    /**
     * delete the company record by companyId and version
     *
     * @param companyId
     *        company id
     * @param versionNo
     *        version No
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    int deleteByCompanyIdByVersion(@Param("companyId") String companyId,
            @Param("versionNo") int versionNo);
}