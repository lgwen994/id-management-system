<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Position  Create</span>
            </div>
            <div>
                <el-row>
                    <el-form ref="form" :model="form" label-width="150px">
                        <el-form-item label="Position Code">
                            <el-input v-model="form.positionCode" id="positionCode"></el-input>
                        </el-form-item>
                        <el-form-item label="User ">
                            <el-col :span="20">
                                <el-input v-model="user" :disabled="true" id="user"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openUserDialog">Dialog</el-button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="Orgnazation ">
                            <el-input v-model="org" :disabled="true" id="org"></el-input>
                        </el-form-item>
                        <el-form-item label="Title ">
                            <el-col :span="20">
                                <el-input v-model="title" :disabled="true" id="title"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openTitleDialog">Dialog</el-button>
                                </el-col>
                        </el-form-item>
                        <el-form-item label="Concurrent Flg">
                            <el-radio v-model="form.concurrentFlg" label="0">Own Post</el-radio>
                            <el-radio v-model="form.concurrentFlg" label="1">Additional Post</el-radio>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                            <br>If you do not enter it, it will be set indefinitely
                        </el-form-item>
                    </el-form>
                </el-row>
                <el-row>
                    <div style="text-align:right;">
                        <el-button @click="back">Back</el-button>
                        <el-button @click="clear">Clear</el-button>
                        <el-button @click="regist" type="primary"> Create</el-button>
                    </div>
                </el-row>
            </div>
        </el-card>
        <role-dialog></role-dialog>
        <user-dialog></user-dialog>
        <title-dialog></title-dialog>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import {showErrorMessage} from '../common';
import TitleReference from './TitleReference.vue';
import UserReference from './UserReference.vue';

export default {
    components: {
        'title-dialog': TitleReference,
        'user-dialog': UserReference
    },
    computed: {
        ...mapState('position', {
            user(state) {
                if(state.form.userMst.userId !== '') {
                    return state.form.userMst.userCode + ':' + state.form.userMst.userName + '(' + state.form.userMst.activeStartTime + '-' + state.form.userMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName +  '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            title(state) {
                if(state.form.titleMst.titleId !== '') {
                    return state.form.titleMst.titleCode + ':' + state.form.titleMst.titleName +  '(' + state.form.titleMst.activeStartTime + '-' + state.form.titleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('position', ['form'])
    },
    methods: {
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/regist').then(() => {
                    this.$router.push({ name: 'orgUpdate', params: { orgId: this.$store.state.position.form.orgMst.orgId}});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        openUserDialog() {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'position', kbn: 'Regist'});
        },
        openTitleDialog() {
            this.$store.dispatch('titleReference/openTitleDialog', {companyId: this.$store.state.position.form.orgMst.companyId, screen: 'position'});
        },
        clear() {
            this.$store.dispatch('position/clearForm');
        },
        back() {
            this.$router.push({
                name: 'orgUpdate',
                params: {
                    orgId: this.$store.state.position.form.orgMst.orgId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: 'Position  Create', path: 'position-regist'});
            this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        this.$store.dispatch('position/clearForm');
        this.$store.dispatch('position/setOrg', this.$route.params);
    }
}
</script>
