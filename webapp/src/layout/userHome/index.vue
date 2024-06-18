<script setup lang="ts">
import { UserData } from "@/type/websiteDataType";
import UserMenuVue from "./userMenu.vue";
import { onMounted, ref } from "vue";
import { getOneUserInfoRequest } from "@/api/user";
import { useRoute, useRouter } from "vue-router";
import { ChatFriends } from "@/api/type";
import { getChatFriendsRequest } from "@/api/chatList";
import { useUserStore } from "@/store/userStore";
import { setLocalSrorage } from "@/utils/localStorage";
import { useMessageListStore } from "@/store/messageListStore";
import { addFriendNotificationRequest, getAddFriendRequest } from "@/api/notification";
const route = useRoute();
const router = useRouter();
const messageStore = useMessageListStore();
const userStore = useUserStore();
const uid = ref(route.query.id as string);
const userInfo = ref<UserData>();
const isFriend = ref(false);
const isSendFrieReq = ref(true);
// 渲染前加载
onMounted(async () => {
  const userResult = await getOneUserInfoRequest(uid.value);
  userInfo.value = userResult.data as UserData;
  const result1 = await getChatFriendsRequest();
  let user = result1.data.friends.find((item) => {
    return item.friendInfo.id == uid.value;
  });
  if (user) {
    isFriend.value = true;
  }
  // 获得用户申请列表
  const f1 = await getAddFriendRequest(userStore.id);
  let index1 = (f1.data as UserData[]).findIndex((user) => {
    return user.id == uid.value;
  });
  const f2 = await getAddFriendRequest(uid.value);
  let index2 = (f2.data as UserData[]).findIndex((user) => {
    return user.id == userStore.id;
  });
  console.log(f1.data);
  console.log(f2.data);
  if (index1 == -1 && index2 == -1) {
    isSendFrieReq.value = false;
  }
});
// 发送消息
const toSendMessage = (user: UserData) => {
  messageStore.messagePush(user);
  let path = "/messageList";
  setLocalSrorage("menuActive", path);
  router.push({ path });
};
// 添加好友
const addFriend = async (user: UserData) => {
  const result = await addFriendNotificationRequest(user.id, userStore.id);
  if (result.code == 200) {
    isSendFrieReq.value = true;
  }
};
</script>

<template>
  <div class="userHome">
    <div class="top">
      <div
        class="userInfo"
        :style="{ backgroundImage: `url('/src/assets/image/bg2.jpg')` }"
      >
        <div class="box">
          <div class="box_left">
            <img :src="`${userInfo?.avatar}`" alt="" />
            <div class="info">
              <section>
                <span class="text-2xl pr-1">{{ userInfo?.nickname }}</span>
                <span class="text-xs">{{ "lv" + userInfo?.level }}</span>
              </section>
              <p class="text-sm">{{ userInfo?.signature }}</p>
            </div>
          </div>
          <div class="box_right">
            <el-button
              type="primary"
              size="default"
              @click="toSendMessage(userInfo!)"
              v-if="isFriend"
              >发消息</el-button
            >
            <el-button
              type="primary"
              size="default"
              @click="addFriend(userInfo!)"
              v-if="!isFriend && uid != userStore.id && !isSendFrieReq"
              >添加好友</el-button
            >
          </div>
        </div>
      </div>
      <div class="menu"><UserMenuVue></UserMenuVue></div>
    </div>
    <div class="bottom">
      <router-view></router-view>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.userHome {
  @apply m-0 w-screen min-h-screen px-28 bg-gray-100 overflow-x-hidden overflow-y-auto;
  .top {
    @apply bg-white w-full h-[25vh] mb-4;
    .userInfo {
      @apply w-full  h-[20vh] bg-cover bg-center flex items-end pb-3;
      .box {
        @apply h-16 w-full items-center flex justify-between px-6;
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
    }
    .menu {
      @apply w-full h-[5vh];
    }
  }
  .bottom {
    @apply bg-white w-full min-h-[30vh];
  }
}
</style>

<script lang="ts">
// 做的是每个用户的 主页
export default {
  name: "userHome",
};
</script>
