<script setup lang="ts">
const clickBack = () => {
  window.history.go(-1);
};
</script>

<template>
  <router-view></router-view>
  <!-- <div class="fixed bottom-44 right-10 flex z-[100000] flex-col items-center gap-2">
    <el-tooltip content="返回上一页">
      <el-button @click="clickBack" size="large" circle icon="Back"></el-button>
    </el-tooltip>
  </div> -->
  <!-- <a-back-top /> -->
</template>

<style scoped></style>
