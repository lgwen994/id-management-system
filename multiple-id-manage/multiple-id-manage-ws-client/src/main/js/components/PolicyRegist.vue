<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMFポリシ登録</span>
        </div>
        <div>
            <el-form ref="policyForm" :model="policyForm" label-width="100px" id="policyRegistForm">
                <el-form-item label="ポリシコード">
                    <el-input v-model="policyForm.policyCode" id="policyCode"></el-input>
                    <span id="message_policyCode"></span>
                </el-form-item>
                <el-form-item label="エフェクト">
                    <el-input v-model="policyForm.effect" id="effect"></el-input>
                    <span id="message_effect"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registPolicy">登録</el-button>
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
            ...mapState('policy', ['policyForm'])
        },
        methods: {
            regist: function(event) {
                this.$confirm('登録します。よろしいですか？', '登録確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('policy/registPolicy').then(() => {
                        this.$router.push({ name: 'policyUpdate', params: { policyId: this.$store.state.policy.policyForm.policyId }});
                        this.$store.dispatch('common/setNaviList', {name: "IDMFポリシ詳細", path: 'policy-update'});
                        this.$alert('登録が完了しました。', '登録完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            },
            clear() {
                this.$store.dispatch('policy/clearForm')
            },
        },
        created: function () {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'IDMFポリシ登録', path: 'policy-regist'});
            this.$store.dispatch('common/setTable', {name: "IDMFポリシ", value: 'policy'});
            this.$store.dispatch('common/setSelectedTable', 'policy');
            this.$store.dispatch('policy/clearForm')
        }
    }
</script>
