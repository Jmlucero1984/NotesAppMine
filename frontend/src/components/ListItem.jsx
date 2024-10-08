import { useState } from "react";
import Modal from './Modal';
import { requestFetchData } from '../service/apiService'
import Capsule from "./Capsule";

const ListItem = ({ note, getData }) => {


    const [showModal, setShowModal] = useState(false)
    const deleteItem = async () => {
      

            const response = await requestFetchData(`notas/${note.id}`, 'DELETE')
            if (response.status === 200) {
                getData();
            }
     
    }
    const checkArchived = (nota) => {
        return nota.estado === "ARCHIVADO" ? { className: "list-item archived" } : { className: "list-item" };
    }
    return (
        <li {...checkArchived(note)}>
            <div className="info-container">
                <div className="category-capsules-container">
                {note.categorias?.map((categoria) => <Capsule key={`${note.id}_${categoria.titulo}`} categoria={categoria}/>)}
                </div>
              
                <p className="note-title">{note.titulo}</p>
            </div>
            <div className="button-container">
                <button className="edit" onClick={() => setShowModal(true)}>Edtar</button>
                <button className="delete" onClick={() => deleteItem()} >Borrar</button>
            </div>
            {showModal && <Modal mode={'editar'} setShowModal={setShowModal} note={note} getData={getData} />}
        </li>
    );
}

export default ListItem;
