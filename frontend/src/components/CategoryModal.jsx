
import { useState, useEffect, useRef } from "react"
 
import { requestFetchData } from '../service/apiService';

const CategoryModal = ({ setShowCategoryModal }) => {
 
    const [categorias, setCategorias] = useState([])
    const [error, setError] = useState(null)
    const inputRef = useRef(null);

    useEffect(() => {
      //  if (cookies.AuthToken) {
            getCategorias()
      //  }
    }, [])

    const postData = async (e) => {
        let allchecked = true;
        e.preventDefault()
        setError(null);
        const titulo = inputRef.current.value
        if (titulo === "") {
            setError("Escribe algo al menos...")
            allchecked = false;
            return
        }
        if (titulo.length < 3) {
            setError("Mínmo 3 letras...")
            allchecked = false;
            return
        }
        categorias?.forEach((c) => {
            if (titulo.toLowerCase() === c.titulo.toLowerCase()) {
                setError("Ya existe esa categoría...")
                allchecked = false;
                return
            }
        })
        if (allchecked) {
            const response = await requestFetchData('categorias', 'POST', { "titulo": titulo })
            if (!response.ok) {
                const errorText = await response.text(); // Extrae el mensaje de error
                setError(errorText)
            }
            if (response.status === 200) {
                setShowCategoryModal(false)
            }

        }
    }
    const getCategorias = async (e) => {
        const response = await requestFetchData('categorias', 'GET')
        if (response.status === 200) {
            const json = await response.json()
            setCategorias(json)
        }

    }

    return (
        <div className="overlay">
            <div className="modal">
                <div className="form-title-container">
                    <h3>Crea una categoria</h3>
                    <button onClick={() => setShowCategoryModal(false)}>X</button>
                </div>
                <form>
                    <input required maxLength={30} placeholder="Tu nueva categoría aquí" name="titulo" ref={inputRef} onChange={() => setError("")} />
                    {error && <p className="error">{error}</p>}
                    <input className="crear" type="submit" value="CREAR CATEGORIA" onClick={postData} />
                </form>
            </div>
        </div>
    );
}

export default CategoryModal;
