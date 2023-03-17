import { useEffect} from 'react';
import { Route, Routes } from 'react-router';
import './App.css';
import useLocalState from './util/useLocalStorage';
import Dashboard from './Dashboard/Dashboard';
import HomePage from './Homepage/HomePage';

function App() {

  const [jwt,setJwt] = useLocalState("","jwt");
  
  useEffect(()=>{
    if(!jwt){
      const requestBody = {
          "username":"Sulayman",
          "password":"123456789"
      };
    
      fetch("api/auth/login", {
        "headers": {
          "content-type" : "application/json"
        },
        "method":"POST",
        "body" : JSON.stringify(requestBody)
      })
        .then((res) => Promise.all([res.json(),res.headers]))
        .then(([body,headers]) =>{
          setJwt(headers.get("authorization"))
        });
    }
  }, );

  useEffect(()=>{
    console.log(`Jwt is: ${jwt}`);
  },[jwt])
  return (
    <Routes>
      <Route path="/dashboard" element={<Dashboard/>}/>
      <Route path="/" element={<HomePage/>}/>
    </Routes>
      );
}

export default App;
