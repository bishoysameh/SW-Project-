// import Navbar from '../../components/Navbar'
// import './UpdateVaccine.css'

// function UpdateVaccine() {
//   return (
//    <div> <Navbar/>
//     <div className="UpdateVaccine">

//     <div>
//   <meta charSet="UTF-8" />
//   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 
//   <div className="containervv">
//     <form action="#" method="post">
//       <h2 className="form-titlevv">update vaccine</h2>
//       <br />
//       <div className="form-groupvv">
//         <label htmlFor="name">Name</label>
//         <input type="text" id="name" name="vaccine name" placeholder="Enter Vaccine Name" required />
//       </div>
//       <div className="form-groupvv">
//         <label htmlFor="type">Type</label>
//         <input type="text" id="type" name="type" placeholder="Enter Vaccine Type" required />
//       </div>
//       <div className="form-groupvv">
//         <label htmlFor="code">Code</label>
//         <input type="text" id="code" name="code of vaccine" placeholder="Enter Vaccine Code" required />
//       </div>
//       <div>
//         <button type="submit" className="btnvv">Update</button>
//       </div>
//     </form>
//   </div>
// </div>

//     </div>
//     </div>
//   )
// }

// export default UpdateVaccine



import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../../components/Navbar';
import './UpdateVaccine.css';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useAuthContext } from '../Auth/hooks/useAuthContext';

function UpdateVaccine() {
  const { id } = useParams(); // Get the ID from the URL parameters
 const {token} = useAuthContext();
  // Define state variables to hold vaccine details
  const [vaccine, setVaccine] = useState({
    name: '',
    precautions: '',
    timeGapFirstSecondDose: ''
  });

  // Fetch vaccine details using the ID
  useEffect(() => {
    fetch(`http://localhost:8080/api/Vaccine/${id}` , {
      headers: {
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

  // Function to handle form submission (update)
  const handleSubmit = (e) => {
    e.preventDefault();
    // Make a PUT request to update the vaccine details
    fetch(`http://localhost:8080/api/Vaccine/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`

      },
      body: JSON.stringify(vaccine)
    })
    .then(response => {
      if (response.ok) {
        // Handle success
        toast.success('Vaccine updated successfully');
      } else {
        // Handle error
        console.error('Failed to update vaccine');
      }
    })
    .catch(error => console.error('Error updating vaccine:', error));
  };

  // Function to handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setVaccine(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  return (
    <div>
      <Navbar />
      <div className="UpdateVaccine">
        <div className="containervv">
          <form onSubmit={handleSubmit}>
            <h2 className="form-titlevv">Update Vaccine</h2>
            <br />
            <div className="form-groupvv">
              <label htmlFor="name">Name</label>
              <input type="text" id="name" name="name" value={vaccine.name} onChange={handleChange} placeholder="Enter Vaccine Name" required />
            </div>
            <div className="form-groupvv">
              <label htmlFor="precautions">Precautions</label>
              <input type="text" id="precautions" name="precautions" value={vaccine.precautions} onChange={handleChange} placeholder="Enter Precautions" required />
            </div>
            <div className="form-groupvv">
              <label htmlFor="timeGapFirstSecondDose">Time Gap First Second Dose In Days</label>
              <input type="text" id="timeGapFirstSecondDose" name="timeGapFirstSecondDose" value={vaccine.timeGapFirstSecondDose} onChange={handleChange} placeholder="Enter Time Gap" required />
            </div>
            <div>
              <button type="submit" className="btnvv">Update</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateVaccine;
 