<script setup>
</script>

<template>
  dsfsfddskfjlkdfskmlffdggfddgfgfdfhhyygjdsdffdsfdsdfsfrgtgtrtghr
  <v-dialog max-width="500">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn
          v-bind="activatorProps"
          class="rounded-circle"
          style="position: absolute; bottom: 2em; right: 2em; background-color: #45FF30; width: 50px; height: 50px; padding: 0;"
          variant="flat"
          icon="mdi-plus"
      >
      </v-btn>
    </template>

    <template v-slot:default="{ isActive }">
      <v-card
          style="padding: 2em; border-radius: 2em; display: flex; flex-direction: column; gap: 2em; justify-items: center; width: 25vw;"
      >
        <v-form>
          <h3 style="text-decoration: underline; font-weight: bold; margin: 1em 0; width: 80%;">Vous venez ?</h3>

          <div style="margin: auto;">
            <p style="font-weight: bold;">Qui ?</p>
            <v-text-field clearable
                          placeholder="Nom"
                          variant="underlined"
                          required
                          style="--v-input-placeholder-color: black;"
            ></v-text-field>
          </div>

          <div style="margin: auto;">
            <p style="font-weight: bold;">Contact ?</p>
            <v-text-field clearable
                          placeholder="Email"
                          variant="underlined"
                          required
                          style="--v-input-placeholder-color: black;"
            ></v-text-field>
          </div>

          <div style="margin: auto;">
            <p style="font-weight: bold;">Comment ?</p>
            <v-select
                :items="['Besoin 1', 'Besoin 2', 'Besoin 3']"
                placeholder="role &#8595;"
                variant="solo"
                density="comfortable"
                required
                style="--v-input-placeholder-color: black;"
            ></v-select>
          </div>
        </v-form>

        <div style="display: flex; justify-content: center;">
          <v-btn
              text="Créer un besoin"
              @click="isActive.value = false"
              class="rounded-pill"
              style="background-color: #45FF30; width: 50%; border-color: black; border-width: 1px;"
          ></v-btn>
        </div>
      </v-card>
    </template>
  </v-dialog>
</template>

<style scoped>
v-form {
  display: flex;
  flex-direction: column;
}
</style>