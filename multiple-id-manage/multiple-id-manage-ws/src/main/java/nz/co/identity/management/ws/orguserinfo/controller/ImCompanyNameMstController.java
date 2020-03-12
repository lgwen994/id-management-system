
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
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImCompanyNameMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of companyName information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_company_names")
public class ImCompanyNameMstController {

    /**
     * the statement of companyName service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImCompanyNameMstService companyNameService;

    /**
     * register companyName information.<br>
     *
     * @param companyNameMst
     *        companyName entity
     * @return companyName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImCompanyNameMst registerCompanyName(
            @RequestBody ImCompanyNameMst companyNameMst) {
        return companyNameService.registerCompanyName(companyNameMst);
    }

    /**
     * register companyName information in one time.<br>
     *
     * @param companyNameList
     *        companyName information list
     * @return registered companyName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImCompanyNameMst> registerBulkCompanyName(
            @RequestBody List<ImCompanyNameMst> companyNameList) {

        List<ImCompanyNameMst> stvCompanyNameList = new ArrayList<ImCompanyNameMst>();
        ImCompanyNameMst stvIdmfCompanyNameMst = null;
        for (int i = 0; i < companyNameList.size(); i++) {
            stvIdmfCompanyNameMst = companyNameService
                    .registerCompanyName(companyNameList.get(i));
            stvCompanyNameList.add(stvIdmfCompanyNameMst);
        }
        return stvCompanyNameList;
    }

    /**
     * register companyName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered companyName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImCompanyNameMst> registerCompanyNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImCompanyNameMst> icompanyNamesList = new ArrayList<ImCompanyNameMst>();
        List<ImCompanyNameMst> companyNames = null;
        try {
            companyNames = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImCompanyNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImCompanyNameMst stvIdmfCompanyNameMst = null;
        for (int i = 0; i < companyNames.size(); i++) {
            stvIdmfCompanyNameMst = companyNameService
                    .registerCompanyName(companyNames.get(i));
            icompanyNamesList.add(stvIdmfCompanyNameMst);
        }
        return icompanyNamesList;

    }

    /**
     * update companyName information by companyName id.<br>
     *
     * @param stvIdmfCompanyNameMst
     *        companyName entity
     * @return updated companyName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImCompanyNameMst updateCompanyName(
            @RequestBody ImCompanyNameMst stvIdmfCompanyNameMst) {

        return companyNameService.updateCompanyName(stvIdmfCompanyNameMst);
    }

    /**
     * update companyName informations by companyName id list in one time.<br>
     *
     * @param companyNames
     *        the companyName id list
     * @return updated companyName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImCompanyNameMst> updateBulkCompanyName(
            @RequestBody List<ImCompanyNameMst> companyNames) {

        List<ImCompanyNameMst> iPosList = new ArrayList<ImCompanyNameMst>();
        ImCompanyNameMst companyNameMst = null;

        for (int i = 0; i < companyNames.size(); i++) {
            companyNameMst = companyNameService
                    .updateCompanyName(companyNames.get(i));
            iPosList.add(companyNameMst);
        }

        return iPosList;
    }

    /**
     * update companyName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated companyName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImCompanyNameMst> updateCompanyNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImCompanyNameMst> iPosList = new ArrayList<ImCompanyNameMst>();
        List<ImCompanyNameMst> companyNames = null;
        try {
            companyNames = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImCompanyNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImCompanyNameMst companyNameMst = null;

        for (int i = 0; i < companyNames.size(); i++) {
            companyNameMst = companyNameService
                    .updateCompanyName(companyNames.get(i));
            iPosList.add(companyNameMst);
        }
        return iPosList;

    }

    /**
     * delete companyName information by company id and locale.<br>
     *
     * @param companyId
     *        companyId
     * @param locale
     *        locale
     * @return deleted companyName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{companyId}/locale/{locale}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteCompanyName(@PathVariable String companyId,
            @PathVariable String locale) {

        ImCompanyNameMst stvIdmfCompanyNameMst = new ImCompanyNameMst();
        stvIdmfCompanyNameMst.setCompanyId(companyId);
        stvIdmfCompanyNameMst.setLocale(locale);
        return companyNameService.deleteCompanyName(stvIdmfCompanyNameMst);
    }

    /**
     * delete companyName informations by companyName id list in one time.<br>
     *
     * @param companyNameList
     *        the companyName list
     * @return deleted companyName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkCompanyName(
            @RequestBody List<ImCompanyNameMst> companyNameList) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < companyNameList.size(); i++) {
            resultList.add(companyNameService
                    .deleteCompanyName(companyNameList.get(i)));
        }
        return resultList;
    }

    /**
     * delete companyName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted companyName information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteCompanyNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImCompanyNameMst> companyNames = null;
        try {
            companyNames = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImCompanyNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < companyNames.size(); i++) {
            resultList.add(
                    companyNameService.deleteCompanyName(companyNames.get(i)));
        }

        return resultList;
    }

    /**
     * find companyName information by companyId
     *
     * @param companyId
     *        company id
     * @return selected companyName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_company/{companyId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImCompanyNameMst> findCompanyName(
            @PathVariable String companyId) {
        return companyNameService.getCompanyName(companyId);
    }

    /**
     * find companyName information by companyNameId
     *
     * @param companyNameId
     *        companyName id
     * @return selected companyName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{companyNameId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImCompanyNameMst findCompanyNameById(
            @PathVariable String companyNameId) {
        return companyNameService.getCompanyNameById(companyNameId);
    }

    /**
     * find companyName information by companyCode
     *
     * @param companyCode
     *        company companyCode
     * @return selected company name information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{companyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImCompanyNameMst> findCompanyNameByCode(
            @PathVariable String companyCode) {
        return companyNameService.getCompanyNameByCompanyCode(companyCode);
    }

    /**
     * search companyName information by company information
     *
     * @param stvIdmfCompanyNameMst
     *        company name entity
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
    public Page<ImCompanyNameMst> searchCompanyName(
            @RequestBody ImCompanyNameMst stvIdmfCompanyNameMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return companyNameService.searchCompanyName(stvIdmfCompanyNameMst,
                pageNo, pageSize, sort);
    }
}