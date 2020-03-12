import axios from 'axios';
import {formatDate} from '../../common';
import store from '../store';

export default {
    namespaced: true,
    state: {
        searchForm: {
            ruleMst: {
                ruleId: null,
                ruleCode: null
            },
            subjectCode: null,
            valueType: null,
            evalType: null,
            value: null
        },
        form: {
            subjectId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            subjectCode: '',
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
            subjectId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            subjectCode: '',
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
        subjectSelectedList: [],
        prevPage:'',
        editable: false,
        errorMessage:'',
        searchResultVisible: false
    },
    mutations: {
        clearForm(state) {
        	 state.form.subjectId = '';
            state.form.subjectCode = '';
            state.form.valueType = '';
            state.form.evalType = '';
            state.form.value = '';
        },
        clearSearchForm(state) {
            state.searchForm.ruleMst.ruleId = null;
            state.searchForm.ruleMst.ruleCode = null;
            state.searchForm.subjectCode = null;
            state.searchForm.valueType = null;
            state.searchForm.evalType = null;
            state.searchForm.value = null;
        },
        setForm(state, data) {
            state.form.subjectId =  data.subjectId;
            state.form.ruleId = data.ruleId;
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.subjectCode =  data.subjectCode;
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
            state.initialData.subjectId =  data.subjectId;
            state.initialData.ruleId = data.ruleId;
            state.initialData.ruleMst.ruleId = data.ruleId;
            state.initialData.subjectCode =  data.subjectCode;
            state.initialData.valueType =  data.valueType;
            state.initialData.evalType =  data.evalType;
            state.initialData.value =  data.value;
            state.initialData.createdTime =  data.createdTime;
            state.initialData.createdUser =  data.createdUser;
            state.initialData.updatedTime =  data.updatedTime;
            state.initialData.updatedUser =  data.updatedUser;
            state.initialData.versionNo =  data.versionNo;
        },
        setSubjectId(state, data) {
            state.form.subjectId = data;
        },
        setRule(state, data) {
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.ruleMst.ruleCode = data.ruleCode;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        // エラー情報設定
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setSubjectSelectedList(state, data) {
            state.subjectSelectedList = data;
        },
    },
    actions: {
        showSubject(context, subjectId) {
            return axios({
                method: 'get',
                url: '/idmf_subjects/' + subjectId
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
        updateSubject(context) {
            return axios({
                method: 'put',
                url: '/idmf_subjects/',
                data: {
                    "subjectId": context.state.form.subjectId,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    "subjectCode": context.state.form.subjectCode,
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
        deleteSubject(context) {
            return axios({
                method: 'post',
                url: '/idmf_subjects/bulk_delete',
                data: context.state.subjectSelectedList
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
                resolve();
            });
        },
        registSubject(context) {
            return axios({
                method: 'post',
                url: '/idmf_subjects/',
                data: {
                    ...context.state.form,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                context.commit("setSubjectId", response.data.subjectId);
                console.log(response);
            });
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        setSubjectSelectedList(context, data) {
            context.commit('setSubjectSelectedList', data);
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
