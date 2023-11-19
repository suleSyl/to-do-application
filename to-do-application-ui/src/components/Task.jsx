import React, { useEffect, useState } from 'react';
import Utils from './../utility/Util';

const Task = (props) => {
    const { onDeleteTask } = props;
    const [task, setTask] = useState(props.data);
    const [isModified, setModified] = useState(false);

    useEffect(() => {
        if (isModified) {
            fetch(`http://localhost:8080/api/tasks/${task.id}`, {
                method: "PUT",
                headers: Utils.getHeaders(),
                body: JSON.stringify(task),
            })
                .then((response) => {
                    if (response.ok) {
                        return response.json();
                    }
                })
                .then((data) => {
                     setModified(false);
                     setTask(data);
                });
        }
    }, [task, isModified]);

    function updateIsCompleted() {
        setModified(true);
        setTask({ ...task, completed: !task.completed });
    }

    function updateTask(e) {
        setModified(true);
        setTask({ ...task, desc: e.target.value });
    }

    function deleteTask() {
        setModified(false);
        fetch(`http://localhost:8080/api/tasks/${task.id}`, {
            method: "DELETE",
            headers: Utils.getHeaders(),
        })
            .then((response) => {
                if (response.ok) {
                    console.log("Task successfully deleted");
                    onDeleteTask(task);
                } else {
                    console.error("Error deleting task:", response.status, response.statusText);
                    throw new Error("Task deletion failed");
                }
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    }

    return (
        <div>
            <input
                type="checkbox"
                checked={task.completed}
                onChange={updateIsCompleted}
            />
            {task.completed ? (
                <span style={{ textDecoration: "line-through" }}>{task.desc}</span>
            ) : (
                <input type="text" value={task.desc} onChange={updateTask} />
            )}
            <span
                style={{ marginLeft: "2rem", cursor: "pointer" }}
                onClick={deleteTask}
            >
                ❌
            </span>
        </div>
    );
};

export default Task;
