<script setup lang="ts">
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
const router = useRouter();
const route = useRoute();
// 控制菜单谁亮的标签
const isMenu = ref(0);
const userId = route.query.id;

const clickMenu = (routeName: string, index: number) => {
  isMenu.value = index;
  router.push({ name: routeName, query: { id: userId } });
};

const menus = ref([
  {
    name: "文章",
    icon: "fas fa-house-user",
    routeName: "MyArticle",
  },
  {
    name: "收藏",
    icon: "fas fa-house-user",
    routeName: "MyCollect",
  },
  {
    name: "点赞",
    icon: "fas fa-house-user",
    routeName: "MyLike",
  },
]);
</script>

<template>
  <div class="userMenu">
    <div
      class="option"
      v-for="(menu, index) in menus"
      :key="index"
      :class="{ 'border-b-[4px]': isMenu == index }"
      @click="clickMenu(menu.routeName, index)"
    >
      <i :class="menu.icon" class="text-red-500"></i>
      <span>{{ menu.name }}</span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.userMenu {
  @apply flex w-full h-full pb-1 px-8 gap-8;
  font-family: "Courier New", Courier, monospace;
  .option {
    @apply flex justify-center  h-full items-center text-lg  
     box-content border-cyan-500   rounded-sm text-sm  cursor-pointer;
    i {
      @apply pr-1;
    }
  }
}
</style>
