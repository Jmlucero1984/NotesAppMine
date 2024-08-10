import { useEffect, useState } from "react";
import ListHeader from "./ListHeader";
import ListItem from "./ListItem";
 
import { requestFetchData } from '../service/apiService';

const Active = () => {
    const [notes, setNotes] = useState(null)

    const getNotes = async () => {
        const response = await requestFetchData('notas', 'GET')
        if (response.status === 200) {
            const json = await response.json()
        console.log(json)
        setNotes(json)
        }
}

    useEffect(() => {

     //   if (authToken) {
             getNotes()
      //  }
    }, [])

    const sortedNotes = notes?.sort((a, b) => a.id - b.id)
    const filteredNotes = sortedNotes?.filter((a) => a.estado === "ACTIVO")
    return (
        <>
            <ListHeader listName={'Notas Activas'} addOption={true} getData={getNotes}></ListHeader>
            {filteredNotes?.length == 0 && <p style={{ textAlign: "center" }}>Aún no hay notas activas</p>}
            {filteredNotes?.map((note) => <ListItem key={note.id} note={note} getData={getNotes} />)}
        </>
    )
}


export default Active;