import { useAPI } from "@/utils/api.js";

export async function registerUser(user) {
    try {
        const api = useAPI();
        const response = await api.post('/register', user);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}

export async function loginUser(credentials) {
    try {
        const api = useAPI();
        const response = await api.post('/login', credentials);
        return await response.data;
    } catch (error) {
        console.error(error);
    }
}