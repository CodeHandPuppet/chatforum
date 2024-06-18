<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import ForumArticle from "../forum/forumArticle.vue";
import { VisForum } from "@/api/type";
import { getArticleByUidRequest } from "@/api/forum";
import { useRoute } from "vue-router";
import {
  getArrForumCollectRequest,
  getArrForumLikeRequest,
} from "@/api/forumLikeOrCollect";
let forumArr = ref<VisForum[]>([]);
const likeArr = ref<number[]>([]);
const collectArr = ref<number[]>([]);
onMounted(async () => {
  await getForumArr();
  const likeArrResult = await getArrForumLikeRequest(route.query.id as string);
  const collectArrResult = await getArrForumCollectRequest(route.query.id as string);
  likeArr.value = likeArrResult.data.Ids;
  collectArr.value = collectArrResult.data.Ids;
});
const route = useRoute();
const getForumArr = async () => {
  const resutl = await getArticleByUidRequest("like", route.query.id as string);
  forumArr.value = resutl.data as VisForum[];
};
const removeForum = (id: number | string) => {
  forumArr.value = forumArr.value.filter((f) => {
    return f.id != id;
  });
};
</script>

<template>
  <div class="myarticle">
    <div v-for="(article, index) in forumArr" class="box">
      <ForumArticle
        :forum="article"
        :isleft="true"
        :iscollectp="collectArr.includes(article.id as number)"
        :islikep="likeArr.includes(article.id as number)"
        @removeForum="removeForum"
      ></ForumArticle>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.myarticle {
  @apply grid grid-cols-2 py-4 px-6 gap-y-10 h-full gap-x-14;
  .box {
    @apply h-64;
  }
}
</style>
