<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import {ref} from "vue";
import FormComponent from "@/components/FormComponent.vue";

const title = ref('Connexion');
const icons = ref([
  {name: 'mdi-menu', size: 'x-large', color: '#868686'},
  {name: 'mdi-account-circle', size: 'x-large', class: 'ml-3'}
]);
const logo = ref('Crazy');

const fields = ref([
  {type: 'text', label: 'Nom d\'utilisateur', name: 'username', placeholder: 'Nom d\'utilisateur', required: true},
  {type: 'password', label: 'Mot de passe', name: 'password', placeholder: 'Mot de passe', required: true}
]);
</script>

<template>
  <HeaderComponent :title="title" :logo="logo">
    <template #icons>
      <v-icon
          v-for="(icon, index) in icons"
          :key="index"
          :size="icon.size"
          :color="icon.color"
          :class="icon.class"
      >
        {{ icon.name }}
      </v-icon>
    </template>
  </HeaderComponent>

  <div class="form">
    <FormComponent title="" :fields="fields" buttonText="Se connecter" submitUrl="login"></FormComponent>
  </div>
</template>

<style scoped>
.form {
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 400px;
  margin: 30px auto;
}
</style>
