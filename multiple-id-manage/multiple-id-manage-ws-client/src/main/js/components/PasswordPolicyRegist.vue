<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>パスワードポリシ登録</span>
        </div>
        <div>
            <el-form ref="passwordPolicyForm" :model="passwordPolicyForm" label-width="180px" id="passwordPolicyRegistForm">
                <el-form-item label="パスワードポリシコード">
                    <el-input v-model="passwordPolicyForm.passwordPolicyCode" id="passwordPolicyCode"></el-input>
                    <span id="message_passwordPolicyCode"></span>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="passwordPolicyForm.companyCode" id="companyCode"></el-input>
                    <span id="message_companyCode"></span>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item label="最低パスワード長">
                    <el-input v-model="passwordPolicyForm.passwordMinLength" id="passwordMinLength"></el-input>
                    <span id="message_passwordMinLength"></span>
                </el-form-item>
                <el-form-item label="パスワード文字種">
                    <el-input v-model="passwordPolicyForm.passwordLetterType" id="passwordLetterType"></el-input>
                    <span id="message_passwordLetterType"></span>
                </el-form-item>
                <el-form-item label="パスワード最低文字種">
                    <el-input v-model="passwordPolicyForm.passwordMinLetterType" id="passwordMinLetterType"></el-input>
                    <span id="message_passwordMinLetterType"></span>
                </el-form-item>
                <el-form-item label="パスワード履歴数">
                    <el-input v-model="passwordPolicyForm.passwordInHistory" id="passwordInHistory"></el-input>
                    <span id="message_passwordInHistory"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registPasswordPolicy">登録</el-button>
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
            ...mapState('passwordPolicy', ['passwordPolicyForm'])
        },
        methods: {
            regist: function(event) {
                this.$confirm('登録します。よろしいですか？', '登録確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('passwordPolicy/registPasswordPolicy').then(() => {
                        this.$router.push({ name: 'passwordPolicyUpdate', params: { passwordPolicyId: this.$store.state.passwordPolicy.passwordPolicyForm.passwordPolicyId }});
                        this.$store.dispatch('common/setNaviList', {name: "パスワードポリシ詳細", path: 'passwordPolicy-update'});
                        this.$alert('登録が完了しました。', '登録完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                        // キャンセルの場合
                });
            },
            clear() {
                this.$store.dispatch('passwordPolicy/clearForm')
            },
        },
        created: function () {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'パスワードポリシ登録', path: 'passwordPolicy-regist'});
            this.$store.dispatch('common/setTable', {name: "パスワードポリシ", value: 'passwordPolicy'});
            this.$store.dispatch('common/setSelectedTable', 'passwordPolicy');
            this.$store.dispatch('passwordPolicy/clearForm');
            this.$store.dispatch('passwordPolicy/setActiveStartTime');
        }
    }
</script>
