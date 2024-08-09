
import ListHeader from "./ListHeader";
import ListItem from "./ListItem";
import { useEffect, useState } from "react";
 
import { requestFetchData } from '../service/apiService';

const FilterNotes = () => {
    const [notes, setNotes] = useState(null)
    const [categorias, setCategorias] = useState(null)
 
    const [selectedCategoria, setSelectedCategoria] = useState(null)
 


    const getCategorias = async (e) => {
        const response = await requestFetchData('categorias', 'GET')
        if (response.status === 200) {
            const json = await response.json()
            setCategorias(json)
        }

    }

    const getNotes = async () => {
            const response = await requestFetchData('notas', 'GET')
            if (response.status === 200) {
                const json = await response.json()
                setNotes(json)
            }
    }

    useEffect(() => {
      //  if (authToken) {
            getNotes()
            getCategorias()
      //  }
    }, [selectedCategoria])

    const handleChange = (e) => {
        console.log("SELECTED: " + e.target.value)
        setSelectedCategoria(e.target.value)
    }

    const filteredNotes = notes?.filter((a) => a.categoria === selectedCategoria)
    return (
        <>
            <ListHeader listName={'Filtrar Notas'} addOption={false} getData={getNotes}></ListHeader>
            <label htmlFor="select-category" style={{ marginRight: "15px" }}>Elige una categoría:</label>
            <select id="select-category" name="categoria" onChange={handleChange}>
                <option key="key-value-by-default" value=""  ></option>
                {categorias?.map((categoria) => <option key={categoria.titulo} value={categoria.titulo}  >{categoria.titulo} </option>)}
            </select>
            {filteredNotes?.length == 0 && <p style={{ textAlign: "center" }}>Aún no hay notas filtradas</p>}
            {filteredNotes?.map((note) => <ListItem key={note.id} note={note} getData={getNotes} />)}
        </>

    );
};


export default FilterNotes
