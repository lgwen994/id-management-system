package nz.co.identity.management.ws.logininfo.controller;

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
import nz.co.identity.management.api.logininfo.entity.ImUserLoginInfo;
import nz.co.identity.management.api.logininfo.service.ImUserLoginInfoService;
import nz.co.identity.management.ws.common.csv.CSVFileReader;

/**
 * the controller class of user login information management
 *
 * @since Staveware Core Ver.5.3
 */
@RestController
@RequestMapping("/idmf_user_login_infos")
public class ImUserLoginInfoController {

    /**
     * the statement of user login information service's interface
     *
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private ImUserLoginInfoService userLoginInfoService;

    /**
     * register user login information.<br>
     *
     * @param userLoginInfo
     *        user login information entity
     * @return user login information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ImUserLoginInfo registerUserLoginInfo(
            @RequestBody ImUserLoginInfo userLoginInfo) {
        return userLoginInfoService.registerUserLoginInfo(userLoginInfo);
    }

    /**
     * register user login information in one time.<br>
     *
     *
     * @param userLoginInfoList
     *        user login information list
     * @return registered user login information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserLoginInfo> registerBulkUserLoginInfo(
            @RequestBody List<ImUserLoginInfo> userLoginInfoList) {

        List<ImUserLoginInfo> iPosList = new ArrayList<ImUserLoginInfo>();
        ImUserLoginInfo retUserLoginInfo = null;

        for (int i = 0; i < userLoginInfoList.size(); i++) {
            retUserLoginInfo = userLoginInfoService
                    .registerUserLoginInfo(userLoginInfoList.get(i));
            iPosList.add(retUserLoginInfo);
        }

        return iPosList;
    }

    /**
     * register user login information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return registered user login information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserLoginInfo> registerUserLoginInfoByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImUserLoginInfo> iPosList = new ArrayList<ImUserLoginInfo>();
        List<ImUserLoginInfo> userLoginInfoList = null;
        try {
            userLoginInfoList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImUserLoginInfo.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImUserLoginInfo retUserLoginInfo = null;

        for (int i = 0; i < userLoginInfoList.size(); i++) {
            retUserLoginInfo = userLoginInfoService
                    .registerUserLoginInfo(userLoginInfoList.get(i));
            iPosList.add(retUserLoginInfo);
        }
        return iPosList;
    }

    /**
     * update user login information by user login information id.<br>
     *
     * @param userLoginInfo
     *        user login information entity
     * @return updated user login information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ImUserLoginInfo updateUserLoginInfo(
            @RequestBody ImUserLoginInfo userLoginInfo) {
        return userLoginInfoService.updateUserLoginInfo(userLoginInfo);
    }

    /**
     * update user login informations by user login information list in one
     * time.<br>
     *
     * @param userLoginInfoList
     *        the user login information list
     * @return updated user login information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<ImUserLoginInfo> updateBulkUserLoginInfo(
            @RequestBody List<ImUserLoginInfo> userLoginInfoList) {

        List<ImUserLoginInfo> iPosList = new ArrayList<ImUserLoginInfo>();
        ImUserLoginInfo retUserLoginInfo = null;

        for (int i = 0; i < userLoginInfoList.size(); i++) {
            retUserLoginInfo = userLoginInfoService
                    .updateUserLoginInfo(userLoginInfoList.get(i));
            iPosList.add(retUserLoginInfo);
        }

        return iPosList;
    }

    /**
     * update user login information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return updated user login information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ImUserLoginInfo> updateUserLoginInfoByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImUserLoginInfo> iPosList = new ArrayList<ImUserLoginInfo>();
        List<ImUserLoginInfo> userLoginInfoList = null;
        try {
            userLoginInfoList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImUserLoginInfo.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        ImUserLoginInfo retUserLoginInfo = null;

        for (int i = 0; i < userLoginInfoList.size(); i++) {
            retUserLoginInfo = userLoginInfoService
                    .updateUserLoginInfo(userLoginInfoList.get(i));
            iPosList.add(retUserLoginInfo);
        }
        return iPosList;
    }

    /**
     * delete user login information by user login information id.<br>
     *
     * @param userLoginId
     *        the user login information id
     * @param version
     *        version
     * @return deleted user login information
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/{userLoginId}/version/{version}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public boolean deleteUserLoginInfo(@PathVariable String userLoginId,
            @PathVariable String version) {
        ImUserLoginInfo userLoginInfo = new ImUserLoginInfo();

        userLoginInfo.setUserLoginInfoId(userLoginId);
        userLoginInfo.setVersionNo(Integer.valueOf(version));
        return userLoginInfoService.deleteUserLoginInfo(userLoginInfo);
    }

    /**
     * delete user login informations by user login information id list in one
     * time.<br>
     *
     * @param userLoginInfoList
     *        the user login information id list
     * @return deleted user login information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/bulk_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteBulkUserLoginInfo(
            @RequestBody List<ImUserLoginInfo> userLoginInfoList) {

        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < userLoginInfoList.size(); i++) {
            resultList.add(userLoginInfoService
                    .deleteUserLoginInfo(userLoginInfoList.get(i)));
        }
        return resultList;
    }

    /**
     * delete user login information by csv file.<br>
     *
     * @param file
     *        the csv file
     * @return deleted user login information list
     * 
     * @since Staveware Core Ver.5.3
     */
    @Transactional
    @RequestMapping(value = "/upload_delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Boolean> deleteUserLoginInfoByUploadFile(
            @RequestParam("file") MultipartFile file) {

        List<ImUserLoginInfo> userLoginInfoList = null;
        try {
            userLoginInfoList = CSVFileReader.getBeansFromCSVFile(
                    file.getInputStream(), ImUserLoginInfo.class);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }
        List<Boolean> resultList = new ArrayList<Boolean>();
        for (int i = 0; i < userLoginInfoList.size(); i++) {
            resultList.add(userLoginInfoService
                    .deleteUserLoginInfo(userLoginInfoList.get(i)));
        }
        return resultList;
    }

    /**
     * find user login information by user login info id
     *
     * @param userLoginId
     *        the user login information id
     * @return selected user login information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/{userLoginId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImUserLoginInfo findUserLoginInfo(
            @PathVariable String userLoginId) {

        return userLoginInfoService.getUserLoginInfo(userLoginId);
    }

    /**
     * find user login information by user login info code
     *
     * @param userLoginCode
     *        the user login information code
     * @return selected user login information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/find_by_code/{userLoginCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ImUserLoginInfo findUserLoginInfoByCode(
            @PathVariable String userLoginCode) {
        return userLoginInfoService.getUserLoginInfoByCode(userLoginCode);
    }

    /**
     * search user login information by user login information
     *
     * @param userLoginInfo
     *        the user login information
     * @param pageNo
     *        the current number of page
     * @param pageSize
     *        the size of one page
     * @param sort
     *        the sort of one page
     * @return selected user login information
     * 
     * @since Staveware Core Ver.5.3
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Page<ImUserLoginInfo> searchUserLoginInfo(
            @RequestBody ImUserLoginInfo userLoginInfo,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sort", required = false) List<String> sort) {

        return userLoginInfoService.searchUserLoginInfo(userLoginInfo, pageNo,
                pageSize, sort);
    }
}
