import { useMutation, useQueries, useQuery } from '@tanstack/react-query'
import React from 'react'
import { Course } from '../types/courses'
import { Friend } from '../types/friend'
import { LoginUserInput, RegisterUserInput } from '../types/user'
import client from './client'
import { API_ENDPOINTS } from './client/api-endpoints'

// export function useGetCourse(id: String) {
//   const { data, isLoading } = useQuery<CourseRespone[], Error>([API_ENDPOINTS.COURSES], () => client.courses.getCourse(id))
//   return { data, isLoading }
// }

export function useAddFriends() {
  const { mutate, isLoading } = useMutation(client.friends.addConnection, {
    onSuccess(data, variables, context) {
      //   notification.open({
      //     message: 'Success!',
      //     description: 'Your account is registered',
      //     icon: <SmileTwoTone style={{ color: 'red' }} />,
      //   })
    },
  })
  return { mutate, isLoading }
}

export function useMyFriends() {
  const { data, isLoading } = useQuery<Friend[]>([API_ENDPOINTS.MYFRIENDS], () => client.friends.getFriends())
  return { data, isLoading }
}

export function useFriendCourse() {
  const { data, mutate, isLoading } = useMutation(client.friends.getFriendCourse, {})
  return { data, mutate, isLoading }
}
