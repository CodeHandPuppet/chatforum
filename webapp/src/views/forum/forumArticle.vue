<script setup lang="ts">
import { deleteArticleRequest } from "@/api/forum";
import {
  addForumCollectRequest,
  addForumLikeRequest,
  removeForumCollectRequest,
  removeForumLikeRequest,
} from "@/api/forumLikeOrCollect";

import { VisForum } from "@/api/type";
import { useSortStore } from "@/store/sortStore";
import { useUserStore } from "@/store/userStore";
import { ForumData, SortData } from "@/type/websiteDataType";
import useStore from "element-plus/es/components/table/src/store";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const sortStore = useSortStore();
const props = defineProps({
  isleft: { type: Boolean, default: false, required: true },
  forum: { type: Object as () => VisForum },
  islikep: { type: Boolean, required: true },
  iscollectp: { type: Boolean, required: true },
});
const likes = ref<number>(props.forum?.likes as number);
const collect = ref<number>(props.forum?.collect as number);
const islike = ref(props.islikep);
const iscollect = ref(props.iscollectp);
watch(props, () => {
  islike.value = props.islikep;
  iscollect.value = props.iscollectp;
});
const emits = defineEmits<{
  (e: "removeForum", id: number | string): void;
  // (e: "removeLike", id: number | string): void;
  // (e: "removeCollect", id: number | string): void;
}>();
const userStore = useUserStore();
// 点击分类时候的跳转
const onButtonClick = (value: SortData, event: any) => {
  event.stopPropagation();
  sortStore.setId(value.id as string);
  sortStore.setSortName(value.sortName as string);
  router.push({ name: "Forum" });
};
//跳转
const toArticle = (value: VisForum) => {
  router.push({ name: "Article", query: { id: value.id } });
};
// 编辑符号
const toEdit = (event: any) => {
  event.stopPropagation();
  router.push({ name: "EditArticle", query: { id: props.forum?.id } });
};
// 叉符号
const toDelete = async () => {
  const retult = await deleteArticleRequest(props.forum?.id as string);
  if (retult.code == 200) {
    emits("removeForum", props.forum?.id as number);
  }
};
const clickLikeOrCollect = async (type: "like" | "collect", event: any) => {
  event.stopPropagation();
  if (type == "like") {
    // emits("removeLike", props.forum?.id as number);
    if (islike.value) {
      likes.value = likes.value - 1;
      // 取消请求
      await removeForumLikeRequest(userStore.id, props.forum?.id as string);
    } else {
      likes.value = likes.value + 1;
      // 点赞请求
      await addForumLikeRequest(userStore.id, props.forum?.id as string);
    }
    // 转换一下
    islike.value = !islike.value;
  }
  if (type == "collect") {
    // emits("removeCollect", props.forum?.id as number);
    if (iscollect.value) {
      collect.value = collect.value - 1;
      // 取消
      await removeForumCollectRequest(userStore.id, props.forum?.id as string);
    } else {
      collect.value = collect.value + 1;
      // 同意收藏
      await addForumCollectRequest(userStore.id, props.forum?.id as string);
    }

    iscollect.value = !iscollect.value;
  }
};
</script>

<template>
  <div class="box-card">
    <!--  -->
    <el-popconfirm
      class=" "
      v-if="forum?.uid == userStore.id || userStore.type == 'admin'"
      title="是否要删除此文章?"
      @confirm="toDelete"
    >
      <template #reference>
        <i
          class="fas fa-times hover:rotate-180 deleteIcon text-red-400 -top-3 -right-4"
        ></i>
      </template>
    </el-popconfirm>

    <i
      class="fas fa-marker deleteIcon top-2 text-amber-200 hover:text-sky-300 -right-5"
      v-if="forum!.uid == userStore.id"
      @click="toEdit($event)"
    ></i>
    <!--  -->
    <div
      class="forum_container"
      :class="{ leftOrRight: isleft }"
      @click="toArticle(forum!)"
    >
      <section class="time">
        <i class="fas fa-calendar-check mr-2 text-cyan-400"></i>
        <span class="flex items-center"
          ><h5 class="mr-1">发布于</h5>
          <h2 class="text-xs">{{ forum?.createTime }}</h2>
        </span>
      </section>
      <div class="article">
        <span class="title">{{ forum?.title }}</span>
        <div class="meta">
          <span>
            <i class="fas fa-fire text-red-500"></i
            ><span class="p-1">
              {{ (forum?.likes as number)+(forum?.collect as number * 2 ) }}</span
            >热度</span
          >
          <span @click="clickLikeOrCollect('like', $event)">
            <i class="fas fa-thumbs-up" :class="{ 'text-red-400': islike }"></i
            ><span class="p-1"> {{ likes }}</span
            >点赞</span
          >
          <span @click="clickLikeOrCollect('collect', $event)">
            <i class="fas fa-star" :class="{ 'text-yellow-600': iscollect }"> </i
            ><span class="p-1"> {{ collect }}</span
            >收藏</span
          >
        </div>
        <div class="intro">
          <p>{{ forum?.intro }}</p>
        </div>
      </div>
      <!-- 底部分类 -->
      <div class="sort">
        <!-- 分类 -->
        <span @click="onButtonClick(forum?.sort!,$event)"
          ><i class="fas fa-tags pr-1 text-violet-400"></i
          >{{ forum?.sort?.sortName }}</span
        >
        <!-- 作者信息 -->
        <div class="text-teal-400">
          作者：<span class="text-violet-400 text-sm"> {{ forum?.user?.nickname }}</span>
        </div>
      </div>
    </div>
    <!-- 大图片 -->
    <div class="forum_img" @click="toArticle(forum!)">
      <img :src="forum?.img" alt="" />
    </div>
  </div>
</template>

<style scoped lang="scss">
.deleteIcon {
  @apply absolute  cursor-pointer   duration-200;
}
.leftOrRight {
  @apply col-start-2 row-start-1;
}

.box-card {
  @apply w-full h-full   rounded-md  bg-gray-100 grid grid-rows-1 grid-cols-2  shadow-xl cursor-pointer relative hover:shadow-2xl duration-300;

  .forum_container {
    @apply flex flex-col w-full h-full justify-between p-3;
    .time {
      @apply text-sm flex;
    }
    .article {
      @apply w-full flex flex-col h-full pt-2;
      .title {
        @apply pt-2   text-2xl text-center;
      }
      .meta {
        @apply p-4  relative flex text-xs justify-evenly items-center;
      }
      .intro {
      }
    }

    .sort {
      @apply flex justify-between items-center;
      & > span {
        @apply flex items-center border-2 px-3 text-sm hover:bg-sky-600 duration-500 bg-slate-300;
      }
    }
  }
  .forum_img {
    @apply flex justify-center w-full h-full items-center rounded-l-md overflow-hidden;
    img {
      @apply object-fill w-full h-full hover:scale-125 duration-500 overflow-hidden;
    }
  }
}

.textHover {
  @apply hover:text-sm hover:relative;
}
</style>
