import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SalarieView from "@/views/SalarieView.vue";
import AdminView from "@/views/AdminView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/salarie',
      name: 'salarie',
      component: SalarieView,
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminView,
    }
  ],
})

export default router
