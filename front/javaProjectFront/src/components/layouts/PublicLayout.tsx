import { Props } from '../../types/general'
import { Layout, Avatar, Typography } from 'antd'
import { ArrowLeftOutlined } from '@ant-design/icons'
import { useNavigate, useLocation } from 'react-router-dom'
const { Content, Header } = Layout
const { Title } = Typography

export const PublicLayout = ({ children }: Props): JSX.Element => {
  const navigate = useNavigate()
  const location = useLocation()
  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Header style={{ backgroundColor: 'rgb(56, 168, 157)', display: 'flex', alignItems: 'center', justifyContent: 'space-between', height: '64px', padding: '10px' }}>
        {location.pathname === '/password_recovery' ? (
          <Avatar
            icon={<ArrowLeftOutlined title="Log out" />}
            size={{ xs: 37, sm: 50, md: 50, lg: 55, xl: 55, xxl: 57 }}
            style={{ marginTop: '4px' }}
            onClick={() => navigate(-1)}
          />
        ) : (
          ''
        )}
        <Title level={1} style={{ color: 'white', fontSize: '20px', display: 'block', margin: 'auto ' }}>
          Courses Managment
        </Title>
      </Header>
      <Content style={{ paddingTop: '50px' }}>{children}</Content>
    </Layout>
  )
}

export default PublicLayout
