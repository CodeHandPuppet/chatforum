<script setup lang="ts">
import { useUserStore } from "@/store/userStore";
import { UserData, UserMessageData } from "@/type/websiteDataType";
import { newTimeDate } from "@/utils/time";
import { reactive, ref } from "vue";
import { sendMessageRequest } from "@/api/messageList";
import { message } from "ant-design-vue";
import { useRouter } from "vue-router";
const props = defineProps<{
  messageInfo: {
    baseInfo: UserData; //  friendInfo
    content: UserMessageData[] | null;
  };
}>();
const router = useRouter();
const userStore = useUserStore();
let inputParmas = reactive<UserMessageData>({
  content: "",
  sendTime: "",
  receiverId: props.messageInfo.baseInfo.id as string,
  uid: userStore.id as string,
});
// 填入对象
inputParmas.receiverId = props.messageInfo.baseInfo.id as string;
const sendInputMessage = async () => {
  if (!inputParmas.content) return;
  inputParmas.sendTime = newTimeDate();
  // 做请求
  const result = await sendMessageRequest(inputParmas);
  if (result.code == 200) {
    props.messageInfo.content?.push(JSON.parse(JSON.stringify(inputParmas)));
    inputParmas.content = "";
  }

  // 清除输入台
};
const handleKeyCode = (e: any) => {
  if (e.ctrlKey && e.keyCode == 13) {
    //用户点击了ctrl+enter触发
    inputParmas.content += "\n";
  } else {
    //用户点击了enter触发
    // 执行一些逻辑方法
    if (e != undefined) {
      e.preventDefault(); // 阻止浏览器默认的敲击回车换行的方法
      sendInputMessage();
    }
  }
};
const toChatList = (id: any) => {
  router.push({ path: "/chatList", query: { id } });
};
</script>

<template>
  <div class="chatScreen">
    <div class="top">
      <div>{{ messageInfo.baseInfo?.nickname }}</div>

      <div>
        <img
          :src="messageInfo.baseInfo.avatar"
          class="cursor-pointer w-10 hover:scale-95 duration-200 rounded-full"
          @click="toChatList(messageInfo.baseInfo.id)"
        />
      </div>
    </div>
    <div class="bottom">
      <div class="chatField">
        <div
          class="messageBox"
          :class="{ 'flex-row-reverse': item.uid == userStore.id }"
          v-for="item in messageInfo.content"
        >
          <div class="imgBox">
            <img
              :src="
                item.uid == userStore.id ? userStore.avatar : messageInfo.baseInfo.avatar
              "
              alt=""
            />
          </div>
          <div class="message" :class="{ 'items-end': item.uid == userStore.id }">
            <section>
              {{
                item.uid == userStore.id
                  ? userStore.nickname
                  : messageInfo.baseInfo.nickname
              }}
            </section>
            <span>{{ item.content }}</span>
          </div>
        </div>
      </div>
      <div class="inputField">
        <div>
          <textarea
            v-model="inputParmas.content"
            @keydown.enter.native="handleKeyCode($event)"
            placeholder="ctrl+enter换行"
          ></textarea>
        </div>
        <div class="inputConfirm">
          <el-button type="primary" size="default" @click="sendInputMessage"
            >发送</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.chatScreen {
  @apply w-full h-screen;
  .top {
    @apply w-full h-[60px] border-b  border-gray-200 flex justify-between;
    div {
      @apply flex p-3  items-center;
    }
  }
  .bottom {
    @apply bg-gray-100  grid grid-rows-[auto_150px];
    height: calc(100% - 60px);
    .chatField {
      @apply overflow-y-auto p-2;
      .messageBox {
        @apply m-3  flex gap-1;
        .imgBox {
          img {
            @apply rounded-full w-[50px];
          }
        }

        .message {
          @apply text-sm flex flex-col gap-1;
          section {
          }
          span {
            @apply text-base max-w-[320px] bg-white border p-2 rounded-md;
          }
        }
      }
    }
    .inputField {
      @apply p-3  border-t border-gray-200;
      textarea {
        @apply w-full h-[85px] resize-none outline-none p-1 bg-gray-100;
      }
      .inputConfirm {
        @apply flex w-full justify-end mt-1;
      }
    }
  }
}
</style>
