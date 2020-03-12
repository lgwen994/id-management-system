
package nz.co.identity.management.api.logininfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import nz.co.identity.management.api.logininfo.entity.ImLoginControlInfo;

/**
 * <code>StvIdmfLoginControlInfoMapper</code>is mapper class for getting
 * logincontrolinfo related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImLoginControlInfoMapper {

    /**
     * delete the LoginControlInfo record by primary key
     *
     * @param record
     *        LoginControlInfo entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(ImLoginControlInfo record);

    /**
     * insert a record
     *
     * @param record
     *        LoginControlInfo entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImLoginControlInfo record);

    /**
     * select the LoginControlInfo record by primary key
     *
     * @param record
     *        LoginControlInfo entity
     * @return LoginControlInfo entity
     * @since Staveware Core Ver.5.3
     */
    ImLoginControlInfo selectByPrimaryKey(ImLoginControlInfo record);

    /**
     * select the LoginControlInfo record by LoginControlInfo properties
     *
     * @param map
     *        LoginControlInfo entity
     * @return LoginControlInfo entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImLoginControlInfo> selectByLoginControlInfo(
            Map<String, Object> map);
    
    /**
     * select the loginControlInfo record Count by loginControlInfo properties
     *
     * @param map
     *        loginControlInfo entity
     * @return loginControlInfo record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByLoginControlInfo(Map<String, Object> map);

    /**
     * update the LoginControlInfo record by primary key
     *
     * @param record
     *        LoginControlInfo entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(ImLoginControlInfo record);

}