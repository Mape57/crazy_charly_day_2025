<script setup>
import CardComponent from './CardComponent.vue';
import { defineProps } from 'vue';

const props = defineProps({
  items: {
    type: Array,
    required: true
  }
});
</script>

<template>
  <v-slide-group
      show-arrows
      class="pa-4"
  >
    <v-slide-group-item
        v-for="(item, i) in props.items"
        :key="i"
    >
      <CardComponent
          :title="item.title"
          :subtitle="item.subtitle"
          :source="item.source"
          max="344"
          link="true"
      ></CardComponent>
    </v-slide-group-item>
  </v-slide-group>
</template>

<style scoped>
</style>
