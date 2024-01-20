import { Login, Dashboard, Register, CourseDetails, Profile, MyCourses, Notifications, Friends, Student} from '../pages'

export const paths = Object.freeze({
  DASHBOARD: '/courses',
  LOGIN: '/',
  REGISTER: '/register',
  PROFILE: '/profile',
  DETAILS: '/courses/:id',
  MYCOURSES: '/mycourses',
  FRIENDS: '/friends',
  NOTIFICATIONS: '/notifications',
  STUDENTS: '/students/:id',
})

const routeNames = Object.freeze({
  DASHBOARD: 'Dashboard',
  LOGIN: 'Login',
  REGISTER: 'Register',
  PROFILE: 'Profile',
  CERTIFICATE: 'Certoficate',
  PASSWORD_RECOVERY: 'Password recovery',
  DETAILS: 'Details',
  MYCOURSES: 'My_courses',
  FRIENDS: 'Friends',
  NOTIFICATIONS: 'Notifications',
  STUDENTS: 'Students',
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
    name: routeNames.PROFILE,
    path: paths.PROFILE,
    protected: true,
    element: <Profile />,
  },
  {
    name: routeNames.MYCOURSES,
    path: paths.MYCOURSES,
    protected: true,
    element: <MyCourses />,
  },
  {
    name: routeNames.FRIENDS,
    path: paths.FRIENDS,
    protected: true,
    element: <Friends />,
  },
  {
    name: routeNames.NOTIFICATIONS,
    path: paths.NOTIFICATIONS,
    protected: true,
    element: <Notifications />,
  },
  {
    name: routeNames.STUDENTS,
    path: paths.STUDENTS,
    protected: true,
    element: <Student />,
  },
]
