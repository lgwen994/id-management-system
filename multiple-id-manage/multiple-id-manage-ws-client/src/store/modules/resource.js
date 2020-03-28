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
            resourceCode: null,
            valueType: null,
            evalType: null,
            value: null
        },
        form: {
            resourceId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            resourceCode: '',
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
            resourceId: '',
            ruleId: '',
            ruleMst: {
                ruleId: '',
                ruleCode: ''
            },
            resourceCode: '',
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
        resourceSelectedList: [],
        prevPage:'',
        editable: false,
        errorMessage:'',
        searchResultVisible: false
    },
    mutations: {
        clearForm(state) {
        	state.form.resourceId = '';
            state.form.resourceCode = '';
            state.form.valueType = '';
            state.form.evalType = '';
            state.form.value = '';
        },
        clearSearchForm(state) {
            state.searchForm.ruleMst.ruleId = null;
            state.searchForm.ruleMst.ruleCode = null;
            state.searchForm.resourceCode = null;
            state.searchForm.valueType = null;
            state.searchForm.evalType = null;
            state.searchForm.value = null;
        },
        setForm(state, data) {
            state.form.resourceId =  data.resourceId;
            state.form.ruleId = data.ruleId;
            state.form.ruleMst.ruleId = data.ruleId;
            state.form.resourceCode =  data.resourceCode;
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
            state.initialData.resourceId =  data.resourceId;
            state.initialData.ruleId = data.ruleId;
            state.initialData.ruleMst.ruleId = data.ruleId;
            state.initialData.resourceCode =  data.resourceCode;
            state.initialData.valueType =  data.valueType;
            state.initialData.evalType =  data.evalType;
            state.initialData.value =  data.value;
            state.initialData.createdTime =  data.createdTime;
            state.initialData.createdUser =  data.createdUser;
            state.initialData.updatedTime =  data.updatedTime;
            state.initialData.updatedUser =  data.updatedUser;
            state.initialData.versionNo =  data.versionNo;
        },
        setResourceId(state, data) {
            state.form.resourceId = data;
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
        setResourceSelectedList(state, data) {
            state.resourceSelectedList = data;
        },
    },
    actions: {
        async showResource(context, resourceId) {
            const response = await axios({
                method: 'get',
                url: '/idmf_resources/' + resourceId
            });
            context.commit('setForm', response.data);
            context.commit('setInitialData', response.data);
            console.log(response);
            const response_1 = await axios({
                method: 'get',
                url: '/idmf_rules/' + response.data.ruleId
            });
            context.commit('setRule', response_1.data);
            console.log(response_1);
        },
        clearForm(context) {
            context.commit('clearForm');
        },
        async updateResource(context) {
            const response = await axios({
                method: 'put',
                url: '/idmf_resources/',
                data: {
                    "resourceId": context.state.form.resourceId,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    "resourceCode": context.state.form.resourceCode,
                    "valueType": context.state.form.valueType,
                    "evalType": context.state.form.evalType,
                    "value": context.state.form.value,
                    "versionNo": context.state.form.versionNo,
                    updateUser: context.rootState.common.user
                }
            });
            console.log(response);
            context.commit("setForm", response.data);
            context.commit('setInitialData', response.data);
        },
        async deleteResource(context) {
            try {
                const response = await axios({
                    method: 'post',
                    url: '/idmf_resources/bulk_delete',
                    data: context.state.resourceSelectedList
                });
                console.log(response);
                context.dispatch('rule/showRule', context.state.form.ruleMst.ruleId, { root: true });
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
            }
        },
        async registResource(context) {
            const response = await axios({
                method: 'post',
                url: '/idmf_resources/',
                data: {
                    ...context.state.form,
                    "ruleId": context.state.form.ruleMst.ruleId,
                    createdUser: context.rootState.common.user
                }
            });
            context.commit("setResourceId", response.data.resourceId);
            console.log(response);
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        setResourceSelectedList(context, data) {
            context.commit('setResourceSelectedList', data);
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
