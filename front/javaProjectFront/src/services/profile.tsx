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

export function useRegister() {
  const navigate = useNavigate()
  const { mutate, isLoading } = useMutation(client.users.register, {
    onSuccess(data, variables, context) {
      notification.open({
        message: 'Success!',
        description: 'Your account is registered',
        icon: <SmileTwoTone style={{ color: 'red' }} />,
      })
      navigate('/')
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

export function useProfile(username: string) {
  const { data, isLoading } = useQuery<ViewProfile, Error>([API_ENDPOINTS.PROFILE], () => client.users.profile(username))
  return { data, isLoading }
}

export function useEditProfile() {
  const queryClient = useQueryClient()

  const navigate = useNavigate()
  const { mutate, isLoading } = useMutation(client.users.editProfile, {
    onSuccess(data, variables, context) {
      localStorage.setItem('auth', localStorage.getItem('authToBe'))
      notification.open({
        message: 'Success!',
        description: 'Your account is edited',
        icon: <SmileTwoTone style={{ color: 'red' }} />,
      })
      //navigate('/courses')
    },
    onError: (error: Error) => {
      notification.open({
        message: 'Error!',
        description: error.response.data.message,
        icon: <CloseCircleOutlined style={{ color: 'red' }} />,
      })
    },
    onSettled: () => {
      queryClient.invalidateQueries()
    },
  })
  return { mutate, isLoading }
}