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
        // フォーム設定
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
        // 初期データ設定
        setInitialData(state, data) {
            state.initialData = {
                ...data,
                groupFlg: String(data.groupFlg),
                companyMst: {}
            };
        },
        // 役職名一覧情報設定
        setTitleNameList(state, data) {
            state.titleNameList = data;
        },
        // 画面部品制御
        setEditable(state, data) {
            state.editable = data;
        },
        // 役職サイズ設定
        setTitleSize(state, data) {
            state.titleSize = data;
        },
        // 役職一覧情報設定
        setTitleList(state, data) {
            state.titleList = data;
        },
        // ページ設定
        setPage(state, data) {
            state.page = data;
        },
        // 検索結果表示状態設定
        setSearchResultVisible(state, data) {
            state.searchResultVisible = true;
        },
        // 検索ドームクリア
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
        // 役職ID設定
        setTitleId(state, data) {
            state.form.titleId = data;
        },
        // フォームクリア
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
        // 適用開始日設定
        setActiveStartTime(state) {
            state.form.activeStartTime = new Date();
        },
        // 選択された役職名情報の設定
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        // 参照役職情報クリア
        clearReferencedTitleList(state) {
            state.referencedTitleList = [];
        },
        // 参照役職情報設定
        setReferencedTitleList(state, data) {
            state.referencedTitleList.push(data);
        },
        // ソートキー設定
        setSortKey(state, data) {
            state.sortKey = data;
        },
        // エラー情報設定
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        // 会社マスタ情報設定（役職登録の場合、または　役職情報表示の場合）
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
        // 会社マスタ情報設定（役職検索の場合）
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
        // 役職情報表示
        showTitle(context, titleId) {
            return axios({
                method: 'get',
                url: '/idmf_titles/' + titleId
            }).then(function(response) {
                console.log(response.data);
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);

                context.dispatch('getCompanyInfo', response.data.companyId).then((response) => {
                    context.commit('setCompanyOfRegist', response.data);
                    context.commit('setCompanyToInitialData', response.data);
                });

                context.dispatch('searchTitleNameList', titleId);
            });
        },
        // 画面制御状態を変換
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        // 会社情報を取得
        getCompanyInfo(context, companyId) {
            return axios({
                method: 'get',
                url: '/idmf_companies/' + companyId
            });
        },
        // 役職名一覧情報を取得
        searchTitleNameList(context, titleId) {
            return axios({
                method: 'get',
                url: '/idmf_title_names/find_by_title/' + titleId
            }).then(function(response) {
                console.log(response.data);
                context.commit('setTitleNameList', response.data);
            });
        },
        // 役職情報を登録
        registTitle(context) {
            return axios({
                method: 'post',
                url: '/idmf_titles/',
                data: {
                    ...context.state.form,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                console.log(response);
                context.commit('setTitleId', response.data.titleId);
            });
        },
        // 役職情報を更新
        updateTitle(context) {
            return axios({
                method: 'put',
                url: '/idmf_titles/',
                data: {
                    ...context.state.form,
                    updatedUser: context.rootState.common.user,
                }
            }).then(function(response) {
                console.log(response.data);
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);
            });
        },
        // フォームリセット
        resetForm(context) {
            context.commit('setForm', context.state.initialData);
        },
        // 役職一覧情報を取得
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
        // ページ設定
        setPage(context, page) {
            context.commit('setPage', page);
        },
        // 検索結果表示状態を設定
        setSearchResultVisible(context, data) {
            context.commit('setSearchResultVisible', data);
        },
        // 検索フォームをクリア
        clearSearchForm(context) {
            context.commit('clearSearchForm');
        },
        // フォームをクリア
        clearForm(context) {
            context.commit('clearForm');
        },
        // 適用開始日を設定
        setActiveStartTime(context) {
            context.commit('setActiveStartTime');
        },
        // 選択された役職名一覧情報を設定
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        // 役職情報を削除
        deleteTitle(context) {

            return new Promise((resolve, reject) => {
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
                Promise.all(searchTitleNameTask).then(response => {
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
                    return axios({
                        method: 'post',
                        url: '/idmf_title_names/bulk_delete',
                        data: deleteTitleNameList
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
                }).then(response => {
                    return axios({
                        method: 'post',
                        url: '/idmf_titles/bulk_delete',
                        data: deleteTitleList
                    }).then(function(response) {
                        console.log(response.data);
                        context.dispatch('searchTitleList');
                        resolve();
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
                });
            });
        },
        // 該当役職情報に関連するポジション情報を取得
        searchPositionTitle(context, titleId) {
            return axios({
                method: 'post',
                url: '/idmf_positions/titles/search',
                data: {
                    titleId: titleId
                }
            });
        },
        // 所属情報に対して、該当役職情報が参照されるかどうか判断
        checkPositionTitle(context) {
            var searchTaskList = [];
            context.commit('clearReferencedTitleList');
            for(var i = 0; i < context.state.selectedList.length; i++) {
                searchTaskList.push(context.dispatch('searchPositionTitle', context.state.selectedList[i].titleId));
            }
            return Promise.all(searchTaskList).then((response)=> {
                if(response.length !== 0){
                    for(var i = 0; i < response.length; i++) {
                        if(response[i].data.paging.total !== 0){
                            context.commit('setReferencedTitleList', response[i].data.data[0].titleId);
                        }
                    }
                }
            });
        },
        // 役職情報が表示されるとき、会社情報を設定
        addCompanyOfRegist(context, data) {
            context.commit('setCompanyOfRegist', data);
        },
        // 役職情報が検索されるとき、会社情報を設定
        addCompanyOfSearch(context, data) {
            context.commit('setCompanyOfSearch', data);
        },
        // ソートキーを設定
        setSortKey(context, data) {
            context.commit('setSortKey', data);
        }
    }
}
