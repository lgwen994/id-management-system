import axios from 'axios';
import store from '../';

export default {
    namespaced: true,
    state: {
        searchForm: {
            ruleMst: {
                ruleId: null,
                ruleCode: null
            },
            actionCode: null,
            valueType: null,
            evalType: null,
            value: null
        },
        form: {
            actionId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            actionCode: '',
            valueType: '',
            evalType: '',
            value: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            versionNo: ''
        },
        initialData: {
            actionId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            actionCode: '',
            valueType: '',
            evalType: '',
            value: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            versionNo: ''
        },
        selectedList: [],
        actionSelectedList: [],
        prevPage:'',
        editable: false,
        errorMessage:'',
        searchResultVisible: false
    },
    mutations: {
        clearForm(state) {
        	state.form.actionId = '';
            state.form.actionCode = '';
            state.form.valueType = '';
            state.form.evalType = '';
            state.form.value = '';
        },
        clearSearchForm(state) {
            state.searchForm.ruleMst.ruleId = null;
            state.searchForm.ruleMst.ruleCode = null;
            state.searchForm.actionCode = null;
            state.searchForm.valueType = null;
            state.searchForm.evalType = null;
            state.searchForm.value = null;
        },
        setForm(state, data) {
            state.form.actionId =  data.actionId;
            state.form.ruleId = data.ruleId;
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.actionCode =  data.actionCode;
            state.form.valueType =  data.valueType;
            state.form.evalType =  data.evalType;
            state.form.value =  data.value;
            state.form.createdTime =  data.createdTime;
            state.form.createdUser =  data.createdUser;
            state.form.updatedTime =  data.updatedTime;
            state.form.updatedUser =  data.updatedUser;
            state.form.versionNo =  data.versionNo;
        },
        setInitialData(state, data) {
            state.initialData.actionId =  data.actionId;
            state.initialData.ruleId = data.ruleId;
            state.initialData.ruleMst.ruleId = data.ruleId;
            state.initialData.actionCode =  data.actionCode;
            state.initialData.valueType =  data.valueType;
            state.initialData.evalType =  data.evalType;
            state.initialData.value =  data.value;
            state.initialData.createdTime =  data.createdTime;
            state.initialData.createdUser =  data.createdUser;
            state.initialData.updatedTime =  data.updatedTime;
            state.initialData.updatedUser =  data.updatedUser;
            state.initialData.versionNo =  data.versionNo;
        },
        setActionId(state, data) {
            state.form.actionId = data;
        },
        setRule(state, data) {
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.ruleMst.ruleCode = data.ruleCode;
        },
        // Error情報設定
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setActionSelectedList(state, data) {
            state.actionSelectedList = data;
        },
    },
    actions: {
        showAction(context, actionId) {
            return axios({
                method: 'get',
                url: '/idmf_actions/' + actionId
            }).then(function(response) {
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);
                console.log(response);
                return axios({
                    method: 'get',
                    url: '/idmf_rules/' + response.data.ruleId
                }).then(function(response) {
                    context.commit('setRule', response.data);
                    console.log(response);
                });
            });
        },
        clearForm(context) {
            context.commit('clearForm');
        },
        updateAction(context) {
            return axios({
                method: 'put',
                url: '/idmf_actions/',
                data: {
                    "actionId": context.state.form.actionId,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    "actionCode": context.state.form.actionCode,
                    "valueType": context.state.form.valueType,
                    "evalType": context.state.form.evalType,
                    "value": context.state.form.value,
                    "versionNo" : context.state.form.versionNo,
                    updateUser: context.rootState.common.user
                }
            }).then(function(response) {
                console.log(response);
                context.commit("setForm", response.data);
                context.commit('setInitialData', response.data);
            });
        },
        deleteAction(context) {
            return axios({
                method: 'post',
                url: '/idmf_actions/bulk_delete',
                data: context.state.actionSelectedList
            }).then(function(response) {
                console.log(response);
                context.dispatch('rule/showRule', context.state.form.ruleMst.ruleId, {root: true});
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
        registAction(context) {
            return axios({
                method: 'post',
                url: '/idmf_actions/',
                data: {
                    ...context.state.form,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                context.commit("setActionId", response.data.actionId);
                console.log(response);
            });
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        setActionSelectedList(context, data) {
            context.commit('setActionSelectedList', data);
        },
        resetForm(context) {
            context.commit('setForm', context.state.initialData);
        },
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        setRule(context) {
            var data = {
                ruleId: store.state.route.params.ruleId,
                ruleCode: store.state.route.params.ruleCode
            }
            context.commit('setRule', data);
        }
    }
}
