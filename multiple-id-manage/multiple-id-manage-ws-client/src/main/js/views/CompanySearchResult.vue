<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Company List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteCompany" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="companyList" stripe border style="width: 100%" @row-dblclick="showCompany" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="companyCode" label="Company Code" width="110" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="companyName" label="Company Name" width="110"></el-table-column>
                    <el-table-column prop="companyType" label="Company Type" width="110"></el-table-column>
                    <el-table-column prop="companyComment" label="Company Comment" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="companySize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('company', [
           'companyList',
           'companySize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('company/setPage', val);
            this.$store.dispatch('company/searchCompanyList');
        },
        showCompany(row) {
            this.$router.push({ name: 'companyUpdate', params: { companyId: row.companyId }});
            this.$store.dispatch('common/setNaviList', {name: 'Company Details', path: 'company-update'});
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('company/setSelectedList', val);
        },
        deleteCompany() {
            if(this.$store.state.company.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'comfirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'Confirmation of deletion', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('company/deleteCompany').then(() => {
                        var errorMessage = this.$store.state.company.errorMessage;
                        if (errorMessage != '') {
                            this.$alert(errorMessage, 'Error', {
                                ConfirmButtonText: 'OK',
                                type: 'error'
                            });
                        } else {
                            this.$alert('Deletion completed.', 'Deleted', {
                                ConfirmButtonText: 'OK'
                            });
                        }
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // when cancel 
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
