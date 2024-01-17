import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import { Props } from '../../src/types/general'

const PublicRoute = ({ children }: Props): JSX.Element => {
  const navigate = useNavigate()

  useEffect(() => {
    if (localStorage.getItem('auth')) {
      navigate('/courses')
    }
  }, [])

  return <>{children}</>
}

export default PublicRoute
