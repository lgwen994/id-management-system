import axios from 'axios';

export default {
	namespaced: true,
	state: {
		searchForm: {
		    roleCode: null,
			activeStartTime: null,
			activeEndTime: null,
            roleName: null,
            roleType: null,
            roleComment: null
		},
		form: {
			roleId: '',
		    roleCode: '',
			activeStartTime: '',
			activeEndTime: '',
            roleName: '',
            roleType: '',
            roleComment: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		initialData: {
			roleId: '',
		    roleCode: '',
			activeStartTime: '',
			activeEndTime: '',
            roleName: '',
            roleType: '',
            roleComment: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		rolenameList: null,
		editable: false,
		roleSize: '',
		roleList: null,
		page: '',
		searchResultVisible: false,
		selectedList: [],
		sortKey: ''
	},
    mutations: {
		setForm(state, data) {
			state.form.roleId =  data.roleId;
			state.form.roleCode =  data.roleCode;
			state.form.activeStartTime =  data.activeStartTime;
			state.form.activeEndTime =  data.activeEndTime;
			state.form.roleName =  data.roleName;
			state.form.roleType = data.roleType;
			state.form.roleComment = data.roleComment;
			state.form.createdTime =  data.createdTime;
			state.form.createdUser =  data.createdUser;
			state.form.updatedTime =  data.updatedTime;
			state.form.updatedUser =  data.updatedUser;
			state.form.deletedFlg =  data.deletedFlg;
			state.form.versionNo =  data.versionNo;
		},
		setInitialData(state, data) {
			state.initialData.roleId =  data.roleId;
			state.initialData.roleCode =  data.roleCode;
			state.initialData.locale =  data.locale;
			state.initialData.activeStartTime =  data.activeStartTime;
			state.initialData.activeEndTime =  data.activeEndTime;
			state.initialData.roleName =  data.roleName;
			state.initialData.roleType = data.roleType;
			state.initialData.roleComment = data.roleComment;
			state.initialData.createdTime =  data.createdTime;
			state.initialData.createdUser =  data.createdUser;
			state.initialData.updatedTime =  data.updatedTime;
			state.initialData.updatedUser =  data.updatedUser;
			state.initialData.deletedFlg =  data.deletedFlg;
			state.initialData.versionNo =  data.versionNo;
		},
		setRolenameList(state, data) {
			state.rolenameList = data;
		},
		setEditable(state, data) {
			state.editable = data;
		},
		setRoleSize(state, data) {
			state.roleSize = data;
		},
		setRoleList(state, data) {
			state.roleList = data;
		},
		setPage(state, data) {
			state.page = data;
		},
		setSearchResultVisible(state, data) {
			state.searchResultVisible = true;
		},
		clearSearchForm(state) {
			state.searchForm.roleCode = null;
			state.searchForm.activeStartTime = null;
			state.searchForm.activeEndTime = null;
			state.searchForm.roleName = null;
			state.searchForm.roleType = null;
			state.searchForm.roleComment = null;
		},
		setRoleId(state, data) {
			state.form.roleId = data;
		},
		clearForm(state) {
			state.form.roleId = '';
			state.form.roleCode = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
			state.form.roleName = '';
			state.form.roleType = '';
			state.form.roleComment = '';
		},
		setActiveStartTime(state) {
			state.form.activeStartTime = new Date();
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		clearReferencedRoleList(state) {
			state.referencedRoleList = [];
		},
		setReferencedRoleList(state, data) {
			state.referencedRoleList.push(data);
		},
		setSortKey(state, data) {
			state.sortKey = data;
		}
    },
    actions: {
		showRole(context, roleId) {
			return axios({
				method: 'get',
				url: '/idmf_roles/' + roleId
			}).then(function(response) {
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				console.log(response.data);
				context.dispatch('searchRolenameList', roleId).then((response) => {
					context.commit('setRolenameList', response.data);
				});
			});
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		searchRolenameList(context, roleId) {
			return axios({
				method: 'get',
				url: '/idmf_role_names/find_by_role/' + roleId
			});
		},
		updateRole(context) {
			return axios({
				method: 'put',
				url: '/idmf_roles/',
				data: {
					roleId: context.state.form.roleId,
					roleCode: context.state.form.roleCode,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime:context.state.form.activeEndTime,
					roleName: context.state.form.roleName,
					roleType: context.state.form.roleType,
					roleComment: context.state.form.roleComment,
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
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		searchRoleList(context) {
			return context.dispatch('searchRole').then((response) => {
				context.commit('setRoleSize', response.data.paging.total);
				context.commit('setRoleList', response.data.data);
			});
		},
		searchRole(context) {
			return axios({
				method: 'post',
				url: '/idmf_roles/search',
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
		registRole(context) {
			return axios({
				method: 'post',
				url: '/idmf_roles/',
				data: {
					...context.state.form,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit('setRoleId', response.data.roleId);
				console.log(response);
			});
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
		deleteRole(context) {
			let deleteRoleList = context.state.selectedList.map(role => {
				return {
					...role,
					updatedUser: context.rootState.common.user
				};
			});
			let searchRolenameTask = context.state.selectedList.map(role =>
				context.dispatch('searchRolenameList', role.roleId)
			);
			Promise.all(searchRolenameTask).then(response => {
				let deleteRolenameList = [];
				response.forEach(element => {
					if(element && element.data && element.data.length != 0) {
						let rolename = {
							roleId: element.data[0].roleId,
							updatedUser: context.rootState.common.user
						};
						deleteRolenameList.push(rolename);
					}
				});
				return axios({
					method: 'post',
					url: '/idmf_role_names/bulk_delete',
					data: deleteRolenameList
				});
			}).then(response => {
				return axios({
					method: 'post',
					url: '/idmf_roles/bulk_delete',
					data: deleteRoleList
				});
			}).then(response => {
				context.dispatch('searchRoleList');
			});
		},
		searchPositionRole(context, roleId) {
			return axios({
				method: 'post',
				url: '/idmf_position_roles/search',
					data: {
						roleId: roleId
					}
			});
		},
		checkPositionRole(context) {
			var searchTaskList = [];
			context.commit('clearReferencedRoleList');
			for(var i = 0; i < context.state.selectedList.length; i++) {
				searchTaskList.push(context.dispatch('searchPositionRole', context.state.selectedList[i].roleId));
			}
			return Promise.all(searchTaskList).then((response)=> {
				for(var i = 0; i < response.length; i++) {
					if(response[i].data.data.length !== 0) {
						context.commit('setReferencedRoleList', response[i].data.data[0].roleId);
					}
				}
			});
		},
		setSortKey(context, data) {
			context.commit('setSortKey', data);
		}
    }
}
