import {defineStore} from 'pinia';
import {ref} from "vue";

export const useUserStore = defineStore('user', {
  state: () => {
    return {
      id: "7bdc0155-8ab2-4231-bd9e-a9528264d04b",
      token: null,
      name: 'tom',
      role: 1,
    }
  },
})
