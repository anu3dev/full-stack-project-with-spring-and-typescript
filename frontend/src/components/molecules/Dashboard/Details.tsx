import React from 'react'
import Table from '../../atoms/Table'

const Details: React.FC<{ type: string }> = (props) => {
  const { type } = props

  const heading = `${type} list:-`

  const onView = () => {}
  const onUpdate = () => {}
  const onDisable = () => {}
  const onDelete = () => {}

  interface DataItem {
    id: number
    name: string
    status: string
    uniqueId: string
  }
  const data: DataItem[] = [
    { id: 1, name: 'Item 1', status: 'Active', uniqueId: 'uid-1' },
    { id: 2, name: 'Item 2', status: 'Inactive', uniqueId: 'uid-2' },
  ]

  return (
    <div className={`details-container${data?.length ? '' : '__loader'}`}>
      <h3>{heading}</h3>
      <Table
        type={type}
        data={data}
        handleView={onView}
        handleUpdate={onUpdate}
        handleStatus={onDisable}
        handleDelete={onDelete}
      />
    </div>
  )
}

export default Details
