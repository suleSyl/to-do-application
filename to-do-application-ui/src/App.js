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

  return (
         <div>
              {tasks
                  ? tasks.map((task) => {
                        return <Task data={task} />;
                    })
                  : 'loading data...'}
         </div>
        );
}

export default App;
