<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMFPolicy Details</span>
        </div>
        <div>
            <el-form ref="policyForm" :model="policyForm" label-width="100px" id="policyUpdateForm">
                <el-form-item label="Policy ID">
                    <el-input v-model="policyForm.policyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Policy Code">
                    <el-input v-model="policyForm.policyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Effect">
                    <el-input v-model="policyForm.effect" :disabled="!editable" id="effect"></el-input>
                    <span id="message_effect"></span>
                </el-form-item>
                <el-form-item label="Created Time">
                    <el-date-picker type="datetime" v-model="policyForm.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Created User">
                    <el-input v-model="policyForm.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Updated Time">
                    <el-date-picker type="datetime" v-model="policyForm.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Updated User">
                    <el-input v-model="policyForm.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Version No">
                    <el-input v-model="policyForm.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">Cancel</el-button>
                        <el-button @click="resetForm">Reset</el-button>
                        <el-button type="primary" @click="update" id="updatePolicy">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                    IDMF Rule List
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">Create</el-button>
                    <el-button type="primary" @click="deleteRule">Delete</el-button>
                </div>
                </el-col>
            </el-row>
           <el-row>
              Double-click on data to see details
              <el-table :data="ruleList" stripe border highlight-current-row @row-dblclick="showRule" @selection-change="handleSelectionChange" @sort-change="sort">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="ruleCode" label=" RuleCode" width="300"></el-table-column>
                  <el-table-column prop="policyId" label="Policy ID" width="300"></el-table-column>
              </el-table>
               <el-pagination layout="prev, pager, next" :total="ruleSize" page-size="10" @current-change="handleCurrentChange" :current-page="pageRule">
               </el-pagination>
            </el-row>
        </div>
    </el-card>
</template>

<script>
    import { mapState } from 'vuex'
    import { showErrorMessage } from '../common';
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
            update: function() {
                this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('policy/updatePolicy').then(() => {
                        this.changeMode(false);
                        this.$alert('Update is completed.', 'Updated', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // Cancel
                });
            },
            regist() {
                this.$router.push({
                    name: 'ruleRegist',
                    params: {
                        policyId: this.$store.state.policy.policyForm.policyId,
                        policyCode: this.$store.state.policy.policyForm.policyCode
                    },
                });
                this.$store.dispatch('common/setNaviList', {name: 'IDMF Rule Create', path: 'rule-regist'});
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
            deleteRule() {
                if(this.$store.state.rule.selectedList.length === 0) {
                    this.$alert('Please select the data to be deleted.', 'Confirm', {
                        ConfirmButtonText: 'OK'
                    });
                } else {
                    this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                        ConfirmButtonText: 'OK',
                        cancelButtonText: 'Cancel',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('rule/deleteRule').then(() => {
                            //this.$store.dispatch('policy/searchRuleList', this.$store.state.policy.policyForm.policyId);
                            this.$alert('Delete completed.', 'Deleted', {
                                ConfirmButtonText: 'OK'
                            });
                        }).catch(error => {
                            showErrorMessage(error);
                        });
                    }).catch(() => {
                        // Cancel
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
                this.$store.dispatch('common/setNaviList', {name: 'IDMF RuleDetails', path: 'rule-update'});
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
                this.$store.dispatch('common/setTable', {name: "IDMFPolicy ", value: 'policy'});
                this.$store.dispatch('common/setSelectedTable', "policy");
            }
        }
    }
</script>
