<template>
    <el-dialog
        title="Delete Confirm"
        :visible.sync="propDialogVisible"
        :close-on-press-escape="true"
        :before-close="cancelHandler"
        width="30%">
        <span>{{this.propDeleteMsg}}</span>
        <span slot="footer" class="dialog-footer">
            <el-button @click="cancelHandler">Cancel</el-button>
            <el-button type="danger" @click="deleteHandler">Delete</el-button>
        </span>
    </el-dialog>
</template>
<script>
export default {
    name : "DeleteConfirmDialog",
    props:{
        propDeleteMsg: String,
        propDialogVisible: Boolean,
    },
    data() {
        return {};
    },
    methods: {
        cancelHandler(){
            this.$emit("cancelDelete");
        },
        deleteHandler(){
            this.$emit("confirmDelete");
        },
    }
}
</script>
