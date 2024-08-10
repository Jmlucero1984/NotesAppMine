
import ListHeader from "./ListHeader";
import ListItem from "./ListItem";
import { useEffect, useState } from "react";
 
import { requestFetchData } from '../service/apiService';
import Capsule from "./Capsule";

const FilterNotes = () => {
    const [notes, setNotes] = useState(null)
    const [categorias, setCategorias] = useState(null)
 
    const [selectedCategorias, setSelectedCategorias] = useState([])
    const [categoriasDisponibles, setCategoriasDisponibles] = useState([])
 


    const getCategorias = async (e) => {
        const response = await requestFetchData('categorias', 'GET')
        if (response.status === 200) {
            const json = await response.json()
            setCategorias(json)
            setCategoriasDisponibles(json)
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
 
            getNotes()
            getCategorias()
            console.log("USE EFFECT")
            console.table(notes)
 
    }, [])

 

    const deleteCategoria = (e,categoria) => {
        console.log(categoria)
        setCategoriasDisponibles(()=>{
            const sel  =[...selectedCategorias.filter(t=>t.titulo!==categoria.titulo)]
            setSelectedCategorias(sel)
            return  [...categorias.filter(t=>!isPresent(t,sel))]

        })


    }
    const isPresent = (target, categorias) => {
        if(categorias==null) return false
  
        for (let index = 0; index < categorias.length; index++) {
            const element = categorias[index];
            if (element.titulo == target.titulo) return true
            
        }
        return false;

    }
    const handleChangeCategory = (e) => {
      
        console.log("_____")
        console.log(e.target.value)
 
        
        setCategoriasDisponibles(()=>{
            const sel  =[...selectedCategorias,...categoriasDisponibles.filter(t=>t.titulo==e.target.value)]
            setSelectedCategorias(sel)
            return  [...categorias.filter(t=>!isPresent(t,sel))]

        })
        console.log("SELECTED")
        console.table(selectedCategorias)
        console.log("DISPONIBLES")
        console.table(categoriasDisponibles)
    }

   // const filteredNotes = notes?.filter((a) => a.categoria === selectedCategoria)
    return (
        <>
            <ListHeader listName={'Filtrar Notas'} addOption={false} getData={getNotes}></ListHeader>
            <label htmlFor="select-category" style={{ marginRight: "15px" }}>Elige una categoría:</label>
   
  
             
                    <select id="select-category" name="categoria" value="seleccione" onChange={handleChangeCategory}>
                    <option key="default-key-value" value="" >{

                        categoriasDisponibles?.length>0?"Agregue categorias...": "No quedan más categorías disponibles"
                        } </option>
                        {
                        
                            categoriasDisponibles?.map((categoria) => <option key={categoria.titulo} value={categoria.titulo} >{categoria.titulo} </option>)
                           
                        
                        }
 
                    </select>
                    <div className="category-capsules-container">
                {selectedCategorias?.map((categoria) => <Capsule key={`${categoria.titulo}`} categoria={categoria} editMode={true} deleteCategoria={deleteCategoria}/>)}
</div>
 {selectedCategorias?.length > 0 ? 
 notes.filter( t => t.categorias.some((cat) => selectedCategorias.some(sc => sc.titulo === cat.titulo))).map((note) => <ListItem key={note.id} note={note} getData={getNotes} />)
 :  
 <p style={{ textAlign: "center" }}>Aún no hay notas filtradas</p>}
 

        </>

    );
};


export default FilterNotes
