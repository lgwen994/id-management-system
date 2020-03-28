<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>PasswordPolicy  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deletePasswordPolicy" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="passwordPolicyList" stripe border style="width: 100%" @row-dblclick="showPasswordPolicy" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="passwordPolicyCode" label="PasswordPolicy Code" width="200" sortable></el-table-column>
                    <el-table-column prop="companyCode" label="Company Code" width="150" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="150" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="150" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="passwordMinLength" label="Min Password Length" width="150"></el-table-column>
                    <el-table-column prop="passwordLetterType" label="Password LetterType" width="150" ></el-table-column>
                    <el-table-column prop="passwordMinLetterType" label="PasswordMin  LetterType" width="180"></el-table-column>
                    <el-table-column prop="passwordInHistory" label="Password In History" width="180" sortable></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="passwordPolicySize" page-size="10" @current-change="handleCurrentChange"  :current-page="page">
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
        ...mapState('passwordPolicy', [
            'passwordPolicyList',
            'passwordPolicySize',
            'searchResultVisible',
            'page'
        ])
    },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('passwordPolicy/setPage', val);
            this.$store.dispatch('passwordPolicy/searchPasswordPolicyList');
        },
        handleSelectionChange(val) {
            this.$store.dispatch('passwordPolicy/setSelectedList', val);
        },
        showPasswordPolicy(row) {
             this.$router.push({ name: 'passwordPolicyUpdate', params: { passwordPolicyId: row.passwordPolicyId }});
             this.$store.dispatch('common/setNaviList', {name: 'PasswordPolicy Details', path: 'passwordPolicy-update'});
         },
        deletePasswordPolicy() {
            if(this.$store.state.passwordPolicy.selectedList.length == 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('passwordPolicy/deletePasswordPolicy').then(() => {
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
