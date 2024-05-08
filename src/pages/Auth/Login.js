import React, { useState } from 'react';
import './Login.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import Navbar from '../../components/Navbar';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { dispatch, decodeToken } = React.useContext(AuthContext); 
  const navigate = useNavigate();
  const handleUsernameChange = (event) => {
    setEmail(event.target.value);
  };
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/v1/auth/authenticate', {
        email,
        password
      });

      if (response && response.data.token) {
        dispatch({ type: 'LOGIN', payload: response.data });
        const decodedToken = decodeToken(response.data.token);
        const role = decodedToken ? decodedToken.role : null;
        console.log(role+"aaaaaaaaaaaaaa");
        switch (role) {
          case 'ADMIN':
            navigate('/ManageCenters');
            break;
          case 'VACCINATION_CENTER':
            navigate('/CenterVaccines');
            break;
          default:
            navigate('/ListCenters');
        }
      } else {
        toast.error(response.data.message);
        console.log(response.data.message);
      }
    } catch (error) {
      console.error('Login failed:', error);
      toast.error('An error occurred. Please try again later.');
    }
  };

  return (
    <div>
      <Navbar />
      <div className="loginBackground">
        <div className="login-boxv">
          <h2>Login</h2>
          <form onSubmit={handleSubmit}>
            <div className="user-boxv">
              <input
                type="text"
                name="username"
                value={email}
                onChange={handleUsernameChange}
                required
              />
              <label>Username</label>
            </div>
            <div className="user-boxv">
              <input
                type="password"
                name="password"
                value={password}
                onChange={handlePasswordChange}
                required
              />
              <label>Password</label>
            </div>
            <button type="submit">Submit</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
