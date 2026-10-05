import { useState, useEffect } from "react";
import JobDetails from "./JobDetails";

function JobList({ onApplicationSuccess, refresh }) {
  const [jobs, setJobs] = useState([]);

  const [searchText, setSearchText] = useState("");

  const [selectedJob, setSelectedJob] = useState(null);

  const searchJobs = () => {
    const token = localStorage.getItem("token");
    fetch(`http://localhost:8080/api/jobs/search?title=${searchText}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setJobs(data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/jobs", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setJobs(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [refresh]);

  const viewJob = (id) => {
    const token = localStorage.getItem("token");
    fetch(`http://localhost:8080/api/jobs/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setSelectedJob(data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const showAllJobs = () => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/jobs", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setJobs(data);
        setSearchText("");
      });
  };

  return (
    <div>
      <h2>Available Jobs</h2>

      <input
        type="text"
        placeholder="search jobs"
        value={searchText}
        onChange={(e) => setSearchText(e.target.value)}
      />

      <button onClick={searchJobs}>Search</button>

      {selectedJob ? (
        <JobDetails
          job={selectedJob}
          setSelectedJob={setSelectedJob}
          onApplicationSuccess={onApplicationSuccess}
          showAllJobs={showAllJobs}
        />
      ) : (
        jobs.map((job) => (
          <div key={job.id}>
            <h3>{job.title}</h3>
            <p>Company:{job.company}</p>
            <p>Location:{job.location}</p>
            <p>Salary:{job.salary}</p>
            <p>Experience:{job.experience}</p>

            <button onClick={() => viewJob(job.id)}>View Details</button>
          </div>
        ))
      )}
    </div>
  );
}

export default JobList;
