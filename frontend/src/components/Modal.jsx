
import { useState, useEffect } from "react"
import { useCookies } from "react-cookie";
import { requestFetchData } from '../service/apiService';

const Modal = ({ mode, setShowModal, note, getData }) => {
    const [cookies, setCookie, removeCookie] = useCookies(null)
    const editMode = mode === "editar" ? true : false
    const [categorias, setCategorias] = useState([])
    const [data, setData] = useState({
        email_usuario: cookies.Email,
        id: editMode ? note.id : "",
        titulo: editMode ? note.titulo : "",
        categoria: editMode ? note.categoria : 'General',
        cuerpo: editMode ? note.cuerpo : '',
        estado: editMode ? note.estado : "ACTIVO"
    })

    useEffect(() => {
       // if (cookies.AuthToken) {
            getCategorias()
       // }
    }, [])

    const getCategorias = async (e) => {
        const response = await requestFetchData('categorias', 'GET')
        if (response.status === 200) {
            const json = await response.json()
            setCategorias(json)
        }
    }

    const postNote = async (e) => {
        e.preventDefault()
        const response = await requestFetchData('notas', 'POST', data)
        if (response.status === 201) {
            setShowModal(false)
            getData()
        }

    }

    const editNote = async (e) => {
        e.preventDefault()
        const response = await requestFetchData('notas', 'PUT', data)
        if (response.status === 200) {
            setShowModal(false)
            getData()
        }

    }
    const checkSelected = (cat) => {
        return cat.titulo === data.categoria ? { selected: true } : {};
    }

    const estado = { "ACTIVO": "ARCHIVADO", "ARCHIVADO": "ACTIVO" }[data.estado];
    const handleChange = (e) => {
        console.log("changing...", e.target.name)
        const { name, value } = e.target
        setData(data => ({
            ...data,
            [name]: value
        }))
    }
    return (
        <div className="overlay">
            <div className="modal">
                <div className="form-title-container">
                    <h3>{ }Vamos a {mode} tu nota</h3>
                    <button onClick={() => setShowModal(false)}>X</button>
                </div>
                <form>
                    <input required maxLength={30} placeholder=" El titulo de tu nota va acá" name="titulo" value={data.titulo} onChange={handleChange} />
                    <label htmlFor="select-category">Elige una categoría:</label>
                    <select id="select-category" name="categoria" value={data.categoria} onChange={handleChange}>
                        {categorias?.map((categoria) => <option key={categoria.titulo} value={categoria.titulo} >{categoria.titulo} </option>)}
                    </select>
                    <input required maxLength={200} placeholder=" El cuerpo de tu nota" name="cuerpo" value={data.cuerpo} onChange={handleChange} />
                    {editMode &&
                        <><label htmlFor="select-state">Define estado:</label>
                            <select id="select-state" name="estado" onChange={handleChange}>
                                <option key={data.estado} value={data.estado}>{data.estado}</option>
                                <option key={estado} value={estado}>{estado}</option>
                            </select>

                        </>
                    }
                    <input className={mode} type="submit" onClick={editMode ? editNote : postNote} />
                </form>
            </div>
        </div>
    );
}

export default Modal;
