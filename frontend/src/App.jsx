import Auth from "./components/Auth";
import { useCookies } from "react-cookie";
import Archived from './components/Archived';
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom';
import Active from './components/Active';
import Top from './components/Top';
import FilterNotes from './components/FilterNotes';


function App() {
  const [cookies, setCookie, removeCookie] = useCookies(null)
  const authToken = cookies.AuthToken
  const userEmail = cookies.Email
  const userName = cookies.UserName
  return (
    <div className="app" >
 {!authToken && <Auth/>}

 {authToken &&
    <>
     <BrowserRouter>
     <p className='user-active'>Bienvenido {userName}</p>
     <Top/>
     <Routes>
     <Route path="/" element={<Active />} />
     <Route index path="active" element={<Active />} />
     <Route path="archived" element={<Archived />} />
     <Route path="filterNotes" element={<FilterNotes />} />
     </Routes>
 
     </BrowserRouter>
    </> }
    <p className="copyright">© NotesApp</p>
  </div>
  )
}

export default App
