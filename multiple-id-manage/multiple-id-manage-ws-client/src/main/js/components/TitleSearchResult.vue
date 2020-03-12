<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>役職マスタ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteTitle" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="titleList" stripe border ref="titleTable" style="width: 100%" @row-dblclick="showTitle" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="titleCode" label="役職コード" width="115" sortable="custom"></el-table-column>
                    <el-table-column prop="companyId" label="会社ID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="groupFlg" label="グループフラグ" width="145" sortable="custom" :formatter="groupLabel"></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="titleName" label="役職名" width="110"></el-table-column>
                    <el-table-column prop="titleType" label="役職種別" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="titleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
            this.$store.dispatch('common/setNaviList', {name: '役職マスタ詳細', path: 'title-update'});
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('title/setSelectedList', val);
        },
        deleteTitle(event) {
            if(this.$store.state.title.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    // ポジション_ロールから参照されている役職があるかを確認する
                    this.$store.dispatch('title/checkPositionTitle').then(()=> {
                        if(this.$store.state.title.referencedTitleList.length === 0) {
                            this.$store.dispatch('title/deleteTitle').then(() => {
                                var errorMessage = this.$store.state.title.errorMessage;
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
                            this.$alert('役職コード('+ this.$store.state.title.referencedTitleList +')は、所属から参照されているため削除ができません。', 'エラー', {
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
            this.$store.dispatch('title/setSortKey', sortKey);
            this.$store.dispatch('title/searchTitleList').catch(error => showErrorMessage(error));
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return '実在役職';
            } else if(row.groupFlg === 1) {
                return '仮想役職';
            }
        }
    }
}
</script>
