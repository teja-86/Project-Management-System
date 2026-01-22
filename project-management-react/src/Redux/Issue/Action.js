import * as actionTypes from "./ActionType";
import api from "@/Config/Api.js";

export const createIssue = (issueData) => {
    return async (dispatch) => {
        dispatch({type: actionTypes.CREATE_ISSUES_REQUEST})
        try {
            const response = await api.post(`api/issue/create`, issueData);
            console.log("Created Issue Successfully", response.data);
            dispatch({
                type: actionTypes.CREATE_ISSUES_SUCCESS,
                issues: response.data,
            });
            console.log("Issue Created Successfully", response.data);
        } catch (error) {
            dispatch({
                type: actionTypes.CREATE_ISSUES_FAILURE,
                error: error.message,
            });
        }
    }
}

export const fetchIssues = (id) => {
    return async (dispatch) => {
        dispatch({type: actionTypes.FETCH_ISSUES_REQUEST});
        try {
            const response = await api.get(`api/issue/project/${id}`);
            console.log("Fetch Issues ", response.data);
            dispatch({
                type: actionTypes.FETCH_ISSUES_SUCCESS,
                issues: response.data,
            });
        } catch (error) {
            dispatch({
                type: actionTypes.FETCH_ISSUES_FAILURE,
                error: error.message,
            });
        }
    };
};

export const fetchIssueById = (id) => {
    return async (dispatch) => {
        dispatch({type: actionTypes.FETCH_ISSUES_BY_ID_REQUEST});
        try {
            const response = await api.get(`api/issue/${id}`);
            console.log("Fetch Issues by ID", response.data);
            dispatch({
                type: actionTypes.FETCH_ISSUES_BY_ID_SUCCESS,
                issues: response.data,
            });
        } catch (error) {
            dispatch({
                type: actionTypes.FETCH_ISSUES_BY_ID_FAILURE,
                error: error.message,
            });
        }
    };
};

export const updateIssueStatus = ({id, status}) => {
    return async (dispatch) => {
        dispatch({type: actionTypes.UPDATE_ISSUE_STATUS_REQUEST});
        try {
            const response = await api.put(`api/issue/${id}/status/${status}`);
            console.log("Update Issue Status", response.data);
            dispatch({
                type: actionTypes.UPDATE_ISSUE_STATUS_SUCCESS,
                issues: response.data,
            });
        } catch (error) {
            dispatch({
                type: actionTypes.UPDATE_ISSUE_STATUS_FAILURE,
                error: error.message,
            });
        }
    };
};

export const assignedIssueToUser = ({issueId, userId}) => {
    return async (dispatch) => {
        dispatch({type: actionTypes.ASSIGNED_ISSUE_TO_USER_REQUEST});
        try {
            const response = await api.put(`api/issue/${issueId}/assignee/${userId}`);
            console.log("Assigned Issue", response.data);
            dispatch({
                type: actionTypes.ASSIGNED_ISSUE_TO_USER_SUCCESS,
                issues: response.data,
            });
        } catch (error) {
            dispatch({
                type: actionTypes.ASSIGNED_ISSUE_TO_USER_FAILURE,
                error: error.message,
            });
        }
    };
};

export const deleteIssue = (issueId) => {
    return async (dispatch) => {
        dispatch({type: actionTypes.DELETE_ISSUE_REQUEST});
        try {
            const response = await api.delete(`api/issue/delete/${issueId}`);
            console.log("Deleted Issue", response.data);
            dispatch({
                type: actionTypes.DELETE_ISSUE_SUCCESS,
                issueId,
            });
        } catch (error) {
            dispatch({
                type: actionTypes.DELETE_ISSUE_FAILURE,
                error: error.message,
            });
        }
    };
};