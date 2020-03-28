<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Login Policy  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteLoginPolicy" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="loginPolicyList" stripe border style="width: 100%" @row-dblclick="showLoginPolicy" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="loginPolicyCode" label="Login Policy Code" width="200" sortable></el-table-column>
                    <el-table-column prop="companyCode" label="Company Code" width="150" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="150" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="150" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="passwordExpireTerm" label="Password Expire Term" width="180"></el-table-column>
                    <el-table-column prop="passwordMaxFailure" label="Login Failure Count Max Failure" width="180"></el-table-column>
                    <el-table-column prop="passwordFailureResetTerm" label="Login Failure Count Failure Reset Term" width="150" ></el-table-column>
                    <el-table-column prop="lockoutTerm" label="Account Lock Out Term" width="180"></el-table-column>
                    <el-table-column prop="maxSessionLimit" label="Session Max Failure" width="180"></el-table-column>
                    <el-table-column prop="permitTime" label="Permit Login Time" width="180"></el-table-column>
                    <el-table-column prop="permitDomain" label="Permit Domain" width="180"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="loginPolicySize" page-size="10" @current-change="handleCurrentChange"  :current-page="page">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>

<script>
    import { mapState } from 'vuex';
    import {showErrorMessage} from '../common';
    export default {
        computed: {
            ...mapState('loginPolicy', [
                'loginPolicyList',
                'loginPolicySize',
                'searchResultVisible',
                'page'
            ])
        },
        methods: {
            handleCurrentChange(val) {
                this.$store.dispatch('loginPolicy/setPage', val);
                this.$store.dispatch('loginPolicy/searchLoginPolicyList');
            },
            handleSelectionChange(val) {
                this.$store.dispatch('loginPolicy/setSelectedList', val);
            },
            showLoginPolicy(row) {
                this.$router.push({ name: 'loginPolicyUpdate', params: { loginPolicyId: row.loginPolicyId }});
                this.$store.dispatch('common/setNaviList', {name: 'Login Policy Details', path: 'loginPolicy-update'});
            },
            deleteLoginPolicy() {
                if(this.$store.state.loginPolicy.selectedList.length == 0) {
                    this.$alert('Please select the data to be deleted.', 'Confirm', {
                        ConfirmButtonText: 'OK'
                    });
                } else {
                    this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                        ConfirmButtonText: 'OK',
                        cancelButtonText: 'Cancel',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('loginPolicy/deleteLoginPolicy').then(() => {
                            this.$alert('Delete completed.', 'Deleted', {
                                ConfirmButtonText: 'OK'
                            });
                        }).catch(error => {
                            showErrorMessage(error);
                        });
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
                this.$store.dispatch('loginPolicy/setSortKey', sortKey);
                this.$store.dispatch('role/searchLoginPolicyList').catch(error => showErrorMessage(error));
            }
        }
    }



</script>
