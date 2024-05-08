// import React from 'react';
// import Navbar from '../../components/Navbar';

// function CenterVaccines() {
//   return (
//     <div>
//       <Navbar />
//       <link rel="stylesheet" type="text/css" href="/assets/managecenters/ManageCenters.css" />
//       <div><img src="photo2.jpg" className='hi' alt="" /></div>


// <div>
//   <h1 className='middleh'>Here Is All the Available Vaccines Right Now here stay up to date with your Center</h1> 
//   </div>


// <div className='all'>
// <div className="gallery">
// <div className="content">
// <img src="photo.png" alt="photos" />
// <h3 className="header">Vaccine center 1</h3>
// </div>
// </div> 
//     </div>
//     </div>
//   )
// }

// export default CenterVaccines




import React, { useState, useEffect } from 'react';
import {  Link, useNavigate } from 'react-router-dom';
import Navbar from '../../components/Navbar';
import { useAuthContext } from '../Auth/hooks/useAuthContext';





function CenterVaccines() {
  const { token } = useAuthContext();
  const{decodeToken} = useAuthContext();
  const [center, setCenter] = useState(null);
  const [vaccines, setVaccines] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCenterData = async () => {
      try {
        const { id } = decodeToken(token); // Extract ID from the token

        const response = await fetch(`http://localhost:8080/api/VaccinationCenter/Owner/${id}`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        const data = await response.json();
        setCenter(data);
      } catch (error) {
        console.error('Error fetching center data:', error);
      }
    };

    if (token) {
      fetchCenterData();
    }
  }, [token]);

  useEffect(() => {
    const fetchVaccines = async () => {
      if (center && center.id) {
        try {
          const response = await fetch(`http://localhost:8080/api/VaccinationCenter/${center.id}/Vaccines`, {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          });
          const data = await response.json();
          setVaccines(data);
        } catch (error) {
          console.error('Error fetching vaccines:', error);
        }
      }
    };

    fetchVaccines();
  }, [center, token]);


















  // const handleDeleteCenter = () => {
  //   fetch(`http://localhost:8080/api/VaccinationCenter/${id}`, {
  //     method: 'DELETE',
  //     headers: {
  //       'Authorization': `Bearer ${token}`
  //     }
  //   })
  //   .then(() => {
  //     // Redirect to some page after deletion
  //     navigate('/ManageCenters');

  //   })
  //   .catch(error => console.error('Error deleting center:', error));
  // };

  return (
    <div>
      <Navbar />
      <link rel="stylesheet" href="/assets/centerdetails/CenterDetails.css" />

      <h1>Center Details:</h1>
      {center && (
        <div className="container">
          <div className="product-card">
            <div className="image">
              <img src="https://encrypted-tbn2.gstatic.com/licensed-image?q=tbn:ANd9GcTmAjfYiyYhKFdlJMZEIRQs_d-zikMcv7HP6Na9fMQAV_42s2oxipTOmbtxMSRSwCydzs86OFtvUGQeVbsK1N3COe7w7XbLzZ1LtEk4gw" alt="center" />
            </div>
            <div className="card-content">
              <h3>{center.name}</h3>
              <p>{center.address}</p>
              <div className="store-purchase">
                <div className="price">{center.contact_info}</div>
                {/* <div className="buy">
                  <Link to={`/UpdateCenter/${id}`} className='update'>Update</Link>
                  <button className='delete' onClick={handleDeleteCenter}>DELETE</button>
                </div> */}
              </div>
            </div>
          </div>
        </div>
      )}

      <div>
        <h1 className='middleh'>Manage this center's vaccines</h1>
      </div>

      <div className='all'>
        {Array.isArray(vaccines) && vaccines.map(vaccine => (
          <div className="gallery" key={vaccine.id}>
            <div className="contenttt">
              <img className='photocent' src="/photo.png" alt="vaccine" />
              <h3 className="header">{vaccine.name}</h3>
              <button className="buy-3">MODIFY Vaccine</button>
            </div>
          </div>
        ))}
      </div>

      {/* <div className='buton'>
        <button className='add'>ADD VACCINE</button>
      </div> */}
    </div>
  );
}

export default CenterVaccines;
