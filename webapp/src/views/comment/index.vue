<script setup lang="ts">
import {
  CommentApi,
  ConfigApi,
  useLevel,
  SubmitParamApi,
  UToast,
  CommentInstance,
  throttle,
} from "undraw-ui";
import Operate from "./operate.vue";
import emoji from "@/utils/emoji";
import { onMounted, reactive, ref } from "vue";
import { useUserStore } from "@/store/userStore";
import { newTimeDate } from "@/utils/time";
import { CommentData } from "@/type/websiteDataType";
import {
  CommentDeleteRequest,
  CommentSaveRequest,
  addCommentLikesRequest,
  getCommentsRequest,
  getLikesRequest,
  removeCommentLikeRequest,
} from "@/api/comment";
import { buildCommentTree } from "@/utils/commentTree";
import { getOneUserInfoRequest } from "@/api/user";
import { ElMessage } from "element-plus";
const props = defineProps({
  forumId: { type: String },
});
const userStore = useUserStore();

const commentParams = reactive<CommentData>({
  uid: userStore.id,
  content: "",
  forumId: props.forumId!,
  parentId: "",
});

// 导入评论区配置
const config = reactive<ConfigApi>({
  user: {
    id: userStore.id,
    username: userStore.nickname,
    avatar: userStore.avatar,
    // 评论id数组 建议:存储方式用户uid和评论id组成关系,根据用户uid来获取对应点赞评论id,然后加入到数组中返回
    likeIds: [1, 2, 3], //  评论点赞里面找
  },
  emoji: emoji, //通用的emoji 不用更改
  comments: [], //在下面显示的评论    需要获取
  total: 10,
});

onMounted(async () => {
  const result = await getCommentsRequest(props.forumId as string);
  const like = await getLikesRequest(userStore.id);
  config.comments = buildCommentTree(result.data);
  // config.comments = result.data;
  config.user.likeIds = like.data.likeIds;
});

// 用户信息是否加载
const loading = ref(false);
// 请求获取用户详细信息
const showInfo = async (uid: string, finish: Function) => {
  loading.value = true;
  // 获取请求  得到 showUserInfo
  const result = await getOneUserInfoRequest(uid);
  let userInfo;
  let temp = result.data;
  setTimeout(() => {
    userInfo = {
      id: String(uid),
      username: temp.nickname,
      avatar: temp.avatar,
      level: temp.level,
      likes: temp.likes,
      collect: temp.collect,
      forumNumber: temp.forumNumber,
      homeLink: temp.homeLink,
    };
    loading.value = false;
    finish(userInfo);
  }, 200);
};

// 提交评论事件
const submit = async ({ content, parentId, files, finish }: SubmitParamApi) => {
  // 配置好请求参数
  commentParams.content = content;
  commentParams.parentId = parentId ? (parentId as string) : null;

  // 做请求 保存 评论后获取请求回执 id
  const result = await CommentSaveRequest(commentParams);
  let commentId = result.data.id;
  //

  // id
  const comment: CommentApi = {
    id: String(commentId), //  评论的id
    parentId: commentParams.parentId, //  获得的父亲评论的id
    uid: commentParams.uid, //   用户id
    address: userStore.address, //
    content: commentParams.content,
    likes: 0,
    createTime: newTimeDate(), //
    user: {
      username: userStore.nickname,
      avatar: config.user.avatar,
      level: userStore.level as number,
      homeLink: `/userHome?id=${userStore.id}`,
    },
  };
  setTimeout(() => {
    finish(comment);
    UToast({ message: "评论成功!", type: "info" });
  }, 200);
};
// 点赞按钮事件 将评论id返回后端判断是否点赞，然后在处理点赞状态
const like = async (id: string, finish: () => void) => {
  const index = config.user.likeIds.findIndex((x) => {
    return x == id;
  });
  if (index != -1) {
    await removeCommentLikeRequest(userStore.id, id);
  } else {
    await addCommentLikesRequest(userStore.id, id);
  }
  config.user.likeIds.splice;
  setTimeout(() => {
    finish();
  }, 200);
};

const commentRef = ref<CommentInstance>();
// 删除评论
const remove = async (comment: CommentApi) => {
  const result = await CommentDeleteRequest(comment.id, comment.parentId);
  if (result.code == 200) {
    ElMessage.success("删除成功");
    setTimeout(() => {
      commentRef.value?.remove(comment);
    }, 200);
  } else {
    ElMessage.error("删除失败");
  }
};

const _throttle = throttle((type: string, comment: CommentApi, finish: Function) => {
  switch (type) {
    case "删除":
      finish();
      break;
  }
});

const operate = (type: string, comment: CommentApi, finish: Function) => {
  _throttle(type, comment, finish);
};
</script>

<template>
  <div class="text-left overflow-auto">
    <u-comment
      :config="config"
      @submit="submit"
      @like="like"
      @operate="operate"
      ref="commentRef"
      @show-info="showInfo"
      aTarget="_self"
      relative-time
    >
      <!-- <template #info="scope">{{ scope }}</template> -->
      <template #card="scope">
        <div class="user-card">
          <div class="user-avatar">
            <el-avatar style="margin-top: 5px" :size="40" fit="cover" :src="scope.avatar">
              <img
                src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
              />
            </el-avatar>
          </div>
          <div class="user-content">
            <div class="user-info">
              <a href="" class="username" target="_blank">
                <span class="name" style="max-width: 10em">{{ scope.username }}</span>
                <span blank="true" class="rank">
                  <u-icon size="24" v-html="useLevel(scope.level)"></u-icon>
                </span>
              </a>
            </div>
            <div class="social-info">
              <a href="" class="attention">
                <span>{{ scope.forumNumber }}</span>
                <span>文章</span>
              </a>
              <a href="" class="follower">
                <span>{{ scope.collect }}</span>
                <span>收藏</span>
              </a>
              <a href="" class="like">
                <span>{{ scope.likes }}</span>
                <span>获赞</span>
              </a>
            </div>
            <div class="card-btn">
              <el-button type="primary">主页</el-button>
              <el-button>发消息</el-button>
            </div>
          </div>
        </div>
      </template>
      <template #operate="scope">
        <Operate :comment="scope" @remove="remove" />
      </template>
    </u-comment>
  </div>
</template>

<style lang="scss" scoped>
.user-card {
  display: flex;
  .user-content {
    flex: 1;
    margin-left: 16px;
    .user-info {
      .username {
        display: flex;
        align-items: center;
        text-decoration: none;
        .name {
          max-width: 10rem;
          font-weight: 500;
          font-size: 15px;
          color: #252933;
          line-height: 32px;
          margin-right: 4px;
        }
      }
    }
    .social-info {
      font-size: 12px;
      margin-bottom: 10px;
      a {
        text-decoration: none;
      }
      a:not(:first-child) {
        margin-left: 18px;
      }
      a span:last-child {
        margin-left: 3px;
        color: #9499a0;
      }
    }
  }
}
</style>
