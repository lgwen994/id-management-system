import Vue from "vue";
import VueRouter from "vue-router";

import RolenameRegist from '../views/RolenameRegist.vue';
import RolenameUpdate from '../views/RolenameUpdate.vue';
import PositionRegist from '../views/PositionRegist.vue';
import PositionUpdate from '../views/PositionUpdate.vue';
import RoleSearch from '../views/RoleSearch.vue';
import RoleSearchResult from '../views/RoleSearchResult.vue';
import RoleRegist from '../views/RoleRegist.vue';
import RoleUpdate from '../views/RoleUpdate.vue';
import TopMenu from '../views/TopMenu.vue';
import Header from '../views/Header.vue';
import PolicyRegist from '../views/PolicyRegist.vue';
import PolicySearch from '../views/PolicySearch.vue';
import PolicySearchResult from '../views/PolicySearchResult.vue';
import PolicyUpdate from '../views/PolicyUpdate.vue';
import PasswordPolicyRegist from '../views/PasswordPolicyRegist.vue';
import PasswordPolicySearch from '../views/PasswordPolicySearch.vue';
import PasswordPolicySearchResult from '../views/PasswordPolicySearchResult.vue';
import PasswordPolicyUpdate from '../views/PasswordPolicyUpdate.vue';
import CompanySearch from '../views/CompanySearch.vue';
import CompanySearchResult from '../views/CompanySearchResult.vue';
import CompanyRegist from '../views/CompanyRegist.vue';
import CompanyUpdate from '../views/CompanyUpdate.vue';
import CompanyNameRegist from '../views/CompanyNameRegist.vue';
import CompanyNameUpdate from '../views/CompanyNameUpdate.vue';
import UserSearch from '../views/UserSearch.vue';
import UserSearchResult from '../views/UserSearchResult.vue';
import UserRegist from '../views/UserRegist.vue';
import UserUpdate from '../views/UserUpdate.vue';
import UserNameRegist from '../views/UserNameRegist.vue';
import UserNameUpdate from '../views/UserNameUpdate.vue';
import LoginPolicyRegist from '../views/LoginPolicyRegist.vue';
import LoginPolicySearch from '../views/LoginPolicySearch.vue';
import LoginPolicySearchResult from '../views/LoginPolicySearchResult.vue';
import LoginPolicyUpdate from '../views/LoginPolicyUpdate.vue';
import TitleSearch from '../views/TitleSearch.vue';
import TitleRegist from '../views/TitleRegist.vue';
import TitleUpdate from '../views/TitleUpdate.vue';
import TitleNameRegist from '../views/TitleNameRegist.vue';
import TitleNameUpdate from '../views/TitleNameUpdate.vue';
import OrgSearch from '../views/OrgSearch.vue';
import OrgRegist from '../views/OrgRegist.vue';
import OrgUpdate from '../views/OrgUpdate.vue';
import OrgNameRegist from '../views/OrgNameRegist.vue';
import OrgNameUpdate from '../views/OrgNameUpdate.vue';
import UserLoginInfoRegist from '../views/UserLoginInfoRegist.vue';
import UserLoginInfoSearch from '../views/UserLoginInfoSearch.vue';
import UserLoginInfoSearchResult from '../views/UserLoginInfoSearchResult.vue';
import UserLoginInfoUpdate from '../views/UserLoginInfoUpdate.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'topMenu',
    components: {
        default: TopMenu,
        header: Header
    }
  },
  {
      path: '/rolename-regist',
      name: 'rolenameRegist',
      components: {
          default: RolenameRegist,
          header: Header
      }
  },
  {
      path: '/rolename-update',
      name: 'rolenameUpdate',
      components: {
          default: RolenameUpdate,
          header: Header
      }
  },
  {
      path: '/position-regist',
      name: 'positionRegist',
      components: {
          default: PositionRegist,
          header: Header
      }
  },
  {
      path: '/position-update',
      name: 'positionUpdate',
      components: {
          default: PositionUpdate,
          header: Header
      }
  },
  {
      path: '/role-search',
      name: 'roleSearch',
      components: {
          default: RoleSearch,
          header: Header
      }
  },
  {
      path: '/role-search-result',
      name: 'roleSearchResult',
      component: RoleSearchResult
  },
  {
      path: '/role-regist',
      name: 'roleRegist',
      components: {
          default: RoleRegist,
          header: Header
      }
  },
  {
      path: '/role-update',
      name: 'roleUpdate',
      components: {
          default: RoleUpdate,
          header: Header
      }
  },
  {
      path: '/policy-regist',
      name: 'policyRegist',
      components: {
          default: PolicyRegist,
          header: Header
      }
  },
  {
      path: '/policy-search',
      name: 'policySearch',
      components: {
          default: PolicySearch,
          header: Header
      }
  },
  {
      path: '/policy-update',
      name: 'policyUpdate',
      components: {
          default: PolicyUpdate,
          header: Header
      }
  },
  {
      path: '/policy-search-result',
      name: 'policySearchResult',
      component: PolicySearchResult
  },
  {
      path: '/passwordPolicy-regist',
      name: 'passwordPolicyRegist',
      components: {
          default: PasswordPolicyRegist,
          header: Header
      }
  },
  {
      path: '/passwordPolicy-search',
      name: 'passwordPolicySearch',
      components: {
          default: PasswordPolicySearch,
          header: Header
      }
  },
  {
      path: '/passwordPolicy-search-result',
      name: 'passwordPolicySearchResult',
      component: PasswordPolicySearchResult
  },
  {
      path: '/passwprdPolicy-update',
      name: 'passwordPolicyUpdate',
      components: {
          default: PasswordPolicyUpdate,
          header: Header
      }
  },
  {
      path: '/company-search',
      name: 'companySearch',
      components: {
          default: CompanySearch,
          header: Header
      }
  },
  {
      path: '/company-search-result',
      name: 'companySearchResult',
      component: CompanySearchResult
  },
  {
      path: '/company-regist',
      name: 'companyRegist',
      components: {
          default: CompanyRegist,
          header: Header
      }
  },
  {
      path: '/company-update',
      name: 'companyUpdate',
      components: {
          default: CompanyUpdate,
          header: Header
      }
  },
  {
      path: '/companyName-regist',
      name: 'companyNameRegist',
      components: {
          default: CompanyNameRegist,
          header: Header
      }
  },
  {
      path: '/companyName-update',
      name: 'companyNameUpdate',
      components: {
          default: CompanyNameUpdate,
          header: Header
      }
  },
  {
      path: '/user-search',
      name: 'userSearch',
      components: {
          default: UserSearch,
          header: Header
      }
  },
  {
      path: '/user-search-result',
      name: 'userSearchResult',
      component: UserSearchResult
  },
  {
      path: '/user-regist',
      name: 'userRegist',
      components: {
          default: UserRegist,
          header: Header
      }
  },
  {
      path: '/user-update',
      name: 'userUpdate',
      components: {
          default: UserUpdate,
          header: Header
      }
  },
  {
      path: '/userName-regist',
      name: 'userNameRegist',
      components: {
          default: UserNameRegist,
          header: Header
      }
  },
  {
      path: '/userName-update',
      name: 'userNameUpdate',
      components: {
          default: UserNameUpdate,
          header: Header
      }
  },
  {
      path: '/loginPolicy-regist',
      name: 'loginPolicyRegist',
      components: {
          default: LoginPolicyRegist,
          header: Header
      }
  },
  {
      path: '/loginPolicy-search',
      name: 'loginPolicySearch',
      components: {
          default: LoginPolicySearch,
          header: Header
      }
  },
  {
      path: '/loginPolicy-search-result',
      name: 'loginPolicySearchResult',
      component: LoginPolicySearchResult
  },
  {
      path: '/loginPolicy-update',
      name: 'loginPolicyUpdate',
      components: {
          default: LoginPolicyUpdate,
          header: Header
      }
  },
  {
      path: '/title-search',
      name: 'titleSearch',
      components: {
          default: TitleSearch,
          header: Header
      }
  },
  {
      path: '/title-regist',
      name: 'titleRegist',
      components: {
          default: TitleRegist,
          header: Header
      }
  },
  {
      path: '/title-update',
      name: 'titleUpdate',
      components: {
          default: TitleUpdate,
          header: Header
      }
  },
  {
      path: '/titleName-regist',
      name: 'titleNameRegist',
      components: {
          default: TitleNameRegist,
          header: Header
      }
  },
  {
      path: '/titleName-update',
      name: 'titleNameUpdate',
      components: {
          default: TitleNameUpdate,
          header: Header
      }
  },
  {
      path: '/org-search',
      name: 'orgSearch',
      components: {
          default: OrgSearch,
          header: Header
      }
  },
  {
      path: '/org-regist',
      name: 'orgRegist',
      components: {
          default: OrgRegist,
          header: Header
      }
  },
  {
      path: '/org-update',
      name: 'orgUpdate',
      components: {
          default: OrgUpdate,
          header: Header
      }
  },
  {
      path: '/orgName-regist',
      name: 'orgNameRegist',
      components: {
          default: OrgNameRegist,
          header: Header
      }
  },
  {
      path: '/orgName-update',
      name: 'orgNameUpdate',
      components: {
          default: OrgNameUpdate,
          header: Header
      }
  },
  {
      path: '/userLoginInfo-regist',
      name: 'userLoginInfoRegist',
      components: {
          default: UserLoginInfoRegist,
          header: Header
      }
  },
  {
      path: '/userLoginInfo-search',
      name: 'userLoginInfoSearch',
      components: {
          default: UserLoginInfoSearch,
          header: Header
      }
  },
  {
      path: '/userLoginInfo-search-result',
      name: 'userLoginInfoSearchResult',
      component: UserLoginInfoSearchResult
  },
  {
      path: '/userLoginInfo-update',
      name: 'userLoginInfoUpdate',
      components: {
          default: UserLoginInfoUpdate,
          header: Header
      }
  }
];

const router = new VueRouter({
  routes
});

export default router;
