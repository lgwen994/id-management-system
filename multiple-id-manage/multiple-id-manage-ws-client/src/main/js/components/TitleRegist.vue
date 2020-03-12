<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>役職マスタ登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="titleRegistForm">
                <el-form-item label="役職コード">
                    <el-input v-model="form.titleCode" id="titleCode"></el-input>
                    <span id="message_titleCode"></span>
                </el-form-item>
                <el-form-item label="会社マスタ">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openCompanyDialog">参照</button>
                    </el-col>
                </el-form-item>
                <el-form-item label="グループフラグ">
                    <el-radio v-model="form.groupFlg" label="0">実在役職</el-radio>
                    <el-radio v-model="form.groupFlg" label="1">仮想役職</el-radio>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item label="役職名">
                    <el-input v-model="form.titleName" id="titleName"></el-input>
                    <span id="message_titleName"></span>
                </el-form-item>
                <el-form-item label="役職種別">
                    <el-input v-model="form.titleType" id="titleType"></el-input>
                    <span id="message_titleType"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registTitle">登録</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
    <company-dialog></company-dialog>
    </div>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common';
import CompanyReference from './CompanyReference.vue';

export default {
    components: {
        'company-dialog': CompanyReference
    },
    computed: {
        ...mapState('title', {
            company(state) {
                if(state.form.companyMst.companyId !== '') {
                    return state.form.companyMst.companyCode + ':' + state.form.companyMst.companyName + '(' + state.form.companyMst.activeStartTime + '-' + state.form.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('title', ['form'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('title/registTitle').then(() => {
                    this.$router.push({ name: 'titleUpdate', params: { titleId: this.$store.state.title.form.titleId }});
                    this.$store.dispatch('common/setNaviList', {name: '役職マスタ詳細', path: 'title-update'});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        openCompanyDialog(event) {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'title', kbn: 'Regist'});
        },
        clear() {
            this.$store.dispatch('title/clearForm');
        }
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: '役職マスタ登録', path: 'title-regist'});
        this.$store.dispatch('common/setTable', {name: '役職マスタ', value: 'title'})
        this.$store.dispatch('common/setSelectedTable', 'title');
        this.$store.dispatch('title/clearForm');
        this.$store.dispatch('title/setActiveStartTime');
    }
}
</script>
