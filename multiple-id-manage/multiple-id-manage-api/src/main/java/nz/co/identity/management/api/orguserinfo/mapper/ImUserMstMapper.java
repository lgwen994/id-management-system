package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImUserMst;

/**
 * <code>StvIdmfUserMstMapper</code>is mapper class for getting user related
 * information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImUserMstMapper {

    /**
     * delete the user record by primary key
     *
     * @param userId
     *        user id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(String userId);

    /**
     * delete the user record by user id and version
     *
     * @param userId
     *        user id
     * @param versionNo
     *        version No
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByUserIdByVersion(@Param("userId") String userId,
            @Param("versionNo") int versionNo);

    /**
     * insert a record
     *
     * @param record
     *        user entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImUserMst record);

    /**
     * select the user record by primary key
     *
     * @param userId
     *        user id
     * @return user entity
     * @since Staveware Core Ver.5.3
     */
    ImUserMst selectByPrimaryKey(String userId);

    /**
     * select the user record by user code
     *
     * @param userCode
     *        user code
     * @return user entity
     * @since Staveware Core Ver.5.3
     */
    List<ImUserMst> selectByUserCode(String userCode);

    /**
     * select the UserMst record by userCode and locale
     *
     * @param userCode
     *        user code
     * @param locale
     *        locale
     * @return UserMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImUserMst> selectByUserCodeByLocale(
            @Param("userCode") String userCode, @Param("locale") String locale);

    /**
     * select the UserMst record by User properties
     *
     * @param map
     *        the Map of User entity and Locale
     * @return User entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImUserMst> selectByUserMstByLocale(Map<String, Object> map);

    /**
     * select the userMstByLocale record count by userMstByLocale information
     *
     * @param map
     *        userMstByLocale entity
     * @return userMstByLocale record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByUserMstByLocale(Map<String, Object> map);

    /**
     * select the UserMst record by User properties
     *
     * @param map
     *        the Map of User entity and Locale
     * @return User entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImUserMst> selectByUserMst(Map<String, Object> map);

    /**
     * select the userMst record count by userMst information
     *
     * @param map
     *        userMst entity
     * @return userMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByUserMst(Map<String, Object> map);

    /**
     * select the max version by user code
     *
     * @param userCode
     *        user code
     * @return UserMst entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String userCode);

    /**
     * select the user record by primary key and locale
     *
     * @param userId
     *        user id
     * @param locale
     *        locale
     * @return UserMst entity
     * @since Staveware Core Ver.5.3
     */
    ImUserMst selectByUserIdByLocale(@Param("userId") String userId,
            @Param("locale") String locale);

    /**
     * update selective the user record by primary key
     *
     * @param map
     *        user entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the user record by primary key
     *
     * @param map
     *        user entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);
}