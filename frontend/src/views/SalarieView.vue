<script setup>
import {useUserStore} from "@/stores/userStore.js";
import {onMounted, ref} from "vue";
import axios from "axios";
import FormComponent from "@/components/FormComponent.vue";
import CarouselComponent from "@/components/CarouselComponent.vue";

const userStore = useUserStore();

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;

const categoryImageMap = {
  administration: '/src/assets/img/administration.png',
  bricolage: '/src/assets/img/bricolage.png',
  informatique: '/src/assets/img/informatique.png',
  jardinage: '/src/assets/img/jardinage.png',
  menage: '/src/assets/img/menage.png',
  default: '/src/assets/img/default.png'
};

const user = ref({
  name: '',
  userMissions: [],
  competences: [],
  userCompetences: [],
});

async function getCompetencesBySalarieId(id) {
  try {
    const response = await axios.get(`${apiBaseUrl}/salaries/${id}/competences`, {
      headers: {
        Authorization: `Bearer ${userStore.token}`,
      },
    }).then((res) => {
      user.value.userCompetences = res.data;
      console.log(user.value.userCompetences);
    });
  } catch (error) {
    console.error(error);
  }
}

async function getCompetenceById(id) {
  try {
    const response = await axios.get(`${apiBaseUrl}/competences/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}

async function getMissionsBySalarieId(id) {
  try {
    const response = await axios.get(`${apiBaseUrl}/salaries/${id}/besoins`, {
      headers: {
        Authorization: `Bearer ${userStore.token}`,
      },
    }).then(async (res) => {
      user.value.userMissions = await Promise.all(res.data.map(async (mission) => {
        const competence = await getCompetenceById(mission.competenceId);
        switch (competence.libelle) {
          case 'AD':
            mission.source = categoryImageMap.administration;
            break;
          case 'BR':
            mission.source = categoryImageMap.bricolage;
            break;
          case 'IF':
            mission.source = categoryImageMap.informatique;
            break;
          case 'JD':
            mission.source = categoryImageMap.jardinage;
            break;
          case 'MN':
            mission.source = categoryImageMap.menage;
            break;
          default:
            mission.source = categoryImageMap.default;
        }
        return {
          title: competence.categorie,
          subtitle: mission.description,
          source: mission.source,
          max: '300',
          link: '/mission',
        };
      }));
    });
  } catch (error) {
    console.error(error);
  }
}

async function getCompetences() {
  try {
    const response = await axios.get(`${apiBaseUrl}/competences`)
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
              <span>{{ competence.categorie }}</span>
              <v-rating :model-value="competence.interet / 2" :length="5" half-increments :size="20" readonly
                        active-color="#45FF30"></v-rating>
            </li>
          </ul>
        </div>
        <FormComponent title="Nouvelle compétence ?" button-text="Demander ma compétence" :fields="[
          { label: 'Quoi?', type: 'text', name: 'nom', placeholder: 'Quoi de neuf', options: user.competences.map(c => c.categorie)  },
          { label: 'Vraiment?', type: 'rating', name: 'rating' },
        ]" :submit-url="`salaries/${userStore.id}/competences`"
        />
      </div>
    </div>

    <div class="right-side">
      <h2>Dernières missions:</h2>
      <ul class="list">
        <CarouselComponent :items="user.userMissions"/>
      </ul>
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
