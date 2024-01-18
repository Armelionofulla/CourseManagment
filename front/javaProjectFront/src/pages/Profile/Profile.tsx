import { Avatar, Button, Col, Row, Form, Typography, Input } from 'antd'
import PublicLayout from '../../components/layouts/PublicLayout'
import { useEditProfile, useProfile } from '../../services/profile'
import { LoginUserInput, RegisterUserInput, ViewProfile } from '../../types/user'
import { getAuth } from '../../utils'
import { UserOutlined, LockOutlined } from '@ant-design/icons'
import PrivateLayout from '../../components/layouts/PrivateLayout'
import FileBase64 from 'react-file-base64'
import { useState, useEffect } from 'react'
import { callbackify } from 'util'

const { Title } = Typography

export const Profile = () => {
  const auth: LoginUserInput = getAuth()
  const { data: user, isLoading } = useProfile(auth.username)
  const { mutate: editProfile } = useEditProfile()
  const [picture, setPicture] = useState(user?.picture)
  const [password, setPassword] = useState('')

  const submitHanlder = (values: ViewProfile) => {
    values = { ...values, id: user?.id as number, picture, password: values.newPassword }

    const auth = JSON.parse(localStorage.getItem('auth'))
    auth.password = values.password
    auth.username = values.username
    localStorage.setItem('authToBe', JSON.stringify(auth))

    editProfile(values)
  }

  const handlePicturChange = file => {
    setPicture(file.base64)
  }

  return (
    <>
      {!isLoading ? (
        <PrivateLayout>
          <Row justify="center" align="top">
            <Col md={8}>
              <Form initialValues={{ ...user, password }} onFinish={submitHanlder}>
                <Form.Item name="picture">
                  <Avatar src={picture ?? user.picture} size={{ xs: 37, sm: 80, md: 90, lg: 100, xl: 110, xxl: 120 }} style={{ display: 'block', margin: 'auto' }}>
                    Profile Picture
                  </Avatar>
                </Form.Item>
                <Form.Item name="name" rules={[{ required: true, message: 'Please enter a valid Name!' }]}>
                  <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Name" />
                </Form.Item>
                <Form.Item name="lastname" rules={[{ required: true, message: 'Please enter a valid Lastname!' }]}>
                  <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Lastname" />
                </Form.Item>
                <Form.Item name="username" rules={[{ required: true, message: 'Please enter a valid Username!' }]}>
                  <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
                </Form.Item>
                <Form.Item name="newPassword">
                  <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} type="password" placeholder="New Password" />
                </Form.Item>
                <Form.Item name="email" rules={[{ required: true, message: 'Please enter a valid Email!' }]}>
                  <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Email" />
                </Form.Item>
                <Form.Item name="address" rules={[{ required: true, message: 'Please enter a valid Address!' }]}>
                  <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Your address" />
                </Form.Item>
                <Form.Item name="picture">
                  <FileBase64 multiple={false} onDone={handlePicturChange} />
                </Form.Item>
                <Button htmlType="submit">Edit profile</Button>
              </Form>
            </Col>
          </Row>
        </PrivateLayout>
      ) : (
        <></>
      )}
    </>
  )
}
