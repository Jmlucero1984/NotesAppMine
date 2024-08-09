import ListHeader from "./ListHeader";
import ListItem from "./ListItem";
import { useEffect, useState } from "react";
 
import { requestFetchData } from '../service/apiService';
const Archived = () => {
    const [notes, setNotes] = useState(null)
 

    const getData = async () => {
        const response = await requestFetchData('notas', 'GET')
        if (response.status === 200) {
            const json = await response.json()
            setNotes(json)
        }
    }
   
    useEffect(() => {
       // if (authToken) {
        getData()
      //  }
    }, [])
   

    const sortedNotes = notes?.sort((a, b) => a.id - b.id)
    const filteredNotes = sortedNotes?.filter((a) => a.estado === "ARCHIVADO")
    return (
        <>
            <ListHeader listName={'Notas Archivadas'} addOption={false} getData={getData}></ListHeader>
            {filteredNotes?.length == 0 && <p style={{ textAlign: "center" }}>Aún no hay notas archivadas</p>}
            {filteredNotes?.map((note) => <ListItem key={note.id} note={note} getData={getData} />)}
        </>

    );
};

export default Archived;