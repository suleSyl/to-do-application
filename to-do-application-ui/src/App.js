import logo from './logo.svg';
import './App.css';
import './style/custom-style.css';
import Task from './components/Task';
import { useEffect, useState } from 'react';

function App() {
  const [tasks, setTasks] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isRegisterMode, setIsRegisterMode] = useState(false);

  const handleLogin = () => {
      if (username === 'user' && password === 'password') {
        setIsLoggedIn(true);
      } else {
        alert('Invalid username or password');
      }
  };

  const handleRegister = () => {
        alert('User registered successfully');
  };

  const handleLogout = () => {
      setIsLoggedIn(false);
      setUsername('');
      setPassword('');
  };

  useEffect(() => {
    console.log("Tasks loading")
    if (!tasks) {
        fetch('http://localhost:8080/api/tasks').then((response) =>
            response.json())
            .then((data) => {
            console.log("Task list", data);
            setTasks(data);
            });
    }
  }, [tasks]);

  function createNewTask() {
     fetch("http://localhost:8080/api/tasks", {
        headers: {
            "content-type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
            desc: "",
            isCompleted: false,
            createdBy: "defaultUser",
        }),
     })
     .then((response) => response.json())
     .then((task) => {
        setTasks([...tasks, task ]);
     });
  }

  function handleDeleteTask(taskToDelete) {
    const updatedTasks = tasks.filter(task => task.id !== taskToDelete.id);
    setTasks([...updatedTasks])
  }

  const renderLoginForm = () => (
      <div>
        <h2>Login</h2>
        <form>
         <div className="auth-form-group">
          <label className="auth-label">
            Username:
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="auth-input" />
          </label>
         </div>
          <br />
         <div className="auth-form-group">
          <label className="auth-label">
            Password:
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="auth-input"/>
          </label>
         </div>
          <br />
         <div className="button-group">
          <button type="button" onClick={handleLogin} className="btn-primary">
            Login
          </button>
          <button type="button" onClick={() => setIsRegisterMode(true)} className="btn-secondary">
            Switch to Register
          </button>
         </div>
        </form>
      </div>
  );

  const renderRegisterForm = () => (
      <div>
        <h2>Register</h2>
        <form>
         <div className="auth-form-group">
          <label className="auth-label">
            Username:
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="auth-input"/>
          </label>
         </div>
          <br />
         <div className="auth-form-group">
          <label className="auth-label">
            Password:
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="auth-input"/>
          </label>
         </div>
          <br />
         <div className="button-group">
          <button type="button" onClick={handleRegister} className="btn-primary">
            Register
          </button>
          <button type="button" onClick={() => setIsRegisterMode(false)} className="btn-secondary">
            Switch to Login
          </button>
         </div>
        </form>
      </div>
  );

  return (
      <div className="app-container" style={{ position: 'relative' }}>
        <div className="header">
          <h1> To-Do App </h1>
          {isLoggedIn ? (
              <>
                <button className="add-button" onClick={createNewTask}>
                 Add New Task
                </button>
                <button className="logout-button" onClick={handleLogout}>
                 Logout
                </button>
              </>
          ) : (
                isRegisterMode ? renderRegisterForm() : renderLoginForm()
              )}
        </div>
        {isLoggedIn && (
          <div className="task-list">
            {tasks
                ? tasks.map((task) => (
                   <Task
                     key={task.id}
                     data={task}
                     onDeleteTask={handleDeleteTask}
                    />
                   ))
               : 'Loading data...'}
          </div>
        )}
      </div>
    );
}

export default App;
