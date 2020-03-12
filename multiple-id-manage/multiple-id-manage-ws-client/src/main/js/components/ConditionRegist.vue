<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMFコンディション登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="160px">
                <el-form-item label="IDMFルール">
                    <el-input v-model="rule" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="コンディションコード">
                    <el-input v-model="form.conditionCode" id="conditionCode" ></el-input>
                    <span id="message_conditionCode"></span>
                </el-form-item>
                <el-form-item label="バリュータイプ">
                    <el-input v-model="form.valueType" id="valueType"></el-input>
                    <span id="message_valueType"></span>
                </el-form-item>
                <el-form-item label="判定タイプ">
                    <el-input v-model="form.evalType" id="evalType"></el-input>
                    <span id="message_evalType"></span>
                </el-form-item>
                <el-form-item label="バリュー">
                    <el-input v-model="form.value" id="value"></el-input>
                    <span id="message_value"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="back">戻る</el-button>
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registCondition">登録</el-button>
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
            ...mapState('condition', {
                form: state => state.form,
                rule(state) {
                    if(state.form.ruleMst.ruleId != "") {
                        return state.form.ruleMst.ruleCode;
                    } else {
                        return "";
                    }
                }
            }),
        },
        methods: {
            regist: function(event) {
                this.$confirm('登録します。よろしいですか？', '登録確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('condition/registCondition').then(() => {
                        var conditionId = this.$store.state.condition.form.conditionId;
                        this.$router.push({ name: 'ruleUpdate', params: { ruleId: this.$store.state.condition.form.ruleMst.ruleId}});
                        this.$store.dispatch('common/deleteNavi', {name: 'IDMFコンディション登録', path: 'condition-regist'});
                        this.$store.dispatch('common/setTable', {name: "IDMFルール", value: 'rule'});
                        this.$alert('登録が完了しました。', '登録完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            },
            clear() {
                this.$store.dispatch('condition/clearForm');
            },
            back() {
                this.$router.push({
                    name: 'ruleUpdate',
                    params: {
                        ruleId: this.$store.state.condition.form.ruleMst.ruleId,
                    }
                });
                this.$store.dispatch('common/deleteNavi', {name: 'IDMFコンディション登録', path: 'condition-regist'});
                this.$store.dispatch('common/setTable', {name: "IDMFルール", value: 'rule'});
                this.$store.dispatch('common/setSelectedTable', "rule");
            }
        },
        created: function() {
            this.$store.dispatch('condition/clearForm');
            if(Object.keys(this.$route.params).length != 0) {
                this.$store.dispatch('condition/setRule');
            }
        }
    }
</script>
