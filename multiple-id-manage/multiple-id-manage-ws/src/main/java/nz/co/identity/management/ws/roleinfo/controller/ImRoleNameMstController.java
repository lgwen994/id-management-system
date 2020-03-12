
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
import nz.co.identity.management.api.roleinfo.entity.ImRoleNameMst;
import nz.co.identity.management.api.roleinfo.service.ImRoleNameMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * The controller class of roleName information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_role_names")
public class ImRoleNameMstController {

    /**
     * The statement of roleName service's interface S
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImRoleNameMstService roleNameService;

    /**
     * register roleName information.<br>
     *
     * @param stvIdmfRoleNameMst
     *        roleName entity
     * @return roleName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImRoleNameMst registerRoleName(
            @RequestBody ImRoleNameMst stvIdmfRoleNameMst) {
        return roleNameService.registerRoleName(stvIdmfRoleNameMst);
    }

    /**
     * register roleName information in one time.<br>
     *
     *
     * @param roleNames
     *        roleName information list
     * @return registered roleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImRoleNameMst> registerBulkRoleName(
            @RequestBody List<ImRoleNameMst> roleNames) {

        List<ImRoleNameMst> iPosList = new ArrayList<ImRoleNameMst>();
        ImRoleNameMst roleNameMst = null;

        for (int i = 0; i < roleNames.size(); i++) {
            roleNameMst = roleNameService.registerRoleName(roleNames.get(i));
            iPosList.add(roleNameMst);
        }
        return iPosList;
    }

    /**
     * register roleName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered roleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImRoleNameMst> registerRoleNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImRoleNameMst> iPosList = new ArrayList<ImRoleNameMst>();
        List<ImRoleNameMst> roleNames = null;
        try {
            roleNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImRoleNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        ImRoleNameMst roleMst = null;

        for (int i = 0; i < roleNames.size(); i++) {
            roleMst = roleNameService.registerRoleName(roleNames.get(i));
            iPosList.add(roleMst);
        }
        return iPosList;
    }

    /**
     * update roleName information by roleName id.<br>
     *
     * @param stvIdmfRoleNameMst
     *        stvIdmfRoleNameMst
     * @return updated roleName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImRoleNameMst updateRoleName(
            @RequestBody ImRoleNameMst stvIdmfRoleNameMst) {

        return roleNameService.updateRoleName(stvIdmfRoleNameMst);
    }

    /**
     * update role informations by roleName id list in one time.<br>
     *
     * @param roleNames
     *        the roleName id list
     * @return updated roleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImRoleNameMst> updateBulkRoleName(
            @RequestBody List<ImRoleNameMst> roleNames) {

        List<ImRoleNameMst> iPosList = new ArrayList<ImRoleNameMst>();
        ImRoleNameMst roleNameMst = null;

        for (int i = 0; i < roleNames.size(); i++) {
            roleNameMst = roleNameService.updateRoleName(roleNames.get(i));
            iPosList.add(roleNameMst);
        }

        return iPosList;
    }

    /**
     * update roleName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated roleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImRoleNameMst> updateRoleNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImRoleNameMst> iPosList = new ArrayList<ImRoleNameMst>();
        List<ImRoleNameMst> roleNames = null;
        try {
            roleNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImRoleNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImRoleNameMst roleNameMst = null;

        for (int i = 0; i < roleNames.size(); i++) {
            roleNameMst = roleNameService.updateRoleName(roleNames.get(i));
            iPosList.add(roleNameMst);
        }
        return iPosList;

    }

    /**
     * delete roleName information by role id and locale.<br>
     *
     * @param roleId
     *        the roleName roleId
     * @param locale
     *        locale
     * @return deleted roleName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{roleId}/locale/{locale}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteRoleName(@PathVariable String roleId,
            @PathVariable String locale) {
        ImRoleNameMst stvIdmfRoleNameMst = new ImRoleNameMst();
        stvIdmfRoleNameMst.setRoleId(roleId);
        stvIdmfRoleNameMst.setLocale(locale);
        return roleNameService.deleteRoleName(stvIdmfRoleNameMst);
    }

    /**
     * delete roleName informations by roleName id list in one time.<br>
     *
     * @param roleNames
     *        the roleName id list
     * @return deleted roleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkRoleName(
            @RequestBody List<ImRoleNameMst> roleNames) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < roleNames.size(); i++) {
            resultList.add(roleNameService.deleteRoleName(roleNames.get(i)));
        }
        return resultList;
    }

    /**
     * delete roleName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted roleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteRoleNameByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImRoleNameMst> roleNames = null;
        try {
            roleNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImRoleNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < roleNames.size(); i++) {
            resultList.add(roleNameService.deleteRoleName(roleNames.get(i)));

        }
        return resultList;
    }

    /**
     * find roleName information by roleId
     *
     * @param roleId
     *        roleName roleId
     * @return selected roleName information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_role/{roleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImRoleNameMst> findRoleName(@PathVariable String roleId) {
        return roleNameService.getRoleName(roleId);
    }

    /**
     * find roleName information by roleNameId
     *
     * @param roleNameId
     *        roleName roleNameId
     * @return selected roleName information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{roleNameId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImRoleNameMst findRoleNameById(
            @PathVariable String roleNameId) {
        return roleNameService.getRoleNameById(roleNameId);
    }

    /**
     * find roleName information by roleCode
     *
     * @param roleCode
     *        roleName code
     * @return selected roleName information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{roleCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImRoleNameMst> findRoleNameByRoleCode(
            @PathVariable String roleCode) {
        return roleNameService.getRoleNameByRoleCode(roleCode);
    }

    /**
     * search role name information by role name
     *
     * @param stvIdmfRoleNameMst
     *        stvIdmfRoleNameMst
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected role information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImRoleNameMst> searchRoleName(
            @RequestBody ImRoleNameMst stvIdmfRoleNameMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return roleNameService.searchRoleName(stvIdmfRoleNameMst, pageNo,
                pageSize, sort);
    }

}