import api, {API_BASE_URL} from "@/Config/Api.js";
import axios from "axios";

export const register= userData => async (dispatch) => {
    dispatch({type:'REGISTER_REQUEST'});
    try{
        const {data} = await axios.post(`${API_BASE_URL}/auth/signup`, userData);
        if (data.jwt){
            localStorage.setItem('jwt',data.jwt);
            dispatch({type:'REGISTER_SUCCESS', payload:data});
        }

        console.log("Register Success", data)

    } catch (error) {
        console.log(error);
    }

}

export const login= userData => async (dispatch) => {
    dispatch({type:'LOGIN_REQUEST'});
    try{
        const {data} = await axios.post(`${API_BASE_URL}/auth/signin`, userData);
        if (data.jwt){
            localStorage.setItem('jwt',data.jwt);
            dispatch({type:'LOGIN_SUCCESS', payload:data});
        }

        console.log("Login Success", data)

    } catch (error) {
        console.log(error);
    }
}

export const getUser= () => async (dispatch) => {
    dispatch({type:'GET_USER_REQUEST'});
    try{
        const {data} = await axios.get(`${API_BASE_URL}/api/users/profile`,{
            headers: {
                "Authorization": `Bearer ${localStorage.getItem('jwt')}`,
            }
        });

            dispatch({type:'GET_USER_SUCCESS', payload:data});


        console.log("User Success", data)

    } catch (error) {
        console.log("error", error)
    }
}

export const logout = () => async (dispatch) => {
    dispatch({type:'LOGOUT'});
    localStorage.clear();
}