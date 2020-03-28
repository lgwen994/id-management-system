<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Title  List</span>
            </div>
            <div>
                <el-col :span="16">
                    Double-click on data to see details
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteTitle" type="primary">Delete</el-button>
                    </div>
                </el-col>
                <el-table :data="titleList" stripe border ref="titleTable" style="width: 100%" @row-dblclick="showTitle" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="titleCode" label="Title Code" width="115" sortable="custom"></el-table-column>
                    <el-table-column prop="companyId" label="CompanyID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="groupFlg" label="Group Flg" width="145" sortable="custom" :formatter="groupLabel"></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="titleName" label="Title Name" width="110"></el-table-column>
                    <el-table-column prop="titleType" label="Title Type" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="titleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('title', [
           'titleList',
           'titleSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('title/setPage', val);
            this.$store.dispatch('title/searchTitleList');
        },
        showTitle(row) {
            this.$router.push({ name: 'titleUpdate', params: { titleId: row.titleId }});
            this.$store.dispatch('common/setNaviList', {name: 'Title Details', path: 'title-update'});
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('title/setSelectedList', val);
        },
        deleteTitle() {
            if(this.$store.state.title.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('title/checkPositionTitle').then(()=> {
                        if(this.$store.state.title.referencedTitleList.length === 0) {
                            this.$store.dispatch('title/deleteTitle').then(() => {
                                var errorMessage = this.$store.state.title.errorMessage;
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
                            this.$alert('Title Code('+ this.$store.state.title.referencedTitleList +')は、Position からDialogされているためDeleteができません。', 'Error', {
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
            this.$store.dispatch('title/setSortKey', sortKey);
            this.$store.dispatch('title/searchTitleList').catch(error => showErrorMessage(error));
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return 'Real Title ';
            } else if(row.groupFlg === 1) {
                return 'Logical Title ';
            }
        }
    }
}
</script>
