<script setup lang="ts">
import { useUserStore } from "@/store/userStore";
import menuVue from "./menu.vue";
import { useRouter } from "vue-router";
import { editUserPassword, editUserRequest, getAllUsersInfoRequest } from "@/api/user";
import { onMounted, reactive, ref } from "vue";
import { AddressData, UserData } from "@/type/websiteDataType";
import { getAllAddressRequest } from "@/api/address";
import { ElMessage, UploadProps } from "element-plus";

//
const oldPassword = ref("");
const newPassword = ref("");

// 提交延迟加载
const submitLoading = ref(false);
const router = useRouter();
// 抽屉
const drawer = ref(false);
// 对话框
const dialogVisible = ref(false);
let userParams = reactive<UserData>({
  address: "",
  avatar: "",
  id: "",
  nickname: "",
  signature: "",
  type: "",
  username: "",
  level: 1,
});
const userStore = useUserStore();
const loginOut = () => {
  userStore.userloginOut();
  window.location.reload();
};

const toUserHome = () => {
  router.push({ name: "UserHome", query: { id: userStore.id } });
};
// 点击修改自己信息
const clickEditMyselfInfo = () => {
  // const result = await getAddressArr();
  userParams.address = userStore.address;
  userParams.avatar = userStore.avatar;
  userParams.nickname = userStore.nickname;
  userParams.signature = userStore.signature;
  userParams.username = userStore.username;
  userParams.type = userStore.type;
  userParams.password = "";
  userParams.id = userStore.id;
  drawer.value = true;
};
// 提交自己信息
const formSubmit = async () => {
  submitLoading.value = true;
  let result = await editUserRequest(userParams);
  drawer.value = false;
  submitLoading.value = false;
  window.location.reload();
};
// 点击编辑
const clickEditPassword = () => {
  oldPassword.value = "";
  newPassword.value = "";
  dialogVisible.value = true;
};
const toEditPassword = async () => {
  const result = await editUserPassword(newPassword.value, oldPassword.value);
  if (result.code == 200) {
    dialogVisible.value = false;
    // 修改成功后退出登录
    loginOut();
  } else {
    oldPassword.value = "";
    ElMessage.error(result.data);
  }
};

// 图片发送成功后做出的响应
const handleAvatarSuccess: UploadProps["onSuccess"] = (response, uploadFile) => {
  console.log(response);

  userParams.avatar = response.data;
};
// 图片发送前的判断
const beforeAvatarUpload: UploadProps["beforeUpload"] = (rawFile) => {
  if (rawFile.type !== "image/jpeg") {
    ElMessage.error("Avatar picture must be JPG format!");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("Avatar picture size can not exceed 2MB!");
    return false;
  }
  return true;
};
</script>

<template>
  <div class="navigation">
    <div class="top">
      <div class="nav-userInfo" @click="toUserHome">
        <img
          :src="userStore.avatar"
          class="cursor-pointer w-12 h-12 rounded-full"
          alt=""
        />
      </div>
      <div class="nav-main">
        <menuVue></menuVue>
      </div>
    </div>

    <div class="bottom">
      <el-popover placement="right" title="设置" trigger="hover">
        <template #reference>
          <el-button type="primary" icon="Setting" circle size="large"></el-button>
        </template>
        <div class="flex flex-col gap-2 h-32 space-between">
          <button size="default" class="btn" @click="clickEditMyselfInfo">
            编辑个人信息
          </button>
          <button size="default" class="btn" @click="clickEditPassword">修改密码</button>
          <button size="default" class="btn" @click="loginOut">退出登录</button>
        </div>
      </el-popover>
    </div>

    <el-drawer v-model="drawer" title="编辑个人信息" destroy-on-close>
      <el-form class="w-[300px]" label-width="60px">
        <el-form-item label="上传头像">
          <div class="upload">
            <el-upload
              class="avatar-uploader"
              action="http://sph-api.atguigu.cn/admin/product/fileUpload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="userParams.avatar" :src="userParams.avatar" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </div>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="userParams.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称"
          ><el-input v-model="userParams.nickname"></el-input
        ></el-form-item>
        <el-form-item label="签名"
          ><el-input v-model="userParams.signature"></el-input
        ></el-form-item>
        <el-form-item label="地址">
          <el-input v-model="userParams.address" disabled clearable />
        </el-form-item>
        <el-form-item label="确认">
          <el-button
            type="primary"
            size="default"
            :loading="submitLoading"
            @click="formSubmit"
            >提交</el-button
          >
        </el-form-item>
      </el-form>
    </el-drawer>

    <el-dialog
      v-model="dialogVisible"
      destroy-on-close
      title="修改密码"
      width="400px"
      draggable
    >
      <el-form>
        <el-form-item label="原密码">
          <!-- <el-col :span="120"> -->
          <el-input v-model="oldPassword" clearable />
          <!-- </el-col> -->
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="newPassword" clearable />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="toEditPassword"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">
.navigation {
  @apply h-full w-full flex flex-col justify-between pt-5 pb-5;
  .top {
    .nav-userInfo {
      @apply flex justify-center mb-2 hover:scale-110 duration-300;
    }
  }
  .bottom {
    @apply flex justify-center;
  }
}
.btn {
  @apply border p-2 bg-violet-200 rounded-md hover:bg-sky-300 hover:scale-95 duration-300 shadow-sm;
}

.upload {
  @apply text-2xl;
  .avatar-uploader {
    @apply w-[200px] border h-[200px] flex justify-center items-center;
  }
}
</style>
