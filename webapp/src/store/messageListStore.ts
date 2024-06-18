import { getMessageListRequest } from "@/api/messageList";
import { SortData, UserData } from "@/type/websiteDataType";
import { getLocalSrorage, setLocalSrorage } from "@/utils/localStorage";
import { defineStore } from "pinia";

interface Type {
    messageList: UserData;
}
export const useMessageListStore = defineStore('messageListStore', {
    state: () => {
        return {
            messageList: getLocalSrorage('messageList') ? getLocalSrorage('messageList') : [] as UserData[]
        }
    },
    getters: {

    },
    actions: {
        async messagePush(info: UserData) {
            let temp = await this.getMessageList();
            temp = [...temp, ...this.messageList]
            const index = temp.findIndex(user => {
                return user.id == info.id;
            })
            if (index == -1) {
                this.messageList.push(info);
                // setLocalSrorage('messageList', this.messageList);
            }
            setLocalSrorage("active", info.id);

        },
        async getMessageList() {
            const result = await getMessageListRequest();
            return result.data.friends;
        }
    },
})