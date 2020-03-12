
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
import nz.co.identity.management.api.roleinfo.entity.ImRoleMst;
import nz.co.identity.management.api.roleinfo.service.ImRoleMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * The controller class of role information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_roles")
public class ImRoleMstController {

    /**
     * The statement of role service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImRoleMstService roleService;

    /**
     * register role information.<br>
     *
     * @param stvIdmfRoleMst
     *        RoleMst entity
     * @return RoleMst information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImRoleMst registerRole(
            @RequestBody ImRoleMst stvIdmfRoleMst) {
        return roleService.registerRole(stvIdmfRoleMst);
    }

    /**
     * register role information in one time.<br>
     *
     *
     * @param roles
     *        role information list
     * @return registered RoleMst information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImRoleMst> registerBulkRole(
            @RequestBody List<ImRoleMst> roles) {

        List<ImRoleMst> iPosList = new ArrayList<ImRoleMst>();
        ImRoleMst roleMst = null;

        for (int i = 0; i < roles.size(); i++) {
            roleMst = roleService.registerRole(roles.get(i));
            iPosList.add(roleMst);
        }

        return iPosList;
    }

    /**
     * register role information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered role information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImRoleMst> registerRoleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImRoleMst> iPosList = new ArrayList<ImRoleMst>();
        List<ImRoleMst> roles = null;
        try {
            roles = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImRoleMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImRoleMst roleMst = null;
        for (int i = 0; i < roles.size(); i++) {
            roleMst = roleService.registerRole(roles.get(i));
            iPosList.add(roleMst);
        }
        return iPosList;
    }

    /**
     * update role information by role id.<br>
     * 
     * @param stvIdmfRoleMst
     *        role entity
     * @return updated role information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImRoleMst updateRole(
            @RequestBody ImRoleMst stvIdmfRoleMst) {
        return roleService.updateRole(stvIdmfRoleMst);
    }

    /**
     * update role informations by role id list in one time.<br>
     *
     * @param roles
     *        the role id list
     * @return updated role information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImRoleMst> updateBulkRole(
            @RequestBody List<ImRoleMst> roles) {

        List<ImRoleMst> iPosList = new ArrayList<ImRoleMst>();
        ImRoleMst roleMst = null;

        for (int i = 0; i < roles.size(); i++) {
            roleMst = roleService.updateRole(roles.get(i));
            iPosList.add(roleMst);
        }

        return iPosList;
    }

    /**
     * update role information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated role information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImRoleMst> updateRoleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImRoleMst> iPosList = new ArrayList<ImRoleMst>();
        List<ImRoleMst> roles = null;
        try {
            roles = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImRoleMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImRoleMst roleMst = null;

        for (int i = 0; i < roles.size(); i++) {
            roleMst = roleService.updateRole(roles.get(i));
            iPosList.add(roleMst);
        }
        return iPosList;

    }

    /**
     * delete role information by role id.<br>
     *
     * @param roleId
     *        the role id
     * @param version
     *        version
     * @return deleted role information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{roleId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteRole(@PathVariable String roleId,
            @PathVariable String version) {
        ImRoleMst stvIdmfRoleMst = new ImRoleMst();
        stvIdmfRoleMst.setRoleId(roleId);
        stvIdmfRoleMst.setVersionNo(Integer.valueOf(version));
        return roleService.deleteRole(stvIdmfRoleMst);
    }

    /**
     * delete role informations by role id list in one time.<br>
     *
     * @param stvIdmfRoleMsts
     *        the role list
     * @return deleted role information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkRole(
            @RequestBody List<ImRoleMst> stvIdmfRoleMsts) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < stvIdmfRoleMsts.size(); i++) {
            resultList.add(roleService.deleteRole(stvIdmfRoleMsts.get(i)));
        }
        return resultList;
    }

    /**
     * delete role information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted role information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteRoleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImRoleMst> roles = null;
        try {
            roles = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImRoleMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < roles.size(); i++) {
            resultList.add(roleService.deleteRole(roles.get(i)));
        }
        return resultList;
    }

    /**
     * find role information by roleId
     *
     * @param roleId
     *        role id
     * @return selected role information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImRoleMst findRole(@PathVariable String roleId) {
        return roleService.getRole(roleId);
    }

    /**
     * find role information by roleCode
     *
     * @param roleCode
     *        role code
     * @return selected role information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{roleCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImRoleMst findRoleByRoleCode(@PathVariable String roleCode) {
        return roleService.getRoleByRoleCode(roleCode);
    }

    /**
     * search role information by role
     *
     * @param stvIdmfRoleMst
     *        stvIdmfRoleMst
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
    public Page<ImRoleMst> searchRole(
            @RequestBody ImRoleMst stvIdmfRoleMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return roleService.searchRole(stvIdmfRoleMst, pageNo, pageSize, sort);
    }
}