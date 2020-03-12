<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ログインポリシ登録</span>
        </div>
        <div>
            <el-form ref="loginPolicyForm" :model="loginPolicyForm" label-width="200px" id="loginPolicyRegistForm">
                <el-form-item label="ログインポリシコード">
                    <el-input v-model="loginPolicyForm.loginPolicyCode" id="loginPolicyCode"></el-input>
                    <span id="message_loginPolicyCode"></span>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="loginPolicyForm.companyCode" id="companyCode"></el-input>
                    <span id="message_companyCode"></span>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item label="パスワード有効期間">
                    <el-input v-model="loginPolicyForm.passwordExpireTerm" id="passwordExpireTerm"></el-input>
                    <span id="message_passwordExpireTerm"></span>
                </el-form-item>
                <el-form-item label="ログイン失敗回数上限値">
                    <el-input v-model="loginPolicyForm.passwordMaxFailure" id="passwordMaxFailure"></el-input>
                    <span id="message_passwordMaxFailure"></span>
                </el-form-item>
                <el-form-item label="ログイン失敗回数解除期間">
                    <el-input v-model="loginPolicyForm.passwordFailureResetTerm" id="passwordFailureResetTerm"></el-input>
                    <span id="message_passwordFailureResetTerm"></span>
                </el-form-item>
                <el-form-item label="アカウントロックアウト期間">
                    <el-input v-model="loginPolicyForm.lockoutTerm" id="lockoutTerm"></el-input>
                    <span id="message_lockoutTerm"></span>
                </el-form-item>
                <el-form-item label="セッション数上限値">
                    <el-input v-model="loginPolicyForm.maxSessionLimit" id="maxSessionLimit"></el-input>
                    <span id="message_maxSessionLimit"></span>
                </el-form-item>
                <el-form-item label="許可ログイン時刻">
                    <el-input v-model="loginPolicyForm.permitTime" id="permitTime"></el-input>
                    <span id="message_permitTime"></span>
                </el-form-item>
                <el-form-item label="許可ドメイン">
                    <el-input v-model="loginPolicyForm.permitDomain" id="permitDomain"></el-input>
                    <span id="message_permitDomain"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registLoginPolicy">登録</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
    import Vue from 'vue'
    import { mapState } from 'vuex'
    import { showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('loginPolicy', ['loginPolicyForm'])
        },
        methods: {
            regist: function(event) {
                this.$confirm('登録します。よろしいですか？', '登録確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('loginPolicy/registLoginPolicy').then(() => {
                        this.$router.push({ name: 'loginPolicyUpdate', params: { loginPolicyId: this.$store.state.loginPolicy.loginPolicyForm.loginPolicyId }});
                        this.$store.dispatch('common/setNaviList', {name: "ログインポリシ詳細", path: 'loginPolicy-update'});
                        this.$alert('登録が完了しました。', '登録完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            },
            clear() {
                this.$store.dispatch('loginPolicy/clearForm')
            },
        },
        created: function () {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'ログインポリシ登録', path: 'loginPolicy-regist'});
            this.$store.dispatch('common/setTable', {name: "ログインポリシ", value: 'loginPolicy'});
            this.$store.dispatch('common/setSelectedTable', 'loginPolicy');
            this.$store.dispatch('loginPolicy/clearForm');
            this.$store.dispatch('loginPolicy/setActiveStartTime');
        }
    }
</script>
