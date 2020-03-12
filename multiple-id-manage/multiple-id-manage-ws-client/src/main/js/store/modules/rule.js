import axios from 'axios';
import {formatDate} from '../../common';
import store from '../store';

export default {
    namespaced: true,
    state: {
        searchForm: {
            policyMst: {
                policyId: null,
                policyCode: null
            },
            ruleCode: null
        },
        form: {
            ruleId: '',
            policyId: '',
            policyMst: {
                policyId: '',
                policyCode: ''
            },
            ruleCode:'',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            versionNo: ''
        },
        initialData: {
            ruleId: '',
            policyId: '',
            policyMst: {
                policyId: '',
                policyCode: ''
            },
            ruleCode:'',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            versionNo: ''
        },
        ruleList: null,
        subjectList: null,
        resourceList: null,
        actionList: null,
        conditionList: null,
        conditionSize: '',
        actionSize: '',
        resourceSize: '',
        subjectSize: '',
        pageCondition: '',
        pageAction: '',
        pageResource: '',
        pageSubject: '',
        selectedList: [],
        prevPage:'',
        editable: false,
        searchResultVisible: false,
        sortKey: '',
        errorMessage:''
    },
    mutations: {
        setRuleList(state, data) {
            state.ruleList = data;
        },
        clearForm(state) {
        	state.form.ruleId = '';
            state.form.ruleCode = '';
        },
        clearSearchForm(state) {
            state.searchForm.policyMst.policyId = null;
            state.searchForm.policyMst.policyCode = null;
            state.searchForm.ruleCode = null;
        },
        setForm(state, data) {
            state.form.ruleId =  data.ruleId;
            state.form.policyId = data.policyId;
            state.form.policyMst.policyId = data.policyId;
            state.form.ruleCode =  data.ruleCode;
            state.form.createdTime =  data.createdTime;
            state.form.createdUser =  data.createdUser;
            state.form.updatedTime =  data.updatedTime;
            state.form.updatedUser =  data.updatedUser;
            state.form.versionNo =  data.versionNo;
        },
        setInitialData(state, data) {
            state.initialData.ruleId =  data.ruleId;
            state.initialData.policyId = data.policyId;
            state.initialData.policyMst.policyId = data.policyId;
            state.initialData.ruleCode =  data.ruleCode;
            state.initialData.createdTime =  data.createdTime;
            state.initialData.createdUser =  data.createdUser;
            state.initialData.updatedTime =  data.updatedTime;
            state.initialData.updatedUser =  data.updatedUser;
            state.initialData.versionNo =  data.versionNo;
        },
        setPolicy(state, data) {
            state.form.policyMst.policyId = data.policyId;
            state.form.policyMst.policyCode = data.policyCode;
        },
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        setRuleId(state, data) {
            state.form.ruleId = data;
        },
        setPrevPage(state, data) {
            state.prevPage = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setSearchResultVisible(state, data) {
            state.searchResultVisible = true;
        },
        setSubjectList(state, data) {
            state.subjectList = data;
        },
        setResourceList(state, data) {
            state.resourceList = data;
        },
        setActionList(state, data) {
            state.actionList = data;
        },
        setConditionList(state, data) {
            state.conditionList = data;
        },
        setConditionSize(state, data) {
            state.conditionSize = data;
        },
        setActionSize(state, data) {
            state.actionSize = data;
        },
        setSubjectSize(state, data) {
            state.subjectSize = data;
        },
        setResourceSize(state, data) {
            state.resourceSize = data;
        },
        setPageCondition(state, data) {
            state.pageCondition = data;
        },
        setPageAction(state, data) {
            state.pageAction = data;
        },
        setPageResource(state, data) {
            state.pageResource = data;
        },
        setPageSubject(state, data) {
            state.pageSubject = data;
        },
        // エラー情報設定
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setSortKey(state, data) {
            state.sortKey = data;
        }
    },
    actions: {
        showRule(context, ruleId) {
            return axios({
                method: 'get',
                url: '/idmf_rules/' + ruleId
            }).then(function(response) {
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);
                context.commit('setPageSubject', 1);
                context.dispatch('searchSubjectList', response.data.ruleId);
                context.commit('setPageResource', 1);
                context.dispatch('searchResourceList', response.data.ruleId);
                context.commit('setPageAction', 1);
                context.dispatch('searchActionList', response.data.ruleId);
                context.commit('setPageCondition', 1);
                context.dispatch('searchConditionList', response.data.ruleId);
                console.log(response);
                return axios({
                    method: 'get',
                    url: '/idmf_policies/' + response.data.policyId
                }).then(function(response) {
                    context.commit('setPolicy', response.data);
                    console.log(response);
                });
            });
        },
        setPageSubject(context, page) {
            context.commit('setPageSubject', page);
        },
        setPageResource(context, page) {
            context.commit('setPageResource', page);
        },
        setPageAction(context, page) {
            context.commit('setPageAction', page);
        },
        setPageCondition(context, page) {
            context.commit('setPageCondition', page);
        },
        searchSubjectList(context, ruleId) {
            axios({
                method: 'post',
                url: '/idmf_subjects/search',
                data: {
                    ruleId: ruleId
                },
                params: {
                    pageNo: context.state.pageSubject,
                    pageSize: 10
                }
            }).then(function(response) {
                context.commit('setSubjectList', response.data.data);
                context.commit('setSubjectSize', response.data.paging.total);
                console.log(response);
            });
        },
        searchResourceList(context, ruleId) {
            axios({
                method: 'post',
                url: '/idmf_resources/search',
                data: {
                    ruleId: ruleId
                },
                params: {
                    pageNo: context.state.pageResource,
                    pageSize: 10
                }
            }).then(function(response) {
                context.commit('setResourceList', response.data.data);
                context.commit('setResourceSize', response.data.paging.total);
                console.log(response);
            });
        },
        searchActionList(context, ruleId) {
            axios({
                method: 'post',
                url: '/idmf_actions/search',
                data: {
                    ruleId: ruleId
                },
                params: {
                    pageNo: context.state.pageAction,
                    pageSize: 10
                }
            }).then(function(response) {
                context.commit('setActionList', response.data.data);
                context.commit('setActionSize', response.data.paging.total);
                console.log(response);
            });
        },
        searchConditionList(context, ruleId) {
            axios({
                method: 'post',
                url: '/idmf_conditions/search',
                data: {
                    ruleId: ruleId
                },
                params: {
                    pageNo: context.state.pageCondition,
                    pageSize: 10
                }
            }).then(function(response) {
                context.commit('setConditionList', response.data.data);
                context.commit('setConditionSize', response.data.paging.total);
                console.log(response);
            });
        },
        clearForm(context) {
            context.commit('clearForm');
        },
        updateRule(context) {
            return axios({
                method: 'put',
                url: '/idmf_rules/',
                data: {
                    ...context.state.form,
                    updatedUser: context.rootState.common.user,
                }
            }).then(function(response) {
                console.log(response);
                context.commit("setForm", response.data);
                context.commit('setInitialData', response.data);
            });
        },
        deleteRule(context) {
            return axios({
                method: 'post',
                url: '/idmf_rules/bulk_delete',
                data: context.state.selectedList
            }).then(function(response) {
                console.log(response);
                context.dispatch('policy/showPolicy', context.state.form.policyMst.policyId, {root: true});
            }).catch(function(error) {
                if(error.response) {
                    context.commit("setErrorMessage", error.response.data.detail);
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else {
                    console.log(error.config);
                }
                resolve();
            });
        },
        registRule(context) {
            return axios({
                method: 'post',
                url: '/idmf_rules/',
                data: {
                    ...context.state.form,
                    "policyId": context.state.form.policyMst.policyId,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                context.commit("setRuleId", response.data.ruleId);
                console.log(response);
            });
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        resetForm(context) {
            context.commit('setForm', context.state.initialData);
        },
        setPrevPage(context, data) {
            context.commit('setPrevPage', data);
        },
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        setSearchResultVisible(context, data) {
            context.commit('setSearchResultVisible', data);
        },
        setPolicy(context) {
            var data = {
                policyId: store.state.route.params.policyId,
                policyCode: store.state.route.params.policyCode,
            }
            context.commit('setPolicy', data);
        },
        setSortKey(context, data) {
            context.commit('setSortKey', data);
        }
    }
}
