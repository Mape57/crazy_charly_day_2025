<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { onMounted, ref } from "vue";
import axios from "axios";
import FormComponent from "@/components/FormComponent.vue";
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
  <main>

    <div class="left-side">
      <router-link to="/">Crazy</router-link>
      <div id="left-side-content">
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
        <FormComponent title="Nouvelle compétence ?" button-text="Demander ma compétence" :fields="[
          { label: 'Quoi?', type: 'text', name: 'nom', placeholder: 'Quoi de neuf', options: user.competences },
          { label: 'Vraiment?', type: 'rating', name: 'rating' },
        ]" />
      </div>
    </div>


    <div class="right-side">
      <h2>Dernières missions:</h2>
      <CarouselComponent :items="user.competences" />
    </div>


  </main>
</template>

<style scoped>
main {
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  height: 100vh;
}

.left-side {
  width: 100%;
  padding: 1.5rem 3.125rem; 
  border-right: none;
  border-bottom: 1px solid #cecece;
  box-sizing: border-box;
}

.left-side h2 {
  color: #5F5F5F;
  font-weight: normal;
}

a {
  text-decoration: none;
  color: #000;
  font-size: 3em;
  font-family: 'Montserrat', sans-serif;
}

#left-side-content {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
  margin-top: 2em;
  margin-bottom: 2em;
}

.competence-title {
  font-size: 1.25rem;
  font-weight: normal;
  margin-bottom: 0.625rem;
  color: #5F5F5F;
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

.list-competences {
  margin-bottom: 2.5rem;
}

.right-side {
  width: 100%;
  padding-left: 3.125rem;
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: center;
}

.right-side h2 {
  border-bottom: #000 0.2rem solid;
  width: fit-content;
  font-size: 2.5rem;
  font-weight: lighter;
}


@media (min-width: 768px) {

  .left-side,
  .right-side {
    width: 50%;
    height: 100%;
  }

  .left-side {
    border-right: 1px solid #cecece;
    border-bottom: none;
  }

}

@media (min-width: 1024px) {


  main {
    flex-wrap: nowrap;
  }

  .left-side {
    width: 30%;
  }

  .right-side {
    width: 70%;

  }

}
</style>
