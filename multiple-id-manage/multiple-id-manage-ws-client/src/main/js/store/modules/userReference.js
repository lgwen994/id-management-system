import axios from 'axios';
import {formatDate} from '../../common';

export default {
    namespaced: true,
    state() {
        return {
            userDialogVisible: false,
            searchForm: {
                userCode: null,
                activeStartTime: null,
                activeEndTime: null,
                userName: null,
                mail: null,
                tel: null,
                fax:null,
                locale:null
            },
            userSize: '',
            userList: [],
            page: '',
            screen: '',
            kbn: ''
        }
    },
    mutations: {
        setUserDialogVisible(state, data) {
            state.userDialogVisible = data;
        },
        setUserList(state, data) {
            state.userList = data;
        },
        setUserSize(state, data) {
            state.userSize = data;
        },
        setPage(state, data) {
            state.page = data;
        },
        clearSearchForm(state) {
            state.searchForm.userCode = null;
            state.searchForm.activeStartTime = null;
            state.searchForm.activeEndTime = null;
            state.searchForm.userName = null;
            state.searchForm.mail = null;
            state.searchForm.tel = null;
            state.searchForm.fax = null;
            state.searchForm.locale = null;
        },
        setScreen(state, data) {
            state.screen = data;
        },
        setKbn(state, data) {
            state.kbn = data;
        }
    },
    actions: {
        openUserDialog(context, {screen, kbn}) {
            context.commit('setScreen', screen);
            context.commit('setKbn', kbn);
            context.commit('setPage', 1);
            return context.dispatch('searchUserList').then(response => {
            }).then(() => {
                context.commit('setUserDialogVisible', true);
            });
        },
        closeUserDialog(context) {
            context.commit('setUserDialogVisible', false);
        },
        clearSearchForm(context) {
            context.commit('clearSearchForm');
        },
        addUser(context, data) {
            context.dispatch(context.state.screen + '/addUserOf' + context.state.kbn, data, {root: true});
            context.dispatch('closeUserDialog');
        },
        searchUserList(context) {
            return axios({
                method: 'post',
                url: '/idmf_users/search',
                data: context.state.searchForm,
                params: {
                    pageNo: context.state.page,
                    pageSize: 10
                }
            }).then(function(response) {
                context.commit('setUserSize', response.data.paging.total);
                context.commit('setUserList', response.data.data);
            });
        },
        setPage(context, page) {
			context.commit('setPage', page);
		}
    }
}
