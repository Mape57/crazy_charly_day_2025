<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import { ref } from "vue";
import BarFormComponent from "@/components/BarFormComponent.vue";
import SelectedComponent from "@/components/SelectedComponent.vue";

const title = ref('Vous avez un besoin ?');
const icons = ref([
  { name: 'mdi-menu', size: 'x-large', color: '#868686' },
  { name: 'mdi-account-circle', size: 'x-large', class: 'ml-3' }
]);

const fields = ref([
  { type: 'text', label: 'écrivez le titre du besoin', name: 'Quoi ?:', required: true },
  { type: 'date', label: '../../../', name: 'Quand ?:', required: true },
  { type: 'select', label: 'Compétences', name: 'Quelle que compétence :', items: ['Type 1', 'Type 2', 'Type 3'], required: true }
]);

const items = ref([
  { name: 'Jardinage', icon: 'mdi-rake' },
  { name: 'Bricolage', icon: 'mdi-tools' },
  { name: 'Ménage', icon: 'mdi-vacuum' },
  { name: 'Informatique', icon: 'mdi-laptop' },
  { name: 'Accompagnement </br> Administratif', icon: 'mdi-note' }
]);

const save = ref('Créer un besoins');

const logo = ref('Crazy');
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

}
</style>
