
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
import nz.co.identity.management.api.orguserinfo.entity.ImOrgNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImOrgNameMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of orgName information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_org_names")
public class ImOrgNameMstController {

    /**
     * the statement of orgName service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImOrgNameMstService orgNameService;

    /**
     * register orgName information.<br>
     *
     * @param stvIdmfOrgNameMst
     *        orgName entity
     * @return orgName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImOrgNameMst registerOrgName(
            @RequestBody ImOrgNameMst stvIdmfOrgNameMst) {

        return orgNameService.registerOrgName(stvIdmfOrgNameMst);
    }

    /**
     * register orgName information in one time.<br>
     *
     * @param orgNameList
     *        orgName information list
     * @return registered orgName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImOrgNameMst> registerBulkOrgName(
            @RequestBody List<ImOrgNameMst> orgNameList) {

        List<ImOrgNameMst> stvIdmfOrgNameMstList = new ArrayList<ImOrgNameMst>();
        ImOrgNameMst stvIdmfOrgNameMstMst = null;
        for (int i = 0; i < orgNameList.size(); i++) {
            stvIdmfOrgNameMstMst = orgNameService
                    .registerOrgName(orgNameList.get(i));
            stvIdmfOrgNameMstList.add(stvIdmfOrgNameMstMst);
        }
        return stvIdmfOrgNameMstList;
    }

    /**
     * register orgName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered orgName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImOrgNameMst> registerOrgNameByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImOrgNameMst> iorgNamesList = new ArrayList<ImOrgNameMst>();
        List<ImOrgNameMst> orgNames = null;
        try {
            orgNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImOrgNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImOrgNameMst stvIdmfOrgNameMst = null;

        for (int i = 0; i < orgNames.size(); i++) {
            stvIdmfOrgNameMst = orgNameService.registerOrgName(orgNames.get(i));
            iorgNamesList.add(stvIdmfOrgNameMst);
        }
        return iorgNamesList;
    }

    /**
     * update orgName information by orgName id.<br>
     *
     * @param stvIdmfOrgNameMst
     *        orgName entity
     * @return updated orgName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImOrgNameMst updateOrgName(
            @RequestBody ImOrgNameMst stvIdmfOrgNameMst) {

        return orgNameService.updateOrgName(stvIdmfOrgNameMst);
    }

    /**
     * update orgName informations by orgName id list in one time.<br>
     *
     * @param orgNames
     *        the orgName id list
     * @return updated orgName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImOrgNameMst> updateBulkOrgName(
            @RequestBody List<ImOrgNameMst> orgNames) {

        List<ImOrgNameMst> iPosList = new ArrayList<ImOrgNameMst>();
        ImOrgNameMst orgNameMst = null;

        for (int i = 0; i < orgNames.size(); i++) {
            orgNameMst = orgNameService.updateOrgName(orgNames.get(i));
            iPosList.add(orgNameMst);
        }

        return iPosList;
    }

    /**
     * update orgName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated orgName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImOrgNameMst> updateOrgNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImOrgNameMst> iPosList = new ArrayList<ImOrgNameMst>();
        List<ImOrgNameMst> orgNames = null;
        try {
            orgNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImOrgNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImOrgNameMst orgNameMst = null;

        for (int i = 0; i < orgNames.size(); i++) {
            orgNameMst = orgNameService.updateOrgName(orgNames.get(i));
            iPosList.add(orgNameMst);
        }
        return iPosList;

    }

    /**
     * delete orgName information by org id and locale.<br>
     *
     * @param orgId
     *        the orgId
     * @param locale
     *        locale
     * @return deleted orgName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{orgId}/locale/{locale}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteOrgName(@PathVariable String orgId,
            @PathVariable String locale) {

        ImOrgNameMst stvIdmfOrgNameMst = new ImOrgNameMst();
        stvIdmfOrgNameMst.setOrgId(orgId);
        stvIdmfOrgNameMst.setLocale(locale);
        return orgNameService.deleteOrgName(stvIdmfOrgNameMst);
    }

    /**
     * delete orgName informations by orgName id list in one time.<br>
     *
     * @param orgNameList
     *        the orgName list
     * @return deleted orgName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkOrgName(
            @RequestBody List<ImOrgNameMst> orgNameList) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < orgNameList.size(); i++) {
            resultList.add(orgNameService.deleteOrgName(orgNameList.get(i)));
        }
        return resultList;
    }

    /**
     * delete orgName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted orgName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteOrgNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImOrgNameMst> orgNames = null;
        try {
            orgNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImOrgNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < orgNames.size(); i++) {
            resultList.add(orgNameService.deleteOrgName(orgNames.get(i)));
        }

        return resultList;
    }

    /**
     * find orgName information by orgId
     *
     * @param orgId
     *        orgId
     * @return selected orgName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_org/{orgId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImOrgNameMst> findOrgName(@PathVariable String orgId) {
        return orgNameService.getOrgName(orgId);
    }

    /**
     * find orgName information by orgNameId
     *
     * @param orgNameId
     *        orgNameId
     * @return selected orgName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{orgNameId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImOrgNameMst findOrgNameById(@PathVariable String orgNameId) {
        return orgNameService.getOrgNameById(orgNameId);
    }

    /**
     * find orgName information by orgCode
     *
     * @param orgCode
     *        org code
     * @return selected orgName information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{orgCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImOrgNameMst> findOrgNameByCode(
            @PathVariable String orgCode) {
        return orgNameService.getOrgNameByOrgCode(orgCode);
    }

    /**
     * search orgName information by orgName information
     *
     * @param stvIdmfOrgNameMst
     *        org name entity
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected company information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImOrgNameMst> searchOrgName(
            @RequestBody ImOrgNameMst stvIdmfOrgNameMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return orgNameService.searchOrgName(stvIdmfOrgNameMst, pageNo, pageSize,
                sort);
    }
}
