<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>User Name Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="User ">
                    <el-input v-model="user" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale" placeholder=""  id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="User Name">
                    <el-input v-model="form.userName" id="userName"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="back">Back</el-button>
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist"> Create</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <!--
            <el-button>Back</el-button>
            -->
        </div>
    </el-card>
</template>

<script>
import { mapState } from 'vuex'
import { showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('userName', {
            form: state => state.form,
            user(state) {
                if(state.form.userMst.userId != "") {
                    return state.form.userMst.userCode + "(" + state.form.userMst.activeStartTime + "-" + state.form.userMst.activeEndTime + ")";
                } else {
                    return "";
                }
            }
        }),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userName/registUserName').then(() => {
                    //var userNameId = this.$store.state.userName.form.userNameId;
                    this.$router.push({ name: 'userUpdate', params: { userId: this.$store.state.userName.form.userMst.userId}});
                    this.$store.dispatch('common/deleteNavi', {name: 'User Name Create', path: 'userName-regist'});
                    this.$store.dispatch('common/setTable', {name: "User ", value: 'user'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        clear() {
            this.$store.dispatch('userName/clearForm');
        },
        back() {
            this.$router.push({
                name: 'userUpdate',
                params: {
                    userId: this.$store.state.userName.form.userMst.userId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: 'User Name Create', path: 'userName-regist'});
            this.$store.dispatch('common/setTable', {name: "User ", value: 'user'});
            this.$store.dispatch('common/setSelectedTable', "user");
        }
    },
    created: function() {
        this.$store.dispatch('userName/clearForm');
        this.$store.dispatch('userName/setActiveTime');
        if(Object.keys(this.$route.params).length != 0) {
            this.$store.dispatch('userName/setUser');
        }
    }
}
</script>
