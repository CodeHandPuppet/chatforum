<script setup lang="ts">
import { ForumData, SortData } from "@/type/websiteDataType";
import { computed, onMounted, reactive, ref, watch } from "vue";
import type { UploadProps } from "element-plus";
import { ElMessage } from "element-plus";
import { useRoute, useRouter } from "vue-router";
import {
  editArticleRequest,
  getOneArticleRequest,
  saveArticleRequest,
} from "@/api/forum";
import { SaveOrDeleteResponseData, VisForum } from "@/api/type";
import { getAllSortRequest } from "@/api/sort";
import { useUserStore } from "@/store/userStore";
const userStore = useUserStore();
const forumId = ref();
let forumParams = ref<VisForum>({
  title: "",
  content: "",
  intro: "",
  sortId: 0,
  img: "",
});
// sort
const checkedSortParamas = ref<number[]>([]);
const sorts = ref<SortData[]>([]);
// 组件加载渲染前
onMounted(async () => {
  forumId.value = useRoute().query.id as string;
  // 判断是不是 编辑跳转还是  添加跳转
  if (forumId.value) {
    const result = await getOneArticleRequest(forumId.value);

    forumParams.value = result.data as VisForum;
    checkedSortParamas.value.push(forumParams.value.sortId as number);
  } else {
    // 发送文章为本人的id
    forumParams.value.uid = userStore.id;
    console.log(userStore.address);
  }
  // sorts 请求
  const sortResult = await getAllSortRequest();
  sorts.value = sortResult.data;
});

// 图片发送成功后做出的响应
const handleAvatarSuccess: UploadProps["onSuccess"] = (response, uploadFile) => {
  console.log(response);

  forumParams.value.img = response.data;
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
// 保存发送文章
const toAddOrSave = async () => {
  let result: SaveOrDeleteResponseData;
  const options = forumId ? "保存" : "发送";
  if (forumId.value) {
    result = await editArticleRequest(forumParams.value);
  } else {
    console.log(forumParams);
    result = await saveArticleRequest(forumParams.value);
  }
  if (result.code == 200) {
    ElMessage.success(options + "成功");
    window.history.go(-1);
  } else {
    ElMessage.error(options + "失败");
  }
  // else 执行 发送
};
watch(checkedSortParamas, () => {
  forumParams.value.sortId = checkedSortParamas.value[0]
    ? checkedSortParamas.value[0]
    : 0;
});
</script>

<template>
  <div class="editArticle">
    <!--  -->

    <!--  -->
    <div class="w-full flex justify-center items-center p-6 text-2xl">
      <h1>{{ !forumParams.id ? "发送文章" : "编辑文章" }}</h1>
    </div>
    <el-form label-width="120px" class="form">
      <!-- content -->
      <el-form-item label="文章内容">
        <div class="mavonEdit">
          <mavon-editor v-model="forumParams.content"></mavon-editor>
        </div>
      </el-form-item>
      <!-- title -->
      <el-form-item label="文章标题">
        <div class="input_text">
          <el-input v-model="forumParams.title" placeholder="请输入文章标题" clearable />
        </div>
      </el-form-item>
      <!-- intro -->
      <el-form-item label="文章简介">
        <div class="input_textarea">
          <el-input v-model="forumParams.intro" placeholder="请输入简介" />
        </div>
      </el-form-item>
      <!-- img -->
      <el-form-item label="上传主题图片">
        <div class="upload">
          <el-upload
            class="avatar-uploader"
            action="http://sph-api.atguigu.cn/admin/product/fileUpload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="forumParams.img" :src="forumParams.img" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </div>
      </el-form-item>
      <!-- sort -->
      <el-form-item label="选择分类">
        <div class="checkBox">
          <el-checkbox-group v-model="checkedSortParamas" :min="0" :max="1">
            <el-checkbox v-for="(sort, index) in sorts" :key="index" :label="sort.id">{{
              sort.sortName
            }}</el-checkbox>
          </el-checkbox-group>
        </div>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item label="提交">
        <el-button type="primary" size="default" @click="toAddOrSave">{{
          forumId ? "保存" : "发送"
        }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped lang="scss">
.editArticle {
  @apply w-full p-4 h-full overflow-auto;
  .form {
    // @apply mt-2;
    .input_text {
      @apply w-60;
    }
    .input_textarea {
      @apply w-64;
    }
    .mavonEdit {
      @apply w-full;
    }
    .checkBox {
    }
    .upload {
      @apply text-2xl;
      .avatar-uploader {
        @apply w-[200px] border h-[200px] flex justify-center items-center;
      }
    }
  }
}
</style>

<script lang="ts">
export default {
  name: "addOrEditArticle",
};
</script>
