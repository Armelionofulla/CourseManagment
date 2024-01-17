import PublicLayout from '../../components/layouts/PublicLayout'
import LoginButton from '../../components/Button/LoginButton'
import { Link } from 'react-router-dom'
import { Form, Input, Row, Col, Typography } from 'antd'
import { LockOutlined, UserOutlined } from '@ant-design/icons'
import { LoginUserInput } from '../../types/user'

import { useLogin } from '../../services/profile'

const { Title } = Typography

export const Login = () => {
  const { mutate: login, isLoading } = useLogin()

  const onFinish = (values: LoginUserInput) => {
    const formData = new FormData()
    formData.append('nuis', values.username)
    formData.append('password', values.password)
    login(values)
    localStorage.setItem('auth', JSON.stringify(values))
  }

  return (
    <PublicLayout>
      <Row justify="center" align="top">
        <Col md={8}>
          <Title level={3} style={{ background: '#eceef1', color: '#073c50', textAlign: 'center', fontFamily: 'Roboto, sans-serif', padding: '16px' }}>
            Login
          </Title>
          <Form initialValues={{ remember: true }} size="middle" onFinish={onFinish}>
            <Form.Item name="username" rules={[{ required: true, message: 'Please enter a valid  Username!' }]}>
              <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
            </Form.Item>
            <Form.Item name="password" rules={[{ required: true, message: 'Please enter a valid Password!' }]}>
              <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} type="password" placeholder="Password" />
            </Form.Item>
            <LoginButton>Login</LoginButton>
          </Form>
          <div style={{ display: 'flex', justifyContent: 'space-evenly' }}>
            <p>
              <Link to="/register"> Create an account</Link>
            </p>
          </div>
        </Col>
      </Row>
    </PublicLayout>
  )
}
