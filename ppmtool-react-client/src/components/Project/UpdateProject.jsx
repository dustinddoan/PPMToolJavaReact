import React, { Component } from "react";
import classnames from "classnames";
import { getProject } from "../../actions/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

export class UpdateProject extends Component {
	componentDidMount() {
		let { id } = this.props.match.params;
		this.props.getProject(id, this.props.history).then(response => {
			console.log(response);
		});
	}
	render() {
		return (
			<div className="project">
				<div className="container">
					<div className="row">
						<div className="col-md-8 m-auto">
							<h5 className="display-4 text-center">Update Project form</h5>
							<hr />
							<form>
								<div className="form-group">
									<input
										type="text"
										// className={classnames("form-control form-control-lg", {
										// 	"is-invalid": errors.projectName
										// })}
										placeholder="Project Name"
										name="projectName"
										value=""
										onChange=""
									/>
									{/* {errors.projectName && (
										<div className="invalid-feedback">{errors.projectName}</div>
									)} */}
								</div>
								<div className="form-group">
									<input
										type="text"
										className="form-control form-control-lg"
										placeholder="Unique Project ID"
										name="projectIdentifier"
										value=""
										onChange=""
									/>
								</div>
								<div className="form-group">
									<textarea
										className="form-control form-control-lg"
										placeholder="Project Description"
										name="description"
										value=""
										onChange=""
									/>
								</div>
								<h6>Start Date</h6>
								<div className="form-group">
									<input
										type="date"
										className="form-control form-control-lg"
										name="start_date"
										value=""
										onChange=""
									/>
								</div>
								<h6>Estimate End Date</h6>
								<div className="form-group">
									<input
										type="date"
										className="form-control form-control-lg"
										name="end_date"
										value=""
										onChange=""
									/>
								</div>
								<input
									type="submit"
									className="btn btn-primary btn-block mt-4"
								/>
							</form>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

UpdateProject.propTypes = {
	project: PropTypes.object.isRequired,
	getProject: PropTypes.func.isRequired
};

const mapStateToProps = state => {
	return {
		project: state.projects.project
	};
};

export default connect(
	mapStateToProps,
	{ getProject }
)(UpdateProject);
