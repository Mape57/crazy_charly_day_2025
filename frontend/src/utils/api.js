import axios from 'axios';

export function useAPI() {
    const apiBase = import.meta.env.VITE_API_BASE_URL;

    const api = axios.create({
        baseURL: apiBase,
        headers: {
            'Content-Type': 'application/json',
        }
    });
    return api;
}
