package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;

/**
 * <code>StvIdmfUserNameMstMapper</code>is mapper class for getting userName
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImUserNameMstMapper {

    /**
     * delete the userName record by userId
     *
     * @param userId
     *        user id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByUserId(String userId);

    /**
     * delete the userNameMst record by userId and locale
     *
     * @param userId
     *        user Id
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByUserIdByLocale(@Param("userId") String userId,
            @Param("locale") String locale);

    /**
     * insert a record
     *
     * @param record
     *        userName entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImUserNameMst record);

    /**
     * select the userName record by primary key
     *
     * @param userNameId
     *        userName userNameId
     * @return userName entity
     * @since Staveware Core Ver.5.3
     */
    ImUserNameMst selectByPrimaryKey(String userNameId);

    /**
     * select the userName by userId and locale
     *
     * @param userId
     *        user Id
     * @param locale
     *        locale
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    ImUserNameMst selectByUserIdByLocale(@Param("userId") String userId,
            @Param("locale") String locale);

    /**
     * select the userName record by userId
     *
     * @param userId
     *        userId
     * @return userName entity
     * @since Staveware Core Ver.5.3
     */
    List<ImUserNameMst> selectByUserId(String userId);

    /**
     * select the userNameMst record by user code
     *
     * @param userCode
     *        user code
     * @return userNameMst entity
     * @since Staveware Core Ver.5.3
     */
    List<ImUserNameMst> selectByUserCode(String userCode);

    /**
     * select the userNameMst record by userNameMst properties
     *
     * @param map
     *        userNameMst entity
     * @return userNameMst entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImUserNameMst> selectByUserNameMst(Map<String, Object> map);

    /**
     * select the userNameMst record count by userNameMst information
     *
     * @param map
     *        userNameMst entity
     * @return userNameMst record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByUserNameMst(Map<String, Object> map);

    /**
     * update selective the userName record by primary key
     *
     * @param map
     *        userName entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the userName record by primary key
     *
     * @param map
     *        userName entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);
}