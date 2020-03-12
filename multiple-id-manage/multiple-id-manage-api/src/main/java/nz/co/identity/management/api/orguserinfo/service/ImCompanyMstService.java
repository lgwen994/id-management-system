package nz.co.identity.management.api.orguserinfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyMst;

/**
 * according to the company form of CRUD operations, return a valid data.<br>
 * company's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImCompanyMstService {
    /**
     * register the company
     *
     * @param record
     *        company entity
     * @return result of company
     * @since Staveware Core Ver.5.3
     */
    ImCompanyMst registerCompany(ImCompanyMst record);

    /**
     * update the company by company id
     *
     * @param record
     *        company entity
     * @return result of company
     * @since Staveware Core Ver.5.3
     */
    ImCompanyMst updateCompany(ImCompanyMst record);

    /**
     * delete the company by company id
     *
     * @param companyMst
     *        company entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteCompany(ImCompanyMst companyMst);

    /**
     * delete Force the company by company id
     *
     * @param companyMst
     *        company entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceCompany(ImCompanyMst companyMst);

    /**
     * get company by company id
     *
     * @param companyId
     *        company id
     * @return result of company
     * @since Staveware Core Ver.5.3
     */
    ImCompanyMst getCompany(String companyId);

    /**
     * get company by company code
     *
     * @param companyCode
     *        company code
     * @return result of company
     * @since Staveware Core Ver.5.3
     */
    ImCompanyMst getCompanyByCode(String companyCode);

    /**
     * paging search the company by company properties
     *
     * @param companyMst
     *        company entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of company list
     * @since Staveware Core Ver.5.3
     */
    Page<ImCompanyMst> searchCompany(ImCompanyMst companyMst,
            Integer pageNum, Integer pageSize, List<String> sort);

}