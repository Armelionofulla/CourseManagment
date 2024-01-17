import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import client from './client'
import { useNavigate } from 'react-router-dom'
import { notification } from 'antd'
import { CloseCircleOutlined, SmileTwoTone } from '@ant-design/icons'
import { API_ENDPOINTS } from './client/api-endpoints'

import { Error, ViewProfile } from '../types/user'

export function useLogin() {
  const navigate = useNavigate()
  const { mutate, isLoading } = useMutation(client.users.login, {
    onSuccess(data, variables, context) {
      navigate('/courses')
    },
    onError: (error: Error) => {
      notification.open({
        message: 'Error!',
        description: error.response.data.message,
        icon: <CloseCircleOutlined style={{ color: 'red' }} />,
      })
    },
  })
  return { mutate, isLoading }
}

