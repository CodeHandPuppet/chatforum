<script setup lang="ts">
import { ref, defineProps } from "vue";

const props = defineProps({
  modelValue: {
    type: String,
    required: true,
  },
});

const innerValue = ref(props.modelValue);

const editable = false;
const toolbars = {};
</script>

<template>
  <div class="articleBody">
    <mavon-editor
      class="mavon"
      :subfield="false"
      :toolbars="toolbars"
      :defaultOpen="'preview'"
      :toolbarsFlag="false"
      :ishljs="false"
      v-model="innerValue"
    />
  </div>
</template>

<style scoped>
.mavon {
  @apply text-base font-light;
  /* font-family: "凤舞体"; */
}
</style>
