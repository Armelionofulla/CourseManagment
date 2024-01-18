import { useMutation, useQuery } from '@tanstack/react-query'
import client from './client'
import { useNavigate } from 'react-router-dom'
import { notification } from 'antd'
import { CloseCircleOutlined } from '@ant-design/icons'
import { Error, ViewProfile } from '../types/user'
import { API_ENDPOINTS } from './client/api-endpoints'
import { Course, Courses, CourseRespone,} from '../types/courses'
import { Friend } from '../types/friend'

export function useFindCourses() {
  const { data, isLoading } = useQuery<Courses, Error>([API_ENDPOINTS.COURSES], () => client.courses.findAll())
  return { data, isLoading }
}

export function useGetCourse(id: String) {
  const { data, isLoading } = useQuery<CourseRespone[], Error>([API_ENDPOINTS.COURSES], () => client.courses.getCourse(id))
  return { data, isLoading }
}

export function useJoinCourse() {
  const { mutate, isLoading, isSuccess } = useMutation(client.courses.joinCourse, {
    onSuccess(data, variables, context) {
      window.location.reload()
    },
    onError: (error: Error) => {
      notification.open({
        message: 'Error!',
        description: error.response.data.message,
        icon: <CloseCircleOutlined style={{ color: 'red' }} />,
      })
    },
  })
  return { mutate, isLoading, isSuccess }
}

export function useDropCourse() {
  const { mutate, isLoading, isSuccess } = useMutation(client.courses.dropCourse, {
    onSuccess(data, variables, context) {
      window.location.reload()
    },
    onError: (error: Error) => {
      notification.open({
        message: 'Error!',
        description: error.response.data.message,
        icon: <CloseCircleOutlined style={{ color: 'red' }} />,
      })
    },
  })
  return { mutate, isLoading, isSuccess }
}

export function useMyCourses() {
  const { data, isLoading } = useQuery<CourseRespone[], Error>([API_ENDPOINTS.MYCOURSES], () => client.courses.getMyCourses())
  return { data, isLoading }
}

export function useStudent(id: number) {
  const { data, isLoading } = useQuery<ViewProfile>([API_ENDPOINTS.MYCOURSES], () => client.users.getStudent(id))
  return { data, isLoading }
}

