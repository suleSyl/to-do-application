import logo from './logo.svg';
import './App.css';
import Task from './components/Task';
import { useEffect, useState } from 'react';

function App() {
  const [tasks, setTasks] = useState(null);

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
            name: "",
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

  return (
      <>
         <div>
              <button onClick={createNewTask}>Add New Task</button>
         </div>
         <div>
              {tasks
                  ? tasks.map((task) => {
                        return (
                            <Task
                               key={task.id}
                               data={task}
                               onDeleteTask={handleDeleteTask}
                            />
                        );
                    })
                  : 'loading data...'}
         </div>
      </>
        );
}

export default App;
