<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>User Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="120px" id="userRegistForm">
                <el-form-item label="User Code">
                    <el-input v-model="form.userCode" id="userCode"></el-input>
                    <span id="message_userCode"></span>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item label="User Name">
                    <el-input v-model="form.userName" id="userName"></el-input>
                    <span id="message_userName"></span>
                </el-form-item>
                <el-form-item label="Mail Address">
                    <el-input v-model="form.mail"  id="mail"></el-input>
                    <span id="message_mail"></span>
                </el-form-item>
                <el-form-item label="Telephone Number">
                    <el-input v-model="form.tel" id="tel"></el-input>
                    <span id="message_tel"></span>
                </el-form-item>
                <el-form-item label="FAX Number">
                    <el-input v-model="form.fax"  id="fax"></el-input>
                    <span id="message_fax"></span>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registUser"> Create</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
    import { mapState } from 'vuex'
    import { showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('user', ['form']),
            ...mapState('common', ['locales'])
        },
        methods: {
            regist: function() {
                    this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                        ConfirmButtonText: 'OK',
                        cancelButtonText: 'Cancel',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('user/registUser').then(() => {
                            this.$router.push({ name: 'userUpdate', params: { userId: this.$store.state.user.form.userId }});
                            this.$store.dispatch('common/setNaviList', {name: "User Details", path: 'user-update'});
                            this.$alert(' Create is completed.', ' Created', {
                                ConfirmButtonText: 'OK'
                            });
                        }).catch(error => showErrorMessage(error));
                    }).catch(() => {
                        // Cancel
                    });
            },
            clear() {
                this.$store.dispatch('user/clearForm');
            },
        },
        created: function() {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'User  Create', path: 'user-regist'});
            this.$store.dispatch('common/setTable', {name: "User ", value: 'user'})
            this.$store.dispatch('common/setSelectedTable', "user");
            this.$store.dispatch('user/clearForm');
            this.$store.dispatch('user/setActiveStartTime');
        }
    }
</script>
