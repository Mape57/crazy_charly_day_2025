import {defineStore} from 'pinia';
import {ref} from "vue";

export const useUserStore = defineStore('user', {
  state: () => {
    return {
      id: null,
      token: null,
      name: 'Gilles Boulot',
    }
  },
})
