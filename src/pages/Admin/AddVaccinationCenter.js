// import React, { useState } from 'react';
// import Navbar from '../../components/Navbar';
// import axios from 'axios';
// import { toast } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';

// function AddVaccinationCenter() {
//   const [formData, setFormData] = useState({
//     Name: '',
//     Address: '',
//     ContactInfo: '',
//     ManagerId: ''
//   });

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setFormData(prevState => ({
//       ...prevState,
//       [name]: value
//     }));
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     axios.post('http://localhost:8080/api/VaccinationCenter', formData)
//     .then(response => {
//       console.log('Vaccination center added successfully:', response.data);
//       toast.success('Vaccination center added successfully');
//     })
//     .catch(error => {
//       console.error('Error adding vaccination center:', error);
//       toast.error('Failed to add vaccination center');
//     });
//   };
  
//   return (
//     <div>
//       <Navbar />
//       <div className='bodyy'>
//         <div className="bashcontainer">
//           <h2>Add Vaccination Center</h2>
//           <form onSubmit={handleSubmit} className="vaccination-center-form">
//             <div className="form-group">
//               <label htmlFor="Name">Center Name</label>
//               <input type="text" id="Name" name="Name" value={formData.Name} onChange={handleChange} placeholder="Enter name" required />
//             </div>
//             <div className="form-group">
//               <label htmlFor="Address">Address</label>
//               <input type="text" id="Address" name="Address" value={formData.Address} onChange={handleChange} placeholder="Enter address" required />
//             </div>
//             <div className="form-group">
//               <label htmlFor="ContactInfo">Contact Info</label>
//               <input type="text" id="ContactInfo" name="ContactInfo" value={formData.ContactInfo} onChange={handleChange} placeholder="Enter contact info" required />
//             </div>
//             <div className="form-group">
//               <label htmlFor="ManagerId">Manager Id</label>
//               <input type="text" id="ManagerId" name="ManagerId" value={formData.ManagerId} onChange={handleChange} placeholder="Enter manager ID" required />
//             </div>
//             <input type="submit" className="btnn" value="Add Center" />
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// }

// export default AddVaccinationCenter;









import React, { useState } from 'react';
import Navbar from '../../components/Navbar';
import axios from 'axios';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useAuthContext } from '../Auth/hooks/useAuthContext';

function AddVaccinationCenter() {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    role: 'VACCINATION_CENTER', // Static value for role
    Name: '',
    Address: '',
    ContactInfo: ''
  });


  const {token} = useAuthContext();
   const { decodeToken } = useAuthContext();
   

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Extract username, email, password, and role from the form data
    const { username, email, password, role } = formData;

    // Send the first POST request to create a new user with username, email, password, and role
    axios.post('http://localhost:8080/api/v1/auth/register', { username, email, password, role })
      .then(response => {
        // Extract the ID from the token in the response
        const { id } = decodeToken(response.data.token);
        
        // Update the form data with the extracted ID
        const updatedFormData = {
          ...formData,
          ManagerId: { id } // Set ManagerId as an object with the structure { id: '' }
        };

        // Extract the relevant fields for the second POST request
        const { Name, Address, ContactInfo, ManagerId } = updatedFormData;

        // Send the second POST request with the updated form data containing ID, Name, Address, and ContactInfo
        axios.post('http://localhost:8080/api/VaccinationCenter', {
          name: Name,
          address: Address,
          contact_info: ContactInfo,
          users: ManagerId
        }, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        })
          .then(response => {
            console.log('Vaccination center added successfully:', response.data);
            toast.success('Vaccination center added successfully');
          })
          .catch(error => {
            console.error('Error adding vaccination center:', error);
            toast.error('Failed to add vaccination center');
          });
      })
      .catch(error => {
        console.error('Error creating user:', error);
        toast.error('Failed to create user');
      });
  };


  return (
    <div>
      <Navbar />
      <div className='bodyy'>
        <div className="bashcontainer">
          <h2>Add Vaccination Center</h2>
          <form onSubmit={handleSubmit} className="vaccination-center-form">
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <input type="text" id="username" name="username" value={formData.username} onChange={handleChange} placeholder="Enter username" required />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} placeholder="Enter email" required />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} placeholder="Enter password" required />
            </div>
            <div className="form-group">
              <label htmlFor="Name">Center Name</label>
              <input type="text" id="Name" name="Name" value={formData.Name} onChange={handleChange} placeholder="Enter name" required />
            </div>
            <div className="form-group">
              <label htmlFor="Address">Address</label>
              <input type="text" id="Address" name="Address" value={formData.Address} onChange={handleChange} placeholder="Enter address" required />
            </div>
            <div className="form-group">
              <label htmlFor="ContactInfo">Contact Info</label>
              <input type="text" id="ContactInfo" name="ContactInfo" value={formData.ContactInfo} onChange={handleChange} placeholder="Enter contact info" required />
            </div>
            <input type="submit" className="btnn" value="Add Center" />
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddVaccinationCenter;





















