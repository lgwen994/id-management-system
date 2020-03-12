import Vue from 'vue'
import Vuex from 'vuex'
import common from './modules/common';
import role from './modules/role';
import rolename from './modules/rolename';
import position from './modules/position';
import orgReference from './modules/orgReference.js';
import titleReference from './modules/titleReference.js';
import positionReference from './modules/positionReference.js';
import roleReference from './modules/roleReference.js';
import companyReference from './modules/companyReference.js';
import userReference from './modules/userReference.js';
import policy from './modules/policy';
import passwordPolicy from './modules/passwordPolicy';
import company from './modules/company';
import companyName from './modules/companyName';
import user from './modules/user';
import userName from './modules/userName';
import loginPolicy from './modules/loginPolicy';
import rule from './modules/rule';
import subject from './modules/subject';
import resource from './modules/resource';
import action from './modules/action';
import condition from './modules/condition';
import title from './modules/title';
import titleName from './modules/titleName';
import org from './modules/org';
import orgName from './modules/orgName';
import userLoginInfo from './modules/userLoginInfo';

Vue.use(Vuex)

// ストアオブジェクトを定義
export default new Vuex.Store({
    modules: {
        common: common,
        role: role,
        rolename: rolename,
        position: position,
        orgReference,
        titleReference,
        positionReference,
        roleReference,
        companyReference,
        userReference,
        policy: policy,
        passwordPolicy: passwordPolicy,
        company: company,
        companyName: companyName,
        user:user,
        userName:userName,
        loginPolicy: loginPolicy,
        rule: rule,
        subject: subject,
        resource: resource,
        action: action,
        condition: condition,
        title: title,
        titleName : titleName,
        org: org,
        orgName : orgName,
        userLoginInfo : userLoginInfo
    }
});
