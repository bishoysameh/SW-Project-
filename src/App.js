// App.js
import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { AuthContextProvider } from './pages/Auth/AuthContext';
import AddVaccinationCenter from './pages/Admin/AddVaccinationCenter';
import AddVaccine from './pages/Admin/AddVaccine';
import CenterDetails from './pages/Admin/CenterDetails';
import ManageCenters from './pages/Admin/ManageCenters';
import ManageUsers from './pages/Admin/ManageUsers';
import VaccinesDetails from './pages/Admin/VaccinesDetails';
import Login from './pages/Auth/Login';
import SignUp from './pages/Auth/SignUp';
import ListCenters from './pages/Patient/ListCenters';
import PatientCenterDetails from './pages/Patient/PatientCenterDetails';
import PatientVaccineDetails from './pages/Patient/PatientVaccineDetails';
import ViewCertificate from './pages/Patient/ViewCertificate';
import CenterVaccines from './pages/VaccinationCenter/CenterVaccines';
import PatientList from './pages/VaccinationCenter/PatientList';
import UploadCertificate from './pages/VaccinationCenter/UploadCertificate';
import UpdateCenter from './pages/Admin/UpdateCenter';
import UpdateVaccine from './pages/Admin/UpdateVaccine';
import Page404 from './pages/Page404';
import NotAuthorized from './pages/Auth/NotAuthorized';
import PrivateRoute from './pages/Auth/PrivateRoute';
import CenterManager from './pages/Admin/CenterManager';
import ManageCertificate from './pages/VaccinationCenter/ManageCertificate';

function App() {
  return (
    <Router>
      <AuthContextProvider>
        <div className="App">
          <ToastContainer />
          <div className="pages">
            <Routes>
              <Route path="/AddVaccinationCenter" element={<PrivateRoute element={<AddVaccinationCenter />} roles={['ADMIN']} />} />
              <Route path="/AddVaccine/:id" element={<PrivateRoute element={<AddVaccine />} roles={['ADMIN']} />} />
              {/* <Route path="/AddVaccine" element={<PrivateRoute element={<AddVaccine />} roles={['ADMIN']} />} /> */}
              <Route path="/UpdateCenter/:id" element={<PrivateRoute element={<UpdateCenter />} roles={['ADMIN']} />} />
              <Route path="/UpdateVaccine/:id" element={<PrivateRoute element={<UpdateVaccine />} roles={['ADMIN']} />} />
              <Route path="/CenterDetails/:id" element={<PrivateRoute element={<CenterDetails />} roles={['ADMIN']} />} />
              <Route path="/ManageCenters" element={<PrivateRoute element={<ManageCenters />} roles={['ADMIN']} />} />
              <Route path="/ManageUsers" element={<PrivateRoute element={<ManageUsers />} roles={['ADMIN']} />} />
              <Route path="/VaccinesDetails/:id" element={<PrivateRoute element={<VaccinesDetails />} roles={['ADMIN']} />} />
              <Route path="/CenterManager" element={<PrivateRoute element={<CenterManager />} roles={['ADMIN']} />} />

              <Route path="/ListCenters"  element={<ListCenters />}  />
              <Route path="/PatientCenterDetails/:id" element={<PatientCenterDetails />}  />
              <Route path="/PatientVaccineDetails/:id"  element={<PatientVaccineDetails />}  />
              <Route path="/ViewCertificate"  element={<ViewCertificate />} />


              <Route path="/CenterVaccines" element={<PrivateRoute element={<CenterVaccines />} roles={['VACCINATION_CENTER']} />} />
              <Route path="/PatientList" element={<PrivateRoute element={<PatientList />} roles={['VACCINATION_CENTER']} />} />
              <Route path="/UploadCertificate" element={<PrivateRoute element={<UploadCertificate />} roles={['VACCINATION_CENTER']} />} />
              <Route path="/ManageCertificate" element={<PrivateRoute element={<ManageCertificate />} roles={['VACCINATION_CENTER']} />} />
              {/* <Route path="/managecertificate" element={<ManageCertificate />} /> */}

              <Route path="/" element={<PrivateRoute element={<Login />} path="/login" roles={['ADMIN', 'USER','VACCINATION_CENTER','null']} />} />
              <Route path="/NotAuthorized" element={<NotAuthorized />} /> 
              <Route path="/login" element={<PrivateRoute element={<Login />} path="/login" roles={['ADMIN', 'USER','VACCINATION_CENTER','null']} />} />
              <Route path="/SignUp" element={<PrivateRoute  path="/SignUp" element={<SignUp />} roles={['ADMIN', 'USER','VACCINATION_CENTER','null']} />} />
              <Route path="*" element={<Page404 />} />

            </Routes>
          </div>
        </div>
      </AuthContextProvider>
    </Router>
  );
}

export default App;














// * <div className='all'>
// <div className="gallery">
// <div className="content">
// <img src="photo.png" alt="photo" />
// <h3 className="header">Vaccine center 1</h3>
// <button className="buy-3">see details</button>
// </div>
// </div> 

// <button className="addcenter">ADD Center</button> 