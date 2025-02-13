<script setup>
import { RouterView } from 'vue-router'
import CreateNeedButton from "@/components/createNeedButton.vue";
</script>

<template>
  <RouterView />
  <createNeedButton/>
</template>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Bungee&family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap');

  *{
    font-family: 'Montserrat', sans-serif;
  }
</style>
