<template>
    <el-dialog title="ロールマスタ選択" :visible="roleDialogVisible" width="1100px" @close="closeRoleDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>検索</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="ロールコード">
                                <el-input v-model="searchForm.roleCode"></el-input>
                            </el-form-item>
                            <el-form-item label="有効開始日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="有効終了日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="ロール名">
                                <el-input v-model="searchForm.roleName"></el-input>
                            </el-form-item>
                            <el-form-item label="ロール種類">
                                <el-input v-model="searchForm.roleType"></el-input>
                            </el-form-item>
                            <el-form-item label="ロールコメント">
                                <el-input v-model="searchForm.roleComment"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="clear">クリア</el-button>
                                <el-button @click="search" type="primary">検索</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>検索一覧</span>
                    </div>
                    <div>
                        <el-button @click="closeRoleDialog">閉じる</el-button>
                        <div>データをダブルクリックするとデータを設定します。</div>
                        <el-table :data="roleList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addRole">
                            <el-table-column prop="roleCode" label="ロールコード" width="110"></el-table-column>
                            <el-table-column prop="activeStartTime" label="有効開始日時" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="有効終了日時" width="200" :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="roleName" label="ロール名" width="80"></el-table-column>
                            <el-table-column prop="roleType" label="ロール種類" width="100"></el-table-column>
                            <el-table-column prop="roleComment" label="ロール説明" width="100"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="roleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('roleReference', ['searchForm', 'roleDialogVisible', 'roleList', 'roleSize', 'page'])
    },
    methods: {
        search(event) {
            this.$store.dispatch('roleReference/setPage', 1);
            this.$store.dispatch('roleReference/searchRoleList').catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('roleReference/clearSearchForm');
        },
        handleCurrentChange(val) {
            this.$store.dispatch('roleReference/setPage', val);
            this.$store.dispatch('roleReference/searchRoleList').catch(error => showErrorMessage(error));
        },
        closeRoleDialog() {
            this.$store.dispatch('roleReference/closeRoleDialog');
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        addRole(row) {
            // 同じロールIDが既に一覧にあるかチェックする
            var check = false;
            for(var i = 0; i < this.$store.state.position.roleList.length; i++) {
                if(this.$store.state.position.roleList[i].role.roleId === row.roleId) {
                    check =true;
                    break;
                }
            }
            if(!check) {
                this.$store.dispatch('position/addRole', row);
            } else {
                this.$alert('ロールコード('+ row.roleCode +')は、既に追加されています。', 'エラー', {
                    confirmButtonText: 'OK',
                    type: 'error'
                });
            }
        }
    }
}
</script>
