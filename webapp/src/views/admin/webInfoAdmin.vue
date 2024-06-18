<script lang="ts" setup>
import { ElMessage, UploadProps } from "element-plus";
import { computed, ref } from "vue";
const url = ref(
  "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"
);
const srcList = ref([
  "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
  "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
  "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
  "https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg",
  "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
  "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
  "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
]);
const clickImgBox = (value: string) => {
  url.value = value;
};
const handleAvatarSuccess: UploadProps["onSuccess"] = (response, uploadFile) => {
  console.log(response);
  srcList.value.push(response.data);
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

const valueEng = ref("forumBg");

const options = [
  {
    value: "forumBg",
    label: "论坛背景",
  },
  {
    value: "userHomeBg",
    label: "用户主页背景",
  },
  {
    value: "userHomeLoadingImg",
    label: "用户主页无内容图片",
  },
  {
    value: "errorImg",
    label: "404错误页面",
  },
  {
    value: "userAvatar",
    label: "用户默认头像",
  },
];
</script>

<template>
  <!-- <el-select v-model="valueEng" class="m-2" placeholder="Select" size="large">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select> -->
  <div class="webInfoAdmin">
    <div class="top"></div>

    <div class="adminImg" v-for="item in options">
      <div class="title">
        <span>{{ item.label }}</span>
      </div>
      <div class="img_list">
        <div
          class="imgBox"
          :class="{ active: url == item }"
          v-for="item in srcList"
          @click="clickImgBox(item)"
        >
          <img :src="item" alt="" />
        </div>

        <div class="upload">
          <el-upload
            class="avatar-uploader"
            action="http://sph-api.atguigu.cn/admin/product/fileUpload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="false" :src="'x'" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.webInfoAdmin {
  @apply w-full h-full overflow-y-auto;
  .top {
  }
  .adminImg {
    @apply w-full;
    .title {
      @apply text-2xl py-3 text-lime-600;
    }
    .img_list {
      @apply flex gap-4  border-2 p-4 w-full h-full flex-wrap;
      .imgBox {
        @apply w-[200px] flex items-center justify-center h-[200px] object-cover relative cursor-pointer;
        img {
          @apply h-full w-full object-cover;
        }
      }

      .upload {
        @apply text-2xl;
        .avatar-uploader {
          @apply w-[200px] border h-[200px] flex justify-center items-center;
        }
      }
    }
  }
}

.active::before {
  content: "✅";
  @apply absolute -right-1 top-0;
}
</style>
