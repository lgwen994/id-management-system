<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>役職名マスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="役職マスタ">
                    <el-input v-model="title" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="ロケール">
                    <el-select v-model="form.locale" placeholder="" id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="役職名">
                    <el-input v-model="form.titleName" id="titleName"></el-input>
                    <span id="message_titleName"></span>
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
        </div>
    </el-card>
</template>

<script>
    import Vue from 'vue'
    import { mapState } from 'vuex'
    import { showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('titleName', {
                title(state) {
                    if(state.form.titleMst.titleId !== '') {
                        return state.form.titleMst.titleCode + ':' + state.form.titleMst.titleName + '(' + state.form.titleMst.activeStartTime + '-' + state.form.titleMst.activeEndTime + ')';
                    } else {
                        return '';
                    }
                }
            }),
            ...mapState('titleName', ['form']),
            ...mapState('common', ['locales'])
        },
        methods: {
            regist: function(event) {
                this.$confirm('登録します。よろしいですか？', '登録確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('titleName/registTitleName').then(() => {
                        this.$router.push({ name: 'titleUpdate', params: { titleId: this.$store.state.titleName.form.titleId}});
                        this.$store.dispatch('common/deleteNavi', {name: '役職名マスタ登録', path: 'titleName-regist'});
                        this.$store.dispatch('common/setTable', {name: '役職マスタ', value: 'title'});
                        this.$alert('登録が完了しました。', '登録完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
                });
            },
            clear() {
                this.$store.dispatch('titleName/clearForm');
            },
            back() {
                this.$router.push({name: 'titleUpdate'});
                this.$store.dispatch('common/deleteNavi', {name: '役職名マスタ登録', path: 'titleName-regist'});
                this.$store.dispatch('common/setTable', {name: '役職マスタ', value: 'title'});
                this.$store.dispatch('common/setSelectedTable', 'title');
            }
        },
        created: function() {
            this.$store.dispatch('titleName/clearForm');
            this.$store.dispatch('titleName/setActiveTime');
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('titleName/setTitle');
            }
        }
    }
</script>
