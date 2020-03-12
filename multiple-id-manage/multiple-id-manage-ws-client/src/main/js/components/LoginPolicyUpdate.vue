<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ログインポリシ詳細</span>
        </div>
        <div>
            <el-form ref="loginPolicyForm" :model="loginPolicyForm" label-width="200px" id="loginPolicyUpdateForm">
                <el-form-item label="ログインポリシID">
                    <el-input v-model="loginPolicyForm.loginPolicyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ログインポリシコード">
                    <el-input v-model="loginPolicyForm.loginPolicyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="loginPolicyForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="パスワード有効期間">
                    <el-input v-model="loginPolicyForm.passwordExpireTerm" :disabled="!editable" id="passwordExpireTerm"></el-input>
                    <span id="message_passwordExpireTerm"></span>
                </el-form-item>
                <el-form-item label="ログイン失敗回数上限値">
                    <el-input v-model="loginPolicyForm.passwordMaxFailure" :disabled="!editable" id="passwordMaxFailure"></el-input>
                    <span id="message_passwordMaxFailure"></span>
                </el-form-item>
                <el-form-item label="ログイン失敗回数解除期間">
                    <el-input v-model="loginPolicyForm.passwordFailureResetTerm" :disabled="!editable" id="passwordFailureResetTerm"></el-input>
                    <span id="message_passwordFailureResetTerm"></span>
                </el-form-item>
                <el-form-item label="アカウントロックアウト期間">
                    <el-input v-model="loginPolicyForm.lockoutTerm" :disabled="!editable" id="lockoutTerm"></el-input>
                    <span id="message_lockoutTerm"></span>
                </el-form-item>
                <el-form-item label="セッション数上限値">
                    <el-input v-model="loginPolicyForm.maxSessionLimit" :disabled="!editable" id="maxSessionLimit"></el-input>
                    <span id="message_maxSessionLimit"></span>
                </el-form-item>
                <el-form-item label="許可ログイン時刻">
                    <el-input v-model="loginPolicyForm.permitTime" :disabled="!editable" id="permitTime"></el-input>
                    <span id="message_permitTime"></span>
                </el-form-item>
                <el-form-item label="許可ドメイン">
                    <el-input v-model="loginPolicyForm.permitDomain" :disabled="!editable" id="permitDomain"></el-input>
                    <span id="message_permitDomain"></span>
                </el-form-item>
                <el-form-item label="作成日時">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="作成者">
                    <el-input v-model="loginPolicyForm.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="変更日時">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="変更者">
                    <el-input v-model="loginPolicyForm.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="論理削除フラグ">
                    <el-input v-model="loginPolicyForm.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="バージョン">
                    <el-input v-model="loginPolicyForm.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">キャンセル</el-button>
                        <el-button @click="resetForm">リセット</el-button>
                        <el-button type="primary" @click="update" id="updateLoginPolicy">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
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
            ...mapState('loginPolicy', ['loginPolicyForm','editable'])
        },
        methods: {
            changeMode(flg) {
                this.$store.dispatch('loginPolicy/changeMode', flg);
            },
            resetForm() {
                this.$store.dispatch('loginPolicy/resetForm');
            },
            cancel() {
                this.resetForm();
                this.changeMode(false);
            },
            update: function(event) {
                this.$confirm('更新します。よろしいですか？', '更新確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('loginPolicy/updateLoginPolicy').then(() => {
                        this.changeMode(false);
                        this.$alert('更新が完了しました。', '更新完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            },
        },
        created: function() {
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('loginPolicy/showLoginPolicy', this.$route.params.loginPolicyId).catch((error) => {
                    showErrorMessage(error);
                })
            }
            if(this.$store.getters['common/getLastOperation'].path != "loginPolicy-update") {
                this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
                this.$store.dispatch('common/setTable', {name: "ログインポリシ", value: 'loginPolicy'});
                this.$store.dispatch('common/setSelectedTable', "loginPolicy");
            }
        }
    }
</script>
