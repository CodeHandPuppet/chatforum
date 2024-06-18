<script setup lang="ts">
import {
  addAddressRequest,
  editAddressRequest,
  getAllAddressRequest,
} from "@/api/address";
import { useUserStore } from "@/store/userStore";
import { AddressData, SortData } from "@/type/websiteDataType";
import { computed, onMounted, reactive, ref } from "vue";

// const drawerTitle=""

const addressArr = ref<AddressData[]>([]);
let addressParames = ref<AddressData>({
  intor: "",
  name: "",
});
const isLoading = ref(false);
const userStore = useUserStore();
const drawer = ref(false);
const submitLoading = ref(false);
// 页面渲染前
onMounted(async () => {
  await getSortArr();
});
const getSortArr = async () => {
  const result = await getAllAddressRequest();
  addressArr.value = result.data;
};

const search = ref("");
const filterTableData = computed(() =>
  addressArr.value.filter(
    (data) =>
      !search.value || data.name!.toLowerCase().includes(search.value.toLowerCase())
  )
);
const addSort = () => {
  addressParames.value.id = "";
  addressParames.value.name = "";
  addressParames.value.intor = "";
  addressParames.value.isLook = false;
  drawer.value = true;
};
const editSort = (row: AddressData) => {
  addressParames.value.id = row.id;
  addressParames.value.name = row.name;
  addressParames.value.intor = row.intor;
  addressParames.value.isLook = row.isLook;
  drawer.value = true;
};

const submitSort = async () => {
  let result;
  // 提交请求
  if (addressParames.value.id) {
    //  编辑
    submitLoading.value = true;
    result = await editAddressRequest(addressParames.value);
  } else {
    // 添加
    submitLoading.value = true;
    result = await addAddressRequest(addressParames.value);
  }
  if (result.code == 200) {
    await getSortArr();
  }
  drawer.value = false;
  submitLoading.value = false;
};
const switchRowIsLook = async (row: AddressData) => {
  isLoading.value = true;
  await editAddressRequest(row);
  isLoading.value = false;
};
</script>

<template>
  <div class="addressAdmin">
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
        <el-table-column prop="name" label="名称" width="220" />
        <!--  -->
        <el-table-column prop="intor" label="简介" />
        <!--  -->
        <el-table-column label="是否启用">
          <template #default="{ row }">
            <el-switch
              v-model="row.isLook"
              size="large"
              @click="switchRowIsLook(row)"
              active-text="是"
              inactive-text="否"
            />
          </template>
        </el-table-column>
        <!--  -->
        <el-table-column prop="createTime" sortable label="创建时间" />
        <!--  -->
        <el-table-column>
          <template #default="{ row }">
            <el-button type="primary" @click="editSort(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-drawer v-model="drawer" :title="`${addressParames.id ? '编辑学院' : '添加学院'}`">
      <el-form class="w-[300px]">
        <el-form-item label="名称：">
          <el-input v-model="addressParames.name"></el-input>
        </el-form-item>
        <el-form-item label="简介："
          ><el-input v-model="addressParames.intor"></el-input
        ></el-form-item>
        <el-form-item label="是否展示："
          ><el-switch
            v-model="addressParames.isLook"
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
.addressAdmin {
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
