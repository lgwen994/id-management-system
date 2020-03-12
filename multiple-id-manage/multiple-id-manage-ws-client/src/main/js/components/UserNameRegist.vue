<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ユーザ名マスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="ユーザマスタ">
                    <el-input v-model="user" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ロケール">
                    <el-select v-model="form.locale" placeholder=""  id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="ユーザ名">
                    <el-input v-model="form.userName" id="userName"></el-input>
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
            <!--
            <el-button>戻る</el-button>
            -->
        </div>
    </el-card>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('userName', {
            form: state => state.form,
            user(state) {
                if(state.form.userMst.userId != "") {
                    return state.form.userMst.userCode + "(" + state.form.userMst.activeStartTime + "-" + state.form.userMst.activeEndTime + ")";
                } else {
                    return "";
                }
            }
        }),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userName/registUserName').then(() => {
                    var userNameId = this.$store.state.userName.form.userNameId;
                    this.$router.push({ name: 'userUpdate', params: { userId: this.$store.state.userName.form.userMst.userId}});
                    this.$store.dispatch('common/deleteNavi', {name: 'ユーザ名マスタ登録', path: 'userName-regist'});
                    this.$store.dispatch('common/setTable', {name: "ユーザマスタ", value: 'user'});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        clear() {
            this.$store.dispatch('userName/clearForm');
        },
        back() {
            this.$router.push({
                name: 'userUpdate',
                params: {
                    userId: this.$store.state.userName.form.userMst.userId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: 'ユーザ名マスタ登録', path: 'userName-regist'});
            this.$store.dispatch('common/setTable', {name: "ユーザマスタ", value: 'user'});
            this.$store.dispatch('common/setSelectedTable', "user");
        }
    },
    created: function() {
        this.$store.dispatch('userName/clearForm');
        this.$store.dispatch('userName/setActiveTime');
        if(Object.keys(this.$route.params).length != 0) {
            this.$store.dispatch('userName/setUser');
        }
    }
}
</script>
