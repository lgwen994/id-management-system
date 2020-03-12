<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ロールマスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="roleRegistForm">
                <el-form-item label="ロールコード">
                    <el-input v-model="form.roleCode" id="roleCode"></el-input>
                    <span id="message_roleCode"></span>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item label="ロール名">
                    <el-input v-model="form.roleName" id="roleName"></el-input>
                    <span id="message_roleName"></span>
                </el-form-item>
                <el-form-item label="ロール種類">
                    <el-input v-model="form.roleType" id="roleType"></el-input>
                    <span id="message_roleType"></span>
                </el-form-item>
                <el-form-item label="ロール説明">
                    <el-input v-model="form.roleComment" id="roleComment"></el-input>
                    <span id="message_roleComment"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registRole">登録</el-button>
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
        ...mapState('role', ['form'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('role/registRole').then(() => {
                    this.$router.push({ name: 'roleUpdate', params: { roleId: this.$store.state.role.form.roleId }});
                    this.$store.dispatch('common/setNaviList', {name: 'ロールマスタ詳細', path: 'role-update'});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        clear() {
            this.$store.dispatch('role/clearForm');
        },
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'ロールマスタ登録', path: 'role-regist'});
        this.$store.dispatch('common/setTable', {name: 'ロールマスタ', value: 'role'})
        this.$store.dispatch('common/setSelectedTable', 'role');
        this.$store.dispatch('role/clearForm');
        this.$store.dispatch('role/setActiveStartTime');
    }
}
</script>
