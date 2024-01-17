import {Login, Register,} from '../pages'

export const paths = Object.freeze({
  LOGIN: '/',
  REGISTER: '/register',
  })

const routeNames = Object.freeze({
    LOGIN: 'Login',
    REGISTER: 'Register',
  })

export const routes = [
  {
    name: routeNames.LOGIN,
    path: paths.LOGIN,
    protected: false,
    element: <Login />,
  },
  {
    name: routeNames.REGISTER,
    path: paths.REGISTER,
    protected: false,
    element: <Register />,
  },
  ]
