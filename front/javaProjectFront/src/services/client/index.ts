import { AuthResponse, ViewProfile, LoginUserInput, RegisterUserInput } from '../../types/user'
import { Courses, JoinCourse, CourseRespone, Course} from '../../types/courses'
import { API_ENDPOINTS } from './api-endpoints'
import { HttpClient } from './http-client'
import { HttpClient } from './http-client'
import { Response } from '../../types/general'
import { Friend } from '../../types/friend'

const getHeaders = () => {
  return { username: JSON.parse(localStorage.getItem('auth')).username, password: JSON.parse(localStorage.getItem('auth')).password }
}

class Client {
  users = {
    login: (input: LoginUserInput) => HttpClient.post<AuthResponse>(API_ENDPOINTS.LOGIN, input),
    register: (input: RegisterUserInput) => HttpClient.post<AuthResponse>(API_ENDPOINTS.REGISTER, input),
    profile: (username: string) =>
      HttpClient.get<ViewProfile>(`${API_ENDPOINTS.PROFILE}/me`, {
        headers: getHeaders(),
      }),
    editProfile: (input: ViewProfile) => HttpClient.put<AuthResponse>(`${API_ENDPOINTS.EDIT_PROFILE}/me`, input, { headers: getHeaders() }),
    getStudent: (id: Number) => HttpClient.get<ViewProfile>(`${API_ENDPOINTS.STUDENTS}/${id}`, { headers: getHeaders() }),
  }

  courses = {
    findAll: () => HttpClient.get<Courses>(API_ENDPOINTS.COURSES),
    getCourse: (id: String) => HttpClient.get<CourseRespone[]>(`${API_ENDPOINTS.COURSES}/${id}`, { headers: getHeaders() }),
    joinCourse: (input: JoinCourse) => HttpClient.post<CourseRespone>(API_ENDPOINTS.JOINCOURSE, input, { headers: getHeaders() }),
    dropCourse: (input: JoinCourse) => HttpClient.post<CourseRespone>(API_ENDPOINTS.DROPCOURSE, input, { headers: getHeaders() }),
    getMyCourses: () => HttpClient.get<CourseRespone[]>(`${API_ENDPOINTS.MYCOURSES}/me`, { headers: getHeaders() }),
    getCommentsByCourseId: (id: number) => HttpClient.get<Comments[]>(`${API_ENDPOINTS.COMMENTS}/${id}`, { headers: getHeaders() }),
  }
  friends = {
    getFriends: () => HttpClient.get<Friend[]>(API_ENDPOINTS.MYFRIENDS, { headers: getAuth() }),
    getPending: () => HttpClient.get<Friend[]>(API_ENDPOINTS.PENDING, { headers: getAuth() }),
    addConnection: (id: Number) => HttpClient.post<Response>(`${API_ENDPOINTS.CONNECTIONS}/${id}`, { id }, { headers: getAuth() }),
    getFriendCourse: (id: Number) => HttpClient.post<Course[]>(`${API_ENDPOINTS.MYCOURSES}/${id}`, {}, { headers: getAuth() }),
  }

}

export default new Client()
