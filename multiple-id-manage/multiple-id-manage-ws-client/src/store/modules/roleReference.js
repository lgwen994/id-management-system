import axios from 'axios';

export default {
	namespaced: true,
	state: {
		referencedRoleList:[],
		searchForm: {
		    roleCode: null,
			activeStartTime: null,
			activeEndTime: null,
            roleName: null,
            roleType: null,
            roleComment: null
		},
	    roleList:[],
		roleSize: '',
		page: '',
		roleDialogVisible: false,
		selectedRole: null,
	},
    mutations: {
		setReferencedRoleList(state, data) {
			state.referencedRoleList = state.referencedRoleList.concat(data);
		},
		clearReferencedRoleList(state) {
			state.referencedRoleList = [];
		},
		setRoleList(state, data) {
			state.roleList = data;
		},
		setRoleSize(state, data) {
			state.roleSize = data;
		},
		setPage(state, data) {
			state.page = data;
		},
		setRoleDialogVisible(state, data) {
			state.roleDialogVisible = data;
		},
		clearSearchForm(state) {
			state.searchForm.roleCode = null;
			state.searchForm.activeStartTime = null;
			state.searchForm.activeEndTime = null;
			state.searchForm.roleName = null;
			state.searchForm.roleType = null;
			state.searchForm.roleComment = null;
		}
    },
    actions: {
		openRoleDialog(context) {
			context.dispatch('setPage', 1);
			return context.dispatch('searchRoleList').then(() =>{
				context.commit('setRoleDialogVisible', true);
			});
		},
		closeRoleDialog(context) {
			context.commit('setRoleDialogVisible', false);
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
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
					pageSize: 10
				}
			});
		},
		setPage(context, page) {
			context.commit('setPage', page);
		}
    }
}
