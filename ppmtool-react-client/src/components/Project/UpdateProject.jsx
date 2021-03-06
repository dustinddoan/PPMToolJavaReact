import React, { Component } from "react";
import classnames from "classnames";
import { getProject, createProject } from "../../actions/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

export class UpdateProject extends Component {
	constructor() {
		super();
		this.state = {
			id: "",
			projectName: "",
			projectIdentifier: "",
			description: "",
			start_date: "",
			end_date: "",
			errors: {}
		};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}
	handleSubmit(e) {
		e.preventDefault();

		const updatedProject = {
			id: this.state.id,
			projectName: this.state.projectName,
			projectIdentifier: this.state.projectIdentifier,
			description: this.state.description,
			start_date: this.state.start_date,
			end_date: this.state.end_date
		};
		console.log(updatedProject);
		this.props.createProject(updatedProject, this.props.history).then(() => {
			console.log("post");
		});
	}
	componentDidMount() {
		let { id } = this.props.match.params;
		console.log(id);
		this.props.getProject(id, this.props.history);
	}

	// setState should be in here
	componentWillReceiveProps(nextProps) {
		if (nextProps.errors) {
			this.setState({
				errors: nextProps.errors
			});
		}
		const {
			id,
			projectName,
			projectIdentifier,
			description,
			start_date,
			end_date
		} = nextProps.project;
		this.setState({
			id,
			projectName,
			projectIdentifier,
			description,
			start_date,
			end_date
		});
	}

	handleChange(event) {
		const name = event.target.name;
		const value = event.target.value;
		this.setState({
			[name]: value
		});
	}
	render() {
		const { errors } = this.state;
		return (
			<div className="project">
				<div className="container">
					<div className="row">
						<div className="col-md-8 m-auto">
							<h5 className="display-4 text-center">Update Project form</h5>
							<hr />
							<form onSubmit={this.handleSubmit}>
								<div className="form-group">
									<input
										type="text"
										className={classnames("form-control form-control-lg", {
											"is-invalid": errors.projectName
										})}
										placeholder="Project Name"
										name="projectName"
										value={this.state.projectName || ""}
										onChange={this.handleChange}
									/>
									{errors.projectName && (
										<div className="invalid-feedback">{errors.projectName}</div>
									)}
								</div>
								<div className="form-group">
									<input
										type="text"
										className="form-control form-control-lg"
										placeholder="Unique Project ID"
										name="projectIdentifier"
										value={this.state.projectIdentifier || ""}
										disabled
									/>
								</div>
								<div className="form-group">
									<textarea
										className="form-control form-control-lg"
										placeholder="Project Description"
										name="description"
										value={this.state.description || ""}
										onChange={this.handleChange}
									/>
								</div>
								<h6>Start Date</h6>
								<div className="form-group">
									<input
										type="date"
										className="form-control form-control-lg"
										name="start_date"
										value={this.state.start_date || ""}
										onChange={this.handleChange}
									/>
								</div>
								<h6>Estimate End Date</h6>
								<div className="form-group">
									<input
										type="date"
										className="form-control form-control-lg"
										name="end_date"
										value={this.state.end_date || ""}
										onChange={this.handleChange}
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
	getProject: PropTypes.func.isRequired,
	createProject: PropTypes.func.isRequired,
	errors: PropTypes.object.isRequired
};

const mapStateToProps = state => {
	return {
		project: state.projects.project,
		errors: state.errors
	};
};

export default connect(
	mapStateToProps,
	{ getProject, createProject }
)(UpdateProject);
