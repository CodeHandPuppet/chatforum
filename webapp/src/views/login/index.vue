<script setup lang="ts">
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { onMounted, ref } from "vue";
import { useUserStore } from "@/store/userStore";
import { AddressData, UserData } from "@/type/websiteDataType";
import { getAllAddressRequest } from "@/api/address";
import { addUserRequest } from "@/api/user";

onMounted(async () => {
  await getAddressArr();
});
const isLogin = ref(true);

const username = ref("xiaobei");
const password = ref("koko");
const loading = ref(false);
const router = useRouter();
const userStore = useUserStore();
const addressArr = ref<AddressData[]>([]);
const userParams = ref<UserData>({
  id: "",
  address: "",
  avatar: "/src/assets/image/avatar_12.jpg",
  signature: "这个人很懒没有签名！",
  type: "user",
  username: "xiaobei",
  password: "koko",
  nickname: "",
});
//
const getAddressArr = async () => {
  const result = await getAllAddressRequest();
  addressArr.value = result.data;
};
const toggleLogin = () => {
  userParams.value.username = "";
  userParams.value.password = "";
  userParams.value.nickname = "";
  isLogin.value = !isLogin.value;
};
// 注册
const loginUp = async () => {
  const result = await addUserRequest(userParams.value);
  if (result.code == 200) {
    ElMessage.success("注册成功");
    isLogin.value = true;
  } else {
    ElMessage.error("注册失败");
  }
};

// 登录 操作
const loginIn = async () => {
  loading.value = true;
  const result = await userStore.userLogin(
    userParams.value.username,
    userParams.value.password as string
  );
  if (result) {
    ElMessage.success("欢迎，你好朋友 !");
    router.push("/");
  }
  if (!result) {
    ElMessage.error("登录失败");
  }
  loading.value = false;
};
</script>
<template>
  <div class="main-box">
    <div :class="['container', 'container-register', { 'is-txl': isLogin }]">
      <form>
        <h2 class="title">欢迎 注册</h2>
        <span class="text">输入用户名进行注册</span>
        <input
          class="form__input"
          v-model="userParams.username"
          type="text"
          placeholder="登录名"
        />
        <input
          class="form__input"
          v-model="userParams.nickname"
          type="text"
          placeholder="昵称"
        />
        <!-- <input
          class="form__input"
          type="text"
          v-model="userParams.nickname"
          placeholder="昵称"
        /> -->
        <input
          class="form__input"
          type="text"
          v-model="userParams.password"
          placeholder="密码"
        />
        <div class="">
          选择学院：
          <el-select v-model="userParams.address" class="m-2" placeholder="Select">
            <template v-for="item in addressArr">
              <el-option :value="item.name" v-if="item.isLook" />
            </template>
          </el-select>
        </div>
        <!-- <input class="form__input" type="text" v-model="password" placeholder="" /> -->
        <!-- <input class="form__input" type="text" v-model="password" placeholder="Email" /> -->
        <!-- <input
          class="form__input"
          type="password"
          v-model="password"
          placeholder="Password"
        /> -->
        <div class="primary-btn" @click="loginUp">立即注册</div>
      </form>
    </div>
    <div :class="['container', 'container-login', { 'is-txl is-z200': isLogin }]">
      <form>
        <h2 class="title">欢迎 登录</h2>
        <span class="text">使用登录名进行登录</span>
        <input
          class="form__input"
          type="text"
          v-model="userParams.username"
          placeholder="登录名"
        />
        <input
          class="form__input"
          type="password"
          v-model="userParams.password"
          placeholder="密码"
        />
        <!-- <div class="primary-btn"> -->
        <el-button class="primary-btn" size="default" :loading="loading" @click="loginIn"
          >立即登录</el-button
        >
        <!-- </div> -->
      </form>
    </div>
    <div :class="['switch', { login: isLogin }]">
      <div class="switch__circle"></div>
      <div class="switch__circle switch__circle_top"></div>
      <div class="switch__container">
        <h2>{{ !isLogin ? "你好 朋友 !" : "欢迎 回来 !" }}</h2>
        <p>
          {{
            isLogin
              ? "输入您的个人资料，与我们一起开始旅程"
              : "为了与我们保持联系，请使用您的个人信息登录"
          }}
        </p>
        <div class="primary-btn" @click="toggleLogin">
          {{ isLogin ? "立即注册" : "立即登录" }}
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.main-box {
  position: relative;
  width: 100vw;
  min-width: 1000px;
  min-height: 600px;
  height: 100vh;
  padding: 25px;
  background-color: #ecf0f3;
  box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius: 12px;
  overflow: hidden;
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 600px;
    height: 100%;
    padding: 25px;
    background-color: #ecf0f3;
    transition: all 1.25s;
    form {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      width: 100%;
      height: 100%;
      color: #a0a5a8;
      .title {
        font-size: 34px;
        font-weight: 700;
        line-height: 3;
        color: #181818;
      }
      .text {
        margin-top: 30px;
        margin-bottom: 12px;
      }
      .form__input {
        width: 350px;
        height: 40px;
        margin: 4px 0;
        padding-left: 25px;
        font-size: 13px;
        letter-spacing: 0.15px;
        border: none;
        outline: none;
        // font-family: 'Montserrat', sans-serif;
        background-color: #ecf0f3;
        transition: 0.25s ease;
        border-radius: 8px;
        box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
        &::placeholder {
          color: #a0a5a8;
        }
      }
    }
  }
  .container-register {
    z-index: 100;
    left: calc(100% - 600px);
  }
  .container-login {
    left: calc(100% - 600px);
    z-index: 0;
  }
  .is-txl {
    left: 0;
    transition: 1.25s;
    transform-origin: right;
  }
  .is-z200 {
    z-index: 200;
    transition: 1.25s;
  }
  .switch {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 400px;
    padding: 50px;
    z-index: 200;
    transition: 1.25s;
    background-color: #ecf0f3;
    overflow: hidden;
    box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
    color: #a0a5a8;
    .switch__circle {
      position: absolute;
      width: 500px;
      height: 500px;
      border-radius: 50%;
      background-color: #ecf0f3;
      box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
      bottom: -60%;
      left: -60%;
      transition: 1.25s;
    }
    .switch__circle_top {
      top: -30%;
      left: 60%;
      width: 300px;
      height: 300px;
    }
    .switch__container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      position: absolute;
      width: 400px;
      padding: 50px 55px;
      transition: 1.25s;
      h2 {
        font-size: 34px;
        font-weight: 700;
        line-height: 3;
        color: #181818;
      }
      p {
        font-size: 14px;
        letter-spacing: 0.25px;
        text-align: center;
        line-height: 1.6;
      }
    }
  }
  .login {
    left: calc(100% - 400px);
    .switch__circle {
      left: 0;
    }
  }
  .primary-btn {
    width: 180px;
    height: 50px;
    border-radius: 25px;
    margin-top: 50px;
    text-align: center;
    line-height: 50px;
    font-size: 14px;
    letter-spacing: 2px;
    background-color: #4b70e2;
    color: #f9f9f9;
    cursor: pointer;
    box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
    &:hover {
      box-shadow: 4px 4px 6px 0 rgb(255 255 255 / 50%),
        -4px -4px 6px 0 rgb(116 125 136 / 50%),
        inset -4px -4px 6px 0 rgb(255 255 255 / 20%), inset 4px 4px 6px 0 rgb(0 0 0 / 40%);
    }
  }
}
</style>
