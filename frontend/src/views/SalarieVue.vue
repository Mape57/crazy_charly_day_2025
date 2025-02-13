<script setup>
import {useUserStore} from "@/stores/userStore.js";
import {onMounted, ref} from "vue";
import axios from "axios";

const userStore = useUserStore();

const user = ref({
  name: '',
  competences: [],
});

async function getCompetencesBySalarieId(id) {
  try {
    const response = await axios.get(`http://localhost:8080/salaries/${id}/competences`, {
      headers: {
        Authorization: `Bearer ${userStore.token}`,
      },
    }).then((res) => {
      return res.data;
    });
  } catch (error) {
    console.error(error);
  }
}

onMounted(async () => {
  user.value.name = userStore.name;
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
    </div>
    <div class="divider"></div>
    <div class="right-side">
      <div>
        <h2>Dernières missions:</h2>
        <ul>
          <li v-for="competence in user.competences" :key="competence.id">{{ competence.name }}</li>
        </ul>
      </div>
    </div>
  </main>
</template>

<style scoped>
header {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

header h1 {
  font-family: 'Montserrat', sans-serif;
  font-weight: normal;
  font-size: 36px;
}

.divider {
  width: 1px;
  height: 100%;
  background-color: #000;
}
</style>
