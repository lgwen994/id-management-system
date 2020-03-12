package nz.co.identity.management.api.orguserinfo.service;
import java.util.List;

import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImTitleMst;

/**
 * according to the title form of CRUD operations, return a valid data.<br>
 * title's Service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
public interface ImTitleMstService {

    /**
     * register the title
     *
     * @param record
     *        title entity
     * @return result of title
     * @since Staveware Core Ver.5.3
     */
    ImTitleMst registerTitle(ImTitleMst record);

    /**
     * update the title by title id
     *
     * @param record
     *        title entity
     * @return result of title
     * @since Staveware Core Ver.5.3
     */
    ImTitleMst updateTitle(ImTitleMst record);

    /**
     * delete the title by title id
     *
     * @param titleMst
     *        title entity
     * @return flag result(false, true)
     * @since Staveware Core Ver.5.3
     */
    boolean deleteTitle(ImTitleMst titleMst);

    /**
     * delete force the title by title id
     *
     * @param titleMst
     *        title entity
     * @return result of title
     * @since Staveware Core Ver.5.3
     */
    boolean deleteForceTitle(ImTitleMst titleMst);

    /**
     * get title by title id
     *
     * @param titleId
     *        title id
     * @return result of title
     * @since Staveware Core Ver.5.3
     */
    ImTitleMst getTitle(String titleId);

    /**
     * get title by title code
     *
     * @param titleCode
     *        title code
     * @return result of title
     * @since Staveware Core Ver.5.3
     */
    ImTitleMst getTitleByTitleCode(String titleCode);

    /**
     * paging search the title by title properties
     *
     * @param titleMst
     *        title entity
     * @param pageNum
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return result of title list
     * @since Staveware Core Ver.5.3
     */
    Page<ImTitleMst> searchTitle(ImTitleMst titleMst, Integer pageNum,
            Integer pageSize, List<String> sort);

}