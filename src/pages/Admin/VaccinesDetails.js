// import React from 'react';
// import Navbar from '../../components/Navbar';

// function VaccinesDetails() {
//   return (
//     <div>
//       <Navbar />
       


//        <link rel="stylesheet" href="/assets/vaccinedetails/VaccinesDetails.css"></link>
     
//        <h1>VaccinesDetails</h1>
//        <div className="container">
//   <div className="product-card">
//     <div className="image">
//       <img src="/photo.png" alt />
//     </div>
//     <div className="card-content">
//       <h3>vaccine name</h3>
//       <p>precautions</p>
//       <div className="store-purchase">
//         <div className="buy">
//         <button className='update'>Update</button>
//           <button className='delete'>DELETE</button>
//         </div>
//       </div>
//     </div>
//   </div>
// </div>
//     </div>
//   )
// }

// export default VaccinesDetails


import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Navbar from '../../components/Navbar';
import { useAuthContext } from '../Auth/hooks/useAuthContext';

function VaccinesDetails() {
  const { id } = useParams(); // Get the ID from the URL parameters
  const navigate = useNavigate(); // Use navigate function for navigation

  const {token} = useAuthContext();
  // Define state variables to hold vaccine details
  const [vaccine, setVaccine] = useState({
    name: '',
    precautions: '',
    timeGapFirstSecondDose: 0
  });

  // Fetch vaccine details using the ID
  useEffect(() => {
    fetch(`http://localhost:8080/api/Vaccine/${id}`, {
      headers:{
        'Authorization': `Bearer ${token}`
      }
    })
      .then(response => response.json())
      .then(data => {
        // Update state with vaccine details
        setVaccine(data);
      })
      .catch(error => console.error('Error fetching vaccine details:', error));
  }, [id]);

  // Function to handle navigation to the update vaccine page
  const handleUpdateVaccine = () => {
    navigate(`/updatevaccine/${id}`);
  };

  // Function to handle deletion of the vaccine
  const handleDeleteVaccine = () => {
    fetch(`http://localhost:8080/api/Vaccine/${id}`, {
      method: 'DELETE',
      headers:{
        'Authorization': `Bearer ${token}`
      }
    })
    .then(() => {
      // Redirect to some page after deletion
      navigate('/somepage');
    })
    .catch(error => console.error('Error deleting vaccine:', error));
  };

  return (
    <div>
      <Navbar />
      <link rel="stylesheet" href="/assets/vaccinedetails/VaccinesDetails.css" />

      <h1>VaccinesDetails</h1>
      <div className="container">
        <div className="product-card">
          <div className="image">
            <img src="/photo.png" alt="vaccine" />
          </div>
          <div className="card-content">
            <h3>{vaccine.name}</h3>
            <p>{vaccine.precautions}</p>
            <p>{vaccine.timeGapFirstSecondDose}</p>
            <div className="store-purchase">
              <div className="buy">
                <button className='update' onClick={handleUpdateVaccine}>Update</button>
                <button className='delete' onClick={handleDeleteVaccine}>DELETE</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default VaccinesDetails;