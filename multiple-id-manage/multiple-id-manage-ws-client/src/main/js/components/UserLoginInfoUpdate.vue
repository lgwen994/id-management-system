<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ユーザ_ログイン情報詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="230px" id="userLoginInfoUpdateForm">
                <el-form-item label="ユーザ_ログイン情報ID">
                    <el-input v-model="form.userLoginInfoId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ユーザ_ログイン情報コード">
                    <el-input v-model="form.userLoginInfoCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ユーザID">
                    <el-input v-model="form.userId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ログインID">
                    <el-input v-model="form.loginId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="form.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
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
                        <el-button type="primary" @click="update" id="updateUserLoginInfo">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
        <div>
            <span>パスワード詳細</span>
        </div>
        <div>
            <el-form ref="passwordForm" :model="passwordForm" label-width="230px" id="passwordUpdateForm">
                <el-form-item label="ログインID">
                    <el-input v-model="passwordForm.loginId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="passwordForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="パスワード">
                    <el-input v-model="passwordForm.password" :disabled="!passwordEditable" id="password"></el-input>
                </el-form-item>
                <el-form-item label="パスワード変更強制フラグ">
                    <el-input v-model="passwordForm.passwordMustChangeFlg" :disabled="!passwordEditable" id="passwordMustChangeFlg"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="passwordEditable" style="text-align:right;">
                        <el-button @click="cancelPassword">キャンセル</el-button>
                        <el-button @click="resetPassword">リセット</el-button>
                        <el-button type="primary" @click="updatePassword" id="updatePassword">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="passwordChangeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>

        <div>
            <span>ログイン制御情報詳細</span>
        </div>
        <div>
            <el-form ref="loginControlInfoForm" :model="loginControlInfoForm" label-width="230px" id="loginControlInfoUpdateForm">
                <el-form-item label="ログインID">
                    <el-input v-model="loginControlInfoForm.loginId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="loginControlInfoForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="アカウント無効フラグ">
                    <el-input v-model="loginControlInfoForm.accountInactiveFlg" :disabled="!loginControlInfoEditable" id="accountInactiveFlg"></el-input>
                </el-form-item>
                <el-form-item label="アカウント有効開始日時">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.accountActiveStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="accountActiveStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="アカウント有効終了日時">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.accountActiveEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="accountActiveEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="ログイン失敗回数">
                    <el-input v-model="loginControlInfoForm.passwordFailureCount" :disabled="!loginControlInfoEditable" id="passwordFailureCount"></el-input>
                </el-form-item>
                <el-form-item label="ログイン失敗回数解除時刻">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.passwordFailureResetTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="passwordFailureResetTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="アカウントロックアウト終了日時">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.lockoutEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="lockoutEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <div v-if="loginControlInfoEditable" style="text-align:right;">
                        <el-button @click="cancelLoginControlInfo">キャンセル</el-button>
                        <el-button @click="resetLoginControlInfo">リセット</el-button>
                        <el-button type="primary" @click="updateLoginControlInfo" id="updateLoginControlInfo">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="loginControlInfoChangeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('userLoginInfo', ['form','passwordForm','loginControlInfoForm','passwordEditable','loginControlInfoEditable','editable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('userLoginInfo/changeMode', flg);
        },
        resetForm() {
            this.$store.dispatch('userLoginInfo/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/updateUserLoginInfo').then(() => {
                    this.changeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        passwordChangeMode(flg) {
            this.$store.dispatch('userLoginInfo/passwordChangeMode', flg);
        },
        resetPassword() {
            this.$store.dispatch('userLoginInfo/resetPassword');
        },
        cancelPassword() {
            this.resetPassword();
            this.passwordChangeMode(false);
        },
        updatePassword(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/updatePassword').then(() => {
                    this.passwordChangeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        loginControlInfoChangeMode(flg) {
            this.$store.dispatch('userLoginInfo/loginControlInfoChangeMode', flg);
        },
        resetLoginControlInfo() {
            this.$store.dispatch('userLoginInfo/resetLoginControlInfo');
        },
        cancelLoginControlInfo() {
            this.resetLoginControlInfo();
            this.loginControlInfoChangeMode(false);
        },
        updateLoginControlInfo(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/updateLoginControlInfo').then(() => {
                    this.loginControlInfoChangeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('userLoginInfo/showUserLoginInfo', this.$route.params.userLoginInfoId).catch((error) => {
                showErrorMessage(error);
            })
        }
        if(this.$store.getters['common/getLastOperation'].path !== 'userLoginInfo-update') {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: 'ユーザ_ログイン情報', value: 'userLoginInfo'});
            this.$store.dispatch('common/setSelectedTable', 'userLoginInfo');
        }
    }
}
</script>
