<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>会社マスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="companyUpdateForm">
                <el-form-item label="会社ID">
                    <el-input v-model="form.companyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-input v-model="form.companyCode" :disabled="true" id="companyCode"></el-input>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="会社名">
                    <el-input v-model="form.companyName" :disabled="!editable" id="companyName"></el-input>
                    <span id="message_companyName"></span>
                </el-form-item>
                <el-form-item label="会社種類">
                    <el-input v-model="form.companyType" :disabled="!editable" id="companyType"></el-input>
                    <span id="message_companyType"></span>
                </el-form-item>
                <el-form-item label="会社説明">
                    <el-input v-model="form.companyComment" :disabled="!editable" id="companyComment"></el-input>
                    <span id="message_companyComment"></span>
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
                        <el-button type="primary" @click="update" id="updateCompany">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                会社名一覧
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">追加</el-button>
                    <el-button type="primary" @click="deleteCompanyName">削除</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="companyNameList" stripe border highlight-current-row @row-dblclick="showCompanyName" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="ロケール" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="companyName" label="会社名" width="100"></el-table-column>
                  <el-table-column prop="activeStartTime" label="有効開始日時" width="150" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="有効終了日時" width="150" :formatter="formatActiveEndTime"></el-table-column>
              </el-table>
            </el-row>
        </div>
    </el-card>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('company', ['form', 'companyNameList','editable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('company/changeMode', flg);
        },
        getLocale(row, column) {
            return this.$store.getters['common/getLocale'](row.locale);
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        regist(event) {
            this.$router.push({
                name: 'companyNameRegist',
                params: {
                    companyId: this.$store.state.company.form.companyId,
                    companyCode: this.$store.state.company.form.companyCode,
                    activeStartTime: this.$store.state.company.form.activeStartTime,
                    activeEndTime: this.$store.state.company.form.activeEndTime
                }
            });
            this.$store.dispatch('common/setNaviList', {name: '会社名マスタ登録', path: 'companyName-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "companyName");
        },
        handleSelectionChange(val) {
            this.$store.dispatch('companyName/setSelectedList', val);
        },
        deleteCompanyName(event) {
            if(this.$store.state.companyName.selectedList.length == 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('companyName/deleteCompanyName').then(() => {
                        this.$store.dispatch('company/searchCompanyNameList', this.$store.state.company.form.companyId);
                        this.$alert('削除が完了しました。', '削除完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // キャンセルの場合
                });
            }
        },
        showCompanyName(row) {
            //var companyNameId = row.companyNameId;
            this.$router.push({ name: 'companyNameUpdate', params: { companyNameId: row.companyNameId }});
            this.$store.dispatch('common/setNaviList', {name: '会社名マスタ詳細', path: 'companyName-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "companyName");
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('company/updateCompany').then(() => {
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
            this.$store.dispatch('company/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('company/showCompany', this.$route.params.companyId).catch((error) => {
                showErrorMessage(error);
            })
        } else{
            this.$store.dispatch('company/searchCompanyNameList', this.$store.state.company.form.companyId).catch((error) => {
                showErrorMessage(error);
            });
        }
        if(this.$store.getters['common/getLastOperation'].path != "company-update") {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: "会社", value: 'company'});
            this.$store.dispatch('common/setSelectedTable', "company");
        }
    }
}
</script>
