import React, { useEffect } from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

import useLocalState from '../util/useLocalStorage';

const Dashboard = () => {
    const [jwt,setjwt] = useLocalState("","jwt");
   const [assignments, setAssignments] = useState(null);

   useEffect(()=>{
    fetch("/api/assignments",{
        headers:{
            "Content-Type":"application/json",
            "Authorization": `Bearer ${jwt}`,
        },
        method:"GET",
    })
    .then(res => {if(res.status ===200) return res.json();
    })
    .then((assignmentsData) =>{
        setAssignments(assignmentsData);
    });
   })

    function createAssignement(){
        fetch("/api/assignments",{
            headers:{
                "Content-Type":"application/json",
                "Authorization": `Bearer ${jwt}`,
            },
            method:"POST",
        }).then(res=>{
            if(res.status === 200){
                return res.json();
            }
        })
        .then((assignment)=>{
            window.location.href = `/assignment/${assignment.id}`;
        });
    }
    return (
        <div style={{margin:'2em'}}>
            {assignments ? (
                assignments.map((assignments) =>(
                    <div>
                        <Link to={`/assignments/${assignments.id}`}>
                            Assignemnt ID: {assignments.id}
                        </Link>
                    </div>
            ))
            ):(
                <></>
            )}
            <button onClick={()=>{createAssignement()}}>submit New assignement</button>

        </div>
    );
};

export default Dashboard;