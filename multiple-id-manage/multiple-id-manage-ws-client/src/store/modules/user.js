import axios from 'axios';

export default {
	namespaced: true,
	state: {
		searchForm: {
		    userCode: null,
			activeStartTime: null,
			activeEndTime: null,
            userName: null,
            mail: null,
            tel: null,
			fax:null,
            locale:null
		},
		form: {
            userId: '',
            userCode: '',
			activeStartTime: '',
			activeEndTime: '',
            userName: '',
            mail: '',
            tel: '',
            fax:'',
            locale:'',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
		initialData: {
            userId: '',
            userCode: '',
            activeStartTime: '',
            activeEndTime: '',
            userName: '',
            mail: '',
            tel: '',
            fax:'',
            locale:'',
            createdTime: '',
            createdUser: '',
            updatedTime: '',
            updatedUser: '',
            deleteFlg: '',
            versionNo: ''
		},
        userNameList: null,
		editable: false,
        userSize: '',
        userList: null,
        page: '',
		searchResultVisible: false,
		selectedList: [],
		deleteUserNameList:[],
		referencedUserList:[],
        errorMessage:'',
        sortKey: ''
	},
    mutations: {
        setForm(state, data) {
            state.form = {
                ...data,
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
            };
        },
        setUserNameList(state, data) {
			state.userNameList = data;
		},
        setEditable(state, data) {
			state.editable = data;
		},
        setUserSize(state, data) {
			state.userSize = data;
		},
        setUserList(state, data) {
			state.userList = data;
		},
        setSearchResultVisible(state) {
			state.searchResultVisible = true;
		},
        clearSearchForm(state) {
            state.searchForm.userCode = null;
            state.searchForm.activeStartTime = null;
            state.searchForm.activeEndTime = null;
            state.searchForm.userName = null;
            state.searchForm.mail = null;
            state.searchForm.tel = null;
            state.searchForm.fax = null;
            state.searchForm.locale = null;
		},
        setUserId(state, data) {
			state.form.userId = data;
		},
        clearForm(state) {
            state.form.userId = '';
			state.form.userCode = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
			state.form.userName = '';
			state.form.mail = '';
			state.form.tel = '';
            state.form.fax = '';
            state.form.locale = '';
		},
        setPage(state, data) {
            state.page = data;
        },
        setActiveStartTime(state) {
			state.form.activeStartTime = new Date();
		},
        setSelectedList(state, data) {
			state.selectedList = data;
		},
		setDeleteUserNameList(state, data) {
			state.deleteUserNameList = state.deleteUserNameList.concat(data);
		},
		clearDeleteUserNameList(state) {
			state.deleteUserNameList = [];
		},
		setReferencedUserList(state, data) {
			state.referencedUserList = state.referencedUserList.concat(data);
		},
		clearReferencedUserList(state) {
			state.referencedUserList = [];
		},
		setErrorMessage(state, data){
    		state.errorMessage = data;
		},
        setSortKey(state, data) {
            state.sortKey = data;
        }
    },
    actions: {
		showUser(context, userId) {
			axios({
				method: 'get',
				url: '/idmf_users/' + userId
			}).then(function(response) {
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				console.log(response.data);
				context.dispatch('searchUserNameList', userId);
			}).catch(function(error) {
				if(error.response) {
					console.log(error.response.data);
					console.log(error.response.status);
					console.log(error.response.headers);
				} else {
					console.log(error.config);
				}
			});
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		searchUserNameList(context, userId) {
            return axios({
				method: 'get',
				url: '/idmf_user_names/find_by_user/' + userId
			}).then(function(response) {
                console.log(response.data);
                context.commit('setUserNameList', response.data);
            });
		},
		updateUser(context) {
			return axios({
				method: 'put',
				url: '/idmf_users/',
				data: {
					userId: context.state.form.userId,
					userCode: context.state.form.userCode,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime:context.state.form.activeEndTime,
					userName: context.state.form.userName,
					mail: context.state.form.mail,
					tel: context.state.form.tel,
					fax: context.state.form.fax,
					locale: context.state.form.locale,
					updatedUser: context.rootState.common.user,
					versionNo : context.state.form.versionNo
				}
			}).then(function(response) {
				console.log(response);
				context.commit("setForm", response.data);
				context.commit('setInitialData', response.data);
			});
		},
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
        setPage(context, page) {
            context.commit('setPage', page);
        },

        searchUserList(context) {
            return context.dispatch('searchUser').then((response) => {
                context.commit('setUserSize', response.data.paging.total);
                context.commit('setUserList', response.data.data);
            });
        },
        searchUser(context) {
            return axios({
                method: 'post',
                url: '/idmf_users/search',
                data: context.state.searchForm,
                params: {
                    pageNo: context.state.page,
                    pageSize: 10,
                    sort: context.state.sortKey
                }
            });
        },
		setSearchResultVisible(context, data) {
			context.commit('setSearchResultVisible', data);
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		registUser(context) {
			return axios({
					method: 'post',
					url: '/idmf_users/',
					data: {
                        ...context.state.form,
                        createdUser: context.rootState.common.user
					}
				}).then(function(response) {
					context.commit("setUserId", response.data.userId);
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
        deleteUser(context) {
            return new Promise((resolve) => {
                let deleteUserList = context.state.selectedList.map(user => {
                    return {
                        ...user,
                        updatedUser: context.rootState.common.user
                    };
                });
                let searchUserNameTask = context.state.selectedList.map(user =>
                    axios({

                        method: 'get',
                        url: '/idmf_user_names/find_by_user/' + user.userId
                    })
                );
                Promise.all(searchUserNameTask).then(response => {
                    let deleteUserNameList = [];
                    response.forEach(element => {
                        if(element && element.data && element.data.length !== 0) {
                            let userName = {
                                userId: element.data[0].userId,
                                updatedUser: context.rootState.common.user
                            };
                            deleteUserNameList.push(userName);
                        }
                    });
                    return axios({
                        method: 'post',
                        url: '/idmf_user_names/bulk_delete',
                        data: deleteUserNameList
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
                        url: '/idmf_users/bulk_delete',
                        data: deleteUserList
                    }).then(function(response) {
                        console.log(response.data);
                        context.dispatch('searchUserList');
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
		getUserNameList(context, userId) {
			return axios({
				method: 'get',
				url: '/idmf_user_names/find_by_user/' + userId
			}).then(function(response) {
				context.commit('setDeleteUserNameList', response.data);
			}).catch(function(error) {
				if(error.response) {
					console.log(error.response.data);
					console.log(error.response.status);
					console.log(error.response.headers);
				} else {
					console.log(error.config);
				}
			});
		},
        searchPositionUser(context, userId) {
            return axios({
                method: 'post',
                url: '/idmf_positions/users/search',
                data: {
                    userId: userId
                }
            });
        },
        checkPositionUser(context) {
            var searchTaskList = [];
            context.commit('clearReferencedUserList');
            for(var i = 0; i < context.state.selectedList.length; i++) {
                searchTaskList.push(context.dispatch('searchPositionUser', context.state.selectedList[i].userId));
            }
            return Promise.all(searchTaskList).then((response)=> {
                if(response.length != 0){
                    for(var i = 0; i < response.length; i++) {
                        if(response[i].data.paging.total != 0){
                            context.commit('setReferencedUserList', response[i].data.data[0].userId);
                        }
                    }
                }
            });
        },
        setSortKey(context, data) {
            context.commit('setSortKey', data);
        }
    }
}
