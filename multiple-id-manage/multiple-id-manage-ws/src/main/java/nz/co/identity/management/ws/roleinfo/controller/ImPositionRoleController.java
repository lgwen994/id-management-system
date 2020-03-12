
package nz.co.identity.management.ws.roleinfo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nz.co.identity.management.api.common.exception.ImRuntimeException;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.orguserinfo.entity.ImPosition;
import nz.co.identity.management.api.roleinfo.data.ImRoleAndPosition;
import nz.co.identity.management.api.roleinfo.entity.ImPositionRole;
import nz.co.identity.management.api.roleinfo.service.ImPositionRoleService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of positionRole information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_position_roles")
public class ImPositionRoleController {

    /**
     * the statement of positionRole service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImPositionRoleService positionroleService;

    /**
     * register positionRole information.<br>
     *
     * @param positionRole
     *        positionRole entity
     * @return positionRole information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImPositionRole registerPositionRole(
            @RequestBody ImPositionRole positionRole) {

        return positionroleService.registerPositionRole(positionRole);
    }

    /**
     * register positionRole information in one time.<br>
     *
     *
     * @param positionRoleList
     *        positionRole information list
     * @return registered positionRole information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPositionRole> registerBulkPositionRole(
            @RequestBody List<ImPositionRole> positionRoleList) {

        List<ImPositionRole> iPosList = new ArrayList<ImPositionRole>();
        ImPositionRole retPositionRole = null;

        for (int i = 0; i < positionRoleList.size(); i++) {
            retPositionRole = positionroleService
                    .registerPositionRole(positionRoleList.get(i));
            iPosList.add(retPositionRole);
        }

        return iPosList;
    }

    /**
     * register positionRole information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered positionRole information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPositionRole> registerPositionRoleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPositionRole> iPosList = new ArrayList<ImPositionRole>();
        List<ImPositionRole> positionRoleList = null;
        try {
            positionRoleList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPositionRole.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        ImPositionRole retPositionRole = null;

        for (int i = 0; i < positionRoleList.size(); i++) {
            retPositionRole = positionroleService
                    .registerPositionRole(positionRoleList.get(i));
            iPosList.add(retPositionRole);
        }
        return iPosList;
    }

    /**
     * update positionRole information by positionRole id.<br>
     *
     * @param positionRole
     *        positionRole entity
     * @return updated positionRole information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImPositionRole updatePositionRole(
            @RequestBody ImPositionRole positionRole) {
        return positionroleService.updatePositionRole(positionRole);
    }

    /**
     * update positionRole informations by positionRole id list in one time.<br>
     *
     * @param positionRoleList
     *        the positionRole id list
     * @return updated positionRole information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImPositionRole> updateBulkPositionRole(
            @RequestBody List<ImPositionRole> positionRoleList) {

        List<ImPositionRole> iPosList = new ArrayList<ImPositionRole>();
        ImPositionRole retPositionRole = null;

        for (int i = 0; i < positionRoleList.size(); i++) {
            retPositionRole = positionroleService
                    .updatePositionRole(positionRoleList.get(i));
            iPosList.add(retPositionRole);
        }

        return iPosList;
    }

    /**
     * update positionRole information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated positionRole information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImPositionRole> updatePositionRoleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImPositionRole> iPosList = new ArrayList<ImPositionRole>();
        List<ImPositionRole> positionRoleList = null;
        try {
            positionRoleList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPositionRole.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImPositionRole retPositionRole = null;

        for (int i = 0; i < positionRoleList.size(); i++) {
            retPositionRole = positionroleService
                    .updatePositionRole(positionRoleList.get(i));
            iPosList.add(retPositionRole);
        }
        return iPosList;
    }

    /**
     * delete positionRole information by positionRole id.<br>
     *
     * @param positionRoleId
     *        the positionRole id
     * @param version
     *        version
     * @return deleted positionRole information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{positionRoleId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deletePositionRole(@PathVariable String positionRoleId,
            @PathVariable String version) {
        ImPositionRole positionRole = new ImPositionRole();
        positionRole.setPositionRoleId(positionRoleId);
        positionRole.setVersionNo(Integer.valueOf(version));
        return positionroleService.deletePositionRole(positionRole);
    }

    /**
     * delete positionRole informations by positionRole id list in one time.<br>
     *
     * @param positionRoles
     *        the PositionRole list
     * @return deleted positionRole information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkPositionRole(
            @RequestBody List<ImPositionRole> positionRoles) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < positionRoles.size(); i++) {
            resultList.add(positionroleService
                    .deletePositionRole(positionRoles.get(i)));
        }
        return resultList;
    }

    /**
     * delete positionRole information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted positionRole information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deletePositionRoleByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        List<ImPositionRole> positionRoleList = null;
        try {
            positionRoleList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImPositionRole.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        boolean delResult = false;
        for (int i = 0; i < positionRoleList.size(); i++) {
            delResult = positionroleService
                    .deletePositionRole(positionRoleList.get(i));
            resultList.add(delResult);
        }
        return resultList;
    }

    /**
     * find positionRole information by positionRoleId
     *
     * @param positionRoleId
     *        positionRole id
     * @return selected PositionRole information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{positionRoleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImPositionRole findPositionRole(
            @PathVariable String positionRoleId) {

        return positionroleService.getPositionRole(positionRoleId);
    }

    /**
     * find PositionRole information by PositionRoleCode
     *
     * @param positionRoleCode
     *        positionRole code
     * @return selected PositionRole information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{positionRoleCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImPositionRole findPositionRoleByCode(
            @PathVariable String positionRoleCode) {

        return positionroleService
                .getPositionRoleByPositionRoleCode(positionRoleCode);
    }

    /**
     * search positionRole information by positionRole
     *
     * @param positionRole
     *        positionRole entity
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected positionRole information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImPositionRole> searchPositionRole(
            @RequestBody ImPositionRole positionRole,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return positionroleService.searchPositionRole(positionRole, pageNo,
                pageSize, sort);
    }

    /**
     * search positionRole information by roles
     *
     * @param position
     *        position entity
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected positionRole information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/roles/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImRoleAndPosition> searchRole(
            @RequestBody ImPosition position,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return positionroleService.searchRole(position, pageNo, pageSize, sort);
    }

}