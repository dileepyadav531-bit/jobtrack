import { useState, useEffect, useRef } from "react";

function AdminJobs({ onJobDeleted }) {
  const [title, setTitle] = useState("");
  const [company, setCompany] = useState("");
  const [location, setLocation] = useState("");
  const [jobType, setJobType] = useState("");
  const [salary, setSalary] = useState("");
  const [experience, setExperience] = useState("");
  const [skills, setSkills] = useState("");
  const [description, setDescription] = useState("");

  const [jobs, setJobs] = useState([]);
  const [editId, setEditId] = useState(null);

  const formRef = useRef(null);

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/jobs", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setJobs(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const addJob = () => {
    if (
      !title ||
      !company ||
      !location ||
      !jobType ||
      !salary ||
      !experience ||
      !skills ||
      !description
    ) {
      alert("Please Fill All Details");
      return;
    }
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/jobs", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        title: title,
        company: company,
        location: location,
        jobType: jobType,
        salary: salary,
        experience: experience,
        skills: skills,
        description: description,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setJobs([...jobs, data]);

        setTitle("");
        setCompany("");
        setLocation("");
        setJobType("");
        setSalary("");
        setExperience("");
        setSkills("");
        setDescription("");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const startEdit = (job) => {
    setEditId(job.id);
    setTitle(job.title);
    setCompany(job.company);
    setLocation(job.location);
    setJobType(job.jobType);
    setSalary(job.salary);
    setExperience(job.experience);
    setSkills(job.skills);
    setDescription(job.description);

    formRef.current.scrollIntoView({ behavior: "smooth" });
  };

  const updateJob = () => {
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/api/jobs/${editId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        title: title,
        company: company,
        location: location,
        jobType: jobType,
        salary: salary,
        experience: experience,
        skills: skills,
        description: description,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setJobs(jobs.map((job) => (job.id === data.id ? data : job)));
        setEditId(null);
        setTitle("");
        setCompany("");
        setLocation("");
        setJobType("");
        setSalary("");
        setExperience("");
        setSkills("");
        setDescription("");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteJob = (id) => {
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/api/jobs/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.text())
      .then((data) => {
        setJobs(jobs.filter((job) => job.id !== id));
        onJobDeleted();
      })
      .catch((error) => {
        console.log(error);
      });
  };
  return (
    <div>
      <div ref={formRef}>
        <h2>Admin Job Management</h2>

        <h3>{editId === null ? "Add New Job" : "Update Job"}</h3>

        <input
          placeholder="Job Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <input
          placeholder="Company"
          value={company}
          onChange={(e) => setCompany(e.target.value)}
        />

        <input
          placeholder="Location"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
        />

        <input
          placeholder="Job Type"
          value={jobType}
          onChange={(e) => setJobType(e.target.value)}
        />

        <input
          placeholder="Salary"
          value={salary}
          onChange={(e) => setSalary(e.target.value)}
        />

        <input
          placeholder="Experience"
          value={experience}
          onChange={(e) => setExperience(e.target.value)}
        />

        <input
          placeholder="Skills"
          value={skills}
          onChange={(e) => setSkills(e.target.value)}
        />

        <textarea
          placeholder="Job Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <br />

        {editId === null ? (
          <button onClick={addJob}>Add Job</button>
        ) : (
          <button onClick={updateJob}>Update Job</button>
        )}
      </div>
      <h3>Existing Jobs</h3>

      {jobs.map((job) => (
        <div key={job.id}>
          <h4>{job.title}</h4>
          <p>Company: {job.company}</p>
          <p>Location: {job.location}</p>
          <p>Salary: {job.salary}</p>
          <p>Experience: {job.experience}</p>

          <button onClick={() => startEdit(job)}>Update Job</button>
          <button onClick={() => deleteJob(job.id)}>Delete Job</button>
        </div>
      ))}
    </div>
  );
}
export default AdminJobs;
