import {useAPI} from "@/utils/api.js";

export async function createCompetence(competence) {
    try {
        const api = useAPI();
        const response = await api.post('/competences', competence);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getCompetences() {
    try {
        const api = useAPI();
        const response = await api.get('/competences');
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function deleteCompetence(id) {
    try {
        const api = useAPI();
        const response = await api.delete(`/competences/${id}`);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getCompetenceById(id) {
    try {
        const api = useAPI();
        const response = await api.get(`/competences/${id}`);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function updateCompetence(id, competence) {
    try {
        const api = useAPI();
        const response = await api.patch(`/competences/${id}`, competence);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}