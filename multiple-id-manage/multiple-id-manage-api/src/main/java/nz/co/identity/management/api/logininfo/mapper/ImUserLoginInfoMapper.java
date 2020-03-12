
package nz.co.identity.management.api.logininfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;

/**
 * <code>StvIdmfUserLoginInfoMapper</code>is mapper class for getting
 * userlogininfo related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImUserLoginInfoMapper {

    /**
     * delete the UserLoginInfo record by primary key
     *
     * @param userLoginInfoId
     *        userLoginInfo id
     * @param versionNo
     *        version no
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByUserLoginInfoIdByVersion(
            @Param("userLoginInfoId") String userLoginInfoId,
            @Param("versionNo") int versionNo);

    /**
     * insert a record
     *
     * @param record
     *        UserLoginInfo entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImUserLoginInfo record);

    /**
     * select the UserLoginInfo record by primary key
     *
     * @param userLoginInfoId
     *        UserLoginInfo id
     * @return UserLoginInfo entity
     * @since Staveware Core Ver.5.3
     */
    ImUserLoginInfo selectByPrimaryKey(String userLoginInfoId);

    /**
     * select the UserLoginInfo record by UserLoginInfo code
     *
     * @param userLoginInfoCode
     *        UserLoginInfo code
     * @return UserLoginInfo entity
     * @since Staveware Core Ver.5.3
     */
    List<ImUserLoginInfo> selectByUserLoginInfoCode(
            String userLoginInfoCode);

    /**
     * update selective the UserLoginInfo record by primary key
     *
     * @param map
     *        UserLoginInfo entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the UserLoginInfo record by primary key
     *
     * @param map
     *        UserLoginInfo entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * select the MaxVersion record by UserLoginInfo code
     *
     * @param userLoginInfoCode
     *        UserLoginInfo code
     * @return Version
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String userLoginInfoCode);

    /**
     * select the UserLoginInfo record by loginId and company code
     * 
     * @param loginId
     *        login id
     * @param companyCode
     *        company code
     * @return UserLoginInfo entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImUserLoginInfo> selectByLoginIdAndCompanyCode(
            @Param("loginId") String loginId,
            @Param("companyCode") String companyCode);

    /**
     * select the UserLoginInfo record by user id
     *
     * @param userId
     *        user id
     * @return UserLoginInfo entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImUserLoginInfo> selectByUserId(String userId);

    /**
     * delete the UserLoginInfo record by loginId and company code
     *
     * @param loginId
     *        login id
     * @param companyCode
     *        company code
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByLoginIdAndCompanyCode(@Param("loginId") String loginId,
            @Param("companyCode") String companyCode);

    /**
     * select the UserLoginInfo record by UserLoginInfo properties
     * 
     * @param map
     *        UserLoginInfo entity
     * @return UserLoginInfo entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImUserLoginInfo> selectUserLoginInfo(Map<String, Object> map);
    
    /**
     * select the userLoginInfo record Count by userLoginInfo properties
     *
     * @param map
     *        userLoginInfo entity
     * @return userLoginInfo record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByUserLoginInfo(Map<String, Object> map);
}