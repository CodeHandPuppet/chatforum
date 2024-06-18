<script setup lang="ts">
import { getChatFriendsRequest } from "@/api/chatList";
import { editFriendAppellationRequest } from "@/api/messageList";
import { ChatFriends } from "@/api/type";
import { getOneUserInfoRequest } from "@/api/user";
import { useMessageListStore } from "@/store/messageListStore";
import { useUserStore } from "@/store/userStore";
import { UserData } from "@/type/websiteDataType";
import { setLocalSrorage } from "@/utils/localStorage";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const chatlist = ref<ChatFriends[]>([]);
const messageStore = useMessageListStore();
// 右边渲染的数据
let userInfo = ref<UserData>();
const appellation = ref();
//
const router = useRouter();
const userStore = useUserStore();
onMounted(async () => {
  await getChatlist();
  // console.log((router.currentRoute.value.query.id = ""));
});

const getChatlist = async () => {
  const result1 = await getChatFriendsRequest();
  chatlist.value = result1.data.friends;
};
const selectChat = async (index: any) => {
  // const result = await getOneUserInfoRequest(index);
  let chat = chatlist.value.find((x) => {
    return x.friendInfo.id == index;
  });
  userInfo.value = chat?.friendInfo;
  appellation.value = chat?.appellation;
};
const toUserHome = (id: any) => {
  router.push({ path: "/userHome", query: { id } });
};
const toSendMessage = (user: UserData) => {
  messageStore.messagePush(user);
  let path = "/messageList";
  router.push({ path });
};
const editAppellation = async (appellation: string, friend_id: number | string) => {
  await editFriendAppellationRequest(userStore.id, friend_id, appellation);
  chatlist.value.forEach((item) => {
    if (item.friendInfo.id == friend_id) {
      item.appellation = appellation;
    }
  });
};
</script>

<template>
  <div class="chatFriends">
    <div class="left">
      <div class="l-top">
        <chatSearch />
      </div>
      <div class="l-bottom">
        <el-menu
          default-active="friend_0"
          class="el-menu-vertical-demo"
          @select="selectChat"
        >
          <!-- <el-sub-menu :index="chat.type" v-for="chat of chatlist"> -->
          <!-- <template #title>
              <span>{{ chat.title }}</span>
            </template> -->
          <template v-for="item of chatlist">
            <el-menu-item :index="`${item.friendInfo.id}`">
              <template #title>
                <div class="temp_box">
                  <div class="temp_img">
                    <img :src="item.friendInfo.avatar" alt="" />
                  </div>
                  <div class="temp_text">
                    {{
                      item.appellation
                        ? `${item.friendInfo.nickname} （${item.appellation}）`
                        : item.friendInfo.nickname
                    }}
                  </div>
                </div>
              </template>
            </el-menu-item>
          </template>

          <!-- </el-sub-menu> -->
        </el-menu>
      </div>
    </div>
    <div class="right">
      <div v-if="userInfo?.id">
        <div class="container">
          <div class="box">
            <div class="box_left">
              <img :src="`${userInfo?.avatar}`" alt="" />
              <div class="info">
                <section>
                  <span class="text-2xl pr-1">{{ userInfo?.nickname }}</span>
                  <span class="text-xs">{{ "lv" + userInfo?.level }}</span>
                </section>
                <p class="text-sm">{{ userInfo?.username }}</p>
              </div>
            </div>
            <div class="box_right">
              <el-button
                type="primary"
                icon="ChatDotRound"
                size="large"
                @click="toSendMessage(userInfo)"
                circle
              />
            </div>
          </div>

          <div class="show_middle">
            <div class="middle_row">
              <span>备注</span>
              <span>
                <input
                  type="text"
                  @change="editAppellation(appellation, userInfo.id)"
                  v-model="appellation"
                />
              </span>
            </div>
            <div class="middle_row">
              <span>所在学院</span>
              <span>
                {{ userInfo?.address }}
              </span>
            </div>

            <div class="middle_row">
              <span>签名</span>
              <span>
                {{ userInfo?.signature }}
              </span>
            </div>
          </div>
          <div class="show_bottom" @click="toUserHome(userInfo?.id)">
            <span class="flex gap-2 items-center">
              <i class="fas fa-user text-blue-400"></i>他的主页</span
            >
            <span
              ><el-icon c><ArrowRight /></el-icon
            ></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.chatFriends {
  @apply grid grid-cols-[300px_1fr] h-full w-full;
  .left {
    @apply flex flex-col;
    .l-top {
      @apply w-full h-[60px];
    }
    .l-bottom {
      @apply flex flex-col  w-full bg-white overflow-y-auto;
      height: calc(100vh - 60px);
    }
  }
  .right {
    @apply py-10 bg-gray-100 h-full w-full flex justify-center;
    .container {
      @apply w-[650px] h-[600px] border-2 p-14;
      .box {
        @apply h-16 w-full items-center border-b-2 flex justify-between;
        .box_left {
          @apply flex gap-2;
          img {
            @apply w-16 h-16 rounded-full;
          }
          .info {
            @apply flex flex-col justify-between py-2;
          }
        }
        .box_right {
        }
      }
      .show_middle {
        @apply border-b-2 py-3;
        .middle_row {
          @apply flex justify-between mt-5;
        }
      }
      .show_bottom {
        @apply py-3 flex justify-between cursor-pointer;
      }
    }
  }
}

.temp_box {
  @apply flex  w-full justify-between;
  .temp_img {
    @apply flex justify-center  items-center;
    img {
      @apply w-10 rounded-full;
    }
  }
  .temp_text {
  }
}
//替换elemet的样式
.el-menu {
  border: 0;
}
</style>
