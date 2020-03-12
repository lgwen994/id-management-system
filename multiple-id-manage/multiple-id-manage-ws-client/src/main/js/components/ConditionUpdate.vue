<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMFコンディション詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="160px">
                <el-form-item label="コンディションID">
                    <el-input v-model="form.conditionId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="IDMFルール">
                    <el-input v-model="rule" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="コンディションコード">
                    <el-input v-model="form.conditionCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="バリュータイプ">
                    <el-input v-model="form.valueType" :disabled="!editable" id="valueType"></el-input>
                    <span id="message_valueType"></span>
                </el-form-item>
                <el-form-item label="判定タイプ">
                    <el-input v-model="form.evalType" :disabled="!editable" id="evalType"></el-input>
                    <span id="message_evalType"></span>
                </el-form-item>
                <el-form-item label="バリュー">
                    <el-input v-model="form.value" :disabled="!editable" id="value"></el-input>
                    <span id="message_value"></span>
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
                <el-form-item label="バージョン">
                    <el-input v-model="form.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">キャンセル</el-button>
                        <el-button @click="resetForm">リセット</el-button>
                        <el-button type="primary" @click="update" id="updateCondition">更新</el-button>
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
                this.$confirm('更新します。よろしいですか？', '更新確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('condition/updateCondition').then(() => {
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
                this.$store.dispatch('condition/resetForm');
            },
            cancel() {
                this.resetForm();
                this.changeMode(false);
            },
            back() {
                this.$router.push({name: 'ruleUpdate'});
                this.$store.dispatch('common/deleteNavi', {name: 'IDMFコンディション詳細', path: 'condition-update'});
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
