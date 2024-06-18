import { SortData } from "@/type/websiteDataType";
import { defineStore } from "pinia";

export const useSortStore = defineStore('sortStore', {
    state: (): SortData => (
        {
            id: 0,
            sortName: "全部文章",
        }
    ),
    getters: {

    },
    actions: {
        setId(id: number | string) {
            this.id = id;
        },
        setSortName(name: string) {
            this.sortName = name;
        }


    },
})