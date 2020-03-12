<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ユーザマスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="120px" id="companyUpdateForm">
                <el-form-item label="ユーザID">
                    <el-input v-model="form.userId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ユーザコード">
                    <el-input v-model="form.userCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="ユーザ名">
                    <el-input v-model="form.userName" :disabled="!editable" id="userName"></el-input>
                    <span id="message_userName"></span>
                </el-form-item>
                <el-form-item label="メールアドレス">
                    <el-input v-model="form.mail" :disabled="!editable" id="mail"></el-input>
                    <span id="message_mail"></span>
                </el-form-item>
                <el-form-item label="電話番号">
                <el-input v-model="form.tel" :disabled="!editable" id="tel"></el-input>
                <span id="message_tel"></span>
                </el-form-item>
                <el-form-item label="FAX番号">
                    <el-input v-model="form.fax" :disabled="!editable" id="fax"></el-input>
                    <span id="message_fax"></span>
                </el-form-item>
                <el-form-item label="ロケール">
                    <el-select v-model="form.locale" :disabled="!editable">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
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
                        <el-button type="primary" @click="update" id="updateUser">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                    ユーザ名一覧
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">追加</el-button>
                    <el-button type="primary" @click="deleteUserName">削除</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="userNameList" stripe border highlight-current-row @row-dblclick="showUserName" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="ロケール" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="userName" label="ユーザ名" width="100"></el-table-column>
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
        ...mapState('user', ['form', 'userNameList','editable']),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('user/changeMode', flg);
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
                name: 'userNameRegist',
                params: {
                    userId: this.$store.state.user.form.userId,
                    userCode: this.$store.state.user.form.userCode,
                    activeStartTime: this.$store.state.user.form.activeStartTime,
                    activeEndTime: this.$store.state.user.form.activeEndTime
                }
            });
            this.$store.dispatch('common/setNaviList', {name: 'ユーザ名マスタ登録', path: 'userName-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "userName");
        },
        handleSelectionChange(val) {
            this.$store.dispatch('userName/setSelectedList', val);
        },
        deleteUserName(event) {
            if(this.$store.state.userName.selectedList.length == 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('userName/deleteUserName').then(() => {
                        this.$store.dispatch('user/searchUserNameList', this.$store.state.user.form.userId);
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
        showUserName(row) {
            this.$router.push({ name: 'userNameUpdate', params: { userNameId: row.userNameId }});
            this.$store.dispatch('common/setNaviList', {name: 'ユーザ名マスタ詳細', path: 'userName-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "userName");
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('user/updateUser').then(() => {
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
            this.$store.dispatch('user/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('user/showUser', this.$route.params.userId).catch((error) => {
                showErrorMessage(error);
            })
        } else{
            this.$store.dispatch('user/searchUserNameList', this.$store.state.user.form.userId).catch((error) => {
                showErrorMessage(error);
            });
        }
        if(this.$store.getters['common/getLastOperation'].path != "user-update") {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: "ユーザ", value: 'user'});
            this.$store.dispatch('common/setSelectedTable', "user");
        }
    }
}
</script>
