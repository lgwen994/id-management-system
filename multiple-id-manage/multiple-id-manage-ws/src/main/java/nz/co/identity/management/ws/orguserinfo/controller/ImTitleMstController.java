
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
import nz.co.identity.management.api.orguserinfo.entity.ImTitleMst;
import nz.co.identity.management.api.orguserinfo.service.ImTitleMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of title information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_titles")
public class ImTitleMstController {

    /**
     * the statement of title service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImTitleMstService titleService;

    /**
     * register TitleMst information.<br>
     *
     * @param stvIdmfTitleMst
     *        TitleMst entity
     * @return TitleMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImTitleMst registerTitle(
            @RequestBody ImTitleMst stvIdmfTitleMst) {

        return titleService.registerTitle(stvIdmfTitleMst);
    }

    /**
     * register TitleMst information in one time.<br>
     *
     * @param titleList
     *        TitleMst information list
     * @return registered TitleMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImTitleMst> registerBulkTitle(
            @RequestBody List<ImTitleMst> titleList) {
        List<ImTitleMst> stvIdmfTitleMstList = new ArrayList<ImTitleMst>();
        ImTitleMst stvIdmfTitleMst = null;
        for (int i = 0; i < titleList.size(); i++) {
            stvIdmfTitleMst = titleService.registerTitle(titleList.get(i));
            stvIdmfTitleMstList.add(stvIdmfTitleMst);
        }

        return stvIdmfTitleMstList;
    }

    /**
     * register TitleMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered TitleMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImTitleMst> registerTitleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImTitleMst> ititlesList = new ArrayList<ImTitleMst>();
        List<ImTitleMst> titles = null;
        try {
            titles = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImTitleMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImTitleMst stvIdmfTitleMst = null;

        for (int i = 0; i < titles.size(); i++) {
            stvIdmfTitleMst = titleService.registerTitle(titles.get(i));
            ititlesList.add(stvIdmfTitleMst);
        }
        return ititlesList;
    }

    /**
     * update TitleMst information by TitleMst id.<br>
     *
     * @param stvIdmfTitleMst
     *        TitleMst entity
     * @return updated TitleMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImTitleMst updateTitle(
            @RequestBody ImTitleMst stvIdmfTitleMst) {
        return titleService.updateTitle(stvIdmfTitleMst);
    }

    /**
     * update TitleMst informations by TitleMst id list in one time.<br>
     *
     * @param titleList
     *        the TitleMst id list
     * @return updated TitleMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImTitleMst> updateBulkTitle(
            @RequestBody List<ImTitleMst> titleList) {
        List<ImTitleMst> stvIdmfTitleMstList = new ArrayList<ImTitleMst>();
        ImTitleMst stvIdmfTitleMst = null;
        for (int i = 0; i < titleList.size(); i++) {
            stvIdmfTitleMst = titleService.updateTitle(titleList.get(i));
            stvIdmfTitleMstList.add(stvIdmfTitleMst);
        }

        return stvIdmfTitleMstList;
    }

    /**
     * update TitleMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated TitleMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImTitleMst> updateTitleByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImTitleMst> ititlesList = new ArrayList<ImTitleMst>();
        List<ImTitleMst> titles = null;
        try {
            titles = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImTitleMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImTitleMst stvIdmfTitleMst = null;

        for (int i = 0; i < titles.size(); i++) {
            stvIdmfTitleMst = titleService.updateTitle(titles.get(i));
            ititlesList.add(stvIdmfTitleMst);
        }
        return ititlesList;
    }

    /**
     * delete TitleMst information by TitleMst id and version.<br>
     *
     * @param titleId
     *        the TitleMst id
     * @param version
     *        version
     * @return deleted TitleMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{titleId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteTitle(@PathVariable String titleId,
            @PathVariable String version) {

        ImTitleMst stvIdmfTitleMst = new ImTitleMst();
        stvIdmfTitleMst.setTitleId(titleId);
        stvIdmfTitleMst.setVersionNo(Integer.valueOf(version));
        return titleService.deleteTitle(stvIdmfTitleMst);
    }

    /**
     * delete TitleMst informations by TitleMst id list in one time.<br>
     *
     * @param titleList
     *        the TitleMst id list
     * @return deleted TitleMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkTitle(
            @RequestBody List<ImTitleMst> titleList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < titleList.size(); i++) {
            resultList.add(titleService.deleteTitle(titleList.get(i)));
        }
        return resultList;
    }

    /**
     * delete TitleMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted TitleMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteTitleByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImTitleMst> titles = null;
        try {
            titles = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImTitleMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < titles.size(); i++) {
            resultList.add(titleService.deleteTitle(titles.get(i)));
        }

        return resultList;
    }

    /**
     * find TitleMst information by TitleId
     *
     * @param titleId
     *        titleMst titleId
     * @return selected TitleMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{titleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImTitleMst findTitle(@PathVariable String titleId) {
        return titleService.getTitle(titleId);
    }

    /**
     * find TitleMst information by TitleCode
     *
     * @param titleCode
     *        titleMst titleCode
     * @return selected TitleMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{titleCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImTitleMst findTitleByCode(@PathVariable String titleCode) {
        return titleService.getTitleByTitleCode(titleCode);
    }

    /**
     * search TitleMst information by TitleMst
     *
     * @param stvIdmfTitleMst
     *        stvIdmfTitleMst
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected TitleMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImTitleMst> searchTitle(
            @RequestBody ImTitleMst stvIdmfTitleMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return titleService.searchTitle(stvIdmfTitleMst, pageNo, pageSize,
                sort);
    }
}