package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImCompanyNameMst;

/**
 * <code>StvIdmfCompanyNameMstMapper</code>is mapper class for getting
 * companyName related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImCompanyNameMstMapper {

    /**
     * select the companyName record by primary key
     *
     * @param companyNameId
     *        companyName id
     * @return company entity
     * @since Staveware Core Ver.5.3
     */
    ImCompanyNameMst selectByPrimaryKey(String companyNameId);

    /**
     * select the stvIdmfCompanyNameMst record by orgId
     *
     * @param companyId
     *        CompanyNameMst companyId
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyNameMst> selectByCompanyId(String companyId);

    /**
     * select the stvIdmfCompanyNameMst record by companyId and locale
     *
     * @param companyId
     *        companyId
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    ImCompanyNameMst selectByCompanyIdByLocale(
            @Param("companyId") String companyId,
            @Param("locale") String locale);

    /**
     * select the CompanyNameMst record by company name code
     *
     * @param companyCode
     *        company code
     * @return CompanyNameMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyNameMst> selectByCompanyCode(String companyCode);

    /**
     * select the CompanyNameMst record by CompanyNameMst properties
     *
     * @param map
     *        CompanyNameMst entity
     * @return CompanyNameMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyNameMst> selectByCompanyNameMst(Map<String, Object> map);

    /**
     * select the companyNameMst record count by companyNameMst information
     *
     * @param map
     *        companyNameMst entity
     * @return companyNameMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByCompanyNameMst(Map<String, Object> map);

    /**
     * insert a record
     *
     * @param record
     *        companyName entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImCompanyNameMst record);

    /**
     * update selective the companyName record by primary key
     *
     * @param map
     *        companyName entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the companyName record by primary key
     *
     * @param map
     *        companyName entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * delete the stvIdmfCompanyNameMst record by companyId
     *
     * @param companyId
     *        CompanyNameMst companyId
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByCompanyId(String companyId);

    /**
     * * delete the stvIdmfCompanyNameMst record by companyId and locale
     *
     * @param companyId
     *        companyId
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     * @since Staveware Core Ver.5.3
     */
    int deleteByCompanyIdByLocale(@Param("companyId") String companyId,
            @Param("locale") String locale);

}