<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Title  Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="titleRegistForm">
                <el-form-item label="Title Code">
                    <el-input v-model="form.titleCode" id="titleCode"></el-input>
                    <span id="message_titleCode"></span>
                </el-form-item>
                <el-form-item label="Company">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openCompanyDialog">Dialog</el-button>
                    </el-col>
                </el-form-item>
                <el-form-item label="Group Flg">
                    <el-radio v-model="form.groupFlg" label="0">Real Title </el-radio>
                    <el-radio v-model="form.groupFlg" label="1">Logical Title </el-radio>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item label="Title Name">
                    <el-input v-model="form.titleName" id="titleName"></el-input>
                    <span id="message_titleName"></span>
                </el-form-item>
                <el-form-item label="Title Type">
                    <el-input v-model="form.titleType" id="titleType"></el-input>
                    <span id="message_titleType"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registTitle"> Create</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
    <company-dialog></company-dialog>
    </div>
</template>

<script>
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
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('title/registTitle').then(() => {
                    this.$router.push({ name: 'titleUpdate', params: { titleId: this.$store.state.title.form.titleId }});
                    this.$store.dispatch('common/setNaviList', {name: 'Title Details', path: 'title-update'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        openCompanyDialog() {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'title', kbn: 'Regist'});
        },
        clear() {
            this.$store.dispatch('title/clearForm');
        }
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'Title  Create', path: 'title-regist'});
        this.$store.dispatch('common/setTable', {name: 'Title ', value: 'title'})
        this.$store.dispatch('common/setSelectedTable', 'title');
        this.$store.dispatch('title/clearForm');
        this.$store.dispatch('title/setActiveStartTime');
    }
}
</script>
