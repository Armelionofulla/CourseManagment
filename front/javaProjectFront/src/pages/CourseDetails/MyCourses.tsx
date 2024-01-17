import { Row, Card, Typography, Button } from 'antd'
import PrivateLayout from '../../components/layouts/PrivateLayout'
import { useDropCourse, useMyCourses } from '../../services/courses'
import { JoinCourse } from '../../types/courses'
import { LoginUserInput } from '../../types/user'
import { getAuth } from '../../utils'
const { Meta } = Card
const { Title, Text } = Typography

export const MyCourses = () => {
  const { data: mycourses } = useMyCourses()
  const { mutate: dropCourse } = useDropCourse()
  const auth: LoginUserInput = getAuth()

  const deleteCourseHandler = (id: number) => {
    const newValues: JoinCourse = {
      username: auth.username,
      password: auth.password,
      courseId: id,
    }
    dropCourse(newValues)
  }

  return (
    <PrivateLayout>
      <Row align="top" justify="space-between">
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gridGap: '20px' }}>
          {mycourses?.map(data => {
            return (
              <div style={{ marginLeft: '30px' }} key={data.id}>
                <Card hoverable style={{ width: 240 }} cover={<img alt="example" src={(process.env.REACT_APP_API_URL as string) + data.image} style={{ height: '200px' }} />}>
                  <Meta title={data.courseName} />
                </Card>
                <div>
                  <Title level={5}> Type: {data.type}</Title>
                  <Title level={5}> Start date: {data.startDate}</Title>
                  <Title level={5}> End date: {data.endDate}</Title>
                  <Title level={5}> Course Time: {data.courseTime}</Title>
                  <Title level={5}>Lecture Hall: {data.lectureHall}</Title>
                  <Button style={{ display: 'grid', gap: '3rem', alignItems: 'center' }} onClick={() => deleteCourseHandler(data.id as number)}>
                    Drop Course
                  </Button>
                </div>
              </div>
            )
          })}
        </div>
      </Row>
    </PrivateLayout>
  )
}
