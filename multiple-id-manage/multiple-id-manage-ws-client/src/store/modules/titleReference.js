import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state() {
        return {
            titleDialogVisible: false,
            searchForm: {
                titleCode: null,
			    companyMst: {
				    companyId: null,
				    companyCode: null,
				    companyName: null,
				    activeStartTime: null,
				    activeEndTime: null
			    },
			    groupFlg: null,
			    activeStartTime: null,
			    activeEndTime: null,
			    titleName: null,
			    titleType: null
		    },
		    titleSize: '',
		    titleList: [],
			page: '',
			screen: ''
        }
	},
	mutations: {
		setTitleDialogVisible(state, data) {
			state.titleDialogVisible = data;
		},
		setTitleList(state, data) {
			state.titleList = data;
		},
		setTitleSize(state, data) {
			state.titleSize = data;
		},
		setPage(state, data) {
			state.page = data;
		},
		clearSearchForm(state) {
			state.searchForm.titleCode = null;
			state.searchForm.groupFlg = null;
			state.searchForm.activeStartTime = null;
			state.searchForm.activeEndTime = null;
			state.searchForm.titleName = null;
			state.searchForm.titleType = null;
		},
		setCompany(state, data) {
            state.searchForm.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
	            activeEndTime: formatDate(data.activeEndTime)
            }
		},
		addCompany(state, data) {
			state.titleList[0].companyMst = data;
		},
		setScreen(state, data) {
			state.screen = data;
		}
	},
	actions: {
		openTitleDialog(context, {companyId, screen}) {
			context.commit('setScreen', screen);
			context.commit('setPage', 1);
			return context.dispatch('searchCompany', companyId).then(response => {
				context.commit('setCompany', response.data);
				return context.dispatch('searchTitleList');
            }).then(() => {
				context.commit('setTitleDialogVisible', true);
			});
		},
		closeTitleDialog(context) {
			context.commit('setTitleDialogVisible', false);
		},
		searchTitleList(context) {
			return axios({
				method: 'post',
				url: '/idmf_titles/search',
				data: {
					...context.state.searchForm,
					companyId: context.state.searchForm.companyMst.companyId
				},
				params: {
					pageNo: context.state.page,
					pageSize: 10
				}
			}).then(function(response) {
				context.commit('setTitleSize', response.data.paging.total);
				console.log(response.data.data);
				var titleList = response.data.data;
				var companyIdList = []
				for(var i = 0; i < titleList.length; i++) {
					if(companyIdList.indexOf(titleList[i].companyId) === -1) {
						companyIdList.push(titleList[i].companyId);
					}
				}
				var searchCompanyTask = [];
				for(var j = 0; j < companyIdList.length; j++) {
					searchCompanyTask.push(context.dispatch('searchCompany', companyIdList[j]));
				}
				return Promise.all(searchCompanyTask).then((response) => {
					for(var i = 0; i < titleList.length; i++) {
						for(var j = 0; j < response.length; j++) {
							if(titleList[i].companyId === response[j].data.companyId) {
								titleList[i].companyMst = response[j].data;
								break;
							}
						}
					}
					context.commit('setTitleList', titleList);
				});
			});
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		addTitle(context, data) {
			context.commit(context.state.screen + '/addTitle', data, {root: true});
			context.dispatch('closeTitleDialog');
		},
        searchCompany(context, companyId) {
			return axios({
				method: 'get',
				url: '/idmf_companies/' + companyId
			});
		},
		setPage(context, page) {
			context.commit('setPage', page);
		}
	}
}
