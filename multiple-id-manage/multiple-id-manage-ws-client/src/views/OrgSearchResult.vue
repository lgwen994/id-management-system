<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Orgnazation  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteOrg" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="orgList" stripe border ref="orgTable" style="width: 100%" @row-dblclick="showOrg" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="orgCode" label="Orgnazation Code" width="115" sortable="custom"></el-table-column>
                    <el-table-column prop="companyId" label="CompanyID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="groupFlg" label="Group Flg" width="145" sortable="custom" :formatter="groupLabel"></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="orgName" label="Orgnazation Name" width="110"></el-table-column>
                    <el-table-column prop="orgType" label="Orgnazation Type" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="orgSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('org', [
           'orgList',
           'orgSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('org/setPage', val);
            this.$store.dispatch('org/searchOrgList');
        },
        showOrg(row) {
            this.$router.push({ name: 'orgUpdate', params: {orgId: row.orgId }});
            this.$store.dispatch('common/setNaviList', {name: 'Orgnazation Details', path: 'org-update'});
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('org/setSelectedList', val);
        },
        deleteOrg() {
            if(this.$store.state.org.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    // ポジション_Role からDialogされているOrgnazation があるかをConfirmする
                    this.$store.dispatch('org/checkPositionOrg').then(()=> {
                        if(this.$store.state.org.referencedOrgList.length === 0) {
                            this.$store.dispatch('org/deleteOrg').then(() => {
                                var errorMessage = this.$store.state.org.errorMessage;
                                if (errorMessage !== '') {
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
                            this.$alert('Orgnazation Code('+ this.$store.state.org.referencedOrgList +')は、Position からDialogされているためDeleteができません。', 'Error', {
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
            this.$store.dispatch('org/setSortKey', sortKey);
            this.$store.dispatch('org/searchOrgList').catch(error => showErrorMessage(error));
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return 'Real Orgnazation ';
            } else if(row.groupFlg === 1) {
                return 'Logical Orgnazation ';
            }
        }
    }
}
</script>
