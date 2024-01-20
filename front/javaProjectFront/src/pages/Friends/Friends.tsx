import { Typography, Row, List, Avatar, Button, Card } from 'antd'
import React from 'react'
import PrivateLayout from '../../components/layouts/PrivateLayout'
import { useFriendCourse, useMyFriends } from '../../services/friends'
const { Title, Text } = Typography
const { Meta } = Card
export const Friends = () => {
  const { data } = useMyFriends()
  const { data: coursesData, mutate, isLoading } = useFriendCourse()

  const friendCoursesHandler = (id: Number) => {
    mutate(id)
  }

  console.log(coursesData)
  return (
    <PrivateLayout>
      <Title level={3} style={{ textAlign: 'center' }}>
        Course Attendees:
      </Title>
      {data?.length && (
        <Row justify="start" align="top" style={{ textAlign: 'left' }}>
          <List style={{ marginLeft: '4rem', marginTop: '2rem' }}>
            {data.map((d, index) => {
              return (
                <li key={index}>
                  <Avatar src={d.picture} size={{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }} style={{ marginRight: '1rem' }} />
                  {d.studentName} {d.lastName}
                  <Button style={{ marginLeft: '1rem' }} onClick={() => friendCoursesHandler(d.id)}>
                    Browse friend's courses
                  </Button>
                  {coursesData && (
                    <div>
                      {coursesData?.map(c => {
                        return (
                          <div>
                            <Card hoverable style={{ width: 240 }} cover={<img alt="example" src={(process.env.REACT_APP_API_URL as string) + c?.image} />}>
                              <Meta title={c?.[0]?.courseName} />
                            </Card>
                            <div>
                              <Title level={5}> Type: {c?.type}</Title>
                              <Title level={5}> Start date: {c?.startDate}</Title>
                              <Title level={5}> End date: {c?.endDate}</Title>
                              <Title level={5}> Course Time: {c?.course_time}</Title>
                              <Title level={5}>Lecture Hall: {c?.lecture_hall}</Title>
                            </div>
                          </div>
                        )
                      })}
                    </div>
                  )}
                </li>
              )
            })}
          </List>
        </Row>
      )}
    </PrivateLayout>
  )
}
