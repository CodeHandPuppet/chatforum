import { getUserinfoRequest, userloginRequest } from "@/api/user"
import { UserData } from "@/type/websiteDataType";
import { getToken, removeToken, setToken } from "@/utils/token"
import { defineStore } from "pinia"

export const useUserStore = defineStore('userStore', {
    state: (): UserData => (
        {
            id: 0,
            token: getToken(),
            avatar: '',
            nickname: '',
            username: '',
            signature: '',
            type: '',
            level: 0,
            address: "",
            create_time: "",
            forum_number: 0,
            collect: 0,
            likes: 0
        }
    ),
    getters: {

    },
    actions: {
        async userLogin(username: string, password: string) {
            const result = await userloginRequest({ username, password });
            if (result.code === 200) {
                // 获取token
                this.token = result.data.token;
                await this.getUserInfo();
                setToken(result.data.token);
                return true;
            }
            if (result.code == 201) {
                return false;
            }
        },
        async getUserInfo() {
            const result = await getUserinfoRequest();
            if (result.code == 200) {
                const { avatar, id, nickname, signature, type, username, level, address, create_time, likes, collect, forum_number } = result.data;
                this.id = id;
                this.avatar = avatar;
                this.nickname = nickname;
                this.type = type;
                this.signature = signature;
                this.username = username;
                this.level = level;
                this.address = address;
                this.likes = likes;
                this.collect = collect;
                this.forum_number = forum_number
            }

        },
        userloginOut() {
            removeToken();
            this.$reset();
        }
    },
})