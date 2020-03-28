<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Title Name Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="Title ">
                    <el-input v-model="title" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale" placeholder="" id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Title Name">
                    <el-input v-model="form.titleName" id="titleName"></el-input>
                    <span id="message_titleName"></span>
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
        </div>
    </el-card>
</template>

<script>
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
            regist: function() {
                this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('titleName/registTitleName').then(() => {
                        this.$router.push({ name: 'titleUpdate', params: { titleId: this.$store.state.titleName.form.titleId}});
                        this.$store.dispatch('common/deleteNavi', {name: 'Title Name Create', path: 'titleName-regist'});
                        this.$store.dispatch('common/setTable', {name: 'Title ', value: 'title'});
                        this.$alert(' Create is completed.', ' Created', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // Cancel
                });
            },
            clear() {
                this.$store.dispatch('titleName/clearForm');
            },
            back() {
                this.$router.push({name: 'titleUpdate'});
                this.$store.dispatch('common/deleteNavi', {name: 'Title Name Create', path: 'titleName-regist'});
                this.$store.dispatch('common/setTable', {name: 'Title ', value: 'title'});
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
