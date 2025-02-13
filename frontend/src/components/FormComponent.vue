<script setup>
import { ref } from 'vue';
import { defineProps, defineEmits } from 'vue';
import axios from 'axios';

const props = defineProps({
  title: {
    type: String,
    required: true
  },
  fields: {
    type: Array,
    required: true
  },
  buttonText: {
    type: String,
    required: true
  },
  submitUrl: {
    type: String,
    required: true
  }
});

const emit = defineEmits(['submitSuccess', 'submitError']);
const formData = ref({});

const handleSubmit = async () => {
  try {
    const response = await axios.post(props.submitUrl, formData.value);
    emit('submitSuccess', response.data);
  } catch (error) {
    emit('submitError', error);
  }
};
</script>

<template>
  <form @submit.prevent="handleSubmit" class="form-container">
    <h2>{{ title }}</h2>
    <div class="input-container" v-for="field in fields" :key="field.name">
      <label :for="field.name">{{ field.label }}</label>
      <template v-if="field.type === 'rating'">
        <v-rating v-model="formData[field.name]" :name="field.name" hover half-increments :length="5" :size="32" active-color="#45FF30"></v-rating>
      </template>
      <template v-else-if="field.options">
        <select v-model="formData[field.name]" :name="field.name">
          <option v-for="option in field.options" :key="option" :value="option">{{ option }}</option>
        </select>
      </template>
      <template v-else>
        <input v-model="formData[field.name]" :type="field.type" :name="field.name" :placeholder="field.placeholder">
      </template>
      <span class="divider"></span>
    </div>
    <button type="submit">{{ buttonText }}</button>
  </form>
</template>

<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  border: 1px solid #868686;
  border-radius: 16px;
  padding: 40px;
  box-shadow: 0 4px 4px #868686;
}

h2 {
  font-size: 24px;
  font-family: 'Montserrat', sans-serif;
  text-decoration: underline;
  margin-bottom: 15px;
}

.input-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.input-container select {
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #868686;
  font-size: 16px;
}

.input-container label {
  font-size: 20px;
  font-family: 'Montserrat', sans-serif;
  margin-bottom: 5px;
  color: #000000;
  font-weight: bold;
}

.input-container input {
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #868686;
  font-size: 16px;
}

.divider {
  height: 2px;
  background-color: #D9D9D9;
  margin: 20px 0;
}

button {
  background-color: #45FF30;
  color: #1E1E1E;
  padding: 14px 20px;
  margin: 8px 0;
  cursor: pointer;
  width: 100%;
  border: 1px solid black;
  font-size: 24px;
  border-radius: 25px;
  font-weight: bold;
}

button:hover {
  background-color: #45FF30;
}

</style>
