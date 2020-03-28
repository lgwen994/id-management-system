<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>User Search</span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="120px" id="userSearchForm">
                        <el-form-item label="User Code">
                            <el-input v-model="searchForm.userCode" id="userCode"></el-input>
                                <span id="message_userCode"></span>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="User Name">
                            <el-input v-model="searchForm.userName" id="userName"></el-input>
                            <span id="message_userName"></span>
                        </el-form-item>
                        <el-form-item label="Mail Address">
                            <el-input v-model="searchForm.mail" id="mail"></el-input>
                            <span id="message_mail"></span>
                        </el-form-item>
                        <el-form-item label="Telephone Number">
                            <el-input v-model="searchForm.tel" id="tel"></el-input>
                            <span id="message_tel"></span>
                        </el-form-item>
                        <el-form-item label="FAX Number">
                            <el-input v-model="searchForm.fax" id="fax"></el-input>
                            <span id="message_fax"></span>
                        </el-form-item>
                        <el-form-item label="Locale">
                            <el-select v-model="searchForm.locale" placeholder="">
                                <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchUser">Search</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </el-col>
        <el-col :span="12">
            <user-search-result></user-search-result>
        </el-col>
    </el-row>
</template>C

<script>
import { mapState } from 'vuex'
import UserSearchResult from './UserSearchResult.vue'
import { showErrorMessage } from '../common'

export default {
    components: {
        'user-search-result': UserSearchResult
    },
    computed: {
        ...mapState('user', ['searchForm']),
        ...mapState('common', ['locales'])
    },
    methods: {
        search() {
            this.$store.dispatch('user/setPage', 1);
            this.$store.dispatch('user/searchUserList').then(() => {
                this.$store.dispatch('user/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },

        clear() {
            this.$store.dispatch('user/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.user.searchResultVisible === true){
            this.$store.dispatch('user/searchUserList');
        }else{
            this.$store.dispatch('user/clearSearchForm');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: "User ", value: 'user'})
        this.$store.dispatch('common/setNaviList', {name: "User Search", path: 'user-search'});
        this.$store.dispatch('common/setSelectedTable', "user");
    }
}
</script>
