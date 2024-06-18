<script setup lang="ts">
import {
  addSortRequest,
  editSortRequest,
  getAllSortRequest,
  removeSortRequest,
} from "@/api/sort";
import { useUserStore } from "@/store/userStore";
import { SortData } from "@/type/websiteDataType";
import { ElMessage } from "element-plus";
import { computed, onMounted, reactive, ref } from "vue";

const sortArr = ref<SortData[]>([]);
let sortParames = ref<SortData>({
  isLook: false,
});
const userStore = useUserStore();
const drawer = ref(false);
const loading = ref(false);
const submitLoading = ref(false);
// 页面渲染前
onMounted(async () => {
  await getSortArr();
});
const getSortArr = async () => {
  const result = await getAllSortRequest();
  sortArr.value = result.data;
};

const search = ref("");
const filterTableData = computed(() =>
  sortArr.value.filter(
    (data) =>
      !search.value || data.sortName!.toLowerCase().includes(search.value.toLowerCase())
  )
);
const isLookChange = async (row: SortData) => {
  loading.value = true;
  //发送请求
  const result = await editSortRequest(row);

  setTimeout(() => {
    loading.value = false;
  }, 500);
};
const addSort = () => {
  sortParames.value.id = "";
  sortParames.value.isLook = false;
  sortParames.value.intor = "";
  sortParames.value.sortName = "";
  sortParames.value.uid = userStore.id;
  drawer.value = true;
};
const editSort = (row: SortData) => {
  sortParames.value.id = row.id;
  sortParames.value.isLook = row.isLook;
  sortParames.value.intor = row.intor;
  sortParames.value.sortName = row.sortName;
  sortParames.value.uid = userStore.id;
  drawer.value = true;
};
const removeSort = async (row: SortData) => {
  const result = await removeSortRequest(row.id as string);
  if (result.code == 200) {
    ElMessage.success("删除成功");
    await getSortArr();
  } else {
    ElMessage.error("删除失败，有文章在应用");
  }
};
const submitSort = async () => {
  // 提交请求
  if (sortParames.value.id) {
    //  编辑
    submitLoading.value = true;
    const result = await editSortRequest(sortParames.value);
    drawer.value = false;
  } else {
    // 添加
    submitLoading.value = true;
    const result = await addSortRequest(sortParames.value);
    drawer.value = false;
  }
  await getSortArr();
  submitLoading.value = false;
};
</script>

<template>
  <div class="sortAdmin">
    <div class="top">
      <div class="w-[200px]">
        <el-button type="primary" size="default" @click="addSort">
          添加分类
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
        <el-table-column prop="sortName" label="名称" width="220" />
        <!--  -->
        <el-table-column prop="intor" label="简介" />
        <!--  -->
        <el-table-column prop="isLook" label="是否展示">
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
        <el-table-column prop="create_time" sortable label="创建时间" />
        <!--  -->
        <el-table-column>
          <template #default="{ row }">
            <el-button type="primary" @click="editSort(row)">编辑</el-button>
            <el-button type="danger" @click="removeSort(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-drawer v-model="drawer" :title="`${sortParames.id ? '编辑分类' : '添加分类'}`">
      <el-form class="w-[300px]">
        <el-form-item label="名称：">
          <el-input v-model="sortParames.sortName"></el-input>
        </el-form-item>
        <el-form-item label="简介："
          ><el-input v-model="sortParames.intor"></el-input
        ></el-form-item>
        <el-form-item label="是否展示："
          ><el-switch
            v-model="sortParames.isLook"
            size="large"
            active-text="是"
            inactive-text="否"
        /></el-form-item>
        <el-form-item label="确认：">
          <el-button
            type="primary"
            size="default"
            :loading="submitLoading"
            @click="submitSort"
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
