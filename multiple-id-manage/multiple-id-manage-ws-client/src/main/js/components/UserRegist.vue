<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ユーザマスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="120px" id="userRegistForm">
                <el-form-item label="ユーザコード">
                    <el-input v-model="form.userCode" id="userCode"></el-input>
                    <span id="message_userCode"></span>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item label="ユーザ名">
                    <el-input v-model="form.userName" id="userName"></el-input>
                    <span id="message_userName"></span>
                </el-form-item>
                <el-form-item label="メールアドレス">
                    <el-input v-model="form.mail"  id="mail"></el-input>
                    <span id="message_mail"></span>
                </el-form-item>
                <el-form-item label="電話番号">
                    <el-input v-model="form.tel" id="tel"></el-input>
                    <span id="message_tel"></span>
                </el-form-item>
                <el-form-item label="FAX番号">
                    <el-input v-model="form.fax"  id="fax"></el-input>
                    <span id="message_fax"></span>
                </el-form-item>
                <el-form-item label="ロケール">
                    <el-select v-model="form.locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registUser">登録</el-button>
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
            ...mapState('user', ['form']),
            ...mapState('common', ['locales'])
        },
        methods: {
            regist: function(event) {
                    this.$confirm('登録します。よろしいですか？', '登録確認', {
                        confirmButtonText: 'OK',
                        cancelButtonText: 'キャンセル',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('user/registUser').then(() => {
                            this.$router.push({ name: 'userUpdate', params: { userId: this.$store.state.user.form.userId }});
                            this.$store.dispatch('common/setNaviList', {name: "ユーザマスタ詳細", path: 'user-update'});
                            this.$alert('登録が完了しました。', '登録完了', {
                                confirmButtonText: 'OK'
                            });
                        }).catch(error => showErrorMessage(error));
                    }).catch(() => {
                        // キャンセルの場合
                    });
            },
            clear() {
                this.$store.dispatch('user/clearForm');
            },
        },
        created: function() {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'ユーザマスタ登録', path: 'user-regist'});
            this.$store.dispatch('common/setTable', {name: "ユーザマスタ", value: 'user'})
            this.$store.dispatch('common/setSelectedTable', "user");
            this.$store.dispatch('user/clearForm');
            this.$store.dispatch('user/setActiveStartTime');
        }
    }
</script>
