import axios from "axios";
import { GET_ERRORS } from "./types";

export const addProjectTask = (
	backlog_id,
	projectTask,
	history
) => async dispatch => {
	try {
		await axios.post(`/api/backlog/${backlog_id}`, projectTask);
		history.push(`/projectBoard/${backlog_id}`);

		dispatch({
			type: GET_ERRORS,
			payload: {}
		});
	} catch (error) {
		dispatch({
			type: GET_ERRORS,
			payload: error.response.data
		});
	}
};
