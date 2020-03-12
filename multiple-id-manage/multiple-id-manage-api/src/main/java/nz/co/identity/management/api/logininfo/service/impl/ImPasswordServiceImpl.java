package nz.co.identity.management.api.logininfo.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.identity.management.api.common.BaseService;
import nz.co.identity.management.api.common.page.Page;
import nz.co.identity.management.api.common.password.PasswordPolicyChecker;
import nz.co.identity.management.api.common.password.PasswordEncoderGenerator;
import nz.co.identity.management.api.logininfo.dao.ImPasswordDAO;
import nz.co.identity.management.api.logininfo.entity.ImPassword;
import nz.co.identity.management.api.logininfo.service.ImPasswordService;

/**
 *
 * the implementation class of password's service interface.<br>
 *
 * @since Staveware Core Ver.5.3
 */
@Service
@Transactional
public class ImPasswordServiceImpl extends BaseService<ImPasswordDAO>
        implements ImPasswordService {

    /**
     * stvIdmfPasswordDAOList
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private List<ImPasswordDAO> stvIdmfPasswordDAOList;

    /**
     * passwordEncoderGenerator
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private PasswordEncoderGenerator passwordEncoderGenerator;

    /**
     * PasswordPolicyChecker
     * 
     * @since Staveware Core Ver.5.3
     */
    @Resource
    private PasswordPolicyChecker passwordPolicyChecker;

    /**
     * the flag of history
     * 
     * @since Staveware Core Ver.5.3
     */
    @Value("${history.logininfo:off}")
    private String historyLogininfo;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePassword(ImPassword password) {
        return getDAO(historyLogininfo, stvIdmfPasswordDAOList)
                .deletePassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteForcePassword(ImPassword password) {
        return getDAO(historyLogininfo, stvIdmfPasswordDAOList)
                .deleteForcePassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword getPassword(ImPassword password) {
        return getDAO(historyLogininfo, stvIdmfPasswordDAOList)
                .getPassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword updatePassword(ImPassword password) {
        return updatePassword(password, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword updatePassword(ImPassword password,
            String pwdPolicyId) {
//        PasswordEncoder passwordEncoder = passwordEncoderGenerator
//                .getPasswordEncoder();
//        checkPasswordPolicy(passwordEncoder, password, pwdPolicyId);
//        password.setPassword(passwordEncoder.encode(password.getPassword()));
        return getDAO(historyLogininfo, stvIdmfPasswordDAOList)
                .updatePassword(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ImPassword> searchPassword(ImPassword password,
            Integer pageNum, Integer pageSize, List<String> sort) {
        return getDAO(historyLogininfo, stvIdmfPasswordDAOList)
                .searchPassword(password, pageNum, pageSize, sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword registerPassword(ImPassword password) {
        return registerPassword(password, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImPassword registerPassword(ImPassword password,
            String pwdPolicyId) {
//        PasswordEncoder passwordEncoder = passwordEncoderGenerator
//                .getPasswordEncoder();
//        checkPasswordPolicy(passwordEncoder, password, pwdPolicyId);
//        password.setPassword(passwordEncoder.encode(password.getPassword()));
        return getDAO(historyLogininfo, stvIdmfPasswordDAOList)
                .registerPassword(password);
    }

    /**
     * check password policy
     * 
     * @param passwordEncoder
     *        passwordEncoder
     * @param password
     *        password
     * @param pwdPolicyId
     *        pwdPolicyId
     * @since Staveware Core Ver.5.3
     */
//    private void checkPasswordPolicy(PasswordEncoder passwordEncoder,
//            StvIdmfPassword password, String pwdPolicyId) {
//        passwordPolicyChecker.setPasswordEncoder(passwordEncoder);
//        passwordPolicyChecker.setPwdPolicyId(pwdPolicyId);
//        if (!passwordPolicyChecker.checkPasswordPolicy(password)) {
//            throw new MstaccessRuntimeException(
//                    "input password does not conform to password policy. ");
//        }
//    }
}
