import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state: {
		// 検索フォーム
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
		// フォーム
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
		// 初期データ
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
		// フォーム設定
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
		//　初期データ設定
		setInitialData(state, data) {
			state.initialData = {
					...data,
					groupFlg: String(data.groupFlg),
					companyMst: {}
				};
		},
		// 組織名情報設定
		setOrgNameList(state, data) {
			state.orgNameList = data;
		},
		// 組織階層情報設定
		setOrgHierarchyList(state, data) {
			state.initialOrgHierarchyList = data.map(orgHierarchy => Object.assign({}, orgHierarchy));
			let orgHierarchyList = [];
			data.forEach((value, index, array) => {
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
		// 所属一覧情報のユーザマスタと役職マスタを編集
		editorUserTitleOfPosition(state, data) {
			let positionList = [];
			data.forEach((value, index, array) => {
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
		// 画面部品制御設定
		setEditable(state, data) {
			state.editable = data;
		},
		// 組織情報サイズ設定
		setOrgSize(state, data) {
			state.orgSize = data;
		},
		// 所属情報サイズ設定
		setPositionSize(state, data) {
			state.positionSize = data;
		},
		// 組織情報設定
		setOrgList(state, data) {
			state.orgList = data;
		},
		// ページ設定（組織検索）
		setPage(state, data) {
			state.page = data;
		},
		// 所属ページ設定
		setPagePosition(state, data) {
			state.pagePosition = data;
		},
		// 検索結果表示設定
		setSearchResultVisible(state, data) {
			state.searchResultVisible = true;
		},
		// 検索フォームクリア
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
		// フォームクリア
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
		// 組織ID設定
		setOrgId(state, data) {
			state.form.orgId = data;
		},
		// 適用開始日設定
		setActiveStartTime(state) {
			state.form.activeStartTime = new Date();
		},
		// 選択情報設定（組織名）
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		// 参照組織情報クリア
		clearReferencedOrgList(state) {
			state.referencedOrgList = [];
		},
		// 参照組織情報設定
		setReferencedOrgList(state, data) {
			state.referencedOrgList.push(data);
		},
		// ソートキー設定（組織検索結果）
		setSortKey(state, data) {
			state.sortKey = data;
		},
		// 会社マスタ情報設定（組織登録の場合、または　組織情報表示の場合）
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
		// 会社マスタ情報設定（組織検索の場合）
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
		// 組織階層追加
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
		// 選択された組織階層を削除
		deleteOrgHierarchy(state) {
			state.orgHierarchyList.some(function(v, i){
				if (v.index === state.selectedOrgHierarchy.index) state.orgHierarchyList.splice(i,1);
			});
		},
		// 選択組織階層を設定
		setSelectedOrgHierarchy(state, data) {
			state.selectedOrgHierarchy = data;
		},
		// 組織階層部品制御の設定
		setHierarchyEditable(state, data) {
			state.hierarchyEditable = data;
		},
		// 組織階層情報をリセット
		resetOrgHierarchy(state) {
			state.orgHierarchyList = state.initialOrgHierarchyList.map(orgHierarchy => Object.assign({}, orgHierarchy));
		},
		// OrgDialog画面を起動するとき、選択された組織階層の行数を記録
		setOrgHierarchyIndex(state, data) {
			state.orgHierarchyIndex = data;
		},
		// 上位組織情報を設定
		setHighOrg(state, data) {
			state.orgHierarchyList[state.orgHierarchyIndex].highOrgId = data.orgId;
			state.orgHierarchyList[state.orgHierarchyIndex].highOrg = data.orgCode + ':' + data.orgName + '(' + formatDate(data.activeStartTime) + '-' + formatDate(data.activeEndTime) + ')';
		},
		// エラー情報設定
		setErrorMessage(state, data){
			state.errorMessage = data;
		}
	},
	actions: {
		// 組織情報を表示
		showOrg(context, orgId) {
			return axios({
				method: 'get',
				url: '/idmf_orgs/' + orgId
			}).then(function(response) {
				console.log(response.data);
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				context.commit('setEditable', false);
				context.commit('setHierarchyEditable', false);

				context.dispatch('getCompanyInfo', response.data.companyId).then((response) => {
					context.commit('setCompanyOfRegist', response.data);
					context.commit('setCompanyToInitialData', response.data);
				});

				context.dispatch('searchOrgNameList', orgId);
				
				context.dispatch('searchOrgHierarchyList', orgId).then((response) => {
					context.commit('setOrgHierarchyList', response.data);
				});
				
				context.dispatch('searchPositionInfo', {orgId : orgId, page : 1});
			});
		},
		// 組織情報編集状態を変更
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		// 会社情報を取得
		getCompanyInfo(context, companyId) {
			return axios({
				method: 'get',
				url: '/idmf_companies/' + companyId
			});
		},
		// 組織名一覧情報を取得
		searchOrgNameList(context, orgId) {
			return axios({
				method: 'get',
				url: '/idmf_org_names/find_by_org/' + orgId
			}).then(function(response) {
				console.log(response.data);
				context.commit('setOrgNameList', response.data);
			});
		},
		// 組織階層一覧情報を取得
		searchOrgHierarchyList(context, orgId) {
			return axios({
				method: 'get',
				url: '/idmf_org_hierarchies/org_id/' + orgId
			});
		},
		// 所属一覧情報を取得
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
		// 組織情報を登録
		registOrg(context) {
			return axios({
				method: 'post',
				url: '/idmf_orgs/',
				data: {
					...context.state.form,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response.data);
				context.commit('setOrgId', response.data.orgId);
			});
		},
		// 組織情報を更新
		updateOrg(context) {
			return axios({
				method: 'put',
				url: '/idmf_orgs/',
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
		// 組織情報を削除
		deleteOrg(context) {

			return new Promise((resolve, reject) => {
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
				}).then(response => {
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
		// 所属情報に対して、該当組織情報が参照されるかどうか判断
		checkPositionOrg(context) {
			var searchTaskList = [];
			context.commit('clearReferencedOrgList');
			for(var i = 0; i < context.state.selectedList.length; i++) {
				searchTaskList.push(context.dispatch('searchPositionOrg', context.state.selectedList[i].orgId));
			}
			return Promise.all(searchTaskList).then((response)=> {
				if(response.length !== 0){
					for(var i = 0; i < response.length; i++) {
						if(response[i].data.paging.total !== 0){
							context.commit('setReferencedOrgList', response[i].data.data[0].orgId);
						}
					}
				}
			});
		},
		// フォームリセット
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		// 組織一覧情報を検索
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
		// 所属一覧情報を検索(ページング処理)
		searchPositionInfo(context, data) {
			context.commit('setPagePosition', data.page);
			context.dispatch('searchPositionList', data.orgId).then((response) => {
				context.commit('setPositionList', response.data.data);
				context.commit('editorUserTitleOfPosition', response.data.data);
				context.commit('setPositionSize', response.data.paging.total);
			});
		},
		// ページ設定
		setPage(context, page) {
			context.commit('setPage', page);
		},
		// 検索結果表示設定
		setSearchResultVisible(context, data) {
			context.commit('setSearchResultVisible', data);
		},
		// 検索フォームクリア
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		// フォームクリア
		clearForm(context) {
			context.commit('clearForm');
		},
		// 適用開始日設定（初期値:システム日付）
		setActiveStartTime(context) {
			context.commit('setActiveStartTime');
		},
		// 選択情報を設定(組織名)
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
		// 所属一覧情報を検索
		searchPositionOrg(context, orgId) {
			return axios({
				method: 'post',
				url: '/idmf_positions/orgs/search',
				data: {
					orgId: orgId
				}
			});
		},
		// 組織情報が表示されるとき、会社情報を設定
		addCompanyOfRegist(context, data) {
			context.commit('setCompanyOfRegist', data);
		},
		// 組織情報が検索されるとき、会社情報を設定
		addCompanyOfSearch(context, data) {
			context.commit('setCompanyOfSearch', data);
		},
		// 組織階層画面へ遷移して、組織階層情報を追加
		addOrgHierarchy(context) {
			context.commit('addOrgHierarchy');
		},
		// 組織階層情報を削除
		deleteOrgHierarchy(context) {
			context.commit('deleteOrgHierarchy');
		},
		// 組織階層情報を選択
		setSelectedOrgHierarchy(context, data) {
			context.commit('setSelectedOrgHierarchy', data);
		},
		// 組織階層部品の制御状態を変換
		orgHierarchyChangeMode(context, data) {
			context.commit('setHierarchyEditable', data);
		},
		// 組織階層をリセット
		resetOrgHierarchy(context) {
			context.commit('resetOrgHierarchy');
			context.commit('setOrgHierarchyList', context.state.orgHierarchyList);
		},
		// OrgDialog画面を起動するとき、選択組織階層の行を記録
		setOrgHierarchyIndex(context, data) {
			context.commit('setOrgHierarchyIndex', data);
		},
		// 編集された組織階層情報を更新
		updateOrgHierarchyList(context) {
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

			return Promise.all(taskList).then(() => {
				context.dispatch('searchOrgHierarchyList', context.state.form.orgId).then((response) => {
					context.commit('setOrgHierarchyList', response.data);
				});
			});
		},
		// 組織階層を更新
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
		// 組織階層を登録
		registOrgHierarchy(context, orgHierarchy) {
			return axios({
				method: 'post',
				url: '/idmf_org_hierarchies/',
				data: {
					...orgHierarchy,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response.data);
			});
		},
		// 組織階層を削除
		deleteOrgHierarchyList(context, deleteOrgHierarchyList) {
			return axios({
				method: 'post',
				url: '/idmf_org_hierarchies/bulk_delete',
				data: deleteOrgHierarchyList
			}).then(function(response) {
				console.log(response.data);
			});
		},
		// 組織階層情報に上位組織IDを設定
		addOrg(context, data) {
			context.commit('setHighOrg', data);
		},
		// ソートキーを設定
		setSortKey(context, data) {
			context.commit('setSortKey', data);
		}
	}
}
