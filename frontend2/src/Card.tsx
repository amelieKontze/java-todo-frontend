import React, {useState} from 'react';


type TaskCard = {
    description: string,
    status: string,
    id: string
}


function Card(props:TaskCard) {

    const [taskCard, setNewTaskCard] = useState<TaskCard[]>([]);

    function showDetails() {
    }

    function showEditPage() {

    }

    function changeStatus() {
    }

    function deleteTask() {

    }


    return (
        <div>
            <h2>
            {props.description}
            </h2>

        <button onClick={showDetails} >Details</button>
        <button onClick={showEditPage}>Edit</button>
        {props.status === "DONE"?<button onClick={deleteTask}>Delete</button>:
            <button onClick={changeStatus}>Next</button>}
        </div>
    );
}

export default Card;