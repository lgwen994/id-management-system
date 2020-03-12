
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
import nz.co.identity.management.api.orguserinfo.entity.ImTitleNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImTitleNameMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of titleName information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_title_names")
public class ImTitleNameMstController {

    /**
     * the statement of titleName service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImTitleNameMstService titleNameService;

    /**
     * register TitleNameMst information.<br>
     *
     * @param stvIdmfTitleNameMst
     *        TitleNameMst entity
     * @return TitleNameMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImTitleNameMst registerTitleName(
            @RequestBody ImTitleNameMst stvIdmfTitleNameMst) {

        return titleNameService.registerTitleName(stvIdmfTitleNameMst);
    }

    /**
     * register TitleNameMst information in one time.<br>
     *
     *
     * @param titleNameList
     *        TitleNameMst information list
     * @return registered TitleNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImTitleNameMst> registerBulkTitleName(
            @RequestBody List<ImTitleNameMst> titleNameList) {

        List<ImTitleNameMst> stvTitleList = new ArrayList<ImTitleNameMst>();
        ImTitleNameMst stvIdmfTitleNameMst = null;
        for (int i = 0; i < titleNameList.size(); i++) {
            stvIdmfTitleNameMst = titleNameService
                    .registerTitleName(titleNameList.get(i));
            stvTitleList.add(stvIdmfTitleNameMst);
        }

        return stvTitleList;
    }

    /**
     * register TitleNameMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered TitleNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImTitleNameMst> registerTitleNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImTitleNameMst> ititleNamesList = new ArrayList<ImTitleNameMst>();
        List<ImTitleNameMst> titleNames = null;
        try {
            titleNames = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImTitleNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImTitleNameMst stvIdmfTitleNameMst = null;

        for (int i = 0; i < titleNames.size(); i++) {
            stvIdmfTitleNameMst = titleNameService
                    .registerTitleName(titleNames.get(i));
            ititleNamesList.add(stvIdmfTitleNameMst);
        }
        return ititleNamesList;
    }

    /**
     * update titleNameMst information by titleNameMst id.<br>
     *
     * @param stvIdmfTitleNameMst
     *        stvIdmfTitleNameMst
     * @return updated titleName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImTitleNameMst updateTitleName(
            @RequestBody ImTitleNameMst stvIdmfTitleNameMst) {

        return titleNameService.updateTitleName(stvIdmfTitleNameMst);
    }

    /**
     * update titleName informations by titleName id list in one time.<br>
     *
     * @param titleNames
     *        the titleName id list
     * @return updated titleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImTitleNameMst> updateBulkTitleName(
            @RequestBody List<ImTitleNameMst> titleNames) {

        List<ImTitleNameMst> iPosList = new ArrayList<ImTitleNameMst>();
        ImTitleNameMst titleNameMst = null;

        for (int i = 0; i < titleNames.size(); i++) {
            titleNameMst = titleNameService.updateTitleName(titleNames.get(i));
            iPosList.add(titleNameMst);
        }

        return iPosList;
    }

    /**
     * update titleName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated titleName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImTitleNameMst> updateTitleNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImTitleNameMst> iPosList = new ArrayList<ImTitleNameMst>();
        List<ImTitleNameMst> titleNames = null;
        try {
            titleNames = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImTitleNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImTitleNameMst titleNameMst = null;

        for (int i = 0; i < titleNames.size(); i++) {
            titleNameMst = titleNameService.updateTitleName(titleNames.get(i));
            iPosList.add(titleNameMst);
        }
        return iPosList;

    }

    /**
     * delete TitleNameMst information by Title id and locale.<br>
     *
     * @param titleId
     *        the titleId
     * @param locale
     *        locale
     * @return deleted TitleNameMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{titleId}/locale/{locale}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteTitleName(@PathVariable String titleId,
            @PathVariable String locale) {
        ImTitleNameMst stvIdmfTitleNameMst = new ImTitleNameMst();
        stvIdmfTitleNameMst.setTitleId(titleId);
        stvIdmfTitleNameMst.setLocale(locale);

        return titleNameService.deleteTitleName(stvIdmfTitleNameMst);
    }

    /**
     * delete TitleNameMst informations by TitleName id list in one time.<br>
     *
     * @param titleNameList
     *        the TitleName id list
     * @return deleted TitleNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkTitleName(
            @RequestBody List<ImTitleNameMst> titleNameList) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < titleNameList.size(); i++) {
            resultList.add(
                    titleNameService.deleteTitleName(titleNameList.get(i)));
        }
        return resultList;
    }

    /**
     * delete TitleNameMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted TitleNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteTitleNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImTitleNameMst> titleNames = null;
        try {
            titleNames = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImTitleNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < titleNames.size(); i++) {
            resultList.add(titleNameService.deleteTitleName(titleNames.get(i)));
        }

        return resultList;
    }

    /**
     * find TitleNameMst information by titleId
     *
     * @param titleId
     *        titleId
     * @return selected title name information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_title/{titleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImTitleNameMst> findTitleName(
            @PathVariable String titleId) {
        return titleNameService.getTitleName(titleId);
    }

    /**
     * find TitleNameMst information by titleNameId
     *
     * @param titleNameId
     *        titleNameId
     * @return selected title name information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{titleNameId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImTitleNameMst findTitleNameById(
            @PathVariable String titleNameId) {
        return titleNameService.getTitleNameById(titleNameId);
    }

    /**
     * find TitleName information by titleCode
     *
     * @param titleCode
     *        title code
     * @return selected TitleName information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{titleCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImTitleNameMst> findTitleNameByCode(
            @PathVariable String titleCode) {
        return titleNameService.getTitleNameByTitleCode(titleCode);
    }

    /**
     * search TitleName information by TitleName information
     *
     * @param stvIdmfTitleNameMst
     *        stvIdmfTitleNameMst
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected TitleName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImTitleNameMst> searchTitleName(
            @RequestBody ImTitleNameMst stvIdmfTitleNameMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return titleNameService.searchTitleName(stvIdmfTitleNameMst, pageNo,
                pageSize, sort);
    }
}
