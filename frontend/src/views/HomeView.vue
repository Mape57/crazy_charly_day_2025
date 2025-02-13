<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import { ref } from "vue";

const title = ref('Vous avez un besoin ?');
const icons = ref([
  { name: 'mdi-menu', size: 'x-large', color: '#868686' },
  { name: 'mdi-account-circle', size: 'x-large', class: 'ml-3' }
]);

const logo = ref('Crazy');
</script>

<template>
  <HeaderComponent :title="title" :logo="logo">
    <template #icons>
      <v-icon
          v-for="(icon, index) in icons"
          :key="index"
          :size="icon.size"
          :color="icon.color"
          :class="icon.class"
      >
        {{ icon.name }}
      </v-icon>
    </template>
  </HeaderComponent>
</template>

<style scoped>
</style>
