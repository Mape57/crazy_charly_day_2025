<script setup>
defineProps({
  items: Array
})

</script>

<template>
  <div class="items">
    <template v-for="(item, index) in items" :key="index">
      <div class="item">
        <v-icon>{{ item.icon }}</v-icon>
        <p v-html="item.name"></p>
      </div>
    </template>
  </div>
</template>

<style scoped>
.items{
  display: flex;
  justify-content: center;
  align-items: center;
  border-style: solid;
  border-width: 1px;
  border-color: gray;
  margin-top: 3em;
  padding-top: 0.5em;
  padding-bottom: 0.5em;
  gap: 10em;
}

.item{
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  margin: 10px 0;
  padding: 1em 2em;
}

.item:hover{
  background-color: gray;
  border-radius: 10px;
  color: white;
}

</style>
