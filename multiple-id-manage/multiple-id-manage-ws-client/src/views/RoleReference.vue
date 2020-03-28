<template>
    <el-dialog title="Role Selection" :visible="roleDialogVisible" width="1100px" @close="closeRoleDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>Search</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="Role Code">
                                <el-input v-model="searchForm.roleCode"></el-input>
                            </el-form-item>
                            <el-form-item label="Active Start Time">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="Active End Time">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="Role Name">
                                <el-input v-model="searchForm.roleName"></el-input>
                            </el-form-item>
                            <el-form-item label="Role Type">
                                <el-input v-model="searchForm.roleType"></el-input>
                            </el-form-item>
                            <el-form-item label="Role Comment">
                                <el-input v-model="searchForm.roleComment"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary">Search</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>Search List</span>
                    </div>
                    <div>
                        <el-button @click="closeRoleDialog">Close</el-button>
                        <div>Double-click the data to set the data.</div>
                        <el-table :data="roleList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addRole">
                            <el-table-column prop="roleCode" label="Role Code" width="110"></el-table-column>
                            <el-table-column prop="activeStartTime" label="Active Start Time" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="Active End Time" width="200" :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="roleName" label="Role Name" width="80"></el-table-column>
                            <el-table-column prop="roleType" label="Role Type" width="100"></el-table-column>
                            <el-table-column prop="roleComment" label="Role Comment" width="100"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="roleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page" />
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
</template>

<script>
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('roleReference', ['searchForm', 'roleDialogVisible', 'roleList', 'roleSize', 'page'])
    },
    methods: {
        search() {
            this.$store.dispatch('roleReference/setPage', 1);
            this.$store.dispatch('roleReference/searchRoleList').catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('roleReference/clearSearchForm');
        },
        handleCurrentChange(val) {
            this.$store.dispatch('roleReference/setPage', val);
            this.$store.dispatch('roleReference/searchRoleList').catch(error => showErrorMessage(error));
        },
        closeRoleDialog() {
            this.$store.dispatch('roleReference/closeRoleDialog');
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        addRole(row) {
            var check = false;
            for(var i = 0; i < this.$store.state.position.roleList.length; i++) {
                if(this.$store.state.position.roleList[i].role.roleId === row.roleId) {
                    check =true;
                    break;
                }
            }
            if(!check) {
                this.$store.dispatch('position/addRole', row);
            } else {
                this.$alert('Role Code('+ row.roleCode +') Has already been created.', 'Error', {
                    ConfirmButtonText: 'OK',
                    type: 'error'
                });
            }
        }
    }
}
</script>
