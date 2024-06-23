<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import MyCommentVue from "../comment/index.vue";
import ArticleBody from "./articleBody.vue";
import { useRoute, useRouter } from "vue-router";
import { getOneArticleRequest } from "@/api/forum";
import { VisForum } from "@/api/type";
import {
  addForumCollectRequest,
  addForumLikeRequest,
  getArrForumCollectRequest,
  getArrForumLikeRequest,
  removeForumCollectRequest,
  removeForumLikeRequest,
} from "@/api/forumLikeOrCollect";
import { useUserStore } from "@/store/userStore";
const route = useRoute();
const userStore = useUserStore();
// 喜欢文章的id
const isLike = ref(false);
const likeArr = ref<number[]>([]);
// 收藏文章的id
const collectArr = ref<number[]>([]);
const isCollect = ref(false);
//热度
const fireNumber = ref();
//
const toolbars = {};
const scrollRef = ref<HTMLDivElement>();
const forumId = ref(route.query.id as any);
const forumData = ref<VisForum>({
  content: "",
  img: "",
});
const router = useRouter();
// 获得本篇文章
const getForumData = async () => {
  const result = await getOneArticleRequest(forumId.value);
  forumData.value = result.data as VisForum;
};
// 渲染前
onMounted(async () => {
  await getForumData();
  const likeArrResult = await getArrForumLikeRequest(userStore.id);
  const collectArrResult = await getArrForumCollectRequest(userStore.id);
  likeArr.value = likeArrResult.data.Ids;
  collectArr.value = collectArrResult.data.Ids;
  let index = likeArr.value.findIndex((x) => x == forumId.value);
  isLike.value = index == -1 ? false : true;
  index = collectArr.value.findIndex((x) => x == forumId.value);
  isCollect.value = index == -1 ? false : true;
  fireNumber.value =
    (forumData?.value.likes as number) + (forumData?.value.collect as number) * 2;
});
//点击作者名称
const toUserHome = (id: any) => {
  router.push({ path: "/userHome", query: { id } });
};

//点击收藏或者点赞
const clickLikeOrCollect = async (type: "like" | "collect") => {
  if (type == "like") {
    // let index = likeArr.value.findIndex((x) => x == forumId.value);
    if (!isLike.value) {
      await addForumLikeRequest(userStore.id, forumId.value);
      forumData.value.likes = (forumData.value.likes as number) + 1;
      // likeArr.value.push(forumId.value);
    } else {
      await removeForumLikeRequest(userStore.id, forumId.value);
      forumData.value.likes = (forumData.value.likes as number) - 1;
      // likeArr.value.splice(index, 1);
    }
    isLike.value = !isLike.value;
  }
  if (type == "collect") {
    // let index = collectArr.value.findIndex((x) => x == forumId.value);
    if (!isCollect.value) {
      await addForumCollectRequest(userStore.id, forumId.value);
      forumData.value.collect = (forumData.value.collect as number) + 1;
      // collectArr.value.push(forumId.value);
    } else {
      await removeForumCollectRequest(userStore.id, forumId.value);
      forumData.value.collect = (forumData.value.collect as number) - 1;
      // collectArr.value.splice(index, 1);
    }
    isCollect.value = !isCollect.value;
  }
};
</script>

<template>
  <div class="detailArticle" ref="scrollRef">
    <!-- 头 -->

    <div class="article_header" :style="{ backgroundImage: `url(${forumData!.img})` }">
      <div class="forumInfo">
        <!-- 标题 -->
        <div class="title">{{ forumData.title }}</div>
        <div class="info">
          <!-- 作者 -->
          <span @click="toUserHome(forumData.user?.id)" class="cursor-pointer"
            ><i class="fas fa-user text-violet-600 pr-2"></i
            >{{ forumData.user?.nickname }}</span
          ><span>.</span>
          <!-- 发布日期 -->
          <span
            ><i class="fas fa-calendar-check mr-2 text-cyan-400"></i
            >{{ forumData.createTime }}</span
          ><span>.</span>
          <!-- 热度 -->
          <span>
            <i class="fas fa-fire text-red-500"></i
            ><span class="pl-2"> {{ fireNumber }}</span></span
          ><span>.</span>
          <!-- 点赞 -->
          <span>
            <i
              @click="clickLikeOrCollect('like')"
              class="fas fa-thumbs-up"
              :class="{ 'text-red-400': isLike }"
            ></i
            ><span class="pl-2"> {{ forumData?.likes }}</span></span
          ><span>.</span>
          <!-- 收藏 -->
          <span>
            <i
              @click="clickLikeOrCollect('collect')"
              class="fas fa-star"
              :class="{ 'text-yellow-600': isCollect }"
            >
            </i
            ><span class="pl-2"> {{ forumData?.collect }}</span></span
          >
        </div>
      </div>
    </div>
    <!-- 正文 -->
    <div class="article_body">
      <div class="articleBody">
        <mavon-editor
          :boxShadow="false"
          previewBackground="#ffffff"
          class="mavon"
          :subfield="false"
          :toolbars="toolbars"
          :defaultOpen="'preview'"
          :toolbarsFlag="false"
          :ishljs="false"
          v-model="forumData.content"
        />
      </div>
    </div>
    <!-- 评论 -->

    <div class="article_foot">
      <MyCommentVue :forumId="forumId"></MyCommentVue>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.detailArticle {
  @apply overflow-auto w-full;
  .article_header {
    @apply bg-center bg-cover flex items-end;
    height: 35vh;
    .forumInfo {
      @apply px-52 py-2  text-white flex flex-col gap-2;
      .title {
        @apply text-4xl;
      }
      .info {
        @apply text-sm flex gap-3;
      }
    }
  }
  .article_body {
    @apply px-64;
  }
  .article_foot {
    @apply px-64;
  }
}
.mavon {
  // 字体应用
  font-family: "SJ-Narrow-ExtraBold-2";
}

// 清除他的默认样式
.v-note-wrapper {
  border: 0;
}
</style>
