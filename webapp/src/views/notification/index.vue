<script lang="ts" setup>
import { addFriendRequest } from "@/api/messageList";
import {
  delFriendNotificationRequest,
  getAddFriendRequest,
  getAdminNotificationRequest,
} from "@/api/notification";
import { useUserStore } from "@/store/userStore";
import { NotificationData, UserData } from "@/type/websiteDataType";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
// import { Dayjs } from "dayjs";
// import { NotificationData } from "@/type/websiteDataType";
// const value = ref<Dayjs>();
// const onPanelChange = (value: Dayjs, mode: string) => {
//   console.log(value, mode);
// };
const userStore = useUserStore();
const router = useRouter();
const addFriendArr = ref<UserData[]>([]);
const adminNotificationArr = ref<NotificationData[]>([]);

// 渲染前
onMounted(async () => {
  await getAddFriendArr();
  await getAdminNotificationArr();
});
// 获得管理员通知
const getAddFriendArr = async () => {
  const result = await getAddFriendRequest(userStore.id);
  addFriendArr.value = result.data;
  console.log(addFriendArr.value);
};
// 获得用户申请列表
const getAdminNotificationArr = async () => {
  const result = await getAdminNotificationRequest();
  adminNotificationArr.value = result.data;
  console.log(adminNotificationArr.value);
};
// 点击用户头像
const toUserHome = (path: string, id: number | string) => {
  router.push({ path, query: { id } });
};
// 点击同意好友请求
const acceptFriend = async (user: UserData) => {
  const result2 = await delFriendNotificationRequest(userStore.id, user.id);
  const result = await addFriendRequest(userStore.id, user.id);
  if (result.code == 200 && result2.code == 200) {
    let index = addFriendArr.value.findIndex((item) => {
      return item.id == user.id;
    });
    addFriendArr.value.splice(index, 1);
  }
};
// 拒绝 申请
const rejectFriend = async (user: UserData) => {
  const result2 = await delFriendNotificationRequest(userStore.id, user.id);
  if (result2.code == 200) {
    let index = addFriendArr.value.findIndex((item) => {
      return item.id == user.id;
    });
    addFriendArr.value.splice(index, 1);
  }
};
</script>

<template>
  <div class="notification">
    <div class="top">
      <div class="block text-center w-[1000px]">
        <h1 class="text-2xl">通知</h1>
        <el-carousel :interval="2000" type="card">
          <template v-for="item in adminNotificationArr">
            <el-carousel-item v-if="item.isLook">
              <h3
                text="2xl"
                class="w-full h-full bg-slate-400 flex justify-center items-center"
              >
                {{ item.content }}
              </h3>
            </el-carousel-item>
          </template>
        </el-carousel>
      </div>
    </div>
    <div class="bottom">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span class="text-2xl">新朋友申请</span>
            <el-button
              class="button"
              type="info"
              icon="Refresh"
              @click="getAddFriendArr"
              circle
            />
          </div>
        </template>
        <div class="card_body">
          <div class="body_main" v-for="user in addFriendArr" :key="user.id">
            <section class="user_info" @click="toUserHome(user.homeLink!,user.id)">
              <img :src="user.avatar" class="h-10 rounded-full mr-2" alt="" />
              <span>{{ user.nickname }}</span>
            </section>
            <div>
              <el-button type="primary" size="default" @click="acceptFriend(user)"
                >同意</el-button
              >
              <el-button type="danger" size="default" @click="rejectFriend(user)"
                >拒绝</el-button
              >
            </div>
          </div>
        </div>
      </el-card>
      <!-- <div :style="{ width: '400px', border: '1px solid #d9d9d9', borderRadius: '4px' }">
        <a-calendar
          v-model:value="value"
          :fullscreen="false"
          @panelChange="onPanelChange"
        />
      </div> -->
    </div>
  </div>
</template>

<style lang="scss" scoped>
.notification {
  @apply px-2;
  .top {
    @apply h-[400px] flex justify-center items-center bg-slate-200;
  }
  .bottom {
    @apply h-full w-full mt-2 flex justify-between;
    .box-card {
      width: 480px;
      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .card_body {
        @apply flex flex-col gap-2 h-[200px] overflow-y-auto p-2;
        .body_main {
          @apply bg-slate-300 flex justify-between items-center h-10 cursor-pointer;
          .user_info {
            @apply bg-slate-50 h-full;
          }
        }
      }
    }
  }
}
</style>
