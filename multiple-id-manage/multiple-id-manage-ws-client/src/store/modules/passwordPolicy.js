import axios from 'axios';

export default {
    namespaced: true,
    state: {
        passwordPolicySearchForm: {
            passwordPolicyCode: null,
            companyCode: null,
            activeStartTime: null,
            activeEndTime: null,
            passwordMinLength: null,
            passwordLetterType: null,
            passwordMinLetterType: null,
            passwordInHistory: null
        },
        passwordPolicyForm: {
            passwordPolicyId: '',
            passwordPolicyCode: '',
            companyCode: '',
            activeStartTime: '',
            activeEndTime: '',
            passwordMinLength:'',
            passwordLetterType:'',
            passwordMinLetterType:'',
            passwordInHistory:'',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deleteFlg: '',
            versionNo: ''
        },
        initialData: {
            passwordPolicyId: '',
            passwordPolicyCode: '',
            companyCode:  '',
            activeStartTime: '',
            activeEndTime: '',
            passwordMinLength:'',
            passwordLetterType:'',
            passwordMinLetterType:'',
            passwordInHistory:'',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deleteFlg: '',
            versionNo: ''
        },
        editable: false,
        passwordPolicyList: null,
        passwordPolicySize: '',
        page: '',
        selectedList: [],
        errorMessage:'',
        searchResultVisible: false,
        sortKey: ''
    },
    mutations: {
        setPasswordPolicyForm(state, data) {
            state.passwordPolicyForm = {
                ...data,
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
            };
        },
        clearSearchForm(state) {
            state.passwordPolicySearchForm.passwordPolicyCode = null,
            state.passwordPolicySearchForm.companyCode = null,
            state.passwordPolicySearchForm.activeStartTime = null,
            state.passwordPolicySearchForm.activeEndTime = null,
            state.passwordPolicySearchForm.passwordMinLength = null,
            state.passwordPolicySearchForm.passwordLetterType = null,
            state.passwordPolicySearchForm.passwordMinLetterType = null,
            state.passwordPolicySearchForm.passwordInHistory = null
        },
        clearForm(state) {
        	state.passwordPolicyForm.passwordPolicyId = '';
            state.passwordPolicyForm.passwordPolicyCode = '';
            state.passwordPolicyForm.companyCode = '',
            state.passwordPolicyForm.activeStartTime = '';
            state.passwordPolicyForm.activeEndTime = '';
            state.passwordPolicyForm.passwordMinLength = '';
            state.passwordPolicyForm.passwordLetterType = '';
            state.passwordPolicyForm.passwordMinLetterType = '';
            state.passwordPolicyForm.passwordInHistory = '';

        },
        setPasswordPolicyId(state, data) {
            state.passwordPolicyForm.passwordPolicyId = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setSearchResultVisible(state) {
            state.searchResultVisible = true;
        },
        setActiveStartTime(state) {
            state.passwordPolicyForm.activeStartTime = new Date();
        },
        setPage(state, data) {
            state.page = data;
        },
        setPasswordPolicyList(state, data) {
            state.passwordPolicyList = data;
        },
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        setPasswordPolicySize(state, data) {
            state.passwordPolicySize = data;
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
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        setActiveStartTime(context) {
            context.commit('setActiveStartTime');
        },
        resetForm(context) {
            context.commit('setPasswordPolicyForm', context.state.initialData);
        },
        setPage(context, page) {
            context.commit('setPage', page);
        },
        async registPasswordPolicy(context) {
            const response = await axios({
                method: 'post',
                url: '/idmf_password_policies/',
                data: {
                    ...context.state.passwordPolicyForm,
                    createdUser: context.rootState.common.user
                }
            });
            context.commit("setPasswordPolicyId", response.data.passwordPolicyId);
            console.log(response);
        },
        async deletePasswordPolicy(context) {
            try {
                context.dispatch("searchPasswordPolicyList");
            }
            catch (error) {
                if (error.response) {
                    context.commit("setErrorMessage", error.response.data.detail);
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                }
                else {
                    console.log(error.config);
                }
                // resolve();
            }
        },
        showPasswordPolicy(context, passwordPolicyId) {
            axios({
                method: 'get',
                url: '/idmf_password_policies/' + passwordPolicyId
            }).then(function(response) {
                context.commit('setPasswordPolicyForm', response.data);
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
        async updatePasswordPolicy(context) {
            const response = await axios({
                method: 'put',
                url: '/idmf_password_policies/',
                data: {
                    "passwordPolicyId": context.state.passwordPolicyForm.passwordPolicyId,
                    "passwordPolicyCode": context.state.passwordPolicyForm.passwordPolicyCode,
                    "companyCode": context.state.passwordPolicyForm.companyCode,
                    "activeStartTime": context.state.passwordPolicyForm.activeStartTime,
                    "activeEndTime": context.state.passwordPolicyForm.activeEndTime,
                    "passwordMinLength": context.state.passwordPolicyForm.passwordMinLength,
                    "passwordLetterType": context.state.passwordPolicyForm.passwordLetterType,
                    "passwordMinLetterType": context.state.passwordPolicyForm.passwordMinLetterType,
                    "passwordInHistory": context.state.passwordPolicyForm.passwordInHistory,
                    "versionNo": context.state.passwordPolicyForm.versionNo
                }
            });
            console.log(response);
            context.commit("setPasswordPolicyForm", response.data);
            context.commit('setInitialData', response.data);
        },
        searchPasswordPolicyList(context) {
            return context.dispatch('searchPasswordPolicy').then((response) => {
                context.commit('setPasswordPolicySize', response.data.paging.total);
                context.commit('setPasswordPolicyList', response.data.data);
            });
        },
        searchPasswordPolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_password_policies/search',
                data: context.state.passwordPolicySearchForm,
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

