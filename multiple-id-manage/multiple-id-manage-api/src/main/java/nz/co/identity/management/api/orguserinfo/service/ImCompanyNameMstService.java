package nz.co.identity.management.api.orguserinfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyNameMst;

/**
 * according to the companyName form of CRUD operations, return a valid
 * data.<br>
 * companyName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImCompanyNameMstService {

    /**
     * register the companyName
     *
     * @param companyNameMst
     *        companyName entity
     * @return result of companyName
     * @since Staveware Core Ver.5.3
     */
    ImCompanyNameMst registerCompanyName(
            ImCompanyNameMst companyNameMst);

    /**
     * update the companyName by companyName id
     *
     * @param companyNameMst
     *        companyName entity
     * @return result of companyName
     * @since Staveware Core Ver.5.3
     */
    ImCompanyNameMst updateCompanyName(
            ImCompanyNameMst companyNameMst);

    /**
     * delete the companyName by companyName id
     *
     * @param companyName
     *        companyName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteCompanyName(ImCompanyNameMst companyName);

    /**
     * delete the companyName by companyName id
     *
     * @param companyName
     *        companyName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceCompanyName(ImCompanyNameMst companyName);

    /**
     * get companyName by company id
     *
     * @param companyId
     *        company id
     * @return result of companyName
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyNameMst> getCompanyName(String companyId);

    /**
     * get companyName by company id
     *
     * @param companyNameId
     *        company name id
     * @return result of companyName
     * @since Staveware Core Ver.5.3
     */
    ImCompanyNameMst getCompanyNameById(String companyNameId);

    /**
     * get companyName by company code
     *
     * @param companyCode
     *        company code
     * @return result of companyName
     * @since Staveware Core Ver.5.3
     */
    List<ImCompanyNameMst> getCompanyNameByCompanyCode(String companyCode);

    /**
     * paging search the companyName by companyName properties
     *
     * @param companyNameMst
     *        companyNameMst entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of roleMst list
     * @since Staveware Core Ver.5.3
     */
    Page<ImCompanyNameMst> searchCompanyName(
            ImCompanyNameMst companyNameMst, Integer pageNum,
            Integer pageSize, List<String> sort);

}