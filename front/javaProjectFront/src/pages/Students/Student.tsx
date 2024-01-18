import { Avatar, Button, Typography } from 'antd'
import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import PrivateLayout from '../../components/layouts/PrivateLayout'
import client from '../../services/client'

const { Title, Text } = Typography

export function Student() {
  const { id } = useParams()
  const [student, setStudent] = useState({})
  const [status, setStatus] = useState('Send request')

  const fetchStudent = async id => {
    const result = await client.users.getStudent(id)
    const friends = await client.friends.getFriends()
    const requests = await client.friends.getPending()

    const connection = friends.find(friend => friend.id === id)
    const pending = requests.find(request => request.id === id)
    setStudent(result)

    if (connection) setStatus('Friends')
    if (pending) setStatus('Pending request')
  }

  useEffect(() => {
    fetchStudent(+id)
  }, [])

  const handleClick = async () => {
    if (status === 'Send request') {
      await client.friends.addConnection(+id)
      window.location.reload()
    }
  }

  return (
    <>
      <PrivateLayout>
        <Avatar src={student?.['picture']} size={{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }} style={{ marginRight: '1rem' }} />
        <Text>Username: </Text>
        <Text>{student?.['username']} </Text>
        <Button onClick={handleClick} disabled={status !== 'Send request'}>
          {status}
        </Button>
      </PrivateLayout>
    </>
  )
}
