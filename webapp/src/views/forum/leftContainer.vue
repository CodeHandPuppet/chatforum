<script setup lang="ts">
import { useSortStore } from "@/store/sortStore";
import TimeLine from "./timeLine.vue";
import { useUserStore } from "@/store/userStore";
import { ForumData, SortData } from "@/type/websiteDataType";
import { onMounted, reactive, ref, watch } from "vue";
import { useRouter } from "vue-router";
import { getAllSortRequest } from "@/api/sort";
import { randomColorClass, getColor } from "@/utils/randomColor";
import { getForumsByLikAndCotRequest } from "@/api/forum";
import { VisForum } from "@/api/type";
const sortStore = useSortStore();
const userStore = useUserStore();
const router = useRouter();

let forumArr = reactive<VisForum[]>([]);
let sortArr = ref<SortData[]>([]);

onMounted(async () => {
  const sortResult = await getAllSortRequest();
  await getForumArr();
  sortArr.value = sortResult.data;
});

const getForumArr = async () => {
  const result = await getForumsByLikAndCotRequest();
  forumArr = result.data as VisForum[];
};
const toAddArticle = () => {
  router.push({ name: "EditArticle" });
};
const onButtonClick = (value: SortData, event: any) => {
  event.stopPropagation();
  sortStore.setId(value.id!);
  sortStore.setSortName(value.sortName as string);
  router.push({ name: "Forum" });
};
</script>

<template>
  <div class="box">
    <!-- user_box -->
    <div class="user_box">
      <div class="avatar"><img :src="userStore.avatar" /></div>
      <div class="nickname">{{ userStore.nickname }}</div>
      <div class="meta">
        <div>
          <section><span>文章</span>{{ userStore.forumNumber }}</section>
        </div>
        <div>
          <section><span>获赞</span>{{ userStore.likes }}</section>
        </div>
        <div>
          <section><span>收藏</span>{{ userStore.collect }}</section>
        </div>
      </div>
      <div class="writerBtn">
        <el-button size="large" color="#95b1e7" @click="toAddArticle"
          ><span class="text-2xl text-white from-neutral-100">去写文章</span></el-button
        >
      </div>
    </div>
    <!--  -->
    <!-- 热度最高 复制 starForum-->
    <div class="starForum">
      <div class="name">
        <span
          ><i class="fas fa-fire pr-2 text-red-500"></i
          ><span class="text-pink-400">热度最高</span></span
        >
      </div>
      <div class="simpleForum">
        <TimeLine type="primary" :forums="forumArr"></TimeLine>
      </div>
      <!--  -->
      <!--button  -->
    </div>
    <!--  -->
    <!--  -->
    <div
      class="sortListBox"
      @click="onButtonClick({ id: 0, sortName: '全部文章' }, $event)"
      :class="{ 'bg-violet-500': true }"
    >
      <span class="pl-1">速览</span>
      <span class="text-4xl" style="flex: 2">全部文章</span>
      <span class="pl-1">论坛的全部文章</span>
    </div>
    <template v-for="(sort, index) in sortArr">
      <div
        class="sortListBox"
        :class="randomColorClass[index]"
        v-if="sort.isLook"
        @click="onButtonClick(sort, $event)"
      >
        <span class="pl-1">速览</span>
        <span class="text-4xl" style="flex: 2">{{ sort.sortName }}</span>
        <span class="pl-1">{{ sort.intro }}</span>
      </div>
    </template>
  </div>
</template>

<style scoped lang="scss">
.box {
  @apply flex flex-col gap-10 w-full h-full pt-2 px-1;
  // color: #95b1e7;
  & > div {
    @apply rounded-2xl;
  }
  & > * {
    @apply shadow-sm  duration-300 hover:shadow-2xl;
  }
  .user_box {
    @apply w-full h-80;
    // 颜色渐变
    background: #7f7fd5; /* fallback for old browsers */
    background: -webkit-linear-gradient(
      to right,
      #91eae4,
      #86a8e7,
      #7f7fd5
    ); /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(
      to right,
      #91eae4,
      #86a8e7,
      #7f7fd5
    ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

    .avatar {
      @apply flex justify-center pt-6;
      img {
        @apply h-2/5 rounded-full w-[100px] duration-300 hover:rotate-[360deg] cursor-pointer delay-200;
      }
    }
    .nickname {
      @apply text-2xl text-center align-middle p-3 cursor-default;
    }
    .meta {
      @apply flex flex-row pl-14 pr-14 pt-2 pb-3 justify-between;
      div {
        @apply cursor-pointer;
        section {
          @apply flex flex-col items-center gap-3;
          span {
          }
        }
      }
    }
    .writerBtn {
      @apply text-center p-6 pt-2 text-2xl leading-5 flex justify-center;
    }
  }
  .love {
    @apply h-40 bg-gray-300;
  }
  .starForum {
    @apply h-[320px] bg-indigo-200 flex pl-4 pr-4 gap-3 flex-col;
    .name {
      @apply pt-3 text-2xl;
    }
    .simpleForum {
      @apply h-56 bg-white overflow-hidden p-2 hover:overflow-auto;
    }
  }
  .sortListBox {
    @apply h-32  flex flex-col text-white  pl-6 cursor-pointer;
    span {
      @apply align-middle h-full flex-1 flex items-center;
    }
  }
}
</style>
