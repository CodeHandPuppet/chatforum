export const setToken = (data: any) => {
    localStorage.setItem('token', JSON.stringify(data));
}

export const getToken = () => {
    return JSON.parse(localStorage.getItem('token')!);
}

export const removeToken = () => {
    localStorage.removeItem('token')
}