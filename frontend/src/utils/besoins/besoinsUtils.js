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