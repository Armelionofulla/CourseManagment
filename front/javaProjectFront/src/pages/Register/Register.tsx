import PublicLayout from '../../components/layouts/PublicLayout'
import LoginButton from '../../components/Button/LoginButton'
import { Form, Input, Row, Col, Typography, Avatar } from 'antd'
import { UserOutlined, LockOutlined } from '@ant-design/icons'
import { Link } from 'react-router-dom'
import { RegisterUserInput } from '../../types/user'
import { useRegister } from '../../services/profile'
import FileBase64 from 'react-file-base64'
import { useState } from 'react'

const { Title } = Typography
export const Register = () => {
  const { mutate } = useRegister()
  const [picture, setPicture] = useState(null)

  const onFinish = (values: RegisterUserInput) => {
    values.picture = picture
    mutate(values)
  }
  const handlePicturChange = file => {
    setPicture(file.base64)
  }

  return (
    <PublicLayout>
      <Row justify="center" align="top">
        <Col md={8}>
          <Title level={3} style={{ background: '#eceef1', color: '#073c50', textAlign: 'center', fontFamily: 'Roboto, sans-serif', padding: '16px' }}>
            Create an account
          </Title>
          <Form initialValues={{ remember: true }} size="middle" onFinish={onFinish}>
            <Form.Item name="name" rules={[{ required: true, message: 'Please enter a valid Name!' }]}>
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Name" />
            </Form.Item>
            <Form.Item name="lastname" rules={[{ required: true, message: 'Please enter a valid Lastname!' }]}>
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Lastname" />
            </Form.Item>
            <Form.Item name="username" rules={[{ required: true, message: 'Please enter a valid Username!' }]}>
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
            </Form.Item>
            <Form.Item name="password" rules={[{ required: true, message: 'Please enter a valid Password!' }]}>
              <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} type="password" placeholder="Password" />
            </Form.Item>
            <Form.Item name="email" rules={[{ required: true, message: 'Please enter a valid Email!' }]}>
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Email" />
            </Form.Item>
            <Form.Item name="address" rules={[{ required: true, message: 'Please enter a valid Address!' }]}>
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Your address" />
            </Form.Item>
            <Form.Item name="picture">
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'space-evenly',
                  alignItems: 'center',
                }}
              >
                <div>
                  <FileBase64 multiple={false} onDone={handlePicturChange} />
                </div>
                <div>
                  <Avatar src={picture} size={{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }} />
                </div>
              </div>
            </Form.Item>
            <LoginButton>Register</LoginButton>
            <p style={{ textAlign: 'center' }}>Already have an account ?</p>
            <Link style={{ display: 'block', margin: 'auto', textAlign: 'center' }} to="/">
              Login
            </Link>
          </Form>
        </Col>
      </Row>
    </PublicLayout>
  )
}
