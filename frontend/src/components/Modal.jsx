
import { useState, useEffect } from "react"
import { useCookies } from "react-cookie";
import { requestFetchData } from '../service/apiService';
import Capsule from "./Capsule";

const Modal = ({ mode, setShowModal, note, getData }) => {
    const [cookies, setCookie, removeCookie] = useCookies(null)
    const editMode = mode === "editar" ? true : false
    const [categoriasDisponibles, setCategoriasDisponibles] = useState([])
    const [data, setData] = useState({
        email_usuario: cookies.Email,
        id: editMode ? note.id : 0,
        titulo: editMode ? note.titulo : "",
        categorias: editMode ? note.categorias : [],
        cuerpo: editMode ? note.cuerpo : '',
        estado: editMode ? note.estado : "ACTIVO"
    })

    useEffect(() => {
       // if (cookies.AuthToken) {
            getCategorias()
            console.log("RELOD")
       // }
    }, [])

 

    const getCategorias = async (e) => {
        const response = await requestFetchData('categorias', 'GET')
        if (response.status === 200) {
            const json = await response.json()
            if(editMode) {
            const disponibles = json.filter(t=>!isPresent(t,data.categorias))
       
            setCategoriasDisponibles(disponibles) }

            else {
                setCategoriasDisponibles(json)}
     
        }
    }

    const postNote = async (e) => {
        e.preventDefault()
        const response = await requestFetchData('notas', 'POST', data)
        if (response.status === 200) {
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

    const isPresent = (target, categorias) => {
       /* if(categorias==null) return false
        console.log("=====",target.titulo,"======")
        for (let index = 0; index < categorias.length; index++) {
            const element = categorias[index];
            if (element.titulo == target.titulo) return true
            
        }
        return false;*/
        return categorias?.map(t=>t.titulo).includes(target.titulo)

    }
    const deleteCategoria = (e,categoria) => {
        e.preventDefault()
        console.log(categoria)
    
        setData(data => ({
            ...data,
            categorias: [...data.categorias.filter(el => el.titulo !== categoria.titulo)]
        }))

        setCategoriasDisponibles([...categoriasDisponibles,categoria])
     

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

    const handleChangeCategory = (e) => {
        console.log("___________")
        console.log(e.target.value)
        console.log(...categoriasDisponibles.filter(t=>t.titulo==e.target.value))
        setData(data => ({
            ...data,
            categorias: [...data.categorias,...categoriasDisponibles.filter(t=>t.titulo==e.target.value)]
        }))
        setCategoriasDisponibles([...categoriasDisponibles.filter(el => el.titulo !== e.target.value)])
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
                    <input required maxLength={200} placeholder=" El cuerpo de tu nota" name="cuerpo" value={data.cuerpo} onChange={handleChange} />
                    <label htmlFor="select-category">Elige una categoría:</label>
                    <select id="select-category" name="categoria" value="seleccione" onChange={handleChangeCategory}>
                    <option key="default-key-value" value="" >{

                        categoriasDisponibles.length>0?"Agregue categorias...": "No quedan más categorías disponibles"
                        } </option>
                        {
                        categoriasDisponibles && data.categorias &&
                            categoriasDisponibles.filter(t=>!isPresent(t,data.categorias)).map((categoria) => <option key={categoria.titulo} value={categoria.titulo} >{categoria.titulo} </option>)
                           
                        
                        }
                        {/*categorias?.filter(t=>isPresent(t,note.categorias))
                        .map((categoria) => <option key={categoria.titulo} value={categoria.titulo} >{categoria.titulo} </option>)*/}
                    </select>
                    <div className="category-capsules-container">
                {data.categorias?.map((categoria) => <Capsule key={`${data.id}_${categoria.titulo}`} categoria={categoria} editMode={true} deleteCategoria={deleteCategoria}/>)}
                </div>
                  
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
