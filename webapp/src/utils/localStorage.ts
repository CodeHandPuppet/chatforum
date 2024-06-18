export const setLocalSrorage = (name: string, data: any) => {
    localStorage.setItem(name, JSON.stringify(data));
}

export const getLocalSrorage = (name: string): undefined | any => {
    return JSON.parse(localStorage.getItem(name)!);
}

export const removeLocalSrorage = (name: string) => {
    localStorage.removeItem(name)
}