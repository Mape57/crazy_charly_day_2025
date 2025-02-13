import { useAPI } from "@/utils/api.js";

export async function getAffectationsStart() {
    try {
        const api = useAPI();
        const response = await api.get('/affectations/start');
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getAffectationsStop() {
    try {
        const api = useAPI();
        const response = await api.get('/affectations/stop');
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getAffectationById(id) {
    try {
        const api = useAPI();
        const response = await api.get(`/affectations/${id}`);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getAffectationsActualisation() {
    try {
        const api = useAPI();
        const response = await api.get('/affectations/actualisation');
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}