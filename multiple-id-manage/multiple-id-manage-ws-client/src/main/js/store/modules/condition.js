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
            conditionCode: null,
            valueType: null,
            evalType: null,
            value: null
        },
        form: {
            conditionId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            conditionCode: '',
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
            conditionId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            conditionCode: '',
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
        conditionSelectedList: [],
        prevPage:'',
        errorMessage:'',
        editable: false,
        searchResultVisible: false
    },
    mutations: {
        clearForm(state) {
        	state.form.conditionId = '';
            state.form.conditionCode = '';
            state.form.valueType = '';
            state.form.evalType = '';
            state.form.value = '';
        },
        clearSearchForm(state) {
            state.searchForm.ruleMst.ruleId = null;
            state.searchForm.ruleMst.ruleCode = null;
            state.searchForm.conditionCode = null;
            state.searchForm.valueType = null;
            state.searchForm.evalType = null;
            state.searchForm.value = null;
        },
        setForm(state, data) {
            state.form.conditionId =  data.conditionId;
            state.form.ruleId = data.ruleId;
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.conditionCode =  data.conditionCode;
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
            state.initialData.conditionId =  data.conditionId;
            state.initialData.ruleId = data.ruleId;
            state.initialData.ruleMst.ruleId = data.ruleId;
            state.initialData.conditionCode =  data.conditionCode;
            state.initialData.valueType =  data.valueType;
            state.initialData.evalType =  data.evalType;
            state.initialData.value =  data.value;
            state.initialData.createdTime =  data.createdTime;
            state.initialData.createdUser =  data.createdUser;
            state.initialData.updatedTime =  data.updatedTime;
            state.initialData.updatedUser =  data.updatedUser;
            state.initialData.versionNo =  data.versionNo;
        },
        setConditionId(state, data) {
            state.form.conditionId = data;
        },
        setRule(state, data) {
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.ruleMst.ruleCode = data.ruleCode;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setConditionSelectedList(state, data) {
            state.conditionSelectedList = data;
        },
    },
    actions: {
        showCondition(context, conditionId) {
            return axios({
                method: 'get',
                url: '/idmf_conditions/' + conditionId
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
        updateCondition(context) {
            return axios({
                method: 'put',
                url: '/idmf_conditions/',
                data: {
                    "conditionId": context.state.form.conditionId,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    "conditionCode": context.state.form.conditionCode,
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
        deleteCondition(context) {
            return axios({
                method: 'post',
                url: '/idmf_conditions/bulk_delete',
                data: context.state.conditionSelectedList
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
        registCondition(context) {
            return axios({
                method: 'post',
                url: '/idmf_conditions/',
                data: {
                    ...context.state.form,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                context.commit("setConditionId", response.data.conditionId);
                console.log(response);
            });
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        setConditionSelectedList(context, data) {
            context.commit('setConditionSelectedList', data);
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
