import React, { useState } from 'react';
import useLocalState from '../util/useLocalStorage';
const Login = () => {
    const [username,setUsername] = useState("username");
    const [password,setPassword] = useState("password");

    const [jwt,setJwt] = useLocalState("","jwt");
    
     const sendLoginRequest = ()=>{
      const reqBody = {
                "username": username,
                "password": password
            };

            fetch("api/auth/login", {
              "headers": {
                "content-type" : "application/json"
              },
              "method":"POST",
              "body" : JSON.stringify(reqBody)
            })
              .then((res) => {
                if(res.status == 200){
                  return Promise.all([res.json(),res.headers]);
                }else{
                  return Promise.reject("Invalid login request");
                }
              })
              .then(([body,headers]) =>{
                setJwt(headers.get("authorization"))
                window.location.href = "dashboard";
              }).catch((message)=>{
                  alert(message);
              });
              
          }
    return (
        <>
        <div>
            <label htmlFor='username'>Username</label>
            <input
            type="email"
            id="username"
            value={username}
            onChange={(event)=> setUsername(event.target.value)}
            />
        </div>
        <div>
            <label>Password</label>
            <input 
            type='password' 
            id='password' 
            value={password} 
            onChange={(event)=>setPassword(event.target.value)}/>
        </div>
        <div>
            <label></label>
            <button  type='button' id='submit' onClick={()=>sendLoginRequest()} >send</button>
       </div>

        </>
    );
};

export default Login;