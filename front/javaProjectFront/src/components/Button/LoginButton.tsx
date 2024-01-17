import { Button } from 'antd'
import { Props } from '../../types/general'

const LoginButton = ({ children }: Props): JSX.Element => {
  return (
    <Button
      type="primary"
      htmlType="submit"
      size="large"
      style={{ width: '100%', color: 'white', backgroundColor: '#38a89d', fontFamily: 'sans-serif', lineHeight: '1rem', borderRadius: '3px', textAlign: 'center' }}
    >
      {children}
    </Button>
  )
}

export default LoginButton
