import axios from 'axios';
import {formatDate} from '../../common';
import store from '../store';

export default {
	namespaced: true,
	state: {
		// フォーム
		form: {
			orgNameId: '',
			orgId: '',
			orgMst: {
				orgId: '',
				orgCode: '',
				orgName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			orgName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		// 初期データ
		initialData: {
			orgNameId: '',
			orgId: '',
			orgMst: {
				orgId: '',
				orgCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			orgName: '',
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
		editable: false
	},
	mutations: {
		// フォームクリア
		clearForm(state) {
			state.form.orgNameId = '';
			state.form.locale = '';
			state.form.orgName = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
		},
		// フォーム設定
		setForm(state, data) {
			var orgMst = {};
			if(data.orgMst){
				orgMst = data.orgMst;
			}
			state.form = {
					...data,
					orgMst: orgMst
				};
		},
		// 初期データ設定
		setInitialData(state, data) {
			state.initialData = {
					...data,
					orgMst: {}
				};
		},
		// 組織情報設定
		setOrg(state, data) {
			state.form.orgId = data.orgId;
			state.form.orgMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
			state.initialData.orgId = data.orgId;
			state.initialData.orgMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
		},
		// 選択された組織名一覧情報を設定
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		// 組織名ID設定
		setOrgNameId(state, data) {
			state.form.orgNameId = data;
		},
		// 画面部品制御設定
		setEditable(state, data) {
			state.editable = data;
		},
		//　適用日設定
		setActiveTime(state) {
			state.form.activeStartTime = store.state.route.params.activeStartTime;
			state.form.activeEndTime = store.state.route.params.activeEndTime;
		}
	},
	actions: {
		// フォームをクリア
		clearForm(context) {
			context.commit('clearForm');
		},
		// 組織名情報を表示
		showOrgName(context, orgNameId) {
			return axios({
				method: 'get',
				url: '/idmf_org_names/' + orgNameId
			}).then(function(response) {
				console.log(response.data);
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				return axios({
					method: 'get',
					url: '/idmf_orgs/' + response.data.orgId
				}).then(function(response) {
					console.log(response.data);
					context.commit('setOrg', response.data);
				});
			});
		},
		// 組織名情報を更新
		updateOrgName(context) {
			return axios({
				method: 'put',
				url: '/idmf_org_names/',
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
		// 組織名情報を削除
		deleteOrgName(context) {
			context.state.selectedList = context.state.selectedList.map((element) => {
				return {
					...element,
					updatedUser: context.rootState.common.user
				}
			})
			return axios({
				method: 'post',
				url: '/idmf_org_names/bulk_delete',
				data: context.state.selectedList
			}).then(function(response) {
				console.log(response.data);
			});
		},
		// 組織名情報を登録
		registOrgName(context) {
			return axios({
				method: 'post',
				url: '/idmf_org_names/',
				data: {
					...context.state.form,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response.data);
				context.commit('setOrgNameId', response.data.orgNameId);
			});
		},
		// 選択された組織名一覧情報 を設定
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
		// フォームをリセット
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		// 画面部品制御状態を変換
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		// 組織情報を設定
		setOrg(context) {
			context.commit('setOrg', store.state.route.params);
		},
		// 適用日を設定
		setActiveTime(context) {
			context.commit('setActiveTime');
		}
	}
}
