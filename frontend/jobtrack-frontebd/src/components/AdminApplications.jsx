import { useState, useEffect } from "react";

function AdminApplications() {
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/applications", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setApplications(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleStatusChange = (applicationId, newStatus) => {
    setApplications(
      applications.map((application) =>
        application.applicationId === applicationId
          ? { ...application, status: newStatus }
          : application,
      ),
    );

    const token = localStorage.getItem("token");

    fetch(
      `http://localhost:8080/api/applications/${applicationId}/status?status=${newStatus}`,
      {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    )
      .then((response) => response.json())
      .then((data) => {})
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <h2>All Applications</h2>
      {applications.map((application) => (
        <div key={application.applicationId}>
          <p>Application Id:{application.applicationId}</p>
          <p>Applicant:{application.userName}</p>
          <p>Email:{application.userEmail}</p>
          <p>Job:{application.jobTitle}</p>
          <p>Company:{application.company}</p>
          <p>Status:{application.status}</p>
          <select
            value={application.status}
            onChange={(e) =>
              handleStatusChange(application.applicationId, e.target.value)
            }
          >
            <option>APPLIED</option>
            <option>SCREENING</option>
            <option>INTERVIEW</option>
            <option>SELECTED</option>
            <option>REJECTED</option>
            <option>WITHDRAWN</option>
          </select>
          <p>Applied Date:{application.appliedDate}</p>
        </div>
      ))}
    </div>
  );
}

export default AdminApplications;
