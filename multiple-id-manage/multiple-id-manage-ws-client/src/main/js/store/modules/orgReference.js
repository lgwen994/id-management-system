import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state: {
		orgDialogVisible: false,
		searchForm: {
			orgCode: null,
			groupFlg: null,
			activeStartTime: null,
			activeEndTime: null,
			orgName: null,
			orgType: null
		},
		orgSize: '',
		orgList: [],
		page: '',
		screen: ''
	},
	mutations: {
		setOrgDialogVisible(state, data) {
			state.orgDialogVisible = data;
		},
		setOrgList(state, data) {
			state.orgList = data;
		},
		setOrgSize(state, data) {
			state.orgSize = data;
		},
		setPage(state, data) {
			state.page = data;
		},
		clearSearchForm(state) {
			state.searchForm.orgCode = null;
			state.searchForm.groupFlg = null;
			state.searchForm.activeStartTime = null;
			state.searchForm.activeEndTime = null;
			state.searchForm.orgName = null;
			state.searchForm.orgType = null;
		},
		setScreen(state, data) {
			state.screen = data;
		}
	},
	actions: {
		openOrgDialog(context, {screen}) {
			context.commit('setScreen', screen);
			return context.dispatch('searchOrgList').then(() => {
				context.commit('setOrgDialogVisible', true);
			});
		},
		closeOrgDialog(context) {
			context.commit('setOrgDialogVisible', false);
		},
		searchOrgList(context) {
			return axios({
				method: 'post',
				url: '/idmf_orgs/search',
				data: {
					orgCode: context.state.searchForm.orgCode,
					groupFlg: context.state.searchForm.groupFlg,
					activeStartTime: context.state.searchForm.activeStartTime,
					activeEndTime: context.state.searchForm.activeEndTime,
					orgName: context.state.searchForm.orgName,
					orgType: context.state.searchForm.orgType
				},
				params: {
					pageNo: context.state.page,
					pageSize: 10
				}
			}).then(function(response) {
				console.log(response.data.data);
				context.commit('setOrgSize', response.data.paging.total);
				context.commit('setOrgList', response.data.data);
			});
		},
		setPage(context, page) {
			context.commit('setPage', page);
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		addOrg(context, data) {
			context.dispatch(context.state.screen + '/addOrg', data, {root: true});
			context.dispatch('closeOrgDialog');
		}
	}
}
