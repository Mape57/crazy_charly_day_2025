<script setup>
import {useUserStore} from "@/stores/userStore.js";
import {onMounted, ref} from "vue";
import axios from "axios";
import FormComponent from "@/components/FormComponent.vue";


const userStore = useUserStore();

const user = ref({
  name: '',
  competences: [
    {
      id: 1,
      name: 'Bricolage',
      libelle: 'Lorem ipsum dolor sit amet consectetur. Elementum convallis a malesuada tincidunt. Nunc ac aliquam quis quam dui velit. Donec sollicitudin cursus tristique hac sagittis eu vitae. Malesuada nisi dui ut arcu faucibus tristique platea gravida.'
    },
    {
      id: 2,
      name: 'Cuisine',
      libelle: 'Lorem ipsum dolor sit amet consectetur. Elementum convallis a malesuada tincidunt. Nunc ac aliquam quis quam dui velit. Donec sollicitudin cursus tristique hac sagittis eu vitae. Malesuada nisi dui ut arcu faucibus tristique platea gravida.'
    },
    {
      id: 3,
      name: 'Jardinage',
      libelle: 'Lorem ipsum dolor sit amet consectetur. Elementum convallis a malesuada tincidunt. Nunc ac aliquam quis quam dui velit. Donec sollicitudin cursus tristique hac sagittis eu vitae. Malesuada nisi dui ut arcu faucibus tristique platea gravida.'
    },
    {
      id: 4,
      name: 'Informatique',
      libelle: 'Lorem ipsum dolor sit amet consectetur. Elementum convallis a malesuada tincidunt. Nunc ac aliquam quis quam dui velit. Donec sollicitudin cursus tristique hac sagittis eu vitae. Malesuada nisi dui ut arcu faucibus tristique platea gravida.'
    },
  ],
  userCompetences: [
    'Bricolage',
    'Cuisine',
    'Jardinage',
    'Informatique',
  ],
});

async function getCompetencesBySalarieId(id) {
  try {
    const response = await axios.get(`http://localhost:8080/salaries/${id}/competences`, {
      headers: {
        Authorization: `Bearer ${userStore.token}`,
      },
    }).then((res) => {
      user.value.competences = res.data;
    });
  } catch (error) {
    console.error(error);
  }
}

async function getCompetences() {
  try {
    const response = await axios.get('http://localhost:8080/competences')
        .then((res) => {
          user.value.userCompetences = res.data;
        });
  } catch (error) {
    console.error(error);
  }
}

onMounted(async () => {
  user.value.name = userStore.name;
  await getCompetences();
  await getCompetencesBySalarieId(userStore.id);
});
</script>

<template>
  <header>
    <h1>Crazy</h1>
  </header>
  <main>
    <div class="left-side">
      <div>
        <img src="../assets/img/profile_picture.png" alt="profile picture">
        <h2>{{ user.name }}</h2>
        <p>Compétences:</p>
        <v-rating half-increments readonly :length="5" :size="32" :model-value="3" active-color="green"></v-rating>
      </div>
      <FormComponent
          title="Nouvelle compétence ?"
          button-text="Demander ma compétence"
          :fields="[
            { label: 'Quoi?', type: 'text', name: 'nom', placeholder: 'Quoi de neuf', options: user.userCompetences },
            { label: 'Vraiment?', type: 'rating', name: 'rating' },
          ]"
      />
    </div>
    <div class="divider"></div>
    <div class="right-side">
      <div>
        <h2>Dernières missions:</h2>
        <ul>
          <li v-for="competence in user.competences" :key="competence.id">{{ competence.name }}{{
              competence.libelle
            }}
          </li>
        </ul>
      </div>
    </div>
  </main>

</template>

<style scoped>
header {
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
  width: 30%;
  border-right: 3px solid #868686;
  height: 80px;
  padding: 0 20px;
}

header h1 {
  font-family: 'Montserrat', sans-serif;
  font-weight: normal;
  font-size: 36px;
  height: 100%;
}

main {
  display: flex;
  align-items: flex-start;
}

.left-side {
  width: 30%;
  padding: 0 50px;
  border-right: 3px solid #868686;
}

.right-side {
  width: 70%;
  padding-left: 50px;
}
</style>
