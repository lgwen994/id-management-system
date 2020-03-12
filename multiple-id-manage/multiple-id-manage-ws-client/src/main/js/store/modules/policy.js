import axios from 'axios';

export default {
    namespaced: true,
    state: {
        policySearchForm: {
            policyCode: null,
            effect: null
        },
        policyForm: {
            policyId: '',
            policyCode: '',
            effect: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            versionNo: ''
        },
        initialData: {
            policyId: '',
            policyCode: '',
            effect: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            versionNo: ''
        },
        ruleList: null,
        editable: false,
        policySize: '',
        ruleSize:'',
        page: '',
        pageRule: '',
        policyList: null,
        selectedList: [],
        searchResultVisible: false,
        errorMessage:'',
        sortKey: ''
    },
    mutations: {
        setPolicyForm(state, data) {
            state.policyForm = {
                ...data,
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
            };
        },
        setPolicyId(state, data) {
            state.policyForm.policyId = data;
        },
        setRuleList(state, data) {
            state.ruleList = data;
        },
        clearSearchForm(state) {
            state.policySearchForm.policyCode = null;
            state.policySearchForm.effect = null;
        },
        clearForm(state) {
        	state.policyForm.policyId = '';
            state.policyForm.policyCode = '';
            state.policyForm.effect = '';
        },
        setPolicyList(state, data) {
            state.policyList = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setPolicySize(state, data) {
            state.policySize = data;
        },
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        setPage(state, data) {
            state.page = data;
        },
        setPageRule(state, data) {
            state.pageRule = data;
        },
        setRuleSize(state, data) {
            state.ruleSize = data;
        },
        setSearchResultVisible(state, data) {
            state.searchResultVisible = true;
        },
        setErrorMessage(state, data){
            state.errorMessage = data;
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
        resetForm(context) {
            context.commit('setPolicyForm', context.state.initialData);
        },
        registPolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_policies/',
                data: {
                    ...context.state.policyForm,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                context.commit('setPolicyId', response.data.policyId);
                console.log(response);
            });
        },
        setPage(context, page) {
            context.commit('setPage', page);
        },
        setPageRule(context, page) {
            context.commit('setPageRule', page);
        },
        showPolicy(context, policyId) {
            return axios({
                method: 'get',
                url: '/idmf_policies/' + policyId
            }).then(function(response) {
                context.commit('setPolicyForm', response.data);
                context.commit('setInitialData', response.data);
                console.log(response.data);
                context.commit('setPageRule', 1);
                context.dispatch('searchRuleList', policyId);
            });
        },
        searchRuleList(context, policyId) {
            return axios({
                method: 'post',
                url: '/idmf_rules/search',
                data: {
                    policyId: policyId
                },
                params: {
                    pageNo: context.state.pageRule,
                    pageSize: 10
                }
            }).then(function(response) {
                context.commit('setRuleList', response.data.data);
                context.commit('setRuleSize', response.data.paging.total);
            });
        },
        updatePolicy(context) {
            return axios({
                method: 'put',
                url: '/idmf_policies/',
                data: {
                    "policyId": context.state.policyForm.policyId,
                    "policyCode": context.state.policyForm.policyCode,
                    "effect": context.state.policyForm.effect,
                    "versionNo" : context.state.policyForm.versionNo
                }
            }).then(function(response) {
                console.log(response);
                context.commit("setPolicyForm", response.data);
                context.commit('setInitialData', response.data);
            });
        },
        deletePolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_policies/bulk_delete',
                data: context.state.selectedList
            }).then(function(response) {
                context.dispatch("searchPolicyList");
            }).catch(function(error) {
                if(error.response) {
                    context.commit("setErrorMessage", error.response.data.detail);
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else {
                    console.log(error.config);
                }
            });
        },
        searchPolicyList(context) {
            return context.dispatch('searchPolicy').then((response) => {
                context.commit('setPolicySize', response.data.paging.total);
                context.commit('setPolicyList', response.data.data);
            });
        },
        searchPolicy(context) {
            return axios({
                method: 'post',
                url: '/idmf_policies/search',
                data: context.state.policySearchForm,
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

