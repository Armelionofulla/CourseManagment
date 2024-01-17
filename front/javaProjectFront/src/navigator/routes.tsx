import {Login,Dashboard, Register, CourseDetails, MyCourses ,} from '../pages'

export const paths = Object.freeze({
  DASHBOARD: '/courses',
  LOGIN: '/',
  REGISTER: '/register',
  DETAILS: '/courses/:id',
  MYCOURSES: '/mycourses',
  })

const routeNames = Object.freeze({
  DASHBOARD: 'Dashboard',
  LOGIN: 'Login',
  REGISTER: 'Register',
  DETAILS: 'Details',
  MYCOURSES: 'My_courses',
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
  {
    name: routeNames.DASHBOARD,
    path: paths.DASHBOARD,
    protected: true,
    element: <Dashboard />,
    // roles: [ROLES.SUPER_ADMIN, ROLES.DATA_ENTRY, ROLES.BROKER],
  },
  {
    name: routeNames.DETAILS,
    path: paths.DETAILS,
    protected: true,
    element: <CourseDetails />,
  },
  {
    name: routeNames.MYCOURSES,
    path: paths.MYCOURSES,
    protected: true,
    element: <MyCourses />,
  },
  ]
