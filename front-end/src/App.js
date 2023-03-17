import './App.css';

function App() {
  console.log("Hello!")
  const requestBody = {
      "username":"Sulayman",
      "password":"123456789"
  }
  fetch("api/auth/login", {
    "headers": {
      "content-type" : "application/json"
    },
    "method":"POST",
    "body" : JSON.stringify(requestBody)
  })
    .then((res) =>{
    return Promise.all([res.json(),res.headers]);
  })
    .then(([body,headers]) =>{
      const autValue = headers.get("authorization")
      console.log(autValue);
      console.log(body);
    } );
   
  return (
    <div className="App">
     <h1>Hello World!</h1> 
    </div>
  );
}

export default App;
