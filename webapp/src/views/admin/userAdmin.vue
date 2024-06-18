<script setup lang="ts">
import { getAllAddressRequest } from "@/api/address";
import { addUserRequest, editUserRequest, getAllUsersInfoRequest } from "@/api/user";
import { useUserStore } from "@/store/userStore";
import { AddressData, UserData } from "@/type/websiteDataType";
import { ElMessage, UploadProps } from "element-plus";
import { computed, onMounted, reactive, ref } from "vue";
const userArr = ref<UserData[]>([]);

const getUserArr = async () => {
  // 获取全部用户请求
  const result = await getAllUsersInfoRequest();
  userArr.value = result.data;
};
const userStore = useUserStore();
onMounted(async () => {
  await getUserArr();
  await getAddressArr();
});
const addressArr = ref<AddressData[]>([]);
const getAddressArr = async () => {
  const result = await getAllAddressRequest();
  addressArr.value = result.data;
};
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
const drawer = ref(false);
const search = ref("");
// 提交按钮加载
const submitLoading = ref(false);
const p = ref(true);
// type  的选择
const options = [{ value: "admin" }, { value: "user" }];
// 过滤器
const filterTableData = computed(() =>
  userArr.value.filter(
    (data) =>
      !search.value || data.nickname!.toLowerCase().includes(search.value.toLowerCase())
  )
);
const addUser = () => {
  userParams.username = "";
  userParams.nickname = "";
  userParams.id = "";
  userParams.signature = "";
  userParams.address = "";
  userParams.type = "";
  userParams.level = 1;
  userParams.avatar = "";
  userParams.password = "";
  drawer.value = true;
};
const editUser = (row: UserData) => {
  userParams.username = row.username;
  userParams.nickname = row.nickname;
  userParams.id = row.id;
  userParams.signature = row.signature;
  userParams.address = row.address;
  userParams.type = row.type;
  userParams.level = row.level;
  userParams.avatar = row.avatar;
  userParams.password = row.password;
  drawer.value = true;
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
const formSubmit = async () => {
  submitLoading.value = true;
  let result;
  if (userParams.id) {
    // 编辑请求
    result = await editUserRequest(userParams);
  } else {
    // add请求
    result = await addUserRequest(userParams);
  }
  if (result.code == 200) {
    await getUserArr();
  }
  drawer.value = false;
  submitLoading.value = false;
};
const changeSelect = async (row: any) => {
  const result = await editUserRequest(row);
};
</script>

<template>
  <div class="userAdmin">
    <div class="userAdmin_top">
      <div user_top_add>
        <el-button type="primary" size="default" @click="addUser"
          >添加用户<span class="text-xl">+</span></el-button
        >
      </div>
      <div class="user_top_input">
        <span>昵称搜索：</span>
        <div>
          <el-input v-model="search" placeholder="请输入昵称" />
        </div>
      </div>
    </div>
    <div class="userAdmin_bottom">
      <el-table :data="filterTableData">
        <!-- 编号 -->
        <el-table-column type="index" label="编号" width="120px" />
        <!-- 登录号 -->
        <el-table-column prop="username" label="登录号" />
        <!-- 密码 -->
        <!-- <el-table-column prop="password" label="密码" /> -->
        <!-- 昵称 -->
        <el-table-column prop="nickname" label="昵称" />
        <!-- 等级 -->
        <el-table-column prop="level" label="等级" />
        <!-- 头像 -->
        <el-table-column prop="avatar" label="头像">
          <template #default="{ row }">
            <img :src="row.avatar" style="width: 60px" alt="" />
          </template>
        </el-table-column>
        <!-- 签名 -->
        <el-table-column prop="signature" label="签名" />
        <!-- 所在学院 -->
        <el-table-column prop="address" label="所在学院" />
        <!-- 账号类型 -->
        <el-table-column prop="type" label="账号类型">
          <template #default="{ row }">
            <div class="w-[120px]" v-if="userStore.id != row.id">
              <el-select
                v-model="row.type"
                @change="changeSelect(row)"
                class="m-2"
                placeholder="Select"
              >
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :value="item.value"
                />
              </el-select>
            </div>
          </template>
        </el-table-column>
        <!-- 拉黑 -->
        <!-- <el-table-column label="启用">
          <template #default="{ row }">
            <el-switch v-model="p" size="large" active-text="是" inactive-text="否" />
          </template>
        </el-table-column> -->
        <!-- 创建时间 -->
        <!-- <el-table-column prop="create_time" sortable label="创建时间" align="center" /> -->
        <!-- 操作 -->
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button
              type="primary"
              size="default"
              v-if="userStore.id != row.id"
              @click="editUser(row)"
              >编辑</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 抽屉 -->
    <el-drawer v-model="drawer" :title="`${userParams.id ? '编辑用户' : '添加用户'}`">
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
        <el-form-item label="密码"
          ><el-input type="password" v-model="userParams.password"></el-input
        ></el-form-item>
        <el-form-item label="签名"
          ><el-input v-model="userParams.signature"></el-input
        ></el-form-item>
        <el-form-item label="地址">
          <el-select v-model="userParams.address" class="m-2" placeholder="Select">
            <template v-for="item in addressArr">
              <el-option :value="item.name" v-if="item.isLook" />
            </template>
          </el-select>
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
  </div>
</template>

<style scoped lang="scss">
.userAdmin {
  @apply pl-4 pt-5 h-full w-full overflow-y-auto;

  .userAdmin_top {
    @apply flex w-full mb-5 justify-between pr-10;
    .user_top_input {
      width: 300px;
      @apply flex justify-center items-center;
    }
  }
}

.upload {
  @apply text-2xl;
  .avatar-uploader {
    @apply w-[200px] border h-[200px] flex justify-center items-center;
  }
}
</style>
