<template>
  <header>
    <router-link to="/">
      <h1>{{ logo }}</h1>
    </router-link>
    <router-link :to="profileRoute">
      <div class="icon-container">
        <slot name="icons"></slot>
      </div>
    </router-link>
  </header>
  <div class="title">
    <h1>{{ title }}</h1>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useUserStore } from "@/stores/userStore.js";
defineProps({
  title: String,
  logo: String
});

const userStore = useUserStore();

const profileRoute = computed(() => {
  console.log(userStore.role);
  switch (userStore.role) {
    case 0:
      return '/admin';
    case 1:
      return '/salarie';
    case 2:
      return '/client';
    default:
      return '/';
  }
});
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 1.80em;
}

header h1 {
  margin-left: 1em;
  font-weight: 200;
  color: black;
}

a {
  text-decoration: none;
}

.icon-container {
  cursor: pointer;
  border: 1px solid #868686;
  border-radius: 1.5em;
  padding: 0.3em 0.7em;
  margin-right: 1.5em;
}

.icon-container * {
  color: black;
}

.title {
  text-align: center;
}

.title h1 {
  font-weight: 200;
}
</style>
