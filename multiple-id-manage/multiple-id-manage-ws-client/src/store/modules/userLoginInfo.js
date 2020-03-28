import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state: {
		searchForm: {
		    userLoginInfoCode: null,
            userId: null,
            userMst: {
                userId:  '',
                userCode:  '',
                activeStartTime:  '',
                activeEndTime:  ''
            },
			loginId: null,
            companyCode: null,
            companyMst: {
                companyId: '',
                companyCode: '',
                activeStartTime: '',
                activeEndTime: ''
            },
			activeStartTime: null,
			activeEndTime: null
		},
		form: {
            userLoginInfoId: '',
            userLoginInfoCode: '',
            userId: '',
            userMst: {
                userId:  '',
                userCode:  '',
                activeStartTime:  '',
                activeEndTime:  ''
            },
            loginId: '',
            companyCode: '',
            companyMst: {
                companyId: '',
                companyCode: '',
                activeStartTime: '',
                activeEndTime: ''
            },
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
            userLoginInfoId: '',
            userLoginInfoCode: '',
            userId: '',
            userMst: {
                userId:  '',
                userCode:  '',
                activeStartTime:  '',
                activeEndTime:  ''
            },
            loginId: '',
            companyCode: '',
            companyMst: {
                companyId: '',
                companyCode: '',
                activeStartTime: '',
                activeEndTime: ''
            },
            activeStartTime: '',
            activeEndTime: '',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deletedFlg: '',
            versionNo: ''
		},
        passwordForm: {
            loginId: '',
            companyCode: '',
            password: '',
            passwordChangedTime: '',
            passwordChangedUser: '',
            passwordMustChangeFlg: ''
        },
        passwordInitialData: {
            loginId: '',
            companyCode: '',
            password: '',
            passwordChangedTime: '',
            passwordChangedUser: '',
            passwordMustChangeFlg: ''
        },
        loginControlInfoForm: {
            loginId: '',
            companyCode: '',
            accountInactiveFlg: '',
            accountActiveStartTime: '',
            accountActiveEndTime: '',
            passwordFailureCount: '',
            passwordFailureResetTime: '',
            lockoutEndTime: '',
        },
        loginControlInfoInitialData: {
            loginId: '',
            companyCode: '',
            accountInactiveFlg: '',
            accountActiveStartTime: '',
            accountActiveEndTime: '',
            passwordFailureCount: '',
            passwordFailureResetTime: '',
            lockoutEndTime: '',
        },
        passwordEditable: false,
        loginControlInfoEditable: false,
		editable: false,
		userLoginInfoSize: '',
        userLoginInfoList: null,
		page: '',
		searchResultVisible: false,
		selectedList: [],
        errorMessage:'',
		sortKey: ''
	},
    mutations: {
        setForm(state, data) {
            state.form = {
                ...data,
            };
        },
        setPasswordForm(state, data) {
            state.passwordForm = {
                ...data,
            };
        },
        setLoginControlInfoForm(state, data) {
            state.loginControlInfoForm = {
                ...data,
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
            };
        },
        setPasswordInitialData(state, data) {
            state.passwordInitialData = {
                ...data,
            };
        },
        setLoginControlInfoInitialData(state, data) {
            state.loginControlInfoInitialData = {
                ...data,
            };
        },
		setEditable(state, data) {
			state.editable = data;
		},
		setPage(state, data) {
			state.page = data;
		},
		setSearchResultVisible(state) {
			state.searchResultVisible = true;
		},
		clearSearchForm(state) {
			state.searchForm.userLoginInfoCode = null;
			state.searchForm.userId = null;
			state.searchForm.userMst.userId = '';
            state.searchForm.userMst.userCode = '';
            state.searchForm.userMst.activeStartTime = '';
            state.searchForm.userMst.activeEndTime = '';
			state.searchForm.loginId = null;
			state.searchForm.companyCode = null;
            state.searchForm.companyMst.companyId = '';
            state.searchForm.companyMst.companyCode = '';
            state.searchForm.companyMst.activeStartTime = '';
            state.searchForm.companyMst.activeEndTime = '';
            state.searchForm.activeStartTime = null;
            state.searchForm.activeEndTime = null;
		},
		setUserLoginInfoId(state, data) {
			state.form.userLoginInfoId = data;
		},
        clearForm(state) {
            let clearData = {
                userLoginInfoId: '',
                userLoginInfoCode: '',
                userId: '',
                userMst: {
                    userId: '',
                    userCode: '',
                    activeStartTime: '',
                    activeEndTime: ''
                },
                loginId: '',
                companyCode: '',
                companyMst: {
                    companyId: '',
                    companyCode: '',
                    activeStartTime: '',
                    activeEndTime: ''
                },
                activeStartTime: '',
                activeEndTime: ''
            };
            state.form = Object.assign({}, state.form, clearData);
        },
		setActiveStartTime(state) {
			state.form.activeStartTime = new Date();
		},
        setCompanyOfRegist(state, data) {
            state.form.companyCode = data.companyCode;
            state.form.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
            state.initialData.companyCode = data.companyCode;
            state.initialData.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setUserOfRegist(state, data) {
            state.form.userId = data.userId;
            state.form.userMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
            state.initialData.userId = data.userId;
            state.initialData.userMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setCompanyOfSearch(state, data) {
            state.searchForm.companyCode = data.companyCode;
            state.searchForm.companyMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setUserOfSearch(state, data) {
            state.searchForm.userId = data.userId;
            state.searchForm.userMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        },
        setUserLoginInfoSize(state, data) {
            state.userLoginInfoSize = data;
        },
        setUserLoginInfoList(state, data) {
            state.userLoginInfoList = data;
        },
		setSelectedList(state, data) {
			state.selectedList = data;
		},
        setPasswordEditable(state, data) {
            state.passwordEditable = data;
        },
        setErrorMessage(state, data){
            state.errorMessage = data;
        },
        setLoginControlInfoEditable(state, data) {
            state.loginControlInfoEditable = data;
        },
		setSortKey(state, data) {
			state.sortKey = data;
		}
    },
    actions: {
        showUserLoginInfo(context, userLoginInfoId) {
			return axios({
				method: 'get',
				url: '/idmf_user_login_infos/' + userLoginInfoId
			}).then(function(response) {
                let itemList = {loginId:response.data.loginId,companyCode:response.data.companyCode};
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
                context.dispatch('searchPassword', itemList);
                context.dispatch('searchLoginControlInfo', itemList);
                console.log(response.data);
			});
		},
        searchPassword(context, itemList) {
            return axios({
                method: 'get',
                url: '/idmf_passwords/' + itemList.loginId + '/company_code/' + itemList.companyCode
            }).then(function(response) {
                context.commit('setPasswordForm', response.data);
                context.commit('setPasswordInitialData', response.data);
                console.log(response);
            });
        },
        searchLoginControlInfo(context, itemList) {
            return axios({
                method: 'get',
                url: '/idmf_login_control_infos/' + itemList.loginId + '/company_code/' + itemList.companyCode
            }).then(function(response) {
                context.commit('setLoginControlInfoForm', response.data);
                context.commit('setLoginControlInfoInitialData', response.data);
                console.log(response);
            });
        },
        updatePassword(context) {
			return axios({
				method: 'put',
                url: '/idmf_passwords/',
				data: {
                    loginId: context.state.passwordForm.loginId,
                    companyCode: context.state.passwordForm.companyCode,
                    password: context.state.passwordForm.password,
                    passwordChangedUser: context.rootState.common.user,
                    passwordMustChangeFlg: context.state.passwordForm.passwordMustChangeFlg
				}
			}).then(function(response) {
				console.log(response);
				context.commit('setPasswordForm', response.data);
				context.commit('setPasswordInitialData', response.data);
			});
		},
        updateLoginControlInfo(context) {
            return axios({
                method: 'put',
                url: '/idmf_login_control_infos/',
                data: {
                    loginId: context.state.loginControlInfoForm.loginId,
                    companyCode: context.state.loginControlInfoForm.companyCode,
                    accountInactiveFlg: context.state.loginControlInfoForm.accountInactiveFlg,
                    accountActiveStartTime: context.state.loginControlInfoForm.accountActiveStartTime,
                    accountActiveEndTime: context.state.loginControlInfoForm.accountActiveEndTime,
                    passwordFailureCount: context.state.loginControlInfoForm.passwordFailureCount,
                    passwordFailureResetTime: context.state.loginControlInfoForm.passwordFailureResetTime,
                    lockoutEndTime: context.state.loginControlInfoForm.lockoutEndTime
                }
            }).then(function(response) {
                console.log(response);
                context.commit('setLoginControlInfoForm', response.data);
                context.commit('setLoginControlInfoInitialData', response.data);
            });
        },
        updateUserLoginInfo(context) {
            return axios({
                method: 'put',
                url: '/idmf_user_login_infos/',
                data: {
                    userLoginInfoId: context.state.form.userLoginInfoId,
                    userLoginInfoCode: context.state.form.userLoginInfoCode,
                    userId: context.state.form.userId,
                    loginId: context.state.form.loginId,
                    companyCode: context.state.form.companyCode,
                    activeStartTime: context.state.form.activeStartTime,
                    activeEndTime:context.state.form.activeEndTime,
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
        changeMode(context, data) {
            context.commit('setEditable', data);
        },
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
        resetPassword(context) {
            context.commit('setPasswordForm', context.state.passwordInitialData);
        },
        resetLoginControlInfo(context) {
            context.commit('setLoginControlInfoForm', context.state.loginControlInfoInitialData);
        },
		searchUserLoginInfoList(context) {
			return context.dispatch('searchUserLoginInfo').then((response) => {
				context.commit('setUserLoginInfoSize', response.data.paging.total);
				context.commit('setUserLoginInfoList', response.data.data);
			});
		},
        searchUserLoginInfo(context) {
			return axios({
				method: 'post',
				url: '/idmf_user_login_infos/search',
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
		registUserLoginInfo(context) {
			return axios({
				method: 'post',
				url: '/idmf_user_login_infos/',
				data: {
					...context.state.form,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit('setUserLoginInfoId', response.data.userLoginInfoId);
				console.log(response);
			});
		},
		clearForm(context) {
			context.commit('clearForm');
		},
		setActiveStartTime(context) {
			context.commit('setActiveStartTime');
		},
        addCompanyOfRegist(context, data) {
            context.commit('setCompanyOfRegist', data);
        },
        addCompanyOfSearch(context, data) {
            context.commit('setCompanyOfSearch', data);
        },
        addUserOfRegist(context, data) {
            context.commit('setUserOfRegist', data);
        },
        addUserOfSearch(context, data) {
            context.commit('setUserOfSearch', data);
        },
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
        deleteUserLoginInfo(context) {
            let deleteUserLoginInfoList = context.state.selectedList.map(userLoginInfo => {
                return {
                    ...userLoginInfo,
                    updatedUser: context.rootState.common.user
                };
            });
            return axios({
                method: 'post',
                url: '/idmf_user_login_infos/bulk_delete',
                data: deleteUserLoginInfoList
            }).then(function(response) {
                console.log(response);
                context.dispatch('searchUserLoginInfoList');
            }).catch(function(error) {
                if(error.response) {
                    context.commit("setErrorMessage", error.response.data.detail);
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else {
                    console.log(error.config);
                }
                //resolve();
            });
        },
        passwordChangeMode(context, data) {
            context.commit('setPasswordEditable', data);
        },
        loginControlInfoChangeMode(context, data) {
            context.commit('setLoginControlInfoEditable', data);
        },
		setSortKey(context, data) {
			context.commit('setSortKey', data);
		}
    }
}
