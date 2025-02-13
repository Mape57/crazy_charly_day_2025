<script setup>
defineProps({
  fields: Array,
  save: String,
});

defineEmits(['saveBesoins']);
</script>

<template>
<v-card>
  <template v-for="field in fields">
    <div class="input">
      <label>{{ field.name }}</label>
      <input
          v-if="field.type === 'text' || field.type === 'date'"
          :type="field.type"
          v-model="field.model"
          :placeholder="field.label"
          :required="field.required"
          class="no-border"
      />
      <select
          v-else-if="field.type === 'select'"
          v-model="field.model"
          :required="field.required"
          class="no-border"
      >
        <option value="" selected>{{ field.label }}</option>
        <option v-for="item in field.items" :key="item" :value="item">
          {{ item }}
        </option>
      </select>
    </div>
  </template>
  <v-btn color="#45FF30">{{ save }}</v-btn>
</v-card>
</template>

<style scoped>
.no-border .v-input__control {
  border: none !important;
}
.v-card{
  display: flex;
  justify-content: space-around;
  align-items: center;
  border-style: solid;
  border-width: 1px;
  border-color: black;
  border-radius: 30px;
  margin-left: 20em;
  margin-right: 20em;
  margin-top: 3em;
  padding-top: 0.5em;
  padding-bottom: 0.5em;
}

.v-btn{
  margin: 1em;
  border-style: solid;
  border-width: 0.01em;
  border-color: black;
  border-radius: 17px;
  padding-top: 1em;
  padding-bottom: 2em;
}

.input{
  display: flex;
  flex-direction: column;
  padding-right: 2em;
  padding-left: 2em;
}

.input:not(:nth-child(2)) {
  border-left: 2px solid #C5C5C5;
}

label{
  font-weight: bold;
}

.input input::placeholder,
.input select::placeholder {
  color: black;
}
</style>
