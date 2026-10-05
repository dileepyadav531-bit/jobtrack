function JobDetails({
  job,
  setSelectedJob,
  onApplicationSuccess,
  showAllJobs,
}) {
  const applyForJob = () => {
    const token = localStorage.getItem("token");
    fetch("http://localhost:8080/api/applications", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        jobId: job.id,
        notes: "Applying for this job",
      }),
    })
      .then((response) => {
        if (response.status === 403) {
          throw new Error("Only users can Apply for job");
        }

        if (!response.ok) {
          return response.json().then((data) => {
            throw new Error(data.message);
          });
        }

        return response.json();
      })
      .then((data) => {
        alert("Application Submitted successfully");
        setSelectedJob(null);
        onApplicationSuccess();
        showAllJobs();
      })
      .catch((error) => {
        alert(error.message);
        setSelectedJob(null);
        showAllJobs();
      });
  };
  return (
    <div>
      <h2>{job.title}</h2>

      <p>{job.company}</p>
      <p>Location: {job.location}</p>
      <p>Job Type: {job.jobType}</p>
      <p>Salary: {job.salary}</p>
      <p>Experience: {job.experience}</p>
      <p>Skills: {job.skills}</p>
      <p>Description: {job.description}</p>

      <button onClick={applyForJob}>Apply for job</button>
    </div>
  );
}

export default JobDetails;
