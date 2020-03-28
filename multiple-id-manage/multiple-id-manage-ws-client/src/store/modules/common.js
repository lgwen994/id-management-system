import axios from 'axios';

export default {
    namespaced: true,
    state: {
        locales: [{
            value: 'ja_JP',
            label: 'Japanese'
        }, {
            value: 'en_US',
            label: 'English'
        }, {
            value: 'zh_CN',
            label: 'Chinese'
        }, {
            value: 'de_DE',
            label: 'German'
        }],
        naviList: [],
        table: null,
        selectedTable: 'Select Table',
        user: ''
    },
    getters: {
        getLocale: (state) => (locale) => {
            for(var key in state.locales) {
                if(state.locales[key].value === locale) {
                    return state.locales[key].label;
                }
            }
        },
        getLastOperation(state) {
            return state.naviList[state.naviList.length-1];
        }
    },
    mutations: {
        setNaviList(state, data) {
            state.naviList.push(data);
        },
        deleteNavi(state, data) {
            state.naviList.some(function(v, i){
                if (v.path === data.path) state.naviList.splice(i,1);
            });
        },
        setTable(state, data) {
            state.table = data;
        },
        deleteNaviList(state) {
            state.naviList = [];
        },
        setSelectedTable(state, data) {
            state.selectedTable = data;
        },
        setUser(state, data) {
            state.user = data;
        }
    },
    actions: {
        setNaviList(context, data) {
            context.commit('setNaviList', data);
        },
        deleteNavi(context, data) {
            context.commit('deleteNavi', data);
        },
        setTable(context, data) {
            context.commit('setTable', data);
        },
        deleteNaviList(context) {
            context.commit('deleteNaviList');
        },
        setSelectedTable(context, data) {
            if(data === 'role') {
                context.commit('setSelectedTable', 'Role Management');
            } else if(data === 'position') {
                context.commit('setSelectedTable', 'Position Management');
            } else if(data === 'rolename') {
                context.commit('setSelectedTable', 'Role Name Management');
            } else if(data === 'title') {
                context.commit('setSelectedTable', 'Title Management');
            } else if(data === 'titleName') {
                context.commit('setSelectedTable', 'Title Name Management');
            } else if(data === 'org') {
                context.commit('setSelectedTable', 'Orgnazation Management');
            } else if(data === 'orgName') {
                context.commit('setSelectedTable', 'Orgnazation Name Management');
            } else if(data === 'company') {
                context.commit('setSelectedTable', 'Company Management');
            } else if(data === 'companyName') {
                context.commit('setSelectedTable', 'Company Name Management');
            } else if(data === 'user') {
                context.commit('setSelectedTable', 'User Management');
            } else if(data === 'userName') {
                context.commit('setSelectedTable', 'User Name Management');
            } else if(data === 'userLoginInfo') {
                context.commit('setSelectedTable', 'Users Login Information Management');
            } else if(data === 'userLoginInfo') {
                context.commit('setSelectedTable', 'Users Login Information Management');
            } else if(data === 'loginControlInfo') {
                context.commit('setSelectedTable', 'Login Control Information Management');
            } else if(data === 'password') {
                context.commit('setSelectedTable', 'Password Management');
            } else if(data === 'loginPolicy') {
                context.commit('setSelectedTable', 'Login Policy Management');
            } else if(data === 'passwordPolicy') {
                context.commit('setSelectedTable', 'Password Policy Management');
            }
        },
        setUser(context) {
            return axios({
                method: 'get',
                url: 'http://localhost:8080/user'
            }).then(function(response) {
                context.commit('setUser', response.data.name);
            });
        }
    }
}
