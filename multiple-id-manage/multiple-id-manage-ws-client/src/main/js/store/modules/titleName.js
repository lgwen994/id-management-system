import axios from 'axios';
import {formatDate} from '../../common';
import store from '../store';

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
        // フォームクリア
        clearForm(state) {
            state.form.titleNameId = '',
                state.form.locale = '';
            state.form.titleName = '';
            state.form.activeStartTime = '';
            state.form.activeEndTime = '';
        },
        // フォーム設定
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
        // 初期データ設定
        setInitialData(state, data) {
            state.initialData = {
                ...data,
                titleMst: {}
            };
        },
        // 役職情報設定
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
        // 選択された役職名一覧設定
        setSelectedList(state, data) {
            state.selectedList = data;
        },
        // 役職名ID設定
        setTitleNameId(state, data) {
            state.form.titleNameId = data;
        },
        // 制御状態設定
        setEditable(state, data) {
            state.editable = data;
        },
        // 適用日設定
        setActiveTime(state) {
            state.form.activeStartTime = store.state.route.params.activeStartTime;
            state.form.activeEndTime = store.state.route.params.activeEndTime;
        }
    },
    actions: {
        // フォームをクリア
        clearForm(context) {
            context.commit('clearForm');
        },
        // 役職名情報を表示
        showTitleName(context, titleNameId) {
            return axios({
                method: 'get',
                url: '/idmf_title_names/' + titleNameId
            }).then(function(response) {
                console.log(response.data);
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);
                return axios({
                    method: 'get',
                    url: '/idmf_titles/' + response.data.titleId
                }).then(function(response) {
                    console.log(response.data);
                    context.commit('setTitle', response.data);
                });
            });
        },
        // 役職名情報を更新
        updateTitleName(context) {
            return axios({
                method: 'put',
                url: '/idmf_title_names/',
                data: {
                    ...context.state.form,
                    updatedUser: context.rootState.common.user
                }
            }).then(function(response) {
                console.log(response.data);
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);
            });
        },
        // 役職名情報を削除
        deleteTitleName(context) {
            context.state.selectedList = context.state.selectedList.map((element) => {
                return {
                    ...element,
                    updatedUser: context.rootState.common.user
                }
            })
            return axios({
                method: 'post',
                url: '/idmf_title_names/bulk_delete',
                data: context.state.selectedList
            }).then(function(response) {
                console.log(response.data);
            });
        },
        // 役職名情報を登録
        registTitleName(context) {
            return axios({
                method: 'post',
                url: '/idmf_title_names/',
                data: {
                    ...context.state.form,
                    createdUser: context.rootState.common.user
                }
            }).then(function(response) {
                console.log(response.data);
                context.commit('setTitleNameId', response.data.titleNameId);
            });
        },
        // 選択された役職名情報を設定
        setSelectedList(context, data) {
            context.commit('setSelectedList', data);
        },
        // フォームリセット
        resetForm(context) {
            context.commit('setForm', context.state.initialData);
        },
        // 画面部品制御状態を変換
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
        // 役職情報を設定
        setTitle(context) {
            context.commit('setTitle', store.state.route.params);
        },
        // 適用日を設定
        setActiveTime(context) {
            context.commit('setActiveTime');
        }
    }
}
