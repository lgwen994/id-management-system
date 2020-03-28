<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>IDMFPolicy  Create</span>
        </div>
        <div>
            <el-form ref="policyForm" :model="policyForm" label-width="100px" id="policyRegistForm">
                <el-form-item label="Policy Code">
                    <el-input v-model="policyForm.policyCode" id="policyCode"></el-input>
                    <span id="message_policyCode"></span>
                </el-form-item>
                <el-form-item label="Effect">
                    <el-input v-model="policyForm.effect" id="effect"></el-input>
                    <span id="message_effect"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registPolicy"> Create</el-button>
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
            ...mapState('policy', ['policyForm'])
        },
        methods: {
            regist: function() {
                this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('policy/registPolicy').then(() => {
                        this.$router.push({ name: 'policyUpdate', params: { policyId: this.$store.state.policy.policyForm.policyId }});
                        this.$store.dispatch('common/setNaviList', {name: "IDMFPolicy Details", path: 'policy-update'});
                        this.$alert(' Create is completed.', ' Created', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // Cancel
                });
            },
            clear() {
                this.$store.dispatch('policy/clearForm')
            },
        },
        created: function () {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'IDMFPolicy  Create', path: 'policy-regist'});
            this.$store.dispatch('common/setTable', {name: "IDMFPolicy ", value: 'policy'});
            this.$store.dispatch('common/setSelectedTable', 'policy');
            this.$store.dispatch('policy/clearForm')
        }
    }
</script>
