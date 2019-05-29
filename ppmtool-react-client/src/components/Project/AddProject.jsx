import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import CreateProjectButton from "./CreateProjectButton";
import classnames from "classnames";

class AddProject extends Component {
	constructor() {
		super();
		this.state = {
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

	componentWillReceiveProps(nextProps) {
		if (nextProps.errors) {
			this.setState({
				errors: nextProps.errors
			});
		}
	}
	handleChange(event) {
		const name = event.target.name;
		const value = event.target.value;
		this.setState({
			[name]: value
		});
	}
	handleSubmit(e) {
		e.preventDefault();

		const newProject = {
			projectName: this.state.projectName,
			projectIdentifier: this.state.projectIdentifier,
			description: this.state.description,
			start_date: this.state.start_date,
			end_date: this.state.end_date
		};
		console.log(newProject);
		this.props.createProject(newProject, this.props.history).then(() => {
			console.log("post");
		});
	}
	render() {
		const { errors } = this.state;
		return (
			<div className="project">
				<div className="container">
					<div className="row">
						<div className="col-md-8 m-auto">
							<h5 className="display-4 text-center">Create Project form</h5>
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
										value={this.state.projectName}
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
										value={this.state.projectIdentifier}
										onChange={this.handleChange}
									/>
								</div>
								<div className="form-group">
									<textarea
										className="form-control form-control-lg"
										placeholder="Project Description"
										name="description"
										value={this.state.description}
										onChange={this.handleChange}
									/>
								</div>
								<h6>Start Date</h6>
								<div className="form-group">
									<input
										type="date"
										className="form-control form-control-lg"
										name="start_date"
										value={this.start_date}
										onChange={this.handleChange}
									/>
								</div>
								<h6>Estimate End Date</h6>
								<div className="form-group">
									<input
										type="date"
										className="form-control form-control-lg"
										name="end_date"
										value={this.end_date}
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

AddProject.propTypes = {
	createProject: PropTypes.func.isRequired,
	errors: PropTypes.object.isRequired
};

const mapStateToProps = state => {
	return {
		errors: state.errors
	};
};

export default connect(
	mapStateToProps,
	{ createProject }
)(AddProject);
