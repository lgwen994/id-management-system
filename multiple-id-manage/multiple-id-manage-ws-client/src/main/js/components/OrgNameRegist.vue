<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>組織名マスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px">
                <el-form-item label="組織マスタ">
                    <el-input v-model="org" :disabled="true" id="org"></el-input>
                </el-form-item>
                <el-form-item label="ロケール">
                    <el-select v-model="form.locale" placeholder="" id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="組織名">
                    <el-input v-model="form.orgName" id="orgName"></el-input>
                    <span id="message_orgName"></span>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
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
        ...mapState('orgName', {
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName + '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('orgName', ['form']),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('orgName/registOrgName').then(() => {
                    this.$router.push({ name: 'orgUpdate', params: { orgId: this.$store.state.orgName.form.orgId}});
                    this.$store.dispatch('common/deleteNavi', {name: '組織名マスタ登録', path: 'orgName-regist'});
                    this.$store.dispatch('common/setTable', {name: '組織マスタ', value: 'org'});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        clear() {
            this.$store.dispatch('orgName/clearForm');
        },
        back() {
            this.$router.push({name: 'orgUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: '組織名マスタ登録', path: 'orgName-regist'});
            this.$store.dispatch('common/setTable', {name: '組織マスタ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        this.$store.dispatch('orgName/clearForm');
        this.$store.dispatch('orgName/setActiveTime');
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('orgName/setOrg');
        }
    }
}
</script>
