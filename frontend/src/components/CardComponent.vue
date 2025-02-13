<script setup>

import {onMounted} from "vue";

const props = defineProps({
  title: String,
  subtitle: String,
  source: String,
  max: String,
  link: String,
})

onMounted(() => {
})
</script>

<template>
    <v-card
        class="card"
        :max-width="max"
        link
    >
        <img
            :src="source"
            class="card-img"></img>
        <v-card-title>{{ title }}</v-card-title>
        <v-card-subtitle>{{ subtitle }}</v-card-subtitle>
    </v-card>
</template>

<style scoped>

.card-img {
    border-radius: 8px;
    object-fit: cover;
    height: 15em;
    width: 100%;
}

.card {
    margin: 1em;
    padding: 0.8em;
    border-radius: 10px;
    box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.1);
    transition: 0.3s;
    border: 1px solid #C5C5C5;
    padding-bottom: 1rem;
}


</style>
