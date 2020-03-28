<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>User タ List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteUser" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="userList" stripe border style="width: 100%" @row-dblclick="showUser" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="userCode" label="User Code" width="110" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="userName" label="User Name" width="110"></el-table-column>
                    <el-table-column prop="mail" label="Mail Address" width="110"></el-table-column>
                    <el-table-column prop="tel" label="Telephone Number" width="110"></el-table-column>
                    <el-table-column prop="fax" label="FAX Number" width="110"></el-table-column>
                    <el-table-column prop="locale" label="Locale" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="userSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('user', [
           'userList',
           'userSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('user/setPage', val);
            this.$store.dispatch('user/searchUserList');
        },
        showUser(row) {
            this.$router.push({ name: 'userUpdate', params: { userId: row.userId }});
            this.$store.dispatch('common/setNaviList', {name: 'User Details', path: 'user-update'});
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('user/setSelectedList', val);
        },
        deleteUser() {
            if(this.$store.state.user.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    
                    this.$store.dispatch('user/checkPositionUser').then(()=> {
                        if(this.$store.state.user.referencedUserList.length === 0) {
                            this.$store.dispatch('user/deleteUser').then(() => {
                                var errorMessage = this.$store.state.user.errorMessage;
                                if (errorMessage != '') {
                                    this.$alert(errorMessage, 'Error', {
                                        ConfirmButtonText: 'OK',
                                        type: 'error'
                                    });
                                } else {
                                    this.$alert('Delete completed.', 'Deleted', {
                                        ConfirmButtonText: 'OK'
                                    });
                                }
                            });
                        } else {
                            this.$alert('User Code('+ this.$store.state.user.referencedUserList +') Cannot be deleted because it is referenced by the affiliation.', 'Error', {
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
            this.$store.dispatch('user/setSortKey', sortKey);
            this.$store.dispatch('user/searchUserList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
