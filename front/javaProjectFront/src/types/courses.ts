import { LoginUserInput } from './user'
export interface Course {
  id: Number
  name: string
  startDate: string
  endDate: string
  type: string
  image: string
  lecture_hall: string
  course_time: string
}

export interface Courses {
  major: Course[]
  minor: Course[]
}

export interface CourseRespone {
  address: string
  courseName: string
  email: string
  endDate: string
  image: string
  lastname: null
  likes: number
  studentName: string
  picture: string
  startDate: string
  type: string
  username: string
  id: Number
  courseTime: Date
  lectureHall: string
  studentId: number
}

export interface JoinCourse {
  username: string
  password: string
  courseId: number
}

// export interface Comments {
//   id: number
//   text: string
//   likes: string
//   username: string
//   studentId: number
//   courseId: number
// }
