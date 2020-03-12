
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
import nz.co.identity.management.api.orguserinfo.entity.ImUserNameMst;
import nz.co.identity.management.api.orguserinfo.service.ImUserNameMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of userName information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_user_names")
public class ImUserNameMstController {

    /**
     * the statement of userName service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserNameMstService userNameService;

    /**
     * register UserNameMst information.<br>
     *
     * @param stvIdmfUserNameMst
     *        UserNameMst entity
     * @return UserNameMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImUserNameMst registerUserName(
            @RequestBody ImUserNameMst stvIdmfUserNameMst) {
        return userNameService.registerUserName(stvIdmfUserNameMst);
    }

    /**
     * register UserNameMst information in one time.<br>
     *
     *
     * @param userNameList
     *        UserNameMst information list
     * @return registered UserNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserNameMst> registerBulkUserName(
            @RequestBody List<ImUserNameMst> userNameList) {

        List<ImUserNameMst> stvUserList = new ArrayList<ImUserNameMst>();
        ImUserNameMst stvIdmfUserNameMst = null;
        for (int i = 0; i < userNameList.size(); i++) {
            stvIdmfUserNameMst = userNameService
                    .registerUserName(userNameList.get(i));
            stvUserList.add(stvIdmfUserNameMst);
        }

        return stvUserList;
    }

    /**
     * register UserNameMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered UserNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserNameMst> registerUserNameByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImUserNameMst> iuserNamesList = new ArrayList<ImUserNameMst>();
        List<ImUserNameMst> userNames = null;
        try {
            userNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImUserNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImUserNameMst stvIdmfUserNameMst = null;

        for (int i = 0; i < userNames.size(); i++) {
            stvIdmfUserNameMst = userNameService
                    .registerUserName(userNames.get(i));
            iuserNamesList.add(stvIdmfUserNameMst);
        }
        return iuserNamesList;
    }

    /**
     * update userNameMst information by userNameMst id.<br>
     *
     * @param stvIdmfUserNameMst
     *        stvIdmfUserNameMst
     * @return updated userName information
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImUserNameMst updateUserName(
            @RequestBody ImUserNameMst stvIdmfUserNameMst) {

        return userNameService.updateUserName(stvIdmfUserNameMst);
    }

    /**
     * update userName informations by userName id list in one time.<br>
     *
     * @param userNames
     *        the userName id list
     * @return updated userName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImUserNameMst> updateBulkUserName(
            @RequestBody List<ImUserNameMst> userNames) {

        List<ImUserNameMst> iPosList = new ArrayList<ImUserNameMst>();
        ImUserNameMst userNameMst = null;

        for (int i = 0; i < userNames.size(); i++) {
            userNameMst = userNameService.updateUserName(userNames.get(i));
            iPosList.add(userNameMst);
        }

        return iPosList;
    }

    /**
     * update userName information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated userName information list
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserNameMst> updateUserNameByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImUserNameMst> iPosList = new ArrayList<ImUserNameMst>();
        List<ImUserNameMst> userNames = null;
        try {
            userNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImUserNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImUserNameMst userNameMst = null;

        for (int i = 0; i < userNames.size(); i++) {
            userNameMst = userNameService.updateUserName(userNames.get(i));
            iPosList.add(userNameMst);
        }
        return iPosList;

    }

    /**
     * delete UserNameMst information by User id and locale.<br>
     *
     * @param userId
     *        userId
     * @param locale
     *        locale
     * @return deleted UserNameMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{userId}/locale/{locale}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteUserName(@PathVariable String userId,
            @PathVariable String locale) {
        ImUserNameMst stvIdmfUserNameMst = new ImUserNameMst();
        stvIdmfUserNameMst.setUserId(userId);
        stvIdmfUserNameMst.setLocale(locale);

        return userNameService.deleteUserName(stvIdmfUserNameMst);
    }

    /**
     * delete UserNameMst informations by UserNameMst id list in one time.<br>
     *
     * @param userNameList
     *        the UserNameMst list
     * @return deleted UserNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkUserName(
            @RequestBody List<ImUserNameMst> userNameList) {
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < userNameList.size(); i++) {
            resultList.add(userNameService.deleteUserName(userNameList.get(i)));
        }
        return resultList;
    }

    /**
     * delete UserNameMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted UserNameMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteUserNameByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImUserNameMst> userNames = null;
        try {
            userNames = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImUserNameMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < userNames.size(); i++) {
            resultList.add(userNameService.deleteUserName(userNames.get(i)));
        }

        return resultList;
    }

    /**
     * find UserNameMst information by userId
     *
     * @param userId
     *        userId
     * @return selected UserNameMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_user/{userId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImUserNameMst> findUserName(@PathVariable String userId) {

        return userNameService.getUserName(userId);
    }

    /**
     * find UserNameMst information by userNameId
     *
     * @param userNameId
     *        userNameId
     * @return selected UserNameMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{userNameId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImUserNameMst findUserNameById(
            @PathVariable String userNameId) {

        return userNameService.getUserNameById(userNameId);
    }

    /**
     * find userName information by userCode
     *
     * @param userCode
     *        user code
     * @return selected userName information
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{userCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<ImUserNameMst> findUserNameByCode(
            @PathVariable String userCode) {
        return userNameService.getUserNameByUserCode(userCode);
    }

    /**
     * search userName information by userName information
     *
     * @param stvIdmfUserNameMst
     *        userName entity
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected userName information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImUserNameMst> searchCompanyName(
            @RequestBody ImUserNameMst stvIdmfUserNameMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return userNameService.searchUserName(stvIdmfUserNameMst, pageNo,
                pageSize, sort);
    }
}