import { useState, useEffect } from "react";
import JobList from "./components/JobList";
import Login from "./components/Login";
import MyApplications from "./components/MyApplications";
import AdminApplications from "./components/AdminApplications";
import AdminJobs from "./components/AdminJobs";
import Register from "./components/Register";

function App() {
  const [loggedIn, setLoggedIn] = useState(
    localStorage.getItem("token") !== null,
  );

  const role = localStorage.getItem("role");

  const [applicationRefresh, setApplicationRefresh] = useState(0);

  const [jobRefresh, setJobRefresh] = useState(0);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      return;
    }

    try {
      const payload = JSON.parse(atob(token.split(".")[1]));

      if (payload.exp * 1000 < Date.now()) {
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        setLoggedIn(false);
      }
    } catch (error) {
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      setLoggedIn(false);
    }
  }, []);

  const handleLogOut = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    setLoggedIn(false);
  };
  return (
    <div>
      <h1>Jobtrack</h1>
      <p>Job Application Management System</p>
      {!loggedIn ? (
        <div>
          <Login setLoggedIn={setLoggedIn} />

          <Register />
        </div>
      ) : (
        <div>
          <button onClick={handleLogOut}>Logout</button>
          <JobList
            onApplicationSuccess={() =>
              setApplicationRefresh(applicationRefresh + 1)
            }
            refresh={jobRefresh}
          />
          {role === "ADMIN" && (
            <AdminJobs onJobDeleted={() => setJobRefresh(jobRefresh + 1)} />
          )}
          {role === "ADMIN" && <AdminApplications />}
          {role === "User" && <MyApplications refresh={applicationRefresh} />}
        </div>
      )}
    </div>
  );
}

export default App;
