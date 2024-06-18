<script setup lang="ts">
import ForumArticle from "./forumArticle.vue";
import LeftContainer from "./leftContainer.vue";
import { onMounted, ref, watch } from "vue";
import { getAllArticleRequest, getArticleBySortRequest } from "@/api/forum";
import { VisForum } from "@/api/type";
import { useSortStore } from "@/store/sortStore";
import { useUserStore } from "@/store/userStore";
import {
  getArrForumCollectRequest,
  getArrForumLikeRequest,
} from "@/api/forumLikeOrCollect";
const bg = "/src/assets/image/bg.jpg";
const sortStore = useSortStore();
const userStore = useUserStore();
let forumArr = ref<VisForum[]>([]);
const likeArr = ref<number[]>([]);
const collectArr = ref<number[]>([]);
// 初始化 页面数据
onMounted(async () => {
  await getForumArr();
  const likeArrResult = await getArrForumLikeRequest(userStore.id);
  const collectArrResult = await getArrForumCollectRequest(userStore.id);
  likeArr.value = likeArrResult.data.Ids;
  collectArr.value = collectArrResult.data.Ids;
});
// 监听 id变化刷新这个页面
watch(
  () => sortStore.id,
  () => getForumArr()
);
const removeLike = (id: number | string) => {
  console.log(likeArr.value);
  let index = likeArr.value.findIndex((x) => x == id);
  if (index == -1) {
    likeArr.value.push(id as number);
  } else {
    likeArr.value.splice(index, 1);
  }
  console.log(likeArr.value);
};
const removeCollect = (id: number | string) => {
  let index = collectArr.value.findIndex((x) => x == id);
  if (index == -1) {
    collectArr.value.push(id as number);
  } else {
    collectArr.value.splice(index, 1);
  }
};
const getForumArr = async () => {
  // 初始化getForumArr
  const getAllForumArr = async () => {
    const result = await getAllArticleRequest();
    forumArr.value = result.data as VisForum[];
  };
  // 根据id获取
  const getforumArrBySortId = async () => {
    const result = await getArticleBySortRequest(sortStore.id as string);
    forumArr.value = result.data as VisForum[];
  };
  // 获取forumArr
  if (sortStore.id == 0) {
    await getAllForumArr();
  } else await getforumArrBySortId();
};
const removeForum = (id: number | string) => {
  forumArr.value = forumArr.value.filter((f) => {
    return f.id != id;
  });
};
</script>

<template>
  <div class="forumBox">
    <div class="bg_forum">
      <div class="main relative" :style="{ backgroundImage: `url(${bg})` }"></div>
    </div>
    <div class="forum_mian">
      <div class="left">
        <LeftContainer></LeftContainer>
      </div>
      <!--  -->
      <div class="right">
        <!-- 头部的分类名称 -->
        <div class="sortName">
          <section><i class="fas fa-guitar"></i>{{ sortStore.sortName }}</section>
        </div>

        <div v-for="(forum, index) in forumArr" class="forumArticle">
          <!-- 叉的符号 已脱离 文档流 -->
          <ForumArticle
            :isleft="index % 2 == 0"
            :forum="forum"
            @removeForum="removeForum"
            :islikep="likeArr.includes(forum.id as number)"
            :iscollectp="collectArr.includes(forum.id as number)"
          ></ForumArticle>
        </div>
        <!--  -->
        <!-- <div><el-button>下一页</el-button></div> -->
      </div>
      <!--  -->
    </div>
  </div>
</template>

<style scoped lang="scss">
.forumBox {
  @apply flex flex-col h-full overflow-auto;
  .bg_forum {
    @apply h-[300px] w-full border-b;
    .main {
      @apply w-full h-[300px] bg-center bg-cover;
    }
  }
  .forum_mian {
    @apply bg-white  w-full flex flex-row px-28 2xl:px-64;
    // height: calc(100% - 300px);
    .left {
      @apply w-[400px] bg-white h-auto;
    }
    .right {
      @apply flex flex-col w-full items-center pt-5 pb-5 gap-11;
      .sortName {
        @apply w-5/6 h-16 border-b border-dashed border-gray-200;
        section {
          @apply h-full text-3xl  flex items-end pb-2;
          i {
            @apply pr-3 text-cyan-500 pb-1;
          }
        }
      }
      .forumArticle {
        @apply w-5/6 min-w-[500px] max-w-[700px] h-[280px] relative;
      }
    }
  }
}
</style>
