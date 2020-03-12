package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImTitleMst;

/**
 * <code>StvIdmfTitleMstMapper</code>is mapper class for getting title related
 * information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImTitleMstMapper {

    /**
     * delete the title record by primary key
     *
     * @param titleId
     *        Title id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(String titleId);

    /**
     * insert a record
     *
     * @param record
     *        Title entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImTitleMst record);

    /**
     * select the Title record by primary key
     *
     * @param titleId
     *        Title id
     * @return Title entity
     * @since Staveware Core Ver.5.3
     */
    ImTitleMst selectByPrimaryKey(String titleId);

    /**
     * delete the Title record by primary key and version
     *
     * @param titleId
     *        title Id
     * @param versionNo
     *        version No
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByTitleIdByVersion(@Param("titleId") String titleId,
            @Param("versionNo") int versionNo);

    /**
     * select the max version by TitleMst code
     *
     * @param titleCode
     *        title code
     * @return RoleMst entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String titleCode);

    /**
     * select the title record by titleId and locale
     *
     * @param titleId
     *        title id
     * @param locale
     *        locale
     * @return TitleMst entity
     * @since Staveware Core Ver.5.3
     */
    ImTitleMst selectByTitleIdByLocale(@Param("titleId") String titleId,
            @Param("locale") String locale);

    /**
     * select the RoleMst/RoleNameMst record by information
     *
     * @param titleCode
     *        title code
     * @param locale
     *        locale
     * @return TitleMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleMst> selectByTitleCodeByLocale(
            @Param("titleCode") String titleCode,
            @Param("locale") String locale);

    /**
     * select the Title record by Title code
     *
     * @param titleCode
     *        Title code
     * @return Title entity
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleMst> selectByTitleCode(String titleCode);

    /**
     * select the Title record by Title properties and locale
     *
     * @param map
     *        the Map of Title entity and Locale
     * @return Title entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleMst> selectByTitleMstByLocale(Map<String, Object> map);

    /**
     * select the titleByLocale record count by titleByLocale information
     *
     * @param map
     *        titleByLocale entity
     * @return titleByLocale record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByTitleMstByLocale(Map<String, Object> map);

    /**
     * select the Title record by Title properties
     *
     * @param map
     *        the Map of Title entity
     * @return Title entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleMst> selectByTitleMst(Map<String, Object> map);

    /**
     * select the title record count by title information
     *
     * @param map
     *        title entity
     * @return title record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByTitleMst(Map<String, Object> map);

    /**
     * select the Title record by companyId
     *
     * @param companyId
     *        company Id
     * @return Title entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImTitleMst> selectByCompanyId(String companyId);

    /**
     * update selective the Title record by primary key
     *
     * @param map
     *        Title entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the title record by primary key
     *
     * @param map
     *        title entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);
}