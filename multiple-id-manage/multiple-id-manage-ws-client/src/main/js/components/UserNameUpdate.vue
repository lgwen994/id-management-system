<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ユーザ名マスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="ユーザ名ID">
                    <el-input v-model="form.userNameId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ユーザマスタ">
                    <el-input v-model="user" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ロケール">
                  <el-select v-model="form.locale" :disabled="!editable"  id="locale">
                      <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="ユーザ名">
                    <el-input v-model="form.userName" :disabled="!editable"  id="userName"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="作成日時">
                    <el-date-picker type="datetime" v-model="form.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="作成者">
                    <el-input v-model="form.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="変更日時">
                    <el-date-picker type="datetime" v-model="form.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="変更者">
                    <el-input v-model="form.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="論理削除フラグ">
                    <el-input v-model="form.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="バージョン">
                    <el-input v-model="form.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">キャンセル</el-button>
                        <el-button @click="resetForm">リセット</el-button>
                        <el-button type="primary" @click="update">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="back">戻る</el-button>
                        <el-button type="primary" @click="changeMode(true)">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'

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
            },
            editable: state => state.editable
        }),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode: function(flg) {
            this.$store.dispatch('userName/changeMode', flg);
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userName/updateUserName').then(() => {
                    this.changeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        resetForm() {
            this.$store.dispatch('userName/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        back() {
            this.$router.push({name: 'userUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: 'ユーザ名マスタ詳細', path: 'userName-update'});
            this.$store.dispatch('common/setTable', {name: "ユーザマスタ", value: 'user'});
            this.$store.dispatch('common/setSelectedTable', "user");
        }
    },
    created: function() {
        this.$store.dispatch('userName/showUserName', this.$route.params.userNameId).catch(error => {
            showErrorMessage(error);
        })
    }
}
</script>
