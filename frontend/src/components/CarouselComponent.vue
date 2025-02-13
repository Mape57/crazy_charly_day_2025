<script setup>
import CardComponent from './CardComponent.vue';
import { ref } from 'vue';

const items = ref([
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/30613415/pexels-photo-30613415/free-photo-of-batiment-historique-avec-jeu-d-ombres-et-marcheur-solitaire.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/5874652/pexels-photo-5874652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/5874652/pexels-photo-5874652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/30613415/pexels-photo-30613415/free-photo-of-batiment-historique-avec-jeu-d-ombres-et-marcheur-solitaire.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/5874652/pexels-photo-5874652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/5874652/pexels-photo-5874652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
    {
        title: "Crazy Charly",
        subtitle: "Bienvenue sur Crazy Charly Day 2025",
        source: "https://images.pexels.com/photos/5874652/pexels-photo-5874652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        max: "344",
        link: "true"
    },
]);
</script>

<template>
    <v-slide-group
        show-arrows
        class="pa-4"
    >
        <v-slide-group-item
            v-for="(item, i) in items"
            :key="i"
        >
                <CardComponent
                    :title="item.title"
                    :subtitle="item.subtitle"
                    :source="item.source"
                    max="344"
                    link="true"
                ></CardComponent>
        </v-slide-group-item>
    </v-slide-group>
</template>

<style scoped>
</style>
