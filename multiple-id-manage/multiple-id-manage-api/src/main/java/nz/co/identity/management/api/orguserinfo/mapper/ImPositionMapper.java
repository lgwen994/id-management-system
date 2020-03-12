package nz.co.identity.management.api.orguserinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import nz.co.identity.management.api.orguserinfo.entity.ImPosition;

/**
 * <code>StvIdmfPositionMapper</code>is mapper class for getting position
 * related information.<br>
 *
 * @since Staveware Core Ver.5.3
 *
 */
@Mapper
public interface ImPositionMapper {

    /**
     * delete the stvIdmfPosition record by primary key
     *
     * @param positionId
     *        position id
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPrimaryKey(String positionId);

    /**
     * insert a record
     *
     * @param record
     *        position entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int insert(ImPosition record);

    /**
     * delete the position record by positionId and versionNo
     *
     * @param positionId
     *        position id
     * @param versionNo
     *        position versionNo
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int deleteByPositionIdByVersion(@Param("positionId") String positionId,
            @Param("versionNo") int versionNo);

    /**
     * select the position record by primary key
     *
     * @param positionId
     *        position positionId
     * @return position entity
     * @since Staveware Core Ver.5.3
     */
    ImPosition selectByPrimaryKey(String positionId);

    /**
     * select the position record by userId
     *
     * @param userId
     *        userId
     * @return userName entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPosition> selectByUserId(String userId);

    /**
     * select the position record by titleId
     *
     * @param titleId
     *        titleId
     * @return position entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPosition> selectByTitleId(String titleId);

    /**
     * select the Position record by orgId
     *
     * @param orgId
     *        orgId
     * @return Position entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPosition> selectByOrgId(String orgId);

    /**
     * select the Position record by positionCode
     *
     * @param positionCode
     *        positionCode
     * @return Position entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPosition> selectByPositionCode(String positionCode);

    /**
     * select the MaxVersion record by positionCode
     *
     * @param positionCode
     *        positionCode
     * @return Position entity
     * @since Staveware Core Ver.5.3
     */
    int selectMaxVersion(String positionCode);

    /**
     * select the position record by information
     *
     * @param map
     *        position information
     * @return position entity
     * @since Staveware Core Ver.5.3
     */
    List<ImPosition> selectByPosition(Map<String, Object> map);

    /**
     * select the position record count by position information
     *
     * @param map
     *        position entity
     * @return position record count
     * @since Staveware Core Ver.5.3
     */
    Long selectCountByPosition(Map<String, Object> map);

    /**
     * select the position record by userId and titleId and orgId
     *
     * @param record
     *        position information
     * @return position entity
     * @since Staveware Core Ver.5.3
     */
    ImPosition selectPositionByOther(ImPosition record);

    /**
     * update selective the position record by primary key
     *
     * @param map
     *        position entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * update the position record by primary key
     *
     * @param map
     *        position entity
     * @return flag result(0:false, 1:true)
     * @since Staveware Core Ver.5.3
     */
    int updateByPrimaryKey(Map<String, Object> map);
}