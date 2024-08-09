import { useState } from "react";
import Modal from "./Modal";
import CategoryModal from "./CategoryModal";

const ListHeader = ({ listName, addOption, getData }) => { // Destructuring the props
  const [showModal, setShowModal] = useState(false)
  const [showCategoryModal, setShowCategoryModal] = useState(false)
  return (
    <div className="list-header" >
      <h1>
        {listName}
      </h1>
      <div className="button-container">
        {addOption &&
          <>
            <button className="create" onClick={() => setShowModal(true)}>Nueva Nota</button>
            <button className="create" onClick={() => setShowCategoryModal(true)}>Nueva Categoria</button>
          </>
        }

      </div>
      {showModal && <Modal mode={'crear'} setShowModal={setShowModal} getData={getData} />}
      {showCategoryModal && <CategoryModal setShowCategoryModal={setShowCategoryModal} />}

    </div>
  );
}

export default ListHeader;
