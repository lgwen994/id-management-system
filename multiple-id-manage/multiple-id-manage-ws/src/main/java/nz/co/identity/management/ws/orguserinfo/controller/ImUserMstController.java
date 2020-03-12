
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
import nz.co.identity.management.api.orguserinfo.entity.ImUserMst;
import nz.co.identity.management.api.orguserinfo.service.ImUserMstService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of user information management.
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_users")
public class ImUserMstController {

    /**
     * the statement of user service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserMstService userService;

    /**
     * register UserMst information.<br>
     *
     * @param stvIdmfUserMst
     *        UserMst entity
     * @return UserMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImUserMst registerUser(
            @RequestBody ImUserMst stvIdmfUserMst) {
        return userService.registerUser(stvIdmfUserMst);
    }

    /**
     * register UserMst information in one time.<br>
     *
     *
     * @param userList
     *        UserMst information list
     * @return registered UserMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserMst> registerBulkUser(
            @RequestBody List<ImUserMst> userList) {

        List<ImUserMst> stvIdmfUserMstList = new ArrayList<ImUserMst>();
        ImUserMst stvIdmfUserMst = null;
        for (int i = 0; i < userList.size(); i++) {
            stvIdmfUserMst = userService.registerUser(userList.get(i));
            stvIdmfUserMstList.add(stvIdmfUserMst);
        }

        return stvIdmfUserMstList;
    }

    /**
     * register UserMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered UserMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserMst> registerUserByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImUserMst> iusersList = new ArrayList<ImUserMst>();
        List<ImUserMst> users = null;
        try {
            users = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImUserMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImUserMst stvIdmfUserMst = null;

        for (int i = 0; i < users.size(); i++) {
            stvIdmfUserMst = userService.registerUser(users.get(i));
            iusersList.add(stvIdmfUserMst);
        }
        return iusersList;
    }

    /**
     * update UserMst information.<br>
     *
     * @param stvIdmfUserMst
     *        UserMst entity
     * @return updated UserMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImUserMst updateUser(
            @RequestBody ImUserMst stvIdmfUserMst) {
        return userService.updateUser(stvIdmfUserMst);
    }

    /**
     * update UserMst informations by UserMst id list in one time.<br>
     *
     * @param userList
     *        the UserMst id list
     * @return updated UserMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImUserMst> updateBulkUser(
            @RequestBody List<ImUserMst> userList) {
        List<ImUserMst> stvIdmfUserMstList = new ArrayList<ImUserMst>();
        ImUserMst stvIdmfUserMst = null;
        for (int i = 0; i < userList.size(); i++) {
            stvIdmfUserMst = userService.updateUser(userList.get(i));
            stvIdmfUserMstList.add(stvIdmfUserMst);
        }

        return stvIdmfUserMstList;
    }

    /**
     * update UserMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated UserMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserMst> updateUserByUploadFile(
            @RequestParam("file") MultipartFile file) {
        List<ImUserMst> iusersList = new ArrayList<ImUserMst>();
        List<ImUserMst> users = null;
        try {
            users = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImUserMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImUserMst stvIdmfUserMst = null;

        for (int i = 0; i < users.size(); i++) {
            stvIdmfUserMst = userService.updateUser(users.get(i));
            iusersList.add(stvIdmfUserMst);
        }
        return iusersList;
    }

    /**
     * delete UserMst information by UserMst id.<br>
     *
     * @param userId
     *        the UserMst id
     * @param version
     *        version
     * @return deleted UserMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{userId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteUser(@PathVariable String userId,
            @PathVariable String version) {
        ImUserMst stvIdmfUserMst = new ImUserMst();
        stvIdmfUserMst.setUserId(userId);
        stvIdmfUserMst.setVersionNo(Integer.valueOf(version));
        return userService.deleteUser(stvIdmfUserMst);
    }

    /**
     * delete UserMst informations by UserMst id list in one time.<br>
     *
     * @param userList
     *        the UserMst id list
     * @return deleted UserMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkUser(
            @RequestBody List<ImUserMst> userList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < userList.size(); i++) {
            resultList.add(userService.deleteUser(userList.get(i)));
        }
        return resultList;
    }

    /**
     * delete UserMst information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted UserMst information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteUserByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImUserMst> users = null;
        try {
            users = CSVFileReader.getBeansFromCSVFile(file.getInputStream(),
                    ImUserMst.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < users.size(); i++) {
            resultList.add(userService.deleteUser(users.get(i)));
        }

        return resultList;
    }

    /**
     * find UserMst information by userId
     *
     * @param userId
     *        UserMst userId
     * @return selected UserMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImUserMst findUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    /**
     * find UserMst information by userCode
     *
     * @param userCode
     *        UserMst userCode
     * @return selected UserMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{userCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImUserMst findUserByCode(@PathVariable String userCode) {
        return userService.getUserByUserCode(userCode);
    }

    /**
     * search UserMst information by UserMst
     *
     * @param stvIdmfUserMst
     *        stvIdmfUserMst
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected UserMst information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImUserMst> searchUser(
            @RequestBody ImUserMst stvIdmfUserMst,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {
        return userService.searchUser(stvIdmfUserMst, pageNo, pageSize, sort);
    }

}