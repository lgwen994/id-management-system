<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>会社マスタ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteCompany" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="companyList" stripe border style="width: 100%" @row-dblclick="showCompany" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="companyCode" label="会社コード" width="110" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="companyName" label="会社名" width="110"></el-table-column>
                    <el-table-column prop="companyType" label="会社種類" width="110"></el-table-column>
                    <el-table-column prop="companyComment" label="会社説明" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="companySize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import Vue from 'vue'
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
            this.$store.dispatch('common/setNaviList', {name: '会社マスタ詳細', path: 'company-update'});
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('company/setSelectedList', val);
        },
        deleteCompany(event) {
            if(this.$store.state.company.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('company/deleteCompany').then(() => {
                        var errorMessage = this.$store.state.company.errorMessage;
                        if (errorMessage != '') {
                            this.$alert(errorMessage, 'エラー', {
                                confirmButtonText: 'OK',
                                type: 'error'
                            });
                        } else {
                            this.$alert('削除が完了しました。', '削除完了', {
                                confirmButtonText: 'OK'
                            });
                        }
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            }
        },
        sort({column, prop, order}) {
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
