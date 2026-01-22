import * as types from "./actionType";
import axios from "axios";

export const getUserSubscription = () => {
    return async (dispatch) => {
        dispatch({type: types.GET_USER_SUBSCRIPTION_REQUEST});
        try {
            const response = await axios.get("http://localhost:5454/api/subscription/user", {
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("jwt")}`,
                },
            });
            dispatch({
                type: types.GET_USER_SUBSCRIPTION_SUCCESS,
                payload: response.data,
            });
            console.log("User Subscription", response.data);
        } catch (error) {
            console.log(error)
            dispatch({
                type: types.GET_USER_SUBSCRIPTION_FAILURE,
                payload: error.message,
            });
        }
    };
};

export const upgradeSubscription = ({planType}) => {
    return async (dispatch) => {
        dispatch({type: types.UPGRADE_SUBSCRIPTION_REQUEST});
        try {
            const response = await axios.patch("http://localhost:5454/api/subscription/upgrade", null, {
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("jwt")}`,
                },
                params: {
                    planType: planType,
                }
            });
            dispatch({
                type: types.UPGRADE_SUBSCRIPTION_SUCCESS,
                payload: response.data,
            })
            console.log("Upgraded Subscription", response.data);
        } catch (error) {
            console.log(error.response.data)
            dispatch({
                type: types.UPGRADE_SUBSCRIPTION_FAILURE,
                payload: error.message,
            });
        }
    };
};