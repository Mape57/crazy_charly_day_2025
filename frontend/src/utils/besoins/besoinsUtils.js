import {useAPI} from "@/utils/api.js";

export async function createBesoin(besoin) {
    try {
        const api = useAPI();
        const response = await api.post('/besoins', besoin);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getBesoins() {
    try {
        const api = useAPI();
        const response = await api.get('/besoins');
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function getBesoinsPaginated(page = 1, limit = 10) {
    try {
        const api = useAPI();
        const response = await api.get(`/besoins?page=${page}&limit=${limit}`);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}