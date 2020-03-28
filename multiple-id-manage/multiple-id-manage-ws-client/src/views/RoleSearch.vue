<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>Role Search</span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="100px" id="roleSearchForm">
                        <el-form-item label="Role Code">
                            <el-input v-model="searchForm.roleCode" id="roleCode"></el-input>
                            <span id="message_roleCode"></span>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Role Name">
                            <el-input v-model="searchForm.roleName" id="roleName"></el-input>
                            <span id="message_roleName"></span>
                        </el-form-item>
                        <el-form-item label="Role Type">
                            <el-input v-model="searchForm.roleType" id="roleType"></el-input>
                            <span id="message_roleType"></span>
                        </el-form-item>
                        <el-form-item label="Role Comment">
                            <el-input v-model="searchForm.roleComment" id="roleComment"></el-input>
                            <span id="message_roleComment"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchRole">Search</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </el-col>
        <el-col :span="12">
            <role-search-result></role-search-result>
        </el-col>
    </el-row>
</template>

<script>
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'
import RoleSearchResult from './RoleSearchResult.vue'

export default {
    components: {
        'role-search-result': RoleSearchResult
    }
    ,
    computed: {
        ...mapState('role', ['searchForm'])
    }
    ,
    methods: {
        search() {
            this.$store.dispatch('role/setPage', 1);
            this.$store.dispatch('role/searchRoleList').then(() => {
                this.$store.dispatch('role/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('role/clearSearchForm');
        },

        created: function() {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setTable', {name: 'Role ', value: 'role'})
            this.$store.dispatch('common/setNaviList', {name: 'Role Search', path: 'role-search'});
            this.$store.dispatch('common/setSelectedTable', 'role');
        }
    }
}
</script>
