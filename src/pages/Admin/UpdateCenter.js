// // import React from "react";
// // import './UpdateCenter.css';
// // import Navbar from "../../components/Navbar";

// // function UpdateCenter() {
// //   return (
// //     <div> <Navbar/>
// //     <div className="UpdateCenter">
// //     <div className="containerv" >
// //       <h2>Update Vaccination Center</h2>
// //       <form action="#" method="post" className="vaccination-center-formv">
// //         <div className="form-groupv">
// //           <label>Center Name</label>
// //           <input type="text" id="center-name" name="center-name" placeholder="Enter name" required />
// //         </div>
// //         <div className="form-groupv">
// //           <label htmlFor="city">City</label>
// //           <input type="text" id="city" name="city" placeholder="Enter city" required />
// //         </div>
// //         <div className="form-groupv">
// //           <label>Address</label>
// //           <input type="text" id="address" name="address" placeholder="Update address" required />
// //         </div>
// //         <input type="submit" className="btnv" defaultValue="Update Center" />
// //       </form>
// //     </div>
// //     </div>
// //     </div>
// //   );
// // }

// // export default UpdateCenter;





// import React, { useState, useEffect } from "react";
// import './UpdateCenter.css';
// import Navbar from "../../components/Navbar";
// import { useParams } from "react-router-dom";
// import { useAuthContext } from "../Auth/hooks/useAuthContext";
// import axios from 'axios';
// import { toast } from 'react-toastify';

// function UpdateCenter() {
//   const { id } = useParams(); // Get the ID from the URL parameters

//   const{token} = useAuthContext()

//   const [formData, setFormData] = useState({
//     name: '',
//     address: '',
//     contact_info: ''
//   });

  

//   useEffect(() => {
//     // Fetch the existing vaccination center data based on the ID
//     fetch(`http://localhost:8080/api/VaccinationCenter/${id}` ,  {
//       headers: {
//         'Authorization': `Bearer ${token}`

//       }})
//       .then(response => response.json())
//       .then(data => {
//         // Set the form data with the fetched vaccination center data
//         setFormData(data);
//       })
//       .catch(error => console.error('Error fetching vaccination center data:', error));
//   }, [id,token]);

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setFormData(prevState => ({
//       ...prevState,
//       [name]: value
//     }));
//   };

//   console.log(token);

//   // const handleSubmit = (e) => {
//   //   e.preventDefault();
    
//   //   // Make a PUT request to update the vaccination center
//   //   fetch(`http://localhost:8080/api/VaccinationCenter/${id}`, {
//   //     method: 'PUT',
//   //     headers: {
//   //       'Authorization': `Bearer ${token}`
//   //     },

//   //     body: JSON.stringify(formData)
//   //   })
//   //   .then(response => {
//   //     // console.log(token);

//   //     console.log(response);
//   //     if (response.ok) {
//   //       // Handle success
//   //       console.log('Vaccination center updated successfully');
//   //     } else {
//   //       // Handle error
//   //       console.error('Failed to update vaccination center');
//   //     }
//   //   })
//   //   .catch(error => console.error('Error updating vaccination center:', error));
//   // };



//   const handleSubmit2 = (e) => {
//     e.preventDefault();
//     axios.put(`http://localhost:8080/api/VaccinationCenter/${id}`, formData, {  
//      headers: {
//       'Authorization': `Bearer ${token}`
//     }})
//       .then(response => {
//         console.log('Vaccination center updated successfully:', response.data);
//         toast.success('Vaccination center updated successfully');
//       })
//       .catch(error => {
//         console.error('Error updating vaccination center:', error);
//         toast.error('Failed to update vaccination center');
//       });
//   };
  

//   return (
//     <div>
//       <Navbar/>
//       <div className="UpdateCenter">
//         <div className="containerv" >
//           <h2>Update Vaccination Center</h2>
//           <form onSubmit={handleSubmit2} className="vaccination-center-formv">
//             <div className="form-groupv">
//               <label>Center Name</label>
//               <input type="text" id="center-name" name="name" value={formData.name} onChange={handleChange} placeholder="Enter name" required />
//             </div>
//             <div className="form-groupv">
//               <label>Address</label>
//               <input type="text" id="address" name="address" value={formData.address} onChange={handleChange} placeholder="Update address" required />
//             </div>
//             <div className="form-groupv">
//               <label>Contact Info</label>
//               <input type="text" id="contact-info" name="contact_info" value={formData.contact_info} onChange={handleChange} placeholder="Update contact info" required />
//             </div>
//             <input type="submit" className="btnv" defaultValue="Update Center" />
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// }

// export default UpdateCenter;












import React, { useState, useEffect } from "react";
import './UpdateCenter.css';
import Navbar from "../../components/Navbar";
import { useParams } from "react-router-dom";
import { useAuthContext } from "../Auth/hooks/useAuthContext";

function UpdateCenter() {
  const { id } = useParams(); // Get the ID from the URL parameters

  const {token} = useAuthContext();
  const [formData, setFormData] = useState({
    name: '',
    address: '',
    contact_info: ''
  });

  

  useEffect(() => {
    // Fetch the existing vaccination center data based on the ID
    fetch(`http://localhost:8080/api/VaccinationCenter/${id}` , {
      headers: {
        'Authorization': `Bearer ${token}`
      }})
      .then(response => response.json())
      .then(data => {
        // Set the form data with the fetched vaccination center data
        setFormData(data);
      })
      .catch(error => console.error('Error fetching vaccination center data:', error));
  }, [id,token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Make a PUT request to update the vaccination center
    fetch(`http://localhost:8080/api/VaccinationCenter/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(formData)
    })
    .then(response => {
      if (response.ok) {
        // Handle success
        console.log('Vaccination center updated successfully');
      } else {
        // Handle error
        console.error('Failed to update vaccination center');
      }
    })
    .catch(error => console.error('Error updating vaccination center:', error));
  };

  return (
    <div>
      <Navbar/>
      <div className="UpdateCenter">
        <div className="containerv" >
          <h2>Update Vaccination Center</h2>
          <form onSubmit={handleSubmit} className="vaccination-center-formv">
            <div className="form-groupv">
              <label>Center Name</label>
              <input type="text" id="center-name" name="name" value={formData.name} onChange={handleChange} placeholder="Enter name" required />
            </div>
            <div className="form-groupv">
              <label>Address</label>
              <input type="text" id="address" name="address" value={formData.address} onChange={handleChange} placeholder="Update address" required />
            </div>
            <div className="form-groupv">
              <label>Contact Info</label>
              <input type="text" id="contact-info" name="contact_info" value={formData.contact_info} onChange={handleChange} placeholder="Update contact info" required />
            </div>
            <input type="submit" className="btnv" defaultValue="Update Center" />
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateCenter;