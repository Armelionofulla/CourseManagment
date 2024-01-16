const USER = 'user'
const TOKEN = 'token'

export const login = () => {
  localStorage.setItem(USER, 'loggedIn')
}

export const logOut = () => {
  localStorage.removeItem('auth')
}

export const isLoggedIn = () => {
  if (localStorage.getItem('auth')) {
    return true
  }

  return false
}

export const getAuth = () => {
  return JSON.parse(localStorage.getItem('auth') as string)
}
