import React from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import { Layout, Avatar, Typography, Button } from 'antd'
import { LogoutOutlined, ArrowLeftOutlined } from '@ant-design/icons'
import { logOut } from '../../utils'

const { Header, Footer, Content } = Layout
const { Title } = Typography

interface Props {
  children: React.ReactNode
}

export const PrivateLayout = ({ children }: Props): JSX.Element => {
  const location = useLocation()
  const navigate = useNavigate()
  const logOutHandler = () => {
    navigate('/')
    logOut()
  }

  return (
    <Layout style={{ minHeight: '50vh' }}>
      <Header style={{ backgroundColor: 'rgb(56, 168, 157)', display: 'flex', alignItems: 'center', justifyContent: 'space-between', height: '64px', padding: '10px' }}>
        <Avatar icon={<ArrowLeftOutlined title="Log out" />} size={{ xs: 37, sm: 50, md: 50, lg: 55, xl: 55, xxl: 57 }} style={{ marginTop: '4px' }} onClick={() => navigate(-1)} />
        <Title level={1} style={{ color: 'white', fontSize: '20px', display: 'block', marginLeft: '26rem' }}>
          Course Managment
        </Title>
        <div style={{ width: '39%', display: 'flex', justifyContent: 'space-between' }}>
          <Button style={{ fontSize: '15px', display: 'block', margin: 'auto ' }} onClick={() => navigate('/profile')}>
            My Profile
          </Button>
          <Button style={{ fontSize: '15px', display: 'block', margin: 'auto ' }} onClick={() => navigate('/mycourses')}>
            My Courses
          </Button>
          <Button style={{ fontSize: '15px', display: 'block', margin: 'auto ' }} onClick={() => navigate('/friends')}>
            My Friends
          </Button>
        </div>
        <Avatar icon={<LogoutOutlined title="Log out" />} size={{ xs: 37, sm: 50, md: 50, lg: 55, xl: 55, xxl: 57 }} style={{ marginTop: '4px' }} onClick={logOutHandler} />
      </Header>
      <Content style={{ height: '100%', paddingTop: '1rem', color: 'transparent' }}>{children}</Content>
      {/* <Footer></Footer> */}
    </Layout>
  )
}

export default PrivateLayout
