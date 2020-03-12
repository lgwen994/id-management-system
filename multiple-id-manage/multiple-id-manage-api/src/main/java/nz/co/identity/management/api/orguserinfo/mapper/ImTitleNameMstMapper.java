package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImTitleNameMst;

/**
 * <code>StvIdmfTitleNameMstMapper</code>is mapper class for getting titleName
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImTitleNameMstMapper {

    /**
     * insert a titleNameMst
     *
     * @param titleNameMst
     *        titleName entity
     * @return result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImTitleNameMst titleNameMst);

    /**
     * select the titleName by primary key
     *
     * @param titleNameId
     *        titleName Id
     * @return titleName entity
     * @since Staveware Core Ver.5.3
     */
    ImTitleNameMst selectByPrimaryKey(String titleNameId);

    /**
     * update selective the titleName titleNameMst by primary key
     *
     * @param map
     *        titleName entity
     * @return flag result(0:false, i:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the titleName titleNameMst by primary key
     *
     * @param map
     *        titleName entity
     * @return flag result(0:false, i:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * select the titleName by titleId and locale
     *
     * @param titleId
     *        title Id
     * @param locale
     *        locale
     * @return flag result(0:false, i:true)
     * @since Staveware Core Ver.5.3
     */
    ImTitleNameMst selectByTitleIdByLocale(
            @Param("titleId") String titleId, @Param("locale") String locale);

    /**
     * select the titleName by titleId
     *
     * @param titleId
     *        title Id
     * @return flag result(0:false, i:true)
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleNameMst> selectByTitleId(String titleId);

    /**
     * select the TitleNameMst record by title code
     *
     * @param titleCode
     *        title code
     * @return TitleNameMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleNameMst> selectByTitleCode(String titleCode);

    /**
     * select the TitleNameMst record by TitleNameMst properties
     *
     * @param map
     *        TitleNameMst entity
     * @return TitleNameMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleNameMst> selectByTitleNameMst(Map<String, Object> map);

    /**
     * select the titleNameMst record count by titleNameMst information
     *
     * @param map
     *        titleNameMst entity
     * @return titleNameMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByTitleNameMst(Map<String, Object> map);

    /**
     * delete the titleNameMst by titleId
     *
     * @param titleId
     *        titleId
     * @return flag result(0:false, i:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByTitleId(String titleId);

    /**
     * delete the titleNameMst record by titleId and locale
     *
     * @param titleId
     *        title Id
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByTitleIdByLocale(@Param("titleId") String titleId,
            @Param("locale") String locale);
}