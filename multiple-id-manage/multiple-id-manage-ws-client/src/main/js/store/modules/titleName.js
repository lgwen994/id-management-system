import axios from 'axios';
import {formatDate} from '../../common';
import store from '../';

export default {
    namespaced: true,
    state: {
        form: {
            titleNameId: '',
            titleId: '',
            titleMst: {
                titleId: '',
                titleCode: '',
                companyId: '',
                groupFlg: '',
                titleName: '',
                activeStartTime: '',
                activeEndTime: ''
            },
            locale:'',
            titleName: '',
            activeStartTime: '',
            activeEndTime: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deletedFlg: '',
            versionNo: ''
        },
        initialData: {
            titleNameId: '',
            titleId: '',
            titleMst: {
                titleId: '',
                titleCode: '',
                companyId: '',
                groupFlg: '',
                titleName: '',
                activeStartTime: '',
                activeEndTime: ''
            },
            locale:'',
            titleName: '',
            activeStartTime: '',
            activeEndTime: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deletedFlg: '',
            versionNo: ''
        },
        selectedList: [],
        editable: false
    },
    mutations: {
        clearForm(state) {
            state.form.titleNameId = '',
                state.form.locale = '';
            state.form.titleName = '';
            state.form.activeStartTime = '';
            state.form.activeEndTime = '';
        },
        setForm(state, data) {
            var titleMst = {};
            if(data.titleMst){
                titleMst = data.titleMst;
            }
            state.form = {
                ...data,
                titleMst: titleMst
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
                titleMst: {}
            };
        },
        setTitle(state, data) {
            state.form.titleId = data.titleId;
            state.form.titleMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
            state.initialData.titleId = data.titleId;
            state.initialData.titleMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        setTitleNameId(state, data) {
            state.form.titleNameId = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setActiveTime(state) {
            state.form.activeStartTime = store.state.route.params.activeStartTime;
            state.form.activeEndTime = store.state.route.params.activeEndTime;
        }
    },
    actions: {
        clearForm(context) {
            context.commit('clearForm');
        },
        async showTitleName(context, titleNameId) {
            const response = await axios({
                method: 'get',
                url: '/idmf_title_names/' + titleNameId
            });
            console.log(response.data);
            context.commit('setForm', response.data);
            context.commit('setInitialData', response.data);
            const response_1 = await axios({
                method: 'get',
                url: '/idmf_titles/' + response.data.titleId
            });
            console.log(response_1.data);
            context.commit('setTitle', response_1.data);
        },
        async updateTitleName(context) {
            const response = await axios({
                method: 'put',
                url: '/idmf_title_names/',
                data: {
                    ...context.state.form,
                    updatedUser: context.rootState.common.user
                }
            });
            console.log(response.data);
            context.commit('setForm', response.data);
            context.commit('setInitialData', response.data);
        },
        async deleteTitleName(context) {
            context.state.selectedList = context.state.selectedList.map((element) => {
                return {
                    ...element,
                    updatedUser: context.rootState.common.user
                }
            })
            const response = await axios({
                method: 'post',
                url: '/idmf_title_names/bulk_delete',
                data: context.state.selectedList
            });
            console.log(response.data);
        },
        async registTitleName(context) {
            const response = await axios({
                method: 'post',
                url: '/idmf_title_names/',
                data: {
                    ...context.state.form,
                    createdUser: context.rootState.common.user
                }
            });
            console.log(response.data);
            context.commit('setTitleNameId', response.data.titleNameId);
        },
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        resetForm(context) {
            context.commit('setForm', context.state.initialData);
        },
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        setTitle(context) {
            context.commit('setTitle', store.state.route.params);
        },
        setActiveTime(context) {
            context.commit('setActiveTime');
        }
    }
}
