import { useState } from "react";
import { useCookies } from "react-cookie";
 
const Auth = () => {
  const [cookies, setCookie, removeCookie] = useCookies(null)
  const [error, setError] = useState(null)
  const [nombre, setNombre] = useState(null)
  const [email, setEmail] = useState(null)
  const [contraseña, setContraseña] = useState(null)
  const [confirmarContraseña, setConfirmarContraseña] = useState(null)
  const [isLogin, setIsLogin] = useState(true)

  const viewLogin = (status) => {
    setError("")
    setIsLogin(status)
  }

  const handleSubmit = async (e, endpoint) => {
    e.preventDefault() // para evitar el reloading


    if (!isLogin) {
      if (email === null || nombre === null || contraseña == null) {
        setError("Todos los campos son obligatorios")
        return
      }
      if (contraseña !== confirmarContraseña) {
        setError("Ambas contraseñas deben coincidir")
        return
      }

      const response = await fetch(`${import.meta.env.VITE_APP_SERVER_URL}/usuarios`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nombre, email, contraseña })
      })

      if (!response.ok) {
        const errorText = await response.text();
        setError(errorText)

      }
      if (response.ok) {
        const data = await response.json();
        alert("Ahora puede loguearte...")
        setIsLogin(true)
        window.location.reload()
      }

    } else {
      if (email === null || contraseña == null) {
        setError("Todos los campos son obligatorios")
        return
      }
      const response = await fetch(`${import.meta.env.VITE_APP_SERVER_URL}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, contraseña })
      })

      if (!response.ok) {
        const errorText = await response.text();
        setError(errorText)

      } else {
        const data = await response.json();
        setCookie('Email', data.email)
        setCookie('AuthToken', data.jwTtoken)
        setCookie('UserName', data.name)
        window.location.reload()
      }
    }
  }

  return (
    <div className="auth-container" >
      <div className="auth-container-box">
        <form>
          <h2>{isLogin ? 'Por favor, logueate' : 'Por favor, regístrate'}</h2>
          {!isLogin &&
            <input
              type="text"
              placeholder="Nombre"
              onChange={(e) => setNombre(e.target.value)}
            />}
          <input
            type="email"
            placeholder="Email"

            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            placeholder="Contraseña"

            onChange={(e) => setContraseña(e.target.value)}
          />
          {!isLogin && <input
            type="password"
            placeholder="Confirma la contraseña"
            onChange={(e) => setConfirmarContraseña(e.target.value)}
          />} {error && <p className="error">{error}</p>}
          <input type="submit" className="register" onClick={(e) => handleSubmit(e, isLogin ? 'login' : 'usuarios')} />

        </form>
        <div className="auth-options">
          <button
            onClick={() => viewLogin(false)}
            style={{ backgroundColor: !isLogin ? 'rgb(255,255,255)' : 'rgb(188,188,188)' }}
          >Registrarse</button>
          <button
            onClick={() => viewLogin(true)}
            style={{ backgroundColor: isLogin ? 'rgb(255,255,255)' : 'rgb(188,188,188)' }}
          >Loguearse</button>
        </div>
      </div>
    </div>
  );
}

export default Auth;
