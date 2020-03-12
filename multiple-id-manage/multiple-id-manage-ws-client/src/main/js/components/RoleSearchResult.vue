<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>ロールマスタ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteRole" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="roleList" stripe border ref="roleTable" style="width: 100%" @row-dblclick="showRole" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="roleCode" label="ロールコード" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="roleName" label="ロール名" width="110"></el-table-column>
                    <el-table-column prop="roleType" label="ロール種類" width="110"></el-table-column>
                    <el-table-column prop="roleComment" label="ロール説明" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="roleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('role', [
           'roleList',
           'roleSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('role/setPage', val);
            this.$store.dispatch('role/searchRoleList');
        },
        showRole(row) {
            this.$router.push({ name: 'roleUpdate', params: { roleId: row.roleId }});
            this.$store.dispatch('common/setNaviList', {name: 'ロールマスタ詳細', path: 'role-update'});
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('role/setSelectedList', val);
        },
        deleteRole(event) {
            if(this.$store.state.role.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    // ポジション_ロールから参照されているロールがあるかを確認する
                    this.$store.dispatch('role/checkPositionRole').then(()=> {
                        if(this.$store.state.role.referencedRoleList.length === 0) {
                            this.$store.dispatch('role/deleteRole').then(() => {
                                this.$alert('削除が完了しました。', '削除完了', {
                                    confirmButtonText: 'OK'
                                });
                            });
                        } else {
                            this.$alert('ロールコード('+ this.$store.state.role.referencedRoleList +')は、所属から参照されているため削除ができません。', 'エラー', {
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
            this.$store.dispatch('role/setSortKey', sortKey);
            this.$store.dispatch('role/searchRoleList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
