
package nz.co.identity.management.ws.orguserinfo.controller;

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
import nz.co.identity.management.api.orguserinfo.entity.ImOrgMst;
import nz.co.identity.management.api.orguserinfo.service.ImOrgMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of organization information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_orgs")
public class ImOrgMstController {

    /**
     * the statement of organization service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImOrgMstService orgService;;

    /**
     * register organization information.<br>
     *
     * @param stvIdmfOrgMst
     *        organization entity
     * @return organization information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImOrgMst registerOrg(@RequestBody ImOrgMst stvIdmfOrgMst) {

        return orgService.registerOrg(stvIdmfOrgMst);
    }

    /**
     * register organization information in one time.<br>
     *
     *
     * @param orgList
     *        organization information list
     * @return registered organization information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImOrgMst> registerBulkOrg(
            @RequestBody List<ImOrgMst> orgList) {

        List<ImOrgMst> stvIdmfOrgMstList = new ArrayList<ImOrgMst>();
        ImOrgMst stvIdmfOrgMst = null;
        for (int i = 0; i < orgList.size(); i++) {
            stvIdmfOrgMst = orgService.registerOrg(orgList.get(i));
            stvIdmfOrgMstList.add(stvIdmfOrgMst);
        }

        return stvIdmfOrgMstList;
    }

    /**
     * register organization information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered organization information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImOrgMst> registerOrgByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImOrgMst> iorgsList = new ArrayList<ImOrgMst>();
        List<ImOrgMst> orgs = null;
        try {
            orgs = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImOrgMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImOrgMst stvIdmfOrgMst = null;

        for (int i = 0; i < orgs.size(); i++) {
            stvIdmfOrgMst = orgService.registerOrg(orgs.get(i));
            iorgsList.add(stvIdmfOrgMst);
        }
        return iorgsList;
    }

    /**
     * update organization information by organization id.<br>
     *
     * @param stvIdmfOrgMst
     *        organization entity
     * @return updated organization information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImOrgMst updateOrg(@RequestBody ImOrgMst stvIdmfOrgMst) {

        return orgService.updateOrg(stvIdmfOrgMst);
    }

    /**
     * update organization informations by organization id list in one time.<br>
     *
     * @param orgList
     *        the organization id list
     * @return updated organization information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImOrgMst> updateBulkOrg(
            @RequestBody List<ImOrgMst> orgList) {
        List<ImOrgMst> stvIdmfOrgMstList = new ArrayList<ImOrgMst>();
        ImOrgMst stvIdmfOrgMst = null;

        for (int i = 0; i < orgList.size(); i++) {
            stvIdmfOrgMst = orgService.updateOrg(orgList.get(i));
            stvIdmfOrgMstList.add(stvIdmfOrgMst);
        }
        return stvIdmfOrgMstList;
    }

    /**
     * update organization information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated organization information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImOrgMst> updateOrgByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImOrgMst> iorgsList = new ArrayList<ImOrgMst>();
        List<ImOrgMst> orgs = null;
        try {
            orgs = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImOrgMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImOrgMst stvIdmfOrgMst = null;

        for (int i = 0; i < orgs.size(); i++) {
            stvIdmfOrgMst = orgService.updateOrg(orgs.get(i));
            iorgsList.add(stvIdmfOrgMst);
        }

        return iorgsList;
    }

    /**
     * delete organization information by organization id.<br>
     *
     * @param orgId
     *        the organization id
     * @param version
     *        version
     * @return deleted organization information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{orgId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteOrg(@PathVariable String orgId,
            @PathVariable String version) {
        ImOrgMst stvIdmfOrgMst = new ImOrgMst();
        stvIdmfOrgMst.setOrgId(orgId);
        stvIdmfOrgMst.setVersionNo(Integer.valueOf(version));
        return orgService.deleteOrg(stvIdmfOrgMst);
    }

    /**
     * delete organization informations by organization id list in one time.<br>
     *
     * @param orgList
     *        the organization id list
     * @return deleted organization information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkOrg(
            @RequestBody List<ImOrgMst> orgList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < orgList.size(); i++) {
            resultList.add(orgService.deleteOrg(orgList.get(i)));
        }
        return resultList;
    }

    /**
     * delete organization information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted organization information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteOrgByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImOrgMst> orgs = null;
        try {
            orgs = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImOrgMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < orgs.size(); i++) {
            resultList.add(orgService.deleteOrg(orgs.get(i)));
        }

        return resultList;
    }

    /**
     * find organization information by organizationId
     *
     * @param organizationId
     *        organization organizationId
     * @return selected organization information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImOrgMst findOrg(@PathVariable String organizationId) {

        return orgService.getOrg(organizationId);
    }

    /**
     * find organization information by organizationCode
     *
     * @param organizationCode
     *        organization organizationCode
     * @return selected organization information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{organizationCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImOrgMst findOrgByCode(@PathVariable String organizationCode) {
        return orgService.getOrgByOrgCode(organizationCode);
    }

    /**
     * search organization information by organizations
     *
     * @param stvIdmfOrgMst
     *        organization
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected organization information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImOrgMst> searchOrg(
            @RequestBody ImOrgMst stvIdmfOrgMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return orgService.searchOrg(stvIdmfOrgMst, pageNo, pageSize, sort);
    }
}