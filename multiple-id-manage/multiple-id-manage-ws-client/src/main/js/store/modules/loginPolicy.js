import axios from 'axios';

export default {
    namespaced: true,
    state: {
        loginPolicySearchForm: {
            loginPolicyCode: null,
            companyCode: null,
            activeStartTime: null,
            activeEndTime: null,
            passwordExpireTerm: null,
            passwordMaxFailure: null,
            passwordFailureResetTerm: null,
            lockoutTerm: null,
            maxSessionLimit: null,
            permitTime: null,
            permitDomain: null
        },
        loginPolicyForm: {
            loginPolicyId: '',
            loginPolicyCode: '',
            companyCode: '',
            activeStartTime: '',
            activeEndTime: '',
            passwordExpireTerm: '',
            passwordMaxFailure: '',
            passwordFailureResetTerm: '',
            lockoutTerm: '',
            maxSessionLimit: '',
            permitTime: '',
            permitDomain: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deleteFlg: '',
            versionNo: ''
        },
        initialData: {
            loginPolicyId: '',
            loginPolicyCode: '',
            companyCode: '',
            activeStartTime: '',
            activeEndTime: '',
            passwordExpireTerm: '',
            passwordMaxFailure: '',
            passwordFailureResetTerm: '',
            lockoutTerm: '',
            maxSessionLimit: '',
            permitTime: '',
            permitDomain: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deleteFlg: '',
            versionNo: ''
        },
        editable: false,
        loginPolicyList: null,
        loginPolicySize: '',
        page: '',
        errorMessage:'',
        selectedList: [],
        searchResultVisible: false,
        sortKey: ''
    },
    mutations: {
        setLoginPolicyForm(state, data) {
            state.loginPolicyForm = {
                ...data,
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
            };
        },
        clearSearchForm(state) {
            state.loginPolicySearchForm.loginPolicyCode = null;
            state.loginPolicySearchForm.companyCode = null;
            state.loginPolicySearchForm.activeStartTime = null;
            state.loginPolicySearchForm.activeEndTime = null;
            state.loginPolicySearchForm.passwordExpireTerm = null;
            state.loginPolicySearchForm.passwordMaxFailure = null;
            state.loginPolicySearchForm.passwordFailureResetTerm = null;
            state.loginPolicySearchForm.lockoutTerm = null;
            state.loginPolicySearchForm.maxSessionLimit = null;
            state.loginPolicySearchForm.permitTime = null;
            state.loginPolicySearchForm.permitDomain = null;

        },
        clearForm(state) {
        	state.loginPolicyForm.loginPolicyId = '';
            state.loginPolicyForm.loginPolicyCode = '';
            state.loginPolicyForm.companyCode = '';
            state.loginPolicyForm.activeStartTime = '';
            state.loginPolicyForm.activeEndTime = '';
            state.loginPolicyForm.passwordExpireTerm = '';
            state.loginPolicyForm.passwordMaxFailure = '';
            state.loginPolicyForm.passwordFailureResetTerm = '';
            state.loginPolicyForm.lockoutTerm = '';
            state.loginPolicyForm.maxSessionLimit = '';
            state.loginPolicyForm.permitTime = '';
            state.loginPolicyForm.permitDomain = '';

        },
        setLoginPolicyId(state, data) {
            state.loginPolicyForm.loginPolicyId = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setSearchResultVisible(state) {
            state.searchResultVisible = true;
        },
        setLoginPolicyList(state, data) {
            state.loginPolicyList = data;
        },
        setPage(state, data) {
            state.page = data;
        },
        setActiveStartTime(state) {
            state.loginPolicyForm.activeStartTime = new Date();
        },
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        setLoginPolicySize(state, data) {
            state.loginPolicySize = data;
        },
        setSortKey(state, data) {
            state.sortKey = data;
        }
    },
    actions: {
        setSearchResultVisible(context, data) {
            context.commit('setSearchResultVisible', data);
        },
        clearSearchForm(context) {
            context.commit('clearSearchForm');
        },
        clearForm(context) {
            context.commit('clearForm');
        },
        setActiveStartTime(context) {
            context.commit('setActiveStartTime');
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        resetForm(context) {
            context.commit('setLoginPolicyForm', context.state.initialData);
        },
        setPage(context, page) {
            context.commit('setPage', page);
        },
        registLoginPolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_login_policies/',
                data: {
                    ...context.state.loginPolicyForm,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                context.commit("setLoginPolicyId", response.data.loginPolicyId);
                console.log(response);
            });
        },
        deleteLoginPolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_login_policies/bulk_delete',
                data: context.state.selectedList
            }).then(function() {
                context.dispatch("searchLoginPolicyList");
            }).catch(function(error) {
                if(error.response) {
                    context.commit("setErrorMessage", error.response.data.detail);
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else {
                    console.log(error.config);
                }
                //resolve();
            });
        },
        showLoginPolicy(context, loginPolicyId) {
            axios({
                method: 'get',
                url: '/idmf_login_policies/' + loginPolicyId
            }).then(function(response) {
                context.commit('setLoginPolicyForm', response.data);
                context.commit('setInitialData', response.data);
                console.log(response.data);
            }).catch(function(error) {
                if(error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else {
                    console.log(error.config);
                }
            });
        },
        updateLoginPolicy(context) {
            return axios({
                method: 'put',
                url: '/idmf_login_policies/',
                data: {
                    "loginPolicyId": context.state.loginPolicyForm.loginPolicyId,
                    "loginPolicyCode": context.state.loginPolicyForm.loginPolicyCode,
                    "companyCode": context.state.loginPolicyForm.companyCode,
                    "activeStartTime" : context.state.loginPolicyForm.activeStartTime,
                    "activeEndTime" : context.state.loginPolicyForm.activeEndTime,
                    "passwordExpireTerm" : context.state.loginPolicyForm.passwordExpireTerm,
                    "passwordMaxFailure" : context.state.loginPolicyForm.passwordMaxFailure,
                    "passwordFailureResetTerm" : context.state.loginPolicyForm.passwordFailureResetTerm,
                    "lockoutTerm" : context.state.loginPolicyForm.lockoutTerm,
                    "maxSessionLimit" : context.state.loginPolicyForm.maxSessionLimit,
                    "permitTime" : context.state.loginPolicyForm.permitTime,
                    "permitDomain" : context.state.loginPolicyForm.permitDomain,
                    "versionNo" : context.state.loginPolicyForm.versionNo
                }
            }).then(function(response) {
                console.log(response);
                context.commit("setLoginPolicyForm", response.data);
                context.commit('setInitialData', response.data);
            });
        },
        searchLoginPolicyList(context) {
            return context.dispatch('searchLoginPolicy').then((response) => {
                context.commit('setLoginPolicySize', response.data.paging.total);
                context.commit('setLoginPolicyList', response.data.data);
            });
        },
        searchLoginPolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_login_policies/search',
                data: context.state.loginPolicySearchForm,
                params: {
                    pageNo: context.state.page,
                    pageSize: 10,
                    sort: context.state.sortKey
                }
            });
        },
        setSortKey(context, data) {
            context.commit('setSortKey', data);
        }
    }
}

