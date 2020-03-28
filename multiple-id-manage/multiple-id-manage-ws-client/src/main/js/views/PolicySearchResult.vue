<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>IDMFPolicy  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deletePolicy" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="policyList" stripe border style="width: 100%" @row-dblclick="showPolicy" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="policyCode" label="Policy Code" width="500" sortable></el-table-column>
                    <el-table-column prop="effect" label="Effect" width="500"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="policySize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
        ...mapState('policy', [
            'policyList',
            'policySize',
            'searchResultVisible',
            'page'
        ])
    },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('policy/setPage', val);
            this.$store.dispatch('policy/searchPolicyList');
        },
        handleSelectionChange(val) {
            this.$store.dispatch('policy/setSelectedList', val);
        },
        showPolicy(row) {
            this.$router.push({ name: 'policyUpdate', params: { policyId: row.policyId }});
            this.$store.dispatch('common/setNaviList', {name: 'IDMFPolicy Details', path: 'policy-update'});
        },
        deletePolicy() {
            if(this.$store.state.policy.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            }else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('policy/deletePolicy').then(() => {
                        var errorMessage = this.$store.state.policy.errorMessage;
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
            this.$store.dispatch('policy/setSortKey', sortKey);
            this.$store.dispatch('policy/searchPolicyList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
