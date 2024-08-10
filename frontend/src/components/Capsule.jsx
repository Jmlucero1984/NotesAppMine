import React from 'react'

const Capsule = ({categoria,editMode,deleteCategoria}) => {

    const generateColor = (word) => {

    }
  return (
    <div className="category-capsule" style={{backgroundColor:categoria.color,display:'flex',FlexDirection:'row',   alignItems:'center'}}>
        <p>{categoria.titulo} </p>
        {editMode && 
         <button style ={{fontSize:'x-small', marginLeft:'5px', borderRadius:'10px', border:'none'}} onClick={(e) => deleteCategoria(e,categoria)}>x</button>
        }

    </div>
      

  )
} 

export default Capsule
