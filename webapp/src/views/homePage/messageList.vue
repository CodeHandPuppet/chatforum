<script setup lang="ts">
import { getMemberMessage, getMessageListRequest } from "@/api/messageList";
import { useMessageListStore } from "@/store/messageListStore";
import { useUserStore } from "@/store/userStore";
import { UserData, UserMessageData } from "@/type/websiteDataType";
import { getLocalSrorage, setLocalSrorage } from "@/utils/localStorage";
import { computed, onBeforeUnmount, onMounted, reactive, ref } from "vue";
const userStore = useUserStore();
// 左侧数组
const messageStore = useMessageListStore();
// 用户数组
const messageList = ref<UserData[]>([]);
const visible = ref(false);
const selectObj = ref<{ id: number }>({ id: 0 });
const messageInfo = reactive<{
  baseInfo: UserData | null;
  content: UserMessageData[] | null;
}>({ baseInfo: null, content: null });
onMounted(async () => {
  await doPost();
});

const doPost = async () => {
  await requestMessageList();
  let active = getLocalSrorage("active");
  let user = messageList.value.find((item) => {
    return item.id == active;
  });
  if (user) {
    selectObj.value.id = user.id as number;
    messageInfo.baseInfo = user;
    const result = await getMemberMessage(userStore.id, user.id);
    if (result.code == 200) {
      messageInfo.content = result.data;
    }
    visible.value = true;
  }
};
const requestMessageList = async () => {
  const result = await getMessageListRequest();
  messageList.value = result.data.friends;

  messageList.value = [...result.data.friends, ...messageStore.messageList];
};

const getMessageInfo = async (obj: UserData) => {
  selectObj.value.id = obj.id as number;
  visible.value = false;
  messageInfo.baseInfo = obj;
  //
  const result = await getMemberMessage(userStore.id, obj.id);
  if (result.code == 200) {
    messageInfo.content = result.data;
  }
  visible.value = true;
};
</script>

<template>
  <div class="chatList">
    <div class="left">
      <div class="l-top">
        <chatSearch :userArr="messageList" />
      </div>
      <div class="l-bottom">
        <template v-for="item in messageList">
          <section
            @click="getMessageInfo(item)"
            class="chatBox"
            :class="{
              'bg-white': !(selectObj.id == item.id),
              ' bg-sky-200': selectObj.id == item.id,
            }"
          >
            <img :src="item.avatar" alt="" />
            <div class="chatBox-info">
              <span>{{ item.nickname }}</span>
              <span>{{ "好友" }}</span>
            </div>
          </section>
        </template>
      </div>
    </div>
    <div class="right">
      <transition class="animate__animated">
        <chatScreen v-if="visible" :messageInfo="messageInfo" type="1" />
      </transition>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.chatList {
  @apply grid grid-cols-[300px_1fr] h-full w-full;
  .left {
    @apply flex flex-col;
    .l-top {
      @apply w-full h-[60px];
    }
    .l-bottom {
      // 颜色
      @apply flex flex-col  w-full bg-white overflow-y-auto;
      height: calc(100vh - 60px);
      .chatBox {
        @apply grid mb-1 grid-cols-[auto_1fr] gap-1  p-3 w-full h-16 hover:bg-gray-200;

        img {
          @apply h-full w-10 rounded-full;
        }
        .chatBox-info {
          @apply flex  justify-between;
        }
      }
    }
  }
  .right {
    @apply bg-gray-100;
  }
}
</style>
