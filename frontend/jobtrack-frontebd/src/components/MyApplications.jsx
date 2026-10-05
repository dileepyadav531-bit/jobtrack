import { useState, useEffect } from "react";

function MyApplications({ refresh }) {
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/applications/my", {
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
        setApplications(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [refresh]);

  return (
    <div>
      <h2>My Applications</h2>

      {applications.map((application) => (
        <div key={application.id}>
          <p>Application Id: {application.id}</p>
          <p>Job Id: {application.jobId}</p>
          <p>Status: {application.status}</p>
        </div>
      ))}
    </div>
  );
}

export default MyApplications;
