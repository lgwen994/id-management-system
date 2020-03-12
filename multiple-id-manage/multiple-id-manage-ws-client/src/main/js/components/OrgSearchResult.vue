<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>組織マスタ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteOrg" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="orgList" stripe border ref="orgTable" style="width: 100%" @row-dblclick="showOrg" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="orgCode" label="組織コード" width="115" sortable="custom"></el-table-column>
                    <el-table-column prop="companyId" label="会社ID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="groupFlg" label="グループフラグ" width="145" sortable="custom" :formatter="groupLabel"></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="orgName" label="組織名" width="110"></el-table-column>
                    <el-table-column prop="orgType" label="組織種別" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="orgSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
            this.$store.dispatch('common/setNaviList', {name: '組織マスタ詳細', path: 'org-update'});
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('org/setSelectedList', val);
        },
        deleteOrg(event) {
            if(this.$store.state.org.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    // ポジション_ロールから参照されている組織があるかを確認する
                    this.$store.dispatch('org/checkPositionOrg').then(()=> {
                        if(this.$store.state.org.referencedOrgList.length === 0) {
                            this.$store.dispatch('org/deleteOrg').then(() => {
                                var errorMessage = this.$store.state.org.errorMessage;
                                if (errorMessage !== '') {
                                    this.$alert(errorMessage, 'エラー', {
                                        confirmButtonText: 'OK',
                                        type: 'error'
                                    });
                                } else {
                                    this.$alert('削除が完了しました。', '削除完了', {
                                        confirmButtonText: 'OK'
                                    });
                                }
                            });
                        } else {
                            this.$alert('組織コード('+ this.$store.state.org.referencedOrgList +')は、所属から参照されているため削除ができません。', 'エラー', {
                                confirmButtonText: 'OK',
                                type: 'error'
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
            this.$store.dispatch('org/setSortKey', sortKey);
            this.$store.dispatch('org/searchOrgList').catch(error => showErrorMessage(error));
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return '実在組織';
            } else if(row.groupFlg === 1) {
                return '仮想組織';
            }
        }
    }
}
</script>
