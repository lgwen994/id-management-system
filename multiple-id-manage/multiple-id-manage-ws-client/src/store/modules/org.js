import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state: {
		searchForm: {
			orgCode: null,
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
			orgName: null,
			orgType: null
		},
		form: {
			orgId: '',
			orgCode: '',
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
			orgName: '',
			orgType: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		initialData: {
			orgId: '',
			orgCode: '',
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
			orgName: '',
			orgType: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		orgNameList: null,
		orgHierarchyList: null,
		initialOrgHierarchyList: null,
		positionList: null,
		orgHierarchyIndex : null,
		editable: false,
		hierarchyEditable:false,
		selectedOrgHierarchy: null,
		orgSize: '',
		positionSize: '',
		orgList: null,
		page: '',
		pagePosition: '',
		searchResultVisible: false,
		selectedList: [],
		sortKey: '',
		errorMessage:''
	},
	mutations: {
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
		setInitialData(state, data) {
			state.initialData = {
					...data,
					groupFlg: String(data.groupFlg),
					companyMst: {}
				};
		},
		setOrgNameList(state, data) {
			state.orgNameList = data;
		},
		setOrgHierarchyList(state, data) {
			state.initialOrgHierarchyList = data.map(orgHierarchy => Object.assign({}, orgHierarchy));
			let orgHierarchyList = [];
			data.forEach((value, index) => {
				value = {
						...value,
						highOrg: ''
					};
				orgHierarchyList.push(value);
				axios({
					method: 'get',
					url: '/idmf_orgs/' + value.highOrgId
				}).then(function(response) {
					console.log(response.data);
					if(response && response.data && response.data !== ''){
						orgHierarchyList[index].highOrg = response.data.orgCode + ':' + response.data.orgName + '(' + formatDate(response.data.activeStartTime) + '-' + formatDate(response.data.activeEndTime) + ')';
					}
				});
			});
			state.orgHierarchyList = orgHierarchyList;
		},
		setPositionList(state, data) {
			state.positionList = data;
		},
		editorUserTitleOfPosition(state, data) {
			let positionList = [];
			data.forEach((value, index) => {
				value = {
						...value,
						title: '',
						user: ''
					};	
				positionList.push(value);
				axios({
					method: 'get',
					url: '/idmf_titles/' + value.titleId
				}).then(function(response) {
					console.log(response.data);
					if(response && response.data && response.data !== ''){
						positionList[index].title = response.data.titleCode + ':' + response.data.titleName + '(' + formatDate(response.data.activeStartTime) + '-' + formatDate(response.data.activeEndTime) + ')';
					}
				});
				axios({
					method: 'get',
					url: '/idmf_users/' + value.userId
				}).then(function(response) {
					console.log(response.data);
					if(response && response.data && response.data !== ''){
						positionList[index].user = response.data.userCode + ':' + response.data.userName + '(' + formatDate(response.data.activeStartTime) + '-' + formatDate(response.data.activeEndTime) + ')'
					}
				});
			});
			state.positionList = positionList;
		},
		// control components of the page
		setEditable(state, data) {
			state.editable = data;
		},
		
		setOrgSize(state, data) {
			state.orgSize = data;
		},
		
		setPositionSize(state, data) {
			state.positionSize = data;
		},
		
		setOrgList(state, data) {
			state.orgList = data;
		},
		
		setPage(state, data) {
			state.page = data;
		},

		setPagePosition(state, data) {
			state.pagePosition = data;
		},
		
		setSearchResultVisible(state) {
			state.searchResultVisible = true;
		},
		
		clearSearchForm(state) {
			state.searchForm = {
					orgCode: null,
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
					orgName: null,
					orgType: null
				};
		},
		
		clearForm(state) {
			state.form = {
					orgId: '',
					orgCode: '',
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
					orgName: '',
					orgType: '',
					createdTime: '',
					createdUser: '',
					updatedTime: '',
					updatedUser: '',
					deletedFlg: '',
					versionNo: ''
			};
		},
		setOrgId(state, data) {
			state.form.orgId = data;
		},
		setActiveStartTime(state) {
			state.form.activeStartTime = new Date();
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		clearReferencedOrgList(state) {
			state.referencedOrgList = [];
		},
		setReferencedOrgList(state, data) {
			state.referencedOrgList.push(data);
		},
		setSortKey(state, data) {
			state.sortKey = data;
		},
		setCompanyOfRegist(state, data) {
			if(data && data !== ''){
				state.form.companyId = data.companyId;
				state.form.companyMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
			};
		},
		setCompanyToInitialData(state, data) {
			if(data && data !== ''){
				state.initialData.companyId = data.companyId;
				state.initialData.companyMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
			};
		},
		setCompanyOfSearch(state, data) {
			if(data && data !== ''){
				state.searchForm.companyId = data.companyId;
				state.searchForm.companyMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
			};
		},
		addOrgHierarchy(state) {
			var orgHierarchy = {
				hierarchyId: '',
				hierarchyCode: '',
				orgId: state.form.orgId,
				highOrg:'',
				highOrgId: '',
				activeStartTime: '',
				activeEndTime: '',
				hierarchyCodeEditable: true,
				index: state.orgHierarchyList.length
			};
			state.orgHierarchyList.push(orgHierarchy);
		},
		deleteOrgHierarchy(state) {
			state.orgHierarchyList.some(function(v, i){
				if (v.index === state.selectedOrgHierarchy.index) state.orgHierarchyList.splice(i,1);
			});
		},
		setSelectedOrgHierarchy(state, data) {
			state.selectedOrgHierarchy = data;
		},
		setHierarchyEditable(state, data) {
			state.hierarchyEditable = data;
		},
		resetOrgHierarchy(state) {
			state.orgHierarchyList = state.initialOrgHierarchyList.map(orgHierarchy => Object.assign({}, orgHierarchy));
		},
		setOrgHierarchyIndex(state, data) {
			state.orgHierarchyIndex = data;
		},
		setHighOrg(state, data) {
			state.orgHierarchyList[state.orgHierarchyIndex].highOrgId = data.orgId;
			state.orgHierarchyList[state.orgHierarchyIndex].highOrg = data.orgCode + ':' + data.orgName + '(' + formatDate(data.activeStartTime) + '-' + formatDate(data.activeEndTime) + ')';
		},
		setErrorMessage(state, data){
			state.errorMessage = data;
		}
	},
	actions: {
		async showOrg(context, orgId) {
			const response = await axios({
				method: 'get',
				url: '/idmf_orgs/' + orgId
			});
			console.log(response.data);
			context.commit('setForm', response.data);
			context.commit('setInitialData', response.data);
			context.commit('setEditable', false);
			context.commit('setHierarchyEditable', false);
			context.dispatch('getCompanyInfo', response.data.companyId).then((response_1) => {
				context.commit('setCompanyOfRegist', response_1.data);
				context.commit('setCompanyToInitialData', response_1.data);
			});
			context.dispatch('searchOrgNameList', orgId);
			context.dispatch('searchOrgHierarchyList', orgId).then((response_2) => {
				context.commit('setOrgHierarchyList', response_2.data);
			});
			context.dispatch('searchPositionInfo', { orgId: orgId, page: 1 });
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		getCompanyInfo(context, companyId) {
			return axios({
				method: 'get',
				url: '/idmf_companies/' + companyId
			});
		},
		async searchOrgNameList(context, orgId) {
			const response = await axios({
				method: 'get',
				url: '/idmf_org_names/find_by_org/' + orgId
			});
			console.log(response.data);
			context.commit('setOrgNameList', response.data);
		},
		searchOrgHierarchyList(context, orgId) {
			return axios({
				method: 'get',
				url: '/idmf_org_hierarchies/org_id/' + orgId
			});
		},
		searchPositionList(context, orgId) {
			return axios({
				method: 'post',
				url: '/idmf_positions/search',
				data: {
					orgId: orgId
				},
				params: {
					pageNo: context.state.pagePosition,
					pageSize: 10
				}
			});
		},
		async registOrg(context) {
			const response = await axios({
				method: 'post',
				url: '/idmf_orgs/',
				data: {
					...context.state.form,
					createdUser: context.rootState.common.user
				}
			});
			console.log(response.data);
			context.commit('setOrgId', response.data.orgId);
		},
		async updateOrg(context) {
			const response = await axios({
				method: 'put',
				url: '/idmf_orgs/',
				data: {
					...context.state.form,
					updatedUser: context.rootState.common.user
				}
			});
			console.log(response.data);
			context.commit('setForm', response.data);
			context.commit('setInitialData', response.data);
		},
		deleteOrg(context) {

			return new Promise((resolve) => {
				context.commit("setErrorMessage", '');

				let deleteOrgList = context.state.selectedList.map(org => {
					return {
						...org,
						updatedUser: context.rootState.common.user
					};
				});
				let searchOrgNameTask = context.state.selectedList.map(org =>
					axios({
						method: 'get',
						url: '/idmf_org_names/find_by_org/' + org.orgId
					})
				);
				Promise.all(searchOrgNameTask).then(response => {
					let deleteOrgNameList = [];
					response.forEach(element => {
						if(element && element.data && element.data.length !== 0) {
							let orgName = {
								orgId: element.data[0].orgId,
								updatedUser: context.rootState.common.user
							};
							deleteOrgNameList.push(orgName);
						}
					});
					return axios({
						method: 'post',
						url: '/idmf_org_names/bulk_delete',
						data: deleteOrgNameList
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
				}).then(() => {
					return axios({
						method: 'post',
						url: '/idmf_orgs/bulk_delete',
						data: deleteOrgList
					}).then(function(response) {
						console.log(response.data);
						context.dispatch('searchOrgList');
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
		async checkPositionOrg(context) {
			var searchTaskList = [];
			context.commit('clearReferencedOrgList');
			for(var i = 0; i < context.state.selectedList.length; i++) {
				searchTaskList.push(context.dispatch('searchPositionOrg', context.state.selectedList[i].orgId));
			}
			const response = await Promise.all(searchTaskList);
			if (response.length !== 0) {
				for (var i_1 = 0; i_1 < response.length; i_1++) {
					if (response[i_1].data.paging.total !== 0) {
						context.commit('setReferencedOrgList', response[i_1].data.data[0].orgId);
					}
				}
			}
		},
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		searchOrgList(context) {
			return context.dispatch('searchOrg').then((response) => {
				context.commit('setOrgSize', response.data.paging.total);
				context.commit('setOrgList', response.data.data);
			});
		},
		searchOrg(context) {
			return axios({
				method: 'post',
				url: '/idmf_orgs/search',
				data: context.state.searchForm,
				params: {
					pageNo: context.state.page,
					pageSize: 10,
					sort: context.state.sortKey
				}
			});
		},
		searchPositionInfo(context, data) {
			context.commit('setPagePosition', data.page);
			context.dispatch('searchPositionList', data.orgId).then((response) => {
				context.commit('setPositionList', response.data.data);
				context.commit('editorUserTitleOfPosition', response.data.data);
				context.commit('setPositionSize', response.data.paging.total);
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
		clearForm(context) {
			context.commit('clearForm');
		},
		setActiveStartTime(context) {
			context.commit('setActiveStartTime');
		},
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
		searchPositionOrg(context, orgId) {
			return axios({
				method: 'post',
				url: '/idmf_positions/orgs/search',
				data: {
					orgId: orgId
				}
			});
		},
		addCompanyOfRegist(context, data) {
			context.commit('setCompanyOfRegist', data);
		},
		addCompanyOfSearch(context, data) {
			context.commit('setCompanyOfSearch', data);
		},
		addOrgHierarchy(context) {
			context.commit('addOrgHierarchy');
		},
		deleteOrgHierarchy(context) {
			context.commit('deleteOrgHierarchy');
		},
		setSelectedOrgHierarchy(context, data) {
			context.commit('setSelectedOrgHierarchy', data);
		},
		orgHierarchyChangeMode(context, data) {
			context.commit('setHierarchyEditable', data);
		},
		resetOrgHierarchy(context) {
			context.commit('resetOrgHierarchy');
			context.commit('setOrgHierarchyList', context.state.orgHierarchyList);
		},
		setOrgHierarchyIndex(context, data) {
			context.commit('setOrgHierarchyIndex', data);
		},
		async updateOrgHierarchyList(context) {
			let taskList = context.state.orgHierarchyList.map(orgHierarchy => {
				if(orgHierarchy.hierarchyId === '') {
					return context.dispatch('registOrgHierarchy', orgHierarchy);
				} else{
					return context.dispatch('updateOrgHierarchy', orgHierarchy);
				}
			});

			let deleteOrgHierarchyList = [];
			context.state.initialOrgHierarchyList.forEach(initialOrgHierarchy => {
				let index = context.state.orgHierarchyList.findIndex(orgHierarchy => {
					return orgHierarchy.hierarchyId === initialOrgHierarchy.hierarchyId;
				});
				if(index === -1) {
					deleteOrgHierarchyList.push({
						...initialOrgHierarchy,
						updatedUser: context.rootState.common.user
					});
				}
			});
			if(deleteOrgHierarchyList.length !== 0) {
				taskList.push(context.dispatch('deleteOrgHierarchyList', deleteOrgHierarchyList));
			}

			await Promise.all(taskList);
			context.dispatch('searchOrgHierarchyList', context.state.form.orgId).then((response) => {
				context.commit('setOrgHierarchyList', response.data);
			});
		},
		updateOrgHierarchy(context, orgHierarchy) {
			return axios({
				method: 'put',
				url: '/idmf_org_hierarchies/',
				data: {
					...orgHierarchy,
					updatedUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response.data);
			});
		},
		async registOrgHierarchy(context, orgHierarchy) {
			const response = await axios({
				method: 'post',
				url: '/idmf_org_hierarchies/',
				data: {
					...orgHierarchy,
					createdUser: context.rootState.common.user
				}
			});
			console.log(response.data);
		},
		async deleteOrgHierarchyList(context, deleteOrgHierarchyList) {
			const response = await axios({
				method: 'post',
				url: '/idmf_org_hierarchies/bulk_delete',
				data: deleteOrgHierarchyList
			});
			console.log(response.data);
		},
		addOrg(context, data) {
			context.commit('setHighOrg', data);
		},
		setSortKey(context, data) {
			context.commit('setSortKey', data);
		}
	}
}
