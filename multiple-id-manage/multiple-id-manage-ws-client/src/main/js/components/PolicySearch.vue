<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>IDMFポリシ検索</span>
                </div>
                <div>
                    <el-form ref="policySearchForm" :model="policySearchForm" label-width="100px" id="policySearchForm">
                        <el-form-item label="ポリシコード">
                            <el-input v-model="policySearchForm.policyCode" id="policyCode"></el-input>
                            <span id="message_policyCode"></span>
                        </el-form-item>
                        <el-form-item label="エフェクト">
                            <el-input v-model="policySearchForm.effect" id="effect"></el-input>
                            <span id="message_effect"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">クリア</el-button>
                                <el-button @click="search" type="primary" id="searchPolicy">検索</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </el-col>
        <el-col :span="12">
            <policy-search-result></policy-search-result>
        </el-col>
    </el-row>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'
import PolicySearchResult from './PolicySearchResult.vue'

export default {
    components: {
        'policy-search-result': PolicySearchResult
    },
    computed: {
        ...mapState('policy', ['policySearchForm'])
    },
    methods: {
        search(event) {
            this.$store.dispatch('policy/setPage', 1);
            this.$store.dispatch('policy/searchPolicyList').then(() => {
                this.$store.dispatch('policy/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('policy/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.policy.searchResultVisible === true){
            this.$store.dispatch('policy/searchPolicyList');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: "IDMFポリシ", value: 'policy'});
        this.$store.dispatch('common/setNaviList', {name: "IDMFポリシ検索", path: 'policy-search'});
        this.$store.dispatch('common/setSelectedTable', "policy");
    }
}
</script>
