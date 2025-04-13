import React from 'react'
import Loader from '../Loader'

const Table: React.FC<{
  type: string
  data: Array<{ id: number; name: string; uniqueId: string }>
  handleView: () => void
  handleUpdate: () => void
  handleStatus: () => void
  handleDelete: () => void
}> = (props) => {
  const { type, data, handleView, handleUpdate, handleStatus, handleDelete } = props

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>{type} name</th>
            <th>Uniquie ID</th>
            <th>Action</th>
          </tr>
        </thead>
        {data?.length
          ? data?.map((item) => (
              <tbody key={item.id}>
                <tr>
                  <td>{item.id}</td>
                  <td>{item.name}</td>
                  <td>{item.uniqueId}</td>
                  <td>
                    <button className="btn btn__view" onClick={() => handleView()}>
                      View
                    </button>
                    <button className="btn btn__contact" onClick={() => handleUpdate()}>
                      Email
                    </button>
                    <button className="btn btn__update" onClick={() => handleStatus()}>
                      Disable
                    </button>
                    <button className="btn btn__delete" onClick={() => handleDelete()}>
                      Delete
                    </button>
                  </td>
                </tr>
              </tbody>
            ))
          : null}
      </table>
      {!data?.length && <Loader />}
    </>
  )
}

export default Table
