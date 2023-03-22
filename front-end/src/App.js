import { useEffect} from 'react';
import { Route, Routes } from 'react-router';
import './App.css';
import useLocalState from './util/useLocalStorage';
import Dashboard from './Dashboard/Dashboard';
import HomePage from './Homepage/HomePage';
import Login from './Login/Login';
import PrivateRoute from './PrivateRoute/PrivateRoute';
import AssignmentView from './AssignmentView/AssignmentView';

function App() {

  const [jwt,setJwt] = useLocalState("","jwt");

  useEffect(()=>{
  },[jwt])
  return (
    <Routes>

      <Route path="/dashboard" element={
          <PrivateRoute>
            <Dashboard/>
          </PrivateRoute>
        }
      />
      <Route path="/assignments/:id" element={
        <PrivateRoute>
          <AssignmentView/>
      </PrivateRoute>
      }/>
      <Route path="/login" element={<Login/>}/>
      <Route path="/" element={<HomePage/>}/>
    </Routes>
      );
}

export default App;
