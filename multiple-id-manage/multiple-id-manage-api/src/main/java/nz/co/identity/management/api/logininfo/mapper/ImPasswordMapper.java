
package nz.co.identity.management.api.logininfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import nz.co.identity.management.api.logininfo.entity.ImPassword;

/**
 * <code>StvIdmfPasswordMapper</code>is mapper class for getting password
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImPasswordMapper {

    /**
     * delete the Password record by primary key
     *
     * @param record
     *        Password entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(ImPassword record);

    /**
     * insert a record
     *
     * @param record
     *        Password entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImPassword record);

    /**
     * select the Password record by primary key
     *
     * @param record
     *        Password entity
     * @return Password entity
     * @since Staveware Core Ver.5.3
     */
    ImPassword selectByPrimaryKey(ImPassword record);

    /**
     * update the Password record by primary key
     *
     * @param record
     *        Password entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(ImPassword record);

    /**
     * select the Password record by Password properties
     *
     * @param map
     *        Password entity
     * @return Password entity list
     * @since Staveware Core Ver.5.3
     */
    List<ImPassword> selectPassword(Map<String, Object> map);

    /**
     * select the password record Count by password properties
     *
     * @param map
     *        password entity
     * @return password record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByPassword(Map<String, Object> map);
}