
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
import nz.co.identity.management.api.orguserinfo.entity.ImCompanyMst;
import nz.co.identity.management.api.orguserinfo.service.ImCompanyMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of company information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_companies")
public class ImCompanyMstController {

    /**
     * the statement of company service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImCompanyMstService companyService;

    /**
     * register company information.<br>
     *
     * @param stvIdmfCompanyMst
     *        company entity
     * @return company information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImCompanyMst registerCompany(
            @RequestBody ImCompanyMst stvIdmfCompanyMst) {
        return companyService.registerCompany(stvIdmfCompanyMst);
    }

    /**
     * register company information in one time.<br>
     *
     * @param companyList
     *        company information list
     * @return registered company information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImCompanyMst> registerBulkCompany(
            @RequestBody List<ImCompanyMst> companyList) {
        List<ImCompanyMst> stvCompanyList = new ArrayList<ImCompanyMst>();
        ImCompanyMst stvIdmfCompanyMst = null;
        for (int i = 0; i < companyList.size(); i++) {
            stvIdmfCompanyMst = companyService
                    .registerCompany(companyList.get(i));
            stvCompanyList.add(stvIdmfCompanyMst);
        }

        return stvCompanyList;
    }

    /**
     * register company information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered company information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImCompanyMst> registerCompanyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImCompanyMst> icompanysList = new ArrayList<ImCompanyMst>();
        List<ImCompanyMst> companys = null;
        try {
            companys = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImCompanyMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImCompanyMst stvIdmfCompanyMst = null;

        for (int i = 0; i < companys.size(); i++) {
            stvIdmfCompanyMst = companyService.registerCompany(companys.get(i));
            icompanysList.add(stvIdmfCompanyMst);
        }
        return icompanysList;
    }

    /**
     * update company information by company id.<br>
     *
     * @param stvIdmfCompanyMst
     *        company entity
     * @return updated company information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImCompanyMst updateCompany(
            @RequestBody ImCompanyMst stvIdmfCompanyMst) {
        return companyService.updateCompany(stvIdmfCompanyMst);
    }

    /**
     * update company informations by company id list in one time.<br>
     *
     * @param companyList
     *        the company id list
     * @return updated company information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImCompanyMst> updateBulkCompany(
            @RequestBody List<ImCompanyMst> companyList) {

        List<ImCompanyMst> stvIdmfCompanyMstList = new ArrayList<ImCompanyMst>();
        ImCompanyMst stvIdmfCompanyMst = null;

        for (int i = 0; i < companyList.size(); i++) {
            stvIdmfCompanyMst = companyService
                    .updateCompany(companyList.get(i));
            stvIdmfCompanyMstList.add(stvIdmfCompanyMst);
        }
        return stvIdmfCompanyMstList;
    }

    /**
     * update company information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated company information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImCompanyMst> updateCompanyByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImCompanyMst> icompanysList = new ArrayList<ImCompanyMst>();
        List<ImCompanyMst> companys = null;
        try {
            companys = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImCompanyMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImCompanyMst stvIdmfCompanyMst = null;

        for (int i = 0; i < companys.size(); i++) {
            stvIdmfCompanyMst = companyService.updateCompany(companys.get(i));
            icompanysList.add(stvIdmfCompanyMst);
        }

        return icompanysList;
    }

    /**
     * delete company information by company id.<br>
     *
     * @param companyId
     *        the company id
     * @param version
     *        version
     * @return deleted company information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{companyId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteCompany(@PathVariable String companyId,
            @PathVariable String version) {

        ImCompanyMst stvIdmfCompanyMst = new ImCompanyMst();
        stvIdmfCompanyMst.setCompanyId(companyId);
        stvIdmfCompanyMst.setVersionNo(Integer.valueOf(version));
        return companyService.deleteCompany(stvIdmfCompanyMst);
    }

    /**
     * delete company informations by company id list in one time.<br>
     *
     * @param companyList
     *        the company id list
     * @return deleted company information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkCompany(
            @RequestBody List<ImCompanyMst> companyList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < companyList.size(); i++) {
            resultList.add(companyService.deleteCompany(companyList.get(i)));
        }
        return resultList;
    }

    /**
     * delete company information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted company information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteCompanyByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImCompanyMst> companys = null;
        try {
            companys = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImCompanyMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < companys.size(); i++) {
            resultList.add(companyService.deleteCompany(companys.get(i)));
        }

        return resultList;
    }

    /**
     * find company information by companyId
     *
     * @param companyId
     *        company companyId
     * @return selected company information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImCompanyMst findCompany(@PathVariable String companyId) {

        return companyService.getCompany(companyId);
    }

    /**
     * find company information by companyCode
     *
     * @param companyCode
     *        company companyCode
     * @return selected company information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{companyCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImCompanyMst findCompanyByCode(
            @PathVariable String companyCode) {

        return companyService.getCompanyByCode(companyCode);
    }

    /**
     * search company information by company information
     *
     * @param stvIdmfCompanyMst
     *        company Company
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
    public Page<ImCompanyMst> searchCompany(
            @RequestBody ImCompanyMst stvIdmfCompanyMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return companyService.searchCompany(stvIdmfCompanyMst, pageNo, pageSize,
                sort);
    }
}