import { useAPI } from "@/utils/api.js";

export async function getSalaries() {
    try {
        const api = useAPI();
        const response = await api.get('/salaries');
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function createSalarie(salarie) {
    try {
        const api = useAPI();
        const response = await api.post('/salaries', salarie);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function updateSalarieCompetence(salarieId, competenceId, competence) {
    try {
        const api = useAPI();
        const response = await api.patch(`/salaries/${salarieId}/competence/${competenceId}`, competence);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function addSalarieCompetence(salarieId, competence) {
    try {
        const api = useAPI();
        const response = await api.post(`/salaries/${salarieId}/competence`, competence);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getSalarieCompetences(salarieId) {
    try {
        const api = useAPI();
        const response = await api.get(`/salaries/${salarieId}/competences`);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}