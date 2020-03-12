<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ロールマスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="roleUpdateForm">
                <el-form-item label="ロールID">
                    <el-input v-model="form.roleId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ロールコード">
                    <el-input v-model="form.roleCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="ロール名">
                    <el-input v-model="form.roleName" :disabled="!editable" id="roleName"></el-input>
                    <span id="message_roleName"></span>
                </el-form-item>
                <el-form-item label="ロール種類">
                    <el-input v-model="form.roleType" :disabled="!editable" id="roleType"></el-input>
                    <span id="message_roleType"></span>
                </el-form-item>
                <el-form-item label="ロール説明">
                    <el-input v-model="form.roleComment" :disabled="!editable" id="roleComment"></el-input>
                    <span id="message_roleComment"></span>
                </el-form-item>
                <el-form-item label="作成日時">
                    <el-date-picker type="datetime" v-model="form.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="作成者">
                    <el-input v-model="form.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="変更日時">
                    <el-date-picker type="datetime" v-model="form.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="変更者">
                    <el-input v-model="form.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="論理削除フラグ">
                    <el-input v-model="form.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="バージョン">
                    <el-input v-model="form.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">キャンセル</el-button>
                        <el-button @click="resetForm">リセット</el-button>
                        <el-button type="primary" @click="update" id="updateRole">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                ロール名一覧
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">追加</el-button>
                    <el-button type="primary" @click="deleteRolename">削除</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="rolenameList" stripe border highlight-current-row @row-dblclick="showRolename" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="ロケール" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="roleName" label="ロール名" width="100"></el-table-column>
                  <el-table-column prop="activeStartTime" label="有効開始日時" width="150" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="有効終了日時" width="150" :formatter="formatActiveEndTime"></el-table-column>
              </el-table>
            </el-row>
        </div>
    </el-card>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('role', ['form', 'rolenameList','editable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('role/changeMode', flg);
        },
        getLocale(row, column) {
            return this.$store.getters['common/getLocale'](row.locale);
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        regist(event) {
            this.$router.push({
                name: 'rolenameRegist',
                params: {
                    roleId: this.$store.state.role.form.roleId,
                    roleCode: this.$store.state.role.form.roleCode,
                    roleName: this.$store.state.role.form.roleName,
                    activeStartTime: this.$store.state.role.form.activeStartTime,
                    activeEndTime: this.$store.state.role.form.activeEndTime
                }
            });
            this.$store.dispatch('common/setNaviList', {name: 'ロール名マスタ登録', path: 'rolename-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'rolename');
        },
        handleSelectionChange(val) {
            this.$store.dispatch('rolename/setSelectedList', val);
        },
        deleteRolename(event) {
            if(this.$store.state.rolename.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('rolename/deleteRolename').then(() => {
                        this.$store.dispatch('role/searchRolenameList', this.$store.state.role.form.roleId);
                        this.$alert('削除が完了しました。', '削除完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // キャンセルの場合
                });
            }
        },
        showRolename(row) {
            this.$router.push({ name: 'rolenameUpdate', params: { roleNameId: row.roleNameId }});
            this.$store.dispatch('common/setNaviList', {name: 'ロール名マスタ詳細', path: 'rolename-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'rolename');
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('role/updateRole').then(() => {
                    this.changeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        resetForm() {
            this.$store.dispatch('role/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('role/showRole', this.$route.params.roleId).catch((error) => {
                showErrorMessage(error);
            })
        }
        if(this.$store.getters['common/getLastOperation'].path !== 'role-update') {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: 'ロールマスタ', value: 'role'});
            this.$store.dispatch('common/setSelectedTable', 'role');
        }
    }
}
</script>
