<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import { ref } from "vue";
import BarFormComponent from "@/components/BarFormComponent.vue";
import SelectedComponent from "@/components/SelectedComponent.vue";
import axios from "axios";

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;

const title = ref('Vous avez un besoin ?');
const icons = ref('test');

const getSalaries = async () => {
  try {
    const response = await axios.get(`${apiBaseUrl}/salaries`);
    response.data = response.data.map((item) => {
      return item.nom;
    });
    return response.data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getClients = async () => {
  try {
    const response = await axios.get(`${apiBaseUrl}/clients`);
    return response.data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getBesoins = async () => {
  try {
    const response = await axios.get(`${apiBaseUrl}/besoins`);
    response.data = response.data.map((item) => {
      return item.description;
    });
    return response.data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const salarieItems = ref([]);
const clientItems = ref([]);
const besoinItems = ref([]);

const fields = ref([
]);

const items = ref([
  { name: 'Clients', icon: 'mdi-briefcase-account-outline' },
  { name: 'Salariés', icon: 'mdi-account-tie' },
  { name: 'Besoins', icon: 'mdi-tag-search-outline' },
  { name: 'Compétences', icon: 'mdi-trophy-award' },
]);

const save = ref('Affecter');
const logo = ref('Crazy');

const fetchData = async () => {
  try {
    salarieItems.value = await getSalaries();
    clientItems.value = await getClients();
    besoinItems.value = await getBesoins();
    const response = await axios.get(`${apiBaseUrl}/besoins`);
    items.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

const getAffectation = async () => {
  try {
    const response = await axios.get(`${apiBaseUrl}/affectations`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const snackbar = ref(false);
const snackbarMessage = ref('');

const handleSaveClick = async () => {
  try {
    const affectationData = await getAffectation();
    snackbarMessage.value = JSON.stringify(affectationData);
    snackbar.value = true;
  } catch (error) {
    console.error(error);
  }
};

fetchData();
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
  <BarFormComponent :fields="fields" :save="save" @click="handleSaveClick" />
  <v-snackbar v-model="snackbar" timeout="6000">
    {{ snackbarMessage }}
  </v-snackbar>
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
