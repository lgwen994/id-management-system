import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state: {
		positionDialogVisible: false,
		positionList:[],
		positionSize: '',
		page: '',
		searchForm: {
			positionCode: null,
			userMst: {
				userId: null,
				userCode: null,
				activeStartTime: null,
				activeEndTime: null
			},
			orgMst: {
				orgId: null,
				orgCode: null,
				activeStartTime: null,
				activeEndTime: null
			},
			titleMst:  {
				titleId: null,
				titleCode: null,
				activeStartTime: null,
				activeEndTime: null
			},
			concurrentFlg: null,
			activeStartTime: null,
			activeEndTime: null
		}
	},
	mutations: {
		setPositionDialogVisible(state, data) {
			state.positionDialogVisible = data;
		},
		setPositionList(state, data) {
			state.positionList = data;
		},
		setPositionSize(state, data) {
			state.positionSize = data;
		},
		setPage(state, data) {
			state.page = data;
		},
		clearSearchForm(state) {
			state.searchForm.positionCode = null;
			state.searchForm.userMst.userId = null;
			state.searchForm.orgMst.orgId = null;
			state.searchForm.titleMst.titleId = null;
			state.searchForm.concurrentFlg = null;
			state.searchForm.activeStartTime = null;
			state.searchForm.activeEndTime = null;
		},
        addTitle(state, data) {
			state.searchForm.titleMst = {
                ...data,
	            activeStartTime: formatDate(data.activeStartTime),
	            activeEndTime: formatDate(data.activeEndTime)
            };
		},
        addOrg(state, data) {
            state.searchForm.orgMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
	            activeEndTime: formatDate(data.activeEndTime)
            }
        }
	},
	actions: {
		searchPosition(context, positionId) {
			return axios({
				method: 'get',
				url: '/idmf_positions/' + positionId
			});
		},
		openPositionDialog(context) {
			context.dispatch('setPage', 1);
			return context.dispatch('searchPositionList').then(() => {
				context.commit('setPositionDialogVisible', true);
			});
		},
		closePositionDialog(context) {
			context.commit('setPositionDialogVisible', false);
		},
		searchPositionList(context) {
			return axios({
				method: 'post',
				url: '/idmf_positions/search',
				data: {
					...context.state.searchForm,
					userId: context.state.searchForm.userMst.userId,
					orgId: context.state.searchForm.orgMst.orgId,
					titleId: context.state.searchForm.titleMst.titleId
				},
				params: {
					pageNo: context.state.page,
					pageSize: 10
				}
			}).then(function(response) {
				console.log(response.data.data);
				context.commit('setPositionSize', response.data.paging.total);
				var positionList = response.data.data;
				var userIdList = []
				var orgIdList = [];
				var titleIdList = [];
				for(var i = 0; i < positionList.length; i++) {
					if(userIdList.indexOf(positionList[i].userId) === -1) {
						userIdList.push(positionList[i].userId);
					}
					if(orgIdList.indexOf(positionList[i].orgId) === -1) {
						orgIdList.push(positionList[i].orgId);
					}
					if(titleIdList.indexOf(positionList[i].titleId) === -1) {
						titleIdList.push(positionList[i].titleId);
					}
				}

				var searchUserTask = [];
				for(var k = 0; k < userIdList.length; k++) {
					searchUserTask.push(context.dispatch('searchUser', userIdList[k]));
				}
				var searchOrgTask = [];
				for(var j = 0; j < orgIdList.length; j++) {
					searchOrgTask.push(context.dispatch('searchOrg', orgIdList[j]));
				}
				var searchTitleTask = [];
				for(var l = 0; l < titleIdList.length; l++) {
					searchTitleTask.push(context.dispatch('searchTitle', titleIdList[l]));
				}
				Promise.all(searchUserTask).then((userResponse) => {
					Promise.all(searchOrgTask).then((orgResponse) => {
						Promise.all(searchTitleTask).then((titleResponse) => {
							for(var i = 0; i < positionList.length; i++) {
								for(var j = 0; j < userResponse.length; j++) {
									if(positionList[i].userId === userResponse[j].data.userId) {
										positionList[i].userMst = userResponse[j].data;
										break;
									}
								}
								for(var k = 0; k < orgResponse.length; k++) {
									if(positionList[i].orgId === orgResponse[k].data.orgId) {
										positionList[i].orgMst = orgResponse[k].data;
										break;
									}
								}
								for(var l = 0; l < titleResponse.length; l++) {
									if(positionList[i].titleId === titleResponse[l].data.titleId) {
										positionList[i].titleMst = titleResponse[l].data;
										break;
									}
								}
							}
							context.commit('setPositionList', positionList);
						});
					});
				});
			});
		},
		setPage(context, page) {
			context.commit('setPage', page);
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
        searchUser(context, userId) {
			return axios({
				method: 'get',
				url: '/idmf_users/' + userId
			});
		},
		searchOrg(context, orgId) {
			return axios({
				method: 'get',
				url: '/idmf_orgs/' + orgId
			});
		},
		searchTitle(context, titleId) {
			return axios({
				method: 'get',
				url: '/idmf_titles/' + titleId
			});
		},
        addTitle(context, data) {
			context.commit('addTitle', data);
		},
        addOrg(context, data) {
            context.commit('addOrg', data);
        }
	}
}
