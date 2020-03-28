import axios from 'axios';
import {formatDate} from '../../common';

export default {
    namespaced: true,
    state: {
        searchForm: {
            titleCode: null,
            companyId: null,
            companyMst: {
                companyId: '',
                companyCode: '',
                activeStartTime: '',
                activeEndTime: ''
            },
            groupFlg: null,
            activeStartTime: null,
            activeEndTime: null,
            titleName: null,
            titleType: null
        },
        form: {
            titleId: '',
            titleCode: '',
            companyId: '',
            companyMst: {
                companyId: '',
                companyCode: '',
                activeStartTime: '',
                activeEndTime: ''
            },
            groupFlg: '',
            activeStartTime: '',
            activeEndTime: '',
            titleName: '',
            titleType: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deletedFlg: '',
            versionNo: ''
        },
        initialData: {
            titleId: '',
            titleCode: '',
            companyId: '',
            companyMst: {
                companyId: '',
                companyCode: '',
                activeStartTime: '',
                activeEndTime: ''
            },
            groupFlg: '',
            activeStartTime: '',
            activeEndTime: '',
            titleName: '',
            titleType: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deletedFlg: '',
            versionNo: ''
        },
        titleNameList: null,
        editable: false,
        titleSize: '',
        titleList: null,
        page: '',
        searchResultVisible: false,
        selectedList: [],
        sortKey: '',
        errorMessage:''
    },
    mutations: {
        setForm(state, data) {
            var companyMst = {};
            if(data.companyMst){
                companyMst = data.companyMst;
            }
            state.form = {
                ...data,
                groupFlg: String(data.groupFlg),
                companyMst: companyMst
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
                groupFlg: String(data.groupFlg),
                companyMst: {}
            };
        },
        setTitleNameList(state, data) {
            state.titleNameList = data;
        },
        setEditable(state, data) {
            state.editable = data;
        },
        setTitleSize(state, data) {
            state.titleSize = data;
        },
        setTitleList(state, data) {
            state.titleList = data;
        },
        setPage(state, data) {
            state.page = data;
        },
        setSearchResultVisible(state) {
            state.searchResultVisible = true;
        },
        clearSearchForm(state) {
            state.searchForm = {
                titleCode: null,
                companyId: null,
                companyMst: {
                    companyId: '',
                    companyCode: '',
                    activeStartTime: '',
                    activeEndTime: ''
                },
                groupFlg: null,
                activeStartTime: null,
                activeEndTime: null,
                titleName: null,
                titleType: null
            };
        },
        setTitleId(state, data) {
            state.form.titleId = data;
        },
        clearForm(state) {
            state.form = {
                titleId: '',
                titleCode: '',
                companyId: '',
                companyMst: {
                    companyId: '',
                    companyCode: '',
                    activeStartTime: '',
                    activeEndTime: ''
                },
                groupFlg: '',
                activeStartTime: '',
                activeEndTime: '',
                titleName: '',
                titleType: '',
                createdTime: '',
                createdUser: '',
                updatedTime: '',
                updatedUser: '',
                deletedFlg: '',
                versionNo: ''
            };
        },
        setActiveStartTime(state) {
            state.form.activeStartTime = new Date();
        },
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        clearReferencedTitleList(state) {
            state.referencedTitleList = [];
        },
        setReferencedTitleList(state, data) {
            state.referencedTitleList.push(data);
        },
        setSortKey(state, data) {
            state.sortKey = data;
        },
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setCompanyOfRegist(state, data) {
            state.form.companyId = data.companyId;
            state.form.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setCompanyToInitialData(state, data) {
            state.initialData.companyId = data.companyId;
            state.initialData.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setCompanyOfSearch(state, data) {
            state.searchForm.companyId = data.companyId;
            state.searchForm.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        }
    },
    actions: {
        async showTitle(context, titleId) {
            const response = await axios({
                method: 'get',
                url: '/idmf_titles/' + titleId
            });
            console.log(response.data);
            context.commit('setForm', response.data);
            context.commit('setInitialData', response.data);
            context.dispatch('getCompanyInfo', response.data.companyId).then((response_1) => {
                context.commit('setCompanyOfRegist', response_1.data);
                context.commit('setCompanyToInitialData', response_1.data);
            });
            context.dispatch('searchTitleNameList', titleId);
        },
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        getCompanyInfo(context, companyId) {
            return axios({
                method: 'get',
                url: '/idmf_companies/' + companyId
            });
        },
        async searchTitleNameList(context, titleId) {
            const response = await axios({
                method: 'get',
                url: '/idmf_title_names/find_by_title/' + titleId
            });
            console.log(response.data);
            context.commit('setTitleNameList', response.data);
        },
        async registTitle(context) {
            const response = await axios({
                method: 'post',
                url: '/idmf_titles/',
                data: {
                    ...context.state.form,
                    createdUser: context.rootState.common.user
                }
            });
            console.log(response);
            context.commit('setTitleId', response.data.titleId);
        },
        async updateTitle(context) {
            const response = await axios({
                method: 'put',
                url: '/idmf_titles/',
                data: {
                    ...context.state.form,
                    updatedUser: context.rootState.common.user,
                }
            });
            console.log(response.data);
            context.commit('setForm', response.data);
            context.commit('setInitialData', response.data);
        },
        resetForm(context) {
            context.commit('setForm', context.state.initialData);
        },
        searchTitleList(context) {
            return context.dispatch('searchTitle').then((response) => {
                context.commit('setTitleSize', response.data.paging.total);
                context.commit('setTitleList', response.data.data);
            });
        },
        searchTitle(context) {
            return axios({
                method: 'post',
                url: '/idmf_titles/search',
                data: context.state.searchForm,
                params: {
                    pageNo: context.state.page,
                    pageSize: 10,
                    sort: context.state.sortKey
                }
            });
        },
        setPage(context, page) {
            context.commit('setPage', page);
        },
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
        deleteTitle(context) {

            return new Promise((resolve) => {
                context.commit("setErrorMessage", '');

                let deleteTitleList = context.state.selectedList.map(title => {
                    return {
                        ...title,
                        updatedUser: context.rootState.common.user
                    };
                });
                let searchTitleNameTask = context.state.selectedList.map(title =>
                    axios({
                        method: 'get',
                        url: '/idmf_title_names/find_by_title/' + title.titleId
                    })
                );
                Promise.all(searchTitleNameTask).then(async response => {
                    let deleteTitleNameList = [];
                    response.forEach(element => {
                        if(element && element.data && element.data.length !== 0) {
                            let titleName = {
                                titleId: element.data[0].titleId,
                                updatedUser: context.rootState.common.user
                            };
                            deleteTitleNameList.push(titleName);
                        }
                    });
                    try {
                        return axios({
                            method: 'post',
                            url: '/idmf_title_names/bulk_delete',
                            data: deleteTitleNameList
                        });
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
                        resolve();
                    }
                }).then(async () => {
                    try {
                        const response_1 = await axios({
                            method: 'post',
                            url: '/idmf_titles/bulk_delete',
                            data: deleteTitleList
                        });
                        console.log(response_1.data);
                        context.dispatch('searchTitleList');
                        resolve();
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
                        resolve();
                    }
                });
            });
        },
        searchPositionTitle(context, titleId) {
            return axios({
                method: 'post',
                url: '/idmf_positions/titles/search',
                data: {
                    titleId: titleId
                }
            });
        },
        async checkPositionTitle(context) {
            var searchTaskList = [];
            context.commit('clearReferencedTitleList');
            for(var i = 0; i < context.state.selectedList.length; i++) {
                searchTaskList.push(context.dispatch('searchPositionTitle', context.state.selectedList[i].titleId));
            }
            const response = await Promise.all(searchTaskList);
            if (response.length !== 0) {
                for (var i_1 = 0; i_1 < response.length; i_1++) {
                    if (response[i_1].data.paging.total !== 0) {
                        context.commit('setReferencedTitleList', response[i_1].data.data[0].titleId);
                    }
                }
            }
        },
        addCompanyOfRegist(context, data) {
            context.commit('setCompanyOfRegist', data);
        },
        addCompanyOfSearch(context, data) {
            context.commit('setCompanyOfSearch', data);
        },
        setSortKey(context, data) {
            context.commit('setSortKey', data);
        }
    }
}
