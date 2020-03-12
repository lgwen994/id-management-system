import Vue from 'vue'
import VueRouter from 'vue-router'

import RolenameRegist from './components/RolenameRegist.vue';
import RolenameUpdate from './components/RolenameUpdate.vue';
import PositionRegist from './components/PositionRegist.vue';
import PositionUpdate from './components/PositionUpdate.vue';
import RoleSearch from './components/RoleSearch.vue';
import RoleSearchResult from './components/RoleSearchResult.vue';
import RoleRegist from './components/RoleRegist.vue';
import RoleUpdate from './components/RoleUpdate.vue';
import TopMenu from './components/TopMenu.vue';
import Header from './components/Header.vue';
import PolicyRegist from './components/PolicyRegist.vue';
import PolicySearch from './components/PolicySearch.vue';
import PolicySearchResult from './components/PolicySearchResult.vue';
import PolicyUpdate from './components/PolicyUpdate.vue';
import PasswordPolicyRegist from './components/PasswordPolicyRegist.vue';
import PasswordPolicySearch from './components/PasswordPolicySearch.vue';
import PasswordPolicySearchResult from './components/PasswordPolicySearchResult.vue';
import PasswordPolicyUpdate from './components/PasswordPolicyUpdate.vue';
import CompanySearch from './components/CompanySearch.vue';
import CompanySearchResult from './components/CompanySearchResult.vue';
import CompanyRegist from './components/CompanyRegist.vue';
import CompanyUpdate from './components/CompanyUpdate.vue';
import CompanyNameRegist from './components/CompanyNameRegist.vue';
import CompanyNameUpdate from './components/CompanyNameUpdate.vue';
import UserSearch from './components/UserSearch.vue';
import UserSearchResult from './components/UserSearchResult.vue';
import UserRegist from './components/UserRegist.vue';
import UserUpdate from './components/UserUpdate.vue';
import UserNameRegist from './components/UserNameRegist.vue';
import UserNameUpdate from './components/UserNameUpdate.vue';
import LoginPolicyRegist from './components/LoginPolicyRegist.vue';
import LoginPolicySearch from './components/LoginPolicySearch.vue';
import LoginPolicySearchResult from './components/LoginPolicySearchResult.vue';
import LoginPolicyUpdate from './components/LoginPolicyUpdate.vue';
import TitleSearch from './components/TitleSearch.vue';
import TitleRegist from './components/TitleRegist.vue';
import TitleUpdate from './components/TitleUpdate.vue';
import TitleNameRegist from './components/TitleNameRegist.vue';
import TitleNameUpdate from './components/TitleNameUpdate.vue';
import OrgSearch from './components/OrgSearch.vue';
import OrgRegist from './components/OrgRegist.vue';
import OrgUpdate from './components/OrgUpdate.vue';
import OrgNameRegist from './components/OrgNameRegist.vue';
import OrgNameUpdate from './components/OrgNameUpdate.vue';
import UserLoginInfoRegist from './components/UserLoginInfoRegist.vue';
import UserLoginInfoSearch from './components/UserLoginInfoSearch.vue';
import UserLoginInfoSearchResult from './components/UserLoginInfoSearchResult.vue';
import UserLoginInfoUpdate from './components/UserLoginInfoUpdate.vue';

Vue.use(VueRouter)

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

export default router
