<script setup lang="ts">
import navigationVue from "./Navigation/index.vue";
</script>

<template>
  <div class="homePageBox">
    <div class="homePage-left">
      <navigationVue />
    </div>
    <div class="homePage-right">
      <router-view></router-view>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.homePageBox {
  @apply min-h-screen w-screen grid grid-cols-[70px_auto] overflow-hidden grid-rows-1 font-medium;
  .homePage-left {
    @apply bg-gray-100;
  }
  .homePage-right {
    @apply h-screen  overflow-hidden;
  }
}
</style>
