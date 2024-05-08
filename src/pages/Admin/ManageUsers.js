import React, { useState, useEffect } from 'react';
import Navbar from '../../components/Navbar';

 import { useAuthContext } from '../Auth/hooks/useAuthContext';

function ManageUsers() {


   const {token} = useAuthContext();


  const [pendingUsers, setPendingUsers] = useState([]);

  useEffect(() => {
    fetchPendingUsers();
  }, [token]);

  const fetchPendingUsers = () => {
    fetch('http://localhost:8080/api/User/Patients',{
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
      .then(response => response.json())
      .then(data => setPendingUsers(data))
      .catch(error => console.error('Error fetching pending users:', error));
  };

  const handleApproveUser = (userId) => {
    // fetch(`http://localhost:8080/api/account/approve-user?userId=${userId}`, {
     fetch(`http://localhost:8080/api/v1/auth/${userId}/approve`,{
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })













    


    .then(response => {
      if (response.ok) {
        // Refresh the pending users list
        fetchPendingUsers();
      } else {
        console.error('Failed to approve user');
      }
    })
    .catch(error => console.error('Error approving user:', error));
  };

  const handleRejectUser = (userId) => {
    fetch(`http://localhost:8080/api/account/reject-user?userId=${userId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
    .then(response => {
      if (response.ok) {
        // Refresh the pending users list
        fetchPendingUsers();
      } else {
        console.error('Failed to reject user');
      }
    })
    .catch(error => console.error('Error rejecting user:', error));
  };

  return (
    <div>
      <Navbar /> 
      <div className='boddyyy'>
        <link rel="stylesheet" type="text/css" href="/assets/manageusers/ManageUsers.css" />
        <main className="table" id="customers_table">
          <section className="table__header">
            <h1>Manage Users</h1>
          </section>
          <section className="table__body">
            <table>
              <thead>
                <tr>
                  <th> </th>
                  <th> Username </th>
                  <th> Email </th>
                  <th> Accept </th>
                  <th> Reject </th>
                </tr>
              </thead>
              <tbody>
                {pendingUsers.map(user => (
                  <tr key={user.id}>
                    <td> {user.id} </td>
                    <td> <strong>{user.username}</strong> </td>
                    <td> {user.email} </td>
                    <td>
                      <button className="status delivered" onClick={() => handleApproveUser(user.id)}>Accept</button>
                    </td>
                    <td>
                      <button className="status cancelled" onClick={() => handleRejectUser(user.id)}>Reject</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </section>
        </main>
      </div>
    </div>
  );
}

export default ManageUsers;
