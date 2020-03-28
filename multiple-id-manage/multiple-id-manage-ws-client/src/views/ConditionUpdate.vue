<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMF Condition Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="160px">
                <el-form-item label=" Condition ID">
                    <el-input v-model="form.conditionId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="IDMF Rule">
                    <el-input v-model="rule" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label=" Condition Code">
                    <el-input v-model="form.conditionCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Value Type">
                    <el-input v-model="form.valueType" :disabled="!editable" id="valueType"></el-input>
                    <span id="message_valueType"></span>
                </el-form-item>
                <el-form-item label="Eval Type">
                    <el-input v-model="form.evalType" :disabled="!editable" id="evalType"></el-input>
                    <span id="message_evalType"></span>
                </el-form-item>
                <el-form-item label="Value">
                    <el-input v-model="form.value" :disabled="!editable" id="value"></el-input>
                    <span id="message_value"></span>
                </el-form-item>
                <el-form-item label="Created Time">
                    <el-date-picker type="datetime" v-model="form.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Created User">
                    <el-input v-model="form.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Updated Time">
                    <el-date-picker type="datetime" v-model="form.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Updated User">
                    <el-input v-model="form.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Version No">
                    <el-input v-model="form.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">Cancel</el-button>
                        <el-button @click="resetForm">Reset</el-button>
                        <el-button type="primary" @click="update" id="updateCondition">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="back">Back</el-button>
                        <el-button type="primary" @click="changeMode(true)">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
    import Vue from 'vue'
    import { mapState } from 'vuex'
    import { formatDate, showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('condition', {
                rule(state) {
                    if(state.form.ruleMst.ruleId !== '') {
                        return state.form.ruleMst.ruleCode ;
                    } else {
                        return '';
                    }
                }
            }),
            ...mapState('condition', ['form', 'editable']),
        },
        methods: {
            changeMode: function(flg) {
                this.$store.dispatch('condition/changeMode', flg);
            },
            update: function(event) {
                this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('condition/updateCondition').then(() => {
                        this.changeMode(false);
                        this.$alert('Update is completed.', 'Updated', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // Cancel
                });
            },
            resetForm() {
                this.$store.dispatch('condition/resetForm');
            },
            cancel() {
                this.resetForm();
                this.changeMode(false);
            },
            back() {
                this.$router.push({name: 'ruleUpdate'});
                this.$store.dispatch('common/deleteNavi', {name: 'IDMF Condition Details', path: 'condition-update'});
                this.$store.dispatch('common/setSelectedTable', 'rule');
            }
        },
        created: function() {
            this.$store.dispatch('condition/showCondition', this.$route.params.conditionId).catch(error => {
                showErrorMessage(error);
            })
        }
    }
</script>
