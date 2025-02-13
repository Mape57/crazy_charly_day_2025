<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import {onMounted, ref} from "vue";
import BarFormComponent from "@/components/BarFormComponent.vue";
import SelectedComponent from "@/components/SelectedComponent.vue";
import CardComponent from "@/components/CardComponent.vue";
import axios from "axios";
import {useUserStore} from "@/stores/userStore.js";

const store = useUserStore();

const title = ref('Vous avez un besoin ?');
const icons = ref([
  { name: 'mdi-menu', size: 'x-large', color: '#868686' },
  { name: 'mdi-account-circle', size: 'x-large', class: 'ml-3' }
]);

const getCompetences = () => {
  axios.get('http://localhost:8080/competences').then(response => {
    console.log(response.data.map(competence => competence.categorie));
    fields.value[2].items = response.data.map(competence => competence.categorie);
  });
}

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

const data = ref([]);

onMounted(()=>{
  axios.get('http://localhost:8080/besoins').then(response => {
    data.value = response.data;
  });

  getCompetences();
})

const saveBesoins = (value) => {
  axios.post('http://localhost:8080/besoins', {
    clientId: store.id,
    description: value[0].value,
    date: value[1].value,
    competenceId: value[2].value
  }).then(response => {
    data.value.push(response.data);
  });
}

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
  <BarFormComponent :fields="fields" :save="save" @saveBesoins="saveBesoins"/>
  <SelectedComponent :items="items">
  </SelectedComponent>
  <div class="main">
    <CardComponent
        v-for="d in data"
        :title="d.description"
        subtitle=""
        source="https://images.pexels.com/photos/29786951/pexels-photo-29786951.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
        max="344"
        link="true"
    />
  </div>
</template>

<style scoped>


.main{
display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin: 3em;
  justify-content: center;
  align-items: center;
}
</style>
