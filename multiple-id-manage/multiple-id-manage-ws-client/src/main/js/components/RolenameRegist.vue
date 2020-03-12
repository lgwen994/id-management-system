<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ロール名マスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="ロールマスタ">
                    <el-input v-model="role" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ロケール">
                    <el-select v-model="form.locale" placeholder="">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="ロール名">
                    <el-input v-model="form.roleName"　id="roleName"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="back">戻る</el-button>
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist">登録</el-button>
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
        ...mapState('rolename', {
            role(state) {
                if(state.form.roleMst.roleId !== '') {
                    return state.form.roleMst.roleCode + ':' + state.form.roleMst.roleName + '(' + state.form.roleMst.activeStartTime + '-' + state.form.roleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('rolename', ['form']),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('rolename/registRolename').then(() => {
                    this.$router.push({ name: 'roleUpdate', params: { roleId: this.$store.state.rolename.form.roleMst.roleId}});
                    this.$store.dispatch('common/deleteNavi', {name: 'ロール名マスタ登録', path: 'rolename-regist'});
                    this.$store.dispatch('common/setTable', {name: 'ロールマスタ', value: 'role'});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        clear() {
            this.$store.dispatch('rolename/clearForm');
        },
        back() {
            this.$router.push({
                name: 'roleUpdate',
                params: {
                    roleId: this.$store.state.rolename.form.roleMst.roleId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: 'ロール名マスタ登録', path: 'rolename-regist'});
            this.$store.dispatch('common/setTable', {name: 'ロールマスタ', value: 'role'});
            this.$store.dispatch('common/setSelectedTable', 'role');
        }
    },
    created: function() {
        this.$store.dispatch('rolename/clearForm');
        this.$store.dispatch('rolename/setActiveTime');
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('rolename/setRole');
        }
    }
}
</script>
