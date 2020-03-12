package nz.co.identity.management.api.orguserinfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleNameMst;

/**
 * according to the titleName form of CRUD operations, return a valid data.<br>
 * titleName's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImTitleNameMstService {

    /**
     * register the titleName
     *
     * @param record
     *        titleName entity
     * @return result of titleName
     * @since Staveware Core Ver.5.3
     */
    ImTitleNameMst registerTitleName(ImTitleNameMst record);

    /**
     * update the titleName by titleName id
     *
     * @param record
     *        titleName entity
     * @return result of titleName
     * @since Staveware Core Ver.5.3
     */
    ImTitleNameMst updateTitleName(ImTitleNameMst record);

    /**
     * delete the titleName by titleName id
     *
     * @param titleNameMst
     *        titleName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteTitleName(ImTitleNameMst titleNameMst);

    /**
     * delete force the titleName by titleName id
     *
     * @param titleNameMst
     *        titleName entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceTitleName(ImTitleNameMst titleNameMst);

    /**
     * get titleName by title id
     *
     * @param titleId
     *        titleId
     * @return result of titleName
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleNameMst> getTitleName(String titleId);

    /**
     * get titleName by titleName id
     *
     * @param titleNameId
     *        titleNameId
     * @return result of titleName
     * @since Staveware Core Ver.5.3
     */
    ImTitleNameMst getTitleNameById(String titleNameId);

    /**
     * get titleMst by title code
     *
     * @param titleCode
     *        titleMst code
     * @return result of titleNameMst
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleNameMst> getTitleNameByTitleCode(String titleCode);

    /**
     * paging search the titleNameMst by titleNameMst properties
     *
     * @param titleNameMst
     *        titleNameMst entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of titleNameMst list
     * @since Staveware Core Ver.5.3
     */
    Page<ImTitleNameMst> searchTitleName(ImTitleNameMst titleNameMst,
            Integer pageNum, Integer pageSize, List<String> sort);

}