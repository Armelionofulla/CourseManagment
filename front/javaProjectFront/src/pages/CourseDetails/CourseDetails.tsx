import { useParams } from 'react-router-dom'
import { useComments, useDropCourse, useGetCourse, useJoinCourse } from '../../services/courses'
import { Card, Row, Typography, Avatar, Button, List, Divider, Form, Input } from 'antd'
import PrivateLayout from '../../components/layouts/PrivateLayout'
import styles from './CourseDetails.module.css'
import { Comments, CourseRespone, JoinCourse } from '../../types/courses'
import { LoginUserInput } from '../../types/user'
import { getAuth } from '../../utils'
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import client from '../../services/client'

const { Meta } = Card
const { Title, Text } = Typography

export const CourseDetails = () => {
  const navigate = useNavigate()
  const { id } = useParams()
  const { data } = useGetCourse(id as string)
  const { mutate: joinCourse, isLoading, isSuccess } = useJoinCourse()
  const { mutate: dropCourse } = useDropCourse()
  const { data: comments } = useComments(+id)
  //const { mutate: addComments } = useAddComments(+id)
  const [isRegistered, setIsRegistered] = useState(false)

  const auth: LoginUserInput = getAuth()
  const image: String = data?.[0]?.image as string

  useEffect(() => {
    if (data?.length) {
      console.log(data)
      data.forEach(d => {
        if (d.username === auth.username) {
          setIsRegistered(true)
        }
      })
    }
  }, [data])

  const joinCourseHandler = (id: number) => {
    const newValues: JoinCourse = {
      username: auth.username,
      password: auth.password,
      courseId: id,
    }
    joinCourse(newValues)
  }

  const deleteCourseHandler = (id: number) => {
    const newValues: JoinCourse = {
      username: auth.username,
      password: auth.password,
      courseId: id,
    }
    dropCourse(newValues)
  }

  const viewProfileHandler = (d: number) => {
    console.log(d)
    navigate(`/students/${d}`)
  }

  const getLikes = (likes: string) => {
    if (!likes) return 0
    const distinctLikes = new Set([...likes.split(',')])
    return distinctLikes.size
  }

  const checkMine = (comment: Comments) => {
    const username = JSON.parse(localStorage.getItem('auth')).username
    if (username === comment.username) return true
    return false
  }

  const handleLike = (comment: Comments) => {
    // dergoje ne backend
  }

  const addCommentHandler = async (values: Comments) => {
    // values.courseId = +id
    values.likes = ''
    values.courseId = +id
    console.log({ values })
    await client.comments.addComment(values)
    window.location.reload()
  }

  return (
    <>
      <PrivateLayout>
        <Title level={3} style={{ textAlign: 'center' }}>
          Course Details:
        </Title>
        <Row justify="space-around" align="top" style={{ textAlign: 'left' }}>
          <div className={styles.course}>
            <Card hoverable style={{ width: 240 }} cover={<img alt="example" src={(process.env.REACT_APP_API_URL as string) + image} />}>
              <Meta title={data?.[0]?.courseName} />
            </Card>
            <div className={styles.details}>
              <Title level={5}> Type: {data?.[0]?.type}</Title>
              <Title level={5}> Start date: {data?.[0]?.startDate}</Title>
              <Title level={5}> End date: {data?.[0]?.endDate}</Title>
              <Title level={5}> Course Time: {data?.[0]?.courseTime}</Title>
              <Title level={5}>Lecture Hall: {data?.[0]?.lectureHall}</Title>

              {!isRegistered ? (
                <Button style={{ display: 'grid', gap: '3rem', alignItems: 'center' }} onClick={() => joinCourseHandler(data?.[0]?.id as number)}>
                  Join Course
                </Button>
              ) : (
                <Button style={{ display: 'grid', gap: '3rem', alignItems: 'center' }} onClick={() => deleteCourseHandler(data?.[0]?.id as number)}>
                  Drop Course
                </Button>
              )}
            </div>
          </div>
        </Row>
        <Divider></Divider>
        <Title level={3} style={{ textAlign: 'center' }}>
          Course Attendees:
        </Title>
        {data?.length && data[0]?.studentName && (
          <Row justify="start" align="top" style={{ textAlign: 'left' }}>
            <List style={{ marginLeft: '4rem', marginTop: '2rem' }}>
              {data.map((d, index) => {
                return (
                  <li key={index} style={{ marginTop: '12px' }}>
                    <Avatar src={d.picture} size={{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }} style={{ marginRight: '1rem' }} />
                    {d.studentName} {d.lastname}
                    {auth.username == d.username ? (
                      ''
                    ) : (
                      <Button style={{ marginLeft: '1rem' }} onClick={() => viewProfileHandler(d.studentId)}>
                        View Profile
                      </Button>
                    )}
                  </li>
                )
              })}
            </List>
          </Row>
        )}
        <Divider></Divider>
        <Row justify="space-between" align="top" style={{ display: 'block' }}>
          <Title level={3} style={{ display: 'block', margin: 'auto', textAlign: 'center' }}>
            Comments:
          </Title>
          <Form style={{ display: 'flex', flexDirection: 'row', marginLeft: '20px' }} onFinish={addCommentHandler}>
            <Form.Item name="text">
              <Input placeholder="Write your comment: " style={{ width: '200px', marginLeft: '30px' }}></Input>
            </Form.Item>
            <Button htmlType="submit" style={{ marginLeft: '20px' }}>
              Add comment
            </Button>
          </Form>
        </Row>
        <Row justify="space-between" align="top" style={{ display: 'block', marginLeft: '50px' }}>
          {comments?.map(c => (
            <div key={c.id} style={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
              <Title level={5}>{c.text}</Title>
              <Text>Username: {c.username}</Text>
              <Text>Likes: {getLikes(c.likes)}</Text>
              <Button style={{ width: '100px' }} disabled={checkMine(c)} onClick={() => handleLike(c)}>
                Like
              </Button>
            </div>
          ))}
          <Divider></Divider>
        </Row>
      </PrivateLayout>
    </>
  )
}
