import { AuthResponse, ViewProfile, LoginUserInput, RegisterUserInput } from '../../types/user'

import { API_ENDPOINTS } from './api-endpoints'
import { HttpClient } from './http-client'


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

}

export default new Client()
