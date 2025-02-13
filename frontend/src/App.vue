<script setup>
import { RouterView } from 'vue-router'
import CardComponent from "@/components/CardComponent.vue";
import CarouselComponent from "@/components/CarouselComponent.vue";
</script>

<template>

  <RouterView />
  <CarouselComponent/>
  <CarouselComponent/>
  <CarouselComponent/>
  <CarouselComponent/>
  <CarouselComponent/>
  <CarouselComponent/>

  <!-- <CardComponent title="Crazy Charly Day 2025" subtitle="Bienvenue sur Crazy Charly Day 2025" source="https://images.pexels.com/photos/5874652/pexels-photo-5874652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" max="344" link="true"></CardComponent> -->
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
