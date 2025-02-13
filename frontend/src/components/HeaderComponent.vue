<template>
  <header>
    <h1>{{ logo }}</h1>
    <div class="icon-container">
      <slot name="icons"></slot>
    </div>
  </header>
  <div class="title">
    <h1>{{ title }}</h1>
  </div>
</template>

<script setup>
defineProps({
  title: String,
  logo: String
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
}

.icon-container {
  border: 1px solid #868686;
  border-radius: 1.5em;
  padding: 0.3em 0.7em;
  margin-right: 1.5em;
}

.title {
  text-align: center;
}

.title h1 {
  font-weight: 200;
}
</style>
