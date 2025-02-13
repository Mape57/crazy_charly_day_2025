<script setup>

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
});

const handleSubmit = async () => {
  try {

  } catch (error) {
    console.error(error);
  }
}
</script>

<template>
  <form @submit.prevent="handleSubmit" class="form-container">
    <h2>{{ title }}</h2>
    <div class="input-container" v-for="field in fields" :key="field.name">
      <label :for="field.name">{{ field.label }}</label>
      <template v-if="field.type === 'rating'">
        <v-rating :name="field.name" hover half-increments :length="5" :size="32" active-color="#45FF30"></v-rating>
      </template>
      <template v-else-if="field.options">
        <select :name="field.name">
          <option v-for="option in field.options" :key="option" :value="option">{{ option }}</option>
        </select>
      </template>
      <template v-else>
        <input :type="field.type" :name="field.name" :placeholder="field.placeholder">
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
  border-radius: 40px;
  padding: 40px;
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
  height: 1px;
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
}
</style>
