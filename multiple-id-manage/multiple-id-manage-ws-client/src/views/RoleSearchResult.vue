<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Role  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteRole" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="roleList" stripe border ref="roleTable" style="width: 100%" @row-dblclick="showRole" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="roleCode" label="Role Code" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="roleName" label="Role Name" width="110"></el-table-column>
                    <el-table-column prop="roleType" label="Role Type" width="110"></el-table-column>
                    <el-table-column prop="roleComment" label="Role Comment" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="roleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import {formatDate, showErrorMessage} from '../common';

export default {
    computed: {
       ...mapState('role', [
           'roleList',
           'roleSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('role/setPage', val);
            this.$store.dispatch('role/searchRoleList');
        },
        showRole(row) {
            this.$router.push({ name: 'roleUpdate', params: { roleId: row.roleId }});
            this.$store.dispatch('common/setNaviList', {name: 'Role Details', path: 'role-update'});
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('role/setSelectedList', val);
        },
        deleteRole() {
            if(this.$store.state.role.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('role/checkPositionRole').then(()=> {
                        if(this.$store.state.role.referencedRoleList.length === 0) {
                            this.$store.dispatch('role/deleteRole').then(() => {
                                this.$alert('Delete completed.', 'Deleted', {
                                    ConfirmButtonText: 'OK'
                                });
                            });
                        } else {
                            this.$alert('Role Code('+ this.$store.state.role.referencedRoleList +')は、Position からDialogされているためDeleteができません。', 'Error', {
                                ConfirmButtonText: 'OK',
                                type: 'error'
                            });
                        }
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // Cancel
                });
            }
        },
        sort({prop, order}) {
            let sortKey = prop.replace(/([A-Z])/g,
                function(s) {
                    return '_' + s.charAt(0).toLowerCase();
                }
            );
            if(order === 'descending') {
                sortKey = `-${sortKey}`
            }
            this.$store.dispatch('role/setSortKey', sortKey);
            this.$store.dispatch('role/searchRoleList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
