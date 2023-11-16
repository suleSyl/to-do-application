import React from 'react';
import { useEffect, useState } from 'react';
import Utils from './../utility/Util';

const Task = (props) => {
    const [task, setTask] = useState(props.data)
    const [isModified, setModified] = useState(false);

    useEffect(() => {
        if (isModified) {
            fetch(`http://localhost:8080/api/tasks/${task.id}`, {
                        method: "PUT",
                        headers: Utils.getHeaders(),
                        body: JSON.stringify(task),
                    }).then((response) => response.json())
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

    return (
        <>
          <input type="checkbox"
                checked={task.completed}
                onChange={updateIsCompleted} />
          <span>{task.name}</span>
        </>
    );
};

export default Task;