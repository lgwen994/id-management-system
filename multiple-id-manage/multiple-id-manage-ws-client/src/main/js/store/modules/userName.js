import axios from 'axios';
import {formatDate} from '../../common';
import store from '../store';

export default {
	namespaced: true,
	state: {
		searchForm: {
            userNameId: '',
            userId: '',
			userMst: {
				userId: null,
				userCode: null,
				activeStartTime: null,
				activeEndTime: null
			},
			locale: null,
			userName: null
		},
		form: {
			userNameId: '',
            userId: '',
			userMst: {
				userId: '',
				userCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			userName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
		initialData: {
			userNameId: '',
            userId: '',
			userMst: {
				userId: '',
				userCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			userName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
		userNameList: null,
		userNameSize:'',
		selectedList: [],
		prevPage:'',
		editable: false,
		searchResultVisible: false
	},
	mutations: {
		setUserNameList(state, data) {
			state.userNameList = data;
		},
		setUserNameSize(state, data) {
			state.userNameSize = data;
		},
		clearForm(state) {
			state.form.locale = '';
			state.form.userName = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
		},
		clearSearchForm(state) {
			state.searchForm.userMst.userId = null;
			state.searchForm.userMst.userCode = null;
			state.searchForm.userMst.activeStartTime = null;
			state.searchForm.userMst.activeEndTime = null;
			state.searchForm.locale = null;
			state.searchForm.userName = null;
		},
		setForm(state, data) {
			state.form.userNameId =  data.userNameId;
            state.form.userId = data.userId;
			state.form.userMst.userId = data.userId;
			state.form.locale =  data.locale;
			state.form.userName =  data.userName;
			state.form.activeStartTime =  data.activeStartTime;
			state.form.activeEndTime =  data.activeEndTime;
			state.form.createdTime =  data.createdTime;
			state.form.createdUser =  data.createdUser;
			state.form.updatedTime =  data.updatedTime;
			state.form.updatedUser =  data.updatedUser;
			state.form.deletedFlg =  data.deletedFlg;
			state.form.versionNo =  data.versionNo;
		},
		setInitialData(state, data) {
			state.initialData.userNameId =  data.userNameId;
            state.initialData.userId = data.userId;
			state.initialData.userMst.userId = data.userId;
			state.initialData.locale =  data.locale;
			state.initialData.userName =  data.userName;
			state.initialData.activeStartTime =  data.activeStartTime;
			state.initialData.activeEndTime =  data.activeEndTime;
			state.initialData.createdTime =  data.createdTime;
			state.initialData.createdUser =  data.createdUser;
			state.initialData.updatedTime =  data.updatedTime;
			state.initialData.updatedUser =  data.updatedUser;
			state.initialData.deletedFlg =  data.deletedFlg;
			state.initialData.versionNo =  data.versionNo;
		},
		setUser(state, data) {
			state.form.userMst.userId = data.userId;
			state.form.userMst.userCode = data.userCode;
			state.form.userMst.activeStartTime = formatDate(data.activeStartTime);
			state.form.userMst.activeEndTime = formatDate(data.activeEndTime);
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		setUserNameId(state, data) {
			state.form.userNameId = data;
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
		setActiveTime(state) {
			state.form.activeStartTime = store.state.route.params.activeStartTime;
			state.form.activeEndTime = store.state.route.params.activeEndTime;
		}
	},
	actions: {
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		clearForm(context) {
			context.commit('clearForm');
		},
        showUserName(context, userNameId) {
            return axios({
                method: 'get',
                url: '/idmf_user_names/' + userNameId
            }).then(function(response) {
                context.commit('setForm', response.data);
                context.commit('setInitialData', response.data);
                console.log(response);
                return axios({
                    method: 'get',
                    url: '/idmf_users/' + response.data.userId
                }).then(function(response) {
                    context.commit('setUser', response.data);
                    console.log(response);
                });
            });
        },
		updateUserName(context) {
			return axios({
				method: 'put',
				url: '/idmf_user_names/',
				data: {
					userNameId: context.state.form.userNameId,
					userId: context.state.form.userMst.userId,
					locale: context.state.form.locale,
					userName: context.state.form.userName,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime:context.state.form.activeEndTime,
                    createdUser: context.state.form.createdUser,
                    updatedUser: context.rootState.common.user,
					versionNo : context.state.form.versionNo
				}
			}).then(function(response) {
				console.log(response);
				context.commit("setForm", response.data);
				context.commit('setInitialData', response.data);
			});
		},
        deleteUserName(context) {
			context.state.selectedList = context.state.selectedList.map((element) => {
                return {
                    ...element,
                    updatedUser: context.rootState.common.user
                }
            })
            return axios({
                method: 'post',
                url: '/idmf_user_names/bulk_delete',
                data: context.state.selectedList
            }).then(function(response) {
                console.log(response);
                context.dispatch('user/showUser', context.state.form.userMst.userId, {root: true});
            });
        },
		registUserName(context) {
			return axios({
				method: 'post',
				url: '/idmf_user_names/',
				data: {
					userId: context.state.form.userMst.userId,
					locale: context.state.form.locale,
					userName: context.state.form.userName,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime:context.state.form.activeEndTime,
                    createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit("setUserNameId", response.data.userNameId);
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
		setUser(context) {
			var data = {
				userId: store.state.route.params.userId,
				userCode: store.state.route.params.userCode,
				activeStartTime: store.state.route.params.activeStartTime,
				activeEndTime: store.state.route.params.activeEndTime
			}
			context.commit('setUser', data);
		},
		setActiveTime(context) {
			context.commit('setActiveTime');
		}
	}
}
