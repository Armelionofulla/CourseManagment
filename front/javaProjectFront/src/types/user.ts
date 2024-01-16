export interface LoginUserInput {
  username: string
  password: string
}

export interface RegisterUserInput {
  name: string
  surname: string
  username: string
  email: string
  password: string
  picture: string
  id: Number
}

export interface ViewProfile {
  id: Number
  address: string
  email: string
  lastname: string
  name: string
  password: string
  picture: string
  newPassword?: string
  username: string
}
export interface AuthResponse {
  code: number
  message: string
}
export interface Error {
  response: {
    data: {
      code: number
      message: string
    }
  }
}
