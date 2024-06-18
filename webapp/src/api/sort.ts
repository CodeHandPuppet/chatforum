import request from "@/utils/request"
import { SaveOrDeleteResponseData, SortDataResponseData } from "./type"
import { SortData } from "@/type/websiteDataType";


enum API {
    // 获得全部分类
    SORT_GETALLSORTS = "/sort/getAllSorts",
    // 添加分类
    SORT_ADD = "/sort/add",
    // 更改分类
    SORT_EDIT = "/sort/edit",
    // 更改isLook
    SORT_EDITISLOOK = "",
    // 删除某个分类
    SORT_DELETE = "/sort/delete",
}

// 获得全部分类
export const getAllSortRequest = () => {
    return request.get<any, SortDataResponseData>(API.SORT_GETALLSORTS);
}

// 添加
export const addSortRequest = (sort: SortData) => {
    const { sortName, intor, isLook, uid } = sort;
    return request.post<any, SaveOrDeleteResponseData>(API.SORT_ADD, { uid, sortName, intor, isLook });
}

// 更改
export const editSortRequest = (sort: SortData) => {
    const { sortName, intor, isLook, id, uid } = sort;
    return request.post<any, SaveOrDeleteResponseData>(API.SORT_EDIT, { uid, id, sortName, intor, isLook });
}
// 删除
export const removeSortRequest = (id: number | string) => {
    return request.post<any, SaveOrDeleteResponseData>(API.SORT_DELETE, { id });
}

