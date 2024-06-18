<script setup lang="ts">
import { getAllUsersInfoRequest } from "@/api/user";
import { UserData } from "@/type/websiteDataType";
import { computed, onMounted, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const userArr = ref<UserData[]>([]);
const getUserArr = async () => {
  // 获取全部用户请求
  const result = await getAllUsersInfoRequest();
  userArr.value = result.data;
};
const show = ref(false);
onMounted(async () => {
  await getUserArr();
});

const search = ref("");
const filterTableData = computed(() =>
  userArr.value.filter(
    (data) =>
      !search.value || data.username!.toLowerCase().includes(search.value.toLowerCase())
  )
);
const toUserHome = (id: any) => {
  router.push({ path: "/userHome", query: { id } });
};
</script>

<template>
  <div class="chatSearch">
    <el-input
      class="inputSearch"
      v-model="search"
      placeholder="搜索"
      prefix-icon="Search"
    />
    <el-button class="buttonSearch" @click="show = !show">
      {{ `${!show ? "搜索" : "取消"}` }}
    </el-button>

    <div v-if="show" class="absolute z-[1000] left-[300px] bg-gray-300 w-[300px]">
      <div class="flex justify-center items-center h-[40px] border-b">搜索情况</div>
      <div class="overflow-y-hidden h-[300px] hover:overflow-y-auto" v-if="search">
        <template v-for="item of filterTableData">
          <section class="chatBox" @click="toUserHome(item.id)">
            <img :src="item?.avatar" alt="" />
            <div class="chatBox-info">
              <span>{{ item?.nickname }}</span>
              <span>{{ item?.username }}</span>
            </div>
          </section>
        </template>
      </div>
      <div v-else class="flex justify-center items-center h-[40px]">无搜索结果</div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.chatSearch {
  @apply p-3 pt-5 pb-2  grid grid-cols-[5fr_1fr] grid-rows-1 gap-2 bg-white relative;
  .inputSearch {
    input {
      @apply bg-slate-100;
    }
  }
  .buttonSearch {
    @apply bg-slate-100;
  }
  .chatBox {
    @apply cursor-pointer grid mb-1 grid-cols-[auto_1fr] gap-1  p-3 w-full h-16 hover:bg-gray-200;

    img {
      @apply h-full w-10 rounded-full;
    }
    .chatBox-info {
      @apply flex items-center justify-between;
    }
  }
}
</style>
