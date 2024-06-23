<script setup lang="ts">
// 需要传递 acticites

// const props = defineProps({ type: { type: String, default: "primary" }, activities: {} });
import { ForumData } from "@/type/websiteDataType";
import { useRouter } from "vue-router";
const router = useRouter();

const props = defineProps({ forums: { type: Object as () => ForumData[] } });

const toArticle = (value: number) => {
  router.push({ name: "Article", query: { id: value } });
};
</script>

<template>
  <el-timeline>
    <el-timeline-item
      v-for="(forum, index) in forums"
      :key="index"
      type="primary"
      :hollow="true"
      :timestamp="forum?.createTime"
    >
      <span class="cursor-pointer" @click="toArticle(forum.id as number)">{{
        forum.title
      }}</span>
    </el-timeline-item>
  </el-timeline>
</template>

<style scoped></style>
