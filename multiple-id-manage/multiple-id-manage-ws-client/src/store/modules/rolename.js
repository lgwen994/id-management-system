import axios from 'axios';
import {formatDate} from '../../common';
import store from '../';

export default {
	namespaced: true,
	state: {
		form: {
			roleNameId: '',
			roleMst: {
				roleId: '',
				roleCode: '',
				roleName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			roleName: '',
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
			roleNameId: '',
			roleMst: {
				roleId: '',
				roleCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			roleName: '',
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
		prevPage:'',
		editable: false,
		searchResultVisible: false
	},
	mutations: {
		clearForm(state) {
			state.form.locale = '';
			state.form.roleName = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
		},
		clearSearchForm(state) {
			state.searchForm.roleMst.roleId = null;
			state.searchForm.roleMst.roleCode = null;
			state.searchForm.roleMst.activeStartTime = null;
			state.searchForm.roleMst.activeEndTime = null;
			state.searchForm.locale = null;
			state.searchForm.roleName = null;
		},
		setForm(state, data) {
			state.form.roleNameId =  data.roleNameId;
			state.form.roleMst.roleId = data.roleId;
			state.form.locale =  data.locale;
			state.form.roleName =  data.roleName;
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
			state.initialData.roleNameId =  data.roleNameId;
			state.initialData.roleMst.roleId = data.roleId;
			state.initialData.locale =  data.locale;
			state.initialData.roleName =  data.roleName;
			state.initialData.activeStartTime =  data.activeStartTime;
			state.initialData.activeEndTime =  data.activeEndTime;
			state.initialData.createdTime =  data.createdTime;
			state.initialData.createdUser =  data.createdUser;
			state.initialData.updatedTime =  data.updatedTime;
			state.initialData.updatedUser =  data.updatedUser;
			state.initialData.deletedFlg =  data.deletedFlg;
			state.initialData.versionNo =  data.versionNo;
		},
		setRole(state, data) {
			state.form.roleMst.roleId = data.roleId;
			state.form.roleMst.roleCode = data.roleCode;
			state.form.roleMst.roleName = data.roleName;
			state.form.roleMst.activeStartTime = formatDate(data.activeStartTime);
			state.form.roleMst.activeEndTime = formatDate(data.activeEndTime);
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		setRoleNameId(state, data) {
			state.form.roleNameId = data;
		},
		setPrevPage(state, data) {
			state.prevPage = data;
		},
		setEditable(state, data) {
			state.editable = data;
		},
		setSearchResultVisible(state) {
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
		showRolename(context, roleNameId) {
			return axios({
				method: 'get',
				url: '/idmf_role_names/' + roleNameId
			}).then(function(response) {
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				console.log(response);
				return axios({
					method: 'get',
					url: '/idmf_roles/' + response.data.roleId
				}).then(function(response) {
					context.commit('setRole', response.data);
					console.log(response);
				});
			});
		},
		updateRolename(context) {
			return axios({
				method: 'put',
				url: '/idmf_role_names/',
				data: {
					roleNameId: context.state.form.roleNameId,
					roleId: context.state.form.roleMst.roleId,
					locale: context.state.form.locale,
					roleName: context.state.form.roleName,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime: context.state.form.activeEndTime,
					createdUser: context.state.form.createdUser,
					updatedUser: context.rootState.common.user,
					deletedFlg: context.state.form.deletedFlg,
					versionNo : context.state.form.versionNo
				}
			}).then(function(response) {
				console.log(response);
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
			});
		},
		deleteRolename(context) {
			context.state.selectedList = context.state.selectedList.map(element => {
				return {
					...element,
					updatedUser: context.rootState.common.user
				};
			})
			return axios({
				method: 'post',
				url: '/idmf_role_names/bulk_delete',
				data: context.state.selectedList
			}).then(function(response) {
				console.log(response);
				context.dispatch('role/showRole', context.state.form.roleMst.roleId, {root: true});
			});
		},
		registRolename(context) {
			return axios({
				method: 'post',
				url: '/idmf_role_names/',
				data: {
					roleId: context.state.form.roleMst.roleId,
					locale: context.state.form.locale,
					roleName: context.state.form.roleName,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime: context.state.form.activeEndTime,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit('setRoleNameId', response.data.roleNameId);
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
		setRole(context) {
			var data = {
				roleId: store.state.route.params.roleId,
				roleCode: store.state.route.params.roleCode,
				roleName: store.state.route.params.roleName,
				activeStartTime: store.state.route.params.activeStartTime,
				activeEndTime: store.state.route.params.activeEndTime
			}
			context.commit('setRole', data);
		},
		setActiveTime(context) {
			context.commit('setActiveTime');
		}
	}
}
