<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>組織名マスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px">
                <el-form-item label="組織名ID">
                    <el-input v-model="form.orgNameId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="組織マスタ">
                    <el-input v-model="org" :disabled="true" id="org"></el-input>
                </el-form-item>
                <el-form-item label="ロケール">
                  <el-select v-model="form.locale" :disabled="!editable" id="locale">
                      <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="組織名">
                    <el-input v-model="form.orgName" :disabled="!editable" id="orgName"></el-input>
                    <span id="message_orgName"></span>
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
        ...mapState('orgName', {
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName + '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('orgName', ['form', 'editable']),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode: function(flg) {
            this.$store.dispatch('orgName/changeMode', flg);
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('orgName/updateOrgName').then(() => {
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
            this.$store.dispatch('orgName/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        back() {
            this.$router.push({name: 'orgUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: '組織名マスタ詳細', path: 'orgName-update'});
            this.$store.dispatch('common/setTable', {name: '組織マスタ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        this.$store.dispatch('orgName/showOrgName', this.$route.params.orgNameId).catch(error => {
            showErrorMessage(error);
        });
    }
}
</script>
