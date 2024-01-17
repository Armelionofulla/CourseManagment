import React from 'react'
import PrivateLayout from '../../components/layouts/PrivateLayout'
import { Row, Col, Typography, Card, Divider } from 'antd'
import { useNavigate, Link, Outlet } from 'react-router-dom'
import { useFindCourses } from '../../services/courses'
import Title from 'antd/es/typography/Title'

const { Meta } = Card

export const Dashboard: React.FC = () => {
  const navigate = useNavigate()
  const { data: courses } = useFindCourses()

  return (
    <>
      <PrivateLayout>
        <Row justify="center" align="top" style={{ textAlign: 'center' }}>
          <Title>Major</Title>
          <Divider />
          <Col xs={{ span: 8, offset: 0 }} xl={{ span: 10, offset: 0 }} style={{ textAlign: 'center', display: 'flex', flexDirection: 'row', gap: '2rem', alignItems: 'center' }}>
            {courses?.['major']?.map(course => (
              <Link to={`/courses/${course.id}`} key={course.id}>
                <Card key={course.id} hoverable style={{ width: 240 }} cover={<img alt="example" src={process.env.REACT_APP_API_URL + course.image} />}>
                  <Meta title={course.name} />
                </Card>
              </Link>
            ))}
          </Col>
        </Row>

        <Row justify="center" align="top" style={{ textAlign: 'center' }}>
          <Title>Minor</Title>
          <Divider />
          <Col
            xs={{ span: 8, offset: 0 }}
            xl={{ span: 10, offset: 0 }}
            style={{ textAlign: 'center', display: 'flex', flexDirection: 'row', gap: '2rem', gridTemplateColumns: '1fr 1fr 1fr' }}
          >
            {courses?.['minor']?.map(course => (
              <Link to={`/courses/${course.id}`} key={course.id}>
                <Card key={course.id} hoverable style={{ width: 240 }} cover={<img alt="example" src={process.env.REACT_APP_API_URL + course.image} />}>
                  <Meta title={course.name} />
                </Card>
              </Link>
            ))}
            <Outlet />
          </Col>
        </Row>
      </PrivateLayout>
    </>
  )
}
