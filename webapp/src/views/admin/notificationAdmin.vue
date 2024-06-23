<script setup lang="ts">
import {
  addNotificationRequest,
  editNotificationRequest,
  getAdminNotificationRequest,
  removeNotificationRequest,
} from "@/api/notification";

import { useUserStore } from "@/store/userStore";
import { NotificationData } from "@/type/websiteDataType";
import { ElMessage } from "element-plus";
import { computed, onMounted, ref } from "vue";

const notificationArr = ref<NotificationData[]>([]);
let notificaParames = ref<NotificationData>({
  isLook: false,
});
const userStore = useUserStore();
const drawer = ref(false);
const loading = ref(false);
const submitLoading = ref(false);
// 页面渲染前
onMounted(async () => {
  await getNotificationArr();
});
const getNotificationArr = async () => {
  const result = await getAdminNotificationRequest();
  notificationArr.value = result.data;
};
// 匹配
const search = ref("");
const filterTableData = computed(() =>
  notificationArr.value.filter(
    (data) =>
      !search.value || data.content!.toLowerCase().includes(search.value.toLowerCase())
  )
);
const isLookChange = async (row: NotificationData) => {
  loading.value = true;
  //发送请求
  const result = await editNotificationRequest(row);
  setTimeout(() => {
    loading.value = false;
  }, 500);
};
//
const addNotification = () => {
  notificaParames.value.content = "";
  notificaParames.value.id = "";
  notificaParames.value.isLook = true;
  notificaParames.value.senderId = userStore.id;
  drawer.value = true;
};
//
const editNotification = (row: NotificationData) => {
  notificaParames.value.content = row.content;
  notificaParames.value.id = row.id;
  notificaParames.value.isLook = row.isLook;
  notificaParames.value.senderId = row.senderId;
  drawer.value = true;
};
const removeNotification = async (row: NotificationData) => {
  const result = await removeNotificationRequest(row.id as string);
  if (result.code == 200) {
    ElMessage.success("删除成功");
    await getNotificationArr();
  } else {
    ElMessage.error("删除失败");
  }
};
const submitNotification = async () => {
  // 提交请求
  if (notificaParames.value.id) {
    //  编辑
    submitLoading.value = true;
    const result = await editNotificationRequest(notificaParames.value);
    drawer.value = false;
  } else {
    // 添加
    submitLoading.value = true;
    const result = await addNotificationRequest(notificaParames.value);
    drawer.value = false;
  }
  await getNotificationArr();
  submitLoading.value = false;
};
</script>

<template>
  <div class="sortAdmin">
    <div class="top">
      <div class="w-[200px]">
        <el-button type="primary" size="default" @click="addNotification">
          添加通知
          <el-icon :icon="35">
            <Plus />
          </el-icon>
        </el-button>
      </div>
      <div class="top_input">
        <span class="">模糊匹配：</span>
        <div class="w-[200px]">
          <el-input v-model="search" placeholder="名称模糊匹配" />
        </div>
      </div>
    </div>
    <div class="bottom">
      <el-table :data="filterTableData" stripe style="width: 100%">
        <el-table-column type="index" label="编号" width="180" />
        <!--  -->
        <el-table-column prop="content" label="内容" />
        <!--  -->
        <!-- <el-table-column prop="intro" label="简介" /> -->
        <!--  -->
        <el-table-column prop="isLook" label="是否置顶展示">
          <template #default="{ row }">
            <el-switch
              v-model="row.isLook"
              @click="isLookChange(row)"
              :loading="loading"
              size="large"
              active-text="是"
              inactive-text="否"
            />
          </template>
        </el-table-column>
        <!--  -->
        <el-table-column prop="updateTime" sortable label="更新时间" />
        <!--  -->
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button type="primary" @click="editNotification(row)">编辑</el-button>
            <el-button type="danger" @click="removeNotification(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-drawer
      v-model="drawer"
      :title="`${notificaParames.id ? '编辑通知' : '添加通知'}`"
    >
      <el-form class="w-[300px]">
        <el-form-item label="内容：">
          <el-input type="textarea" v-model="notificaParames.content"></el-input>
        </el-form-item>
        <!-- <el-form-item label="简介："
          ><el-input v-model="sortParames.isLook"></el-input
        ></el-form-item> -->
        <el-form-item label="是否置顶展示："
          ><el-switch
            v-model="notificaParames.isLook"
            size="large"
            active-text="是"
            inactive-text="否"
        /></el-form-item>
        <el-form-item label="确认：">
          <el-button
            type="primary"
            size="default"
            :loading="submitLoading"
            @click="submitNotification"
            >提交</el-button
          >
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<style scoped lang="scss">
.sortAdmin {
  @apply w-full h-full overflow-y-auto pl-3;
  .top {
    @apply mb-4 flex justify-between pt-4;
    .top_input {
      @apply w-[300px] flex flex-row items-center;
    }
  }
  .bottom {
  }
}
</style>
