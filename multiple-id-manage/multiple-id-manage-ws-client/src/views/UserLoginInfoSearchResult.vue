<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>User Login Info  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteUserLoginInfo" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="userLoginInfoList" stripe border ref="userLoginInfoTable" style="width: 100%" @row-dblclick="showUserLoginInfo" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="userLoginInfoCode" label="User Login Info Code" width="160" sortable="custom"></el-table-column>
                    <el-table-column prop="userId" label="User ID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="loginId" label="Login ID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="companyCode" label="Company Code" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="userLoginInfoSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('userLoginInfo', [
           'userLoginInfoList',
           'userLoginInfoSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('userLoginInfo/setPage', val);
            this.$store.dispatch('userLoginInfo/searchUserLoginInfoList');
        },
        showUserLoginInfo(row) {
            this.$router.push({ name: 'userLoginInfoUpdate', params: { userLoginInfoId: row.userLoginInfoId }});
            this.$store.dispatch('common/setNaviList', {name: 'User Login Info Details', path: 'userLoginInfo-update'});
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('userLoginInfo/setSelectedList', val);
        },
        deleteUserLoginInfo() {
            if(this.$store.state.userLoginInfo.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('userLoginInfo/deleteUserLoginInfo').then(() => {
                        this.$alert('Delete completed.', 'Deleted', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
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
            this.$store.dispatch('userLoginInfo/setSortKey', sortKey);
            this.$store.dispatch('userLoginInfo/searchUserLoginInfoList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
