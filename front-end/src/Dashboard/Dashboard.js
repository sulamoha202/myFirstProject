import React from 'react';
import { Link } from 'react-router-dom';

import useLocalState from '../util/useLocalStorage';

const Dashboard = () => {
    const [jwt,setJwt] = useLocalState("","jwt");
    function createAssignement(){
        fetch("api/assignemnts",{
            headers:{
                "Content-Type":"application/json",
                "Authorization": `Bearer ${jwt}`
            },
            method:"POST"
        }).then(res=>{
            if(res.status === 200){
                return res.json();
            }
        }).then((data)=>{
            console.log(data);
        })
    }
    return (
        <div style={{margin:'2em'}}>
            <button onClick={()=>{createAssignement()}}>Submit New Assignement</button>
        </div>
    );
};

export default Dashboard;