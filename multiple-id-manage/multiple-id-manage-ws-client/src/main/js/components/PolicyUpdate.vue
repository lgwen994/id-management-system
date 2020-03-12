<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMFポリシ詳細</span>
        </div>
        <div>
            <el-form ref="policyForm" :model="policyForm" label-width="100px" id="policyUpdateForm">
                <el-form-item label="ポリシID">
                    <el-input v-model="policyForm.policyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ポリシコード">
                    <el-input v-model="policyForm.policyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="エフェクト">
                    <el-input v-model="policyForm.effect" :disabled="!editable" id="effect"></el-input>
                    <span id="message_effect"></span>
                </el-form-item>
                <el-form-item label="作成日時">
                    <el-date-picker type="datetime" v-model="policyForm.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="作成者">
                    <el-input v-model="policyForm.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="変更日時">
                    <el-date-picker type="datetime" v-model="policyForm.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="変更者">
                    <el-input v-model="policyForm.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="バージョン">
                    <el-input v-model="policyForm.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">キャンセル</el-button>
                        <el-button @click="resetForm">リセット</el-button>
                        <el-button type="primary" @click="update" id="updatePolicy">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                    IDMFルール一覧
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">追加</el-button>
                    <el-button type="primary" @click="deleteRule">削除</el-button>
                </div>
                </el-col>
            </el-row>
           <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="ruleList" stripe border highlight-current-row @row-dblclick="showRule" @selection-change="handleSelectionChange" @sort-change="sort">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="ruleCode" label="ルールコード" width="300"></el-table-column>
                  <el-table-column prop="policyId" label="ポリシID" width="300"></el-table-column>
              </el-table>
               <el-pagination layout="prev, pager, next" :total="ruleSize" page-size="10" @current-change="handleCurrentChange" :current-page="pageRule">
               </el-pagination>
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
            ...mapState('policy', ['policyForm','ruleList','ruleSize','pageRule','editable'])
        },
        methods: {
            changeMode(flg) {
                this.$store.dispatch('policy/changeMode', flg);
            },
            resetForm() {
                this.$store.dispatch('policy/resetForm');
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
                    this.$store.dispatch('policy/updatePolicy').then(() => {
                        this.changeMode(false);
                        this.$alert('更新が完了しました。', '更新完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            },
            regist(event) {
                this.$router.push({
                    name: 'ruleRegist',
                    params: {
                        policyId: this.$store.state.policy.policyForm.policyId,
                        policyCode: this.$store.state.policy.policyForm.policyCode
                    },
                });
                this.$store.dispatch('common/setNaviList', {name: 'IDMFルール登録', path: 'rule-regist'});
                this.$store.dispatch('common/setTable', null);
                this.$store.dispatch('common/setSelectedTable', "rule");
            },
            handleCurrentChange(val) {
                this.$store.dispatch('policy/setPageRule', val);
                this.$store.dispatch('policy/searchRuleList',this.$store.state.policy.policyForm.policyId);
            },
            handleSelectionChange(val) {
                this.$store.dispatch('rule/setSelectedList', val);
            },
            deleteRule(event) {
                if(this.$store.state.rule.selectedList.length === 0) {
                    this.$alert('削除するデータを選択してください。', '確認', {
                        confirmButtonText: 'OK'
                    });
                } else {
                    this.$confirm('削除します。よろしいですか？', '削除確認', {
                        confirmButtonText: 'OK',
                        cancelButtonText: 'キャンセル',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('rule/deleteRule').then(() => {
                            //this.$store.dispatch('policy/searchRuleList', this.$store.state.policy.policyForm.policyId);
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
            showRule(row) {
                this.$router.push({
                    name: 'ruleUpdate',
                    params: {
                        ruleId: row.ruleId,
                        policyId: this.$store.state.policy.policyForm.policyId,
                        policyCode: this.$store.state.policy.policyForm.policyCode
                    },
                });
                this.$store.dispatch('common/setNaviList', {name: 'IDMFルール詳細', path: 'rule-update'});
                this.$store.dispatch('common/setTable', null);
                this.$store.dispatch('common/setSelectedTable', "rule");
            },
        },
        created: function() {
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('policy/showPolicy', this.$route.params.policyId).catch((error) => {
                    showErrorMessage(error);
                })
            }else{
                this.$store.dispatch('policy/searchRuleList', this.$store.state.policy.policyForm.policyId).catch((error) => {
                    showErrorMessage(error);
                });
            }
            if(this.$store.getters['common/getLastOperation'].path != "policy-update") {
                this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
                this.$store.dispatch('common/setTable', {name: "IDMFポリシ", value: 'policy'});
                this.$store.dispatch('common/setSelectedTable', "policy");
            }
        }
    }
</script>
