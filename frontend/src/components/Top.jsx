import React from 'react'
import { Link } from 'react-router-dom'
import { useCookies } from "react-cookie";


const Top = () => {
  const [cookies, setCookie, removeCookie] = useCookies(null)
  const signOut = () => {
    console.log("signout")
    removeCookie('Email')
    removeCookie('AuthToken')
    window.location.reload()
  }
  return (
    <div className='top'>
      <div className='nav-routes'>
      <Link className="router-link" to="/active">Activas</Link>
      <Link className="router-link" to="/archived">Archivadas</Link>
      <Link className="router-link" to="/filterNotes">Filtrar</Link>
        </div>
        <button className="signout" onClick={signOut}>Salir</button>
    </div>
  )
}

export default Top

 