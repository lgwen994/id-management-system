
package nz.co.identity.management.api.roleinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import nz.co.identity.management.api.roleinfo.data.ImRoleAndPosition;
import nz.co.identity.management.api.roleinfo.entity.ImPositionRole;

/**
 * <code>StvIdmfPositionRoleMapper</code>is mapper class for getting
 * positionRole related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImPositionRoleMapper {

    /**
     * insert a record
     *
     * @param record
     *        positionRole entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImPositionRole record);

    /**
     * select the PositionRole record by positionRole information
     *
     * @param map
     *        StvIdmfPositionRole positionRole
     * @return PositionRole entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPositionRole> selectPositionRole(Map<String, Object> map);

    /**
     * select the positionRole record Count by positionRole properties
     *
     * @param map
     *        positionRole entity
     * @return positionRole record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByPositionRole(Map<String, Object> map);

    /**
     * select the PositionRole record by roleId
     *
     * @param roleId
     *        PositionRole roleId
     * @return PositionRole entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPositionRole> selectByRoleId(String roleId);

    /**
     * select the PositionRole record by positionId
     *
     * @param positionId
     *        PositionRole positionId
     * @return PositionRole entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPositionRole> selectByPositionId(String positionId);

    /**
     * select the PositionRole record by positionRoleId
     *
     * @param positionRoleId
     *        PositionRole positionRoleId
     * @return PositionRole entity
     * @since Staveware Core Ver.5.3
     */
    ImPositionRole selectByPrimaryKey(String positionRoleId);

    /**
     * select the max(version) record by positionRoleCode
     *
     * @param positionRoleCode
     *        PositionRole positionRoleCode
     * @return PositionRole entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String positionRoleCode);

    /**
     * select the PositionRole record by positionRoleCode
     *
     * @param positionRoleCode
     *        PositionRole positionRoleCode
     * @return PositionRole entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPositionRole> selectByPositionRoleCode(String positionRoleCode);

    /**
     * elect the StvIdmfRoleAndPosition record by StvIdmfPosition information
     *
     * @param map
     *        StvIdmfPosition entity
     * @return StvIdmfRoleAndPosition entity
     * @since Staveware Core Ver.5.3
     */
    List<ImRoleAndPosition> selectRoleByPosition(Map<String, Object> map);

    /**
     * select the Role record Count by position
     *
     * @param map
     *        StvIdmfPosition entity
     * @return Role record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectRoleCountByPosition(Map<String, Object> map);

    /**
     * select the StvIdmfRoleAndPosition record by information and locale
     *
     * @param map
     *        the Map of StvIdmfPosition entity and Locale
     * @return StvIdmfRoleAndPosition entity
     * @since Staveware Core Ver.5.3
     */
    List

    <ImRoleAndPosition> selectRoleByPositionByLocale(
            Map<String, Object> map);

    /**
     * select the Role record Count by position and locale
     *
     * @param map
     *        the Map of StvIdmfPosition entity and Locale
     * @return Role record Count
     * @since Staveware Core Ver.5.3
     */
    Long selectRoleCountByPositionByLocale(Map<String, Object> map);

    /**
     * update selective the PositionRole record by primary key
     *
     * @param map
     *        positionRole entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the PositionRole record by primary key
     *
     * @param map
     *        positionRole entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);

    /**
     * delete the PositionRole record by primary key and version
     *
     * @param record
     *        StvIdmfPositionRole entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPositionRoleIdByVersion(ImPositionRole record);

    /**
     * delete the PositionRole record by roleId
     *
     * @param roleId
     *        positionRole roleId
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByRoleId(String roleId);

}