<script setup>
import {useUserStore} from "@/stores/userStore.js";
import {onMounted, ref} from "vue";
import axios from "axios";
import FormComponent from "@/components/FormComponent.vue";
import CardComponent from "@/components/CardComponent.vue";
import CarouselComponent from "@/components/CarouselComponent.vue";


const userStore = useUserStore();

const user = ref({
  name: '',
  userMissions: [
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
  competences: [
    'Bricolage',
    'Cuisine',
    'Jardinage',
    'Informatique',
  ],
  userCompetences: [
    {
      id: 1,
      name: 'Bricolage',
      rating: 4.5,
    },
    {
      id: 2,
      name: 'Cuisine',
      rating: 3,
    },
  ]
});

async function getCompetencesBySalarieId(id) {
  try {
    const response = await axios.get(`http://localhost:8080/salaries/${id}/competences`, {
      headers: {
        Authorization: `Bearer ${userStore.token}`,
      },
    }).then((res) => {
      user.value.userCompetences = res.data;
    });
  } catch (error) {
    console.error(error);
  }
}

async function getMissionsBySalarieId(id) {
  try {
    // TODO: modifier l'url
    const response = await axios.get(`http://localhost:8080/salaries/${id}/missions`, {
      headers: {
        Authorization: `Bearer ${userStore.token}`,
      },
    }).then((res) => {
      user.value.userMissions = res.data;
    });
  } catch (error) {
    console.error(error);
  }
}

async function getCompetences() {
  try {
    const response = await axios.get('http://localhost:8080/competences')
        .then((res) => {
          user.value.competences = res.data;
        });
  } catch (error) {
    console.error(error);
  }
}

onMounted(async () => {
  user.value.name = userStore.name;
  await getCompetences();
  await getMissionsBySalarieId(userStore.id);
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
        <p class="competence-title">Compétences:</p>
        <ul class="list-competences">
          <li v-for="competence in user.userCompetences" :key="competence.id" class="competence-item">
            <span>{{ competence.name }}</span>
            <v-rating :model-value="competence.rating" :length="5" half-increments :size="20" readonly
                      active-color="#45FF30"></v-rating>
          </li>
        </ul>
      </div>
      <FormComponent
          title="Nouvelle compétence ?"
          button-text="Demander ma compétence"
          :fields="[
            { label: 'Quoi?', type: 'text', name: 'nom', placeholder: 'Quoi de neuf', options: user.competences },
            { label: 'Vraiment?', type: 'rating', name: 'rating' },
          ]"
      />
    </div>
    <div class="divider"></div>
    <div class="right-side">
      <div>
        <h2>Dernières missions:</h2>
        <ul class="list">
          <CarouselComponent :items="user.competences"/>
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
  border-right: 0.2rem solid #868686;
  height: 5rem;
  padding: 0 1.25rem;
}

header h1 {
  font-family: 'Montserrat', sans-serif;
  font-weight: normal;
  font-size: 2.25rem;
}

main {
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  min-height: 100vh;
}

.left-side {
  width: 100%;
  padding: 0 3.125rem;
  border-right: 0.2rem solid #868686;
  box-sizing: border-box;
  height: 100%;
}

.left-side h2 {
  color: #5F5F5F;
  font-weight: normal;
}

.competence-title {
  font-size: 1.25rem;
  font-weight: normal;
  margin-bottom: 0.625rem;
  color: #5F5F5F;
}

.right-side {
  width: 100%;
  padding-left: 3.125rem;
  box-sizing: border-box;
  display: flex;
  height: 100%;
}

.list-competences {
  margin-bottom: 2.5rem;
}

.competence-item {
  display: flex;
  gap: 0.625rem;
  align-items: center;
}

.competence-item span {
  font-size: 1rem;
  font-weight: normal;
  color: #5F5F5F;
}

.competence-item .v-rating * {
  display: flex;
  align-self: center;
}

@media (min-width: 768px) {
  .left-side, .right-side {
    width: 50%;
  }
}

@media (min-width: 1024px) {
  header {
    width: 30%;
    height: 5rem;
    padding: 0 1.25rem;
  }

  main {
    flex-wrap: nowrap;
  }

  .left-side {
    width: 30%;
    padding: 0 3.125rem;
  }

  .right-side {
    width: 70%;
    padding-left: 3.125rem;
  }
}
</style>
