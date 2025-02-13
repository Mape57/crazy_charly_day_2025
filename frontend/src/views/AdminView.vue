<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import { ref } from "vue";
import BarFormComponent from "@/components/BarFormComponent.vue";
import SelectedComponent from "@/components/SelectedComponent.vue";
import CardComponent from "@/components/CardComponent.vue";
import axios from "axios";

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;

const title = ref('Vous avez un besoin ?');
const icons = ref('test');

const fields = ref([
  { type: 'select', label: 'sélectionner un client', name: 'Qui ?:', items: [], required: true },
  { type: 'select', label: 'sélectionner un besoin', name: 'Quoi ?:',items: [], required: true },
  { type: 'select', label: 'sélectionner un  salarié', name: 'Avec Qui :', items: [], required: true }
]);

const items = ref([
  { name: 'Clients', icon: 'mdi-briefcase-account-outline' },
  { name: 'Salariés', icon: 'mdi-account-tie' },
  { name: 'Besoins', icon: 'mdi-tag-search-outline' },
  { name: 'Compétences', icon: 'mdi-trophy-award' },
]);

const save = ref('Affecter');

const logo = ref('Crazy');

const getBesoins = async () => {
  try {
    const response = await axios.get(`${apiBaseUrl}/besoins`);
    items.value = response.data;
    console.log(items.value);
    console.log(response);
  } catch (error) {
    console.error(error);
  }
}


getBesoins();
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
  <BarFormComponent :fields="fields" :save="save" />
  <SelectedComponent :items="items">
  </SelectedComponent>
  <div class="main">


  </div>
</template>

<style scoped>


.main{
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 3rem;

}
</style>
