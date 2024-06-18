<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { setLocalSrorage, getLocalSrorage } from "@/utils/localStorage";
import { ref, watch } from "vue";
import router from "@/router";
import { useUserStore } from "@/store/userStore";
const $router = useRouter();
const route = useRoute();
const name = "menuActive";
const defaultActive = ref(route.path);
const userStore = useUserStore();
// const toRoute = (path: string, index: number) => {
//   setLocalSrorage(name, index);
//   defaultActive.value = index;
//   $router.push(path);
// };
watch(route, () => {
  defaultActive.value = route.path;
});
const selectMenu = (index: any) => {
  router.push(index);
};
</script>

<template>
  <div class="menu w-full">
    <el-menu
      @select="selectMenu"
      :default-active="`${defaultActive}`"
      :collapse="true"
      background-color=" rgb(243 244 246)"
    >
      <!-- 管理员界面 -->
      <el-sub-menu index="1" v-if="userStore.type == 'admin'">
        <template #title>
          <el-tooltip content="管理员页面" placement="right-start" effect="light">
            <el-icon><Platform /></el-icon>
          </el-tooltip>
        </template>
        <el-menu-item index="/admin/user">用户管理</el-menu-item>
        <el-menu-item index="/admin/notifications">通知管理</el-menu-item>
        <el-menu-item index="/admin/sort">分类管理</el-menu-item>
        <!-- <el-menu-item index="/admin/webInfo">网站管理</el-menu-item> -->
        <el-menu-item index="/admin/address">学院管理</el-menu-item>
      </el-sub-menu>
      <!-- 论坛主页 -->
      <el-menu-item index="/forum">
        <el-tooltip content="论坛首页" placement="right-start" effect="light">
          <el-icon :size="30">
            <Promotion />
          </el-icon>
        </el-tooltip>
      </el-menu-item>
      <!-- 消息列表 -->
      <el-menu-item index="/messageList">
        <el-tooltip content="聊天消息" placement="right-start" effect="light">
          <el-icon :size="30">
            <Comment />
          </el-icon>
        </el-tooltip>
      </el-menu-item>
      <!-- 好友列表 -->
      <el-menu-item index="/chatList">
        <el-tooltip content="好友列表" placement="right-start" effect="light">
          <el-icon :size="30">
            <User />
          </el-icon>
        </el-tooltip>
      </el-menu-item>
      <!-- 通知 -->
      <el-menu-item index="/notification">
        <el-tooltip content="通知" placement="right-start" effect="light">
          <el-icon :size="30">
            <Bell />
          </el-icon>
        </el-tooltip>
      </el-menu-item>
      <!-- <el-menu-item index="1" @click="$router.push('/genericPage')">
        <el-tooltip content="首页" placement="right-start" effect="light">
          <el-icon :size="30">
            <HomeFilled />
          </el-icon>
        </el-tooltip>
      </el-menu-item> -->
    </el-menu>
  </div>
</template>

<style scoped>
.el-menu {
  border: 0;
}
.el-menu--collapse {
  width: 100%;
}
</style>
