<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>パスワードポリシ詳細</span>
        </div>
        <div>
            <el-form ref="passwordPolicyForm" :model="passwordPolicyForm" label-width="180px" id="passwordPolicyUpdateForm">
                <el-form-item label="パスワードポリシID">
                    <el-input v-model="passwordPolicyForm.passwordPolicyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="パスワードポリシコード">
                    <el-input v-model="passwordPolicyForm.passwordPolicyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="passwordPolicyForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="最低パスワード長">
                    <el-input v-model="passwordPolicyForm.passwordMinLength" :disabled="!editable" id="passwordMinLength"></el-input>
                    <span id="message_passwordMinLength"></span>
                </el-form-item>
                <el-form-item label="パスワード文字種">
                    <el-input v-model="passwordPolicyForm.passwordLetterType" :disabled="!editable" id="passwordLetterType"></el-input>
                    <span id="message_passwordLetterType"></span>
                </el-form-item>
                <el-form-item label="パスワード最低文字種">
                    <el-input v-model="passwordPolicyForm.passwordMinLetterType" :disabled="!editable" id="passwordMinLetterType"></el-input>
                    <span id="message_passwordMinLetterType"></span>
                </el-form-item>
                <el-form-item label="パスワード履歴数">
                    <el-input v-model="passwordPolicyForm.passwordInHistory" :disabled="!editable" id="passwordInHistory"></el-input>
                    <span id="message_passwordInHistory"></span>
                </el-form-item>
                <el-form-item label="作成日時">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="作成者">
                    <el-input v-model="passwordPolicyForm.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="変更日時">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="変更者">
                    <el-input v-model="passwordPolicyForm.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="論理削除フラグ">
                    <el-input v-model="passwordPolicyForm.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="バージョン">
                    <el-input v-model="passwordPolicyForm.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">キャンセル</el-button>
                        <el-button @click="resetForm">リセット</el-button>
                        <el-button type="primary" @click="update" id="updatePasswordPolicy">更新</el-button>
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
            ...mapState('passwordPolicy', ['passwordPolicyForm','editable'])
        },
        methods: {
            changeMode(flg) {
                this.$store.dispatch('passwordPolicy/changeMode', flg);
            },
            resetForm() {
                this.$store.dispatch('passwordPolicy/resetForm');
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
                    this.$store.dispatch('passwordPolicy/updatePasswordPolicy').then(() => {
                        this.changeMode(false);
                        this.$alert('更新が完了しました。', '更新完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // キャンセルの場合
                });
            },
        },
        created: function() {
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('passwordPolicy/showPasswordPolicy', this.$route.params.passwordPolicyId).catch((error) => {
                    showErrorMessage(error);
                })
            }
            if(this.$store.getters['common/getLastOperation'].path != "passwordPolicy-update") {
                this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
                this.$store.dispatch('common/setTable', {name: "パスワードポリシ", value: 'passwordPolicy'});
                this.$store.dispatch('common/setSelectedTable', "passwordPolicy");
            }
        }
    }
</script>
